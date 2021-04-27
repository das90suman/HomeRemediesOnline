<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
 
<!DOCTYPE html>
<html style="background-image: url('${pageContext.request.contextPath}/images/html-banner.jpg');background-size: cover;">
<head>
<meta charset="UTF-8">
<title>Product List</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
<script src="${pageContext.request.contextPath}/core/jquery.1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/core/jquery.autocomplete.min.js"></script>
<link href="${pageContext.request.contextPath}/main.css" rel="stylesheet">
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
   
   <script>

$(document).ready(function() {
	$('#diseasename').autocomplete({
		serviceUrl: '${pageContext.request.contextPath}/diseaseList',
		paramName: "term",
		delimiter: ",",
	    transformResult: function(response) {
	    	//console.log('------------response Dat', response); 
	        return {
	        	
	            suggestions: $.map($.parseJSON(response), function(item) {
	            	//console.log('------------item Dat', item); 
		            	
		                return { value: item.dname, data: item.dname };
		            })
	            
	        };
	        
	    }
	});
});


</script>
  
   <fmt:setLocale value="en_US" scope="session"/>
 
   <div class="page-title">Product List</div>
 
 <form:form method="GET" modelAttribute="remediesForm"
       action="${pageContext.request.contextPath}/getRemedies">
       <table>
           <tr>
               <td style="width: 350px;"><input type="text" name = "diseasename" id = "diseasename" placeholder="Please enter diseases name,herbs name,fruits name" style="width: 350px;height: 20px;"></td>
                <td style="width: 130px;"><input type="submit" value="Submit" /> 
                <input type="reset" value="Reset" /></td>
           </tr>
  	  
   </table>
   <c:forEach items="${paginationremedialProducts.list}" var="prodInfo">
       <div class="product-preview-container">
           <ul>
           <c:choose>
           		<c:when test = "${fn:toLowerCase(prodInfo.dname) == fn:toLowerCase(disease)}">
          		<c:set var = "dname" scope = "session" value = "${prodInfo.dname}"/>
          		<c:set var = "dremedies" scope = "session" value = "${prodInfo.dremedies}"/>
          		</c:when>
          		<c:otherwise>
          		<li>Disease: ${prodInfo.dname}</li>
           	   	<li>Remedy: ${prodInfo.dremedies}</li>
          		</c:otherwise>
          	</c:choose>	
              <%-- <li>Disease: ${prodInfo.dname}</li>
           	   <li>Remedy: ${prodInfo.dremedies}</li> --%>
           	   
           	   <c:choose>
           		<c:when test = "${fn:toLowerCase(prodInfo.name) == fn:toLowerCase(disease)}">
          		<c:set var = "code" scope = "session" value = "${prodInfo.code}"/>
          		<c:set var = "name" scope = "session" value = "${prodInfo.name}"/>
          		<c:set var = "price" scope = "session" value = "${prodInfo.price}"/>
          		</c:when>
          		<c:otherwise>
          		<li><img class="product-image"
                   src="${pageContext.request.contextPath}/productImage?code=${prodInfo.code}" /></li>
               <li>Code: ${prodInfo.code}</li>
               <li>Name: ${prodInfo.name}</li>
               <li>Price: <fmt:formatNumber value="${prodInfo.price}" type="currency"/></li>
               <security:authorize  access="hasRole('ROLE_EMPLOYEE')">
               <li><a style="color:#87CEFA;"
                   href="${pageContext.request.contextPath}/buyProduct?code=${prodInfo.code}">
                       Buy Now</a></li>
               </security:authorize>        
               <!-- For Manager edit Product -->
               <security:authorize  access="hasRole('ROLE_MANAGER')">
                 <li><a style="color:#87CEFA;"
                     href="${pageContext.request.contextPath}/product?code=${prodInfo.code}">
                       Edit Product</a></li>
               </security:authorize>
          		
          		</c:otherwise>
          		</c:choose>
               
           </ul>
       </div>
 
   </c:forEach>
   <br/>
   <%-- disease: <c:out value = "${fn:toLowerCase(disease)}"/>
   dname: <c:out value = "${fn:toLowerCase(dname)}"/>
   pname: <c:out value = "${fn:toLowerCase(name)}"/>
   code: <c:out value = "${fn:toLowerCase(code)}"/> --%>
   <c:if test="${not empty disease}"> 
   <div class="product-preview-container">
   <ul>
   <c:choose>
    <c:when test = "${fn:toLowerCase(dname) != fn:toLowerCase(disease)}">
     <li><img class="product-image"
                   src="${pageContext.request.contextPath}/productImage?code=${code}" /></li>
               <li>Code: ${code}</li>
               <li>Name: ${name}</li>
               <li>Price: <fmt:formatNumber value="${price}" type="currency"/></li>
               <security:authorize  access="hasRole('ROLE_EMPLOYEE')">
               <li><a style="color:#87CEFA;"
                   href="${pageContext.request.contextPath}/buyProduct?code=${code}">
                       Buy Now</a></li>
               </security:authorize>        
               <!-- For Manager edit Product -->
               <security:authorize  access="hasRole('ROLE_MANAGER')">
                 <li><a style="color:#87CEFA;"
                     href="${pageContext.request.contextPath}/product?code=${code}">
                       Edit Product</a></li>
               </security:authorize>
    </c:when>
    <c:otherwise>
    <li>Disease: <c:out value = "${dname}"/></li>
    <li>Remedy: <c:out value = "${dremedies}"/></li>
    </c:otherwise>
    </c:choose>
      </ul>
  </div>
   </c:if>
  
 
 
   <c:if test="${paginationremedialProducts.totalPages > 1}">
       <div class="page-navigator">
          <c:forEach items="${paginationremedialProducts.navigationPages}" var = "page">
              <c:if test="${page != -1 }">
                <a href="productList?page=${page}" class="nav-item">${page}</a>
              </c:if>
              <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
              </c:if>
          </c:forEach>
          
       </div>
   </c:if>
  
 </form:form>
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>