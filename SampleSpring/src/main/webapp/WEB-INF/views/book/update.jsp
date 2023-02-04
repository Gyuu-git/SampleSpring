<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<title>도서관리시스템</title>
</head>
<body>
	<h3>책 수정</h3>
	<hr/>
	<form action="/book/update.do" method="post">
		<input type="hidden" name="bookId" value="${book.BOOK_ID }"/>
		<table border="1">
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="title" id="title" value="${book.TITLE }"/>
				</td>
			</tr>
			<tr>
				<td>카테고리</td>
				<td>
					<input type="text" name="category" id="category" value="${book.CATEGORY }"/>
				</td>
			</tr>
			<tr>
				<td>가격</td>
				<td>
					<input type="text" name="price" id="price" value="${book.PRICE }"/>
				</td>
			</tr>
		</table>
		<input type="submit" value="수정"/>
	</form>
	<input type="button" value="목록" onclick="location.href='/book/list.do'"/>
</body>
</html>