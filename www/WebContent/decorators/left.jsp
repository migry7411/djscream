<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="net.migry.dto.Member" %>
<%
	String root = request.getContextPath();
	Member member = (Member)session.getAttribute("login");
	
	if(member == null) {
%>
				<section class="login">
					<h1>LOGIN</h1>
					<form action="<%= root %>/user/login.do" method="post" onsubmit="return loginValidation(this)">
						<fieldset>
							<legend>로그인 폼</legend>
							<p>
								<label for="user_id">ID</label>
								<input type="text" id="user_id" name="userid" required placeholder="아이디">
							</p>
							<p>
								<label for="user_pw">PW</label>
								<input type="password" id="user_pw" name="passwd" required placeholder="비밀번호">
							</p>
							<button type="submit">로그인</button>
						</fieldset>
					</form>
					<ul class="member_info">
						<li><a href="<%= root %>/user/member/insertMember.do">회원가입</a></li>
						<li><a href="<%= root %>/user/member/findId.do">아이디 찾기</a></li>
						<li><a href="<%= root %>/user/member/findPasswd.do">비밀번호 찾기</a></li>
					</ul>
				</section>
				<section class="banner">
					<h1 class="hidden">배너</h1>
					<a href="http://blog.naver.com/migry" target="_blank">Love Deficiency Syndrome</a>
					<a href="http://www.cyworld.com/migry7411" target="_blank">Inner Peace...</a>
				</section>
<%
	} else {
%>
				<section class="login">
					<h1>WELCOME!!</h1>
					<h2><%= member.getNickname() %>(<%= member.getUserid() %>)</h2>
					<ul class="member_info">
						<li><a href="<%= root %>/user/logout.do">로그아웃</a></li>
						<li><a href="<%= root %>/user/member/updateMember.do?userid=<%= member.getUserid() %>">회원 정보 수정</a></li>
						<li><a href="<%= root %>/user/member/deleteMember.do?userid=<%= member.getUserid() %>">회원 탈퇴</a></li>
					</ul>
				</section>
				<section class="banner">
					<h1 class="hidden">배너</h1>
					<a href="http://blog.naver.com/migry" target="_blank">Love Deficiency Syndrome</a>
					<a href="http://www.cyworld.com/migry7411" target="_blank">Inner Peace...</a>
				</section>
<%
	}
%>