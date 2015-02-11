<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 11/22/14
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Benefitting Agriculture - User Management List Page</title>
</head>
<body>
<div id="user-management-view">

  <h3 id="user-management-title">
    <span>用户管理</span>
    <a href="javascript:void(0);"
       class="orange-bt-small float-right" data-toggle="modal"
       data-target="#exportModal">导出问卷调查结果</a>
  </h3>
  <jsp:include page='../modal/data-export-modal.jsp'/>

  <jsp:include page='user-search-wizard.jsp'/>
  <div id="userManagementTable">
    <ul class="umListGrid">
      <li class="head">
        <div class="col-small">姓名</div>
        <div class="col-small">注册日期</div>
        <div class="col-small">手机号码</div>
        <div class="col-large">工作单位</div>
        <div class="col-small">职位</div>
        <div class="col-medium">电子邮箱</div>
        <div class="col-small"></div>
      </li>
      <li>
        <div id="user-list"></div>
      </li>
    </ul>
    <ul id="pagination" class="customPagination">
      <li><a id="previous" href="javascript:void(0);" class="bt back meta-event-source"
             meta-event-handler="userManagement:previous"></a>
      </li>
      <li><label id="current-page-number">1</label>/<label id="total-page-number">1</label>
      </li>
      <li><a id="next" href="javascript:void(0);" class="bt next activeBt meta-event-source"
             meta-event-handler="userManagement:next"></a>
      </li>
    </ul>
    <input type="hidden" id="cellphone">
  </div>
</div>

<jsp:include page='questionnaire-edit.jsp'/>

<form class="userForm" role="form" id="profile-edit-form" action="user-center/user-profile" method="post">
  <div id="profile-edit-view" style="display:none"></div>
</form>
<jsp:include page='../template/user-profile-template.jsp'/>

<jsp:include page='../template/user-list-template.jsp'/>
<jsp:include page='../template/question-template.jsp'/>

</body>
</html>