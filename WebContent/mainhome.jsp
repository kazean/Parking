<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mainhome.jsp</title>
</head>
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
</script>
<style>

article {font-family: 'Open Sans', sans-serif;}

/* 배경 */
.background {
    position:relative;
    background-image: url("stock.jpg");
    background-size: 100%;
    background-repeat: no-repeat;
    top:0;
    left:0;
    bottom:0;
    right:0;
    height: auto;
    min-height: 1500px;
}
/* 스케쥴체크박스 */
.selectschedule{
 width:800px; 
 height:100px; 
 border: 1px solid;
 border-radius: 50px;
 border-color:#F2B210;  
 margin:auto; 
 padding:30px; 
 position:absolute;
 top:30%;  
 left:50%;  
 background:#F2B210;  
 transform:translate(-50%, -50%)}


/* 메인 텍스트그룹 */
.content {
    /* color: #f2f2f2;  흰색*/
    position:absolute;
    top:10%;  
    left:0%; 
    position: absolute;
    margin-left: 38.5%;
    margin-top:30%;
    text-align: center;
    max-width: 1200px;
}

/* 큰글씨 */
.content h1 {
    font-family: "Exo 2","Helvetica Neue",Helvetica,sans-serif;
    text-transform: uppercase;
    font-weight: 800;
    font-size: 50px;
    margin: 1.25em 0 0.2em;
    letter-spacing: -2px;
}

/* 작은글씨 */
.content p {
    margin: 0 0 1.313em;
    font-weight: 800;
    font-size: 18px;
}

/* 버튼 */
/* #content #your-move {
    text-decoration: none;
    text-transform: uppercase;
    color: #f2f2f2;
    border: 1px solid #ac1100;
    border-color: #b43322;
    background-color: #b43322;
    display: inline-block;
    padding: 19px;
    font-size: 19px;
}
#content #your-move:hover {
    border-color: #891e1b;
    background-color: #891e1b;
} */

</style>

<body>
  <header></header>
  <article>
  
  <div class="background">
  
  <!-- 메인화면 시간체크 박스 시작 -->
  <div class="selectschedule"></div>
  <!-- 메인화면 시간체크 박스 끝 -->
  
  <!-- 메인화면 텍스트 시작 -->
  <div class="content">
      <h1>즐거운 주차생활</h1>
      <p>누구보다 쉽고 빠르게, 가까운 주차장을 찾아 들어가쟈!<br>
                 KITRI parking을 이용하는 나야말로 진정한 승자!!!! </p>
      <a href="#home" id="your-move"><strong>start here</strong></a>
  </div> 
  <!-- 메인화면 텍스트 끝 -->
  
  </div>
  </article>
  
  <footer></footer>
</body>
</html>