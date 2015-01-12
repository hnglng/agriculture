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
        renderQuestionnaireView: function(questionnaire){
            //fill out questionnaire
            var checkboxCompiler = handlebars.compile($("#question-template-checkbox").html()),
                radioCompiler = handlebars.compile($("#question-template-radio").html()),
                questions = questionnaire.questions,
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
        enableNextTab: function(currentQuestionnaireNo){
            //下一个选项卡可用
            if ($("#q" + (currentQuestionnaireNo + 1).toString()).parent().hasClass("disabled")){
                $("#q" + (currentQuestionnaireNo + 1).toString()).parent().removeClass("disabled");
                $("#q" + (currentQuestionnaireNo + 1).toString()).attr("data-toggle","tab").addClass("meta-event-source");
                $("#q" + (currentQuestionnaireNo + 1).toString()).parent().removeAttr("data-toggle").removeAttr("title").removeAttr("data-original-title");
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
        renderQuestionnaireAnswers: function(questionnaireNo, data){
            // answerStatus: 10, 11, 20, 21, 30, 31, 40, 41, 50, 51
            // the first digit represent questionnaire page number, it indicates how many questionnaire were done.
            // the second digit represent questionnaire completion status, 0 means temporarily saved, 1 means questionnaire was finished.
            var answerStatus = data.answerStatus,
                answerStatusStr =  answerStatus.toString(),
                latestQuestionnaireNo = parseInt(answerStatusStr.substring(0,1), 10),
                stageOrCommit = answerStatusStr.substring(1,2),//stage: 问卷暂存, commit: 问卷提交
                currentQuestionnaireNo = questionnaireNo,
                disableAnswerOptions = true;

            if (currentQuestionnaireNo == latestQuestionnaireNo && stageOrCommit == 0){         // 当前页面是填写的最大问卷, 暂存状态
                disableAnswerOptions = false;
                questionnaire.View.enableSaveButtons();
                questionnaire.View.disableSubsequentTab(currentQuestionnaireNo + 1);
            }else if(currentQuestionnaireNo < latestQuestionnaireNo && stageOrCommit == 0){     // 当前页面小于填写的最大问卷, 暂存状态
                disableAnswerOptions = true;
                questionnaire.View.disableSaveButtons();
                questionnaire.View.disableSubsequentTab(latestQuestionnaireNo + 1);
            }else if (currentQuestionnaireNo == latestQuestionnaireNo && stageOrCommit == 1){   // 当前页面是填写的最大问卷, 提交状态
                disableAnswerOptions = true;
                questionnaire.View.disableSaveButtons();
                questionnaire.View.enableNextTab(currentQuestionnaireNo);
                //之后第二个开始不可用
                questionnaire.View.disableSubsequentTab(currentQuestionnaireNo + 2);
            }else if(currentQuestionnaireNo < latestQuestionnaireNo && stageOrCommit == 1){     // 当前页面小于填写的最大问卷, 提交状态
                disableAnswerOptions = true;
                questionnaire.View.disableSaveButtons();
                questionnaire.View.disableSubsequentTab(latestQuestionnaireNo + 2);
            }else if (currentQuestionnaireNo == (latestQuestionnaireNo + 1) && stageOrCommit == 1){
                disableAnswerOptions = false;
                questionnaire.View.enableSaveButtons();
                questionnaire.View.disableSubsequentTab(currentQuestionnaireNo + 1);
            }else{
                return;
            }

            questionnaire.View.renderQuestionnaireView(data);

            var answerString = questionnaire.getAnswers(questionnaireNo, data);
            questionnaire.View.fillAnswers(questionnaireNo, answerString, disableAnswerOptions);

            questionnaire.View.renderQuestionnaireComments(data, answerStatus);

        }
    };

    questionnaire.Controller = {
    };

    sannong.Questionnaire = questionnaire;
    return questionnaire;

});
