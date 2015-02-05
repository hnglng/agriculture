<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2/5/15
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
<!-- Button trigger modal -->
<button type="button" id="myModalTrigger" class="btn btn-primary" data-toggle="modal" data-target="#myModal"
        style="display:none">提交
</button>

<div class="modal fade in" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">
          <span aria-hidden="true">×</span><span class="sr-only">Close</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">提示</h4>
      </div>
      <div class="modal-body">你确认要更新调查问卷答案和状态吗？</div>
      <div class="buttonsRow">
        <button id="return" type="button" class="white-bt" data-dismiss="modal">返回</button>
        <button type="submit" id="submit" class="orange-bt meta-event-source"
                meta-event-handler="userList:submit">提交
        </button>
      </div>
    </div>
  </div>
</div>

</body>
</html>
