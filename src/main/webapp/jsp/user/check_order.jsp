<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 03.04.2021
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="http://code.jquery.com/jquery-2.2.4.js"
        type="text/javascript"></script>
<script type="text/javascript" src="../../js/scripts.js"></script>
<script src="../../js/user/check_order.js"></script>
<link rel='stylesheet' href='../../css/user/check_order_style.css' type='text/css' media='all'/>

<fmt:setLocale value = "${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>

<html>
<head>
    <title><fmt:message bundle="${loc}" key="locale.check.order"/></title>
</head>
<body>
    <jsp:include page="../header.jsp"/>
    <form class="check_order_form" id="check_order_form" onsubmit="return onCheck(this)">
        <label for="order_to_check"><fmt:message bundle="${loc}" key="locale.enter.uid.msg"/></label>
        <input type=number id="order_to_check" name="order_to_check">

        <button type="submit" id = "check_order" class="btn ok">
            <fmt:message bundle="${loc}" key="locale.check.order"/>
        </button>
    </form>
    <div class = "order_info">
        <p>${'status'}${' - '}<span id = "status"></span></p>
        <p>${'date'}${' - '}<span id = "date"></span></p>
        <p>${'delivery pont'}${' - '}<span id = "delivery_point"></span></p>
        <p>${'price'}${' - '}<span id = "price"></span></p>
    </div>
</body>
</html>
