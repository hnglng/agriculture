/**
 * Created by apple on 11/27/14.
 */
require(['../main'], function () {
    require(['jquery', 'bootstrap', 'handlebars', 'sannong', 'validate', 'ajaxHandler',
            'formValidator', 'additionalMethods', 'pagination', 'selector', 'jqueryForm',
            'questionnaire', 'login', 'eventHandler', 'userManagement', 'userApplicationForm',
            'userProfile', 'userPassword', 'custom'],
        function($, bootstrap, handlebars, sannong, validate, ajaxHandler,
                 formValidator, additionalMethods, pagination, selector, jqueryForm,
                 questionnaire, login, eventHandler, userManagement, userApplicationForm,
                 userProfile, userPassword, custom) {

            "use strict";

            var userPersonalCenter = {};
            userPersonalCenter.Model = {};
            userPersonalCenter.View = {
            };

            function showMyQuestionnaireAnswers(questionnaireNumber) {
                questionnaire.View.resetQuestionnaireView(questionnaireNumber);

                $("#questionnaireNo").val(questionnaireNumber);

                $.ajax({
                    type : "GET",
                    dataType : "json",
                    url : 'user-personal-center/myapplication',
                    success : function(response) {
                        var data = response.questionnaires[questionnaireNumber - 1],
                            concatenatedAnswers = data.concatenatedAnswers;
                        questionnaire.View.renderQuestionnaireView(data);
                        questionnaire.View.fillAnswers(questionnaireNumber, concatenatedAnswers, false);
                        userManagement.View.renderApplicationComments(response);

                    },
                    fail: function(data){

                    }
                });

            }
            userPersonalCenter.init = function(){

                $("#userManagementTab").click(function(){
                    var currentEditUser = userManagement.Model.currentEditUser;
                    if ( currentEditUser != ""){
                        userProfile.View.emptyUserProfileView();
                        userManagement.Controller.editUserProfile(currentEditUser);
                    }
                });
                $("#userProfileTab").click(function(){
                    userManagement.View.emptyUserProfileEditView();
                    userProfile.Controller.showUserProfileView("", "#userProfileView");
                });
            }

            $(function() {
                eventHandler.registerEventListener();
                userPersonalCenter.init();
                 if ($("#userAppFormTab").length > 0){
                     showMyQuestionnaireAnswers(1);
                }
            })

            sannong.UserPersonalCenter = userPersonalCenter;
            return userPersonalCenter;
        });
});
