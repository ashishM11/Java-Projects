<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="icon" href="../image/car.ico" type="image/png" sizes="16x16">
<title>Welcome to Kriva Cabs</title>
<script defer
	src="https://use.fontawesome.com/releases/v5.0.9/js/all.js"
	integrity="sha384-8iPTk2s/jMVj81dnzb/iFR2sdA7u06vHJyyLlAd4snFpCl/SnyUjRrbdJsw1pGIl"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="../css/admin.css">
</head>
<body onload="getAllUser()">

	<nav
		class="navbar  navbar-expand-md navbar-expand-lg navbar-dark bg-secondary">
		<a class="navbar-brand" href="adminDashboard.jsp"> <i
			class="fas fa-taxi"></i> KRIVA CAB's <=> <b>ADMIN DASHBOARD</b>
		</a>

		<ul class="nav navbar-nav ml-auto" style="margin-right: 5%;">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false"><i class="fas fa-user-circle"></i>
					${usrEmail} <span class="caret"></span></a>
				<ul class="dropdown-menu" style="margin-top: 20px;">
					<li><a href='#' class='dropdown-item'><i
							class='fas fa-edit'></i> Update Profile</a></li>
					<li><a href='#' class='dropdown-item'><i
							class='fas fa-key'></i> Change Password</a></li>
					<li class='divider dropdown-divider'></li>
					<li><a href='../signOut.jsp' class='dropdown-item'><i
							class="fas fa-sign-out-alt"></i> Logout</a></li>
				</ul></li>
		</ul>
	</nav>

	<div class="container" style="margin-top: 4%">
		
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>