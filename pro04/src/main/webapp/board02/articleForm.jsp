<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,sec03.brd02.*"
    isELIgnored="false"
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기창</title>
<script src="http://code.jquery.com/jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
	/* 제이쿼리 이용해 이미지 파일첨부시 미리보기 기능 구현 */
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function (e) {
					$('#preview').attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
		function backToList(obj) {
			obj.action = "${contextPath}/board02/listArticles.do"
			obj.submit();
		}
	</script>
<title>새글쓰기창</title>
</head>
<body>
<h1 style="text-align:center">새글쓰기</h1>
<form name="articleForm" enctype="multipart/form-data"
action ="${contextPath}/board2/addArticle.do" method="post">
<!-- action='새글 등록'을 요청 enctype=파일 업로드 기능  -->
	<table board="1" align="center">
			<tr>
				<td align="right">글제목: </td>
				<td colspan="2"><input type="text" size="67" maxlength="500" name="title" /></td>
			</tr>
			<tr>
				<td align="right" valign="top">글내용: </td>
				<td colspan="2"><textarea name="conten" rows="10" cols="65" maxlength="4000"></textarea></td>
			</tr>
			<tr> <!-- 첨부한 이미지 미리보기 -->
				<td align="right">이미지파일첨부: </td>
				<td colspan="2"><input type="file" name="imageFileName" onchange="readURL(this);" /></td>
				<td><img id="preview" src="#" width="200" height="200"></td>
			</tr>
			<tr>
				<td align="right">글제목: </td>
				<td colspan="2">
				<input type="submit" value="글쓰기" />
				<input type="button" value="목록보기" onClick="backToList(this.form)" />
				</td>
			</tr>
	</table>
</form>
</body>
</html>