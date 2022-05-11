<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.List"%>

<%@page import="com.utils.DecodeCookieUtil"%>
<%@page import="com.service.model.ServiceService"%>
<%@page import="com.servicetab.model.ServiceTabService"%>
<%@page import="com.member.model.service.MemberDataSettingService"%>
<%@page import="com.service.model.ServiceVO"%>
<%@page import="com.servicetab.model.ServiceTabVO"%>
<%@page import="com.member.model.vo.MemberVO"%>

<%
int memId = (int) DecodeCookieUtil.getMemId(request);

List<ServiceVO> serviceList = new ServiceService().getServiceList();
List<ServiceTabVO> serviceTabList = new ServiceTabService().getServiceTabList();
MemberVO memberVO = new MemberDataSettingService().getMemberData(memId);

pageContext.setAttribute("memId", memId);
pageContext.setAttribute("memberVO", memberVO);
pageContext.setAttribute("serviceList", serviceList);
pageContext.setAttribute("serviceTabList", serviceTabList);
%>

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

.cool-color{
  /**定義一串顏色，8個顏色，1|2號顏色和最後的7|8號顏色要相同，才能銜接上，看不出迴圈間斷*/
  background: linear-gradient(to right, #FFCF70, #FFC857, #FFC247, #FFBB33, #FFAD0A, #FFA300, #FFCF70, #FFC857);
  /**動畫的寬度，8個顏色，寬度就是8-1=7*100%，最後一個顏色用來迴圈迴歸的。*/
  background-size: 700% 100%;
  /**動畫使用，線性移動，速率20秒*/
  animation: mymove 4s linear infinite;
  /**適配不同瀏覽器*/
  -webkit-animation: mymove 4s linear infinite;
  -moz-animation: mymove 4s linear infinite;
}

/**定義過度動畫*/
@-webkit-keyframes mymove {
        0% {background-position: 0% 0%;}
        100% {background-position: 100% 0%;}
}

.text_center {
	text-align: center;
}

.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
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
			<a href="<%= request.getContextPath() %>/front-end/carersearch/carerSearch.jsp">返回搜尋</a><br>																<!-- back A0 -->
		</div>
		
		<div style="margin-left: 20%; margin-right: 20%;">
			<div class="text_center">
				<h1>刊登需求</h1>
			</div>
			<div>
				<h4>指定照護員：${(carerName == null) ? '無' : carerName}</h4>
			</div>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<form action="careRequest" method="post" style="height: 600px;" onsubmit="return validateForm();">
				<div style="display: flex; height: 550px;">
					<div style="flex: 1; overflow:auto; padding-top: 3%; border-radius: 20px 0px 0px 20px; background-color: #F7F3E3; border: 1px solid #000; border-top: none;border-bottom: none;border-left: none;">
						<button type="button" id="same" 
							onclick="replace('${memberVO.memName}','${memberVO.memGender}', '${memberVO.memAge}', '${memberVO.memAddr}')"
							style=
								"float: right; margin-right: 6%; margin-top: 2.5%;
								display: inline-block;
						        background-color: #BA0F30;
								border-radius: 10px;
						        border: 4px double #cccccc;
						        color: #eeeeee;
						        text-align: center;
						        font-size: 15px;
						        padding: 10px;
						        width: 125px;
						        transition: all 0.5s;
						        cursor: pointer;">同會員資料</button>
												
						<div style="margin-left: 10%;">
							<label for="name">姓名</label><br>
	                        <input type="text" id="name" name="patientName" style="width: 125px;"
	                        	value="${careRequestVO.patientName}"><br>
							
	                        <label>性別 </label><br>
	                        <input type="radio" id="male" name="patientGender" value="0"
	                        	${(careRequestVO.patientGender == 0) ? 'checked' : ''}>
	    		        	<label for="male">男</label>
	        		        <input type="radio" id="female" name="patientGender" value="1"
	        		        	${(careRequestVO.patientGender == 1) ? 'checked' : ''}>
	            		    <label for="female">女</label><br>
	                        
							<label for="age">年紀</label><br>
	                        <input type="number" id="age" name="patientAge" min="0" max="120" style="width: 50px;"
	                        	value="${careRequestVO.patientAge}"><br>
							
							<label>服務地點類型</label><br>						
	                        <input type="radio" id="home" name="serviceType" value="0"
	                        	${(careRequestVO.serviceType == 0) ? 'checked' : ''}>
	                        <label for="home">居家照護</label>
	                        <input type="radio" id="hospital" name="serviceType" value="1"
	                        	${(careRequestVO.serviceType == 1) ? 'checked' : ''}>
	                        <label for="hospital">醫院看護</label><br>
	                        	
							<label for="address">地址</label><br>
	                        <input type="text" id="address" name="patientAddr" style="width: 300px;"
	                        	value="${careRequestVO.patientAddr}"><br>
							
							<label for="start_dateTime">服務開始時間</label><br>
	                        <input onkeydown="return false" type="text" id="start_dateTime" name="startTime" autocomplete="off" style="width: 175px;"
	                        	value="${careRequestVO.startTime.toString().substring(0, 16)}"><br>
	                        <label for="end_dateTime">服務結束時間</label><br>
							<input onkeydown="return false" type="text" id="end_dateTime" name="endTime" autocomplete="off" style="width: 175px;"
								value="${careRequestVO.endTime.toString().substring(0, 16)}"><br>
							
							<label for="note">備註</label><br>
	                        <textarea id="note" name="note" placeholder="身體狀況與注意事項等等...(100字內)" maxlength="100" style="resize: none; width: 300px; height: 100px">${careRequestVO.note}</textarea>
						</div>
					</div>
					<div style="flex: 1; overflow:auto; padding-top: 2%; border-radius: 0px 20px 20px 0px; background-color: #F7F3E3;">
						<ol style="margin-top: 20px">
							<c:forEach var="serviceVO" items="${serviceList}">
								<li>${serviceVO.serviceName}
									<ul>
										<c:forEach var="serviceTabVO" items="${serviceTabList}">
											<c:set var="no" value="${serviceTabVO.serviceTabNo}" />
											<c:if test="${serviceTabVO.serviceNo==serviceVO.serviceNo}">
												<li style="list-style-type: none;">
													<input type="checkbox" id="${no}" name="serviceTabNo" value="${no}"> 
													<label for="${no}">${serviceTabVO.serviceTabName}</label>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</li>
							</c:forEach>
						</ol>
					</div>
				</div>
				<div class="text_center" style="height: 50px;">
					<input type="hidden" name="memId" value="${memId}">
				 	<input type="hidden" name="carerId" value="${carerId}">
				 	<input type="hidden" name="carerName" value="${carerName}">
				 	<input type="hidden" name="action" value="post_request">
                    <button class="cool-color" type="submit" style="border-radius: 5px; border-color: #B68D40; height: 50px; width: 100%; background-color: #F4EBD0;">刊登</button>
                </div>
			</form>
		</div>
	</main>
	
	<!-- Footer -->
	<footer class="bg-light py-5">
		<div class="container px-4 px-lg-5">
			<div class="small text-center text-muted">Copyright &copy; 2021
				- Daily Warm</div>
		</div>
	</footer>
	
	<!-- <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script> -->
    <script src="../js/utility.js"></script>
    <script src="../js/afterLogin.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	
	<script type="text/javascript">
		const address = document.querySelector('#address');

		function replace(name, gender, age, addr) {
		    document.querySelector('#name').value = name;
		    document.querySelector('#age').value = age;
		    document.querySelector('#home').checked = true;
		    address.value = addr;
		    (gender == 0) ? document.querySelector('#male').checked = true
		    		: document.querySelector('#female').checked = true;
		}
		
		<c:forEach var="no" items="${requestTabArray}">
			document.querySelector('#${no}').setAttribute('checked', true);
		</c:forEach>
		
		document.querySelector('#home').addEventListener('change', () => {
	    	address.value = '';
	    	address.removeAttribute('placeholder');
	    });

	    document.querySelector('#hospital').addEventListener('change', () => {
	    	address.value = '';
	    	address.setAttribute('placeholder', '填寫縣市、醫院名稱、病房號碼、病床號碼');
	    });
		
		// datetimepicker
		var min = new Date(); // 三天後
		min.setDate(min.getDate() + 3);

		var max = new Date();
		max.setDate(max.getDate() + 60);
		
		
		$.datetimepicker.setLocale('zh'); // kr ko ja en
		$(function() {
			$('#start_dateTime').datetimepicker({
				theme : 'dark',
				format : 'Y-m-d H:i',
				step : 60,
				minDate : min,
				maxDate : max
			});
			$('#end_dateTime').datetimepicker({
				theme : 'dark',
				format : 'Y-m-d H:i',
				step : 60,
				minDate : min,
				maxDate : max
			});
		});
		
	   function validateForm() {
			var today = new Date();
			var todayGetTime = today.getTime() + 3 * 24 * 60 * 60 * 1000;

			var startTime = document.querySelector('#start_dateTime');
			var startTimeValue = new Date(startTime.value);
			var startTimeGetTime = startTimeValue.getTime();

			var endTime = document.querySelector('#end_dateTime');
			var endTimeValue = new Date(endTime.value);
			var endTimeGetTime = endTimeValue.getTime();
	     
			if (startTimeGetTime <= todayGetTime || endTimeGetTime <= todayGetTime) { 
			    alert("服務時間請設定於三天後");
			    return false;
			} else if (startTimeGetTime >= endTimeGetTime) {
				alert("服務結束時間不可小於或等於服務開始時間");
				return false;
  			}
			
  			return true;
		}
	</script>
</body>

</html>