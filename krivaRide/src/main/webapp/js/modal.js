// Get modal element
var modal = document.getElementById('simpleModal');
// Get close button
var closeBtn = document.getElementById('closeBtn');

closeBtn.addEventListener('click', closeModal);

// Modal Body content
var modalBodyContent = document.getElementById("modalBodyContent");
var modalHeaderText = document.getElementById("modalHeaderText");
var login = document.getElementById("login");
var createAccount = document.getElementById("createAccount");
var forgetPassword = document.getElementById("forgetPassword");
var changePassword = document.getElementById("changePassword");
var feedback = document.getElementById("feedback");
var updateProfile = document.getElementById("updateProfile");
var complaint = document.getElementById("complaint");
var dynamicTableConent = document.getElementById("dynamicTableConent");

function openModal() {
	modal.style.display = 'block';
}

// Function to close modal
function closeModal() {
	modal.style.display = 'none';
	dynamicTableConent.innerHTML = "";

}

function openModalBox(modalComponent) {
	switch (modalComponent) {
	case 'signIn':
		openModal();
		modalHeaderText.innerHTML = "Please Sign In, To book your ride";
		login.style.display = 'block';
		modalBodyContent.innerHTML = login.innerHTML;
		break;
	case 'signUp':
		openModal();
		modalHeaderText.innerHTML = "Create your account to enjoy the best ride in your town.";
		createAccount.style.display = 'block';
		modalBodyContent.innerHTML = createAccount.innerHTML;
		break;
	case 'forgetPassword':
		login.style.display = 'none';
		modalHeaderText.innerHTML = "Forget Password?";
		forgetPassword.style.display = 'block';
		modalBodyContent.innerHTML = forgetPassword.innerHTML;
		break;
	case 'changePassword':
		openModal();
		modalHeaderText.innerHTML = "Change your current password";
		changePassword.style.display = 'block';
		modalBodyContent.innerHTML = changePassword.innerHTML;
		break;
	case 'feedback':
		openModal();
		modalHeaderText.innerHTML = "Provide your feedback its valuable to us";
		feedback.style.display = 'block';
		modalBodyContent.innerHTML = feedback.innerHTML;
		break;
	case 'complaint':
		openModal();
		modalHeaderText.innerHTML = "Is there any Problem? Do write to us!!";
		complaint.style.display = 'block';
		modalBodyContent.innerHTML = complaint.innerHTML;
		break;
	case 'updateProfile':
		openModal();
		modalHeaderText.innerHTML = "Update your profile...";
		updateProfile.style.display = 'block';
		modalBodyContent.innerHTML = updateProfile.innerHTML;
		break;
	case 'viewCustomers':
		openModal();
		modalHeaderText.innerHTML = "List Of Customers Records";
		modalBodyContent.innerHTML = dynamicTableConent.innerHTML;
		getTableData(modalComponent);
		break;
	case 'viewFeedbacks':
		openModal();
		modalHeaderText.innerHTML = "Feedbacks Given by Customer to Service";
		modalBodyContent.innerHTML = dynamicTableConent.innerHTML;
		getTableData(modalComponent);
		break;
	case 'viewComplaints':
		openModal();
		modalHeaderText.innerHTML = "Complaints registered against our service..";
		modalBodyContent.innerHTML = dynamicTableConent.innerHTML;
		getTableData(modalComponent);
		break;
	case 'viewConstants':
		openModal();
		modalHeaderText.innerHTML = "All Constant Code & it's information";
		modalBodyContent.innerHTML = dynamicTableConent.innerHTML;
		getTableData(modalComponent);
		break;
	case 'viewDrivers':
		openModal();
		modalHeaderText.innerHTML = "List Of Rider's records";
		modalBodyContent.innerHTML = dynamicTableConent.innerHTML;
		getTableData(modalComponent);
		break;
	case 'viewCabs':
		openModal();
		modalHeaderText.innerHTML = "List of Available & Running Cabs";
		modalBodyContent.innerHTML = dynamicTableConent.innerHTML;
		getTableData(modalComponent);
		break;
	case "viewConfirmation":
		openModal();
		modalHeaderText.innerHTML = "List Of Ride Confirmation";
		modalBodyContent.innerHTML = dynamicTableConent.innerHTML;
		getTableData(modalComponent);
		break;
	}
}

