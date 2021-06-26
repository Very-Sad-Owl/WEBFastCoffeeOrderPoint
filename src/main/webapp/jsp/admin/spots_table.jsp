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
        <td>id</td>
        <td>balance</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="n" items="${requestScope.spots}" varStatus="loop">
        <tr id="${n.uid}">
            <c:if test="${pageContext.request.locale.language == 'en' || pageContext.request.locale.language == null}">
                <td>${n.uid}</td>
                <td>${n.balance}</td>
                <td>${n.address.region}</td>
                <td>${n.address.city}</td>
                <td>${n.address.street}</td>
                <td>${n.address.house}</td>
            </c:if>
            <c:if test="${pageContext.request.locale.language == 'ru'}">
                <td>${n.uid}</td>
                <td>${n.balance}</td>
                <td>${n.address.regionRu}</td>
                <td>${n.address.cityRu}</td>
                <td>${n.address.streetRu}</td>
                <td>${n.address.house}</td>
            </c:if>
            <th>
                <div>
                    <button type="button" class="delete_spot" onclick="deleteSpot(${n.uid})">
                        <fmt:message bundle="${loc}" key="locale.delete.btn"/>
                    </button>
                    <button type="button" class="edit_spot" onclick="openEditForm('${n.uid}', '${n.address.region}', '${n.address.regionRu}', '${n.address.city}', '${n.address.cityRu}', '${n.address.street}', '${n.address.streetRu}', '${n.address.house}')">
                        <fmt:message bundle="${loc}" key="locale.edit.btn"/>
                    </button>
                    <a href="Controller?command=gotobuyingredients&uid=${n.uid}">
                    <button type="button" class="buy_ingr">
                        <fmt:message bundle="${loc}" key="locale.buy.btn"/>
                    </button>
                    </a>
                </div>
            </th>
        </tr>
    </c:forEach>
    </tbody>
</table>
