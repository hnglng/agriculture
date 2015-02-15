<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 11/20/14
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <!-- 模拟ie9及以下浏览器输入框内容提示-->
    <link href="css/ie.css" rel="stylesheet" type="text/css">
</head>
<body>
<!-- HEADER -->
<header>
  <div class="topbar">
    <div class="container">
      <div class="row">
        <span class="col-sm-12">
          <ul>
            <li><a href="/user-center">用户管理</a></li>
            <sec:authorize ifNotGranted="ROLE_USER,ROLE_ADMIN">
              <li><a href="#" data-toggle="modal" data-target="#login-modal">登录</a></li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
                <li><span id="welcome"></span></li>
                <li><a href="/user-questionnaire">我的项目</a></li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                <li><a href="/user-center">用户管理</a></li>
            </sec:authorize>
            <sec:authorize access="(hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')) and isAuthenticated()">
                <li><a href="j_spring_security_logout">退出</a></li>
            </sec:authorize>
          </ul>
        </span>
      </div>
    </div>
  </div>
  <div class="logoContainer">
    <div class="container">
      <div class="row">
        <span class="col-sm-4 logoCol">
          <h1><a href="landing"><img src="images/logo.png" alt="Benefitting Agriculture"/></a></h1>
        </span>
        <span class="col-sm-4">
        </span>
        <span class="col-sm-4 topNo">
          电话: <span>0800 556 2540</span>
        </span>
      </div>
    </div>
  </div>
</header>
<!-- /HEADER -->

<jsp:include page='../modal/login-modal.jsp'/>
<jsp:include page='../modal/forgot-password-modal.jsp'/>

</body>
<!-- 模拟ie9及以下浏览器输入框内容提示-->
<script src="js/app/modules/ie.js"></script>
</html>
