<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>

<div id="content">
	<h2>Hello and welcome to our fantastic bank, SimpleBank! </h2>
	
	<p>
	    <a href="${pageContext.request.contextPath}/Login">Login</a>
	</p>
	

</div>
<div id="sidebar">
	<p>
	    New user, register <a href="${pageContext.request.contextPath}/Register">here</a>
	</p>
</div>
<%@include file="footer.jsp" %>