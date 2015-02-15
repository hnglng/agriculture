<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2/4/15
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
<!-- Login Modal -->
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="login-modal-label" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button id="loginModalCloseBtn" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="login-modal-label">登录</h4>
      </div>
      <div class="modal-body">
        <form id="login-form" role="form" action="/login" method="POST">
          <span class="errorMsg" id="login-error-container"></span>
          <input type="text" id="login-username" name='username' placeholder="手机号码" class="model-input" />
          <input type="password" id="login-password" name='password' placeholder="密码" class="model-input" />
          <span class="checkboxCustom"><input type="checkbox" name='_spring_security_remember_me' id="login-remember-me"/>自动登录</span>
          <a href="#" class="leftBorderLink" id="forgot-password-link" data-toggle="modal" data-target="#forgot-password-modal">忘记密码</a>
          <input id="login-form-submit" type="button" value="登录" class="orange-bt"/>
        </form>
      </div>
    </div>
  </div>
</div>
<!-- /Login Modal -->
</body>
</html>
