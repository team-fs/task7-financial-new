<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="databeans.TransactionBean" %>
<%@ page import="databeans.CustomerBean" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
 <link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/apple-touch-icon-144-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/apple-touch-icon-114-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/apple-touch-icon-72-precomposed.png">
  <link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-57-precomposed.png">
  <link rel="shortcut icon" href="img/favicon.png">
  
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/scripts.js"></script>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<ul class="breadcrumb">
				<li>
					<a href="#">Home</a> <span class="divider">/</span>
				</li>
				
				<li class="active">
					View Account
				</li>
			</ul>
			<!-- Choose a certain customer -->
			<div class="btn-group">
				 <button class="btn btn-default">Choose a customer</button>
				  <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
				<ul class="dropdown-menu">
				<c:forEach var="cus" items="${cusList}">
					<li>
						<a href="viewByEmployee.do?customer=${cus.getCustomerId }">${cus.getUsername }</a>
					</li>
				</c:forEach>
				</ul>
			</div>
			<!-- End of choosing -->
			
			 
			<c:set var="customer" value="${customer}" />
			<c:choose>
                    <c:when test="${customer==null}">
                        <br/>
				    </c:when>
				    <c:otherwise> 
				    <h2>    ${customer.getUsername()} </h2><br/>
				    <div class="tabbable" id="tabs-813699">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel-179879" data-toggle="tab">Account Information</a>
					</li>
					<li>
						<a href="#panel-305422" data-toggle="tab">Fund Information</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-179879">
						<table class="table">
				
				<tbody>
					<tr>
						<td>
							First Name
						</td>
						<td>
							${customer.getFName()}
						</td>
					</tr>
					<tr>
						<td>
							Last Name
						</td>
						<td>
							${customer.getLName()} 
						</td>
					</tr>
					<tr class="active">
						<td>
							Address
						</td>
						<td>
							${customer.addrL1} ${user.addrL2()}  
						</td>
					</tr>
					<tr >
						<td>
							Cash Balance ($)
						</td>
						<td>
							${customer.cash}
						</td>
					</tr>
					<tr class="active">
						<c:set var="transaction" value="${transaction}" />
						<td>
							${transaction.execute_date }
						</td>
						<td>
							
						</td>
					</tr>
				</tbody>
			</table>
					</div>
					<div class="tab-pane" id="panel-305422">
						<table class="table">
				<thead>
					<tr>
						<th>
							#
						</th>
						<th>
							Fund ID
						</th>
						<th>
							Name
						</th>
						<th>
							Number of shares
						</th>
						<th>
							Position Value ($)
						</th>
					</tr>
				</thead>
				<tbody>
				<c:set var="count" value="1" />
				<c:forEach var="position" items="${positions}" >
				<c:set var="count" value="${ count }" />
					<tr>
						<td>
							${count}
						</td>
						<td>
							${position.fund_id }
						</td>
						<td>
							Public Asia Market
						</td>
						<td>
							${position.shares }
						</td>
						<td>
							${position.shares } * ${fund.price }
						</td>
					</tr>
				</c:forEach>			
				</tbody>
			</table>
					</div>
				</div>
			</div>
				    </c:otherwise>
                </c:choose>
			
			
			
		</div>
	</div>
</div>
</body>
</html>