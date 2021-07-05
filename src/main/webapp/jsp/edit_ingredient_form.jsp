<%@ page language="java" contentType="text/html;
    charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<%--<script src="http://code.jquery.com/jquery-2.2.4.js"--%>
        <%--type="text/javascript"></script>--%>
<%--<script type="text/javascript" src="../js/scripts.js"></script>--%>
<%--<link href="../css/edit_ingr_popup_style.css" rel="stylesheet" type="text/css">--%>
<link rel='stylesheet' href='../css/ingredient_form_style.css' type='text/css' media='all'/>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/scripts.js"></script>
<script type="text/javascript" src="../js/ingredient_management.js"></script>

<fmt:setLocale value = "${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>

<div class="edit_ingredient_popup" id = "edit_ingredient_popup">
  <h1> <fmt:message bundle="${loc}" key="locale.edit.btn"/></h1>
    <form class="edit_ingredient" id="edit_ingredient" onsubmit="return onIngredientUpdate(this)">

        <input id = "original_ingr_title" name="orig_title" type="hidden"/>
        <input id = "img_path" type="hidden" name="img_path" placeholder="img_path"/>

        <label for="ingr_title"><fmt:message bundle="${loc}" key="locale.title"/></label>
        <input id = "ingr_title" type="text" name="title" placeholder="ingr_title"/>

        <label for="ingr_type">Ingredient type</label>
        <select name="ingr_type" id="ingr_type" required>
            <c:forEach items="${requestScope.ingredient_types}" var="n">
                <option value="${n}">${n}</option>
            </c:forEach>
        </select>

        <label for="ingr_season_type">For season</label>
        <select name="season_type" id="ingr_season_type" required>
            <c:forEach items="${requestScope.season_types}" var="n">
                <option value="${n}">${n}</option>
            </c:forEach>
        </select>

        <label for="ingr_price"><fmt:message bundle="${loc}" key="locale.coast"/></label>
		<input id = "ingr_price" type="number" step="0.01" name="price" placeholder="coast" />

        <input id = "ingr_quantity" type="hidden" name="quantity" placeholder="quantity" value="0"/>

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

    <form id="upload_ingredient_img" onsubmit="return uploadFile(this)">
        <label for="ingr_img"><fmt:message bundle="${loc}" key="locale.img.source"/></label>
        <input id = "ingr_img" type="file" name="img" placeholder="image" />
        <button type="submit" id = "upload_img" class="btn ok">
            <fmt:message bundle="${loc}" key="locale.upload"/>
        </button>
        <img src="" alt="img_preview" id = "uploaded_img_preview" hidden>
    </form>

</div>




