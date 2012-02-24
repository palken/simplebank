<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>

<div id="content">
	<c:forEach var="user" items="${users}">
	    <c:out value="${user.username}"></c:out>
	</c:forEach>
</div>

<%@include file="footer.jsp" %>