<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Daily Warm照護媒合</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/healthcare.png" />
    <!-- Bootstrap Icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic"
        rel="stylesheet" type="text/css" />
    <!-- SimpleLightbox plugin CSS-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/earlyaccess/cwtexyen.css" rel="stylesheet">
</head>
<style>
body,button, input,select, textarea,h1 ,h2, h3, h4, h5, h6 {
  font-family:cwtexyen, Merriweather;
} 

</style>
<body id="page-top">
    <!-- Navigation 導覽列 -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
        <div class="container px-4 px-lg-0">
            <a class="navbar-brand" href="index.jsp">Daily Warm</a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ms-auto my-2 my-lg-0">
                    <li class="nav-item"><a class="nav-link" href="index.jsp">首頁</a></li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            關於我們
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="news/newspage.jsp">最新消息</a></li>
                            <li><a class="dropdown-item" href="info/serviceIntro.jsp">網站簡介</a></li>
                            <li><a class="dropdown-item" href="info/faq.jsp">常見問題</a></li>
                        </ul>
                    </li>
                    <li id="order" class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            歷史訂單
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="careRequest/requestMgt.jsp">照護</a></li>
                            <li><a class="dropdown-item" href="meal/memorder.jsp">餐點</a></li>
                            <li><a class="dropdown-item" href="driveorder/MyDriveOrder.html">派車</a></li>
                        </ul>
                    </li>
                    <li id="login" class="nav-item"><a class="nav-link" href="login.html">登入/註冊</a></li>
                    <li id="logout" class="nav-item"><a class="nav-link" onclick="logoutbef()">登出</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- Masthead ABOUT US -->
    <header class="masthead" id="about">
        <div class="container px-4 px-lg-5 h-100">
            <div class="row gx-4 gx-lg-5 h-100 align-items-center text-lift">
                <div class="col-lg-8 align-self-end">
                    <h1 style="font-weight:bold; text-shadow:0px 0px 10px #cecf91, 0px 0px 20px #d7d8cb, 0px 0px 30px #767770, 0px 0px 40px #91917c; color: #ffe8a3;">DailyWarm</h1>
                </div>
                <div class="col-lg-8 align-self-baseline">
                    <h2 style="color: #ffdeb4; text-shadow: 2px 2px 2px #27271e;" class="title">以日常的溫暖 <span style="color: #ff4949; text-shadow: 0px 0px 10px #9d6262, 0px 0px 20px #da5b5b, 0px 0px 30px #ff1a1a, 0px 0px 40px #e48484">❤</span> <span>提供最適合的照護服務</span></h2>
                </div>
            </div>
        </div>
    </header>
    <!-- Services 服務內容 -->
    <section class="page-section bg-primary" id="services">
        <div class="container">
            <div class="text-center">
                <h2 class="section-heading text-uppercase text-brown">服務項目</h2>
                <h5 class="section-subheading text-yellow2">提供給您三大項的服務</h5>
            </div>
            <div class="serforthree text-center">
                <div class="ser">
                    <span class="fa-stack fa-4x">
                        <i class="fas fa-circle fa-stack-2x text-brown"></i>
                        <i class="fas fa-utensils fa-stack-1x fa-inverse"></i>
                    </span>
                    <h4 class="my-3">餐點</h4>
                    <p class="text-brown">多樣方案</p>
                    <a class="btn btn-primary fw-bold" href="meal/browseMeal.jsp" role="button">預約</a>
                </div>
                <div class="ser">
                    <span class="fa-stack fa-4x">
                        <i class="fas fa-circle fa-stack-2x text-brown"></i>
                        <i class="fas fa-hand-holding-heart fa-stack-1x fa-inverse"></i>
                    </span>
                    <h4 class="my-3">照護</h4>
                    <p class="text-brown">專業服務</p>
                    <a class="btn btn-primary fw-bold" href="carersearch/carerSearch.jsp" role="button">尋找</a>
                </div>
                <div class="ser">
                    <span class="fa-stack fa-4x">
                        <i class="fas fa-circle fa-stack-2x text-brown"></i>
                        <i class="fas fa-car fa-stack-1x fa-inverse"></i>
                    </span>
                    <h4 class="my-3">派車</h4>
                    <p class="text-brown">無障礙接送</p>
                    <a class="btn btn-primary fw-bold" href="driveorder/browsecar.html" role="button">預約</a>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer class="bg-light py-5">
        <div class="container px-4 px-lg-5">
            <div class="small text-center text-muted">Copyright &copy; 2021 - Daily Warm</div>
        </div>
    </footer>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- SimpleLightbox plugin JS-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.js"></script>
    <!-- Core theme JS-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="js/utility.js"></script>
    <script src="js/scripts.js"></script>
    <script src="js/index.js"></script>
    
    <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
    <!-- * *                               SB Forms JS                               * *-->
    <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
    <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
    <!-- <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script> -->
</body>

</html>