/**
 * Created by Bright Huang on 11/6/14.
 */

define(['jquery', 'bootstrap', 'handlebars', 'sannong', 'validate', 'ajaxHandler', 'formValidator',
        'additionalMethods', 'pagination', 'selector', 'jqueryForm', 'eventHandler', 'questionnaire'],
        function($, bootstrap, handlebars, sannong, validate, ajaxHandler, formValidator,
                 additionalMethods, pagination, selector, jqueryForm, eventHandler, questionnaire) {

            "use strict";
            var searchParams = "";
            var userList = {};

            userList.Model = {
                currentEditUser: ""
            };

            userList.View = {
                userProfileEditView: $("#userProfileEditView"),
                questionnaireTable: $("#questionnaire-table"),
                searchBar: $("#searchBar"),
                userListTitle: $("#user-management-title"),
                userTextShow: $("#userTextShow"),
                userListTable: $("#userListTable"),
                userProfileCancel: $("#userProfileCancel"),
                emptyUserProfileEditView: function(){
                    $("#userProfileEditView").empty();
                },
                showUserProfileEditView: function(){
                    userList.View.questionnaireTable.hide();
                    userList.View.userListTitle.hide();
                    userList.View.userTextShow.hide();
                    userList.View.searchBar.hide();
                    userList.View.userListTable.hide();
                    userList.View.userProfileEditView.show();
                },
                resetView: function(){
                    userList.View.userListTitle.show();
                    userList.View.userListTable.show();
                    userList.View.searchBar.show();
                    userList.View.questionnaireTable.hide();
                    userList.View.userTextShow.hide();
                    userList.View.userProfileEditView.hide();
                },
                showQuestionnaire: function(questionnaireNo){
                    $("#questionnaireNo").val(questionnaireNo);
                    $("#userTextShow").show();
                    $("#user-management-title").hide();
                    $("#userListTable").hide();
                    $("#questionnaire-table").show();
                    $("#questionnaireStatus").show();
                    $("#update-success").remove();
                    $("#update-error").remove();

                    if ($("#questionnaire-table").show()) {
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
                renderQuestionnaireView: function(questionnaireNumber, application){
                    var latestQuestionnaireNumber = application.questionnaires.length,
                        questionnaireObj = application.questionnaires[questionnaireNumber - 1],
                        user = application.user;

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

                    if (questionnaireObj === null || questionnaireObj === undefined) {
                        questionnaire.Controller.showQuestionnaire(questionnaireNumber);
                    }else{
                        questionnaire.View.renderQuestionnaireView(questionnaireObj);
                    }

                    if (questionnaireObj !== null && questionnaireObj !== undefined){
                        questionnaire.View.fillAnswers(questionnaireNumber, questionnaireObj.concatenatedAnswers, false);
                    }

                    questionnaire.View.renderApplicationComments(application);
                }

            };

            userList.Controller = {
                retrieve: function() {
                    var searchKey = $("#searchKey").val();
                    var searchValue = $("#searchValue").val();

                    var parameter;
                    if (searchKey == "手机号"){
                        parameter = "cellphone=" + searchValue;
                    }else if (searchKey == "姓名"){
                        parameter = "realName=" + searchValue;
                    }else if (searchKey == "工作单位"){
                        parameter = "company=" + searchValue;
                    }else if (searchKey == "职位"){
                        parameter = "jobTitle=" + searchValue;
                    }else if (searchKey == "电子邮箱"){
                        parameter = "mailbox=" + searchValue;
                    }else if (searchKey == "单位地址"){
                        parameter = "companyAddress=" + searchValue;
                    }
                    var provinceIndex = $("#provinceQuerySelect").val();
                    var cityIndex = $("#cityQuerySelect").val();
                    var districtIndex = $("#districtQuerySelect").val();

                    parameter = parameter + "&provinceIndex=" + provinceIndex + "&cityIndex=" + cityIndex + "&districtIndex=" + districtIndex;
                    //parameter = "realName=william&provinceIndex=&cityIndex=&districtIndex=";
                    searchParams = parameter;

                    $.ajax({
                        type : "get",
                        dataType : "text",
                        url : "userTotalCount",
                        data : parameter,
                        success : function(totalCount) {
                            // pagination and data list presentation
                            showTotalPageNumber(totalCount, parameter);
                        }
                    });
                },
                cancel: function () {
                    userList.View.userTextShow.hide();
                    $("#questionnaire-table").hide();
                    $("#userListTable").show();
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
                    var currentPage = $("#current-page-number").text();
                    var previousPage = parseInt(currentPage) - 1;

                    if (currentPage == 1){
                        $("#previous").removeClass("activeBt");
                        return;
                    }
                    if (previousPage == 1){
                        $("#previous").removeClass("activeBt");
                    }

                    var pageIndex = parseInt(currentPage) - 1;
                    $("#current-page-number").text(pageIndex);
                },
                next: function(){
                    $("#previous").addClass("activeBt");
                    $("#next").addClass("activeBt");

                    var currentPage = $("#current-page-number").text();
                    var nextPage = parseInt(currentPage) + 1;
                    var totalPage = $("#total-page-number").text();

                    if (currentPage == totalPage){
                        $("#next").removeClass("activeBt");
                        return;
                    }
                    if (nextPage == totalPage){
                        $("#next").removeClass("activeBt");
                    }

                    var pageIndex = parseInt(currentPage) + 1;
                    $("#current-page-number").text(pageIndex);
                },

                q1: function(){
                    userList.Controller.showQuestionnaire(1,"");
                },
                q2: function(){
                    userList.Controller.showQuestionnaire(2,"");
                },
                q3: function(){
                    userList.Controller.showQuestionnaire(3,"");
                },
                q4: function(){
                    userList.Controller.showQuestionnaire(4,"");
                },
                q5: function(){
                    userList.Controller.showQuestionnaire(5,"");
                },
                renderUserProfileEditView: function(userName, viewName){
                    ajaxHandler.sendRequest({
                        type: "GET",
                        url: "/user-management/users/" + userName + '/profile',

                        success: function(response){
                            if (response.code < 2000){
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
                                        userList.Model.currentEditUser = "";
                                        userList.View.resetView();
                                    });
                                }
                            }
                        },
                        fail: function(){
                        }
                    });
                },
                editUserProfile: function(userName){
                    userList.Model.currentEditUser = userName;
                    userList.View.showUserProfileEditView();
                    userList.Controller.renderUserProfileEditView(userName, "#userProfileEditView");
                },
                showQuestionnaire: function (questionnaireNumber, userName) {
                    userList.View.showQuestionnaire(questionnaireNumber);

                    if (userName != "") {
                        $("#cellphone").val(userName);
                        $("#commentContent").val("");
                    } else {
                        userName = $("#cellphone").val();
                    }

                    $.ajax({
                        type : "GET",
                        dataType : "json",
                        url : '/user-management/users/' + userName + '/application',
                        success : function(data) {
                            userList.View.renderQuestionnaireView(questionnaireNumber, data);
                        },
                        fail: function(data){

                        }
                    });
                },
                showUserByPage: function (pageNumber) {
                    $("#userTextShow").hide();

                    $.ajax({
                        type : "GET",
                        dataType : "json",
                        url : '/api/users',
                        data:{pageNumber: pageNumber, perPage: 10},
                        success : function(data) {
                            handlebars.registerHelper("addOne", function(index){return index + 1;});

                            var compiler = handlebars.compile($("#table-template").html()),
                                html = compiler(data.content);

                            $("#user-list").empty();
                            $("#user-list").append(html);
                            $("#total-page-number").text(data.totalPages);
                        },
                        error: function(response){
                            console.log(response);
                        }
                    });
                }
            }

            function subscribeEvent(){
                eventHandler.subscribe("userList:cancel", userList.Controller.cancel);
                eventHandler.subscribe("userList:update", userList.Controller.update);
                eventHandler.subscribe("userList:submit", userList.Controller.submit);
                eventHandler.subscribe("userList:retrieve", userList.Controller.retrieve);
                eventHandler.subscribe("userList:previous", userList.Controller.previous);
                eventHandler.subscribe("userList:next", userList.Controller.next);
                eventHandler.subscribe("userList:q1", userList.Controller.q1);
                eventHandler.subscribe("userList:q2", userList.Controller.q2);
                eventHandler.subscribe("userList:q3", userList.Controller.q3);
                eventHandler.subscribe("userList:q4", userList.Controller.q4);
                eventHandler.subscribe("userList:q5", userList.Controller.q5);
            }

            $(function() {
                subscribeEvent();
                userList.Controller.showUserByPage(1);
            })

            sannong.UserList = userList;
            return userList;
});