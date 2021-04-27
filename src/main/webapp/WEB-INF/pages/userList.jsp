<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html style="background-image: url('${pageContext.request.contextPath}/images/html-banner.jpg');background-size: cover;">
<head>
<meta charset="UTF-8">
<title>Product List</title>
 
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
 
   <fmt:setLocale value="en_US" scope="session"/>
  
   <div class="page-title">User List</div>
   
    <c:if test="${not empty message }">
     <div class="error-message">
         ${message}
     </div>
   </c:if>
 
   <div style="color:#87CEFA;">Total Order Count: ${paginationResult.totalRecords}</div>
  
   <table border="1" style="width:100%;color:white;">
       <tr>
           <th>First Name</th>
           <th>Last Name</th>
           <th>Email</th>
           <th>Contact</th>
           <th>Password</th>
           <th>CPassword</th>
           <th>Created Date</th>
       </tr>
       <c:forEach items="${paginationResult.list}" var="userInfo">
           <tr>
               <td>${userInfo.fname}</td>
               <td>${userInfo.lname}</td>
               <td><a style="color:#87CEFA;" href="${pageContext.request.contextPath}/UpdateUserList?email=${userInfo.email}">${userInfo.email}</a></td>
               <td>${userInfo.contact}</td>
               <td>${userInfo.passwd}</td>
               <td>${userInfo.cpasswd}</td>
               <td>
                  <fmt:formatDate value="${userInfo.createDate}" pattern="dd-MM-yyyy HH:mm"/>
               </td>
               <td><a style="color:#87CEFA;" href="${pageContext.request.contextPath}/deleteUserList?email=${userInfo.email}">Delete</a></td>
           </tr>
       </c:forEach>
   </table>
   <c:if test="${paginationResult.totalPages > 1}">
       <div class="page-navigator">
          <c:forEach items="${paginationResult.navigationPages}" var = "page">
              <c:if test="${page != -1 }">
                <a href="userList?page=${page}" class="nav-item">${page}</a>
              </c:if>
              <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
              </c:if>
          </c:forEach>
          
       </div>
   </c:if>
 
 
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>