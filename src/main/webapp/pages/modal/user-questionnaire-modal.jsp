<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2/12/15
  Time: 09:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
<button type="button" id="myModalTrigger" class="btn btn-primary" data-toggle="modal" data-target="#myModal"
        style="display:none">提交
</button>
<div class="modal fade in" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">提示</h4>
      </div>
      <div class="modal-body">提交后即不可以再修改。如要修改，需要电话联系我们的工作人员。你确定提交吗？</div>
      <div class="buttonsRow">
        <button id="return" type="button" class="white-bt" data-dismiss="modal">返回</button>
        <button type="submit" id="dialogSubmit" class="orange-bt meta-event-source"
                meta-event-handler="userApplication:confirmSubmit">提交
        </button>
      </div>
    </div>
  </div>
</div>
</body>
</html>
