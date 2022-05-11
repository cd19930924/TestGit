<%@page import="com.utils.DecodeCookieUtil"%>
<%@page import="com.carerequest.model.CareRequestVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
int id = (int) DecodeCookieUtil.getMemId(request);

HttpSession session1 = request.getSession();
int carerId = (int) session1.getAttribute("carerId");
%>

<jsp:useBean id="requestSearchResult" scope="request"
	type="java.util.List<CareRequestVO>" />
<jsp:useBean id="careRequestSvc" scope="page"
	class="com.carerequest.model.CareRequestService" />
<%-- <jsp:useBean id="carerId" scope="session" type="id" /> --%>

<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>需求單查詢結果 - Daily Warm</title>
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
<link type="text/css" href="${pageContext.request.contextPath}/front-end/css/styles.css"
	rel="stylesheet" />
<link  type="text/css" 
	href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.min.css"
	rel="stylesheet" />
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
    <nav class="navbar navbar-expand-lg navbar-light fixed-top1 py-3" id="mainNav">
        <div class="container px-4 px-lg-0">
            <a class="navbar-brand" href="../Carer.jsp">Daily Warm</a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ms-auto my-2 my-lg-0">

                    <!-- 首頁 -->
                    <li class="nav-item"><a class="nav-link" href="../Carer.jsp">首頁</a></li>
                    <!-- 專業服務 -->
                    <li class="nav-item">
      <a class="nav-link" 
       href="<%=request.getContextPath()%>/front-end/updatecarerdata/showCarerData.do?action=showCarerData&carerID=<%=carerId%>">照護資料設定</a>
     </li>
                    <!-- 需求列表 -->
                    <li class="nav-item"><a class="nav-link" href="../careRequest/requestSearch.jsp">需求列表</a></li>
                    <!-- 服務列表 -->
                    <li class="nav-item"><a class="nav-link" href="../careordermgt/CareOrderMgt.jsp">服務列表</a></li>
                    <!-- 個人資料設定 -->
                    <li class="nav-item"><a class="nav-link" href="../member/setMemData.html">個人資料設定</a></li>
                    <!-- 通知 -->
                    <li class="nav-item"><a class="nav-link" href="../mailbox/noticeCenter.jsp">通知</a></li>
                    <!-- 會員 -->
                    <li class="nav-item dropdown">
                        <a class="nav-link" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                            aria-expanded="false">
                            <i class="fas fa-user-circle fa-2x "></i>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" id="switchAcct" href="../afterlogin.jsp" >切換帳號</a></li>
                            <li><a class="dropdown-item" id="logout">登出</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
	<!-- Services 服務內容 -->
	<section class="pt-5 pb-5">
		<div class="container">
			<div class="row w-100">
				<div class="col-lg-12 col-md-12 col-12">
					<h2 class="mb-2 mt-5 fw-bold">
						<a
							href="<%=request.getContextPath()%>/front-end/careRequest/requestSearch.jsp">←回搜尋首頁</a>
					</h2>
<%-- 					<span style="dispaly:none;">您的id為<%=carerId%></span> --%>
					<div class="table-responsive">

						<table class="table table-striped text-start" id="dataTable">
						<thead>
							<tr>

								<th>需求單編號</th>
								<th>需求者</th>
								<th>年紀</th>
								<th>地區</th>
								<th>開始時間</th>
								<th>結束時間</th>
								<th>類型</th>
								<th>備註</th>
								<th>狀態</th>

							</tr>
							</thead>
							<tbody>
							
							<c:forEach var="careRequestVO" items="${requestSearchResult}">
			
								<tr align='center' valign='middle' class='clickable-row'
									data-href='<%=request.getContextPath()%>/request/requestsearch?action=displayARequest&requestId=${careRequestVO.requestId}'>


									<td>${careRequestVO.requestId}</td>
									<td>${careRequestVO.patientName}${careRequestVO.patientGender}</td>
									<td>${careRequestVO.patientAge}</td>
									<td>${careRequestVO.patientAddr}</td>
									<td>${careRequestVO.startTime}</td>
									<td>${careRequestVO.endTime}</td>
									<td>${careRequestVO.serviceType}</td>
									<td>${careRequestVO.note}</td>
									<td>${careRequestVO.status}</td>
									<!-- 								<td> -->

									<!-- 									<FORM METHOD="post" -->
									<%-- 										ACTION="<%=request.getContextPath()%>/request/requestsearch"> --%>
									<!-- 										<input type="text" name="requestId" -->
									<%-- 											value="${careRequestVO.requestId}" style="display: none;"> --%>
									<!-- 										<input type="hidden" name="action" value="displayARequest"> -->
									<!-- 										<input type="submit" value="詳細資料" class="button"> -->
									<!-- 									</FORM> -->


									<!-- 								</td> -->
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
	<script src="../js/scripts.js"></script>
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.min.js"></script>
	

	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script> -->
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			$(".clickable-row").click(function() {
				window.location = $(this).data("href");
			});
			$("#dataTable").DataTable({
	            language: {
	                url: '//cdn.datatables.net/plug-ins/1.11.4/i18n/zh_Hant.json'
	            },
	            pagingType: "full_numbers",
	            orderable : false,
	            retrieve: true,
			});
		});
		

	</script>
</body>
</html>