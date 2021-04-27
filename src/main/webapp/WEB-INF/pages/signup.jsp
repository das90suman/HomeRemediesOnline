<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<!DOCTYPE html>
<html style="background-image: url('${pageContext.request.contextPath}/images/html-banner.jpg');background-size: cover;">

<head>
<meta charset="UTF-8">
<title>User Registration</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 <script type="text/javascript">
 function validateform(){
	 
	  var email;
	  var contact;
	  var passwd;
	  var cpasswd;
	  var text = null;
	  // Get the value of the input field with id="numb"
	  email = document.mySignUpform.email.value;
	  contact = document.mySignUpform.contact.value;
	  passwd = document.mySignUpform.passwd.value;
	  cpasswd = document.mySignUpform.cpasswd.value;
	  
	  var atposition=email.indexOf("@");  
	  var dotposition=email.lastIndexOf(".");  
	  if ((email !="") && (atposition<1 || dotposition<atposition+2 || dotposition+2>=email.length)){ 
		  text = "Please enter a valid e-mail address"; 
		  document.getElementById("eemail").innerHTML = text;
		  
		}
	  else{
		  document.getElementById("eemail").innerHTML = "";
	  }
	  
	  if ((contact !="") && (contact.length !=10)){ 
		  text = "Please enter a valid contact number"; 
		  document.getElementById("econtact").innerHTML = text;
		}
	  else{
		  document.getElementById("econtact").innerHTML = "";
	  }
	  
	  if ((passwd !="") && (cpasswd !="") && (passwd!=cpasswd)){ 
		  text = "Password and Confirm Password are not same"; 
		  document.getElementById("ecpasswd").innerHTML = text;
		  return false;
		}
	  else{
		  document.getElementById("ecpasswd").innerHTML = "";
	  }
	 
	  
 }
 
 </script>
 
</head>
<body style="background-image: url('${pageContext.request.contextPath}/images/aga-banner2.jpg');background-size: cover;">
 
   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
 
   <div class="page-title">User Registration</div>
  
   <c:if test="${not empty message }">
     <div class="error-message">
         ${message}
     </div>
   </c:if>
 
   <form:form name="mySignUpform" modelAttribute="signUpForm" method="POST" enctype="multipart/form-data">
       <table style="text-align:left;color:white;">
           <%-- <tr>
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
           </tr> --%>
 
           <tr>
               <td>First Name *</td>
               <td><form:input path="fname" required="required"/></td>
               <td><form:errors path="fname" class="error-message" /></p></td>
           </tr>
 
           <tr>
               <td>Last Name *</td>
               <td><form:input path="lname" required="required"/></td>
               <td><form:errors path="lname" class="error-message" /></p></td>
           </tr>
           <tr>
               <td>Email Id *</td>
               <td>
                  <c:if test="${not empty signUpForm.email}">
                       <form:hidden path="email"/>
                       ${signUpForm.email}
                  </c:if>
                  <c:if test="${empty signUpForm.email}">
                       <form:input path="email" required="required"/>
                  </c:if>
               </td>
               <td><form:errors path="email" class="error-message" /><p id="eemail" class="error-message" ></p></td>
           </tr>
           <tr>
               <td>Contact *</td>
               <td><form:input path="contact" required="required"/></td>
               <td><form:errors path="contact" class="error-message" /><p id="econtact" class="error-message" ></p></td>
           </tr>
           <tr>
               <td>Password *</td>
               <td><form:input path="passwd" type="password" required="required"/></td>
               <td><form:errors path="passwd" class="error-message" /></td>
           </tr>
           <tr>
               <td>Confirm Password *</td>
               <td><form:input path="cpasswd" type="password" required="required"/></td>
               <td><form:errors path="cpasswd" class="error-message" /><p id="ecpasswd" class="error-message" ></p></td>
           </tr>
           
 			<%-- <tr>
               <td>Product Code *</td>
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
           </tr> --%>
           <tr>
				<td>Captcha</td>
				<td>
					<img src="${pageContext.request.contextPath }/captcha">
					<br>
					<form:input type="text" path="captcha" required="required" style="margin-top: 5px;"/>
					<br>
					${error }
					<td><form:errors path="captcha" class="error-message" /></td>
				</td>
				</tr>
 
           <tr>
               <td>&nbsp;</td>
               <td><input type="submit" value="Submit" onclick="return validateform()" /> <input type="reset"
                   value="Reset" /></td>
           </tr>
       </table>
   </form:form>
 
 
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>