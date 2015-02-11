<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2/6/15
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>

  <form id="questionnaire3-form" role="form" action="updateQuestionnaire" method="post">

    <div id="questionnaire">
      <li class="J_group_choice">
        1. 村民平时出行（远距离），用哪一种交通工具？
        <div class="checkboxRow">
          <span class="checkboxCustom"><input type="checkbox" name="answers[0]" value="131:a"><label>汽车</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[0]" value="131:b"><label>火车</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[0]" value="131:c"><label>飞机</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[0]" value="131:d"><label>轮船</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        2. 学生平时如何上学？
        <div class="checkboxRow">
          <span class="checkboxCustom"><input type="checkbox" name="answers[1]" value="132:a"><label>步行</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[1]" value="132:b"><label>骑自行车</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[1]" value="132:c"><label>校车接送</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[1]" value="132:d"><label>拼车接送</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        3. 如果有使用到交通工具，交通费大概每人每月多少钱？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[2]" value="133:a"><label>5元以下</label></span>
          <span class="radioCustom"><input type="radio" name="answers[2]" value="133:b"><label>5-10元</label></span>
          <span class="radioCustom"><input type="radio" name="answers[2]" value="133:c"><label>10-20元</label></span>
          <span class="radioCustom"><input type="radio" name="answers[2]" value="133:d"><label>20元以上</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        4. 农集团有计划在符合条件的村里建设“农村综合服务中心”，用地约35亩 (非农耕地，村里是否有意向让惠农集团建设“农村综合服务中心”。
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[3]" value="134:a"><label>有</label></span>
          <span class="radioCustom"><input type="radio" name="answers[3]" value="134:b"><label>无</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        5. 村内的水源主要来自哪里？
        <div class="checkboxRow">
          <span class="checkboxCustom"><input type="checkbox" name="answers[4]" value="135:a"><label>自来水</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[4]" value="135:b"><label>河水</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[4]" value="135:c"><label>湖水</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[4]" value="135:d"><label>井水</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[4]" value="135:e"><label>其他</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        6. 惠农集团将建立水处理设施，以消除水中的杂质，为贵村提供健康饮用水资源，且每人每天免费提供一桶，是否乐意接受？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[5]" value="136:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[5]" value="136:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        7. 村主要从事哪种农业活动？（可多选）
        <div class="checkboxRow">
          <span class="checkboxCustom"><input type="checkbox" name="answers[6]" value="137:a"><label>种植业</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[6]" value="137:b"><label>林业</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[6]" value="137:c"><label>畜牧业</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[6]" value="137:d"><label>渔业</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[6]" value="137:e"><label>其他</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        8. 贵村从事农业活动的技术来源？
        <div class="checkboxRow">
          <span class="checkboxCustom"><input type="checkbox" name="answers[7]" value="138:a"><label>合作社</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[7]" value="138:b"><label>农业部门</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[7]" value="138:c"><label>企业</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[7]" value="138:d"><label>祖传</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[7]" value="138:e"><label>其他</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        9. 贵村有多少人从事种植业？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[8]" value="139:a"><label>200-300人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[8]" value="139:b"><label>300-400人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[8]" value="139:c"><label>400-500人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[8]" value="139:d"><label>500-600人</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        10. 贵村有多少人从事林业？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[9]" value="140:a"><label>50-100人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[9]" value="140:b"><label>100-150人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[9]" value="140:c"><label>150-200人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[9]" value="140:d"><label>200-300人</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        11. 贵村有多少人从事畜牧业？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[10]" value="141:a"><label>50-100人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[10]" value="141:b"><label>100-150人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[10]" value="141:c"><label>150-200人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[10]" value="141:d"><label>200-300人</label></span>
        </div>
      </li>
    </div>
    <input type="hidden" name="questionnaireNumber" id="questionnaire-number" value="3">
    <input type="hidden" name="applicationId" id="application-id" value="2">
  </form>

</body>
</html>
