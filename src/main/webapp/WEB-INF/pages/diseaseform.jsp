<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<!DOCTYPE html>
<html style="background-image: url('${pageContext.request.contextPath}/images/html-banner.jpg');background-size: cover;">
<head>
<meta charset="UTF-8">
<title>Disease</title>
 
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
 
   <div class="page-title">Disease</div>
  
   <c:if test="${not empty message }">
     <div class="error-message">
         ${message}
     </div>
   </c:if>
 
   <form:form modelAttribute="diseaseDetailsForm" method="POST" enctype="multipart/form-data">
       <table style="text-align:left;color:white;">
           <tr>
               <td>Code *</td>
               <td>
                  <c:if test="${not empty diseaseDetailsForm.code}">
                       <form:hidden path="code"/>
                       ${diseaseDetailsForm.code}
                  </c:if>
                  <c:if test="${empty diseaseDetailsForm.code}">
                       <form:input path="code" />
                       <form:hidden path="newProduct" />
                  </c:if>
               </td>
               <td><form:errors path="code" class="error-message" /></td>
           </tr>
 
           <tr>
               <td>Name *</td>
               <td><form:input path="name" /></td>
               <td><form:errors path="name" class="error-message" /></td>
           </tr>
 
           <tr>
               <td>Remedies *</td>
               <td><form:input path="remedies" /></td>
               <td><form:errors path="remedies" class="error-message" /></td>
           </tr>
           
 			<tr>
               <td>Product Code *</td>
                <%-- <td><form:input path="pcode" /></td>
               	<td><form:errors path="pcode" class="error-message" /></td> --%>
                <%-- <td><select name='pcode'>
			    <option value="${selected}" selected>${selected}</option>
			    <c:forEach items="${diseaseDetailsForm.product}" var="prodList">
			        <c:if test="${prodList.code != selected}">
			            <option value="${prodList.code}">${prodList.code}</option>
			        </c:if>
			       
			    </c:forEach>
			   </select></td> --%>
                <td>
                <c:if test="${empty diseaseDetailsForm.pcode}">
                <form:select path="pcode">
                      <form:option value="NONE" label="--- Select ---" />
                      <form:options items="${diseaseDetailsForm.product}" />
                       </form:select>
                </c:if>
               <c:if test="${not empty diseaseDetailsForm.pcode}">
                       <form:hidden path="pcode"/>
                       ${diseaseDetailsForm.pcode}
                  </c:if>
                  </td>
                <td><form:errors path="pcode" class="error-message"  /></td>  
           </tr>
 
           <tr>
               <td>&nbsp;</td>
               <td><input type="submit" value="Submit" /> <input type="reset"
                   value="Reset" /></td>
           </tr>
       </table>
   </form:form>
 
 
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>