/**
 * Created by Bright Huang on 10/26/14.
 */

require(['../main'], function () {
    require(['jquery', 'bootstrap', 'handlebars', 'sannong', 'login', 'validate', 'ajaxHandler',
            'questionnaire', 'jqueryForm', 'formValidator', 'selector', 'additionalMethods', 'custom'],
        function ($, bootstrap, handlebars, sannong, login, validate, ajaxHandler,
                  questionnaire, jqueryForm, formValidator, selector, additionalMethods, custom) {

            "use strict";

            var applicationForm = {};

            applicationForm.Model = {};

            applicationForm.View = {
                cellphone: $("#mobile-phone"),
                cellphoneError: $("#cellphone-error"),
                validationCode: $("#alidation-code"),
                applicationFormFormSubmit: $("#submit"),
                showUniqueCellphoneError: function (message) {
                    var errorLabel = '<label id="mobile-phone-error" class="error" for="mobile-phone" style="display: inline-block;">' + message + '</label>';
                    applicationForm.View.cellphoneError.remove();
                    applicationForm.View.cellphone.removeClass("error");
                    applicationForm.View.cellphone.after(errorLabel);
                    applicationForm.View.cellphone.addClass("error");
                },
                showValidationCodeError: function (message) {
                    var errorLabel = '<label id="validation-code-error" class="error" for="validation-code">' + message + '</label>';
                    applicationForm.View.validationCode.removeClass("error");
                    applicationForm.View.validationCode.removeAttr("aria-invalid");

                    if ($("#validation-code-error") !== undefined) {
                        $("#validation-code-error").text(message);
                    } else {
                        applicationForm.View.validationCode.after(errorLabel);
                    }
                    applicationForm.View.validationCode.addClass("error");
                    applicationForm.View.validationCode.attr("aria-invalid", "true");
                    applicationForm.View.validationCode.attr("style", "display: inline-block");

                },
                showValidationCodeMessage: function (message) {
                    applicationForm.View.showValidationCodeError(message);
                },
                enableSubmitButton: function () {
                    applicationForm.View.applicationFormFormSubmit.removeClass("disabled");
                },
                disableSubmitButton: function () {
                    applicationForm.View.applicationFormFormSubmit.addClass("disabled");
                },
                showQuestions: function () {
                    $.ajax({
                        type: "GET",
                        dataType: "json",
                        url: '/api/questions/questionnaireNumbers/1',
                        success: function (data) {
                            questionnaire.View.renderQuestions(data._embedded.questionList);
                        }
                    });
                }
            };

            applicationForm.Controller = {
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
                                    applicationForm.View.enableSubmitButton();
                                    $("#validation-button").addClass("disabled");
                                    $("#validation-code").removeAttr("disabled");
                                    applicationForm.View.showValidationCodeMessage(response.statusMessage);
                                } else {
                                    $("#alidation-button").removeClass("disabled");
                                    $("#alidation-code").attr({disabled: "disabled"});
                                    applicationForm.View.disableSubmitButton();
                                    if (response.statusCode == 2012) {
                                        applicationForm.View.showUniqueCellphoneError(response.statusMessage);
                                    } else {
                                        applicationForm.View.showValidationCodeError(response.statusMessage);
                                    }
                                }
                            },
                            fail: function (response) {
                                $("#alidation-button").removeClass("disabled");
                                $("#alidation-code").removeAttr("disabled");
                                applicationForm.View.disableSubmitButton();
                                applicationForm.View.showValidationCodeError("验证码发送失败");
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
                        contentType: 'applicationForm/json',
                        url: "/application-form",
                        dataType: "json",
                        data: $("#projectAppForm").serialize(),
                        success: function(data, textStatus, jqXHR){
                            window.location.href = "/completion";
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
                    applicationForm.Controller.handleValidationCodeClick();
                });

                $("#submit-button").click(function () {
                    applicationForm.Controller.handleFormSubmit();
                });

                $("#confirmed-submit").click(function () {
                    applicationForm.Controller.handleConfirmedSubmit();
                });
            }

            $(function () {
                selector.initSelect('select');
                applicationForm.View.showQuestions();
                registerEventListener();
            });

            sannong.ApplicationForm = applicationForm;
            return applicationForm;

        });
});
