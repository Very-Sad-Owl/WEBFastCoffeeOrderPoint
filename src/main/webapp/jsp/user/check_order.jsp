<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 03.04.2021
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://code.jquery.com/jquery-2.2.4.js"
        type="text/javascript"></script>
<script type="text/javascript" src="../../js/scripts.js"></script>
<script src="../../js/user/check_order.js"></script>

<html>
<head>
    <title>Check My Order</title>
</head>
<body>
    <jsp:include page="../header.jsp"/>
    <form class="check_order_form" id="check_order_form" onsubmit="return onCheck(this)">
        <label for="order_to_check">Enter your order's ID</label>
        <input type=number id="order_to_check" name="order_to_check">

        <button type="submit" id = "check_order" class="btn ok">
            Check
        </button>
    </form>
</body>
</html>
