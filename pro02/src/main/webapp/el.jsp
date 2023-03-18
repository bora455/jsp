<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%
    isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${10 + 20}
	${10 * 20}
	${true && false}
	${10 >= 20}
	${user.name == "홍길동"? "교수" : "학생"}
</body>
</html>