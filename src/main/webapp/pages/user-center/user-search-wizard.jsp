<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2/5/15
  Time: 09:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>

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
          <option value="1">北京市</option>
          <option value="2">天津市</option>
          <option value="3">河北省</option>
          <option value="4">山西省</option>
          <option value="5">内蒙古自治区</option>
          <option value="6">辽宁省</option>
          <option value="7">吉林省</option>
          <option value="8">黑龙江省</option>
          <option value="9">上海市</option>
          <option value="10">江苏省</option>
          <option value="11">浙江省</option>
          <option value="12">安徽省</option>
          <option value="13">福建省</option>
          <option value="14">江西省</option>
          <option value="15">山东省</option>
          <option value="16">河南省</option>
          <option value="17">湖北省</option>
          <option value="18">湖南省</option>
          <option value="19">广东省</option>
          <option value="20">广西壮族自治区</option>
          <option value="21">海南省</option>
          <option value="22">重庆市</option>
          <option value="23">四川省</option>
          <option value="24">贵州省</option>
          <option value="25">云南省</option>
          <option value="26">西藏自治区</option>
          <option value="27">陕西省</option>
          <option value="28">甘肃省</option>
          <option value="29">青海省</option>
          <option value="30">宁夏回族自治区</option>
          <option value="31">新疆维吾尔自治区</option>
          <option value="32">台湾</option>
          <option value="33">香港特别行政区</option>
          <option value="34">澳门特别行政区</option>
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

</body>
</html>
