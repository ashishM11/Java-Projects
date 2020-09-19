
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%!int uid = 0;
	String pwdChar = "";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="image/car.ico" type="image/png" sizes="16x16">
<title>Welcome to Kriva Cabs</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<div>
			<%
				System.out.println(request.getParameter("uid") + " - " + request.getParameter("data"));
				uid = (null != request.getParameter("uid")) ? Integer.parseInt(request.getParameter("uid").toString()) : 0;
				pwdChar = (null != request.getParameter("data")) ? request.getParameter("data").toString() : "";
				if ((uid == 0) && "".equals(pwdChar)) {
					response.sendRedirect("index.jsp");
				}

				if (request.getAttribute("errors") != null) {
					out.print("<li id='error' class='text-danger'>" + request.getAttribute("errors") + "</li>");
				}
			%>
		</div>
		<div class='col-md-offset-2 col-md-8'>
			<form class="form-horizontal" action="ResetUserPasswordController"
				method="post" name="reset_pwd">
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-10">
						<input type="password" class="form-control" name="newPwd"
							placeholder="New Password">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-10">
						<input type="password" class="form-control" name="repeatPwd"
							placeholder="Repeat Password">
					</div>
				</div>
				<input type="hidden" name="uid" value="<%=uid%>" /> 
				<input type="hidden" name="oldPwdChar" value="<%=pwdChar%>" /> 
				<input type="hidden" name="resetPwd" value="resetPwd" />
				
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-10">
						<button type="submit" class="btn btn-primary">Reset
							Password</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
