<%@page import="com.utils.DecodeCookieUtil"%>
<%@page import="com.membermailbox.model.MemberMailBoxService"%>
<%@page import="com.membermailbox.model.MemberMailBoxVO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
int sendMemId = (int)DecodeCookieUtil.getMemId(request);
List<MemberMailBoxVO> list = new MemberMailBoxService().getMemberMaillBoxVO(sendMemId);

pageContext.setAttribute("list", list);
// pageContext.setAttribute("memId", 1);
pageContext.setAttribute("sendMemId", sendMemId);
%>

<html lang="en">

<head>

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Daily Warm照護媒合</title>
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
	<script src="https://kit.fontawesome.com/328d7554bf.js" crossorigin="anonymous">
	</script>
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
<link
	href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.min.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../css/styles.css" rel="stylesheet" />
</head>
<style>
#dataTable tbody tr:hover {
	background-color: wheat;
}

.icon {
	border-radius: 5px;
	background-color: transparent;
	border-style: none;
	color: black;
}

.icon:hover{
color: brown;
}

 element.style { 
 	position: relative; 
 } 

 .content { 
 	width: 200px; 
 	overflow: hidden; 
 	text-overflow: ellipsis; 
 	display: -webkit-box; 
 	-webkit-line-clamp: 1; 
 	-webkit-box-orient: vertical; 
/* 	padding: 0 40px 0 0; */
 } 

 .title { 
 	width: 120px; 
 	overflow: hidden; 
 	text-overflow: ellipsis; 
 	display: -webkit-box; 
 	-webkit-line-clamp: 1; 
 	-webkit-box-orient: vertical; 
/* 	padding: 0 40px 0 0; */
 } 

textarea {
	resize: none
}

body,button, input,select, textarea,h1 ,h2, h3, h4, h5, h6 {
  font-family:cwtexyen, Merriweather;
}

</style>
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
                    <li class="nav-item"><a class="nav-link" href="noticeCenter.jsp">通知</a></li>
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


	<div
		style="margin: 100px 100px; padding:0px; position: relative; display: flex; flex-direction: column; min-width: 0; word-wrap: break-word; background-color: #fff; background-clip: border-box; border: 1px solid #e3e6f0; border-radius: 0.35rem;">

		<div
			style="padding: 0.75rem 1.25rem; margin-bottom: 0; background-color: #f8f9fc; border-bottom: 1px solid #e3e6f0;">
			<!-- 切版面 -->
			<div>
				<ul class="nav nav-tabs">
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/front-end/mailbox/noticeCenter.jsp">通知</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">信箱</a>
                        </li>
<!--                         <div>  -->
<!-- 							<button id="openerwrite" style="margin: 10px; border-radius: 10px; float: right;">新信件</button> -->
<!-- 						</div> -->
                    </ul>
			</div>
		</div>
		<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
		<table class="table" id="dataTable">

			<thead>
