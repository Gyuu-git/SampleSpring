<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<title>도서관리시스템</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>책제목</th>
			<th>카테고리</th>
			<th>가격</th>
		</tr>
		<c:choose>
			<c:when test="${empty list }">
				<tr>
					<td colspan="4">조회하신 게시글이 존재하지 않습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="book">
					<tr>
						<td>${book.BOOK_ID }</td>
						<td>
							<a href="/book/detail.do?bookId=${book.BOOK_ID}">
								${book.TITLE }
							</a>
						</td>
						<td>${book.CATEGORY }</td>
						<td>${book.PRICE }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	<a href="/book/form.do">등록</a>
</body>
</html>