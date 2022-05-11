<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
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
                            <li><a class="dropdown-item" id="logout" onclick="logout1()">登出</a></li>
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
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-lg-8 col-xl-6 text-center">
					<h2 class="mt-0">派車接駁預約</h2>
					<hr class="divider" />
					<p class="text-muted mb-5">請選擇您的乘車地點與目的地，並選擇日期及時間</p>
				</div>
			</div>
			<div class="row gx-4 gx-lg-5 justify-content-center mb-5">
				<div class="col-lg-6">
				<%-- 錯誤表列 --%>
							<div id="output">
								<c:if test="${not empty errorMsgs}">
									<font style="color: red"><i class="fa-solid fa-circle-exclamation"></i> 請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<p style="color: red">${message}</p>
										</c:forEach>
									</ul>
								</c:if>
							</div>
					<!-- * * * * * * * * * * * * * * *-->
					<!-- * * SB Forms Contact Form * *-->
					<!-- * * * * * * * * * * * * * * *-->
					<!-- This form is pre-integrated with SB Forms.-->
					<!-- To make this form functional, sign up at-->
					<!-- https://startbootstrap.com/solution/contact-forms-->
					<!-- to get an API token!-->
					<form id="contactForm"  METHOD="post" ACTION="driveorderApply.do" name="form1" >
						<!-- Name input-->
						<div class="form-floating mb-3">
							<input class="form-control" id="from" type="text"
								name="startPoint" placeholder="輸入乘車地點..."
								data-sb-validations="required" onfocus="mapStart(this)"/> 
								<label for="from"><i class="fa-solid fa-location-dot"></i> 乘車地點</label>
							<div class="invalid-feedback" data-sb-feedback=from:required">必須輸入乘車地點.</div>
						</div>
						<!-- Email address input-->
						<div class="form-floating mb-3">
							<input class="form-control" id="to" type="text" name="endPoint"
								placeholder="輸入目的地" data-sb-validations="required" onfocus="mapStart(this)"/>
								<label for="to"><i class="fa-solid fa-location-pin"></i> 目的地</label>
							<div class="invalid-feedback" data-sb-feedback="to:required">必須輸入目的地.</div>
						</div>
						
						<div class="form-floating mb-3">
							<input class="form-control" id="orderAmount" type="text"
								name="orderAmount" readonly="readonly" /> 
								<label for="orderAmount"><i class="fa-solid fa-comment-dollar"></i> 乘車費用</label>
						</div>
						<div class="form-floating mb-3">
							<input class="form-control" id="distance" type="text"
								name="distance" readonly="readonly" />
							<label for="distance"><i class="fa-solid fa-car"></i> 預計乘車距離</label>
						</div>
						<div class="form-floating mb-3">
							<input class="form-control" id="maybetime" type="text" 
							name="mayBeTime" readonly="readonly" /> 
							<label for="maybetime"><i class="fa-solid fa-clock"></i> 預計乘車時間</label>
						</div>
						<!-- Phone number input-->
						<div class="form-floating mb-3">
							<input class="form-control" id="sendDriveDate" type="date"
								name="sendDriveDate" placeholder="選擇日期"
								data-sb-validations="required" /> <label for="sendDriveDate">日期</label>
							<div class="invalid-feedback"
								data-sb-feedback="sendDriveDate:required">必須選擇日期.</div>
						</div>
						
					
						<!-- Message input-->
						<div class="form-floating mb-3">
							<select class="form-control" id="sendDriveTime"
								name="sendDriveTime"></select>
							<label for="sendDriveTime"><i class="fa-regular fa-clock"></i> 時間</label>
						</div>
						
						<div class="form-floating mb-3">
							<input class="form-control" id="contactName" type="text"
								name="contactName" placeholder="輸入聯絡人姓名..."
								data-sb-validations="required" />
								<label for="contactName"><i class="fa-solid fa-person"></i> 聯絡人姓名</label>
							<div class="invalid-feedback" data-sb-feedback=contactName:required">必須輸入聯絡人姓名.</div>
						</div>
						<div class="form-floating mb-3">
							<input class="form-control" id="from" type="tel"
								name="contactNumber" placeholder="輸入聯絡人電話..."
								data-sb-validations="required" />
								<label for="contactNumber"><i class="fa-solid fa-phone"></i> 聯絡人電話</label>
							<div class="invalid-feedback" data-sb-feedback=contactNumber:required">必須輸入聯絡人電話.</div>
						</div>
						
						<!-- Submit success message-->
						<!---->
						<!-- This is what your users will see when the form-->
						<!-- has successfully submitted-->
						<div class="d-none" id="submitSuccessMessage">
							<div class="text-center mb-3">
								<div class="fw-bolder">Form submission successful!</div>
								To activate this form, sign up at <br /> <a
									href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
							</div>
						</div>
						<!-- Submit error message-->
						<!---->
						<!-- This is what your users will see when there is-->
						<!-- an error submitting the form-->
						<div class="d-none" id="submitErrorMessage">
							<div class="text-center text-danger mb-3">Error sending
								message!</div>
						</div>
						<!-- Submit Button-->
						<div class="d-grid">
							<input type="hidden" name="action" value="apply">
							<input class="btn btn-primary btn-xl" id="submitButton"
								type="submit" value="送出預約">
						</div>
					</form>
				</div>
				<div class="col-lg-6" id= "googleMap" style="width:50%;height: 650px;">
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
    <script src="../js/logout.js"></script>
</body>

</html>