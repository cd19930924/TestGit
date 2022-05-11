<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>瀏覽常見問題 - DailyWarm</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/front-end/assets/healthcare.png" />
<!-- Bootstrap Icons-->
<link type="text/css"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
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
<link type="text/css" rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<link type="text/css" rel="stylesheet" href="/resources/demos/style.css">
<style type="text/css">
#accordion .ui-accordion-header { 
background: #FFEBCD; 
}
#accordion .ui-accordion-header.ui-state-active {
background: #c37d22;
border-color: #c37d22;
}

</style>
</head>
<body id="page-top">
	<!-- Navigation 導覽列 -->
	<nav class="navbar navbar-expand-lg navbar-light fixed-top1 py-3"
		id="mainNav">
		<div class="container px-4 px-lg-0">
			<a class="navbar-brand" href="#page-top">Daily Warm</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto my-2 my-lg-0">
					<li class="nav-item"><a class="nav-link" href="#">首頁</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							關於我們 </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#">最新消息</a></li>
							<li><a class="dropdown-item" href="#">網站簡介</a></li>
							<li><a class="dropdown-item" href="#">常見問題</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							服務項目 </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#">照護</a></li>
							<li><a class="dropdown-item" href="#">餐點</a></li>
							<li><a class="dropdown-item" href="#">派車</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link" href="#">登入/註冊</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Services 服務內容 -->
	<section class="pt-5 pb-5">
		<div class="container">
			<div class="row w-100">
				<div class="col-lg-12 col-md-12 col-12">
					<h2 class="mb-2 mt-5 fw-bold">常見問題FAQ</h2>
					<jsp:useBean id="faq" scope="page"
						class="com.infomanage.model.InfoService" />

					<div class="table-responsive">
						<div id="accordion">
							<c:forEach var="newslist" items="${faq.validFAQ}">
								<h3>${newslist.name}</h3>
								<div>
									<p>${newslist.content}</p>
								</div>
							</c:forEach>
						</div>
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
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#accordion").accordion({
				collapsible : true
			});
		});
	</script>
</body>

</html>