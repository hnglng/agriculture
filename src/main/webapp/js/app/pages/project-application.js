/**
 * Created by Bright Huang on 10/26/14.
 */

require(['../main'], function () {
    require(['jquery', 'bootstrap', 'handlebars', 'sannong', 'login', 'validate', 'ajaxHandler',
            'questionnaire', 'jqueryForm', 'formValidator', 'selector', 'additionalMethods', 'custom'],
        function ($, bootstrap, handlebars, sannong, login, validate, ajaxHandler,
                  questionnaire, jqueryForm, formValidator, selector, additionalMethods, custom) {

            "use strict";

            var application = {};

            application.Model = {};

            application.View = {
                cellphone: $("#mobile-phone"),
                cellphoneError: $("#cellphone-error"),
                validationCode: $("#alidation-code"),
                applicationFormSubmit: $("#submit"),
                showUniqueCellphoneError: function (message) {
                    var errorLabel = '<label id="mobile-phone-error" class="error" for="mobile-phone" style="display: inline-block;">' + message + '</label>';
                    application.View.cellphoneError.remove();
                    application.View.cellphone.removeClass("error");
                    application.View.cellphone.after(errorLabel);
                    application.View.cellphone.addClass("error");
                },
                showValidationCodeError: function (message) {
                    var errorLabel = '<label id="validation-code-error" class="error" for="validation-code">' + message + '</label>';
                    application.View.validationCode.removeClass("error");
                    application.View.validationCode.removeAttr("aria-invalid");

                    if ($("#validation-code-error") !== undefined) {
                        $("#validation-code-error").text(message);
                    } else {
                        application.View.validationCode.after(errorLabel);
                    }
                    application.View.validationCode.addClass("error");
                    application.View.validationCode.attr("aria-invalid", "true");
                    application.View.validationCode.attr("style", "display: inline-block");

                },
                showValidationCodeMessage: function (message) {
                    application.View.showValidationCodeError(message);
                },
                enableSubmitButton: function () {
                    application.View.applicationFormSubmit.removeClass("disabled");
                },
                disableSubmitButton: function () {
                    application.View.applicationFormSubmit.addClass("disabled");
                },
                showQuestions: function () {
                    $.ajax({
                        type: "GET",
                        dataType: "json",
                        url: '/questions/questionnaireNumbers/1',
                        success: function (data) {
                            questionnaire.View.renderQuestions(data._embedded.questionList);
                        }
                    });
                }
            };

            application.Controller = {
                handleValidationCodeClick: function () {
                    if ($("#alidation-button").hasClass("disabled")) { return;}

                    if (formValidator.getValidator("#projectAppForm").form() == true) {
                        ajaxHandler.sendRequest({
                            url: '/sms/captcha',
                            type: 'POST',
                            data: {
                                mobilePhone: $("#mobile-phone").val()
                            },
                            success: function (response) {
                                if (response.statusCode < 2000) {
                                    additionalMethods.updateTimeLabel("#validation-button", "验证码");
                                    application.View.enableSubmitButton();
                                    $("#validation-button").addClass("disabled");
                                    $("#validation-code").removeAttr("disabled");
                                    application.View.showValidationCodeMessage(response.statusMessage);
                                } else {
                                    $("#alidation-button").removeClass("disabled");
                                    $("#alidation-code").attr({disabled: "disabled"});
                                    application.View.disableSubmitButton();
                                    if (response.statusCode == 2012) {
                                        application.View.showUniqueCellphoneError(response.statusMessage);
                                    } else {
                                        application.View.showValidationCodeError(response.statusMessage);
                                    }
                                }
                            },
                            fail: function (response) {
                                $("#alidation-button").removeClass("disabled");
                                $("#alidation-code").removeAttr("disabled");
                                application.View.disableSubmitButton();
                                application.View.showValidationCodeError("验证码发送失败");
                            }

                        });
                    }
                },
                handleFormSubmit: function () {
                    if ($("#submit").hasClass("disabled") == true) { return; }

                    if (formValidator.getValidator("#projectAppForm").form() == true) {
                        $("#projectAppModelTrigger").click();
                    }
                },
                handleConfirmedSubmit: function () {
                    var option = {
                        type: 'POST',
                        contentType: 'application/json',
                        url: "/project-application",
                        dataType: "json",
                        data: $("#projectAppForm").serialize(),
                        success: function(data, textStatus, jqXHR){
                            console.log(textStatus)
                        },
                        error: function(jqXHR, textStatus, errorThrown){
                            console.log(textStatus)
                        }
                    }

                    ajaxHandler.sendRequest(option);
                }
            };

            function registerEventListener() {
                $("#validation-button").click(function () {
                    application.Controller.handleValidationCodeClick();
                });

                $("#submit-button").click(function () {
                    application.Controller.handleFormSubmit();
                });

                $("#confirmed-submit").click(function () {
                    application.Controller.handleConfirmedSubmit();
                });
            }

            $(function () {
                selector.initSelect('select');
                application.View.showQuestions();
                registerEventListener();
            });

            sannong.ProjectApplication = application;
            return application;

        });
});
