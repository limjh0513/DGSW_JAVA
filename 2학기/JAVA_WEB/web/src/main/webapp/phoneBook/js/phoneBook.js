$(document).ready(function() {
	initEventHandlers();
	
	readList(function(list) {
		showList(list);
	});
}); 


function initEventHandlers() {
	$("#btn_register").on("click", function() {
		openRegisterPopup();
	});
	
	$(".registerLayer #btn_close").on("click", function() {
		$(".registerLayer").hide();
	});
	
	$(".registerLayer #btn_save").on("click", function() {
		doSave();
	});
	
	$("#btn_delete").on("click", function() {
		deleteSelected();
	});
}

function openRegisterPopup() {
	$(".registerLayer").show();
	$(".registerLayer #btn_save").val("등록");
	
	$(".registerLayer [name='idx']").val("");
	$(".registerLayer [name='name']").val("");
	$(".registerLayer [name='phoneNumber']").val("");
}

function openUpdatePopup(data) {
	$(".registerLayer").show();
	$(".registerLayer #btn_save").val("수정");
	
	$(".registerLayer [name='idx']").val(data.idx);
	$(".registerLayer [name='name']").val(data.name);
	$(".registerLayer [name='phoneNumber']").val(data.phoneNumber);
}

function doSave() {
	var param = {
		idx: $(".registerLayer [name='idx']").val(),
		name: $(".registerLayer [name='name']").val(),
		phoneNumber: $(".registerLayer [name='phoneNumber']").val()
	}

	var url;
	if (param.idx == "") {
		url = "register.do";
	} else {
		url = "update.do";
	}
	
	$.post(url, param, function(response) {
		if (response.resultCode == "Success") {
			$(".registerLayer").hide();
			
			readList(function(list) {
				showList(list);
			});
		} else {
			alert("저장에 실패했습니다. " + response.resultMessage);
		}
	});
}

function readList(callback) {
	$.get("list.do", null, function(response) {
		if (response.resultCode == "Success") {
			callback(response.data);
		} else {
			alert("서버에서 데이터를 읽어 올 수 없습니다. " + response.resultMessage);
		}
	});
}

function showList(list) {
	$(".list tbody").empty();
	
	for (var i in list) {
		showLine(list[i]);
	}
}

function showLine(data) {
	var tr = $("<tr/>");
	
	// checkbox
	var td1 = $("<td></td>");
	var checkbox = $("<input name='checkSelect' type='checkbox'>");
	checkbox.val(data.idx);
	td1.append(checkbox);
	tr.append(td1);
	
	// name
	var td2 = $("<td></td>");
	var aTag = $("<a></a>")
	aTag.html(data.name);
	aTag.attr("href", "javascript:;");
	aTag.on("click", function() {
		openUpdatePopup(data);
	});
	td2.append(aTag);
	tr.append(td2);
	// TODO 이름 클릭 -> update
	
	// phone number
	var td3 = $("<td></td>");
	td3.html(data.phoneNumber);
	tr.append(td3);
	
	$(".list tbody").append(tr);
	
}

function deleteSelected() {
	var selectedList = $("[name='checkSelect']:checked");
	
	if (selectedList.length == 0) {
		alert("삭제할 전화번호를 선택하세요.");
		return;
	}
	
	var idxList = "";
	for (var i = 0 ; i < selectedList.length ; i++) {
		idxList += selectedList[i].value + ",";
	}
	idxList = idxList.substring(0, idxList.length - 1);

	if (confirm("선택된 전화번호를 삭제하시겠습니까?")) {
		$.post("delete.do", {idxList: idxList}, function(response) {
			if (response.resultCode == "Success") {
				readList(function(list) {
					showList(list);
				});
			} else {
				alert("데이터를 삭제할 수 없습니다. " + response.resultMessage);
			}
		});
	}
}