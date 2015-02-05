<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2/5/15
  Time: 09:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>

<div id="exportModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabelTitle">确定导出问卷调查结果?</h4>
      </div>
      <div class="buttonsRow">
        <button type="button" class="white-bt" data-dismiss="modal">取消</button>
        <a href="javascript:void(0)" class="orange-bt meta-event-source" meta-event-handler="dataExport:exportCSV"
           id="exportCSV">确定</a>
      </div>
    </div>
  </div>
</div>
</body>
</html>
