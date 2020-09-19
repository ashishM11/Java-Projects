$(document).ready(function() {
      $.ajax({
          url: 'CommonFetchController',
          data: {
            reqType: 'fetchCount'
          },
          success: function(responseObj) {
            console.log(responseObj);
            var data = JSON.parse(responseObj);
            document.getElementById("totcust").innerHTML = data.UsersEM;
            document.getElementById("totFeedback").innerHTML = data.FeedbacksEM;
            document.getElementById("totComplaint").innerHTML = data.ComplaintsEM;
            document.getElementById("totConst").innerHTML = data.ConstantsEM;
            document.getElementById("totCabcapt").innerHTML = data.UsersEM;
            document.getElementById("totcab").innerHTML = data.CabsEM;
            document.getElementById("totCabBook").innerHTML = data.BookingConfirmationEM;
          },
          error: function() {
            console.log("Unable to fetch any records");
          }
        })
    });