<!-- 				<div> -->
<!-- 					<button id="openerwrite" style="margin: 10px; border-radius: 10px;">新信件</button> -->
<!-- 				</div> -->
				<tr>
					<td>寄件者</td>
					<td>標題</td>
					<td>內容</td>
					<td>發送時間</td>
					<td></td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="pvo" items="${list}">
					
					<tr>
					<c:if test="${pvo.status == 0 }">
						<th scope="col">${pvo.memName}</th>
						<th scope="col">
							<div class="title">${pvo.msgTitle}</div>
						</th>
						<th scope="col">
							<div class="content">${pvo.msgContent}</div>
						</th>
						<th scope="col">${pvo.sendTime.toString().substring(0, 16)}</th>
					</c:if>
					<c:if test="${pvo.status == 2 }">
						<th scope="col" style="color:#f1d683;">寄件備份</th>
						<th scope="col">
							<div class="title" style="color:#f1d683;">${pvo.msgTitle}</div>
						</th>
						<th scope="col">
							<div class="content" style="color:#f1d683;">${pvo.msgContent}</div>
						</th>
						<th scope="col" style="color:#f1d683;">${pvo.sendTime.toString().substring(0, 16)}</th>
					</c:if>
						<!-- 查看信件 -->
						<th scope="col">
						<form method="post" action="<%=request.getContextPath()%>/front-end/mailbox/mail.do"></form>
							<button id="openerwatch${pvo.mailId}" class="icon"><i class="fa-solid fa-eye" id="test"></i></button>
							<div id="dialogwatch${pvo.mailId}" title="信件內容" style="display: none;">
									<table class="table table-bordered">
										<tr>
										<c:if test="${pvo.status == 0 }">
											<th style="width: 80px;">寄件者</th>
											<td>${pvo.memName}</td>
										</c:if>
										<c:if test="${pvo.status == 2 }">
										</c:if>
										</tr>
										<tr>
											<th>標題</th>
											<td>${pvo.msgTitle}</td>
										</tr>
										<tr>
											<th>時間</th>
											<td>${pvo.sendTime}</td>
										</tr>
										<tr>
											<th>內容</th>
											<td>${pvo.msgContent}</td>
										</tr>
									</table>
							</div>
						</th>
						<c:if test="${pvo.status == 0 }">
						<!-- 回信按鈕 -->
						<th scope="col">
							<button id="openercheck${pvo.mailId}" class="icon"><i class="fa-solid fa-share"></i></button>
							<div id="dialogcheck${pvo.mailId}" title="回覆訊息" style="display: none;">
								<form method="post" action="<%=request.getContextPath()%>/front-end/mailbox/mail.do">
									<table class="table table-bordered">
										<tr>
											<th>收件人</th>
											<td><input value="${pvo.memName}" disabled="true" /></td>
										</tr>
										<tr>
											<th>標題</th>
											<td><input type="text" name="msgTitle" value="RE:${pvo.msgTitle}" /></td>
										</tr>
										<tr>
											<th>內容</th>
											<td><textarea name="msgContent" cols="50" rows="8" placeholder="請輸入內容"></textarea></td>
										</tr>
									</table>
									<input type="hidden" name="receiveMemId" value="${pvo.sendMemId}">
									<input type="hidden" name="sendMemId" value="${sendMemId}">
									<input type="hidden" name="action" value="sendMail">
									<input type="submit" id="button" class="button" value="送出" style="float: right;">
								</form>
							</div>
						</th>
						</c:if>
							<c:if test="${pvo.status == 2 }">
							<th></th>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	<div>
		<div id="dialogwrite" title="寫信" class="card-head">
			<div class="card-head">
				<form method="post" action="<%=request.getContextPath()%>/front-end/mailbox/mail.do">
					<table class="table table-bordered">
						<tr>
							<th>收件人</th>
							<td><input type="text" id="" name="newreceiveMemId" value="" placeholder="請輸入收件人"/></td>
						</tr>
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
					<input type="hidden" name="sendMemId" value="${sendMemId}">
					<input type="hidden" name="action" value="sendNewMail">
					<input type="submit" id="button" class="button" value="送出" style="float: right;">
				</form>
			</div>
		</div>


		<!-- 彈窗完整信件內容 載入網頁時依使用者id作為receiveMemId搜尋所有信件 -->
<%-- 		<c:forEach var="pvo" items="${list}"> --%>
<%-- 			<div id="dialogdetail${pvo.mailId}" title="信件"> --%>
<!-- 				<table class="table table-bordered" style="table-layout: fixed;"> -->
<!-- 					<tr> -->
<!-- 						<th>寄件人</th> -->
<%-- 						<td>${pvo.memName}</td> --%>
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>標題</th> -->
<%-- 						<td>${pvo.msgTitle}</td> --%>
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>內容</th> -->
<!-- 						<td style="word-wrap: break-word; width: 20px"> -->
<%-- 							${pvo.msgContent}</td> --%>
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>時間</th> -->
<%-- 						<td>${pvo.sendTime}</td> --%>
<!-- 					</tr> -->
<!-- 				</table> -->
<!-- 			</div> -->
<%-- 		</c:forEach> --%>
	</div>

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
	<link rel="stylesheet"
		href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.12.4.js"></script>
	<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.min.js"></script>
	
    <script src="../js/utility.js"></script>
    <script src="../js/afterLogin.js"></script>
	
	
	<script type="text/javascript">
		$("#dialogwrite").dialog({
			autoOpen : false,
			width : 700
		});
		$("#openerwrite").click(function() {
			$("#dialogwrite").dialog("open");
		});

	<c:forEach var="pvo" items="${list}">
		$("#dialogcheck${pvo.mailId}").dialog({
			autoOpen : false,
			width : 700
		});
		$("#openercheck${pvo.mailId}").click(function() {
			$("#dialogcheck${pvo.mailId}").dialog("open");
		});
	</c:forEach>
	<c:forEach var="pvo" items="${list}">
		$("#dialogdetail${pvo.mailId}").dialog({
			autoOpen : false,
			width : 700
		});
		$("#openerdetail${pvo.mailId}").click(function() {
			$("#dialogcheck${pvo.mailId}").dialog("open");
		});
	</c:forEach>
	<c:forEach var="pvo" items="${list}">
	$("#dialogwatch${pvo.mailId}").dialog({
		autoOpen : false,
		width : 700
	});
	$("#openerwatch${pvo.mailId}").click(function() {
		$("#dialogwatch${pvo.mailId}").dialog("open");
	});
	</c:forEach>
		
	</script>
	
	
</body>

</html>