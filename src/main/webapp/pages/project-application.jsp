<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--doctype html-->
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!--
  <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
  -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Benefitting Agriculture - Project Application Page</title>

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="js/lib/html5shiv.min.js"></script>
  <script src="js/lib/respond.min.js"></script>
  <![endif]-->

  <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
  <link href="css/custom.css" rel="stylesheet" type="text/css">
  <link href="css/validation.css" rel="stylesheet">
  <!--[if IE 8]>
  <link href="css/ie8.css" rel="stylesheet" type="text/css">
  <![endif]-->

</head>

<body>
<jsp:include page='header.jsp'/>

<!-- PAGE TITLE -->
<div class="page-title withOutTitle">
</div>
<!-- /PAGE TITLE -->

<!-- CONTENT SECTION -->
<section class="contentSection">
  <div class="container">
    <div class="row">
      <span class="col-sm-9 rightBorder equalCol">
        <h3 class="borderBottom">申报项目</h3>
        <form class="projectAppForm" id="projectAppForm" role="form" action="project-application" method="POST">
          <jsp:include page='project-application-questionnaire.jsp'/>
          <jsp:include page='project-application-user.jsp'/>
        </form>
      </span>
      <span class="col-sm-3 sidebar equalCol">
        <jsp:include page='project-application-faq.jsp'/>
      </span>
      <jsp:include page='project-application-modal.jsp'/>
    </div>
  </div>
</section>
<!-- /CONTENT SECTION -->

<jsp:include page='footer.jsp'/>
<script data-main="js/app/pages/project-application" src="js/lib/require-2.1.15.min.js"></script>
</body>
</html>