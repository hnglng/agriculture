/**
 * Created by Bright Huang on 11/6/14.
 */

define(['jquery', 'sannong', 'handlebars'], function($, sannong, handlebars) {

    "use strict";

    if(!String.prototype.trim){
    	String.prototype.trim = function() {
    		return this.replace(/(^\s*)|(\s*$)/g, "");
    	}
    }
    
    var questionnaire = {};
    questionnaire.Model = {1:null, 2:null, 3:null, 4:null, 5:null};

    questionnaire.View = {
        resetQuestionnaireView: function(questionnaireNo){
            if ($("#q" + questionnaireNo).parent().hasClass("disabled")) {
                $("#questionnaire").empty();
                $("#buttonGroup").hide();
                $("#questionnaireStatus").hide();
                $("#submitStatus").hide();
                return false;
            }

            $("#buttonGroup").show();
            $("#submitStatus").show();
            $("#save").removeAttr("disabled");
            $("#questionnaireSubmit").removeAttr("disabled");

            $("#save-success").remove();
            $("#save-error").remove();
            $("#update-success").remove();
            $("#update-error").remove();

            if ($(".steps")){
                $(".no").each(function(){
                    $(this).parent().removeClass("active");

                    if ($(this).text() == questionnaireNo){
                        $(this).parent().addClass("active");
                    }
                })
            }
        },
        renderQuestions: function(questions){
            //fill out questionnaire
            var checkboxCompiler = handlebars.compile($("#question-template-checkbox").html()),
                radioCompiler = handlebars.compile($("#question-template-radio").html()),
                //questions = questionnaire.questions,
                question = null,
                html = null;

            $("#questionnaire").empty();

            for (var i = 0; i < questions.length; i++){
                handlebars.registerHelper("fromOne",function(){ return i + 1; });
                handlebars.registerHelper("fromZero",function(){ return i; });

                question = questions[i];
                if (question.singleSelectionOnly == 1){
                    html = radioCompiler(question);
                }else{
                    html = checkboxCompiler(question);
                }
                $("#questionnaire").append(html);
            }

            $("#questionnaire").find(".checkboxCustom").each(function(){
                var checkbox = $(this).text();
                if (checkbox.trim() == ""){
                    $(this).remove();
                }
            });

            $("#questionnaire").find(".radioCustom").each(function(){
                var radio = $(this).text();
                if (radio.trim() == ""){
                    $(this).remove();
                }
            });

            $('#questionnaire .radioCustom input').click(function () {
                $(this).parents(".radioRow").find(".radioCustom").removeClass("radioCustom-checked");
                $(this).parent(".radioCustom").addClass("radioCustom-checked");
            });

            $('#questionnaire .checkboxCustom').click(function () {
                $(this).toggleClass('checkboxCustom-checked');
                var $checkbox = $(this).find(':checkbox');
                $checkbox.attr('checked', !$checkbox.attr('checked'));
            });

        },
        disableSubsequentTab: function(nextQuestionnaireNo){
            //之后选项卡不可用
            for (var i = nextQuestionnaireNo; i < 6; i++){
                if ($("#q" + i).parent().hasClass("active")){
                    $("#q" + i).parent().removeClass("active").addClass("disabled");
                }else{
                    $("#q" + i).parent().addClass("disabled");
                }
                $("#q" + i).removeAttr("data-toggle").removeClass("meta-event-source");
                $("#q" + i).parent().attr("data-toggle","tooltip").attr("title","请顺序完成问卷题集");
            }
            $("[data-toggle='tooltip']").tooltip();
        },
        enableSaveButtons: function(){
            //当前选项卡中的button enabled
            $("#save").attr("disabled",false);
            $("#save").removeClass("gray-bt-small").addClass("white-bt");

            $("#questionnaireSubmit").attr("disabled",false);
            $("#questionnaireSubmit").removeClass("gray-bt-small").addClass("orange-bt-small");
        },
        disableSaveButtons: function(){
            //当前选项卡中的button disabled
            $("#save").attr("disabled","disabled");
            $("#save").removeClass("white-bt").addClass("gray-bt-small");

            $("#questionnaireSubmit").attr("disabled","disabled");
            $("#questionnaireSubmit").removeClass("orange-bt-small").addClass("gray-bt-small");
        },
        enableNextTab: function(currentQuestionnaireNumber){
            //下一个选项卡可用
            if ($("#q" + (currentQuestionnaireNumber + 1).toString()).parent().hasClass("disabled")){
                $("#q" + (currentQuestionnaireNumber + 1).toString()).parent().removeClass("disabled");
                $("#q" + (currentQuestionnaireNumber + 1).toString()).attr("data-toggle","tab").addClass("meta-event-source");
                $("#q" + (currentQuestionnaireNumber + 1).toString()).parent().removeAttr("data-toggle").removeAttr("title").removeAttr("data-original-title");
            }

        },
        fillAnswers: function(questionnaireNo, answerString, disableAnswerOptions){
            if (answerString != "" && answerString != null){
                var answersByQuestion = answerString.split(";");

                for (var i = 0;i < answersByQuestion.length;i++){
                    var $_radios = $(".J_group_choice").eq(i).find("input");

                    $_radios.each(function(){
                        if (disableAnswerOptions == true){
                            $(this).attr("disabled","disabled");
                        }

                        var answers = answersByQuestion[i].split(",");
                        for (var j = 0;j < answers.length;j++){
                            if($(this).val() === answers[j]){
                                if ($(this).parent(".radioCustom")){
                                    $(this).parent(".radioCustom").addClass("radioCustom-checked");
                                }
                                if ($(this).parent(".checkboxCustom")){
                                    $(this).parent(".checkboxCustom").toggleClass("checkboxCustom-checked");
                                    $(this).attr("checked", "checked");
                                }
                            }

                        }
                    });
                }
            }
        },
        renderApplicationComments: function(application){
            var applicationId = application.applicationId,
                comments = application.comments,
                lastQuestionnaireNumber = application.questionnaires.length,
                lastQuestionnaireCommitted = application.questionnaires[lastQuestionnaireNumber - 1].questionnaireCommitted;

            if ($("#applicationId")){
                $("#applicationId").val(applicationId);
            }

            if (comments != null && comments != ""){
                if($("#questionnaireStatus")){
                    $("#questionnaireStatus").text(comments);
                    $("#questionnaireStatus").show();
                }
            } else if (lastQuestionnaireNumber == 5 && lastQuestionnaireCommitted === true){
                $("#questionnaireStatus").text("您的申请正在审核中。请保存手机畅通，我们的工作人员会尽快联系您。");
                $("#questionnaireStatus").show();
            } else{
                $("#questionnaireStatus").text("请完成所有问卷调查，然后我们的工作人员会第一时间联系您。");
                $("#questionnaireStatus").show();
            }
        }
    };

    questionnaire.Controller = {
        showQuestionnaire: function(questionnaireNumber){
            if (questionnaire.Model[questionnaireNumber] !== null){
                questionnaire.View.renderQuestionnaireView(questionnaire.Model[questionnaireNumber]);
            }
            else{
                $.ajax({
                    type: "GET",
                    dataType: "json",
                    url: 'project-application/questionnaire/' + questionnaireNumber,
                    success: function(response) {
                        if (questionnaire.Model[questionnaireNumber] === null){
                            questionnaire.Model[questionnaireNumber] = response;
                        }
                        questionnaire.View.renderQuestionnaireView(response);
                    }
                });
            }
        },
        renderQuestionnaireAnswers: function(questionnaireNumber, application){
            var latestQuestionnaireNumber = application.questionnaires.length,
                latestQuestionnaire = application.questionnaires[latestQuestionnaireNumber - 1],
                latestQuestionnaireCommitted = latestQuestionnaire.questionnaireCommitted,
                currentQuestionnaire = application.questionnaires[questionnaireNumber - 1],
                currentQuestionnaireNumber = questionnaireNumber,
                disableAnswerOptions = true;

            if (currentQuestionnaireNumber == latestQuestionnaireNumber && latestQuestionnaireCommitted == false){         // 当前页面是填写的最大问卷, 暂存状态
                disableAnswerOptions = false;
                questionnaire.View.enableSaveButtons();
                questionnaire.View.disableSubsequentTab(currentQuestionnaireNumber + 1);
            }else if(currentQuestionnaireNumber < latestQuestionnaireNumber && latestQuestionnaireCommitted == false){     // 当前页面小于填写的最大问卷, 暂存状态
                disableAnswerOptions = true;
                questionnaire.View.disableSaveButtons();
                questionnaire.View.disableSubsequentTab(latestQuestionnaireNumber + 1);
            }else if (currentQuestionnaireNumber == latestQuestionnaireNumber && latestQuestionnaireCommitted == true){   // 当前页面是填写的最大问卷, 提交状态
                disableAnswerOptions = true;
                questionnaire.View.disableSaveButtons();
                questionnaire.View.enableNextTab(currentQuestionnaireNumber);
                //之后第二个开始不可用
                questionnaire.View.disableSubsequentTab(currentQuestionnaireNumber + 2);
            }else if(currentQuestionnaireNumber < latestQuestionnaireNumber && latestQuestionnaireCommitted == true){     // 当前页面小于填写的最大问卷, 提交状态
                disableAnswerOptions = true;
                questionnaire.View.disableSaveButtons();
                questionnaire.View.disableSubsequentTab(latestQuestionnaireNumber + 2);
            }else if (currentQuestionnaireNumber == (latestQuestionnaireNumber + 1) && latestQuestionnaireCommitted == true){
                disableAnswerOptions = false;
                questionnaire.View.enableSaveButtons();
                questionnaire.View.disableSubsequentTab(currentQuestionnaireNumber + 1);
            }else{
                return;
            }

            if (currentQuestionnaire === null || currentQuestionnaire === undefined) {
                questionnaire.Controller.showQuestionnaire(currentQuestionnaireNumber);
            }else{
                questionnaire.View.renderQuestionnaireView(currentQuestionnaire);
            }

            if (currentQuestionnaire !== null && currentQuestionnaire !== undefined){
                questionnaire.View.fillAnswers(questionnaireNumber, currentQuestionnaire.concatenatedAnswers, disableAnswerOptions);
            }

            questionnaire.View.renderApplicationComments(application);

        }

    };

    sannong.Questionnaire = questionnaire;
    return questionnaire;

});
