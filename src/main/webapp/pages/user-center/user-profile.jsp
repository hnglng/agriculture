
<%--
  Created by IntelliJ IDEA.
  User: Bright Huang
  Date: 11/22/14
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
</body>

<script id="user-profile-template" type="text/x-handlebars-template">
  <input name="userName" type="hidden" value="{{userName}}">

  <div class="row">
    <aside class="userFormCol-1">姓名</aside>
    <aside class="userFormCol-right">{{realName}}</aside>
    <input name="realName" type="hidden" value="{{realName}}">
  </div>

  <div class="row">
    <aside class="userFormCol-1">职位</aside>
    <aside class="userFormCol-right">
      <input type="text" class="width-172" id="jobTitle" name="jobTitle"
             placeholder="职务" value="{{jobTitle}}">
    </aside>
  </div>

  <div class="row">
    <aside class="userFormCol-1">工作单位</aside>
    <aside class="userFormCol-right">
      <input type="text" class="width-281" id="jobCompany" name="company"
             placeholder="工作单位" value="{{company}}">
    </aside>
  </div>

  <div class="row">
    <aside class="userFormCol-1">单位地址</aside>
    <aside class="userFormCol-right">
      <div class="width-162" id="provinceSelectDiv">
        <select id="provinceSelect" name="companyProvince">
          <jsp:include page='../template/province-select-template.jsp'/>
        </select>
      </div>
      <div class="width-162" id="citySelectDiv">
        <select id="citySelect" name="companyCity">
        </select>
      </div>
      <div class="width-162" id="districtSelectDiv">
        <select id="districtSelect" name="companyDistrict">
        </select>
      </div>
    </aside>
  </div>

  <div class="row">
    <aside class="userFormCol-1"></aside>
    <aside class="userFormCol-right">
      <input type="text" class="width-281" id="jobAddress" name="companyAddress" placeholder="单位地址"
             value="{{companyAddress}}">
    </aside>
  </div>

  <div class="row">
    <aside class="userFormCol-1">工作电话</aside>
    <aside class="userFormCol-right">
      <input type="text" class="width-281" id="deskPhone" name="deskPhone"
             placeholder="工作电话" value="{{deskPhone}}">
    </aside>
  </div>

  <div class="row">
    <aside class="userFormCol-1">电子邮件</aside>
    <aside class="userFormCol-right">
      <input type="text" class="width-281" id="mailbox" name="mailbox"
             placeholder="电子邮箱" value="{{mailbox}}">
    </aside>
  </div>

  <div class="row">
    <aside class="userFormCol-1">手机号码</aside>
    <aside class="userFormCol-right">
      <span class="font-size-20">{{cellphone}}<input name="cellphone" type="hidden" value="{{cellphone}}"></span>
      <sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
        <a href="#" class="white-bt" data-toggle="collapse" data-target="#updateCellphone" aria-expanded="false"
           aria-controls="#updateCellphone">更新手机号码</a>
      </sec:authorize>
    </aside>
  </div>
  <sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
    <div id="updateCellphone" class="collapse">
      <div class="row">
        <aside class="userFormCol-1">新手机号码</aside>
        <aside class="userFormCol-right"><input type="text" class="width-281" id="newCellphone" name="newCellphone"
                                                placeholder="新手机号码"></aside>
        <a href="javascript:void(0)" class="white-bt meta-event-source" role="button" id="validationCodeBtn"
           name="validationCodeBtn" meta-event-handler="userProfile:handleValidationCodeClicked">获取验证码</a>
      </div>
      <div class="row">
        <aside class="userFormCol-1">验证码</aside>
        <aside class="userFormCol-right">
          <div class="width-87">
            <input type="text" id="validationCode" name="validationCode" placeholder="验证码" autocomplete="off"/>
          </div>
        </aside>
      </div>
    </div>
  </sec:authorize>

  <div class="row">
        <span>
          <input type="button" id="userProfileSubmit" value="更新" class="meta-event-source"
                 meta-event-handler="userProfile:handleFormSubmit"/>
        </span>
  </div>
</script>
</html>