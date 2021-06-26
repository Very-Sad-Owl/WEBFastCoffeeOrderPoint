<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 27.03.2021
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>

<script src="http://code.jquery.com/jquery-2.2.4.js"
        type="text/javascript"></script>
<script type="text/javascript" src="../../js/scripts.js"></script>
<script src='../../js/user/profile_manager.js'></script>
<link rel='stylesheet' href='../../css/user/profile_style.css' type='text/css' media='all'/>

<html>
<head>
    <title><fmt:message bundle="${loc}" key="locale.account"/></title>
</head>
<body>
<%--<form class = editable_img" onsubmit="return uploadFile(this)">--%>

<div class="wrapper">
    <div class="left">
        <form class="change_img" onsubmit="return changeImg(this)">
            <img id="user_avatar" src="${'../'}${sessionScope.user.imgPath}"
                 alt="user_img">
            <input type="file" name="img" src="${'../'}${sessionScope.user.imgPath}">
            <button type="submit" id="edit_img"><fmt:message bundle="${loc}" key="locale.save"/></button>
        </form>
    </div>
    <div class="right">
        <form class="edit_user" id="edit_user" onsubmit="return onUpdateUser(this)">
            <div class="info">
                <h3><fmt:message bundle="${loc}" key="locale.information"/></h3>
                <button class="show show_state" type="button" id="edit_switcher" onclick="onUserButton(this)">
                    <fmt:message bundle="${loc}" key="locale.edit.profile"/>
                </button>
                <button class="edit edit_state" id="apply_changes" type="submit" hidden>
                    <fmt:message bundle="${loc}" key="locale.save"/>
                </button>
                <button class="edit edit_state" id="cancel_changes" type="button" onclick="showUser()" hidden>
                    <fmt:message bundle="${loc}" key="locale.cancel"/>
                </button>
            </div>
            <div class="info_data">
                <div class="data">
                    <h4><fmt:message bundle="${loc}" key="locale.login"/></h4>
                    <p class="show">${sessionScope.user.login}</p>
                </div>
                <div class="data">
                    <h4><fmt:message bundle="${loc}" key="locale.email.hint"/></h4>
                    <p class="show">${sessionScope.user.email}</p>
                    <input class="edit" type="email" name="email" id="email_edit" value="${sessionScope.user.email}"
                           hidden>
                </div>
                <div class="data">
                    <h4><fmt:message bundle="${loc}" key="locale.password.hint"/></h4>
                    <p class="show">${sessionScope.user.password}</p>
                    <input class="edit" type="text" name="password" id="password_edit"
                           value="${sessionScope.user.password}" hidden>
                </div>
            </div>
        </form>

        <div class="projects">
            <h3><fmt:message bundle="${loc}" key="locale.order.history"/></h3>
            <div class="projects_data">
                <c:forEach var="n" items="${requestScope.orders}" varStatus="loop">
                    <p>${n.uid}${' - '}${n.date}</p>
                    <br>
                </c:forEach>
            </div>
        </div>

    </div>
</div>

</body>

</html>
