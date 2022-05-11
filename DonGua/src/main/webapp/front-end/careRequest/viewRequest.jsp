<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Daily Warm照護媒合</title>
<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->
<link rel="stylesheet" type="text/css" href="datetimepicker/jquery.datetimepicker.css" />
<script src="datetimepicker/jquery.js"></script>
<script src="datetimepicker/jquery.datetimepicker.full.js"></script>

<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/healthcare.png" />
<link href="https://fonts.googleapis.com/earlyaccess/cwtexyen.css" rel="stylesheet">
<!-- Bootstrap Icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic"
	rel="stylesheet" type="text/css" />
<!-- SimpleLightbox plugin CSS-->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../css/styles.css" rel="stylesheet" />

<style>
body,button, input,select, textarea,h1 ,h2, h3, h4, h5, h6 {
  font-family:cwtexyen, Merriweather;
}
.text_center {
	text-align: center;
}
</style>
</head>
<body>
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
	
	<main style="background-color: #F0DC84;">
		<div>
			<a href="<%= request.getContextPath() %>/front-end/careRequest/requestMgt.jsp">返回照護需求單管理</a>
		</div>
		<div class="text_center">
				<h1>需求單資訊</h1>
			</div>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div style="display: flex; margin-left: 20%; margin-right: 20%; height: 400px;">
			<div style="flex: 1; padding-top: 3%; border-radius: 20px 0px 0px 20px; border: 1px solid #000; border-top: none;border-bottom: none;border-left: none; background-color: #F7F3E3;">
				<div style="overflow: auto; word-break: break-all; height: 88%;">
					<h2 class="text_center" style="height: 10%;">個人資料</h2>
					
					<ul>
						<li>姓名：${careRequestVO.patientName}</li>
						
						<li>
							<c:if test="${careRequestVO.patientGender == 0}">性別：男</c:if> 
							<c:if test="${careRequestVO.patientGender == 1}">性別：女</c:if>
						</li>
						
						<li>年紀：${careRequestVO.patientAge}</li>
						
						<li>
							<c:if test="${careRequestVO.serviceType == 0}">服務地點類型：居家照護</c:if> 
       						<c:if test="${careRequestVO.serviceType == 1}">服務地點類型：醫院看護</c:if>
						</li>
						
						<li>服務地址：${careRequestVO.patientAddr}</li>
						
						<li>開始時間：${careRequestVO.startTime.toString().substring(0, 16)}</li>
						
						<li>結束時間：${careRequestVO.endTime.toString().substring(0, 16)}</li>
						
						<li>
							<c:if test="${careRequestVO.status == 0}">
       							需求單狀態：已取消
      						</c:if> 
							<c:if test="${careRequestVO.status == 1}">
       							需求單狀態：已轉為訂單
      						</c:if> 
							<c:if test="${careRequestVO.status == 2}">
       							需求單狀態：已刊登
      						</c:if> 
						</li>
						
						<li>成立時間：${careRequestVO.createTime.toString().substring(0, 16)}</li>
						
						<li>備註：${careRequestVO.note}</li>
					</ul>
				</div>
			</div>
			
			<div style="flex: 1; padding-top: 3%; border-radius: 0px 20px 20px 0px; background-color: #F7F3E3;">
				<h2 class="text_center" style="height: 10%;">服務明細</h2>
				<div style="overflow: auto; word-break: break-all; height: 88%;">
					<ul>
						<c:forEach var="requestTabPVO" items="${requestTabList}">
							<li>${requestTabPVO.serviceTabName}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div style="height: 50px;"></div>
	</main>
	
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>