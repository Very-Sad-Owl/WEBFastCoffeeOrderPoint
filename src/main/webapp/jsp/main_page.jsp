<%@ page language="java" contentType="text/html;
    charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script src="http://code.jquery.com/jquery-2.2.4.js"
		type="text/javascript"></script>
<script type="text/javascript" src="../js/scripts.js"></script>
<script src="../js/order_popup.js"></script>
<link rel='stylesheet' href='../css/main_style.css' type='text/css' media='all'/>
<link rel='stylesheet' href='../css/order_style.css' type='text/css' media='all'/>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Oswald:400,500,700%7CRoboto:400,500,700%7CHerr+Von+Muellerhoff:400,500,700%7CQuattrocento+Sans:400,500,700' type='text/css' media='all'/>
<!-- <link rel='stylesheet' href='css/easy-responsive-shortcodes.css' type='text/css' media='all'/> -->

<fmt:setLocale value = "${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>

<html lang="en-US">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>GoDot</title>
	</head>
	
	
<body class="home page page-template page-template-template-portfolio page-template-template-portfolio-php">

<jsp:include page="order.jsp" />

<%--<c:if test="${param.message != null}">--%>
	<%--<fmt:message bundle="${loc}" key="${param.message}"/>--%>
<%--</c:if>--%>

<c:if test="${param.message != null}">
	<script>alert(<fmt:message bundle="${loc}" key="${param.message}"/>)</script>
</c:if>

<c:if test="${requestScope.chosen != null}">
	<script>openForm()</script>
</c:if>

<div id="page">
	<div class="container">
		<jsp:include page="header.jsp"/>

		<div class = "coffee_list">
			<div class="coffee_type_el">
				<c:forEach var="n" items="${sessionScope.coffee}" varStatus="loop">
					<section class="coffee_element" id = "${loop.index}">
						<c:if test="${sessionScope.auth == true}">
							<a class="open-button" onclick="openForm(${loop.index})">
						</c:if>
						<c:if test="${sessionScope.auth != true}">
							<a class="open-button" href="Controller?command=gotologinationpage">
						</c:if>
								<img src="${'../'}${n.imgPath}" alt="${n.type}"
							style="width:100px;height:100px;">
						<header>
							<p><c:out value="${n.type}${' - '}${n.coast}" /></p>
						</header>
						</a>
					</section>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- .container -->
	<jsp:include page="footer.jsp"/>
	<a href="#top" class="smoothup" title="Back to top"><span class="genericon genericon-collapse"></span></a>
</div>
<!-- #page -->
<script src='../js/jquery.js'></script>
<script src='../js/plugins.js'></script>
<script src='../js/scripts.js'></script>
<script src='../js/masonry.pkgd.min.js'></script>
</body>
</html>