<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		</tr>
		<tr>
			<td><input type="submit" value="Submit"></td>
		</tr>

	</table>
</form>