<%@ page language="java" contentType="text/html;
    charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<script src="http://code.jquery.com/jquery-2.2.4.js"
        type="text/javascript"></script>
<script type="text/javascript" src="../../js/scripts.js"></script>
<link href="../../css/edit_ingr_popup_style.css" rel="stylesheet" type="text/css">
<link rel='stylesheet' href='../../css/admin/spot_form_style.css' type='text/css' media='all'/>
<script type="text/javascript" src="../js/admin/spots_management.js"></script>

<fmt:setLocale value = "${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>

<div class="edit_spot_popup" id = "edit_spot_popup">
  <h1 class = "title" id = "edit_label">Edit</h1>

    <form class="spot_form" id="edit_spot" onsubmit="return onSpotUpdate(this)">

        <input id = "uid" name="uid" type="hidden"/>

        <%--<label for="region"><fmt:message bundle="${loc}" key="locale.title"/></label>--%>
        <input class="region" id = "region" type="text" name="region" placeholder="region"/>

        <%--<label for="city"><fmt:message bundle="${loc}" key="locale.type"/></label>--%>
        <input class="city" id = "city" type="text" name="city" placeholder="city"/>

        <%--<label for="street"><fmt:message bundle="${loc}" key="locale.season"/></label>--%>
		<input class="street" id = "street" type="text" name="street" placeholder="street"/>

        <%--<label for="region"><fmt:message bundle="${loc}" key="locale.title"/></label>--%>
        <input class="region" id = "region_ru" type="text" name="region_ru" placeholder="region_ru"/>

        <%--<label for="city"><fmt:message bundle="${loc}" key="locale.type"/></label>--%>
        <input class="city" id = "city_ru" type="text" name="city_ru" placeholder="city_ru"/>

        <%--<label for="street"><fmt:message bundle="${loc}" key="locale.season"/></label>--%>
        <input class="street" id = "street_ru" type="text" name="street_ru" placeholder="street_ru"/>

        <%--<label for="house"><fmt:message bundle="${loc}" key="locale.coast"/></label>--%>
        <input class="house" id = "house" type="text" name="house" placeholder="house" />

        <button type="submit" id = "submit_changes" class="btn ok">
            <fmt:message bundle="${loc}" key="locale.submit"/>
        </button>
        <button type="button" id="cancel_changes" class="btn cancel" onclick="closeEditForm()">
            <fmt:message bundle="${loc}" key="locale.cancel"/>
        </button>

        <div class = "error">
            <br><p id = "fail_msg" hidden><fmt:message bundle="${loc}" key="locale.insert.success"/></p>
            <br><p id = "success_msg" hidden><fmt:message bundle="${loc}" key="locale.insert.fail"/></p>
        </div>
    </form>
</div>




