<%@page import="com.mealimg.model.MealImgService"%>
<%@page import="com.common.util.CommonUtil"%>
<%@page import="com.mealorder.model.MealOrderVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.mealorder.model.*"%>
<%@ page import="com.meal.model.*"%>
<%@page import="com.meal.model.service.MealService"%>
<%@ page import="com.meal.model.vo.*" %>
<%@ page import="com.utils.DecodeCookieUtil"%>

<%
	Long memId = DecodeCookieUtil.getMemId(request);
	session.setAttribute("id", memId);
	
	MealService mealSvc = CommonUtil.getBean(getServletContext(), MealService.class);
// 	MealService mealSvc = new MealService();
	List<MealVO> list = mealSvc.getAll();
	pageContext.setAttribute("list", list);
	MealImgService mealImgSvc = CommonUtil.getBean(getServletContext(), MealImgService.class);
%>
<%-- <jsp:useBean id="mealImgSvc" scope="page" class="com.mealimg.model.MealImgService" /> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Daily Warm照護媒合</title>
<!-- Favicon-->
  <link rel="icon" type="image/x-icon" href="../assets/healthcare.png" />
  <!-- Bootstrap Icons-->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
  <!-- Font Awesome icons (free version)-->
  <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
  <script src="https://kit.fontawesome.com/19fb92fb24.js" crossorigin="anonymous"></script>
  <!-- Google fonts-->
  <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
  <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic"
    rel="stylesheet" type="text/css" />
  <!-- SimpleLightbox plugin CSS-->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css" rel="stylesheet" />
  <!-- Core theme CSS (includes Bootstrap)-->
  <link href="../css/styles.css" rel="stylesheet" />
  <link href="https://fonts.googleapis.com/earlyaccess/cwtexyen.css" rel="stylesheet">
</head>

<style>
  .brline {
	display: inline-block;
/* 	border: 2px solid #edbc4a;   */
	border-radius: 7px;
	padding: 7px;
	margin: 5px;
	max-width: 300px;
	word-wrap:break-word;
 	background-color: #ffda88; 
}

.onemeal {
 	padding: 220px 60px 10px 60px; 
	position: relative;
}

.all {
	padding: 0 15px 0 50px;
	
}

.mar {
	padding: 10px 0px;
}

.brline{
  /**定義一串顏色，8個顏色，1|2號顏色和最後的7|8號顏色要相同，才能銜接上，看不出迴圈間斷*/
  background: linear-gradient(to left, #ffda88, #edbc4a, #f5e7ba, #edbc4a, #f5e7ba, #edbc4a, #ffda88, #edbc4a);
  /**動畫的寬度，8個顏色，寬度就是8-1=7*100%，最後一個顏色用來迴圈迴歸的。*/
  background-size: 700% 100%;
  /**動畫使用，線性移動，速率20秒*/
  animation: mymove 20s linear infinite;
  /**適配不同瀏覽器*/
  -webkit-animation: mymove 20s linear infinite;
  -moz-animation: mymove 20s linear infinite;
}
@-webkit-keyframes mymove {
        0% {background-position: 0% 0%;}
        100% {background-position: 100% 0%;}
}

#context i{
	color:#9e5b20;
}
#context label{
	color:#9e5b20;
}
body,button, input,select, textarea,h1 ,h2, h3, h4, h5, h6 {
  font-family:cwtexyen, sans-serif;
 
} 
 
</style>

<body id="page-top" style="background-color: #ffdea08c;">
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
<!-- 內容 -->

    <div class="container pt-5 pb-5" style="min-height:100%; margin-bottom: -50px;">
      <div style="padding-bottom: 50px;">
        <h2 class="mb-2 mt-5 fw-bold">餐點外送預約</h2>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
        <form method="post" action="order" name="form">

        <div class="all">
         <% int i=1; %>
<c:forEach var="mealVO" items="${list}">
          <div class="brline">
            <c:forEach var="mealImgVO" items="${mealImgSvc.all}">
            	<c:if test="${mealImgVO.mealNo==mealVO.mealNo}">
                		<img src="data:image/jpg;base64,${mealImgVO.mealImgFile}"
                			width="200"height="200" style="position:absolute; margin:10px 6px 10px 35px; border-radius: 7px;">
				</c:if>
				
           </c:forEach>
          
           <div class="onemeal" style="width: 270px;">
           <input type="checkbox" name="meal" id="meal<%=  i%>" value="${mealVO.mealNo}" onchange="check(<%= i%>)" >
            <label for="meal<%=  i%>"><h5>${mealVO.mealName}</h5></label><br>
            <label id="price<%= i%>" value="${mealVO.mealPrice}" for="meal<%=  i%>"><h5>$${mealVO.mealPrice}</h5></label><br>
              <label><h5>數量</h5></label>
				<input type="number" id="qty<%= i%>" name="qty" min="1" max="50" placeholder="≤50" disabled="disabled" onblur="countAmount(<%= i%>)"/>
			<% i++; %>
			</div>
			
          </div>
</c:forEach>
        </div>
        
<div style="padding: 50px 80px 0px 80px" id="context">
          <div class="mar"><h5>
            <label>餐點時間</label>
            <input type="checkbox" class="mt-2" id="breakfast" value="1" name="mealtime" onchange="time()">
            <label for="breakfast">早餐</label>
            <input type="checkbox" class="mt-2" id="lunch" value="2" name="mealtime" onchange="time()">
            <label for="lunch">午餐</label>
            <input type="checkbox" class="mt-2" id="dinner" value="3" name="mealtime" onchange="time()">
            <label for="dinner">晚餐</label>
            </h5>
          </div>
          <div class="form-floating mb-3">
            <input type="date" class="form-control" name="startdate" id="mealdate" >
            <label for="mealdate">起始日</label>
          </div>
            <div class="form-floating mb-3">
            <input type="text" class="form-control" onblur="day()" name="totaldays" placeholder="請輸入≤60的數字"  id="days">
            <label for="totaldays"><h5>天數</h5></label>
            </div>
          <div class="form-floating mb-3">
            <input id="orderamount" name="orderamount" style="color: red;" class="form-control" readonly="readonly">
            <label for="totaldays"><h5>總金額</h5></label>
