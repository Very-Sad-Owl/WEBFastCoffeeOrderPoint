<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 05.04.2021
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel='stylesheet' href='../../css/floating_btn_style.css' type='text/css' media='all'/>
<link rel='stylesheet' href='../../css/logo_style.css' type='text/css' media='all'/>
<script type="text/javascript" src="../../js/admin/spots_management.js"></script>
<html>
<head>
    <title>Buy ingredients</title>
</head>
<jsp:include page="../logo.jsp"/>
<body>
    <c:if test="${param.message != '' && param.message != null}">
        <script>openForm('${param.message}')</script>
    </c:if>
    <jsp:include page="../logo.jsp"/>
    <jsp:include page="buy_ingredient_popup.jsp"/>
    <jsp:include page="add_ingredient_popup.jsp"/>
    <jsp:include page="available_ingredients_table.jsp" />
    <div class="fab" onclick="openBuyIngredientsForm()"> + </div>
</body>
</html>
