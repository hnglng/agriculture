<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 1/28/15
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
  <!-- Button trigger modal -->
  <button type="button" id="projectAppModelTrigger" data-toggle="modal" data-target="#projectAppModel"
          style="display:none"></button>

  <!-- Project Application Page-Confirm Modal -->
  <div class="modal fade" id="projectAppModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
       aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">确认提交之前，以下信息</h4>
        </div>
        <div class="modal-body">
          请确认是否提交?
        </div>
        <div class="buttonsRow">
          <button type="submit" class="orange-bt" id="confirmed-submit">提交</button>
          <button type="button" class="white-bt" data-dismiss="modal" id="closeModal">取消</button>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
