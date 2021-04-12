<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 04.04.2021
  Time: 01:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript" src="../../js/admin/spots_management.js"></script>
<link rel='stylesheet' href='../../css/table_style.css' type='text/css' media='all'/>
<fmt:setLocale value="${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>

<table class="styled-table">
    <thead>
    <tr>

    </tr>
    </thead>
    <tbody id = "table_body">
    <c:forEach var="n" items="${requestScope.ingredients}" varStatus="loop">
        <tr id="${n.title}">
            <td>${n.title}</td>
            <td>${n.coast}</td>
            <td id = "${n.title}${'_quantity'}">${n.quantity}</td>
            <th>
                <button type="button" class="delete_ingr" onclick="openUpdateIngredientsForm('${n.title}', '${n.coast}')">
                    <fmt:message bundle="${loc}" key="locale.buy.btn"/>
                </button>
            </th>
        </tr>
    </c:forEach>
    </tbody>
</table>
