<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 15.03.2021
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel='stylesheet' href='../css/table_style.css' type='text/css' media='all'/>
<link rel='stylesheet' href='../css/logo_style.css' type='text/css' media='all'/>
<link rel='stylesheet' href='../css/floating_btn_style.css' type='text/css' media='all'/>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/scripts.js"></script>
<script type="text/javascript" src="../js/ingredient_management.js"></script>
<jsp:include page="edit_ingredient_form.jsp" />
<jsp:include page="add_recepit_form.jsp" />
<jsp:include page="confirmation_modal.jsp" />

<html>
<head>
    <title>Ingredients Manager</title>
</head>
<body>

<%--<jsp:include page="header.jsp"/>--%>
<jsp:include page="logo.jsp"/>
<jsp:include page="recepits_table.jsp"/>
<div class="fab" onclick="openAddForm()"> + </div>
</body>
</html>
