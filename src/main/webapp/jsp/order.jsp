<%@ page language="java" contentType="text/html;
    charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel='stylesheet' href='../css/order_style.css' type='text/css' media='all'/>

<fmt:setLocale value = "${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>

<div class="form-popup" id="orderForm">
  <form class="form-container" id = "form-container" onsubmit="return onConfirm(this)">
    <h1><fmt:message bundle="${loc}" key="locale.order"/></h1>
    <h2 id = "coffee_title">${requestScope.chosen.type}</h2>

    <div class = "pos_properties" id = "pos_properties">
      <%--content from openForm--%>
    </div>

    <div class = "size_list" id = "size_list">
       <%--content from openForm--%>
    </div>

    <div class="total_coast">
      <p><span id = "coast"></span>ml</p>
    </div>

    <%--<button type="submit" class="btn"><fmt:message bundle="${loc}" key="local.about"/></button>--%>
    <button type="submit" class="btn">Submit</button>
    <button type="button" class="btn cancel" onclick="closeForm()">Cancel</button>
  </form>
</div>
