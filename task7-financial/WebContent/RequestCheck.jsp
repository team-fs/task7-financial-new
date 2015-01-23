<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<p style="font-size:medium">
    Request a Check
</p>

<p>
<div id="requestForm">
	<table>
		<tr>
			<td>User Name:</td>
			<td>${username}</td>
		</tr>
		<tr>
			<td>Full Name:</td>
			<td>${firstName} ${lastName}</td>
		</tr>
		<tr>
			<td>Current Cash Balance:</td>
			<td>${cash}</td>
		</tr>
		<tr>
			<td>Amount:</td>
			<td><input type="text" name="amount"></td>
		</tr>
		<tr>
			<td>Notes:</td>
			<td><input type="text" name="notes"></td>
		</tr>
		<tr>
  			<td colspan="2"><input type="checkbox" align="center"> I have read and agreed with the<a href="https://www.google.com/"></a> Terms of Service of CFS</td>
  		</tr>
	</table>
</div>

<div>
	<table border="0" style="width:100%" id="checkConfirm" >
		<tr>
			<td><input type="button" style="width:60%" align="center" value="Cancel"></td>
			<td><input type="submit" style="width:60%" align="center" value="Confirm"></td>
		</tr>
	</table>
</div>

<jsp:include page="footer.jsp" />
