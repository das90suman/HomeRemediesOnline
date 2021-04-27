<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ page import="java.util.*, java.text.*,java.io.*" %>
 
<!DOCTYPE html>
<html style="background-image: url('${pageContext.request.contextPath}/images/html-banner.jpg');background-size: cover;">
<head>
<meta charset="UTF-8">
 
<title>Account Info</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
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
 
</head>
<body style="background-image: url('${pageContext.request.contextPath}/images/aga-banner2.jpg');background-size: cover;" onload="onLoad()">
 
 
   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
   <c:set var="email" value="${pageContext.request.userPrincipal.name}" />
   <c:set var="username1" value="${fn:split(email, '@')}" />
   <c:set var="username2" value="${fn:split(username1[0], '.')}" />
   <div class="page-title">Welcome ${fn:toUpperCase(fn:substring(username2[0], 0, 1))}${fn:toLowerCase(fn:substring(username2[0], 1,fn:length(username2[0])))} !!</div>

 
   <div class="account-container">
 
 
       <ul style="font-size: 100%;color:white;font-weight: bold;">
           <%-- <li>Your User Name is ${pageContext.request.userPrincipal.name}</li> --%>
           <li>Role:
               <ul>
                   <c:forEach items="${userDetails.authorities}" var="auth">
                   <c:choose>
                   	<c:when test="${auth.authority == 'ROLE_MANAGER'}">
                   	<li>You have currently logged in as an ADMIN</li>
                   	</c:when>
                   	<c:otherwise>
                   	<li>You have currently logged in as an USER</li>
                   	</c:otherwise>
                   </c:choose>
                   </c:forEach>
               </ul>
           </li>
       </ul>
   </div>
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>