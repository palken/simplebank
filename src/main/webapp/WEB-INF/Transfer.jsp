<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>

<div id="content">
<c:out value="${Errors}"></c:out>
<form name="input" action="Transfer" method="POST">
	<fieldset>
		<legend>From:</legend>
		<table>
			<tr>
				<td>Accounts:</td>
				<td><select name="fromAccount">
						<c:forEach var="account" items="${currentlyLoggedInUserAccounts}">
							<option value='<c:out value="${account.accountId}"></c:out>'>
								<c:out value="${account.accountName}(${account.accountId})"></c:out>
							</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td>Amount:</td>

				<td><input type="text" name="amount" /></td>
			</tr>
		</table>
	</fieldset>

	<fieldset>
	   <legend>To:</legend>
		<table>
			<tr>
				<td>To Accountnumber:</td>
				<td><input type="text" name="toAccount"></td>
			</tr>
		</table>
	</fieldset>
	
    <input type="submit" value="Submit">

</form>

</div>
<%@include file="footer.jsp" %>