<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 07.04.2021
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel='stylesheet' href='../../css/common/language_switcher_style.css' type='text/css' media='all'/>
<fmt:setLocale value = "${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<div>
    <ul class="lang_options">
        <li><a href="Controller?command=switchlanguage&language=ru"><fmt:message bundle="${loc}" key="locale.russian"/></a></li>
        <li><a href="Controller?command=switchlanguage&language=en"><fmt:message bundle="${loc}" key="locale.english"/></a></li>
    </ul>
</div>
