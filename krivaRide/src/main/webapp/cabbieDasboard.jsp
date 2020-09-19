<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if
	test="${(null == sessionScope.user) && (sessionScope.user.userType !='Driver')}">
	<c:redirect url="signOut.jsp"></c:redirect>
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
</head>
<body>
	<nav
		class="navbar  navbar-expand-md navbar-expand-lg navbar-dark bg-secondary">
		<a class="navbar-brand" href="adminDashboard.jsp"> <i
			class="fas fa-taxi"></i> KRIVA CAB's <b>CABBIE DASHBOARD</b>
		</a>
		<ul class="nav navbar-nav ml-auto" style="margin-right: 5%;">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false"> <i class="fas fa-user-circle"></i>
					${sessionScope.user.userFname} ${sessionScope.user.userLname} <span
					class="caret"></span>
			</a>
				<ul class="dropdown-menu" style="margin-top: 20px;">
					<li><a href='#' class='dropdown-item'><i
							class='fas fa-edit'></i> Update Profile</a></li>
					<li><a href='#' class='dropdown-item'><i
							class='fas fa-key'></i> Change Password</a></li>
					<li class='divider dropdown-divider'></li>
					<li><a href='signOut.jsp' class='dropdown-item'><i
							class="fas fa-sign-out-alt"></i> Logout</a></li>
				</ul></li>
		</ul>
	</nav>
	<div id="simpleModal" class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<h4 id="modalHeaderText"></h4>
				<span id="closeBtn">&times;</span>
			</div>
			<div class="modal-body" id="modalBodyContent">
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
			</div>
	</div>
	<div class="container" style="margin-top: 4%">
		<div class="row">
			<div class="col-sm-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-user"></i> Customer's Details
						</h5>
						<p class="card-text">
							Total Number of customers present in our system: <b id="totcust"></b>
						</p>
						<a href="#" class="btn btn-primary col-sm-12" id="viewCustomers"
							onClick="openModalBox(this.id)">View Information</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-bookmark"></i> BooKing Information
						</h5>
						<p class="card-text">
							Total number of cab booked in our system: <b id="totCabBook"></b>
						</p>
						<a href="#" class="btn btn-primary col-sm-12">View Cab Booking
							Details</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-ban"></i> Cab Cancellation
						</h5>
						<p class="card-text">
							Total number of Cancellation happened once booking is done : <b
								id="totCabCancel"></b>
						</p>
						<a href="#" class="btn btn-primary col-sm-12">Cancellation
							Information</a>
					</div>
				</div>
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-sm-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-car"></i> Cab's Management
						</h5>
						<p class="card-text">
							Total number of Cab registered in our system: <b id="totcab"></b>
						</p>
						<a href="#" class="btn btn-primary">View Cab Details</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-comment-dots"></i> Feedbacks & Complaint's
						</h5>
						<p class="card-text">
							Feedback Count: <b id="totFeedback"></b>, & Complaint's Count:<b
								id="totComplaint"></b>
						</p>
						<div class="row">
							<div class="col-sm-6">
								<a href="#" class="btn btn-primary" onclick="openModalBox(this.id)" id="viewFeedbacks" >View Feedback's</a>
							</div>
							<div class="col-sm-6">
								<a href="#" class="btn btn-primary" onclick="openModalBox(this.id)" id="viewComplaints" >View Complaint's</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-motorcycle"></i> Cab Captain Information
						</h5>
						<p class="card-text">
							Total number of captain riding cabs are : <b id="totCabcapt"></b>
						</p>
						<a href="#" class="btn btn-primary">Captain Information</a>
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
</body>
</html>