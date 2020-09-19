<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	
	if(session.getAttribute("user")==null){
		response.sendRedirect("../index.jsp");
	}

%>
<form class="form-horizontal" action="ResetUserPasswordController"
	method="post" name="change_pwd">
	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-10">
			<input type="password" class="form-control" name="currentPwd"
				placeholder="Current Password">
		</div>
	</div>
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
	<input type="hidden" name="changePwd" value="changePwd">
	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-10">
			<button type="submit" class="btn btn-primary">Change
				Password</button>
		</div>
	</div>
</form>