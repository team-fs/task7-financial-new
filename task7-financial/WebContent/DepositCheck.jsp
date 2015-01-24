<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<p style="font-size:medium">
    Deposit a Check
</p>

<jsp:include page="error-list.jsp" />

<p>
	<form method="post">
		<input type="hidden" name="redirect" value="${redirect}"/>
		<table>
			<tr>
				<td>Customer ID: </td>
				<td><input type="text" name="customerId" value="${form.customerId}"/></td>
			</tr>
			<tr>
				<td>Customer Name: </td>
				<td><input type="text" name="customerName" value="${form.customerName}"/></td>
			</tr>
			<tr>
				<td>Amount: </td>
				<td><input type="text" name="amount" value="${form.amount}"/></td>
			</tr>
			<tr>
				<td>Requested On: </td>
				<td><input type="text" name="amount" value="${form.requestDate}"/></td>
			</tr>
			<tr>
				<td>Deposited By:</td>
				<td><input type="text" name="employeeId" value="${form.employeeId}"/></td>
			</tr>
			<tr>
			  	<td><input type="checkbox"></td>
  				<td>I have read and agreed with the<a href="https://www.google.com/"></a> Terms of Service of CFS</td>
  			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="button" value="Deposit Check"/>
				</td>
			</tr>
		</table>
	</form>
</p>

<jsp:include page="footer.jsp" />
