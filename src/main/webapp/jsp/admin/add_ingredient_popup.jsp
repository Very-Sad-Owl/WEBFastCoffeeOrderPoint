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
<link rel='stylesheet' href='../../css/admin/add_ingredient_popup_style.css' type='text/css' media='all'/>

<fmt:setLocale value = "${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>

<div class = "update_popup" id = "update_popup">
    <h2 id = "chosen_title_header"></h2>

    <form class = "update_ingr_form" id = "update_ingr_form" onsubmit="return onBuy(this)">
        <input type="hidden" id = "spot_uid" value="${requestScope.uid}" name = "spot_uid">
        <input type="hidden" id = "chosen_title" name = "ingredient">

        <input name = "amount" value="1" id = "ingredient_increment" type="number" min="1" step="1" onchange="onAmountChanged()">

        <button type="submit" id = "submit_changes" class="btn ok">
            <fmt:message bundle="${loc}" key="locale.submit"/>
        </button>
        <button type="button" id="cancel_changes" class="btn cancel" onclick="closeUpdateIngredientsForm()">
            <fmt:message bundle="${loc}" key="locale.cancel"/>
        </button>
    </form>
    <div class = "total_coast">
        <p>Total coast:<span id = "coast_span">0.00</span></p>
    </div>
</div>
