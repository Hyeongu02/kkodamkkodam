<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../include/header.jsp"%>

	<div id="wrapped">
		<form action="loginForm.user" method="post">
        <div class="login-box">
			<div class="input-box">
                <input type="text" placeholder="아이디" class="input-id" name="id" value= <%=userId %>> required="required">
                <input type="password" placeholder="비밀번호" class="input-pw" name="pw" required="required">
            </div>
			<label>
				<input type="checkbox" class="id-save">아이디 기억하기
			</label>
            <button type="submit" class="check-btn" name="check" value="checked" >로그인</button>
			<div class="find-all">
				<a href="#">아이디 찾기 | </a>
				<a href="#">비밀번호 찾기</a>
			</div>
        </div>
        </form>
    </div>
    
<%@ include file="../include/footer.jsp"%>