<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.project.entities.UsersEM"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="image/car.ico" type="image/png" sizes="16x16">
<title>Welcome to Kriva Cabs</title>
<link href="https://fonts.googleapis.com/css?family=Merienda+One"
	rel="stylesheet">
<link href="css/index.css" rel="stylesheet">
<link href="css/modal.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<nav class="navbar navbar-default navbar-expand-xl navbar-light">
		<div class="navbar-header d-flex col">
			<a class="navbar-brand" href="#"><i class="fa fa-cab"></i><b>KRIVA
					CAB Ride's</b></a>
			<button type="button" data-target="#navbarCollapse"
				data-toggle="collapse" class="navbar-toggle navbar-toggler ml-auto">
				<span class="navbar-toggler-icon"></span> <span class="icon-bar"></span>
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
		</div>
		<!-- Collection of nav links, forms, and other content for toggling -->
		<div id="navbarCollapse"
			class="collapse navbar-collapse justify-content-start">
			<ul class="nav navbar-nav navbar-right ml-auto">
				<c:choose>
					<c:when test="${null == sessionScope.user}">
						<li class='nav-item'><a href='#' class='nav-link'
							onclick='openModalBox(this.name)' name='signIn'><i
								class='fa fa-sign-in'></i> Sign In</a></li>
						<li class='nav-item'><a href='#' class='nav-link'
							onclick='openModalBox(this.name)' name='signUp'><i
								class='fa fa-user-plus'></i> Create Account</a></li>
					</c:when>
					<c:when
						test="${(null != sessionScope.user) && (sessionScope.user.userType =='Customer')}">
						<li class='nav-item dropdown'><a href='#'
							data-toggle='dropdown'
							class='nav-link dropdown-toggle user-action'> <i
								class='fa fa-user'></i> ${sessionScope.user.userFname}
								${sessionScope.user.userLname} <b class='caret'></b>
						</a>
							<ul class='dropdown-menu'>
								<li><a href='#' class='dropdown-item'
									onclick='openModalBox(this.name)' name='updateProfile'><i
										class='fa fa-user-o'></i> Update Profile</a></li>
								<li><a href='#' class='dropdown-item'
									onclick='openModalBox(this.name)' name='changePassword'><i
										class='fa fa-key'></i> Change Password</a></li>
								<li><a href='#' class='dropdown-item'
									onclick='openModalBox(this.name)' name='feedback'><i
										class='fa fa-comments'></i> Feedback</a></li>
								<li><a href='#' class='dropdown-item'
									onclick='openModalBox(this.name)' name='complaint'><i
										class='fa fa-list-alt'></i> Complaint</a></li>
								<li class='divider dropdown-divider'></li>
								<li><a href='signOut.jsp' class='dropdown-item'><i
										class='material-icons'>&#xE8AC;</i> Logout</a></li>
							</ul></li>
					</c:when>
				</c:choose>
			</ul>
		</div>
	</nav>
	<span id="errorSpan"> <c:choose>
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
			<c:when test="${null != errorList}">
				<div class='alert alert-danger col-md-offset-3 col-md-6'>
					<c:forEach items="${errorList}" var="error">
						<li>${error}</li>
					</c:forEach>
				</div>
			</c:when>
		</c:choose>
	</span>
	<div id="simpleModal" class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<h4 id="modalHeaderText"></h4>
				<span id="closeBtn">&times;</span>
			</div>
			<div class="modal-body" id="modalBodyContent">
				<div id="login">
					<jsp:include page="html5/signIn.html"></jsp:include>
				</div>
				<div id="createAccount">
					<jsp:include page="html5/createAccount.html"></jsp:include>
				</div>
				<div id="forgetPassword">
					<jsp:include page="html5/forgetPassword.html"></jsp:include>
				</div>
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
				<div id="feedback">
					<jsp:include page="html5/feedback.jsp"></jsp:include>
				</div>
				<div id="complaint">
					<jsp:include page="html5/complaint.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-5">
				<form action="SearchNearByCab" method="POST" autocomplete="off">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-map-marker"
							aria-hidden="true"></i></span> <input type="text" class="form-control"
							placeholder="Source" name="from">
					</div>
					<br />
					<div class="input-group">
						<span class="input-group-addon"><i
							class="fa fa-location-arrow" aria-hidden="true"></i></span> <input
							type="text" class="form-control" placeholder="Destination"
							name="to">
					</div>
					<br />
					<div class="form-group" id="whenFormGrp">
						<select class="form-control" onchange="choseWhenToRide()"
							name="when" id="when">
							<option value="0">Select When To Ride</option>
							<option data-icon="glyphicon-heart" value="now">Ride Now</option>
							<option data-icon="fa fa-calendar" value="later">Schedule
								it for Later</option>
						</select>
					</div>
					<div class="form-group" id="selectDT">
						<div class="row">
							<div class=" col-md-6">
								<input type="date" class="form-control" name="futureDate"
									min='<%=LocalDate.now()%>'
									max='<%=LocalDate.now().plusDays(2)%>'>
							</div>
							<div class=" col-md-6">
								<input type="time" class="form-control" name="futureTime">
							</div>
						</div>
					</div>
					<div>
						<c:choose>
							<c:when test="${null == sessionScope.user}">
								<p class="col-md-12 bg-warning">* Please Login to our
									system, if you want to book a ride!! *</p>
							</c:when>
							<c:when
								test="${(null != sessionScope.user) && (sessionScope.user.userType =='Customer')}">
								<button class="btn-primary btn form-control" type="submit"> Search Cab Near You !!</button>
							</c:when>
						</c:choose>
					</div>
				</form>
			</div>
			<div class="col-md-7" id="map"></div>
		</div>
		<div id="cabNearYou"></div>
		<div id="testimonial-carousel"></div>
		<hr />
		<div class="row-fluid">
			<div class="span12">
				<div class="span8">
					Project Designed & Developed By <b>Ashish Tiwari</b>
				</div>
				<div class="span4">
					<p class="muted pull-right">2018 Shree Shankar Narayan College.
						All rights reserved</p>
				</div>
			</div>
		</div>
	</div>
	<script src="js/modal.js" type="text/javascript"></script>
	<script src="js/utility.js" type="text/javascript"></script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDUbzXEBibrrrB5o_iFSqCmBFnn8jiMRnQ&callback=initMap"></script>
	<script>
		function initMap() {
			navigator.geolocation.watchPosition(function(pos) {
				var map = new google.maps.Map(document.getElementById('map'), {
					zoom : 16,
					center : {
						lat : pos.coords.latitude,
						lng : pos.coords.longitude
					}
				});
				var marker = new google.maps.Marker({
					position : {
						lat : pos.coords.latitude,
						lng : pos.coords.longitude
					},
					map : map
				});
			});
		}
	</script>
	<script type="text/javascript">
		if (document.getElementById("errorSpan").innerHTML.trim() !== 0) {
			setTimeout(function() {
				document.getElementById("errorSpan").style.display = 'none';
			}, 3000)
		}
	</script>
</body>
</html>