/**
 * Created by Bright Huang on 11/5/14.
 */

define(['jquery', 'bootstrap', 'sannong', 'validate', 'ajaxHandler', 'formValidator', 'additionalMethods'],
    function($, bootstrap, sannong, validate, ajaxHandler, formValidator, additionalMethods) {

        "use strict";

        var login = {};
        login.View = {
            showLoginError: function(message){
                $('#login-form .errorMsg span').remove();
                $('#login-error-container').append('<span><em id="login-error" class="error" style="display: inline;">' + message + '</em></span>');
                $('#login-form .errorMsg').show();
            },
            showForgotPasswordError: function(message){
                $('#forgot-password-form .errorMsg span').remove();
                $('#forgot-password-error-container').append('<span><em id="forgot-password-error" class="error" style="display: inline;">' + message + '</em></span>');
                $('#forgot-password-form .errorMsg').show();
            }
        };

        login.Controller = {
            submitLoginFrom: function(){
                var validator = formValidator.getLoginValidator("#login-form");
                if (validator.form() == true){
                    ajaxHandler.sendRequest({
                        type: "POST",
                        url: "/login",
                        dataType: "json",
                        data: $("#login-form").serialize(),
                        success: function (response) {
                                window.location.href = response.uri;
                        },
                        error: function (response) {
                            console.log(response);
                            login.View.showLoginError("提交请求失败");
                        }
                    });
                }
            },
            submitForgotPasswordForm: function(){
                var validator = formValidator.getForgotPasswordValidator("#forgot-password-form");
                if (validator.form() == true){
                    ajaxHandler.sendRequest({
                        type: "POST",
                        url: "/login",
                        //dataType: "json",
                        data: {
                            username: $("#forgot-password-cellphone").val(),
                            password: $("#forgot-password-password").val(),
                            _spring_security_remember_me: $("#forgot-password-remember-me").prop("checked")
                        },
                        success: function (response) {
                            window.location.href = response.uri;
                        },
                        error: function (response) {
                            login.View.showForgotPasswordError("提交请求失败");
                        }
                    });
                }

            },
            sendNewPassword: function(){
                var validator = formValidator.getForgotPasswordValidator("#forgotPasswordForm");
                var realNameValid = validator.element($("#forgot-password-real-name"));
                var cellphoneValid = validator.element($("#forgot-password-cellphone"));
                if ((cellphoneValid == true) && (realNameValid == true)){
                    ajaxHandler.sendRequest({
                        url: '/sms/password',
                        type: 'POST',
                        dataType: 'json',
                        data: $("#login-form").serialize(),
                        success: function(response){
                            login.View.showForgotPasswordError(response.statusMessage)
                            additionalMethods.updateTimeLabel("#forgot-password-send-password", "密码");
                        },
                        error: function(response){
                            login.View.showError("发送新密码失败")
                        }
                    });
                }
            }
        };

        function addEventListener(){
            $('.radioCustom input').click(function () {
                $(this).parents(".radioRow").find(".radioCustom").removeClass("radioCustom-checked");
                $(this).parent(".radioCustom").addClass("radioCustom-checked");
            })

            $('.checkboxCustom').click(function () {
                $(this).toggleClass('checkboxCustom-checked');
                var $checkbox = $(this).find(':checkbox');
                $checkbox.attr('checked', !$checkbox.attr('checked'));
            })

            $("#login-form-submit").click(function () {
                login.Controller.submitLoginFrom();
            })

            $('#forgot-password-link').click(function () {
                $('#loginModalCloseBtn').click();
            })

            $("#forgot-password-send-password").click(function(){
                login.Controller.sendNewPassword();
            })

            $("#forgot-password-submit").click(function () {
                login.Controller.submitForgotPasswordForm();
            })
        }

        $(function() {
            addEventListener();
        });

        sannong.Login = login;
        return login;

});
