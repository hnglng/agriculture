/**
 * Created by Bright Huang on 11/6/14.
 */

define(['jquery', 'bootstrap', 'handlebars', 'sannong', 'validate', 'ajaxHandler', 'formValidator',
        'additionalMethods', 'pagination', 'selector', 'jqueryForm', 'eventHandler', 'questionnaire'],
        function($, bootstrap, handlebars, sannong, validate, ajaxHandler, formValidator,
                 additionalMethods, pagination, selector, jqueryForm, eventHandler, questionnaire) {

            "use strict";
            var userManagement = {};
            userManagement.Model = {
                currentEditUser: "",
                userProfileInitData: {
                    "uerName": "", "cellphone": "", "realName": "", "jobTitle": "",
                    "company": "", "companyAddress": "", "deskPhone": "", "mailbox": ""
                }
            };

            userManagement.View = {
                userManagementView: $("#user-management-view"),
                questionnaireEditView: $("#questionnaire-edit-view"),
                profileEditView: $("#profile-edit-view"),
                switchView: function(targetView){
                    userManagement.View.userManagementView.hide();
                    userManagement.View.questionnaireEditView.hide();
                    userManagement.View.profileEditView.hide();
                    targetView.show();
                },
                renderUserManagementView: function(data){
                    handlebars.registerHelper("addOne", function(index){return index + 1;});
                    var compiler = handlebars.compile($("#table-template").html()),
                        html = compiler(data.content);

                    $("#user-list").empty();
                    $("#user-list").append(html);
                    $("#total-page-number").text(data.totalPages);


                },
                renderProfileEditView: function(isPageLoad, data){
                    var user = data.user,
                        userProfileData = (isPageLoad == true ? userManagement.Model.userProfileInitData : user),
                        compiler = handlebars.compile($("#user-profile-template").html()),
                        html = compiler(userProfileData);

                    userManagement.View.profileEditView.empty();
                    userManagement.View.profileEditView.append(html);

                    var address = {
                        provinceId: user.province.provinceId,
                        cityId: user.city.cityId,
                        districtId: user.district.districtId,
                        cities: data.addressRegion.cities,
                        districts: data.addressRegion.districts
                    }

                    selector.Controller.initAddressSelect("#profile-edit-view", address);

                    $("#profile-edit-cancel").removeClass("hidden");
                },
                renderQuestionnaireEditView: function(application, questionnaireNumber){
                    var latestQuestionnaireNumber = application.questionnaires.length,
                        currentQuestionnaire = application.questionnaires[questionnaireNumber - 1],
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

                    if (currentQuestionnaire === null || currentQuestionnaire === undefined) {
                        questionnaire.Controller.switchQuestionnaire(questionnaireNumber);
                    }else{
                        //questionnaire.View.renderQuestionnaireView(currentQuestionnaire);
                    }

                    if (currentQuestionnaire !== null && currentQuestionnaire !== undefined){
                        questionnaire.View.fillAnswers(questionnaireNumber, currentQuestionnaire.answers, false);
                    }

                    questionnaire.View.renderApplicationComments(application);
                },
                switchQuestionnaire: function(number){
                    $("#questionnaire1").addClass("hidden");
                    $("#questionnaire2").addClass("hidden");
                    $("#questionnaire3").addClass("hidden");
                    $("#questionnaire4").addClass("hidden");
                    $("#questionnaire5").addClass("hidden");
                    $("#questionnaire" + number).removeClass("hidden");

                    if ($(".steps")){
                        $(".no").each(function(){
                            $(this).parent().removeClass("active");

                            if ($(this).text() == number){
                                $(this).parent().addClass("active");
                            }
                        })
                    }
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
                showUserByPage: function (pageNumber) {
                    $("#userTextShow").hide();
                    $.ajax({
                        type : "GET",
                        dataType : "json",
                        url : '/api/users',
                        data:{pageNumber: pageNumber, perPage: 10},
                        success : function(data) {
                            userManagement.View.switchView(userManagement.View.userManagementView);
                            userManagement.View.renderUserManagementView(data);
                        },
                        error: function(data){
                            console.log(data);
                        }
                    });
                },
                search: function() {
                    $.ajax({
                        type : "GET",
                        dataType : "json",
                        url : '/api/users',
                        data : {
                            pageNumber: 1,
                            perPage: 10,
                            searchKey: $("#searchKey").val(),
                            searchValue: $("#searchValue").val(),
                            provinceId: $("#provinceQuerySelect").val(),
                            cityId: $("#cityQuerySelect").val(),
                            districtId: $("#districtQuerySelect").val()
                        },
                        success : function(data) {
                            userManagement.View.renderUserManagementView(data);
                        },
                        error: function(data){
                            console.log(data);
                        }
                    });
                },
                previous: function(){
                    var currentPageNumber = parseInt($("#current-page-number").text(), 10),
                        previousPageNumber = parseInt(currentPageNumber) - 1;

                    userManagement.View.activatePagination();
                    if (currentPageNumber === 1){
                        userManagement.View.inactivatePaginationPrevious();
                        return;     // Do nothing as current page is the first one.
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
                        return;     // Do nothing as current page is the latest one.
                    }
                    if (nextPageNumber === totalPageNumber){
                        userManagement.View.inactivatePaginationNext();
                    }
                    userManagement.View.setCurrentPageNumber(nextPageNumber);
                    userManagement.Controller.showUserByPage(nextPageNumber);
                },
                editUserProfile: function(userId){
                    ajaxHandler.sendRequest({
                        type: "GET",
                        url: "/user-center/user-profile",
                        dataType: "json",
                        data:{userId: userId},
                        success: function(data){
                            userManagement.View.switchView(userManagement.View.profileEditView);
                            userManagement.View.renderProfileEditView(false, data);
                        },
                        error: function(data){
                            console.log(data);
                        }
                    });
                },
                editQuestionnaire: function (userId) {
                    var options = {
                        type: "GET",
                        dataType: "json",
                        url: "/api/applications",
                        data: {userId: userId},
                        success: function(data) {
                            userManagement.View.switchView(userManagement.View.questionnaireEditView);
                            userManagement.View.renderQuestionnaireEditView(data, 1);
                        },
                        error: function(data){
                            console.log(data);
                        }
                    }
                    ajaxHandler.sendRequest(options);
                },
                q1: function(){
                    userManagement.View.switchQuestionnaire(1);
                },
                q2: function(){
                    userManagement.View.switchQuestionnaire(2);
                },
                q3: function(){
                    userManagement.View.switchQuestionnaire(3);
                },
                q4: function(){
                    userManagement.View.switchQuestionnaire(4);
                },
                q5: function(){
                    userManagement.View.switchQuestionnaire(5);
                },
                update: function () {
                    if (formValidator.getValidator("#answerForm").form() == true){
                        $("#myModalTrigger").click();
                    }
                },
                submit: function(){
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
                cancelQuestionnaireEdit: function () {
                    userManagement.View.switchView(userManagement.View.userManagementView);
                },
                cancelProfileEdit: function(){
                    userManagement.Model.currentEditUser = "";
                    userManagement.View.switchView(userManagement.View.userManagementView);
                }
            }

            function subscribeEvent(){
                eventHandler.subscribe("userManagement:cancelQuestionnaireEdit", userManagement.Controller.cancelQuestionnaireEdit);
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
                eventHandler.subscribe("userManagement:cancelProfileEdit", userManagement.Controller.cancelProfileEdit);
            }

            $(function() {
                subscribeEvent();
                selector.initSelect("select");
                userManagement.Controller.showUserByPage(1);
            })

            sannong.UserManagement = userManagement;
            return userManagement;
});