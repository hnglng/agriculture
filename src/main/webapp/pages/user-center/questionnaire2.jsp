<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2/6/15
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>

  <form id="questionnaire2-form" role="form" action="updateQuestionnaire" method="post">
    <div id="questionnaire">
      <li class="J_group_choice">
        1. 贵村有多少留守儿童（15岁以下）？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[0]" value="121:a"><label>200 人以下</label></span>
          <span class="radioCustom"><input type="radio" name="answers[0]" value="121:b"><label>200-300人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[0]" value="121:c"><label>300-400人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[0]" value="121:d"><label>400人以上</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        2. 贵村有多少老人（60岁以上）？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[1]" value="122:a"><label>300人以下</label></span>
          <span class="radioCustom"><input type="radio" name="answers[1]" value="122:b"><label>300-400人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[1]" value="122:c"><label>400-500人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[1]" value="122:d"><label>500人以上</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        3. 贵村有哪些学校？
        <div class="checkboxRow">
          <span class="checkboxCustom"><input type="checkbox" name="answers[2]" value="123:a"><label>幼儿园</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[2]" value="123:b"><label>小学</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[2]" value="123:c"><label>初中</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[2]" value="123:d"><label>高中</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[2]" value="123:e"><label>其他</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        4. 贵村常年在外工作的人口有多少（不在本市工作的人）？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[3]" value="124:a"><label>300人以下</label></span>
          <span class="radioCustom"><input type="radio" name="answers[3]" value="124:b"><label>300-600人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[3]" value="124:c"><label>500-900人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[3]" value="124:d"><label>900人以上</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        5. 贵村或所在乡镇是否有集市？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[4]" value="125:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[4]" value="125:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        6. 贵村到集市的距离？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[5]" value="126:a"><label>10公里以内</label></span>
          <span class="radioCustom"><input type="radio" name="answers[5]" value="126:b"><label>10-20公里</label></span>
          <span class="radioCustom"><input type="radio" name="answers[5]" value="126:c"><label>20-30公里</label></span>
          <span class="radioCustom"><input type="radio" name="answers[5]" value="126:d"><label>30公里以上</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        7. 贵村是否有小卖铺？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[6]" value="127:a"><label>有</label></span>
          <span class="radioCustom"><input type="radio" name="answers[6]" value="127:b"><label>无</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        8. 贵村是否有医疗所？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[7]" value="128:a"><label>有</label></span>
          <span class="radioCustom"><input type="radio" name="answers[7]" value="128:b"><label>无</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        9. 附近15公里内邻村数量？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[8]" value="129:a"><label>3个以下</label></span>
          <span class="radioCustom"><input type="radio" name="answers[8]" value="129:b"><label>3~5个</label></span>
          <span class="radioCustom"><input type="radio" name="answers[8]" value="129:c"><label>6~8个</label></span>
          <span class="radioCustom"><input type="radio" name="answers[8]" value="129:d"><label>8个以上</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        10. 村民平时出行（近距离），用哪一种交通工具？
        <div class="checkboxRow">
          <span class="checkboxCustom"><input type="checkbox" name="answers[9]" value="130:a"><label>步行</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[9]" value="130:b"><label>自行车</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[9]" value="130:c"><label>摩托车</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[9]" value="130:d"><label>三轮车</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[9]" value="130:e"><label>公交车</label></span>
        </div>
      </li>
    </div>
    <input type="hidden" name="questionnaireNumber" id="questionnaire-number" value="2">
    <input type="hidden" name="applicationId" id="application-id" value="2">
  </form>

</body>
</html>
