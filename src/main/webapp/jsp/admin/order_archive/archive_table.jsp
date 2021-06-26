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
<script src="http://code.jquery.com/jquery-2.2.4.js"
        type="text/javascript"></script>
<script type="text/javascript" src="../../../js/scripts.js"></script>
<script type="text/javascript" src="../../../js/admin/order_management.js"></script>
<link rel='stylesheet' href='../../../css/table_style.css' type='text/css' media='all'/>
<%@ taglib prefix = "addr" uri = "/WEB-INF/addressLineTag.tld"%>
<fmt:setLocale value="${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>

<table class="styled-table" id = "inprocess_orders_table">
    <thead>
    <tr class="header">
        <td>uid</td>
        <td>Coast</td>
        <td>Payment Method</td>
        <td>User</td>
        <td>Client Arrival</td>
        <td>Status</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="n" items="${requestScope.orders}" varStatus="loop">
        <tr id="${n.uid}" class = "${n.address.uid}">

                <td>${n.uid}</td>
                <td>${n.coast}</td>
                <td>${n.paymentMethod}</td>
                <td>${n.user.login}</td>
                <td>${n.estimatedTime}</td>
                <td>${n.status}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
