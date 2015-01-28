<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 1/28/15
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <title></title>
</head>
<body>
  <div class="row">
    <aside class="userFormCol-1">姓名</aside>
    <aside class="userFormCol-right"><input type="text" class="width-172"
                                            id="projectAppForm_userRealName"
                                            name="user.realName"></aside>
  </div>
  <div class="row">
    <aside class="userFormCol-1">职位</aside>
    <aside class="userFormCol-right"><input type="text" class="width-172"
                                            id="projectAppForm_jobTitle" name="user.jobTitle">
    </aside>
  </div>
  <div class="row">
    <aside class="userFormCol-1">工作单位</aside>
    <aside class="userFormCol-right"><input type="text" class="width-281"
                                            id="projectAppForm_company" name="user.companyName">
    </aside>
  </div>
  <div class="row">
    <aside class="userFormCol-1">单位地址</aside>
    <aside class="userFormCol-right">
      <div class="width-162" id="provinceSelectDiv">
        <select id="companyProvinceSelect" name="user.companyProvince">
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
      <div class="width-162" id="citySelectDiv">
        <select id="companyCitySelect" name="user.companyCity">
          <option value="1">市辖区</option>
          <option value="2">县</option>
        </select>
      </div>
      <div class="width-162" id="districtSelectDiv">
        <select id="companyDistrictSelect" name="user.companyDistrict">
          <option value='1'>东城区</option>
          <option value='2'>西城区</option>
          <option value='3'>崇文区</option>
          <option value='4'>宣武区</option>
          <option value='5'>朝阳区</option>
          <option value='6'>丰台区</option>
          <option value='7'>石景山区</option>
          <option value='8'>海淀区</option>
          <option value='9'>门头沟区</option>
          <option value='10'>房山区</option>
          <option value='11'>通州区</option>
          <option value='12'>顺义区</option>
          <option value='13'>昌平区</option>
          <option value='14'>大兴区</option>
          <option value='15'>怀柔区</option>
          <option value='16'>平谷区</option>
        </select>
      </div>
      <input type="text" class="width-281" id="projectAppForm_jobAddress"
             name="user.companyAddress">
    </aside>
  </div>
  <div class="row">
    <aside class="userFormCol-1">工作电话</aside>
    <aside class="userFormCol-right"><input type="text" class="width-281"
                                            id="projectAppForm_deskPhone" name="user.deskPhone">
    </aside>
  </div>
  <div class="row">
    <aside class="userFormCol-1">电子邮件</aside>
    <aside class="userFormCol-right"><input type="text" class="width-281"
                                            id="projectAppForm_mailbox" name="user.mailbox">
    </aside>
  </div>
  <div class="row">
    <aside class="userFormCol-1">手机号码</aside>
    <aside class="userFormCol-right">
      <input type="text" class="width-281" id="projectAppForm_cellphone"
             name="user.mobilePhone">
      <a href="javascript:void(0)" class="white-bt" role="button"
         id="projectAppForm_validationBtn" name="validationBtn">获取验证码</a>
    </aside>
  </div>
  <div class="row">
    <aside class="userFormCol-1">验证码</aside>
    <aside class="userFormCol-right">
      <input type="text" class="width-87" id="projectAppForm_validationCode"
             name="sms.smsValidationCode"/>
    </aside>
  </div>
  <div class="row">
    <input type="button" class="disabled" id="projectAppForm_submit" value="提交"/>
  </div>
</body>
</html>
