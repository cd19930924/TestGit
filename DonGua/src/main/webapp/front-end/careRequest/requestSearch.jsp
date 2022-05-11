<%@page import="com.utils.DecodeCookieUtil"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
HttpSession session1 = request.getSession();
int id = (int) DecodeCookieUtil.getMemId(request);
session1.setAttribute("carerId", id);
%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>搜尋照護需求單 - DailyWarm</title>
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

<link type="text/css" rel="stylesheet"
	href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/jquery.datetimepicker.css" />
<style type="text/css">
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

.service {
    height: 300px;
	background: url("../assets/pexels-andrea-piacquadio-3768114.jpg")
}
.button {
  margin: 10px 10px 10px 10px;
  background-color: hsl(27, 92%, 77%);
  color: #703e05;
  font-size: 14px;
  border-radius: 8px;
  padding: 6px;
  border: 2px;
  width: 100px;
  transition-duration: 0s;
}

.button:hover {
  background-color: #f07c10;
  color: black;
}
select {

  padding: 6px;
  font-size: 14px;
  background: rgba(240, 196, 167, 0.692);
  color: rgb(128, 77, 11);
  border-radius: 8px;
}

option:not(:checked) {
  background-color: #fff;
}

.item{
  margin: 10px 10px 10px 10px;
  padding: 6px;
  font-size: 14px;
  color: grey;
  text-align: left 10px;
}
.selectdiv{
  margin: 10px 10px 10px 10px;
}
.xdsoft_datetimepicker .xdsoft_datepicker {
  width:  300px;   /* width:  300px; */
}
.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
  height: 151px;   /* height:  151px; */
}
</style>

</head>
<body>
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
       href="<%=request.getContextPath()%>/front-end/updatecarerdata/showCarerData.do?action=showCarerData&carerID=<%=id%>">照護資料設定</a>
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
<section class="pt-5 pb-5">
<div class="container"> 
<div class="col-lg-12 col-md-12 col-12">
		<div class="row w-100">
		<h2 class="mb-2 mt-5 fw-bold">搜尋照護需求單</h2>
		<span style="display:none;"></span>
		<div class="table-responsive">
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/request/requestsearch" onsubmit="return validateMyForm();">

			<div class="item"><i class="bi bi-geo-alt-fill"></i> 地區</div>		
			<div class="selectdiv">
			<select id="county" name="county">
				<option value="">縣市</option>
			</select> 
			
			<select id="district" name="district">
				<option value="">行政區</option>
			</select> 
			</div>

			<div class="item"><i class="bi bi-info-circle-fill"></i> 服務類型</div>
			<div class="selectdiv">
			<select id="service" name="SERVICE_TYPE">
				<option value="" >請選擇服務類型</option>
				<option value="0">居家照護</option>
				<option value="1">醫院看護</option>
				<option value="">全選</option>
			</select>
			</div>
			
			<div class="item"><i class="bi bi-calendar-check"></i> 服務開始時間(可搜尋即日起三個月內需求單)</div>
			<div class="selectdiv">
			<input type="text" id="start_dateTime" name="start_dateTime" placeholder="開始時間" onkeydown="return false" />
			<span id="checkStartTime"></span>
			</div>
			<div class="item"><i class="bi bi-calendar-check-fill"></i> 服務結束持間(可搜尋即日起三個月內需求單)</div>
			<div class="selectdiv">
            <input type="text" id="end_dateTime" name="end_dateTime" placeholder="結束時間" onkeydown="return false" />
			<span id="checkEndTime"></span>
            </div>
            
<!--          	<div class="item"><i class="bi bi-person-plus-fill"></i> 應徵狀態</div> -->
<!--          	<div class="selectdiv"> -->
<!-- 			<select name="STATUS"> -->
<!-- 				<option value="">請選擇狀態</option> -->
<!-- 				<option value="1">已應徵</option> -->
<!-- 				<option value="2">尚未應徵</option> -->
<!-- 				<option value="">全選</option> -->
				
