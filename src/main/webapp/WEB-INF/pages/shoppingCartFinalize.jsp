<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html style="background-image: url('${pageContext.request.contextPath}/images/html-banner.jpg');background-size: cover;">
<script>
function onLoad(){
	
	    if ('<c:out value="${pageContext.request.userPrincipal.name}"/>' == '')
	    {
	    	window.location.href="login";
	    }
	    <%-- else
	    {
	    	<%
	    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	    	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	    	response.setDateHeader("Expires", 0);
	    %>
	    } --%>
	
}
</script>
<head>
<meta charset="UTF-8">
 
<title>Shopping Cart Finalize</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
</head>
<body style="background-image: url('${pageContext.request.contextPath}/images/aga-banner2.jpg');background-size: cover;" onload="onLoad()">
   <jsp:include page="_header.jsp" />
 
   <jsp:include page="_menu.jsp" />
 
   <!-- <div class="page-title">Finalize</div> -->
  
   <div class="container">
       <h2>Thank You For Placing Your Order !!</h2>
       Please visit the Order List to see your Orders
       <%-- Your order number is: ${lastOrderedCart.orderNum} --%>
   </div>
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>