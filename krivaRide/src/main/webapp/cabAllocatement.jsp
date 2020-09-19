<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <c:if test="${(null == sessionScope.user) && (sessionScope.user.userType !='Admin')}">
	<c:redirect url="signOut.jsp"></c:redirect>
</c:if> --%>
<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/jqueryDT.min.css">
<link rel="icon" href="image/car.ico" type="image/png" sizes="16x16">
<title>Welcome to Kriva Cabs</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script defer
	src="https://use.fontawesome.com/releases/v5.0.9/js/all.js"></script>
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/modal.css">
</head>

<body>
	<nav
		class="navbar  navbar-expand-md navbar-expand-lg navbar-dark bg-secondary">
		<a class="navbar-brand" href="adminDashboard.jsp"> <i
			class="fas fa-taxi"></i> KRIVA CAB's <b>ADMIN DASHBOARD</b>
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-item nav-link" href="adminDashboard.jsp"><i
					class="fas fa-home"></i> Home<span class="sr-only">(current)</span></a>
				<a class="nav-item nav-link" href="addCabInfo.jsp"><i
					class="far fa-plus-square"></i> Cab</a> <a class="nav-item nav-link"
					href="addDriver.jsp"><i class="far fa-address-card"></i> Driver</a>
				<a class="nav-item nav-link active" href="cabAllocatement.jsp"><i
					class="fas fa-adjust"></i> Allotment</a> <a class="nav-item nav-link"
					href="addConstants.jsp"><i class="far fa-list-alt"></i>
					Constants</a>
			</div>
			<ul class="nav navbar-nav ml-auto" style="margin-right: 5%;">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> <i class="fas fa-user-circle"></i>
						${sessionScope.user.userFname} ${sessionScope.user.userLname} <span
						class="caret"></span>
				</a>
					<ul class="dropdown-menu" style="margin-top: 20px;">
						<li><a href='#' class='dropdown-item'> <i
								class='fas fa-edit'></i> Update Profile
						</a></li>
						<li><a href='#' class='dropdown-item'> <i
								class='fas fa-key'></i> Change Password
						</a></li>
						<li class='divider dropdown-divider'></li>
						<li><a href='signOut.jsp' class='dropdown-item'> <i
								class="fas fa-sign-out-alt"></i> Logout
						</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
	<span id="errorSpan"> <c:choose>
			<c:when test="${null != errorList}">
				<div class='alert alert-danger col-md-offset-3 col-md-6'>
					<c:forEach items="${errorList}" var="error">
						<li>${error}</li>
					</c:forEach>
				</div>
			</c:when>
			<c:when test="${null != error}">
				<div class='alert alert-danger col-md-offset-3 col-md-6'>
					<li>${error}</li>
				</div>
			</c:when>
			<c:when test="${null != success}">
				<div class='alert alert-success col-md-offset-3 col-md-6'>
					<li>${success}</li>
				</div>
			</c:when>
		</c:choose>
	</span>
	<div class="container">
		<form action="SaveCabCaptainController" method="POST">
			<div class="row">
				<div class="col-md-12">
					<h3 class="text-left">Search Rider's those cab be allocated</h3>
					<br />
					<div class="col-md-12">
						<div class="row">
							<div class="col">
								<input type="email" id="mailId" class="form-control"
									placeholder="Search via Email ID">
							</div>
							<div class="col">
								<input type="text" class="form-control" id="mNo"
									placeholder="Search via Mobile Number" pattern="\d{10}">
							</div>
							<div class="col">
								<input type="button" class="btn btn-primary" value="Search"
									onclick="getRiders()">
							</div>
						</div>
					</div>
				</div>
			</div>
			<br />
			<div class="row">
				<div class="col-md-10">
					<input type="text" class="form-control" id="cabbieInfo" name="user" placeholder="Display search result....">
				</div>
			</div>
			<br />
			<div class="row">
				<div class="col-md-12">
					<h3 class="text-left">Assign a Cab Type for a Rider</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10">
					<Select name="cabType" class="form-control" id="cabType"
						onchange="getUnassignedCab()">
						<option>Select</option>
					</Select>
				</div>
			</div>
			<br />
			<div class="row">
				<div class="col-md-12">
					<h3 class="text-left">Allocate unassigned Cab to Rider</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10">
					<Select name="cabId" class="form-control" id="unassignedCab">
						<option>Select</option>
					</Select>
				</div>
			</div>
			<br/>
			<div class="row">
				<div class="col-md-12">
					<div class="col-md-5">
						<input type="submit" class="btn btn-primary" value="Assign Cab to Captain"> 
						<input type="reset" class="btn btn-warning" value="Reset"> 
					</div>
				</div>
			</div>
		</form>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/commonAjax.js"></script>
	<script type="text/javascript" src="js/modal.js"></script>
	<script type="text/javascript" src="js/riderTable.js"></script>
</body>

</html>