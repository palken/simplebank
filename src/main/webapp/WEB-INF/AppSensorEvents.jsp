<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>

<div id="content">

	<h1><c:out value="All the AppSensor Events fired"></c:out></h1>

	<table>
	  <tr>
	    <th>Event Code</th>
	    <th>Event log message</th>
	  </tr>

		<c:forEach var="intrusion" items="${intrusions}">
		   <tr>
			   <td><c:out value="${intrusion.eventCode}"/></td>
			   <td><c:out value="${intrusion.securityExceptionLogMessage}"/></td>
		   </tr>
		</c:forEach>

	</table>
</div>

<%@include file="footer.jsp"%>