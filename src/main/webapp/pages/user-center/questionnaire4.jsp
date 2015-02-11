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

  <form id="questionnaire4-form" role="form" action="updateQuestionnaire" method="post">

    <div id="questionnaire">
      <li class="J_group_choice">
        1. 贵村有多少人从事渔业？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[0]" value="142:a"><label>50-100人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[0]" value="142:b"><label>100-150人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[0]" value="142:c"><label>150-200人</label></span>
          <span class="radioCustom"><input type="radio" name="answers[0]" value="142:d"><label>200-300人</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        2. 贵村是否有特色养殖或者种植？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[1]" value="143:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[1]" value="143:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        3. 贵村是否有农家乐或旅游项目，或者有待发展的旅游项目？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[2]" value="144:a"><label>有</label></span>
          <span class="radioCustom"><input type="radio" name="answers[2]" value="144:b"><label>无</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        4. 贵村是否有积压农产品的现象？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[3]" value="145:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[3]" value="145:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        5. 导致积压产品的主要原因是什么？
        <div class="checkboxRow">
          <span class="checkboxCustom"><input type="checkbox" name="answers[4]" value="146:a"><label>交通不便</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[4]" value="146:b"><label>价格太低</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[4]" value="146:c"><label>信息不畅</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[4]" value="146:d"><label>品质不好</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        6. 导致产品利润不高的主要原因是什么？
        <div class="checkboxRow">
          <span class="checkboxCustom"><input type="checkbox" name="answers[5]" value="147:a"><label>运输费用高</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[5]" value="147:b"><label>品质不好</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[5]" value="147:c"><label>中间环节多</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[5]" value="147:d"><label>种植成本高</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        7. 贵村农产品的销售途径有哪些？
        <div class="checkboxRow">
          <span class="checkboxCustom"><input type="checkbox" name="answers[6]" value="148:a"><label>合作社统一销售</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[6]" value="148:b"><label>私人收购</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[6]" value="148:c"><label>企业上门收购</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[6]" value="148:d"><label>政府收购</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[6]" value="148:e"><label>无</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        8. 惠农集团可以帮助贵村解决农产品积压和销售的问题，建立统一的销售渠道，贵村是否愿意加入？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[7]" value="149:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[7]" value="149:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        9. 惠农集团拥有专业、科学的食品保鲜技术，可以延长食品的保鲜期限，贵村是否愿意采用？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[8]" value="150:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[8]" value="150:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        10. 惠农集团还将向贵村定制需要的农产品，比如蔬菜、鱼等农产品，是否乐意加入？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[9]" value="151:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[9]" value="151:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        11. 如果家乡有企业，贵村村民是否愿意回乡发展，壮大贵村的经济实力？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[10]" value="152:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[10]" value="152:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        12. 惠农集团将帮助贵村发展适合贵村的旅游资源、特色产品、地方风俗、小吃等，是否欢迎和支持？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[11]" value="153:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[11]" value="153:b"><label>否</label></span>
        </div>
      </li>
    </div>
    <input type="hidden" name="questionnaireNumber" id="questionnaire-number" value="4">
    <input type="hidden" name="applicationId" id="application-id" value="2">
  </form>


</body>
</html>
