<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>글이 삭제됨.</title>
<script type="text/javascript" charset="UTF-8">
<!--
window.onload = function() {
	alert("<spring:message code='messages.board.delete' />");
	
	var url = "listBoard.do?";
	url = url + "nowPage=${pageinfo.nowPage}";
	url = url + "&nowBlock=${pageinfo.nowBlock}";
	url = url + "&searchColumn=${pageinfo.searchColumn}";
	url = url + "&searchWord=${pageinfo.searchWord}";
	url = url + "&code=${pageinfo.code}";
	location.href = url;
};
//-->
</script>
</head>
<body>

</body>
</html>