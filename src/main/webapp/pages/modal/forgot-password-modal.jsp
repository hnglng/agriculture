<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2/4/15
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
<!-- Forgot Password Modal -->
<div class="modal fade" id="forgot-password-modal" tabindex="-1" role="dialog" aria-labelledby="forgot-password-modal-label" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="forgot-password-modal-label">忘记密码</h4>
      </div>
      <div class="modal-body">
        <form id="forgot-password-form" role="form" action="j_spring_security_check" method="POST">
          <span class="errorMsg" id="forgot-password-error-container"></span></span>
          <input type="text" name="realName" id="forgot-password-real-name" placeholder="姓名" class="model-input" />
          <input type="text" name='j_username' id="forgot-password-cellphone" placeholder="手机号码" class="model-input-75" />
          <a href="#" id="forgot-password-send-password" class="white-bt">获取新密码</a>
          <input type="password" name="j_password" id="forgot-password-password" placeholder="新密码" class="model-input margin-top-15" />
          <span class="checkboxCustom"><input type="checkbox" name='_spring_security_remember_me' id="forgot-password-remember-me"/>自动登录</span>
          <input id="forgot-password-submit" type="button" value="登录" class="orange-bt"/>
        </form>
      </div>
    </div>
  </div>
</div>
<!-- /Forgot Password Modal -->
</body>
</html>
