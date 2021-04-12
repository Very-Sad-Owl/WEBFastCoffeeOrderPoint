<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;
    charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<link href="../css/edit_ingr_popup_style.css" rel="stylesheet" type="text/css">
<link rel='stylesheet' href='../css/ingredient_form_style.css' type='text/css' media='all'/>
<%--<script type="text/javascript" src="../js/ingredient_management.js"></script>--%>'
<script src="http://code.jquery.com/jquery-2.2.4.js"
        type="text/javascript"></script>
<script type="text/javascript" src="../js/scripts.js"></script>

<div class="add_ingredient_popup" id = "add_ingredient_popup">
  <h1>Edit</h1>
    <form class="add_ingredient" id="add_ingredient" onsubmit="return onAddIngredient(this)" method="post">

        <label for="ingr_title">Title</label>
        <input id = "ingr_title" type="text" name="title" placeholder="ingr_title" required/>

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

        <label for="ingr_price">Price</label>
        <input id = "ingr_price" type="number" step="0.01" name="coast" placeholder="coast" required/>

        <input id = "ingr_quantity" type="hidden" name="quantity" placeholder="quantity" value="0"/>

        <label for="ingr_img">Image source</label>
        <input id = "ingr_img" type="text" name="img" placeholder="image" required/>

        <button type="submit" id = "submit_changes" class="btn ok">Submit</button>
        <button type="button" id="cancel_changes" class="btn cancel" onclick="closeAddForm()">Cancel</button>
        <div class = "error">
            <%--<br><p id = "error">${param.message}</p>--%>
        </div>
    </form>
</div>




