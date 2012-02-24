<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>

<div id="content">
<form name="input" action="Login" method="POST">
	<table>
        
        <c:if test="${login_failed}">
            <c:out value="Wrong username and/or password"></c:out>
        </c:if>
        
		<tr>
			<td>Login</td>
			<td><input type="text" name="username"></td>
		</tr>

		<tr>
			<td>Password</td>
			<td><input type="password" name="password"></td>
		    <input type="hidden" name="next" value="<%= request.getParameter("next") %>" />
		</tr>
		
		<tr>
			<td><input type="submit" value="Submit"></td>
		</tr>

	</table>
</form>
</div>

<%@include file="footer.jsp" %>