<!--           <h5 style="color: #9e5b20;">總金額<input id="orderamount" name="orderamount" style="color: red;"/></h5> -->
			</div>
          <div class="form-floating mb-3">
            <input type="text" class="form-control" name="contactname" placeholder="請輸入文字" id="contactname"/>
            <label for="contactname"><h5><i class="fa-solid fa-person"></i> 聯絡姓名</h5></label>
          </div>
          <div class="form-floating mb-3">
            <input type="text" class="form-control" name="contactnumber" placeholder="請輸入號碼" id="contactnumber">
            <label for="contactnumber"><h5><i class="fa-solid fa-phone"></i>聯絡人電話</h5></label>
          </div>
          <div class="form-floating mb-3">
            <input type="text" class="form-control" name="addr" placeholder="請輸入文字" id="addr">
            <label for="addr"><h5><i class="fa-solid fa-location-dot"></i>送餐地址</h5></label>
          </div>
</div>
          <div class="d-grid">
          <input type="hidden" name="action" value="insert">
          <input type="hidden" name="memId" value="${id}">
          <input type="submit" class="btn btn-primary btn-xl" value="送出訂單">
<!--           <input type="hidden" name="memId" value=""> -->
          </div>
        </form>
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
  <!-- <script src="js/scripts.js"></script> -->
  <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
  <!-- * *                               SB Forms JS                               * *-->
  <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
  <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
  <!-- <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script> -->
  <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="../js/utility.js"></script>
<!--   <script src="../js/afterLogin.js"></script> -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
  <script>
  let arrayLength = <%= i%>;
  let mealAmount = new Array(<%= i%>);
  var totalPrice =0;
  let test = 0;
    function check(num) {
    	var checkBox = document.getElementById("meal"+num);
    	if(checkBox.checked==true){
    		document.getElementById("qty"+num).disabled = false;
    		document.getElementById("qty"+num).value = 1;
    		countAmount(num);
//     		alert($("#price"+num).html()); // 取得price的值
    	}else{
    		document.getElementById("qty"+num).disabled = true;
    		document.getElementById("qty"+num).value = 0;
    		countAmount(num);
    	}
    }
    
    function countAmount(num){
    let qty = document.getElementById("qty"+num).value;
    let day1 = document.getElementById("days").value;
    console.log(qty);
    
    if(qty>50){
    	document.getElementById("qty"+num).value = 1;
    	countAmount(num);
    	alert("數量不可大於50");
    } else if(qty<0) {
    	document.getElementById("qty"+num).value = 1;
    	countAmount(num);
    	alert("數量不可小於50");
    } else {
    	let price = document.getElementById("price"+num).innerText.substring(1);
        price = Number(price);
        console.log(price);
        let amount = qty*price;
//         alert(amount);
        total(num,amount,day1);
        day();
    }
    
  }
    
	function day(){
	let day2 = document.getElementById("days").value;
	let realAmount=0;
	if(day2 > 0 && day2 <= 60 && test >0 && test<4){
		realAmount = totalPrice*day2*test;
  	  document.getElementById("orderamount").value=realAmount;
    } else if(day2 > 60){
      alert('天數不可大於60');
    } else if (day2<0) {
  	  alert('天數不可小於等於0');
    }
}
    
    function total(num,amount,day){
      mealAmount[num-1] = amount;
      let totalAmount =0;
      for(let i=0;i<arrayLength;i++){
        if(mealAmount[i]>0){
          console.log(mealAmount[i]);
          totalAmount = totalAmount+mealAmount[i];
        }
      }
      totalPrice = totalAmount;
//       if(day !== 0){
//     	  totalAmount = totalAmount*day;
//     	  document.getElementById("orderamount").value=totalAmount;
//       } else {
//     	  document.getElementById("orderamount").value=totalAmount;
//       }
      
    }
    function time(){
    
    let checkbox = document.querySelectorAll('input[name=mealtime]:checked');
    	test = 0;
	    for(let i = 0; i < checkbox.length; i++){
	      if(checkbox[i].value !== null){
	        test++;
	      }
	    }
	    if(test > 0){
	   		console.log(test);
	    } 
    }
    
  </script>

	<script>
        let dd = new Date();
        let ss = dd.getFullYear() + "-" +  ((dd.getMonth() + 1) < 10 ? "0" + (dd.getMonth() + 1) : (dd.getMonth() + 1)) + "-" + (dd.getDate() < 10 ? "0" + dd.getDate() : dd.getDate());  
        console.log(ss);
        dd.setDate(dd.getDate() + 60);//獲取AddDayCount天后的日期  
        let y = dd.getFullYear();
        let m = (dd.getMonth() + 1) < 10 ? "0" + (dd.getMonth() + 1) : (dd.getMonth() + 1);//獲取當前月份的日期，不足10補0  
        let d = dd.getDate() < 10 ? "0" + dd.getDate() : dd.getDate();//獲取當前幾號，不足10補0  
        let ss2 = y + "-" + m + "-" + d;   
        document.querySelector("#mealdate").setAttribute("min",ss);
        document.querySelector("#mealdate").setAttribute("max",ss2);
    </script>
    
</body>
</html>