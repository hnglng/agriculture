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

            var userCenter = {};
            userCenter.Model = {};
            userCenter.View = {};


            userCenter.init = function(){
            }

            $(function() {
                eventHandler.registerEventListener();
                userCenter.init();
            })

            sannong.UserCenter = userCenter;
            return userCenter;
        });
});
