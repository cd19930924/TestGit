<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.List"%>

<%@page import="com.utils.DecodeCookieUtil"%>
<%@page import="com.careorder.model.CareOrderService"%>
<%@page import="com.careorder.model.CareOrderPVO"%>

<%
int memId = (int) DecodeCookieUtil.getMemId(request);

List<CareOrderPVO> careOrderList = new CareOrderService().getOrderList(memId); // 測試

pageContext.setAttribute("careOrderList", careOrderList);
%>

<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Daily Warm照護媒合</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/healthcare.png" />
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

<link type="text/css"  rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">

<link href="https://fonts.googleapis.com/earlyaccess/cwtexyen.css" rel="stylesheet">

<style type="text/css">
body,button, input,select, textarea,h1 ,h2, h3, h4, h5, h6 {
  font-family:cwtexyen, Merriweather;
}
.cool-color{
  /**定義一串顏色，8個顏色，1|2號顏色和最後的7|8號顏色要相同，才能銜接上，看不出迴圈間斷*/
  background: linear-gradient(to right, #FFCF70, #FFC857, #FFC247, #FFBB33, #FFAD0A, #FFA300, #FFCF70, #FFC857);
  /**動畫的寬度，8個顏色，寬度就是8-1=7*100%，最後一個顏色用來迴圈迴歸的。*/
  background-size: 700% 100%;
  /**動畫使用，線性移動，速率20秒*/
  animation: mymove 8s linear infinite;
  /**適配不同瀏覽器*/
  -webkit-animation: mymove 8s linear infinite;
  -moz-animation: mymove 8s linear infinite;
}

/**定義過度動畫*/
@-webkit-keyframes mymove {
        0% {background-position: 0% 0%;}
        100% {background-position: 100% 0%;}
}


td, th { 
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
                            <li><a class="dropdown-item" id="logout" onclick="logout1()">登出</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

	<section class="pt-5 pb-5" style="background-color: #ffdea08c;">
		<div class="container">
			<div class="row w-100">
				<div class="col-lg-12 col-md-12 col-12">
					<h1 class="mb-2 mt-5 fw-bold">照護清單</h1>
					<div>
						<a href="<%= request.getContextPath() %>/front-end/careRequest/requestMgt.jsp"><span>需求單管理</span></a>
						<span>｜</span>
						<span>訂單管理</span>
					</div>
<!-- 					<p>注意事項1：已完成的服務七天內未填寫回饋將自動關閉</p> -->
<!-- 					<p>注意事項2：訂單於三天內執行則不得取消</p> -->
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<div class="table-responsive">
						<table class="table table-striped text-start cool-color" style="vertical-align: middle;">
							<thead>
								<tr>
									<th>訂單編號</th>
									<th>照護對象</th>
									<th>開始時間</th>
									<th>結束時間</th>
									<th>費用</th>
									<th>照護員</th>
									<th>狀態</th>
									<th>管理</th>
								</tr>
							</thead>
							<%@ include file="page1.file" %>
							<c:forEach var="careOrderPVO" items="${careOrderList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<c:set var="orderId" value="${careOrderPVO.careOrderId}" />
								<c:set var="status" value="${careOrderPVO.status}" />
								<tbody>
									<tr>
										<td><a
											href="careOrder?action=view_order&orderId=${orderId}">${orderId}</a></td>

										<td>${careOrderPVO.patientName}</td>

										<td>${careOrderPVO.startTime.toString().substring(0, 16)}</td>

										<td>${careOrderPVO.endTime.toString().substring(0, 16)}</td>

										<td>${careOrderPVO.amount.intValue()}</td>

										<td>
											<a href="<%=request.getContextPath()%>/carer/carersearch?action=displayACarer&carerId=${careOrderPVO.carerId}">${careOrderPVO.carerName}</a>
										</td>

										<c:if test="${status == 0}">
											<td>已取消</td>
											<td></td>
										</c:if>

										<c:if test="${status == 1}">
											<td>待執行</td>
											<td>
												<form action="careOrder" method="post">
													<input type="hidden" name="orderId" value="${orderId}">
													<input type="hidden" name="carerId" value="${careOrderPVO.carerId}">
													<input type="hidden" name="action" value="cancel_order">
													<button type="submit" style="  
													display: inline-block;
											        background-color: #BA0F30;
											        border-radius: 10px;
											        border: 4px double #cccccc;
											        color: #eeeeee;
											        text-align: center;
											        font-size: 15px;
											        padding: 10px;
											        width: 150px;
											        transition: all 0.5s;
											        cursor: pointer;
											        margin: 5px;
											        margin-top: 23px;">❌取消訂單</button>
												</form>
											</td>
										</c:if>

										<c:if test="${status == 2}">
											<td>執行中</td>
											<td></td>
										</c:if>

										<c:if test="${status == 3}">
											<td>已結單</td>
											<td>
												<button id="openerA${orderId}" style="  
													display: inline-block;
											        background-color: #059DC0;
											        border-radius: 10px;
											        border: 4px double #cccccc;
											        color: #eeeeee;
											        text-align: center;
											        font-size: 15px;
											        padding: 10px;
											        width: 150px;
											        transition: all 0.5s;
											        cursor: pointer;
											        margin: 5px;
											        margin-top: 7px;">✏️填寫回饋</button>
												<div id="dialogA${orderId}" title="訂單編號：${orderId}">
													<form action="careOrder" method="post">
														 <input type="hidden" name="orderId" value="${orderId}"> 
														 <input type="hidden" name="action" value="fill_in_feedback">
														 <textarea name="feedback" placeholder="100字內" maxlength="100" style="resize: none; width: 250px; height: 100px"></textarea>
														 <input type="hidden" name="carerId" value="${careOrderPVO.carerId}">
														 <input type="submit" value="送出回饋" style="margin-left: 64%;">
													</form>
												</div>
											</td>
										</c:if>

										<c:if test="${status == 4}">
											<td>訂單完成</td>
											<td>
												<button id="openerB${orderId}" style="  
													display: inline-block;
											        background-color: #107869;
											        border-radius: 10px;
											        border: 4px double #cccccc;
											        color: #eeeeee;
											        text-align: center;
											        font-size: 15px;
											        padding: 10px;
											        width: 150px;
											        transition: all 0.5s;
											        cursor: pointer;
											        margin: 5px;">查看回饋</button>
											        
												<div id="dialogB${orderId}" title="訂單編號：${orderId}">
													${(careOrderPVO.careFeedback == null) ? "未填寫" : careOrderPVO.careFeedback}
												</div>
											</td>
										</c:if>
									</tr>
								</tbody>
							</c:forEach>
						</table>
						<%@ include file="page2.file" %>
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
	
	<script type="text/javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script> -->
    <script src="../js/utility.js"></script>
    <script src="../js/logout.js"></script>
	<script type="text/javascript">
	function logout1(){
		logout();
	}
		<c:forEach var="careOrderPVO" items="${careOrderList}">
			<c:set var="orderId" value="${careOrderPVO.careOrderId}" />
			<c:set var="status" value="${careOrderPVO.status}" />
			
			<c:if test="${status == 3}">
				$( "#dialogA${orderId}" ).dialog({ 
					autoOpen: false
				});
				
				$( "#openerA${orderId}" ).click(function() {
				  $( "#dialogA${orderId}" ).dialog( "open" );
				});
			</c:if>
			
			<c:if test="${status == 4}">
				$( "#dialogB${orderId}" ).dialog({ 
					autoOpen: false 
				});
				
				$( "#openerB${orderId}" ).click(function() {
				  $( "#dialogB${orderId}" ).dialog( "open" );
				});
			</c:if>
		</c:forEach>
	</script>
</body>
</html>