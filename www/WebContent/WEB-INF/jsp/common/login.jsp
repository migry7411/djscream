<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>잘못된 만남</title>
<script type="text/javascript" charset="UTF-8">
<!--
window.onload = function() {
	var flag = "${flag}";
	
	if(flag == "U") {
		alert("<spring:message code='messages.common.login' />");
	} else if(flag == "A") {
		alert("<spring:message code='messages.common.admin' />");
	} else {
		alert("<spring:message code='messages.common.access.fail' />");
	}
	
	//history.back();
	location.href = "${pageContext.request.contextPath}/user/main.do";
};
//-->
</script>
</head>
<body>

</body>
</html>