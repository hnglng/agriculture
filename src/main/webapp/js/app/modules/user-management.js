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

            userManagement.View = {
                userManagementView: $("#user-management-view"),
                questionnaireEditView: $("#questionnaire-edit-view"),
                profileEditView: $("#profile-edit-view"),

                searchBar: $("#searchBar"),
                userManagementTitle: $("#user-management-title"),
                userTextShow: $("#userTextShow"),
                userManagementTable: $("#userManagementTable"),
                userProfileCancel: $("#userProfileCancel"),
                emptyUserProfileEditView: function(){
                    $("#userProfileView").empty();
                },
                showProfileEditView: function(){
                    userManagement.View.userManagementView.hide();
                    userManagement.View.questionnaireEditView.hide();
                    userManagement.View.profileEditView.show();
                },
                showUserManagementView: function(){
                    userManagement.View.questionnaireEditView.hide();
                    userManagement.View.profileEditView.hide();
                    userManagement.View.userManagementView.show();
                },
                showQuestionnaireEditView: function(questionnaireNumber, userId){
                    userManagement.View.userManagementView.hide();
                    userManagement.View.profileEditView.hide();
                    userManagement.View.questionnaireEditView.show();

                    ajaxHandler.sendRequest({
                        type : "GET",
                        dataType : "json",
                        url : '/api/applications',
                        data: {userId : userId},
                        success : function(data) {
                            userManagement.View.renderQuestionnaireView(questionnaireNumber, data);
                        },
                        error: function(data){
                            console.log(data);
                        }
                    });
                },
                showQuestionnaire: function(questionnaireNo){
                    $("#questionnaireNo").val(questionnaireNo);
                    $("#userTextShow").show();
                    $("#user-management-title").hide();
                    $("#userManagementTable").hide();
                    $("#user-questionnaire-view").show();
                    $("#questionnaireStatus").show();
                    $("#update-success").remove();
                    $("#update-error").remove();

                    if ($("#user-questionnaire-view").show()) {
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
                },
                activatePagination: function(){
                    $("#previous").addClass("activeBt");
                    $("#next").addClass("activeBt");
                },
                inactivatePaginationNext: function(){
                    $("#next").removeClass("activeBt");
                },
                inactivatePaginationPrevious: function(){
                    $("#previous").removeClass("activeBt");
                },
                setCurrentPageNumber: function(pageNumber){
                    $("#current-page-number").text(pageNumber);
                }

            };

            userManagement.Controller = {
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
                    userManagement.View.userTextShow.hide();
                    $("#user-questionnaire-view").hide();
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
                    var currentPageNumber = parseInt($("#current-page-number").text(), 10),
                        previousPageNumber = parseInt(currentPageNumber) - 1;

                    userManagement.View.activatePagination();
                    if (currentPageNumber === 1){
                        userManagement.View.inactivatePaginationPrevious();
                        return; // Do nothing as current page is the first
                    }
                    if (previousPageNumber === 1){
                        userManagement.View.inactivatePaginationPrevious();
                    }
                    userManagement.View.setCurrentPageNumber(previousPageNumber);

                    userManagement.Controller.showUserByPage(previousPageNumber);

                },
                next: function(){
                    var currentPageNumber = parseInt($("#current-page-number").text(), 10),
                        totalPageNumber = parseInt($("#total-page-number").text(), 10),
                        nextPageNumber = currentPageNumber + 1;

                    userManagement.View.activatePagination();
                    if (currentPageNumber === totalPageNumber) {
                        userManagement.View.inactivatePaginationNext();
                        return; // Do nothing as current page is the latest
                    }
                    if (nextPageNumber === totalPageNumber){
                        userManagement.View.inactivatePaginationNext();
                    }
                    userManagement.View.setCurrentPageNumber(nextPageNumber);

                    userManagement.Controller.showUserByPage(nextPageNumber);
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
                renderProfileEditView: function(userId, viewName){
                    ajaxHandler.sendRequest({
                        type: "GET",
                        url: "/api/users/" + userId,
                        success: function(response){
                            var userProfileViewHandler = handlebars.compile($("#user-profile-template").html()),
                                html = userProfileViewHandler(response.models.userProfile);

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

                            if (viewName == "#userProfileView"){
                                $("#userProfileCancel").removeClass("hidden");
                                $("#userProfileCancel").click(function () {
                                    userManagement.Model.currentEditUser = "";
                                    userManagement.View.showUserListView();
                                });
                            }
                        },
                        error: function(){
                        }
                    });
                },
                editUserProfile: function(userId){
                    userManagement.Model.currentEditUser = userId;
                    userManagement.View.showProfileEditView();
                    userManagement.Controller.renderProfileEditView(userId, "#profile-edit-view");
                },
                editQuestionnaire: function (questionnaireNumber, userId) {

                    userManagement.View.showQuestionnaireEditView(questionnaireNumber, userId);
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
                eventHandler.subscribe("userManagement:cancel", userManagement.Controller.cancel);
                eventHandler.subscribe("userManagement:update", userManagement.Controller.update);
                eventHandler.subscribe("userManagement:submit", userManagement.Controller.submit);
                eventHandler.subscribe("userManagement:retrieve", userManagement.Controller.retrieve);
                eventHandler.subscribe("userManagement:previous", userManagement.Controller.previous);
                eventHandler.subscribe("userManagement:next", userManagement.Controller.next);
                eventHandler.subscribe("userManagement:q1", userManagement.Controller.q1);
                eventHandler.subscribe("userManagement:q2", userManagement.Controller.q2);
                eventHandler.subscribe("userManagement:q3", userManagement.Controller.q3);
                eventHandler.subscribe("userManagement:q4", userManagement.Controller.q4);
                eventHandler.subscribe("userManagement:q5", userManagement.Controller.q5);
            }

            $(function() {
                subscribeEvent();
                userManagement.Controller.showUserByPage(1);
            })

            sannong.UserManagement = userManagement;
            return userManagement;
});