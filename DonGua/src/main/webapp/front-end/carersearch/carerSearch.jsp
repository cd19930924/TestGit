<%@page import="com.utils.DecodeCookieUtil"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
HttpSession session1 = request.getSession();
int memId = (int) DecodeCookieUtil.getMemId(request);
session1.setAttribute("memId", memId);
%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>搜尋照護員 - DailyWarm</title>
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
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	margin: auto 90px;
	padding: 50px 10px 10px;
	display: inline;
}

.service {
	height: 300px;
	background: url("assets/pexels-andrea-piacquadio-3768114.jpg")
}

.button {
	margin: 10px 5px 10px 100px;
	background-color: hsl(27, 92%, 77%);
	color: #703e05;
	font-size: 14px;
	border-radius: 8px;
	padding: 6px;
	border: 2px;
	width: 100px;
	transition-duration: 0s;
}

.buttonreset {
	margin: 10px 10px 10px 0px;
	background-color: hsl(27, 92%, 77%);
	color: #703e05;
	font-size: 14px;
	border-radius: 8px;
	padding: 6px;
	border: 2px;
	width: 100px;
	transition-duration: 0s;
}

.button:hover {
	background-color: #f07c10;
	color: black;
}
.buttonreset:hover {
	background-color: #f07c10;
	color: black;
}

select {
	padding: 6px;
	font-size: 14px;
	background: rgba(240, 196, 167, 0.692);
	color: rgb(128, 77, 11);
	border-radius: 8px;
}

option:not(:checked) {
	background-color: #fff;
}

.item {
	margin: 10px 10px 10px 100px;
	padding: 6px;
	font-size: 14px;
	color: grey;
	text-align: left 100px;
}

.selectdiv {
	margin: 10px 10px 10px 100px;
}
</style>

</head>
<body id="page-top">
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

			<div class="row w-100">

				<h4>在DailyWarm迅速尋找照護員</h4><span style="display:none;">目前您的id為<%=memId%></span>
				<div class="item">
				<span><a href="<%=request.getContextPath()%>/front-end/careRequest/postRequest.jsp"><i class="bi bi-file-earmark-post"></i>刊登需求單</a></span><span>｜</span>
				<span><a href="<%=request.getContextPath()%>/front-end/carersearch/collection.jsp"><i class="bi bi-star-fill"></i>查看收藏列表</a></span>
				</div>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/carer/carersearch" onsubmit="return validateMyForm();">

					<div class="item"><i class="bi bi-info-circle-fill"></i>服務類型</div>
					<div class="selectdiv">
						<select id="service" name="SERVICE_TYPE">
							<option value="">選擇服務類型</option>
							<option value="0">居家服務</option>
							<option value="1">醫院照護</option>
							<option value="2">皆可</option>

						</select>
					</div>
					<div class="item"><i class="bi bi-geo-alt-fill"></i>服務地區</div>

					<div class="selectdiv">
						<select id="county" name="county">
							<option value="">縣市</option>
						</select> <select id="district" name="district">
							<option value="">行政區</option>
						</select>
					</div>
					<input type="hidden" name="action"
						value="multipleCarersSearch">
					<button type="submit"
						class="button" ><i class="bi bi-search"></i>查詢</button>
					<button type="reset" value="Reset" class="buttonreset"><i class="bi bi-x-circle"></i>清除搜尋</button>
				</FORM>
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
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- SimpleLightbox plugin JS-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="../js/scripts.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/js/utility.js"></script>
	<script src="../js/afterLogin.js"></script>
	<script type="text/javascript">
	(() => {
	    $(document).ready(function(){
	        initOption('#county','county');/*載入縣市Select資料*/
	    })
	    $("#county").change(function(){/*點擊縣市select載入地區資料*/
	        initOption('#district','district',$(this).val())
	    })
	})();
	
   function validateMyForm()
   {
	   var service = document.querySelector('#service');
	   var county = document.querySelector('#county');
	   var dist = document.querySelector('#district');
	      if (service.value ==0 && county.value ==0 && dist.value ==0){
	    	  if(!confirm("您未選擇選項，將搜尋所有照護員")){
	    		  return false;
	    	　　}
	     	return true;
	 	   }
	   	 return true;
	   	 
	 }
    
</script>
</body>
</html>