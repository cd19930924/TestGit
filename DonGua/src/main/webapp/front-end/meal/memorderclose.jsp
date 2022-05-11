<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.mealorder.model.*" %>
<%@ page import="com.mealorderdetail.model.*" %>
<%@ page import="com.utils.DecodeCookieUtil"%>

<%
	Long id = DecodeCookieUtil.getMemId(request);
	MealOrderService mealOrderSvc = new MealOrderService();
	List<MealOrderVO> list = mealOrderSvc.findByMemIdAndStatus(id, "3");
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="mealOrderDetailSvc" scope="page" class="com.mealorderdetail.model.MealOrderDetailService"/>
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
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic"
        rel="stylesheet" type="text/css" />
    <!-- SimpleLightbox plugin CSS-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="../css/styles.css" rel="stylesheet" />
     <link
	href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.min.css"
	rel="stylesheet" />
	<link href="https://fonts.googleapis.com/earlyaccess/cwtexyen.css" rel="stylesheet">
</head>
<style>
body,button, input,select, textarea,h1 ,h2, h3, h4, h5, h6 {
  font-family:cwtexyen, Merriweather;
} 
	#detail{
	position: fixed;
	top: 5%;
	right: 30%;
	z-index:1200;
	display: none;
	}
	.modal-dialog-scrollable .modal-content{
	height: 90%;
	}
	.modal-content{
	width: 600px !important;
	}
</style>
<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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
    <section class="pt-5 pb-5">
        <div class="container">
            <div class="row w-100">
                <div class="col-lg-12 col-md-12 col-12">
                    <h2 class="mb-2 mt-5 fw-bold">餐點訂單管理</h2>

                    <ul class="nav nav-tabs">
                        <li class="nav-item">
                            <a class="nav-link" href='<%=request.getContextPath()%>/front-end/meal/memorder.jsp'>歷史訂單</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href='<%=request.getContextPath()%>/front-end/meal/memorderwait.jsp'>待執行</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href='<%=request.getContextPath()%>/front-end/meal/memorderdoing.jsp'>執行中</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href='<%=request.getContextPath()%>/front-end/meal/memorderfinish.jsp'>已完成</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href='<%=request.getContextPath()%>/front-end/meal/memorderclose.jsp'>已結單</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href='<%=request.getContextPath()%>/front-end/meal/memordercancel.jsp'>已取消</a>
                        </li>
                    </ul>

                    <div class="table-responsive">
                        <table class="table table-striped text-start" id="dataTable">
                            <thead class="">
                                <tr class=" ">
                                    <th>訂單單號</th>
                                    <th>起始日</th>
                                    <th>天數</th>
                                    <th>金額</th>
                                    <th>狀態</th>
                                    <th>明細</th>
                                </tr>
                            </thead>
								<tbody>
