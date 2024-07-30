<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<%@ include file="../include/header.jsp" %>    
    <div id="wrapped">
        <form action="post_regi.board" method="post">
	        <input type="hidden" name="boardId" value="${boardId}">
	        <div class="form-group title">
	            <label for="title">글 제목</label>
	            <div class="input-group">
	                    <input type="text" name=title class="form-control" placeholder="글제목을 입력해주세요" required>
	                     <!--  
	                    <div class="input-group-btn">
	                        <div class="dropdown">
	                            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown" style="line-height: 20px; font-size : 12px; min-width:150px;">게시판을 선택해주세요
	                            <span class="caret"></span></button>
	                            <ul class="dropdown-menu">
	                            <li><a href="#">자유게시판</a></li>
	                            <li><a href="#">질문게시판</a></li>
	                            <li><a href="#">코드공유게시판</a></li>
	                            <li><a href="#">정보공유게시판</a></li>
	                            <li><a href="#">미니개설신청</a></li>
	                            </ul>
	                        </div>
	                     
	                     </div>
	                	 -->
	            </div>
	        </div>
	     
	        <div class="form-group contents">
	            <label for="content">글 내용</label>
	            <textarea class="form-control" rows="20" name="content" style="resize:none;" required></textarea>
	        
	        </div>
	
	        <div class="btn-wrap btns">
	            <button type="submit" class="btn btn-default right" >등록</button>
	            <button type="button" class="btn btn-default right" onclick = "location.href='postList.board'">취소</button>
	        </div> 
	    </form>    
    </div>  

<%@ include file="../include/footer.jsp" %>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        // Get the dropdown button and items
        var dropdownButton = document.getElementById('dropdownMenuButton');
        var dropdownItems = document.querySelectorAll('.dropdown-menu .dropdown-item');

        // Add click event listeners to all dropdown items
        dropdownItems.forEach(function(item) {
            item.addEventListener('click', function(event) {
                event.preventDefault(); // Prevent the default action
                var selectedText = item.textContent; // Get the text of the clicked item
                dropdownButton.textContent = selectedText; // Update the button text
                dropdownButton.appendChild(document.createElement('span')).className = 'caret'; // Add caret back
            });
        });
    });
</script>