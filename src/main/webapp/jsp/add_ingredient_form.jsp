<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;
    charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<link href="../css/add_ingr_popup_style.css" rel="stylesheet" type="text/css">
<link rel='stylesheet' href='../css/ingredient_form_style.css' type='text/css' media='all'/>
<%--<script type="text/javascript" src="../js/ingredient_management.js"></script>--%>'
<script src="http://code.jquery.com/jquery-2.2.4.js"
        type="text/javascript"></script>
<script type="text/javascript" src="../js/scripts.js"></script>

<fmt:setLocale value = "${sessionScope.locale == null ? 'en' : sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>

<div class="add_ingredient_popup" id = "add_ingredient_popup">
  <h1>Edit</h1>
    <form class="add_ingredient" id="add_ingredient" onsubmit="return onAddIngredient(this)">

        <label for="ingr_title">Title</label>
        <input id = "ingr_title" type="text" name="title" placeholder="ingr_title" required/>
        <br>
        <input id = "new_img_path" type="hidden" name="img_path" placeholder="img_path" required/>

        <label for="ingr_type">Ingredient type</label>
        <select name="ingr_type" id="ingr_type" required>
            <c:forEach items="${requestScope.ingredient_types}" var="n">
                <option value="${n}">${n}</option>
            </c:forEach>
        </select>

        <br>
        <label for="ingr_season_type">For season</label>
        <select name="season_type" id="ingr_season_type" required>
            <c:forEach items="${requestScope.season_types}" var="n">
                <option value="${n}">${n}</option>
            </c:forEach>
        </select>

        <br>
        <label for="ingr_price">Price</label>
        <input id = "ingr_price" type="number" step="0.01" name="price" placeholder="price" required/>

        <input id = "ingr_quantity" type="hidden" name="quantity" placeholder="quantity" value="0"/>

        <button type="submit" id = "submit_changes" class="btn ok">Submit</button>
        <button type="button" id="cancel_changes" class="btn cancel" onclick="closeAddForm()">Cancel</button>
        <div class = "error">
            <%--<br><p id = "error">${param.message}</p>--%>
        </div>
    </form>

    <form id="upload_ingredient_img" onsubmit="return uploadNewFile(this)">
        <label for="ingr_img"><fmt:message bundle="${loc}" key="locale.img.source"/></label>
        <input id = "ingr_img" type="file" name="img" placeholder="image" />
        <button type="submit" id = "upload_img" class="btn ok">
            <fmt:message bundle="${loc}" key="locale.upload"/>
        </button>
        <img src="" alt="new_img_preview" id = "new_uploaded_img_preview" hidden>
    </form>
</div>




