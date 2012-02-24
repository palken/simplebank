<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>

<div id="content">

<form name="input" action="Register" method="POST">
    <table>

        <tr>
            <td>Username</td>
            <td><input type="text" name="username"></td>
        </tr>

        <tr>
            <td>Password</td>
            <td><input type="password" name="password"></td>
        </tr>
        
        <tr>
            <td>First Name</td>
            <td><input type="text" name="first_name"></td>
        </tr>
        
        <tr>
            <td>Surname</td>
            <td><input type="text" name="surname"></td>
        </tr>
        
        <tr>
            <td>SomethingSecret</td>
            <td><input type="text" name="somethingSecret"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"></td>
        </tr>

    </table>
</form>

</div>

<%@include file="footer.jsp" %>