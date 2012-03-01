<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp"%>

<div id="content">

	<table>
		<tr>
			<th>Username:</th>
			<th>First Name:</th>
			<th>Last Name:</th>
			<th>Something Private:</th>
		</tr>

		<c:forEach var="user" items="${users}">
			<tr>
				<td><c:out value="${user.username}"></c:out></td>
				<td><c:out value="${user.first_name}"></c:out></td>
				<td><c:out value="${user.surname}"></c:out></td>
				<td><c:out value="${user.somethingPrivate}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</div>

<%@include file="footer.jsp"%>