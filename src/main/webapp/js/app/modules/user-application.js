/**
 * Created by Bright Huang on 11/6/14.
 */
define(['jquery', 'bootstrap', 'sannong', 'validate',  'formValidator', 'additionalMethods', 'questionnaire', 'jqueryForm', 'eventHandler'],
        function($, bootstrap, sannong, validate, formValidator, additionalMethods, questionnaire, jqueryForm, eventHandler) {

            "use strict";

            var userApplication = {};

            function submitForm(saveOrSubmit){
                if (formValidator.getValidator("#answerForm").form() == true){
                    var questionnaireNo = $("#questionnaireNo").val();
                    var answerStatus = questionnaireNo + saveOrSubmit;
                    $("#answerStatus").val(answerStatus);

                    if (saveOrSubmit == 1){
                    	$("#myModalTrigger").click();
                    }else{
                        $("#answerForm").ajaxSubmit(function(message) {
                            $("#save-success").remove();
                            $("#save-error").remove();

                            if (message.result == true){
                                $("#return").click();
                                $("#questionnaireSubmit").after('<label id="save-success" class="error" for="jobTitle">已暂存成功</label>');
                            }else{
                                $("#questionnaireSubmit").after('<label id="save-error" class="error" for="jobTitle">暂存失败</label>');                            }
                        });
                    }
                }
            }

            userApplication.Controller = {
                showMyApplication: function(questionnaireNumber) {
                    questionnaire.View.resetQuestionnaireView(questionnaireNumber);

                    $("#questionnaireNo").val(questionnaireNumber);

                    $.ajax({
                        type : "GET",
                        dataType : "json",
                        url : 'user-personal-center/myapplication',
                        success : function(application) {
                            questionnaire.Controller.renderQuestionnaireAnswers(questionnaireNumber, application)
                        },
                        fail: function(response){

                        }
                    });

                },
                stage: function(){
                    submitForm(0);
                },
                submitForm: function(){
                    submitForm(1);

                },
                confirmSubmit: function(){
                    $("#answerForm").ajaxSubmit(function(message) {
                        if (message.result == true){
                            $("#return").click();

                            //更新成功重新加载questionnaire and answer
                            var questionnaireNo = $("#questionnaireNo").val();
                            questionnaire.Controller.showQuestionnaireAnswers(questionnaireNo);
                            $("#questionnaireSubmit").after('<label id="update-success" class="success" for="jobTitle">已提交成功</label>');
                        }else{
                            if ($("#save-success") != null){
                                $("#save-success").remove();
                            }
                            if ($("#update-error") != null){
                                $("#update-error").remove();
                            }
                            $("#questionnaireSubmit").after('<label id="update-error" class="error" for="jobTitle">提交失败</label>');
                        }
                    });
                    return false;
                },
                q1: function(){
                    userApplication.Controller.showMyApplication(1);
                },
                q2: function(){
                    userApplication.Controller.showMyApplication(2);
                },
                q3: function(){
                    userApplication.Controller.showMyApplication(3);
                },
                q4: function(){
                    userApplication.Controller.showMyApplication(4);
                },
                q5: function(){
                    userApplication.Controller.showMyApplication(5);
                }
            };

            function subscribeEvent(){
                eventHandler.subscribe("userApplication:save", userApplication.Controller.stage);
                eventHandler.subscribe("userApplication:submit", userApplication.Controller.submitForm);
                eventHandler.subscribe("userApplication:confirmSubmit", userApplication.Controller.confirmSubmit);

                eventHandler.subscribe("userApplication:q1", userApplication.Controller.q1);
                eventHandler.subscribe("userApplication:q2", userApplication.Controller.q2);
                eventHandler.subscribe("userApplication:q3", userApplication.Controller.q3);
                eventHandler.subscribe("userApplication:q4", userApplication.Controller.q4);
                eventHandler.subscribe("userApplication:q5", userApplication.Controller.q5);
            }


            /*************************
             * DOM ready function
             ************************/
            $(function() {
                subscribeEvent();
            })


            sannong.UserApplicationForm = userApplication;
            return userApplication;

});
