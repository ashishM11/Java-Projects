function choseWhenToRide() {
	whenVal = document.getElementById("when").value;
	if (whenVal === 'later') {
		document.getElementById("selectDT").style.display = 'block';
	} else {
		document.getElementById("selectDT").style.display = 'none';
	}
}

function getCodeWithDesc(reqFor) {
	var reqObj ={};
	reqObj.reqType = 'fetchConstantInfo';
	if(reqFor === "CabType"){
		reqObj.reqFor=reqFor
	}else if(reqFor === "Cab"){
		reqObj.reqFor=reqFor
		reqObj.subType = document.getElementById("cabType").value;
	}else if(reqFor === "Rate"){
		reqObj.reqFor=reqFor
		reqObj.subType = document.getElementById("cabType").value;
	}
	
	
	$.get('CommonFetchController',reqObj, function(responseObj) {
		if (reqFor === 'CabType') {
			JSON.parse(responseObj).forEach(function(obj) {
				$('#cabType').append('<option value="'+obj.constCode+'">'+obj.constCodeDesc+'</option>');
			});
		}else if(reqFor === 'Cab'){
			$('#cabBrand').empty();
			$('#cabBrand').append('<option value="Select">Select</option>');
			JSON.parse(responseObj).forEach(function(obj) {
				$('#cabBrand').append('<option value="'+obj.constCode+'">'+obj.constCodeDesc+'</option>');
			});
		}else if(reqFor === "Rate"){
			var distance = parseInt(document.getElementById("distance").value.length===0 ? "1":document.getElementById("distance").value);
			var cost = document.getElementById("cost");
			JSON.parse(responseObj).forEach(function(obj) {
				if(obj.constSubType === document.getElementById("cabType").value){
					cost.value = Math.ceil(distance * parseInt(obj.constCode));
				}
			});
		}
	})
}

function getCabCaptInfo(cabType){
	$.get("CommonFetchController",{
		reqType :'fetchCabCaptainDetails',
		cabType : cabType
	},function(responseObj){
		if(responseObj !== undefined){
			$('#cabCaptId').empty();
			$('#cabCaptId').append('<option value="Select">Select</option>');
			JSON.parse(responseObj).forEach(function(obj) {
				$('#cabCaptId').append('<option value="'+obj.cabCaptID+'">'+obj.user.userFname.concat(" ").concat(obj.user.userLname)+"|Contact Number : "+obj.user.userMobileNo+"| Cab Number : "+obj.cab.cabRegNo+'</option>');
			});
		}else{
			$('#cabCaptId').empty();
			$('#cabCaptId').append('<option value="Select">Select</option>');
		}
	});
}

function calculateCost(){
	getCodeWithDesc("Rate");
	getCabCaptInfo(document.getElementById("cabType").value);
}