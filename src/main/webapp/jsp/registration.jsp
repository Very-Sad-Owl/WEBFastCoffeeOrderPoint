<%@ page language="java" contentType="text/html;
    charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<link href="../css/registration_style.css" rel="stylesheet" type="text/css">

<fmt:setLocale value = "${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>

<body>

<!-- <div class="fullscreen-bg"> -->
    <!-- <video width="100%" height="auto" playsinline autoplay muted loop poster="../../resources/image/auth_bak.jpg" id="bgvid"> -->
			<!-- <source src="../../resources/video/auth.webm" type="video/webm"> -->
			<!-- <source src="../../resources/video/auth.mp4" type="video/mp4"> -->
	<!-- </video> -->
<!-- </div> -->

<div class="register">
  <h1><fmt:message bundle="${loc}" key="locale.registration"/></h1>
    <form name="registrationForm" method="post" action="Controller" autocomplete="off">
        <input type="hidden" name="command" value="registration" />

        <input type="text" name="login"
               placeholder="<fmt:message bundle="${loc}" key="locale.username.hint"/>"
               required="required" />

        <input type="password" name="password"
               placeholder="<fmt:message bundle="${loc}" key="locale.password.hint"/>"
               required="required" />

		<input type="email" name="email"
               placeholder="<fmt:message bundle="${loc}" key="locale.email.hint"/>" />

        <button type="submit" class="btn btn-primary btn-block btn-large">
            <fmt:message bundle="${loc}" key="locale.register"/>
        </button>
        <a href="Controller?command=gotologinationpage">
            <button class="btn btn-primary btn-block btn-large">
                <fmt:message bundle="${loc}" key="locale.registration"/>
            </button>
        </a>

        <div class = "error">
            <br>
            <c:forEach var="n" items="${param.message != null ? fn:split(param.message, ';') : ''}" varStatus="loop">
            <p id = "error">
                <fmt:message bundle="${loc}" key="${n}"/>
            </p>
            </c:forEach>
        </div>
    </form>
</div>
</body>


