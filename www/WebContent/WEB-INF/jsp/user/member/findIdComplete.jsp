<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>find id</title>
<script type="text/javascript" charset="UTF-8">
<!--
window.onload = function() {
	var flag = "${flag}";
	
	if(flag == "Y") {
		alert("<spring:message code='messages.find.id' />");
		var url = "${pageContext.request.contextPath}/user/main.do";
		location.href = url;
	} else {
		alert("<spring:message code='messages.member.check' />");
		history.back();
	}
};
//-->
</script>
</head>
<body>

</body>
</html>