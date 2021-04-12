<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 06.04.2021
  Time: 01:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="http://code.jquery.com/jquery-2.2.4.js"
        type="text/javascript"></script>
<script type="text/javascript" src="../../js/scripts.js"></script>
<script type="text/javascript" src="../../js/admin/spots_management.js"></script>
<link rel='stylesheet' href='../../css/admin/buy_ingredient_popup_style.css' type='text/css' media='all'/>

<fmt:setLocale value = "${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>

<div class = "buy_popup" id = "buy_popup">
    <h2>The select Element</h2>

    <p>Choose ingredient:</p>

    <form class = "add_ingr_form" id = "add_ingr_form" onsubmit="return onBuyNew(this)">
        <input type="hidden" id = "spot_uid" value="${requestScope.uid}" name = "spot_uid">

        <label for="all_ingredients">Choose an ingredient:</label>
        <select id="all_ingredients" name="ingredient">
            <c:forEach var="n" items="${requestScope.all_ingredients}" varStatus="loop">
                <option value="${n.title}">${n.title}</option>
            </c:forEach>
        </select>
        <br>
        <input name = "amount" value="1" id = "ingredient_increment" type="number" min="1" step="1" onchange="onAmountChanged()">
        <br>
        <button type="submit" id = "submit_changes" class="btn ok">
            <fmt:message bundle="${loc}" key="locale.submit"/>
        </button>
        <button type="button" id="cancel_changes" class="btn cancel" onclick="closeBuyIngredientsForm()">
            <fmt:message bundle="${loc}" key="locale.cancel"/>
        </button>
    </form>
    <div class = "total_coast">
        <p>Total coast:<span id = "coast_span">0.00</span></p>
    </div>
</div>
