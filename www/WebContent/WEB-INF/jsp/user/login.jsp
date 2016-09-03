<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>login</title>
<script type="text/javascript" charset="UTF-8">
<!--
window.onload = function() {
	var flag = "${flag}";
	
	if(flag == "1") {
		alert("<spring:message code='messages.member.id.check4' />");
	} else if(flag == "2") {
		alert("<spring:message code='messages.member.password.check' />");
	}
	
	var url = "${pageContext.request.contextPath}/user/main.do";
	location.href = url;
};
//-->
</script>
</head>
<body>

</body>
</html>