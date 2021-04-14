<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 12.04.2021
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel='stylesheet' href='../../../css/common/language_switcher_style.css' type='text/css' media='all'/>

<fmt:setLocale value = "${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>

<html>
<head>
    <title><fmt:message bundle="${loc}" key="locale.manage.orders"/></title>
</head>
<body>
    <jsp:include page="../../common/language_switcher.jsp"/>

    <select id="spots" name="spots" onchange="changeSpot(this)">
        <c:forEach var="n" items="${requestScope.spots}" varStatus="loop">
            <c:if test="${sessionScope.locale == 'en' || sessionScope.locale == null}">
                ${n.address.region}${', '}${n.address.city}${', '}${n.address.street}
                ${', '}${n.address.city}${', '}${n.address.house}
            </c:if>
            <c:if test="${sessionScope.locale == 'ru'}">
                ${n.address.regionRu}${', '}${n.address.cityRu}${', '}${n.address.streetRu}
                ${', '}${n.address.cityRu}${', '}${n.address.house}
            </c:if>
            <%--<c:if test="${requestScope.uid == n.uid}">--%>
            <%--<option value="${n.uid}" selected>${n.address}</option>--%>
        <%--</c:if>--%>
            <%--<c:if test="${requestScope.uid != n.uid}">--%>
                <%--<option value="${n.uid}">${n.address}</option>--%>
            <%--</c:if>--%>
        </c:forEach>
    </select>
    <jsp:include page="orders_table.jsp"/>
</body>
</html>
