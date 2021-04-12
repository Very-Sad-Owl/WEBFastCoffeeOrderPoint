<%@ page language="java" contentType="text/html; 
    charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value = "${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<link href="../css/logination_style.css" rel="stylesheet" type="text/css">

<div class="login">
  <h1><fmt:message bundle="${loc}" key="locale.login"/></h1>
    <form name="loginForm" method="post" action="Controller" autocomplete="off">
      <input type="hidden" name="command" value="logination" />

      <input type="text" name="login"
             placeholder="<fmt:message bundle="${loc}" key="locale.username.hint"/>"
             required="required" />

        <input type="password" name="password"
               placeholder="<fmt:message bundle="${loc}" key="locale.password.hint"/>"
               required="required" />

        <button type="submit" class="btn btn-primary btn-block btn-large">
          <fmt:message bundle="${loc}" key="locale.login"/>
        </button>
      <a href="Controller?command=gotoregistrationpage">
        <button class="btn btn-primary btn-block btn-large">
          <fmt:message bundle="${loc}" key="locale.registration"/>
        </button>
      </a>
    </form>
  <div class = "error">
      <br><p id = "error"><fmt:message bundle="${loc}" key="${param.message != null ? param.message : ''}"/></p>
  </div>
</div>

