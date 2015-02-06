<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2/5/15
  Time: 09:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
<script id="table-template" type="text/x-handlebars-template">
  {{#each this}}
  <li>
    <div class="col-small">{{realName}}</div>
    <div class="col-small">{{creationTime}}</div>
    <div class="col-small" id="cell{{addOne @index}}">{{mobilePhone}}</div>
    <div class="col-large">{{companyName}}</div>
    <div class="col-small">{{jobTitle}}</div>
    <div class="col-medium">{{mailbox}}</div>
    <div class="col-small">
      <span class="bts">
        <a href="javascript:void(0);" class="edit"
           onclick="Sannong.UserManagement.Controller.editUserProfile('{{userId}}')">Edit</a>
        <a href="javascript:void(0);" class="help"
           onclick="Sannong.UserManagement.Controller.editQuestionnaire(1,'{{userId}}')">Help</a>
      </span>
    </div>
  </li>
  {{/each}}
</script>
</body>
</html>
