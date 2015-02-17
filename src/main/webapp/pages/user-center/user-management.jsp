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

<!-- user management view -->
<div id="user-management-view">
  <div class="row">
    <h3 id="user-management-title"><span>用户管理</span>
    <jsp:include page='../modal/data-export-modal.jsp'/>
    </h3>
  </div>

  <!-- search wizard -->
  <div class="searchRow" id="user-search-wizard">
    <div class="left">
      <label>查询条件</label>
      <div class="width-97">
        <select id="searchKey" name="searchKey">
          <option value="姓名">姓名</option>
          <option value="手机号">手机号</option>
          <option value="工作单位">工作单位</option>
          <option value="职位">职位</option>
          <option value="电子邮箱">电子邮箱</option>
          <option value="单位地址">单位地址</option>
        </select>
      </div>
      <div class="width-152">
        <input id="searchValue" name="searchValue" type="text">
      </div>
    </div>
    <div class="right">
      <label>地址</label>

      <div class="width-152" id="provinceQuerySelectDiv">
        <select id="provinceQuerySelect" name="provinceQuerySelect">
          <option value="">省/直辖市</option>
          <jsp:include page='../template/province-select-template.jsp'/>
        </select>
      </div>
      <div class="width-152" id="cityQuerySelectDiv">
        <select id="cityQuerySelect" name="cityQuerySelect">
          <option value="">市</option>
        </select>
      </div>
      <div class="width-152" id="districtQuerySelectDiv">
        <select id="districtQuerySelect" name="districtQuerySelect">
          <option value="">县/市辖区</option>
        </select>
      </div>
      <a id="retrieve" href="javascript:void(0);" class="glyphicon glyphicon-search meta-event-source"
         meta-event-handler="userManagement:search"></a>
    </div>
  </div>
  <!-- /search wizard -->

  <!-- user table -->
  <div id="user-table">
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
      <li><a id="previous" href="javascript:void(0);" class="bt back meta-event-source" meta-event-handler="userManagement:previous"></a></li>
      <li><label id="current-page-number">1</label>/<label id="total-page-number">1</label></li>
      <li><a id="next" href="javascript:void(0);" class="bt next activeBt meta-event-source" meta-event-handler="userManagement:next"></a></li>
    </ul>
  </div>
  <!-- /user table -->
</div>
<!-- /user management view -->

<!-- user table js template -->
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
<!-- /user table js template -->
</body>
</html>