<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 12.04.2021
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Manage Orders</title>
</head>
<body>
    <select id="spots" name="spots" onchange="changeSpot(this)">
        <c:forEach var="n" items="${requestScope.spots}" varStatus="loop">
            <c:if test="${requestScope.uid == n.uid}">
                <option value="${n.uid}" selected>${n.address}</option>
            </c:if>
            <c:if test="${requestScope.uid != n.uid}">
                <option value="${n.uid}">${n.address}</option>
            </c:if>
        </c:forEach>
    </select>
    <jsp:include page="orders_table.jsp"/>
</body>
</html>
