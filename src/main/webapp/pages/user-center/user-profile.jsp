
<%--
  Created by IntelliJ IDEA.
  User: Bright Huang
  Date: 11/22/14
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Benefitting Agriculture - End User Profile</title>
</head>
<body>
<h3>个人信息</h3>
<form class="userForm" role="form" id="user-profile-form" action="user-center/user-profile" method="post">
  <div id="user-profile-view"></div>
</form>

<jsp:include page='../template/user-profile-template.jsp'/>
</body>
</html>