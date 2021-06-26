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
<%@ taglib uri="/WEB-INF/addressLineTag.tld" prefix="addr"%>
<link rel='stylesheet' href='../../../css/common/language_switcher_style.css' type='text/css' media='all'/>
<script src="http://code.jquery.com/jquery-2.2.4.js"
        type="text/javascript"></script>
<script type="text/javascript" src="../../../js/scripts.js"></script>
<script type="text/javascript" src="../../../js/admin/order_management.js"></script>
<fmt:setLocale value = "${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>

<html>
<head>
    <title><fmt:message bundle="${loc}" key="locale.manage.orders"/></title>
</head>
<body>
    <jsp:include page="../../common/language_switcher.jsp"/>

    <label for="sorting_options">Sort by</label>
    <select id="sorting_options" name="sorting_options" onchange="location = this.value;">
        <option value="0" selected>
            All
        </option>
        <c:forEach var="n" items="${requestScope.sorting_options}" varStatus="loop">
            <c:if test="${sessionScope.locale == 'en' || sessionScope.locale == null}">
                <option value="Controller?command=gotoorderarchivepage&sorting_option=${loop.index}">
                    ${n}
                </option>
            </c:if>
            <c:if test="${sessionScope.locale == 'ru'}">
                <option value="Controller?command=gotoorderarchivepage&sorting_option=${loop.index}">
                    ${n}
                </option>
            </c:if>
        </c:forEach>
    </select>
    <jsp:include page="archive_table.jsp"/>
</body>
</html>
