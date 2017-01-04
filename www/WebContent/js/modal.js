function openModal(width, height) {
	var top = $("#modal_back").height() / 2 - height / 2;
	var left = $("#modal_back").width() / 2 - width / 2;
	
	$('#modal').css("top", top);
	$('#modal').css("left", left);
	$('#modal').css("width", width);
	$('#modal').css("height", height);
	$('#modal_back').css("display", "block");
	$("#modal_back").width($(document).width());
	$("#modal_back").height($(document).height());
}

function closeModal() {
	$('#modal_back').css("display", "none");
}