function getTableData(modalComponent) {
	var tblData = [];
	$.get('CommonFetchController', {
		reqType : 'fetchAllRecords',
		forTable : modalComponent
	}, function(responseObj) {
		switch (modalComponent) {
		case "viewCustomers":
			tblData = JSON.parse(responseObj).map(function(data) {
				return {
					"ID" : data.userId,
					"Name" : data.userFname + " " + data.userLname,
					"SignUp Date" : data.userCreationDT,
					"Email" : data.userMailId,
					"Mobile" : data.userMobileNo
				}
			})
			generateDynamicTable(tblData);
			break;
		case "viewFeedbacks":
			tblData = JSON.parse(responseObj).map(function(data) {
				return {
					"ID" : data.user.userId,
					"Name" : data.user.userFname + " " + data.user.userLname,
					"Subject" : data.feedbackSub,
					"Message" : data.feedbackMsg,
					"Rating" : data.feedbackRating
				}
			})
			generateDynamicTable(tblData);
			break;
		case "viewComplaints":
			tblData = JSON.parse(responseObj).map(function(data) {
				return {
					"ID" : data.user.userId,
					"Name" : data.user.userFname + " " + data.user.userLname,
					"Email" : data.user.userMailId,
					"Subject" : data.complaintSub,
					"Message" : data.complaintMsg
				}
			})
			generateDynamicTable(tblData);
			break;
		case 'viewConstants':
			tblData = JSON.parse(responseObj).map(function(data) {
				return {
					"ID" :data.constId,
					"Code" : data.constCode,
					"Description" : data.constCodeDesc,
					"Type" : data.constType,
					"Sub-Type" : data.constSubType
				}
			})
			generateDynamicTable(tblData);
			break;
		case 'viewDrivers':
			tblData = JSON.parse(responseObj).map(function(data) {
				return {
					"Rider ID" : data.userId,
					"Rider Name" : data.userFname + " " + data.userLname,
					"Rider Email" : data.userMailId,
					"Rider Mobile" : data.userMobileNo
				}
			})
			generateDynamicTable(tblData);
			break;
		case 'viewCabs':
			tblData = JSON.parse(responseObj).map(function(data) {
				return {
					"ID" : data.cabId,
					"Cab Registered Number" : data.cabRegNo,
					"Is Cab Insured" : data.cabInsured,
					"Is Cab Active" : data.isCabActive,
					"Cab Type":data.cabType.toUpperCase(),
					"Cab Registered Date":data.cabRegisterDate
				}
			})
			generateDynamicTable(tblData);
			break;
		case 'viewConfirmation':
			tblData = JSON.parse(responseObj).map(function(data) {
				return {
					"Customer Name":data.booking.user.userFname.concat(" ").concat(data.booking.user.userLname),
					"Source":data.booking.source,
					"Destination":data.booking.destination,
					"Booking Date":data.booking.cabDate,
					"Booking Time":data.booking.cabTime,
					"Distance":data.bookingDistance,
					"Duration":data.bookingDuration,
					"Cost":data.bookingConfirmationCost,
					"Cab Reg No":data.cabCaptain.cab.cabRegNo,
					"Cab Brand":data.cabCaptain.cab.cabBrand,
					"Driver Name":data.cabCaptain.user.userFname.concat(" ").concat(data.cabCaptain.user.userLname),
					"Mobile Number":data.cabCaptain.user.userMobileNo
				}
			})
			generateDynamicTable(tblData);
			break;
		}
	});
}

function generateDynamicTable(tableData) {
	var table = document.createElement("table");
	table.setAttribute("class", "display");
	table.setAttribute("id", "viewTable");

	var columnNames = [];
	columnNames = Object.keys(tableData[0]);

	if (columnNames.length > 0) {
		var tableHead = document.createElement("thead")
		var tableRowHead = document.createElement("tr");
		columnNames.forEach(function(cellHeader) {
			var cell = document.createElement("th");
			var cellText = document.createTextNode(cellHeader);
			cell.appendChild(cellText);
			tableRowHead.appendChild(cell);
		})
		tableHead.appendChild(tableRowHead);
		table.appendChild(tableHead);
	}
	var tableBody = document.createElement("tbody");
	if (tableData.length > 0) {

		tableData.forEach(function(rowDataObj) {
			var tableRowBody = document.createElement("tr");
			colValuesArr = Object.values(rowDataObj);
			colValuesArr.forEach(function(colValue) {
				var cell = document.createElement("td");
				var cellText = document.createTextNode(colValue);
				cell.appendChild(cellText);
				tableRowBody.appendChild(cell);
			});
			tableBody.appendChild(tableRowBody);
		})
	}
	table.appendChild(tableBody);
	dynamicTableConent.appendChild(table);
	applyDataTable();
	modalBodyContent.appendChild(dynamicTableConent)
	console.log(dynamicTableConent)
}
function applyDataTable() {
	$(document).ready(function() {
		$("#viewTable").dataTable();
	});
}
