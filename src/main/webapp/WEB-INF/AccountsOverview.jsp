<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>

<div id="content">

	<h1><c:out value="Overview of account for ${logged_in_user}"></c:out></h1>
	
	<table>
	  <tr>
	    <th>Account Name</th>
	    <th>Account Type</th>
	    <th>Money</th>
	  </tr>
	
		<c:forEach var="account" items="${accounts}">
		   <tr>
			   <td>${account.accountName}</td>
			   <td>${account.accountType}</td>
			   <td>${account.money}</td>
		   </tr>	
		</c:forEach>
	  
	</table>
</div>

<div id="sidebar">
	<a href="${pageContext.request.contextPath}/CreateAccount">Create a new Account</a>
	<a href="${pageContext.request.contextPath}/Transfer">Transfer money</a>
</div>

<%@include file="footer.jsp"%>