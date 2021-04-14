<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 15.03.2021
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel='stylesheet' href='../css/main_style.css' type='text/css' media='all'/>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Oswald:400,500,700%7CRoboto:400,500,700%7CHerr+Von+Muellerhoff:400,500,700%7CQuattrocento+Sans:400,500,700' type='text/css' media='all'/>
<script src='../js/scripts.js'></script>
<fmt:setLocale value = "${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>

<header id="masthead" class="site-header">
        <jsp:include page="logo.jsp"/>
    <nav id="site-navigation" class="main-navigation">
        <button class="menu-toggle"><fmt:message bundle="${loc}" key="locale.menu"/></button>
        <a class="skip-link screen-reader-text" href="#content">Skip to content</a>
        <div class="menu-menu-1-container">
            <ul id="menu-menu-1" class="menu">
                <c:if test="${sessionScope.auth == true}">
                    <li><a href="coming soon .."><fmt:message bundle="${loc}" key="locale.about"/></a></li>
                    <li><a href="Controller?command=gotoindexpage"><fmt:message bundle="${loc}" key="locale.order"/></a></li>
                    <li><a href="Controller?command=gotocheckorder"><fmt:message bundle="${loc}" key="locale.check.order"/></a></li>
                    <li><a href="#"><fmt:message bundle="${loc}" key="locale.account"/></a>
                        <ul class="sub-menu">
                            <li><a href="Controller?command=logout"><fmt:message bundle="${loc}" key="locale.logout"/></a></li>
                            <li><a href="Controller?command=gotoprofilepage"><fmt:message bundle="${loc}" key="locale.account"/></a></li>
                        </ul>
                    </li>
                    <li class = "cart">
                        <a href="Controller?command=gotocart"><fmt:message bundle="${loc}" key="locale.cart"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.auth == false || sessionScope.auth == null}">
                    <li><a href="coming soon .."><fmt:message bundle="${loc}" key="locale.about"/></a></li>
                    <li><a href="Controller?command=gotoindexpage"><fmt:message bundle="${loc}" key="locale.menu"/></a></li>
                    <li><a href="#"><fmt:message bundle="${loc}" key="locale.account"/></a>
                        <ul class="sub-menu">
                            <li><a href="Controller?command=gotologinationpage"><fmt:message bundle="${loc}" key="locale.login"/></a></li>
                            <li><a href="Controller?command=gotoregistrationpage"><fmt:message bundle="${loc}" key="locale.register"/></a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </div>
    </nav>
</header>
