<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1><c:out value="Overview of account for ${logged_in_user}"></c:out></h1>

<table>
  <tr>
    <th>Account Name</th>
    <th>Account Type</th>
    <th>Money</th>
  </tr>

	<c:forEach var="account" items="${accounts}">
	   <tr>
		   <td><c:out value="${account.accountName}"></c:out>
		   <td><c:out value="${account.accountType}"></c:out>
		   <td><c:out value="${account.money}"></c:out>
	   </tr>	
	</c:forEach>
  
</table>

<a href="${pageContext.request.contextPath}/CreateAccount">Create a new Account</a>