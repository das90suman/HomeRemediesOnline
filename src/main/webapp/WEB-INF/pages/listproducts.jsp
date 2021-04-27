<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
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
  
   <div class="page-title">Product List</div>
   
    <c:if test="${not empty message }">
     <div class="error-message">
         ${message}
     </div>
   </c:if>
 
   <div style="color:#87CEFA;">Total Product Count: ${paginationProducts.totalRecords}</div>
   <table border="1" style="width:100%;color:white;">
       <tr>
           <th>Product Code</th>
           <th>Product Name</th>
           <th>Product Price</th>
           <th>Product Image</th>
           <th>Product Created Date</th>
       </tr>
       <c:forEach items="${paginationProducts.list}" var="prodInfo">
           <tr>
               <td>${prodInfo.code}</td>
               <td>${prodInfo.name}</td>
               <td>${prodInfo.price}</td>
               <td><img class="product-image"
                   src="${pageContext.request.contextPath}/productImage?code=${prodInfo.code}" /></td>
               <td>
                  <fmt:formatDate value="${prodInfo.createDate}" pattern="dd-MM-yyyy HH:mm"/>
               </td>
           </tr>
       </c:forEach>
   </table>
   <c:if test="${paginationProducts.totalPages > 1}">
       <div class="page-navigator">
          <c:forEach items="${paginationProducts.navigationPages}" var = "page">
              <c:if test="${page != -1 }">
                <a href="listproducts?page=${page}" class="nav-item">${page}</a>
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