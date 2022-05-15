$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});




// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}



	// If valid------------------------
	var type = ($("#hidIDSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		url : "PaymentApi",
		type : type,
		data : $("#formRegister").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});

});




function onItemSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidIDSave").val("");
	$("#formRegister")[0].reset();
}



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
			$("#hidIDSave").val( $(this).closest("tr").find('#hidIDUpdate').val());
			$("#EMP_ID").val($(this).closest("tr").find('td:eq(0)').text());
			$("#EMP_Name").val($(this).closest("tr").find('td:eq(1)').text());
			$("#EMP_Number").val($(this).closest("tr").find('td:eq(2)').text());
			$("#EMP_Deparment").val($(this).closest("tr").find('td:eq(3)').text());
			$("#EMP_Email").val($(this).closest("tr").find('td:eq(4)').text());
			$("#EMP_Age").val($(this).closest("tr").find('td:eq(5)').text());
			$("#EMP_DOB").val($(this).closest("tr").find('td:eq(6)').text());
		});


// Delete============================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "PaymentApi",
		type : "DELETE",
		data : "EMP_ID=" + $(this).data("EMP_ID"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});




function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}



//CLIENTMODEL=========================================================================
function validateItemForm()
{

//Employee ID
if ($("#EMP_ID").val().trim() == "")
 {
 return "Insert EmpID";
 }

//Name
if ($("#EMP_Name").val().trim() == "")
 {
 return "Insert Name";
 }

//Number
if ($("#EMP_Number").val().trim() == "")
 {
 return "Insert Number";
 }

//Department
if ($("#EMP_Deparment").val().trim() == "")
 {
 return "Insert Department";
 } 
//Email
if ($("#EMP_Email").val().trim() == "")
 {
 return "Insert Email";
 }

//Age
if ($("#EMP_Age").val().trim() == "")
 {
 return "Insert Age";
 } 
if ($("#EMP_DOB").val().trim() == "")
 {
 return "Insert Date Of Birth";
 } 

return true;
}














