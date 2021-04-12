<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 04.04.2021
  Time: 01:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="http://code.jquery.com/jquery-2.2.4.js"
        type="text/javascript"></script>
<script type="text/javascript" src="../../../js/scripts.js"></script>
<script type="text/javascript" src="../../../js/admin/order_management.js"></script>
<link rel='stylesheet' href='../../../css/table_style.css' type='text/css' media='all'/>
<fmt:setLocale value="${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>

<table class="styled-table">
    <thead>
    <tr>
        <td>uid</td>
        <td>To pay</td>
        <td>Payment Method</td>
        <td>User</td>
        <td>Client Arrival</td>
        <td>Action</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="n" items="${requestScope.orders}" varStatus="loop">
        <tr id="${n.uid}">

                <td>${n.uid}</td>
                <td>${n.coast}</td>
                <td>${n.paymentMethod}</td>
                <td>${n.user.login}</td>
                <td>${n.estimatedTime}</td>

                <td>${n.estimatedTime}</td>
            <c:if test="${n.status == 'PLACED'}">
                <th>
                    <div>
                        <button type="button" class="accept_order" onclick="acceptOrder('${n.uid}')">
                            <%--<fmt:message bundle="${loc}" key="locale.delete.btn"/>--%>
                            Accept
                        </button>
                        <button type="button" class="decline_order" onclick="acceptDecline('${n.uid}')">
                            <%--<fmt:message bundle="${loc}" key="locale.edit.btn"/>--%>
                            Decline
                        </button>
                    </div>
                </th>
            </c:if>
            <c:if test="${n.status == 'PREPARING'}">
                <th>
                    <div>
                        <button type="button" class="accept_order" onclick="setRedy('${n.uid}')">
                                <%--<fmt:message bundle="${loc}" key="locale.delete.btn"/>--%>
                            Ready
                        </button>
                        <button type="button" class="decline_order" onclick="acceptDecline('${n.uid}')">
                                <%--<fmt:message bundle="${loc}" key="locale.edit.btn"/>--%>
                            Decline
                        </button>
                    </div>
                </th>
            </c:if>
            <c:if test="${n.status == 'READY'}">
                <th>
                    <div>
                        <button type="button" class="deliver_order" onclick="acceptDelivered('${n.uid}')">
                                <%--<fmt:message bundle="${loc}" key="locale.delete.btn"/>--%>
                            Delivered
                        </button>
                        <button type="button" class="archive_order" onclick="acceptForgotten('${n.uid}')">
                                <%--<fmt:message bundle="${loc}" key="locale.edit.btn"/>--%>
                            Forgotten
                        </button>
                    </div>
                </th>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
