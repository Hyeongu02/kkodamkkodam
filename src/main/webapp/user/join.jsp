<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../include/header.jsp"%>



<div id="wrapped">
	<div class="join-page">
		<h3>회원 가입 페이지</h3>
		<div class="join-box">
			<div>
				<i class="fa-regular fa-user user-icon1"></i> <input type="text"
					placeholder=" 아이디" class="join-input id-input">
				<button type="button" onclick="location.href = '#'"
					class="check-btn">중복 확인</button>
				<i class="fa-solid fa-lock user-icon2"></i> <input type="password"
					placeholder=" 비밀번호" class="join-input"> <i
					class="fa-solid fa-lock user-icon3"></i> <input type="password"
					placeholder=" 비밀번호 확인" class="join-input"> <i
					class="fa-regular fa-user user-icon4"></i> <input type="text"
					placeholder=" 닉네임" class="join-input"> <i
					class="fa-regular fa-envelope user-icon5"></i> <input type="email"
					placeholder=" 이메일 - 비밀번호 찾기 등 본인 확인용" class="join-input"> <i
					class="fa-solid fa-phone user-icon6"></i> <input type="tel"
					placeholder=" 전화번호" class="join-input">
			</div>
		</div>
		<div>
			<button type="button" onclick="location.href = '#'" class="join-btn">회원
				가입</button>
		</div>
	</div>
</div>
<%@ include file="../include/footer.jsp"%>