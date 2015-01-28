<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 1/28/15
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <title></title>
</head>
<body>

    <h3>常见问题</h3>
    <div id="accordion" class="panel-group faqAccordion">

      <div class="panel panel-default">
        <div class="panel-heading">
          <a href="#collapse1One" data-parent="#accordion" data-toggle="collapse" class="panel-toggle">
            1.这个网站我们都可以上吗？
          </a>
        </div>
        <!-- end panel-heading -->
        <div class="panel-collapse in" id="collapse1One">
          <div class="panel-body">
            答：可以我们的网站是对公网开放的，任何人都可以通过个人身份证来进行注册，然后享受上面的服务。
          </div>
        </div>
      </div>

      <div class="panel panel-default">
        <div class="panel-heading">
          <a href="#collapse1Two" data-parent="#accordion" data-toggle="collapse"
             class="panel-toggle collapsed">
            2.每个村都可以建立这样的车站吗？
          </a>
        </div>
        <!-- end panel-heading -->
        <div class="panel-collapse collapse" id="collapse1Two">
          <div class="panel-body">
            答：不是，要以10个村为一个点建立一个基点设施，对基点设施的选择也有一定要求。
          </div>
        </div>
      </div>

      <div class="panel panel-default">
        <div class="panel-heading">
          <a href="#collapse1Three" data-parent="#accordion" data-toggle="collapse"
             class="panel-toggle collapsed">
            3.平台上什么都有吗？
          </a>
        </div>
        <!-- end panel-heading -->
        <div class="panel-collapse collapse" id="collapse1Three">
          <div class="panel-body">
            答：只要是对农村有利的服务，我们里面都会有，杜绝不健康的资料出现在平台上，我们会严格审核。
          </div>
        </div>
      </div>
    </div>
    <p class="txtAlignRight">
      有更多问题？请看<br/>
      <a href="faq" class="orange-link">常见问题</a>
    </p>

</body>
</html>
