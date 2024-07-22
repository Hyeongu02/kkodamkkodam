<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../include/header.jsp"%>


<div id="wrapped">
	<div class="modify-page">
		<h3>회원 정보 조회/수정</h3>
		<div class="modify-box">
			<div>
				<i class="fa-regular fa-user user-icon1"></i> <input type="text"
					class="modify-input id-input" value="he11oworld" readonly>
				<i class="fa-solid fa-lock user-icon2"></i> <input type="password"
					placeholder=" 비밀번호" class="modify-input"> <i
					class="fa-solid fa-lock user-icon3"></i> <input type="password"
					placeholder=" 비밀번호 확인" class="modify-input"> <i
					class="fa-regular fa-user user-icon4"></i> <input type="text"
					class="modify-input" value="홍길동"> <i
					class="fa-regular fa-envelope user-icon5"></i> <input type="email"
					class="modify-input" value="he11oworld@naver.com"> <i
					class="fa-solid fa-phone user-icon6"></i> <input type="tel"
					class="modify-input" value="01036361717">
			</div>
		</div>
		<div>
			<button type="button" onclick="location.href = '#'"
				class="modify-btn">취소</button>
			<button type="button" onclick="location.href = '#'"
				class="modify-btn check-btn">저장</button>
		</div>
	</div>
</div>
<%@ include file="../include/footer.jsp"%>