<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
Integer driveOrderId = Integer.parseInt(request.getParameter("driveOrderId"));
pageContext.setAttribute("driveOrderId", driveOrderId);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Daily Warm派車申請</title>
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
<script src="https://kit.fontawesome.com/19fb92fb24.js" crossorigin="anonymous"></script>
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

<style type="text/css">
	i{
		color:#9e5b20
	}
	label{
		color:#9e5b20
	}
	body,button, input,select, textarea,h1 ,h2, h3, h4, h5, h6 {
  		font-family:cwtexyen, Merriweather;
	}
	th{
		color:#9e5b20;
		background:#f5e7ba;
		border:1px solid white;
	}
	td{
		background:#f7efd2;
		border:1px solid white;
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
	<!-- Masthead ABOUT US -->

	<!-- Services 服務內容 -->
	<section class="page-section" id="portfolio" style="background-color:#f1d683">
		<div class="container px-4 px-lg-5" style="margin-top:30px">
			<h1 class="h3 mb-2 text-gray-800">派車訂單詳細資訊</h1>
			<!-- DataTales Example -->
			<div class="card shadow mb-4">
				<div>
					<div class="table-responsive">
						<table id="dataTable" width="100%"
							cellspacing="0" style="text-align:center">
						</table>
					</div>
				</div>
			</div>		
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-lg-4 text-center mb-5 mb-lg-0">
					<i class="bi-phone fs-2 mb-3 text-muted"></i>
					<div>無障礙專車接駁服務</div>
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
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- SimpleLightbox plugin JS-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script> -->
	<!-- Jquery -->
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCyp3iiOUV2ARrnux538EqsW3srekIBUyM&language=zh_tw&libraries=places"></script>
	<script src="../js/app.js"></script>
	<script src="../js/apply.js"></script>
	<script src="../js/utility.js"></script>
    <script src="../js/afterLogin.js"></script>
    <script type="text/javascript">
    $(document).ready(function () {
    	let driveOrderId = "${driveOrderId}";
        $.ajax({
            url: "driveorder.do", /*GET格式*/
            type: "POST",
            data:{
            	action:"Get_Order_Info",
            	drverOrderId:driveOrderId,
            },
            success: function (data) {
                var jsonObj = JSON.parse(data);
                for (let option of jsonObj) {
                	let se = (option.sendDriveDate).substring(0,10);
                	let st = (option.sendDriveTime).substring(9,11)+" "+(option.sendDriveTime).substring(0,8);
                	$("#dataTable").html(
                			`<table style="width:100%">`+
                			`<tr><th>訂單編號</th><td>${"${option.drverOrderId}"}</td><th>預計里程數</th><td>${"${option.distance}"}</td></tr>`+
                			`<tr><th>司機ID</th><td>${"${option.driverId}"}</td><th>訂單金額</th><td>${"${option.orderAmount}"}</td></tr>`+
                			`<tr><th>司機姓名</th><td>${"${option.driverName}"}</td><th>派車日期</th><td>${"${se}"}</td></tr>`+
                			`<tr><th>司機手機</th><td>${"${option.driverPhone}"}</td><th>派車時間</th><td>${"${st}"}</td></tr>`+
                			`<tr><th>司機車牌號碼</th><td>${"${option.carNumber}"}</td><th>聯絡人姓名</th><td>${"${option.contactName}"}</td></tr>`+
                			`<tr><th >出發點</th><td nowrap="nowrap">${"${option.startPoint}"}</td><th>聯絡電話</th><td>${"${option.contactNumber}"}</td></tr>`+
                			`<tr><th>目的地</th><td nowrap="nowrap">${"${option.endPoint}"}</td><th>訂單狀態</th><td>`+
                			`${"${option.orderStatus=='0'?'待執行':''}"}`+
                            `${"${option.orderStatus=='1'?'執行中':''}"}`+
                            `${"${option.orderStatus=='2'?'已完成':''}"}`+
                            `${"${option.orderStatus=='3'?'已結單':''}"}`+
                            `${"${option.orderStatus=='4'?'取消申請中':''}"}`+
                            `${"${option.orderStatus=='5'?'已取消':''}"}`+
                			`<tr><th>備註</th><td style="word-break:break-all" colspan="3">${"${option.driveFeedback==null?'尚未填寫':(option.driveFeedback)}"}</td></tr>`+
                			`</table>`
               	 	);
               }
            }
        });
    });
    </script>
</body>

</html>