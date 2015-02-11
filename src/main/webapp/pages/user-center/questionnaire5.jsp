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

  <form id="questionnaire5-form" role="form" action="updateQuestionnaire" method="post">
    <div id="questionnaire">
      <li class="J_group_choice">
        1. 惠农集团的人力资源部，将为农民朋友提供就业咨询及适合的企业、工厂的招聘信息，避免出现农民在农闲时，想进城找工作而没有好的途径的困惑，以及企业招不到人的现象，农民朋友是否需要这项服务？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[0]" value="154:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[0]" value="154:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        2. 惠农集团的人力资源部，还将提供劳动技能培训服务，提升农民朋友的劳动技能，扩展就业范围，您是否愿意参与和支持？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[1]" value="155:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[1]" value="155:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        3. 对于想创业的农民朋友，惠农集团将提供创业咨询及帮助，您是否支持？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[2]" value="156:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[2]" value="156:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        4. 惠农集团将向农民朋友提供农业科技知识的宣传，甚至可以让农业专家通过视频对农民朋友的问题进行逐个讲解和解决，从而达到科技兴农的目的，您是否觉得很有必要？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[3]" value="157:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[3]" value="157:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        5. 惠农集团的网上平台，还可以通过视频，由各行业专家给予农民朋友各种服务和支持，比如行政、法律、会计、翻译、报关、旅游等等，您认为这些服务对农民朋友是否有用？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[4]" value="158:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[4]" value="158:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        6. 惠农集团的“农村综合服务中心”设有小型医疗室，可以对村民平时的小病小痛进行治疗，村民们是否欢迎？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[5]" value="159:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[5]" value="159:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        7. 当遇重大意外或突发重大疾病的时候，可以由医疗室的医生通过网络联系医疗专家，及时对患者进行指导治疗，从而避免因去大医院而耽误的时间，您是否支持这样的服务？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[6]" value="160:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[6]" value="160:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        8. 惠农集团的“农村综合服务中心”还将设有小型游乐场，包括深受欢迎的旋转木马、海盗船等等，价格低廉，希望借此丰富小朋友的童年生活，使他们健康快乐的成长，是否愿意消费？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[7]" value="161:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[7]" value="161:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        9. 惠农集团的“农村综合服务中心”还将设有简易KTV、电影院，以此来丰富村民的生活，您是否愿意参与？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[8]" value="162:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[8]" value="162:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        10. 车站将设小卖部，出售一些日常用品、普通衣服、鞋等，价格公道，村民们是否欢迎及接受？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[9]" value="163:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[9]" value="163:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        11. 惠农集团的“农村综合服务中心”设有洗衣间，价格低廉，冬天的时候衣服、被子难以手洗，村民们是否愿意来消费？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[10]" value="164:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[10]" value="164:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        12. 车站将设公共澡堂，冷热水都有，价格低廉，村民们是否愿意消费？
        <div class="radioRow">
          <span class="radioCustom"><input type="radio" name="answers[11]" value="165:a"><label>是</label></span>
          <span class="radioCustom"><input type="radio" name="answers[11]" value="165:b"><label>否</label></span>
        </div>
      </li>

      <li class="J_group_choice">
        13. 您认为贵村急需解决的问题是什么？
        <div class="checkboxRow">
          <span class="checkboxCustom"><input type="checkbox" name="answers[12]" value="166:a"><label>交通问题</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[12]" value="166:b"><label>教育问题</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[12]" value="166:c"><label>经济发展</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[12]" value="166:d"><label>环境卫生</label></span>
          <span class="checkboxCustom"><input type="checkbox" name="answers[12]" value="166:e"><label>科技信息</label></span>
        </div>
      </li>
    </div>
    <input type="hidden" name="questionnaireNumber" id="questionnaire-number" value="5">
    <input type="hidden" name="applicationId" id="application-id" value="2">
  </form>


</body>
</html>
