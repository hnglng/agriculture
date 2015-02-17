<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2/6/15
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>

<form class="userForm" role="form" id="profile-edit-form" action="user-center/user-profile" method="post">
  <div id="profile-edit-view" style="display:none">
    <jsp:include page='../template/user-profile-template.jsp'/>
  </div>
</form>

</body>
</html>
