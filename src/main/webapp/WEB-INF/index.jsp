<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>

<div id="content">
	<p>
	   This simple bank will securily let you store your money and other valuables.
	   
	   We use our trusted technology which is fully buzzword compliant to secure your data and perform safe transactions for everybody. Sign up today!
	</p>
	
	<p>
	    Please log in to administer your account. <a href="${pageContext.request.contextPath}/Login">Login</a>
	</p>
	

</div>
<div id="sidebar">
	<p>
	    New user, register <a href="${pageContext.request.contextPath}/Register">here</a>
	</p>
</div>
<%@include file="footer.jsp" %>