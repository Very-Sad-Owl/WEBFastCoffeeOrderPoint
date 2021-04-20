<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 24.03.2021
  Time: 01:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel='stylesheet' href='../../css/modal_style/alert.css' type='text/css' media='all'/>

<fmt:setLocale value = "${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<link rel='stylesheet' href='../../css/modal_style/alert.css' type='text/css' media='all'/>

<div class="alert" id="alert_modal">
    <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
    <p class = msg id = "msg"></p>
</div>
