<%@ page language="java" contentType="text/html; 
    charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value = "${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<link href="../css/logination_style.css" rel="stylesheet" type="text/css">

<div class="login" id = "login">
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

  <button class="btn btn-primary btn-block btn-large" onclick="switchToPswRecovery() ">Forgot your password?</button>

  <div class = "error">
      <br>
        <p id = "error">
            <c:if test="${param.message != null}">
              <fmt:message bundle="${loc}" key="${param.message}"/>
            </c:if>
       </p>
  </div>
</div>

<div class="login" id = "password_recovery" style="display: none;">
  <h1><fmt:message bundle="${loc}" key="locale.login"/></h1>
  <form name="recoveryForm" method="post" action="Controller" autocomplete="off">
    <input type="hidden" name="command" value="editprofile" />
    <input type="hidden" name="action" value="send_reset_msg" />

    <input type="text" name="email"
           placeholder="<fmt:message bundle="${loc}" key="locale.email.hint"/>"
           required="required" />

    <button type="submit" class="btn btn-primary btn-block btn-large">
      <fmt:message bundle="${loc}" key="locale.save"/>
    </button>

    <button onclick="switchToAuth()" class="btn btn-primary btn-block btn-large">
      <fmt:message bundle="${loc}" key="locale.cancel"/>
    </button>

  </form>
</div>

<script>
  function switchToPswRecovery(){
    document.getElementById("password_recovery").style.display = "initial";
    document.getElementById("login").style.display = "none";
  }

  function switchToAuth(){
    document.getElementById("password_recovery").style.display = "none";
    document.getElementById("login").style.display = "initial";
  }
</script>