<!-- 			</select> -->
<!-- 			</div> -->
			<input type="hidden" name="action" value="multipleRequestsSearch">
<!-- 			<input type="submit" class="button" value="查詢"> -->
			  <button type="submit" class="button"><i class="bi bi-search"></i>查詢</button>
 			  <button type="reset" value="Reset" class="button"><i class="bi bi-x-circle"></i>清除搜尋</button>
		</FORM>
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

	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script> -->
	
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/js/utility.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/js/jquery.datetimepicker.full.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/js/dateTimePickerSearch.js"></script>
	<script type="text/javascript">
	(() => {
	    $(document).ready(function(){
	        initOption('#county','county');/*載入縣市Select資料*/
	    })
	    $("#county").change(function(){/*點擊縣市select載入地區資料*/
	        initOption('#district','district',$(this).val())
	    })
	})();
	
	
    var startTime = document.querySelector('#start_dateTime');
    var endTime = document.querySelector('#end_dateTime');
    var checkStartTime = document.querySelector('#checkStartTime');
    var checkEndTime = document.querySelector('#checkEndTime');
    var today = new Date();
    startTime.addEventListener('blur', () => {
    	console.log("text");
    	if (startTime.value == 0 ) {
    		checkStartTime.innerHTML = '<span style="color:grey"><i class="bi bi-exclamation-circle"></i>未輸入開始日期，則預設為今天</span>';
    	}
        if (startTime.value!=0 && startTime.value < today) {
            checkStartTime.innerHTML = '<span style="color:red">不可小於現在時間</span>';
        } 
        if (endTime.value!=0 && startTime.value > endTime.value) {
        	checkStartTime.innerHTML = '<span style="color:red">不可大於結束時間</span>';
        }
    });
    endTime.addEventListener('blur', () => {
    	console.log("text");
    	if (endTime.value == 0 ) {
    		checkEndTime.innerHTML = '<span style="color:grey"><i class="bi bi-exclamation-circle"></i>未輸入結束日期，則預設搜尋未來所有需求單</span>';
    	}
        if (endTime.value!=0 && endTime.value < today) {
            checkEndTime.innerHTML = '<span style="color:red">不可小於現在時間</span>';
        } 
        if (endTime.value!=0 && startTime.value > endTime.value) {
        	checkEndTime.innerHTML = '<span style="color:red">不可小於開始時間</span>';
        }
    });
    	
    startTime.addEventListener('click', () => {
            checkStartTime.innerHTML = '';
    });
    endTime.addEventListener('click', () => {
            checkEndTime.innerHTML = '';
    });
    
    function validateMyForm()
    {
  	  var today = new Date();
   	  var startTime = document.querySelector('#start_dateTime');
  	  var endTime = document.querySelector('#end_dateTime');
//   	  var checkStartTime = document.querySelector('#checkStartTime');
//   	  var checkEndTime = document.querySelector('#checkEndTime');
  	  var startTimeValue = new Date(startTime.value);
  	  var endTimeValue = new Date(endTime.value);
  	  var startTimeGetTime = startTimeValue.getTime();
  	  var endTimeGetTime = endTimeValue.getTime();
  	  var todayGetTime = today.getTime();
//   　　　debugger
     
  	  if( startTime.value !=0 && startTimeGetTime < todayGetTime ) { 
        alert("開始時間不可小於現在時間");
        return false;
      } else if( startTime.value !=0 && startTimeGetTime > endTimeGetTime ){
  		 alert("結束時間不可小於開始時間");
  		 return false;
      } 
      
  	if( endTime.value !=0 && endTimeGetTime < todayGetTime ){
 	   alert("結束時間不可小於現在時間");
 	   return false;
       } else if ( endTime.value !=0 && startTimeGetTime > endTimeGetTime ){
   		alert("結束時間不可小於開始時間");
          return false;
       }

      if (startTime.value ==0 && endTime.value ==0){
    	  if(!confirm("您未輸入資料，將搜尋未來所有需求單")){
    		  return false;
    	　　}
     	return true;
 	   }
   	 return true;
   	 
    }
	</script>

</body>
</html>