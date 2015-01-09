/**
 * Created by Bright Huang on 11/6/14.
 */

define(['jquery', 'bootstrap', 'handlebars', 'sannong', 'validate', 'ajaxHandler', 'formValidator',
        'additionalMethods', 'pagination', 'selector', 'jqueryForm', 'eventHandler', 'questionnaire'],
        function($, bootstrap, handlebars, sannong, validate, ajaxHandler, formValidator,
                 additionalMethods, pagination, selector, jqueryForm, eventHandler, questionnaire) {

            "use strict";
            var searchParams = "";
            var userManagement = {};


            userManagement.Model = {
                currentEditUser: ""
            };

            /***********************************************
             *  View
             ***********************************************/
            userManagement.View = {
                userProfileEditView: $("#userProfileEditView"),
                questionnaireTable: $("#questionnaireTable"),
                searchBar: $("#searchBar"),
                userManagementTitle: $("#user-management-title"),
                userTextShow: $("#userTextShow"),
                userManagementTable: $("#userManagementTable"),
                userProfileCancel: $("#userProfileCancel"),
                emptyUserProfileEditView: function(){
                    $("#userProfileEditView").empty();
                },
                showUserProfileEditView: function(){
                    userManagement.View.questionnaireTable.hide();
                    userManagement.View.userManagementTitle.hide();
                    userManagement.View.userTextShow.hide();
                    userManagement.View.searchBar.hide();
                    userManagement.View.userManagementTable.hide();
                    userManagement.View.userProfileEditView.show();
                },
                resetView: function(){
                    userManagement.View.userManagementTitle.show();
                    userManagement.View.userManagementTable.show();
                    userManagement.View.searchBar.show();
                    userManagement.View.questionnaireTable.hide();
                    userManagement.View.userTextShow.hide();
                    userManagement.View.userProfileEditView.hide();
                },
                showQuestionnaire: function(questionnaireNo){
                    $("#questionnaireNo").val(questionnaireNo);
                    $("#userTextShow").show();
                    $("#user-management-title").hide();
                    $("#userManagementTable").hide();
                    $("#questionnaireTable").show();
                    $("#questionnaireStatus").show();
                    $("#update-success").remove();
                    $("#update-error").remove();

                    if ($("#questionnaireTable").show()) {
                        $("#questionList").empty();
                    }

                    if ($(".steps")){
                        $(".no").each(function(){
                            $(this).parent().removeClass("active");

                            if ($(this).text() == questionnaireNo){
                                $(this).parent().addClass("active");
                            }
                        })
                    }

                    if ($(".error")) {
                        $(".error").empty();
                    }
                },
                renderApplicationComments: function(data){
                    var applicationId = data.applicationId,
                        comments = data.comments,
                        lastQuestionnaireNumber = data.questionnaires.length,
                        lastQuestionnaireCommitted = data.questionnaires[lastQuestionnaireNumber - 1].questionnaireCommitted;

                    if ($("#applicationId")){
                        $("#applicationId").val(applicationId);
                    }

                    if (comments != null && comments != ""){
                        if($("#questionnaireStatus")){
                            $("#questionnaireStatus").text(comments);
                            $("#questionnaireStatus").show();
                        }
                    } else if (lastQuestionnaireNumber == 5 && lastQuestionnaireCommitted === true){
                        $("#questionnaireStatus").text("您的申请正在审核中。请保存手机畅通，我们的工作人员会尽快联系您。");
                        $("#questionnaireStatus").show();
                    } else{
                        $("#questionnaireStatus").text("请完成所有问卷调查，然后我们的工作人员会第一时间联系您。");
                        $("#questionnaireStatus").show();
                    }
                },
                renderQuestionnaireView: function(questionnaireNumber, data){
                    var latestQuestionnaireNumber = data.questionnaires.length,
                        questionnaireObj = data.questionnaires[questionnaireNumber - 1],
                        concatenatedAnswers = questionnaireObj.concatenatedAnswers,
                        user = data.user;

                    if (questionnaireNumber > latestQuestionnaireNumber){
                        $("#update").removeClass("orange-bt-small").addClass("gray-bt-small");
                        $("#update").attr("disabled",true);
                    }else{
                        $("#update").removeClass("gray-bt-small").addClass("orange-bt-small");
                        $("#update").attr("disabled", false);
                    }

                    if (user != null) {
                        $("#userName").val(user.userName);
                        $("#userRealName").text(user.realName);
                        $("#userTextShow").show();
                    }

                    questionnaire.View.renderQuestionnaireView(questionnaireObj);

                    questionnaire.View.fillAnswers(questionnaireNumber, concatenatedAnswers, false);

                    userManagement.View.renderApplicationComments(data);
                }

            };


            /**********************************************************
             *  Controller
             **********************************************************/

            userManagement.Controller = {
                retrieve: function() {
                    var searchKey = $("#searchKey").val();
                    var searchValue = $("#searchValue").val();

                    var parameter;
                    if (searchKey == "手机号"){
                        parameter = "mobilePhone=" + searchValue;
                    }else if (searchKey == "姓名"){
                        parameter = "realName=" + searchValue;
                    }else if (searchKey == "工作单位"){
                        parameter = "companyName=" + searchValue;
                    }else if (searchKey == "职位"){
                        parameter = "jobTitle=" + searchValue;
                    }else if (searchKey == "电子邮箱"){
                        parameter = "mailbox=" + searchValue;
                    }else if (searchKey == "单位地址"){
                        parameter = "companyAddress=" + searchValue;
                    }
                    var provinceId = $("#provinceQuerySelect").val();
                    var cityId = $("#cityQuerySelect").val();
                    var districtId = $("#districtQuerySelect").val();

                    parameter = parameter + "&provinceId=" + provinceId + "&cityId=" + cityId + "&districtId=" + districtId;
//                    searchParams = parameter;

                    $.ajax({
                        type : "get",
                        dataType : "text",
                        url : "user-personal-center/userTotalCount",
                        data : parameter,
                        success : function(totalCount) {
                            // pagination and data list presentation
                            var pageSize = 10;
                            if(totalCount == 0){
                                $("#totalPage").text(1);
                            }else{
                                $("#totalPage").text(Math.ceil(totalCount/pageSize));
                            }
                        }
                    });
                    showUserPagination(1,parameter);
                },
                cancel: function () {
                    userManagement.View.userTextShow.hide();
                    $("#questionnaireTable").hide();
                    $("#userManagementTable").show();
                    $("#searchBar").show();
                    $("#user-management-title").show();
                    $("#questionnaireTab li").removeClass("active")
                    $("#questionnaireTab li:first-child").addClass("active");
                },
                update: function () {
                    if (formValidator.getValidator("#answerForm").form() == true){

                        //var questionnaireNo = $("#questionnaireNo").val();
                        //var answerStatus = questionnaireNo + "1";
                        //$("#answerStatus").val(answerStatus);

                        $("#myModalTrigger").click();
                    }
                },
                submit: function(event){
                    $("#answerForm").ajaxSubmit(function(message) {
                        if (message.result == true){
                            $("#update-success").remove();
                            $("#update-error").remove();
                            $("#return").click();
                            $("#update").after('<label id="update-success" class="update">已更新成功</label>');
                        }else{
                            $("#update-success").remove();
                            $("#update-error").remove();
                            $("#update").after('<label id="update-error" class="update">更新失败</label>');
                        }
                    });
                    return false;
                },
                previous: function(){
                    $("#previous").addClass("activeBt");
                    $("#next").addClass("activeBt");
                    var currentPage = $("#currentPage").text();
                    var previousPage = parseInt(currentPage) - 1;

                    if (currentPage == 1){
                        $("#previous").removeClass("activeBt");
                        return;
                    }
                    if (previousPage == 1){
                        $("#previous").removeClass("activeBt");
                    }

                    var pageIndex = parseInt(currentPage) - 1;
                    $("#currentPage").text(pageIndex);
                    InitTable(pageIndex, searchParams);
                },
                next: function(){
                    $("#previous").addClass("activeBt");
                    $("#next").addClass("activeBt");
                    var currentPage = $("#currentPage").text();
                    var nextPage = parseInt(currentPage) + 1;
                    var totalPage = $("#totalPage").text();

                    if (currentPage == totalPage){
                        $("#next").removeClass("activeBt");
                        return;
                    }
                    if (nextPage == totalPage){
                        $("#next").removeClass("activeBt");
                    }

                    var pageIndex = parseInt(currentPage) + 1;
                    $("#currentPage").text(pageIndex);
                    InitTable(pageIndex, searchParams);
                },
                exportCSV: function() {
                    var searchKey = $("#searchKey").val(),
                        searchValue = $("#searchValue").val(),
                        provinceId = $("#provinceQuerySelect").val(),
                        cityId = $("#cityQuerySelect").val(),
                        districtId = $("#districtQuerySelect").val(),
                        parameter;

                    if (searchKey == "手机号"){
                        parameter = "mobilePhone=" + searchValue;
                    }else if (searchKey == "姓名"){
                        parameter = "realName=" + searchValue;
                    }else if (searchKey == "工作单位"){
                        parameter = "companyName=" + searchValue;
                    }else if (searchKey == "职位"){
                        parameter = "jobTitle=" + searchValue;
                    }else if (searchKey == "电子邮箱"){
                        parameter = "mailbox=" + searchValue;
                    }else if (searchKey == "单位地址"){
                        parameter = "companyAddress=" + searchValue;
                    }

                    parameter = parameter + "&provinceId=" + provinceId + "&cityId=" + cityId + "&districtId=" + districtId;

                    $.ajax({
                        type : "post",
                        dataType : "json",
                        url : "user-personal-center/exportCSV",
                        data : parameter,
                        success : function(data) {
                            $('#exportModal').modal('hide');
                            location.href = "/sannong/user-personal-center/downloadCsv?csvFileName=" + data.returnValue;
                        }
                    });
                },
                q1: function(){
                    userManagement.Controller.showQuestionnaire(1,"");
                },
                q2: function(){
                    userManagement.Controller.showQuestionnaire(2,"");
                },
                q3: function(){
                    userManagement.Controller.showQuestionnaire(3,"");
                },
                q4: function(){
                    userManagement.Controller.showQuestionnaire(4,"");
                },
                q5: function(){
                    userManagement.Controller.showQuestionnaire(5,"");
                },
                renderUserProfileEditView: function(userName, viewName){
                    ajaxHandler.sendRequest({
                        type: "GET",
                        url: "user-personal-center/user-profile",
                        data:{userName: userName},
                        success: function(response){
                            if (response.statusCode < 2000){
                                var userProfileViewHandler = handlebars.compile($("#user-profile-template").html());
                                var html = userProfileViewHandler(response.models.userProfile);
                                $(viewName).empty();
                                $(viewName).append(html);

                                selector.View.addCityOptions(viewName + " #citySelect", response.models.cities);
                                selector.View.addDistrictOptions(viewName + " #districtSelect", response.models.districts);
                                selector.View.selectOption(viewName + " #provinceSelect", response.models.userProfile.companyProvince);
                                selector.View.selectOption(viewName + " #citySelect", response.models.userProfile.companyCity);
                                selector.View.selectOption(viewName + " #districtSelect", response.models.userProfile.companyDistrict);

                                selector.initSelect('select', {
                                    provinceOption: {
                                        value: response.models.userProfile.companyProvince,
                                        text: $("#provinceSelect option:selected").text()
                                    },
                                    cityOption: {
                                        value: response.models.userProfile.companyCity,
                                        text: $("#citySelect option:selected").text()
                                    },
                                    districtOption: {
                                        value: response.models.userProfile.companyDistrict,
                                        text: $("#districtSelect option:selected").text()
                                    }
                                });

                                if (viewName == "#userProfileEditView"){
                                    $("#userProfileCancel").removeClass("hidden");
                                    $("#userProfileCancel").click(function () {
                                        userManagement.Model.currentEditUser = "";
                                        userManagement.View.resetView();
                                    });
                                }
                            }
                        },
                        fail: function(){
                        }
                    });
                },
                editUserProfile: function(userName){
                    userManagement.Model.currentEditUser = userName;
                    userManagement.View.showUserProfileEditView();
                    userManagement.Controller.renderUserProfileEditView(userName, "#userProfileEditView");
                },
                showQuestionnaire: function (questionnaireNumber, userName) {
                    userManagement.View.showQuestionnaire(questionnaireNumber);

                    if (userName != "") {
                        $("#mobilePhone").val(userName);
                        $("#commentContent").val("");
                    } else {
                        userName = $("#mobilePhone").val();
                    }

                    $.ajax({
                        type : "GET",
                        dataType : "json",
                        url : 'user-personal-center/applications/' + userName,
                        success : function(data) {
                            userManagement.View.renderQuestionnaireView(questionnaireNumber, data);
                        },
                        fail: function(data){

                        }
                    });
                }
            }


            /************************************************************
             * Private functions
             ************************************************************/
            function showUserPagination(pageNumber,parameter) {
                $("#userTextShow").hide();

                $.ajax({
                    type : "GET",
                    dataType : "json",
                    url : 'user-personal-center/users/page/'+pageNumber,
                    data : parameter,
                    success : function(data) {
                        handlebars.registerHelper("addOne", function(index){return index + 1;});
                        var compiler = handlebars.compile($("#table-template").html()),
                            html = compiler(data);
                        $("#userList").empty();
                        $("#userList").append(html);
                    }
                });
            }

            function showPageTotal() {
                $.ajax({
                    type : "GET",
                    dataType : "text",
                    url : "user-personal-center/userTotalCount",
                    success : function(totalCount) {
                        var pageSize = 10;
                        if(totalCount == 0){
                            $("#totalPage").text(1);
                        }else{
                            $("#totalPage").text(Math.ceil(totalCount/pageSize));
                        }
                    }
                });
            }


            function subscribeEvent(){
                eventHandler.subscribe("userManagement:cancel", userManagement.Controller.cancel);
                eventHandler.subscribe("userManagement:update", userManagement.Controller.update);
                eventHandler.subscribe("userManagement:submit", userManagement.Controller.submit);
                eventHandler.subscribe("userManagement:retrieve", userManagement.Controller.retrieve);
                eventHandler.subscribe("userManagement:previous", userManagement.Controller.previous);
                eventHandler.subscribe("userManagement:next", userManagement.Controller.next);
                eventHandler.subscribe("userManagement:exportCSV", userManagement.Controller.exportCSV);
                eventHandler.subscribe("userManagement:q1", userManagement.Controller.q1);
                eventHandler.subscribe("userManagement:q2", userManagement.Controller.q2);
                eventHandler.subscribe("userManagement:q3", userManagement.Controller.q3);
                eventHandler.subscribe("userManagement:q4", userManagement.Controller.q4);
                eventHandler.subscribe("userManagement:q5", userManagement.Controller.q5);
            }


            /*************************
             * DOM ready function
             ************************/
            $(function() {
                subscribeEvent();
                showPageTotal();
                showUserPagination(1);
            })

            sannong.UserManagement = userManagement;
            return userManagement;
});