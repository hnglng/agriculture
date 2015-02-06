<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2/5/15
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
<div id="questionnaire-edit-view" style="display:none">
  <div id="userTextShow" style="display:none; float:right; text-align:right">
    <div style="float:left;color:#F75000" id="userRealName"></div>
    <div style="float:left">的调查问卷答案</div>
  </div>

  <ul class="nav nav-tabs" role="tablist" id="questionnaireTab">
    <li role="presentation" class="active"><a id="q1" href="javascript:void(0)" role="tab" data-toggle="tab"
                                              class="meta-event-source" meta-event-handler="userList:q1">问卷题集一</a>
    </li>
    <li role="presentation"><a id="q2" href="javascript:void(0)" role="tab" data-toggle="tab" class="meta-event-source"
                               meta-event-handler="userList:q2">问卷题集二</a></li>
    <li role="presentation"><a id="q3" href="javascript:void(0)" role="tab" data-toggle="tab" class="meta-event-source"
                               meta-event-handler="userList:q3">问卷题集三</a></li>
    <li role="presentation"><a id="q4" href="javascript:void(0)" role="tab" data-toggle="tab" class="meta-event-source"
                               meta-event-handler="userList:q4">问卷题集四</a></li>
    <li role="presentation"><a id="q5" href="javascript:void(0)" role="tab" data-toggle="tab" class="meta-event-source"
                               meta-event-handler="userList:q5">问卷题集五</a></li>
  </ul>
  <div class="tab-content">
    <ul class="steps">
      <li class="active"><span class="no">1</span> <span class="stepHeading">问卷题集一</span>
      </li>
      <li><span class="no">2</span> <span class="stepHeading">问卷题集二</span>
      </li>
      <li><span class="no">3</span> <span class="stepHeading">问卷题集三</span>
      </li>
      <li><span class="no">4</span> <span class="stepHeading">问卷题集四</span>
      </li>
      <li><span class="no">5</span> <span class="stepHeading">问卷题集五</span>
      </li>
    </ul>
    <ul class="step-1-listing">
      <div id="submitStatus" class="brown-bg">项目状态：<span id="questionnaireStatus"></span></div>

      <form id="answerForm" role="form" action="updateAnswersAndComment" method="post">
        <div id="questionnaire">
          <!-- Fill in template here -->
        </div>
        <div id="questionnaireComment">更改状态:
          <textarea class="form-control" name="comment.content" rows="3" id="commentContent" autofocus></textarea>
        </div>
        <input type="hidden" name="questionnaireNo" id="questionnaireNo">
        <input type="hidden" name="applicant.userName" id="userName">
        <input type="hidden" name="application.applicationId" id="applicationId">
      </form>
    </ul>
  </div>

  <br>
  <a href="#" class="white-bt meta-event-source" meta-event-handler="userList:cancel" id="cancel">取消</a>
  <button class="orange-bt-small meta-event-source" meta-event-handler="userList:update" id="update">更新</button>

  <jsp:include page='../modal/quesitonnaire-update-modal.jsp'/>
</div>

</body>
</html>
