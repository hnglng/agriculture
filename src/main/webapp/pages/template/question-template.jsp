<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2/5/15
  Time: 09:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
<script id="question-template-checkbox" type="text/x-handlebars-template">
  <li class="J_group_choice">
    {{fromOne}}. {{questionContent}}
    <div class="checkboxRow">
      <span class="checkboxCustom"><input type="checkbox" name="answers[{{fromZero}}]" value="{{questionId}}:a"><label>{{option1}}</label></span>
      <span class="checkboxCustom"><input type="checkbox" name="answers[{{fromZero}}]" value="{{questionId}}:b"><label>{{option2}}</label></span>
      <span class="checkboxCustom"><input type="checkbox" name="answers[{{fromZero}}]" value="{{questionId}}:c"><label>{{option3}}</label></span>
      <span class="checkboxCustom"><input type="checkbox" name="answers[{{fromZero}}]" value="{{questionId}}:d"><label>{{option4}}</label></span>
      <span class="checkboxCustom"><input type="checkbox" name="answers[{{fromZero}}]" value="{{questionId}}:e"><label>{{option5}}</label></span>
    </div>
  </li>
</script>

<script id="question-template-radio" type="text/x-handlebars-template">
  <li class="J_group_choice">
    {{fromOne}}. {{questionContent}}
    <div class="radioRow">
      <span class="radioCustom"><input type="radio" name="answers[{{fromZero}}]" value="{{questionId}}:a"><label>{{option1}}</label></span>
      <span class="radioCustom"><input type="radio" name="answers[{{fromZero}}]" value="{{questionId}}:b"><label>{{option2}}</label></span>
      <span class="radioCustom"><input type="radio" name="answers[{{fromZero}}]" value="{{questionId}}:c"><label>{{option3}}</label></span>
      <span class="radioCustom"><input type="radio" name="answers[{{fromZero}}]" value="{{questionId}}:d"><label>{{option4}}</label></span>
      <span class="radioCustom"><input type="radio" name="answers[{{fromZero}}]" value="{{questionId}}:e"><label>{{option5}}</label></span>
    </div>
  </li>
</script>
</body>
</html>
