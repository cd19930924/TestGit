<%@page import="com.utils.DecodeCookieUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.collection.model.CollectionService"%>
<%@page import="com.collection.model.CollectionVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
int id = (int) DecodeCookieUtil.getMemId(request);

CollectionService svc = new CollectionService();
List<CollectionVO> collectedList = new CollectionService().getCollectedCarer(id);
pageContext.setAttribute("collectedList", collectedList);
%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>瀏覽收藏照護員 - DailyWarm</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon"
	href="../front-end/assets/healthcare.png" />
<!-- Bootstrap Icons-->
<link type="text/css" rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
<!-- Font Awesome icons (free version)-->
<script type="text/javascript"
	src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link type="text/css"
	href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700"
	rel="stylesheet" />
<link type="text/css"
	href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic"
	rel="stylesheet" type="text/css" />
<!-- SimpleLightbox plugin CSS-->
<link type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link type="text/css"
	href="${pageContext.request.contextPath}/front-end/css/styles.css"
	rel="stylesheet" />

<link type="text/css" rel="stylesheet"
	href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/css/jquery.datetimepicker.css" />

<style type="text/css">
div.gallery {
  margin: 5px;
  border: 1px solid #ccc;
  float: left;
  width: 180px;
}

div.gallery:hover {
  border: 1px solid #777;
}

div.gallery img {
  width: 100%;
  height: 60%;
}

div.desc {
  padding: 15px;
  text-align: center;
}
.button {
  background-color: hsl(27, 92%, 77%);
  color: #703e05;
  font-size: 14px;
  border-radius: 8px;
  border: 2px;
  width: 80px;
  transition-duration: 0s;
}

.button:hover {
  background-color: #f07c10;
  color: black;
}
</style>
</head>
<body id="page-top">
	<!-- Navigation 導覽列 -->
<!-- Navigation 導覽列 -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
        <div class="container px-4 px-lg-0">
            <a class="navbar-brand" href="../afterlogin.jsp">Daily Warm</a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ms-auto my-2 my-lg-0">
                 <!-- 首頁 -->
                    <li class="nav-item"><a class="nav-link" href="../afterlogin.jsp">首頁</a></li>
                    <!-- 關於我們 -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            關於我們
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="../news/newspage.jsp">最新消息</a></li>
                            <li><a class="dropdown-item" href="../info/serviceIntro.jsp">網站簡介</a></li>
                            <li><a class="dropdown-item" href="../info/faq.jsp">常見問題</a></li>
                        </ul>
                    </li>
                    <!-- 歷史訂單 -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            歷史訂單
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="../careRequest/requestMgt.jsp">照護</a></li>
                            <li><a class="dropdown-item" href="../meal/memorder.jsp">餐點</a></li>
                            <li><a class="dropdown-item" href="../driveorder/MyDriveOrder.html">派車</a></li>
                        </ul>
                    </li>
                    <!-- 通知 -->
                    <li class="nav-item"><a class="nav-link" href="../mailbox/noticeCenter.jsp">通知</a></li>
                    <!-- 會員 -->
                    <li class="nav-item dropdown">
                        <a class="nav-link" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                            aria-expanded="false">
                            <i class="fas fa-user-circle fa-2x "></i>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                          <li><a class="dropdown-item" id="switchAcct"href="../Carer.jsp">切換帳號</a></li>
                            <li><a class="dropdown-item" id="applyCarer" href="../carermemapply/CarerMemApplyPage.jsp">申請成為照護員</a></li>
                            <li><a class="dropdown-item" id="memberData" href="../member/setMemData.html">會員資料設定</a></li>
                            <li><a class="dropdown-item" id="logout">登出</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
	<section class="pt-5 pb-5">
	<div class="container">
<div class="col-lg-12 col-md-12 col-12">
			<div class="row w-100">
			<h2 class="mb-2 mt-5 fw-bold">收藏列表</h2>
		<span style="display:none;">目前您的id為<%=id%></span>
		
		<a href="<%=request.getContextPath()%>/front-end/carersearch/carerSearch.jsp"><i class="bi bi-search"></i>前往搜尋頁面</a>
	<div class="table-responsive">
		<c:forEach var="collect" items="${collectedList}">
			<div class="gallery">
			
			<a target="_blank" href="<%=request.getContextPath()%>/carer/carersearch?action=displayACarer&carerId=${collect.carerId}">
  			<img src="data:image/jpg;base64,${collect.carerPhoto}" alt="Cinque Terre" width="600" height="400">
  			</a>
				<div class="desc"><span style="display:none;">${collect.carerId}</span>
				${collect.carerSurname}${collect.carerGender}
				${collect.carerCounty}${collect.carerDist}
				${collect.collTime}
<%-- 				<div>服務次數 <%=svc.getServiceTimes(id,${collect.carerId}) %></div> --%>
				
				<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/carersearch/collection" onsubmit="return check();">
				<input type="text" name="memId" value="<%=id%>" style="display:none;"> 
				<input type="text" name="carerId" value="${collect.carerId}" style="display:none;"> 
				<input type="hidden" name="action" value="delete"> 
				<input type="submit" value="取消收藏" class="button">
			</FORM>
			
				</div>
			</div>
		</c:forEach>

</div>
</div>
</div>
	</div>
	</section>
		<!-- Footer -->
	<footer class="bg-light py-5">
		<div class="container px-4 px-lg-5">
			<div class="small text-center text-muted">Copyright &copy; 2021
				- Daily Warm</div>
		</div>
	</footer>
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="../js/utility.js"></script>
    <script src="../js/afterLogin.js"></script>
    <script type="text/javascript">
    function check(){
    	 if(!confirm("確定取消收藏照護員嗎？")){
   		  return false;
   	　　}
    	 alert('已取消收藏!');
    	return true;
    }
    </script>
</body>
</html>