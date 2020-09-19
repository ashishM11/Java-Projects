<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if
	test="${(null == sessionScope.user) && (sessionScope.user.userType !='Admin')}">
	<c:redirect url="index.jsp"></c:redirect>
</c:if>
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
<link rel="stylesheet"
	href="https://formden.com/static/cdn/bootstrap-iso.css" />
<style>
.bootstrap-iso .formden_header h2, .bootstrap-iso .formden_header p,
	.bootstrap-iso form {
	font-family: Arial, Helvetica, sans-serif;
	color: black
}

.bootstrap-iso form button, .bootstrap-iso form button:hover {
	color: white !important;
}

.asteriskField {
	color: red;
}
</style>
</head>

<body onload="getCodeWithDesc('CabType')">
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
				<a class="nav-item nav-link active" href="addCabInfo.jsp"><i
					class="far fa-plus-square"></i> Cab</a> <a class="nav-item nav-link"
					href="addDriver.jsp"><i class="far fa-address-card"></i> Driver</a>
				<a class="nav-item nav-link" href="cabAllocatement.jsp"><i
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
			<c:when test="${null != success}">
				<div class='alert alert-success col-md-offset-3 col-md-6'>
					<li>${success}</li>
				</div>
			</c:when>
			<c:when test="${null != errorList}">
				<div class='alert alert-danger col-md-offset-3 col-md-6'>
					<c:forEach items="${errorList}" var="error">
						<li>${error}</li>
					</c:forEach>
				</div>
			</c:when>
		</c:choose>
	</span>
	<div class="container">
		<div class="bootstrap-iso">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-6 col-sm-6 col-xs-12">
						<form method="post" action="SaveCabInformation">
							<div class="form-group ">
								<label class="control-label requiredField" for="cabRNo">
									Cab Registeration Number <span class="asteriskField"> *
								</span>
								</label> <input class="form-control" id="cabRNo" name="cabRNo"
									placeholder="Unique Number" type="text" />
							</div>
							<div class="form-group ">
								<label class="control-label requiredField" for="cabType">
									Chose Cab Type <span class="asteriskField"> * </span>
								</label> <select class="select form-control" id="cabType" name="cabType" onchange="getCodeWithDesc('Cab')">
									<option value="Select">Select</option>
								</select>
							</div>
							<div class="form-group ">
								<label class="control-label requiredField" for="cabBrand">
									Select Cab Brand <span class="asteriskField"> * </span>
								</label> <select class="select form-control" id="cabBrand"
									name="cabBrand">
									<option value="Select">Select</option>
								</select>
							</div>
							<div class="form-group ">
								<label class="control-label requiredField" for="cabColor">
									Choose Color <span class="asteriskField"> * </span>
								</label> <input class="form-control" id="cabColor" name="cabColor"
									type="color" />
							</div>
							<div class="form-group" id="div_cabInsured">
								<label class="control-label requiredField" for="cabInsured">
									Is this Cab Insured, with any Insurance company? <span
									class="asteriskField"> * </span>
								</label>
								<div class="">
									<label class="radio-inline"> <input name="cabInsured"
										type="radio" value="Y" /> cab Insured
									</label>
								</div>
							</div>
							<div class="form-group" id="div_isCabActive">
								<label class="control-label requiredField" for="isCabActive">
									Is this Cab Active or Available for touring <span
									class="asteriskField"> * </span>
								</label>
								<div class="">
									<label class="radio-inline"> <input name="isCabActive"
										type="radio" value="Y" /> Cab is Active
									</label>
								</div>
							</div>
							<div class="form-group">
								<div>
									<button class="btn btn-primary" name="submit" type="submit">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/utility.js"></script>
	<script type="text/javascript" src="js/modal.js"></script>
	<script type="text/javascript" src="js/jqueryDT.min.js"></script>
	<script type="text/javascript">
		if (document.getElementById("errorSpan").innerHTML.trim() !== 0) {
			setTimeout(function() {
				document.getElementById("errorSpan").style.display = 'none';
			}, 3000)
		}
	</script>
</body>

</html>