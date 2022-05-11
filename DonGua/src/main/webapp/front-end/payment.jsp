<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>付款頁面-Daily Warm照護媒合</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/front-end/assets/healthcare.png" />
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
	href="<%=request.getContextPath()%>/front-end/css/styles.css"
	rel="stylesheet" />
	
<!-- sweet alert css -->
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />	
	
<style type="text/css">
.div1 {
	margin: 20px 400px 20px 400px;
	padding: 50px;
	border: 1px solid rgb(253, 253, 252);
	border-radius: 10px;
	background-color: rgb(255, 255, 255);
	opacity: 60%;
}

hr.style-one {
	border: 0;
	height: 1px;
	background: #333;
	background-image: linear-gradient(to right, #ccc, #333, #ccc);
}

.button {
	margin: 10px;
	background-color: #DEB887;
	color: #3b2205;
	font-size: 14px;
	border-radius: 20px;
	padding: 6px;
	border: 2px;
	width: 80px;
	transition-duration: 0s;
}

.button:hover {
	background-color: #FAEBD7;
	color: black;
}

.card-inline {
	display:inline-block;
	white-space: nowrap;
}
.date-inline {
	display:inline-block;
	white-space: nowrap;
}
</style>
</head>
<body>
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
	<!-- Payment -->
	<section class="page-section bg-primary" id="services">
		<div class="div1">
		<div class="div2">
	<h1>付款頁面</h1>
		<%-- errorMessage --%>
			<c:if test="${not empty errorMsgs}">
				<div>
				<font style="color:red"><span>&#9888;</span> 請檢查欄位</font>
				<ol>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ol>
				</div>
			</c:if>
			
			<FORM METHOD="post" ACTION="payment" onsubmit="return validateMyForm();">
			<div>
				<label class="control-label col-sm-2"><b>付款人姓名</b></label>
				<input type="text" class="form-control" name="memName" id="memName" style="width: 330px" />
			
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><b>信用卡卡號</b></label>
				<div class="card-inline col-sm-10">
					<input type="text" class="card-inline form-control autotab" id="card-inline1" style="width: 70px" name="firstBlockOfCard"
						maxlength="4" pattern="[0-9]+"> <span id="card-inline" class="card-inline">─</span>
					<input type="text" class="card-inline form-control autotab" id="card-inline2" style="width: 70px" name="secondBlockOfCard"
						maxlength="4" pattern="[0-9]+"> <span id="card-inline" class="card-inline">─</span>
					<input type="text" class="card-inline form-control autotab" id="card-inline3" style="width: 70px" name="thirdBlockOfCard"
						maxlength="4" pattern="[0-9]+"> <span id="card-inline" class="card-inline">─</span>
					<input type="text" class="card-inline form-control autotab" id="card-inline4" style="width: 70px" name="fourthBlockOfCard"
						maxlength="4" pattern="[0-9]+">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"><b>信用卡卡別</b></label>
				<div class="col-sm-10">
					<label class="radio-inline"> <input type="radio"
						name="inlineRadioOptions" id="inlineRadio1" value="option1"
						checked="">VISA
					</label> <label class="radio-inline"> <input type="radio"
						name="inlineRadioOptions" id="inlineRadio2" value="option2">
						MasterCard
					</label> <label class="radio-inline"> <input type="radio"
						name="inlineRadioOptions" id="inlineRadio3" value="option3">
						JCB
					</label>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" style="white-space: nowrap;"><b>信用卡截止日</b></label>
				<div class="date-inline col-sm-10">
					<select class="date-inline form-control" name="expiryMonth" id="month" style="width: 80px" >
						<option value="">請選擇</option>

						<option value="1">1</option>

						<option value="2">2</option>

						<option value="3">3</option>

						<option value="4">4</option>

						<option value="5">5</option>

						<option value="6">6</option>

						<option value="7">7</option>

						<option value="8">8</option>

						<option value="9">9</option>

						<option value="10">10</option>

						<option value="11">11</option>

						<option value="12">12</option>

					</select><span class="date-inline">月／</span><select class="date-inline form-control" name="expire_year" id="year" style="width: 80px" >
						<option value="">請選擇</option>

						<option value="2022">2022</option><option value="2023">2023</option><option value="2024">2024</option><option value="2025">2025</option><option value="2026">2026</option><option value="2027">2027</option><option value="2028">2028</option><option value="2029">2029</option>

					</select><span class="date-inline">年</span>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"><b>安全驗證碼</b></label>
				<div class="col-sm-10">
									<input type="text" name="expire_safe" class="form-control" id="expire_safe"
						placeholder="請輸入安全驗證碼" maxlength="3" pattern="[0-9]+" style="width: 330px" >
				</div>
			</div>
			
				<div>
					<label class="control-label col-sm-2"><b>付款金額</b></label>
					<div class="col-sm-10">
					<input type="text" class="form-control"
						value="${amount}" disabled="disabled" style="width: 330px" >
					</div>
				</div>
				
				<input type="hidden" name="action" value="${location}">
				<input type="submit" class="button" id="button" value="送出" />
	</FORM>
			
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
	<script src="<%=request.getContextPath()%>/front-end/js/scripts.js"></script>
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script> -->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../js/utility.js"></script>
<script type="text/javascript">

function logout1(){
	logout();
}

function validateMyForm() {
	  var regexCard = /^[0-9]{4}$/;
	  var regexExpire = /^[0-9]{3}$/;
	  
	  var memName = document.querySelector('#memName');
	  var cardInline1 = document.querySelector('#card-inline1');
	  var cardInline2 = document.querySelector('#card-inline2');
	  var cardInline3 = document.querySelector('#card-inline3');
	  var cardInline4 = document.querySelector('#card-inline4');
	  var month = document.querySelector('#month');
	  var year = document.querySelector('#year');
	  var expireSafe = document.querySelector('#expire_safe');
	  
	  if(!(memName.value)) { 
		alert("姓名不得空值");
		return false;
	 }
	  if(!regexCard.test(cardInline1.value) || !regexCard.test(cardInline2.value) || !regexCard.test(cardInline3.value) || !regexCard.test(cardInline4.value) ){
 		 alert("信用卡號格式不正確");
 		 return false;
   	 } 
	  if((!month.value) || (!year.value)){
	 		alert("請輸入信用卡到期日");
	       return false;
	  }
	  if (!regexExpire.test(expireSafe.value)) {		  
	 	   alert("信用卡安全驗證碼格式不正確");
	 	   return false;
	  }
	  if (year.value == '2022' && month.value == '1') {
	 	   alert("信用卡截止日請勿小於現在時間");
	 	   return false;
	  }
	   if(!confirm("是否確認付款？")){
	  return false;
		}
	   alert("付款成功，即將為您跳轉訂單詳細資訊");
		return true;
}

//輸入想要跳下一格的class名稱
var tablist = ["autotab","autotab2","autotab3"];

//特殊功能鍵，防止修改時按了ctrl shift alt、方向鍵、del之類的被跳到下一格
var functionkey = [8,9,16,17,18,20,33,34,35,36,37,38,39,40,45,46,93,144];
tablist.forEach(function(element) {
  $("."+element).on( "keydown", function( event ) {
        //next
        if ($(this).attr("maxLength") == $(this).val().length && (functionkey.indexOf(event.keyCode)==-1)) 
            $(this).nextAll("."+element).first().focus();
        //prev
        if($(this).val().length==0 && event.keyCode==8) {
           $(this).prevAll("."+element).first().focus();
        }
  });
});
  
</script>
</body>
</html>