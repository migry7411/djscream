<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="contents.music.admin.title" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/board_code.css">
<script type="text/javascript" charset="UTF-8">
<!--
var num = 0;

function selectRow(n, id) {
	$("#row" + num).css("background-color", "transparent");
	$("#row" + n).css("background-color", "#333");
	num = n;
	
	$.ajax({
		type: 'POST'
 		, dataType: 'JSON'
 		, async: true
 		, url: 'detailMusicCode.do'
 		, data: { "id" : id }
 		, success: function(data) {
 			$("#id").val(data.result.id);
 			$("#name").val(data.result.name);
 			$("#visible").val(data.result.visible);
		}
		, error: function(data, status, err) {
			alert(err);
		}
	});
}
//-->
</script>
</head>
<body>
	<h1><spring:message code="contents.music.admin.title" /></h1>
	<div class="list">
		<div class="row_header">
			<ul>
				<li><spring:message code="contents.board.admin.code" /></li>
				<li><spring:message code="contents.board.admin.name" /></li>
			</ul>
		</div>
		
		<c:if test="${count < 1}">
		<div class="rows"><spring:message code="messages.common.list.empty" /></div>
		</c:if>
		
		<c:if test="${count > 0}">
		
		<c:set var="num" value="1" />
			
		<c:forEach var="row" items="${list}">
		<div id="row${num}" class="rows" onmousedown="selectRow(${num}, '${row.id}')">
			<ul>
				<li>${row.id}</li>
				<li>${row.name}</li>
			</ul>
		</div>
		<c:set var="num" value="${num + 1}" />
		</c:forEach>
		
		</c:if>
	</div>
	<div class="detail">
		<form action="saveMusic.do" method="post">
			<fieldset>
				<legend>으악개시판 코드 정보</legend>
				<p>
					<label for="id" class="lbl"><spring:message code="contents.board.admin.code" /></label>
					<span><input type="text" id="id" name="id" readonly="readonly"></span>
				</p>
				<p>
					<label for="name" class="lbl"><spring:message code="contents.board.admin.name" /></label>
					<span><input type="text" id="name" name="name" required></span>
				</p>
				<p>
					<label class="lbl"><spring:message code="contents.board.admin.use" /></label>
					<span>
						<select name="visible" id="visible">
							<option value="Y"><spring:message code="contents.common.use.y" /></option>
							<option value="N"><spring:message code="contents.common.use.n" /></option>
						</select>
					</span>
				</p>
				<button type="reset"><spring:message code="contents.common.add" /></button>
				<button type="submit"><spring:message code="contents.common.save" /></button>
			</fieldset>
		</form>
	</div>
</body>
</html>