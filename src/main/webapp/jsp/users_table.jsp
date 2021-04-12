<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 15.03.2021
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="../js/users_management.js"></script>
<fmt:setLocale value = "${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>

<table class="styled-table">
    <thead>
    <tr>
        <%--<c:forEach var="n" items="${requestScope.beverages}">--%>
            <%--<th>${n}</th>--%>
        <%--</c:forEach>--%>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="n" items="${requestScope.users}" varStatus="loop">
        <tr id="${n.login}">
            <td>${n.login}</td>
            <td id = "${n.login}${"password"}">${n.password}</td>
            <td id = "${n.login}${"email"}">${n.email}</td>
            <td id = "${n.login}${"balance"}">${n.balance}</td>
            <td id = "${n.login}${"role"}">${n.role}</td>
            <%--<td>--%>
                <%--<img src="${'../'}${n.imgPath}" alt="${n.title}"/>--%>
            <%--</td>--%>
            <th>
                <div >
                        <button type="button" class="delete_ingr" onclick="deleteUser('${n.login}')">
                            <fmt:message bundle="${loc}" key="locale.delete.btn"/>
                        </button>
                        <button type="button" class="delete_ingr" onclick="banUser('${n.login}')">
                            <fmt:message bundle="${loc}" key="locale.ban"/>
                        </button>
                        <button type="button" class="delete_ingr" onclick="unbanUser('${n.login}')">
                            <fmt:message bundle="${loc}" key="locale.unban"/>
                        </button>
                </div>
            </th>
        </tr>
    </c:forEach>
    </tbody>
</table>