<c:forEach var="mealOrderVO" items="${list}">
									<tr>
										<td>${mealOrderVO.mealOrderId}</td>
										<td>${mealOrderVO.startDate}</td>
										<td>${mealOrderVO.totalDays}天</td>
										<td>${mealOrderVO.orderAmount}</td>
											<c:if test="${mealOrderVO.orderStatus == 0}">
		                                    <td>待執行</td>
		                                    </c:if>
		                                    
		                                    <c:if test="${mealOrderVO.orderStatus == 1}">
		                                    <td>執行中</td>
		                                    </c:if>
		                                    
		                                    <c:if test="${mealOrderVO.orderStatus == 2}">
		                                    <td>訂單完成</td>
		                                    </c:if>
		                                    
		                                    <c:if test="${mealOrderVO.orderStatus == 3}">
		                                    <td>已結單</td>
		                                    </c:if>
		                                    
		                                    <c:if test="${mealOrderVO.orderStatus == 4}">
		                                    <td>已取消</td>
		                                    </c:if>
										<td>
											<!-- Button trigger modal -->
											<button type="button" class="show" style="border-radius:5px">
												查看</button> <!-- Modal -->
											<div
												class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable"
												id="detail">
												<div class="modal-content">
													<div class="modal-header"></div>
													<div class="modal-body">
														<table class="table table-sm">
															<thead>
																<tr>
																	<th scope="col"><h4>訂單資訊</h4></th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<th>訂單單號</th>
																	<td>${mealOrderVO.mealOrderId}</td>
																</tr>
																<tr>
																	<th>起始日</th>
																	<td>${mealOrderVO.startDate}</td>
																</tr>
																<tr>
																	<th>總天數</th>
																	<td>${mealOrderVO.totalDays}天</td>
																</tr>
																<tr>
																	<th>餐點時間</th>
																	<c:if test="${mealOrderVO.mealTime == 111}">
																		<td>早餐、午餐、晚餐</td>
																	</c:if>
																	<c:if test="${mealOrderVO.mealTime == 110}">
																		<td>早餐、午餐</td>
																	</c:if>
																	<c:if test="${mealOrderVO.mealTime == 101}">
																		<td>早餐、晚餐</td>
																	</c:if>
																	<c:if test="${mealOrderVO.mealTime == 011}">
																		<td>午餐、晚餐</td>
																	</c:if>
																	<c:if test="${mealOrderVO.mealTime == 100}">
																		<td>早餐</td>
																	</c:if>
																	<c:if test="${mealOrderVO.mealTime == 010}">
																		<td>午餐</td>
																	</c:if>
																	<c:if test="${mealOrderVO.mealTime == 001}">
																		<td>晚餐</td>
																	</c:if>
																</tr>
																<tr>
																	<th>訂單成立時間</th>
																	<td>${mealOrderVO.createTime.toString().substring(0, 16)}</td>
																</tr>
															</tbody>
														</table>
														<br>
														<table class="table table-sm">
															<thead>
																<tr>
																	<th scope="col"><h4>餐點明細</h4></th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<th>編號</th>
																	<th>名稱</th>
																	<th>數量</th>
																	<th>金額</th>
																	<th>單價</th>
																</tr>
																<c:forEach var="mealOrderDetailVO" items="${mealOrderDetailSvc.all}">
																	<c:if test="${mealOrderDetailVO.mealOrderId==mealOrderVO.mealOrderId}">
																<tr>
																	<td>${mealOrderDetailVO.mealNo}</td>
																	<td>${mealOrderDetailVO.mealName}</td>
																	<td>${mealOrderDetailVO.mealCount}</td>
																	<td>${mealOrderDetailVO.mealAmount}</td>
																	<td>${mealOrderDetailVO.mealPrice}</td>
																</tr>
																	</c:if>
																</c:forEach>
																<tr>
																	<th>總金額</th>
																	<td>${mealOrderVO.orderAmount}</td>
																</tr>
															</tbody>
														</table>
														<br>
														<table class="table table-sm">
															<thead>
																<tr>
																	<th scope="col"><h4>配送資訊</h4></th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<th>聯絡人</th>
																	<td>${mealOrderVO.contactName}</td>
																</tr>
																<tr>
																	<th>電話</th>
																	<td>${mealOrderVO.contactNumber}</td>
																</tr>
																<tr>
																	<th>地址</th>
																	<td>${mealOrderVO.addr}</td>
																</tr>
															</tbody>
														</table>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-primary hide">確定</button> 
													</div>
												</div>
											</div>
										</td>
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
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.min.js"></script>
    <script src="../js/utility.js"></script>
  	<script src="../js/afterLogin.js"></script>
    <script>
    $(document).ready(function(){
    	$("#dataTable").DataTable({
            language: {
                url: '//cdn.datatables.net/plug-ins/1.11.4/i18n/zh_Hant.json'
            },
            pagingType: "full_numbers",
            orderable : false,
            retrieve: true,
        });
      $(".show").click(function(){
        $(this).next().show();
      });
      $(".hide").click(function(){
        $(".show").next().hide();
      });
    });
    </script>
</body>
</html>