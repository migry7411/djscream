<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<title><spring:message code='contents.board.detail' /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/board_detail.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/reply.js"></script>
<script type="text/javascript"  charset="UTF-8">
<!--
var flag = false;

$(function(){
	listReply(0, 0, "${board.id}", "listBoardReply.do");
});


function checkPasswd(id, value) {
	$.ajax({
		type: 'POST'
 		, dataType: 'JSON'  
 		, url: 'checkPasswd.do'
 		, data: { "id" : id }
 		, success: function(data) {
 			if(data.result.passwd == value) {
 				var url = "deleteBoard.do?id=${board.id}";
 				url = url + "&userid=${board.userid}";
 				url = url + "&filename=${board.filename}";
 				url = url + "&nowPage=${pageinfo.nowPage}";
 				url = url + "&nowBlock=${pageinfo.nowBlock}";
 				url = url + "&searchColumn=${pageinfo.searchColumn}";
 				url = url + "&searchWord=${pageinfo.searchWord}";
 				url = url + "&code=${pageinfo.code}";
 				location.href = url;
 			} else {
 				alert("<spring:message code='messages.member.password' />");
 				$('input[name="passwd"]').focus();
 			}
		}
		, error: function(data, status, err) {
			alert(err);
		}
	});
}

function goList() {
	var url = "listBoard.do?";
	url = url + "nowPage=${pageinfo.nowPage}";
	url = url + "&nowBlock=${pageinfo.nowBlock}";
	url = url + "&searchColumn=${pageinfo.searchColumn}";
	url = url + "&searchWord=${pageinfo.searchWord}";
	url = url + "&code=${pageinfo.code}";
	location.href = url;
}

function goUpdate() {
	var url = "updateBoard.do?id=${board.id}";
	url = url + "&nowPage=${pageinfo.nowPage}";
	url = url + "&nowBlock=${pageinfo.nowBlock}";
	url = url + "&searchColumn=${pageinfo.searchColumn}";
	url = url + "&searchWord=${pageinfo.searchWord}";
	url = url + "&code=${pageinfo.code}";
	location.href = url;
}

function goDelete() {
	if(confirm("<spring:message code='messages.common.delete' />")) {
		openModal(300, 110);
		
		var html = "<ul>";
		html = html + "<li><spring:message code='messages.member.password' /></li>";
		html = html + "<li><input type='password' name='passwd' id='passwd' maxlength='20' required /></li>";
		html = html + "<li><button type='button' onclick='deleteProc()'><spring:message code='contents.common.confirm' /></button> ";
		html = html + "<button type='button' onclick='closeModal()'><spring:message code='contents.common.close' /></button></li>";
		html = html + "</ul>";
		
		$("#modal").html(html);
	}
}

function goDetail(id) {
	var url = "detailBoard.do?id=" + id;
	url = url + "&nowPage=${pageinfo.nowPage}";
	url = url + "&nowBlock=${pageinfo.nowBlock}";
	url = url + "&searchColumn=${pageinfo.searchColumn}";
	url = url + "&searchWord=${pageinfo.searchWord}";
	url = url + "&code=${pageinfo.code}";
	location.href = url;
}

function deleteProc() {
	if($("#passwd").val() == "") {
		alert("<spring:message code='messages.member.password' />");
		$('input[name="passwd"]').focus();
		return;
	}
	
	checkPasswd("${board.id}", $("#passwd").val());
}

function goReplyProc() {
	var frm = $("form[name='frmReply']").serializeArray();
	var param = {};
	
	for(var i=0; i<frm.length; i++) {
		if(frm[i].name == "contents" && frm[i].value == "") {
			alert("<spring:message code='messages.board.contents' />");
			$('textarea[name="contents"]').focus();
			return;
		}
		
		param[frm[i].name] = frm[i].value;
	}
	
	replyProc(param, "replyProc.do", "listBoardReply.do");
}

function goDeleteReply(id, userid) {
	if(confirm("<spring:message code='messages.common.delete' />")) {
		var param = { id : id, userid : userid, boardid : "${board.id}" };
		replyProc(param, "replyDeleteProc.do", "listBoardReply.do");
	}
}

function openImage(width, height) {
	openModal(width+20, height+20);
	
	var filename = "${board.filename}";
	var src = "";
	
	if(filename == "noimage.png") {
		src = "${pageContext.request.contextPath}/images/" + filename;
	} else {
		src = "${pageContext.request.contextPath}/upload/board/" + filename;
	}
	
	var html = "<div class='img'><a href='javascript:closeModal()'><img src='" + src + "' border='0' /></a></div>";
	$("#modal").html(html);
}
//-->
</script>
</head>
<body>
	<h1 class="title"><spring:message code='contents.board.detail' /></h1>
	<div class="detail">
		<ul>
			<li>${board.subject}</li>
			<li>${board.name} :: ${board.regdate} :: <spring:message code='contents.common.readcount' /> ${board.readcount}</li>
			<li>${contents}</li>
			<c:if test="${board.filename != null && board.filename != ''}">
			<li>
				<spring:message code="contents.board.filename" /> : <a href="javascript:openImage(${width}, ${height})">image</a>
			</li>
			</c:if>
			<c:if test="${board.filename == null || board.filename == ''}"><li><spring:message code="messages.board.file" /></li></c:if>
			<li>
				<c:if test="${(boardCode.write_auth == 'L' && board.userid == login.userid) || (boardCode.write_auth == 'A' && login.userid == 'administrator')}">
				<button type="button" onclick="goUpdate()"><spring:message code='contents.common.update' /></button>
				<button type="button" onclick="goDelete()"><spring:message code='contents.common.delete' /></button>
				</c:if>
				<button type="button" onclick="goList()"><spring:message code='contents.common.list' /></button>
			</li>
		</ul>
	</div>
	<c:if test="${login != null}">
	<div class="reply">
		<form name="frmReply" action="javascript:goReplyProc()" method="post">
			<fieldset>
				<input type="hidden" name="boardid" value="${board.id}" />
				<input type="hidden" name="userid" value="${login.userid}" />
				<input type="hidden" name="name" value="${login.nickname}" />
				<ul>
					<li><spring:message code='contents.reply.write' /></li>
					<li><textarea name="contents" required></textarea></li>
					<li class="btn"><button type="submit"><spring:message code='contents.common.confirm' /></button> <button type="reset"><spring:message code='contents.common.cancel' /></button></li>
				</ul>
			</fieldset>
		</form>
	</div>
	</c:if>
	<div class="reply">
		<ul id="replyList">
		</ul>
	</div>
</body>
</html>