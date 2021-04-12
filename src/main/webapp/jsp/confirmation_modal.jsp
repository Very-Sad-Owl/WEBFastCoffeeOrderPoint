<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 16.03.2021
  Time: 03:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="../js/ingredient_management.js"></script>
<link rel='stylesheet' href='../css/confirmation_modal_style.css' type='text/css' media='all'/>
<script type="text/javascript" src="../js/ingredient_management.js"></script>

<div id="confirmation_modal" class="modal">
    <form class="modal-content">
        <div class="container">
            <h1>Delete <span class = "toDelete">this</span></h1>
            <p>Are you sure you want to delete <span class = "toDelete">this</span>?</p>

            <div class="clearfix">
                <button type="button" id="cancelbtn">Cancel</button>
                <button type="button" id="deletebtn">Delete</button>
            </div>
        </div>
    </form>
</div>
