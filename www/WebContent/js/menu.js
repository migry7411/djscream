function getSubMenu(num) {
	if(num == 0) {
		$("#sub_music").css("display", "none");
		$("#sub_board").css("display", "none");
	} else if(num == 1) {
		$("#sub_music").css("display", "block");
		$("#sub_board").css("display", "none");
	} else {
		$("#sub_music").css("display", "none");
		$("#sub_board").css("display", "block");
	}
}

function loginValidation(frm) {
	if(frm.user_id.value == "") {
		alert("아이디를 입력하세요.");
		frm.user_id.focus();
		return false;
	}
	
	if(frm.user_pw.value == "") {
		alert("비밀번호를 입력하세요.");
		frm.user_pw.focus();
		return false;
	}
	
	return true;
}