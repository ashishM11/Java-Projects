<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<form class="form-horizontal" action="FeedbackController" method="post">
	<!-- Text input-->
	<div class="form-group">
		<div class="col-md-offset-1 col-md-10">
			<input id="feeedbackSub" name="feeedbackSub" type="text"
				placeholder="Feedback Subject" class="form-control input-md">
		</div>
	</div>

	<!-- Textarea -->
	<div class="form-group">
		<div class="col-md-offset-1 col-md-10">
			<textarea class="form-control" id="feebackComments"
				name="feebackComments"></textarea>
		</div>
	</div>
	<!-- Multiple Radios (inline) -->
	<div class="form-group">
		<div class="col-md-offset-1  col-md-10">
			<label class="control-label" for="rating">Rate Our Service</label>
			<div>
				<label class="radio-inline" for="rating-0"> <input
					type="radio" name="rating" id="rating-0" value="1"
					checked="checked"> 1
				</label> <label class="radio-inline" for="rating-1"> <input
					type="radio" name="rating" id="rating-1" value="2"> 2
				</label> <label class="radio-inline" for="rating-2"> <input
					type="radio" name="rating" id="rating-2" value="3"> 3
				</label> <label class="radio-inline" for="rating-3"> <input
					type="radio" name="rating" id="rating-3" value="4"> 4
				</label> <label class="radio-inline" for="rating-4"> <input
					type="radio" name="rating" id="rating-4" value="5"> 5
				</label>
			</div>
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
