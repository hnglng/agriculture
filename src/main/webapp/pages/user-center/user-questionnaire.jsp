<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 11/22/14
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Benefitting Agriculture - End Users Project Application Form</title>
</head>
<body>
<div id="userTextShow" style="float:right; text-align:right">
  <div style="float:left;color:#F75000" id="userRealName"></div>
  <div style="float:left">的调查问卷答案</div>
</div>

<div>
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a id="q1" href="#tab-pane1" role="tab" data-toggle="tab"
                                              class="meta-event-source" meta-event-handler="userApplication:q1">问卷题集一</a>
    </li>
    <li role="presentation"><a id="q2" href="#tab-pane2" role="tab" data-toggle="tab" class="meta-event-source"
                               meta-event-handler="userApplication:q2">问卷题集二</a></li>
    <li role="presentation"><a id="q3" href="#tab-pane3" role="tab" data-toggle="tab" class="meta-event-source"
                               meta-event-handler="userApplication:q3">问卷题集三</a></li>
    <li role="presentation"><a id="q4" href="#tab-pane4" role="tab" data-toggle="tab" class="meta-event-source"
                               meta-event-handler="userApplication:q4">问卷题集四</a></li>
    <li role="presentation"><a id="q5" href="#tab-pane5" role="tab" data-toggle="tab" class="meta-event-source"
                               meta-event-handler="userApplication:q5">问卷题集五</a></li>
  </ul>
  <ul class="steps">
    <li class="active"><span class="no">1</span></li>
    <li><span class="no">2</span></li>
    <li><span class="no">3</span></li>
    <li><span class="no">4</span></li>
    <li><span class="no">5</span></li>
  </ul>
  <span id="submitStatus" class="brown-bg">项目状态：<span id="application-status"></span></span>
</div>
<!-- Tab panes -->
<div class="tab-content">
  <div role="tabpanel" class="tab-pane active" id="tab-pane1">
    <ul class="step-1-listing">
      <jsp:include page='../questionnaires/questionnaire1.jsp'/>
    </ul>
  </div>
  <div role="tabpanel" class="tab-pane" id="tab-pane2">
    <ul class="step-1-listing">
      <jsp:include page='../questionnaires/questionnaire2.jsp'/>
    </ul>
  </div>
  <div role="tabpanel" class="tab-pane" id="tab-pane3">
    <ul class="step-1-listing">
      <jsp:include page='../questionnaires/questionnaire3.jsp'/>
    </ul>
  </div>
  <div role="tabpanel" class="tab-pane" id="tab-pane4">
    <ul class="step-1-listing">
      <jsp:include page='../questionnaires/questionnaire4.jsp'/>
    </ul>
  </div>
  <div role="tabpanel" class="tab-pane" id="tab-pane5">
    <ul class="step-1-listing">
      <jsp:include page='../questionnaires/questionnaire5.jsp'/>
    </ul>
  </div>
  <div>
    <form id="application-comment" role="form" action="updateApplicationComment" method="POST">
      <div id="questionnaireComment">更改状态:
        <textarea class="form-control" name="comment.content" rows="3" id="commentContent"></textarea>
      </div>
    </form>
  </div>
  <span>如果需要修改问卷调查的答案，请致电免费电话400-XXXX-XXXX联系我们的工作人员。</span>
</div>

<form id="answerForm" class="projectAppForm" role="form" action="updateAnswersAndComment" method="post">
  <div id="questionnaire"></div>
  <input type="hidden" name="answerStatus" id="answerStatus">
  <input type="hidden" name="questionnaireNo" id="questionnaireNo">
</form>

<!--
<div id="buttonGroup" class="float-right step-1-bts">
  <button class="white-bt meta-event-source" meta-event-handler="userApplication:save" id="save"
          disabled="disabled">暂存
  </button>
  <button class="orange-bt-small meta-event-source" type="button" id="questionnaireSubmit"
          meta-event-handler="userApplication:submit">提交
  </button>
</div>
-->
<div>
  <button class="orange-bt-small meta-event-source" meta-event-handler="userManagement:update" id="update">提交</button>
  <a href="#" class="white-bt meta-event-source" meta-event-handler="userManagement:cancelQuestionnaireEdit" id="cancel">暂存</a>
</div>
<jsp:include page='../modal/quesitonnaire-update-modal.jsp'/>

<%--
<jsp:include page='../modal/user-questionnaire-modal.jsp'/>
--%>

<jsp:include page='../modal/user-questionnaire-modal.jsp'/>
</body>
</html>
