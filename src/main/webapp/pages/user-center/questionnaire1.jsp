<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2/6/15
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
  <form id="questionnaire1-form" role="form" action="updateQuestionnaire" method="post">
    <div id="questionnaire">
      <li class="J_group_choice">
        1. 贵村面积多大？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[0]" value="112:a"><label>3000亩以下</label></span>
          <span class="radioCustom"><input type="radio" name="answers[0]" value="112:b"><label>3000~6000亩</label></span>
          <span class="radioCustom"><input type="radio" name="answers[0]" value="112:c"><label>6000~9000亩</label></span>
          <span class="radioCustom"><input type="radio" name="answers[0]" value="112:d"><label>9000亩以上</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        2. 贵村耕地面积多大？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[1]" value="113:a"><label>2000亩以下</label></span>
          <span class="radioCustom"><input type="radio" name="answers[1]" value="113:b"><label>2000~3000亩</label></span>
          <span class="radioCustom"><input type="radio" name="answers[1]" value="113:c"><label>3000~4000亩</label></span>
          <span class="radioCustom"><input type="radio" name="answers[1]" value="113:d"><label>4000亩以上</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        3. 贵村离县城距离多远？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[2]" value="114:a"><label>10公里以内</label></span>
          <span class="radioCustom"><input type="radio" name="answers[2]" value="114:b"><label>10-30公里</label></span>
          <span class="radioCustom"><input type="radio" name="answers[2]" value="114:c"><label>30-50公里</label></span>
          <span class="radioCustom"><input type="radio" name="answers[2]" value="114:d"><label>50公里以上</label></span>

        </div>
      </li>

      <li class="J_group_choice">
        4. 贵村主要道路是哪种？
        <div class="checkboxRow">
          <span class="checkboxCustom"><input type="checkbox" name="answers[3]" value="115:a"><label>水泥路</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[3]" value="115:b"><label>沥青路</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[3]" value="115:c"><label>砂石路</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[3]" value="115:d"><label>泥巴路</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        5. 和附近村的联络主要道路是哪种？
        <div class="checkboxRow">
          <span class="checkboxCustom"><input type="checkbox" name="answers[4]" value="116:a"><label>水泥路</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[4]" value="116:b"><label>沥青路</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[4]" value="116:c"><label>砂石路</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[4]" value="116:d"><label>泥巴路</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        6. 经过贵村的主干道有哪些？
        <div class="checkboxRow">
          <span class="checkboxCustom"><input type="checkbox" name="answers[5]" value="117:a"><label>国道</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[5]" value="117:b"><label>省道</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[5]" value="117:c"><label>县道</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[5]" value="117:d"><label>乡道</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[5]" value="117:e"><label>无</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        7. 贵村的主要民族是哪种？（用点击选择的方式）
        <div class="checkboxRow">
          <span class="checkboxCustom"><input type="checkbox" name="answers[6]" value="118:a"><label>汉族</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[6]" value="118:b"><label>回族</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[6]" value="118:c"><label>壮族</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[6]" value="118:d"><label>满族</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[6]" value="118:e"><label>其他</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        8. 贵村有多少人口？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[7]" value="119:a"><label>1000人以下</label></span>
          <span class="radioCustom"><input type="radio" name="answers[7]" value="119:b"><label>1000-2000人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[7]" value="119:c"><label>2000-3000人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[7]" value="119:d"><label>3000人以上</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        9. 贵村有多少儿童（15岁以下）？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[8]" value="120:a"><label>200 人以下</label></span>
          <span class="radioCustom"><input type="radio" name="answers[8]" value="120:b"><label>200-300人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[8]" value="120:c"><label>300-400人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[8]" value="120:d"><label>400人以上</label></span>
        </div>
      </li>
    </div>
    <input type="hidden" name="questionnaireNumber" id="questionnaire-number" value="1">
    <input type="hidden" name="applicationId" id="application-id" value="2">
  </form>
</body>
</html>
