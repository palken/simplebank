<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1><c:out value="Create a new account"></c:out></h1>

<h1> <c:out value="${error_in_new_account}"></c:out> </h1>
<form name="createAccount" action="CreateAccount" method="POST">
    <table>
        
        <tr>
            <td>Account Name</td>
            <td><input type="text" name="accountName" /></td>
        </tr>

        <tr>
            <td>Account Type</td>
            <td><input type="radio" name="accountType" value="Normal" /> Normal</td>
            <td><input type="radio" name="accountType" value="Credit" /> Credit</td>
        </tr>
        
        <tr>
            <td>Money amount</td>
            <td><input type="text" name="money"></td>
        </tr>
        
        <tr>
            <td><input type="submit" value="Submit"></td>
        </tr>

    </table>
</form>