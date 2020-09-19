function getRiders() {
	var tblData = {};
	$.get('CommonFetchController', {
		reqType : 'fetchRiderInfo',
		emailId : document.getElementById("mailId").value,
		mobileNo : document.getElementById("mNo").value
	}, function(responseObj) {
		userData = JSON.parse(responseObj);
		document.getElementById("cabbieInfo").value = userData.userId + ","
				+ userData.userFname.concat(" ").concat(userData.userLname);
		getCabType();
	})
}

function getCabType() {
	$.get('CommonFetchController', {
		reqType : 'fetchConstantInfo',
		reqFor : 'CabType'
	}, function(responseObj) {
		$('#cabType').empty();
		$('#cabType').append('<option value="Select">Select</option>');
		JSON.parse(responseObj).forEach(
				function(obj) {
					$('#cabType').append(
							'<option value="' + obj.constCode + '">'
									+ obj.constCodeDesc + '</option>');
				});
	})
}

function getUnassignedCab(){
	$.get('CommonFetchController',{
		reqType : 'fetchCabInfo'
	},function(responseObj){
		var data = JSON.parse(responseObj);
		$('#unassignedCab').empty();
		$('#unassignedCab').append('<option value="Select">Select</option>');
		data.forEach(function(obj){
			if(obj.cabType===document.getElementById("cabType").value){
				$('#unassignedCab').append('<option value="' + obj.cabId + '">' + obj.cabRegNo
						+ "-" + obj.cabBrand + '</option>');
			}
		});
	})
}
