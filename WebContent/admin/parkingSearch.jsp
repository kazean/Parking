<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>parkingSearch</title>
</head>

<body>
	<select name = "searchSort">
	    <option value = "all">전체</option>
	    <option value = "no">코드순</option>
	    <option value = "name">이름순</option>
	    <option value = "name">주소순</option>
	    <option value = "name">등록일자순</option>
  	</select> 
	<br>
	<select name = "searchItem">
	    <option value = "all">전체</option>
	    <option value = "no">코드</option>
	    <option value = "name">이름</option>
  	</select>    
  	<input type="search" name="searchValue"> <input type="button" name="search" value="검색">
	<br>
	<input type="button" name="searchDetail" value="상세검색">
</body>
</html>