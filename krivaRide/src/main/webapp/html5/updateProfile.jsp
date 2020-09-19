<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${null == sessionScope.user}">
		<c:redirect url="../index.jsp"></c:redirect>
	</c:when>
	<c:when test="${null != sessionScope.user }">
		<form class="form-horizontal" action="UpdateUserController"
			method="post" name="updateProfileForm">

			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
					<input type="text" class="form-control" name="userFname" disabled
						value="${sessionScope.user.userFname}">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
					<input type="text" class="form-control" name="userLname" disabled
						value="${sessionScope.user.userLname}">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
					<input type="email" class="form-control" name="userMailId"
						placeholder="User Mail ID*"
						value="${sessionScope.user.userMailId}">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
					<input type="text" class="form-control" name="userMobile"
						placeholder="Mobile Number*"
						value="${sessionScope.user.userMobileNo}">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
					<button type="submit" class="btn btn-primary">Update Your
						Profile</button>
				</div>
			</div>
		</form>
	</c:when>
</c:choose>