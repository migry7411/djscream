<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="contents.board.admin.title" /></title>
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
 		, url: 'detailBoardCode.do'
 		, data: { "id" : id }
 		, success: function(data) {
 			$("#id").val(data.result.id);
 			$("#name").val(data.result.name);
 			$("#use_board").val(data.result.use_board);
 			$("#use_html_tag").val(data.result.use_html_tag);
 			$("#write_auth").val(data.result.write_auth);
 			$("#use_reply").val(data.result.use_reply);
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
	<h1><spring:message code="contents.board.admin.title" /></h1>
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
		<form action="saveBoard.do" method="post">
			<fieldset>
				<legend>개시판 코드 정보</legend>
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
						<select name="use_board" id="use_board">
							<option value="Y"><spring:message code="contents.common.use.y" /></option>
							<option value="N"><spring:message code="contents.common.use.n" /></option>
						</select>
					</span>
				</p>
				<p>
					<label class="lbl">HTML 태그 사용여부</label>
					<span>
						<select name="use_html_tag" id="use_html_tag">
							<option value="Y"><spring:message code="contents.common.use.y" /></option>
							<option value="N"><spring:message code="contents.common.use.n" /></option>
						</select>
					</span>
				</p>
				<p>
					<label class="lbl">쓰기 권한</label>
					<span>
						<select name="write_auth" id="write_auth">
							<option value="L">로그인 유저</option>
							<option value="A">관리자만</option>
						</select>
					</span>
				</p>
				<p>
					<label class="lbl">댓글 사용여부</label>
					<span>
						<select name="use_reply" id="use_reply">
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