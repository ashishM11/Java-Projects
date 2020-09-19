<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when
		test="${(null == sessionScope.user) || (empty bookingInfo) }">
		<c:redirect url="signOut.jsp"></c:redirect>
	</c:when>
</c:choose>
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
<script type="text/javascript" src="js/utility.js"></script>

</head>

<body onload="getCodeWithDesc('CabType')">
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
				<c:if
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
				</c:if>
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
				<div id="changePassword">
					<jsp:include page="html5/changePassword.jsp"></jsp:include>
				</div>
				<div id="updateProfile">
					<jsp:include page="html5/updateProfile.jsp">
						<jsp:param value="${sessionScope.user.userFname}" name="userFname" />
						<jsp:param value="${sessionScope.user.userLname}" name="userLname" />
						<jsp:param value="${sessionScope.user.userMailId}"
							name="userMailId" />
						<jsp:param value="${sessionScope.user.userMobileNo}"
							name="userMobileNo" />
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
		<form action="SaveBookingCabInfoController" method="post">
			<div class="row">
				<div class="col-md-4">

					<div class="input-group">
						<span class="input-group-addon"> <i
							class="fa fa-map-marker" aria-hidden="true"></i>
						</span> <input type="text" class="form-control" placeholder="Source"
							id="source" name="from" value="${bookingInfo.source}">
					</div>
					<br /> <br />
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="fa fa-location-arrow" aria-hidden="true"></i>
						</span> <input type="text" class="form-control" placeholder="Destination"
							id="destination" value="${bookingInfo.destination}">
					</div>
					<br /> <br />
					<div class="input-group">
						<span class="input-group-addon"> <i class="fa fa-road"
							aria-hidden="true"></i>
						</span> <input type="text" class="form-control" placeholder="distance"
							id="distance" name="distance">
					</div>
					<br /> <br />
					<div class="input-group">
						<span class="input-group-addon"> <i class="fa fa-clock-o"
							aria-hidden="true"></i>
						</span> <input type="text" class="form-control" placeholder="duration"
							id="duration" name="duration">
					</div>
					<br /> <br />
					<div class="input-group">
						<span class="input-group-addon"> <i class="fa fa-car"
							aria-hidden="true"></i>
						</span> <select class="select form-control" id="cabType" name="cabType"
							onChange="calculateCost()">
							<option value="Select">Select</option>
						</select>
					</div>
					<br /> <br />
					<div class="input-group">
						<span class="input-group-addon"> <i class="fa fa-money"
							aria-hidden="true"></i>
						</span> <input type="text" class="form-control" placeholder="cost"
							id="cost" name="cost">
					</div>
				</div>
				<div class="col-md-8" id="map"></div>
			</div>
			<br/><br/>
			<div class="row">
				<div class="col-md-12">
					<div class="input-group">
						<span class="input-group-addon"> 
							<i class="fa fa-id-card" aria-hidden="true"></i>
						</span> 
						<select class="select form-control" id="cabCaptId"
							name="cabCaptId">
							<option value="Select">Select</option>
						</select>
					</div>
				</div>
			</div>
			<br/>
			<div class="row">
				<div class="col-md-12">
					<input type="submit" value="Book Your Ride" class="btn btn-primary form-control"/>
				</div>
			</div>
		</form>
	</div>
	<script src="js/modal.js" type="text/javascript"></script>
	<script type="text/javascript">
		function initMap() {
			var src = document.getElementById("source").value;
			var dest = document.getElementById("destination").value;

			var directionsDisplay = new google.maps.DirectionsRenderer;
			var directionsService = new google.maps.DirectionsService;
			var map = new google.maps.Map(document.getElementById('map'), {
				zoom : 18
			});
			directionsDisplay.setMap(map);
			calculateAndDisplayRoute(directionsService, directionsDisplay, src,
					dest);
			getInformation(src, dest);
		}

		function calculateAndDisplayRoute(directionsService, directionsDisplay,
				src, dest) {
			var selectedMode = "DRIVING";
			directionsService.route({
				origin : src,
				destination : dest,
				travelMode : google.maps.TravelMode[selectedMode]
			}, function(response, status) {
				if (status == 'OK') {
					directionsDisplay.setDirections(response);
				} else {
					window.alert('Directions request failed due to ' + status);
				}
			});
		}

		function getInformation(origin, destination) {
			var service = new google.maps.DistanceMatrixService();
			service.getDistanceMatrix({
				origins : [ origin ],
				destinations : [ destination ],
				travelMode : google.maps.TravelMode.DRIVING,
				unitSystem : google.maps.UnitSystem.METRIC,
				avoidHighways : false,
				avoidTolls : false,
			}, callback);
		}

		function callback(response, status) {
			console.log(response);
			if (status === google.maps.DistanceMatrixStatus.OK) {
				var distance = response.rows[0].elements[0].distance.text;
				var duration = response.rows[0].elements[0].duration.text;

				document.getElementById("distance").value = distance;
				document.getElementById("duration").value = duration
			}
		}
	</script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDUbzXEBibrrrB5o_iFSqCmBFnn8jiMRnQ&callback=initMap">
		
	</script>
</body>

</html>