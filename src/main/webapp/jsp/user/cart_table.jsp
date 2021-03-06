<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 15.03.2021
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix = "addr" uri = "/WEB-INF/addressLineTag.tld"%>
<link rel='stylesheet' href='../../css/table_style.css' type='text/css' media='all'/>
<%--<script type="text/javascript" src="../js/users_management.js"></script>--%>
<fmt:setLocale value = "${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>

<form class = "order_form" id = "order_form" onsubmit="return onOrder(this)">
    <label for="spots"></label><fmt:message bundle="${loc}" key="locale.delivery.point"/><select id="spots" name="spots">
        <c:forEach var="n" items="${requestScope.spots}" varStatus="loop">
            <option value="${n.uid}">
                <c:if test="${sessionScope.locale == 'en' || sessionScope.locale == null}">
                    <addr:address message = "${n}" locale="en"/>
                </c:if>
                <c:if test="${sessionScope.locale == 'ru'}">
                    <addr:address message = "${n}" locale="ru"/>
                </c:if>
            </option>
        </c:forEach>
    </select>

    <label for="payment_methods"><fmt:message bundle="${loc}" key="locale.payment.method"/></label><select id="payment_methods" name="payment_methods">
        <c:forEach var="n" items="${requestScope.payment_methods}" varStatus="loop">
            <option value="${n}">${n}</option>
        </c:forEach>
    </select>

    <label for="estimated_time"><fmt:message bundle="${loc}" key="locale.estimated.time"/></label><input name="estimated_time" id = "estimated_time" type="number" value="${requestScope.cart.estimatedTime}">

    <input type="hidden" name="order" value="${requestScope.cart.uid}">
    <table class="styled-table">
        <thead>
        <tr>
            <%--<c:forEach var="n" items="${requestScope.beverages}">--%>
            <%--<th>${n}</th>--%>
            <%--</c:forEach>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="n" items="${requestScope.cart.positions}" varStatus="loop">
            <input type="hidden" name="uid" class="pos_uid">
            <tr id="${n.beverage.type}">
                <td>
                    <img src="${'../'}${n.beverage.imgPath}" alt="${n.beverage.type}"/>
                </td>
                <td>${n.beverage.type}</td>
                <td id="volume">${n.beverage.size.volume}</td>
                <td id="coast">${n.coast}</td>
                <td>
                    <input name="amount" type="number" step="1" min="1" max="10" value="1">
                </td>
                <td>
                    <c:forEach var="m" items="${n.beverage.decorations}" varStatus="loop">
                        ${m.title}${' x'}${m.quantity}${' '}
                    </c:forEach>
                </td>
                <th>
                    <div>
                        <input name="selected" type="checkbox" value="${n.uid}" checked>
                    </div>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button type="submit" id = "make_order" class="btn ok">
        <fmt:message bundle="${loc}" key="locale.order"/>
    </button>
</form>


