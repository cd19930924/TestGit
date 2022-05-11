<%@page import="com.collection.model.CollectionService"%>
<%@page import="com.carermem.model.CarerMemVO"%>
<%@page import="com.utils.DecodeCookieUtil"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%
// int memId = (int) DecodeCookieUtil.getMemId(request);
//如果要帶到下一頁
HttpSession session1 = request.getSession();

int memId = 0;

if (session1.getAttribute("memId") != null){
	memId = (int) session1.getAttribute("memId");
}else {
	memId = (int) DecodeCookieUtil.getMemId(request);
}
%>

<%
CarerMemVO carerMemVO = (CarerMemVO) request.getAttribute("carerVO");
CollectionService collectSvc = new CollectionService();
Boolean checkCollected = collectSvc.isCollected(memId, carerMemVO.getCarerID());
String checkCollectedStr = collectSvc.isCollectedStr(memId, carerMemVO.getCarerID());

%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>照護員資料 - listOneCarer.jsp</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/front-end/assets/healthcare.png" />
<!-- Bootstrap Icons-->
<link  type="text/css"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Font Awesome icons (free version)-->
<script type="text/javascript" src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
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
<link type="text/css" href="${pageContext.request.contextPath}/front-end/css/styles.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style type="text/css">
hr.style-one {
	border: 0;
	height: 1px;
	background: #333;
	background-image: linear-gradient(to right, #ccc, #333, #ccc);
}
.post {
	margin: 10px;
	color: #3b2205;
	font-size: 14px;
	border-radius: 20px;
	padding: 6px;
	border: 2px;
	border-color: 2px;
}
.button {
	margin-top: 10px;
	background-color: #de7523;
	color: #ffffff;
	font-size: 14px;
	border-radius: 20px;
	padding: 6px;
	border: 2px;
	transition-duration: 0s;
}

.button:hover {
	background-color: #e6bb94;
	color: black;
}
.post:hover {
	background-color: #FFE4C4;
	color: black;
}

.postButton{
	background-color: #de7523;
	color: #ffffff;
	font-size: 14px;
	border-radius: 20px;
	padding: 6px;
	border: 2px;
	transition-duration: 0s;
}

.postButton:hover {
	background-color: #FFE4C4;
	color: black;
}
th {
text-align: -webkit-center;
vertical-align: middle;
}

div.blockFixed{
  left: 90%;
  width: 150px;
  height: 140px;
  top: 11%;
  position: fixed;
  background-color:#ffdf9e;
  border-radius: 8px;
　　vertical-align: middle;
text-align: -webkit-center;
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
							href="<%=request.getContextPath()%>/front-end/carersearch/carerSearch.jsp">回搜尋頁面
							</a><span style="display:none;">您的id為<%=memId%></span>｜<a href="javascript:history.back()">回上一頁</a>
					</h2>

					<div class="table-responsive">
						<table class="table table-striped text-start">
							<tr>
								<th>照護員</th>
								<td>${carerVO.searchName}${carerVO.searchGender} <img
									src="data:image/jpg;base64,${fileVO.fileContentString}"
									width="100" height="100" />
								</td>
							</tr>
							<tr>
								<th>簡介</th>
								<td>${carerVO.intro}</td>
							</tr>
							<tr>
								<th>服務地區</th>
								<td>${carerVO.searchCounty}${carerVO.searchDist}</td>
							</tr>
							<tr>
								<th>照護證照</th>
								<td>${certi}</td>
							</tr>
							<tr>
								<th>照護技能</th>
								<td>【基本技能】 ${normalSkill} 【進階技能】 ${proSkill}</td>
							</tr>
							<tr>
								<th>服務類型</th>
								<td>
									<div class="serviceType">${carerVO.serviceType}</div>
								</td>
							</tr>
							<tr>
								<th>費用</th>
								<td>
									<ul>
										<li>未滿12小時之時薪：${Math.round(carerVO.priceHour)} 元</li>
										<li>12-24小時之時薪：${Math.round(carerVO.priceHalfday)} 元</li>
										<li>24小時以上之時薪：${Math.round(carerVO.priceDay)} 元</li>
									</ul>
								</td>
							</tr>
							<tr>
							<th>　　　　　　　　</th>
								<td>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>

		</div>
	</section>
	<div class="blockFixed">
										　<%=checkCollectedStr%>　      
									<%--收藏照護員 --%> <c:choose>
										<c:when test="<%=checkCollected%>">
											<FORM onsubmit="return unmark();" METHOD="post"
												ACTION="<%=request.getContextPath()%>/carersearch/collection">
												<input type="hidden" name="memId" value="<%=memId%>">
												<input type="hidden" name="carerId"
													value="${carerVO.carerID}"> <input type="hidden"
													name="action" value="delete"> <button type="submit"
													class="button"><i class="bi bi-star"></i>取消收藏</button>
											</FORM>
										</c:when>
										<c:otherwise>
											<form
												action="<%=request.getContextPath()%>/carersearch/collection"
												method="post">
												<input type="hidden" name="memId" value="<%=memId%>">
												<input type="hidden" name="carerId"
													value="${carerVO.carerID}"> <input type="hidden"
													name="action" value="add">
												<button type="submit" class="button">
													<i class="bi bi-star-fill"></i>收藏照護員
												</button>
											</form>    
										</c:otherwise>
									</c:choose>
									
																	<%--串接至刊登需求單 --%>
							 <form class="blockFixed" action="<%=request.getContextPath()%>/front-end/careRequest/careRequest" method="post">
						        <input type="hidden" name="carerId" value="${carerVO.carerID}">
						        <input type="hidden" name="carerName" value="${carerVO.searchName}${carerVO.searchGender}">
						        <input type="hidden" name="action" value="goto_post_request">
						        <button type="submit" class="postButton"><i class="bi bi-file-earmark-post"></i>刊登需求單</button>
						    </form>
	</div>
						  
	

			
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="../js/utility.js"></script>
    <script src="../js/afterLogin.js"></script>
	<script src="../js/spilitstring.js"></script>
	<script type="text/javascript">
	function unmark(){
		if(!confirm("確定取消收藏嗎？")){
			return false;
		}
		alert('已取消收藏!');
		return true;
	}
	</script>
</body>
</html>