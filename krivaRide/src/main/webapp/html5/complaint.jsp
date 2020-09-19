<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<form action="SaveComplaintController" method="post"
	class="form-horizontal">
	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-10">
			<input id="compSub" name="compSub" class="form-control" type="text"
				placeholder="Complaint Subject" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-10">
			<textarea name="compMatter" class="form-control" id="compMatter"
				placeholder="Wrtie your trouble's"></textarea>
		</div>
	</div>
	<div>
		<input type="hidden" value="${param.uid}" name="uid">
	</div>
	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-10">
			<button type="submit" class="btn btn-primary">Send</button>
			<button type="reset" class="btn btn-secondary">Reset</button>
		</div>
	</div>
</form>