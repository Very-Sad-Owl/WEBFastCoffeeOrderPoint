<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 04.04.2021
  Time: 01:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel='stylesheet' href='../../css/table_style.css' type='text/css' media='all'/>
<link rel='stylesheet' href='../../css/logo_style.css' type='text/css' media='all'/>
<link rel='stylesheet' href='../../css/floating_btn_style.css' type='text/css' media='all'/>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/scripts.js"></script>
<script type="text/javascript" src="../../js/admin/spots_management.js"></script>

<html>
<head>
    <title>Spots Manager</title>
</head>
<body>
    <jsp:include page="../logo.jsp"/>
    <jsp:include page="spots_table.jsp"/>
    <jsp:include page="edit_spot_form.jsp"/>
    <jsp:include page="add_spot_form.jsp"/>
    <div class="fab" onclick="openAddForm()"> + </div>
</body>
</html>
