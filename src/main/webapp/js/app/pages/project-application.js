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
                cellphone: $("#projectAppForm_cellphone"),
                cellphoneError: $("#cellphone-error"),
                validationCode: $("#projectAppForm_validationCode"),
                applicationFormSubmit: $("#projectAppForm_submit"),
                showUniqueCellphoneError: function (message) {
                    var errorLabel = '<label id="projectAppForm_cellphone-error" class="error" for="cellphone" style="display: inline-block;">' + message + '</label>';
                    application.View.cellphoneError.remove();
                    application.View.cellphone.removeClass("error");
                    application.View.cellphone.after(errorLabel);
                    application.View.cellphone.addClass("error");
                },
                showValidationCodeError: function (message) {
                    var errorLabel = '<label id="projectAppForm_validationCode-error" class="error" for="projectAppForm_validationCode">' + message + '</label>';
                    application.View.validationCode.removeClass("error");
                    application.View.validationCode.removeAttr("aria-invalid");

                    if ($("#projectAppForm_validationCode-error") !== undefined) {
                        $("#projectAppForm_validationCode-error").text(message);
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
                }

            };

            application.Controller = {
                showQuestions: function () {
                    $.ajax({
                        type: "GET",
                        dataType: "json",
                        url: '/questions/questionnaireNumbers/1',
                        success: function (data) {
                            questionnaire.View.renderQuestions(data._embedded.questionEntityList);
                        }
                    });
                },
                handleValidationCodeClick: function () {
                    //application.View.enableSubmitButton();

                    if ($("#projectAppForm_validationBtn").hasClass("disabled")) {
                        return;
                    }

                    if (formValidator.getValidator("#projectAppForm").form() == true) {
                        ajaxHandler.sendRequest({
                            url: 'project-application/captcha',
                            type: 'POST',
                            data: {mobilePhone: $("#projectAppForm_cellphone").val()},
                            success: function (response) {
                                if (response.statusCode < 2000) {
                                    additionalMethods.updateTimeLabel("#projectAppForm_validationBtn", "验证码");
                                    application.View.enableSubmitButton();
                                    $("#projectAppForm_validationBtn").addClass("disabled");
                                    $("#projectAppForm_validationCode").removeAttr("disabled");
                                    application.View.showValidationCodeMessage(response.statusMessage);
                                } else {
                                    $("#projectAppForm_validationBtn").removeClass("disabled");
                                    $("#projectAppForm_validationCode").attr({disabled: "disabled"});
                                    application.View.disableSubmitButton();
                                    if (response.statusCode == 2012) {
                                        application.View.showUniqueCellphoneError(response.statusMessage);
                                    } else {
                                        application.View.showValidationCodeError(response.statusMessage);
                                    }
                                }
                            },
                            fail: function (response) {
                                $("#projectAppForm_validationBtn").removeClass("disabled");
                                $("#projectAppForm_validationCode").removeAttr("disabled");
                                application.View.disableSubmitButton();
                                application.View.showValidationCodeError("验证码发送失败");
                            }

                        });
                    }
                },
                handleFormSubmit: function () {
                    //$("#projectAppModelTrigger").click();
                    if ($("#projectAppForm_submit").hasClass("disabled") == true) {
                        return;
                    }

                    if (formValidator.getValidator("#projectAppForm").form() == true) {
                        $("#projectAppModelTrigger").click();
                    }
                },
                handleConfirmedSubmit: function () {
                    $("#projectAppForm").ajaxSubmit(function (response) {
                        if (response.statusCode < 2000) {
                            window.location.href = response.uri;
                        } else {
                            $("#closeModal").click();
                            application.View.showValidationCodeError(response.statusMessage);
                        }
                    });
                }
            };

            function registerEventListener() {
                $("#projectAppForm_validationBtn").click(function () {
                    application.Controller.handleValidationCodeClick();
                });

                $("#projectAppForm_submit").click(function () {
                    application.Controller.handleFormSubmit();
                });

                $("#confirmedSubmit").click(function () {
                    application.Controller.handleConfirmedSubmit();
                });
            }

            $(function () {
                selector.initSelect('select');
                application.Controller.showQuestions();
                registerEventListener();
            });

            sannong.ProjectApplication = application;
            return application;

        });
});
