<%@page import="com.project.entities.UsersEM"%>
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
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/jqueryDT.min.css">
	<link rel="icon" href="image/car.ico" type="image/png" sizes="16x16">
	<title>Welcome to Kriva Cabs</title>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script defer src="https://use.fontawesome.com/releases/v5.0.9/js/all.js"></script>
	<link rel="stylesheet" href="css/admin.css">
	<link rel="stylesheet" href="css/newModal.css">
</head>

<body>
	<nav class="navbar  navbar-expand-md navbar-expand-lg navbar-dark bg-secondary">
		<a class="navbar-brand" href="adminDashboard.jsp"> <i
			class="fas fa-taxi"></i> KRIVA ADMIN DASHBOARD
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-item nav-link active" href="adminDashboard.jsp"><i class="fas fa-home"></i> Home<span class="sr-only">(current)</span></a>
				<a class="nav-item nav-link" href="addCabInfo.jsp"><i class="far fa-plus-square"></i> Cab</a>
				<a class="nav-item nav-link" href="addDriver.jsp"><i class="far fa-address-card"></i> Driver</a>
				<a class="nav-item nav-link" href="cabAllocatement.jsp"><i class="fas fa-adjust"></i> Allotment</a>
				<a class="nav-item nav-link" href="addConstants.jsp"><i class="far fa-list-alt"></i> Constants</a>
			</div>
			<ul class="nav navbar-nav ml-auto" style="margin-right: 5%;">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> 
								<i class="fas fa-user-circle"></i> ${sessionScope.user.userFname} ${sessionScope.user.userLname} 
								<span class="caret"></span>
						</a>
					<ul class="dropdown-menu" style="margin-top: 20px;">
						<li>
							<a href='#' class='dropdown-item' onclick='openModalBox(this.name)' name='updateProfile'>
									<i class='fas fa-edit'></i> Update Profile
								</a>
						</li>
						<li>
							<a href='#' class='dropdown-item' onclick='openModalBox(this.name)' name='changePassword'>
								<i class='fas fa-key'></i> Change Password
							</a>
						</li>
						<li class='divider dropdown-divider'></li>
						<li>
							<a href='signOut.jsp' class='dropdown-item'>
								<i class="fas fa-sign-out-alt"></i> Logout
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
	<div id="simpleModal" class="modal">
		<div class="modal-content" id="adminModalContent">
			<div class="modal-header">
				<h4 id="modalHeaderText"></h4>
				<span id="closeBtn">&times;</span>
			</div>
			<div class="modal-body" id="modalBodyContent">
				<c:if
					test="${(null != sessionScope.user) && (sessionScope.user.userType =='Admin')}">
					<div id="dynamicTableConent"></div>
					<div id="changePassword">
						<jsp:include page="html5/changePassword.jsp"></jsp:include>
					</div>
					<div id="updateProfile">
						<jsp:include page="html5/updateProfile.jsp">
							<jsp:param value="${sessionScope.user.userFname}" name="userFname"/>
							<jsp:param value="${sessionScope.user.userLname}" name="userLname"/>
							<jsp:param value="${sessionScope.user.userMailId}" name="userMailId"/>
							<jsp:param value="${sessionScope.user.userMobileNo}" name="userMobileNo"/>
						</jsp:include>
					</div>
				</c:if>
			</div>
			
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-md-3" id="cussys">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title text-center">
								<i class="fas fa-user"></i> Customer's Details
							</h5>
						<p class="card-text text-center">
							Number of customers present in our system: <b id="totcust"></b>
						</p>
						<a href="#" class="btn btn-primary col-md-12 col-sm-12" id="viewCustomers" onClick="openModalBox(this.id)">View Information</a>
					</div>
				</div>
			</div>
			<div class="col-sm-12 col-md-3" id="booksys">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title text-center">
								<i class="fas fa-bookmark"></i> BooKing Information
							</h5>
						<p class="card-text text-center">
							Number of cab booked in our system: <b id="totCabBook"></b>
						</p>
						<a href="#" class="btn btn-primary col-md-12 col-sm-12" onclick="openModalBox(this.id)" id="viewConfirmation">Cab Booked Details</a>
					</div>
				</div>
			</div>
			<div class="col-sm-12 col-md-3" id="cancelsys">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title text-center">
								<i class="fas fa-ban "></i> Cab Cancellation
							</h5>
						<p class="card-text text-center">
							Number of Cancellation after booking: <b id="totCabCancel"></b>
						</p>
						<a href="#" class="btn btn-primary col-md-12 col-sm-12">Cancellation
								Information</a>
					</div>
				</div>
				</div>
			<div class="col-sm-12 col-md-3" id="cabmngsys">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title text-center">
								<i class="fas fa-car"></i> Cab's Management
							</h5>
						<p class="card-text text-center">
							Number of Cab registered in system: <b id="totcab"></b>
						</p>
						<a href="#" class="btn btn-primary col-md-12 col-sm-12" onclick="openModalBox(this.id)" id="viewCabs">Cab Details</a>
					</div>
				</div>
			</div>
		</div>			
		<br />
		<div class="row">
			<div class="col-sm-12 col-md-3">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title text-center">
								<i class="fas fa-comment-dots"></i> Feedback's
						</h5>
						<p class="card-text text-center">
							Feedback's Received : <b id="totFeedback"></b>
						</p>
						<a href="#" class="btn btn-primary col-md-12 col-sm-12"  onclick="openModalBox(this.id)" id="viewFeedbacks">View
									Feedback's</a>
					</div>
				</div>
			</div>
			<div class="col-sm-12 col-md-3">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title text-center">
								<i class="fas fa-comment-slash"></i> Complaint's
						</h5>
						<p class="card-text text-center">
							Complaint's Counts : <b id="totComplaint"></b>
						</p>
						<a href="#" class="btn btn-primary col-md-12 col-sm-12" onclick="openModalBox(this.id)" id="viewComplaints">View
									Complaint's</a>
					</div>
				</div>
			</div>
			<div class="col-sm-12 col-md-3">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title text-center">
							<i class="far fa-address-card"></i> Driver Information
							</h5>
						<p class="card-text text-center">
							Number of Driver's: <b id="totCabcapt"></b>
						</p>
						<a href="#" class="btn btn-primary col-md-12 col-sm-12" id="viewDrivers" onClick="openModalBox(this.id)">Captain Information</a>
					</div>
				</div>
			</div>
			<div class="col-sm-12 col-md-3">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title text-center">
							<i class="far fa-list-alt"></i> Constant's
							</h5>
						<p class="card-text text-center">
							Checkout All Constant's: <b id="totConst"></b>
						</p>
						<a href="#"  class="btn btn-primary col-md-12 col-sm-12" onclick="openModalBox(this.id)" id="viewConstants">Constant Info</a>
					</div>
				</div>
			</div>
		</div>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/commonAjax.js"></script>
	<script type="text/javascript" src="js/modal.js"></script>
	<script type="text/javascript" src="js/jqueryDT.min.js"></script>
</body>
</html>