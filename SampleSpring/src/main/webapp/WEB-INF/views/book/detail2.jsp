<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<title>도서관리시스템</title>
</head>
<body>
	<h3>책 상세페이지</h3>
	<table>
		<tr>
			<td>제목</td>
			<td>${book.title }</td>
		</tr>
		<tr>
			<td>카테고리</td>
			<td>${book.category }</td>
		</tr>
		<tr>
			<td>가격</td>
			<td>${book.price }</td>
		</tr>
		<tr>
			<td>입력일</td>
			<td>${book.insertDate }</td>
		</tr>
		<tr>
			<td>
				<c:forEach items="${book.bookFileList }" var="bookFile">
					<a href="${bookFile.fileSavepath }" download>
						${bookFile.fileName }
					</a> (${bookFile.fileFancysize })
				</c:forEach>
			</td>
		</tr>
	</table>
	<a href="/book/update.do?bookId=${book.bookId }">수정</a>
	<a href="/book/list.do">목록</a>
	
	<form method="post" action="/book/delete.do" id="delForm">
		<input type="hidden" name="bookId" value="${book.bookId }"/>
		<input type="submit" value="삭제" id="delBtn"/>
	</form>
</body>
</html>








