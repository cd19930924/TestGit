<%@page import="com.utils.DecodeCookieUtil"%>
<%@page import="com.carermem.model.CarerMemVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
// int id = (int) DecodeCookieUtil.getMemId(request);

HttpSession session1 = request.getSession();
int memId = (int) session1.getAttribute("memId");
%>
<jsp:useBean id="carerSearchResult" scope="request"
	type="java.util.List<CarerMemVO>" />
<jsp:useBean id="carerSvc" scope="page"
	class="com.carermem.model.CarerMemService" />

<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />

<meta charset="utf-8">
<title>照護員搜尋結果 - Daily Warm</title>
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
<link type="text/css"
	href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.min.css"
	rel="stylesheet" />
 <link type="text/css" rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<style type="text/css">
td, tr, th {
	border: 1px solid rgb(233, 226, 190);
}

.button {
	background-color: hsl(27, 92%, 77%);
	color: #703e05;
	font-size: 14px;
	border-radius: 14px;
	padding: 9px;
	border: 2px;
	transition-duration: 0s;
}

.button:hover {
	background-color: #f07c10;
	color: black;
}

table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	padding: 8px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

tr.clickable-row:hover {
	background-color: #ffebd1;
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
                    <li class="nav-item"><a class="nav-link" href="../front-end/afterlogin.jsp">首頁</a></li>
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
				<div class="col-lg-12 col-md-12 col-12">
					<h2 class="mb-2 mt-5 fw-bold">
						<a
							href="<%=request.getContextPath()%>/front-end/carersearch/carerSearch.jsp">回搜尋首頁
							</a>
							<span style="display:none;">您的id為<%=memId%></span>
					</h2>
					<div class="table-responsive">
						<table class="table table-striped text-start" id="dataTable">
							<thead>
								<tr>
									<th>照護員</th>
									<th>服務地區</th>
									<th>服務類型</th>
									<th>簡介</th>
									<th>時薪</th>
									<th>半日時薪</th>
									<th>整日時薪</th>
									<th>詳細資料</th>
									<th>寄信</th>
								</tr>
							</thead>
							<tbody>
							<% int i=1; %>
								<c:forEach var="carerVO" items="${carerSearchResult}">

												<tr align='center' valign='middle' class='clickable-row'>
										<td>${carerVO.searchName}${carerVO.searchGender}</td>
										<td>${carerVO.searchCounty}${carerVO.searchDist}</td>
										<td>${carerVO.serviceType}</td>
										<td>${carerVO.intro}</td>
										<td>${Math.round(carerVO.priceHour)}元</td>
										<td>${Math.round(carerVO.priceHalfday)}元</td>
										<td>${Math.round(carerVO.priceDay)}元</td>
										<td><a href="<%=request.getContextPath()%>/carer/carersearch?action=displayACarer&carerId=${carerVO.carerID}"><i class="bi bi-search"></i></a></td>
										<td>
										
<%-- <form method="post" action="<%=request.getContextPath()%>/front-end/mailbox/mail.do"> --%>
<%-- 		<input type="text" name="newreceiveMemId" value="${carerVO.carerID}"> --%>
<!-- 		<input type="text" name="newmsgTitle"> -->
<!-- 		<input type="text" name="newmsgContent"> -->
<%-- 		<input type="text" name="sendMemId" value="${memId}"> --%>
<!-- 		<input type="hidden" name="action" value="sendNewMail"> -->
<!-- 		<button type="submit" value="送出" >XX</button> -->
<!-- </form> -->
 						<div> 
							<button id="openerwrite${carerVO.carerID}" style="margin: 10px; border-radius: 10px;"><i class="fa fa-envelope" ></i></button>
						</div>
						
		<div id="dialogwrite${carerVO.carerID}" title="寫信" class="card-head" style="display: none;" >
			<div class="card-head">
				<form method="post" action="<%=request.getContextPath()%>/front-end/mailbox/mail.do">
					<table class="table table-bordered">
						<tr>
							<th>標題</th>
							<td><input type="text" name="newmsgTitle" value="" placeholder="請輸入標題"/></td>
						</tr>
						<tr>
							<th>內容</th>
							<td>
								<textarea name="newmsgContent" cols="50" rows="8" placeholder="請輸入內容"></textarea>
							</td>
						</tr>
					</table>
					<input type="hidden" name="newreceiveMemId" value="${carerVO.carerID}">
					<input type="hidden" name="sendMemId" value="${memId}">
					<input type="hidden" name="action" value="sendNewMail">
					<input type="submit" id="button" class="button" value="送出" style="float: right;">
				</form>
			</div>
		</div>
<% i++; %>
										</td>
<!-- 										<td> -->

<!-- 											<FORM METHOD="post" -->
<%-- 												ACTION="<%=request.getContextPath()%>/carer/carersearch"> --%>
<%-- 												<input type="text" name="carerId" value="${carerVO.carerID}" --%>
<!-- 													style="display: none;"> <input type="hidden" -->
<!-- 													name="action" value="displayACarer"> <input -->
<!-- 													type="submit" value="詳細資料" class="button"> -->
<!-- 											</FORM> -->


<!-- 										</td> -->
									</tr>
								</c:forEach>
							</tbody>
						</table>
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
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="../js/utility.js"></script>
	<script src="../js/scripts.js"></script>
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script
		src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
	
		// 	(() => {
		// 	    $(document).ready(function(){
		// 	        initOption('#county','county');/*載入縣市Select資料*/
		// 	    })
		// 	    $("#county").change(function(){/*點擊縣市select載入地區資料*/
		// 	        initOption('#district','district',$(this).val())
		// 	    })
		// 	})();

// 		jQuery(document)
// 				.ready(
// 						function($) {
// 							$(".clickable-row").click(function() {
// 								window.location = $(this).data("href");
// 							});
// 							$("#dataTable")
// 									.DataTable(
// 											{
// 												language : {
// 													url : '//cdn.datatables.net/plug-ins/1.11.4/i18n/zh_Hant.json'
// 												},
// 												pagingType : "full_numbers",
// 												orderable : false,
// 												retrieve : true,
// 											});
// 						});
	</script>
	
	<script>
	<c:forEach var="carerVO" items="${carerSearchResult}">
		$("#dialogwrite${carerVO.carerID}").dialog({
			autoOpen : false,
			width : 700
		});
		$("#openerwrite${carerVO.carerID}").click(function() {
			$("#dialogwrite${carerVO.carerID}").dialog("open");
		});
	</c:forEach>
	</script>
	
</body>
</html>