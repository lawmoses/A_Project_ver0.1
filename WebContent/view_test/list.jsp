<!DOCTYPE html>
<html>
<title>W3.CSS</title>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>

<div class="w3-container" style="margin: 40px 100px;">
  <h2 style="text-align: center;">게시판 : ${boardid} 번 (전체 글:${count})</h2>

  <table class="w3-table-all w3-hoverable">
    <thead>
      <tr class="w3-teal">
        <th>순번</th>
        <th>보낸사람</th>
        <th>제목</th>
        <th>보낸일시</th>
      </tr>
    </thead>
    <tr>
      <td>3</td>
      <td>이그리(gree)</td>
      <td>
            <a href="/groupWare/message/receiveMsg.jsp">부장님 안녕하세요?</a>
      </td>
      <td>2018-03-25 16:08</td>
    </tr>
    <tr>
      <td>2</td>
      <td>이그리(gree)</td>
      <td>파일 확인 부탁드립니다</td>
      <td>2018-03-22 10:45</td>
    </tr>
    <tr>
      <td>1</td>
      <td>이그리(gree)</td>
      <td>회식 장소입니다</td>
      <td>2018-01-25 14:22</td>
    </tr>
  </table>
</div>
<!-- 하단 페이지 -->
   <div class="w3-center" style="margin-top: 20px; text-align: center;">
   <c:if test="${count>0}">
   <c:if test="${startPage > bottomLine }">
      <a href="adList?pageNum=${startPage - bottomLine}">[이전]</a> 
   </c:if>
   
   <c:forEach var="i" begin="${startPage}" end="${endPage}">   
   <a href="adList?pageNum=${i}">
   <c:if test="${i !=  currentPage}">[${i}]</c:if>
   <c:if test="${i ==  currentPage}">
   <font color='red'>[${i}]</font></c:if></a>
   </c:forEach>
   
   <c:if test="${endPage < pageCount}">
      <a href="adList?pageNum=${startPage+bottomLine}">[다음]</a>
   </c:if>   
   </c:if>
   </div><br>
   


<!-- 회원 검색 -->
   <form name ="search" method="post" style="text-align: center;">
      <select name="keyField" style="height: 30px;">
	      <option>선택</option>
	      <option value="m_id">보낸사람</option>
	      <option value="m_name">내용</option>
      </select>
	      <input type="text" size="20" name="keyWord">
	      <input type="button" value="검색" onclick="mem_search(form)" />&nbsp;
   </form>
</body>
</html> 