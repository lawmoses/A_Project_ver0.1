<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<% response.sendRedirect(request.getContextPath()+"/board/list"); %>

		<!-- 주석:
		여기서 실행후에 
		http://localhost:8080/JSP_model2Board/view/Index.jsp 
		이 주소 화면에서 ok가 나오고
		http://localhost:8080/JSP_model2Board/board/index
		view까지 삭제하고 board/index 입력하였다.
		이 화면에서도 ok가 나온다 
		-->
</body>
</html>