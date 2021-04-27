<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>   
 
 
<div class="menu-container">
  
   <a href="${pageContext.request.contextPath}/accountInfo">Home</a>
   <security:authorize  access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
   <a style="color:#87CEFA;">|</a>
   <a href="${pageContext.request.contextPath}/productList">
      Product List
   </a>
   <a style="color:#87CEFA;">|</a>
   </security:authorize>
   <security:authorize  access="hasRole('ROLE_EMPLOYEE')">
   <a href="${pageContext.request.contextPath}/shoppingCart">
      My Cart
   </a>
  <a style="color:#87CEFA;">|</a>
   </security:authorize>
   <security:authorize  access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
     <a href="${pageContext.request.contextPath}/orderList">
         Order List
     </a>
     <a style="color:#87CEFA;">|</a>
   </security:authorize>
   
   <security:authorize  access="hasRole('ROLE_MANAGER')">
         <a href="${pageContext.request.contextPath}/product">
                        Create Product
         </a>
         <a style="color:#87CEFA;">|</a>
   </security:authorize>
   <security:authorize  access="hasRole('ROLE_MANAGER')">
         <a href="${pageContext.request.contextPath}/listproducts">
                        Products
         </a>
         <a style="color:#87CEFA;">|</a>
   </security:authorize>
   <security:authorize  access="hasRole('ROLE_MANAGER')">
         <a href="${pageContext.request.contextPath}/diseaseform">
                        Create Disease
         </a>
         <a style="color:#87CEFA;">|</a>
   </security:authorize>
   <security:authorize  access="hasRole('ROLE_MANAGER')">
         <a href="${pageContext.request.contextPath}/listdiseases">
                       Diseases
         </a>
         <a style="color:#87CEFA;">|</a>
   </security:authorize>
   <security:authorize  access="hasRole('ROLE_MANAGER')">
         <a href="${pageContext.request.contextPath}/userList">
                        Edit User
         </a>
         
   </security:authorize>
  
</div>