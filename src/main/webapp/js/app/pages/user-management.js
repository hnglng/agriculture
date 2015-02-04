/**
 * Created by apple on 11/27/14.
 */
require(['../main'], function () {
    require(['jquery', 'bootstrap', 'handlebars', 'sannong', 'validate', 'ajaxHandler',
            'formValidator', 'additionalMethods', 'pagination', 'selector', 'jqueryForm',
            'questionnaire', 'login', 'eventHandler', 'userManagement', 'userApplication',
            'userProfile', 'userPassword', 'custom'],
        function($, bootstrap, handlebars, sannong, validate, ajaxHandler,
                 formValidator, additionalMethods, pagination, selector, jqueryForm,
                 questionnaire, login, eventHandler, userManagement, userApplication,
                 userProfile, userPassword, custom) {

            "use strict";

            var userManagement = {};
            userManagement.Model = {};
            userManagement.View = {
            };

            userManagement.init = function(){

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
                userManagement.init();
                 if ($("#userAppFormTab").length > 0){
                     userApplication.Controller.showMyApplication(1);

                }
            })

            sannong.UserManagement = userManagement;
            return userManagement;
        });
});
