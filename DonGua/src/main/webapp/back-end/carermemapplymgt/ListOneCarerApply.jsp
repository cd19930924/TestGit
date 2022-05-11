<%@page import="com.file.model.FileVO"%>
<%@page import="com.file.model.FileService"%>
<%@page import="com.carermemapplymgt.model.CarerMemApplyMgtVO"%>
<%@page import="com.carermemapplymgt.model.CarerMemApplyMgtService"%>
<%@page import="com.member.model.dao.impl.MemberDaoImpl"%>
<%@page import="com.member.model.vo.MemberVO"%>
<%@page import="com.member.model.dao.MemberDao"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@page import="com.common.model.service.JWTokenUtils"%>
<%@page import="com.utils.DecodeCookieUtil" %>
<%@page import="com.auth.model.AuthVO"%>
<%@page import="com.auth.model.AuthService"%>

<%
CarerMemApplyMgtService cmamSvc = new CarerMemApplyMgtService();
CarerMemApplyMgtVO carerMemApplyMgtVo = (CarerMemApplyMgtVO) request.getAttribute("carerMemApplyMgtVo");
pageContext.setAttribute("carerMemApplyMgtVo", carerMemApplyMgtVo);
%>
<%
List<AuthVO> functionList = DecodeCookieUtil.getFunctionList(request);
pageContext.setAttribute("functionList", functionList);
%>

<jsp:useBean id="photoresult" scope="request"
	type="java.util.List<FileVO>" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>申請者資料 - DailyWarm</title>

<!-- Custom fonts for this template -->
<link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="../vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">

<style>
#table1 td {
	padding: 10px 0;
}

#table1 tr:nth-child(even) {
	background: #ffdea08c;
}
</style>

</head>

<body id="page-top">

	<!-- Page Wrapper -->
 <div id="wrapper">

  <!-- Sidebar -->
  <ul
   class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
   id="accordionSidebar">

   <!-- Sidebar - Brand -->
   <a
    class="sidebar-brand d-flex align-items-center justify-content-center"
    href="../index.html">
    <div class="sidebar-brand-icon rotate-n-15">
     <i class="fas fa-laugh-wink"></i>
    </div>
    <div class="sidebar-brand-text mx-3">Daily Warm照護管理後台</div>
   </a>

   <!-- Divider -->
   <hr class="sidebar-divider">

   <!-- Heading -->
   <div class="sidebar-heading">會員資料管理</div>

   <!-- Nav Item - Tables -->
   <li class="nav-item"><a class="nav-link" href="../member/memberList.html">
     <i class="fas fa-fw fa-table"></i> <span>一般會員管理</span>
   </a></li>

   <!-- Divider -->
   <hr class="sidebar-divider">

   <!-- Heading -->
   <div class="sidebar-heading">照護管理</div>

   <!-- Nav Item - Tables -->
   <li class="nav-item"><a class="nav-link" href="../carermgt/carerMgt.jsp">
     <i class="fas fa-fw fa-table"></i> <span>照護員管理</span>
   </a></li>
   <!-- Nav Item - Tables -->
   <li class="nav-item"><a class="nav-link" href="../carermemapplymgt/CarerMemApplyMgt.jsp">
     <i class="fas fa-fw fa-table"></i> <span>照護員申請審核</span>
   </a></li>
   <!-- Nav Item - Tables -->
   <li class="nav-item"><a class="nav-link" href="../careorder/careOrder.jsp">
     <i class="fas fa-fw fa-table"></i> <span>照護訂單管理</span>
   </a></li>



   <!-- Divider -->
   <hr class="sidebar-divider">

   <!-- Heading -->
   <div class="sidebar-heading">派車管理</div>

   <!-- Nav Item - Tables -->
   <li class="nav-item"><a class="nav-link" href="../driver/driver.jsp">
     <i class="fas fa-fw fa-table"></i> <span>司機基本資料管理</span>
   </a></li>
   <!-- Nav Item - Tables -->
   <li class="nav-item"><a class="nav-link" href="../driverschedule/driverSchedule.jsp">
     <i class="fas fa-fw fa-table"></i> <span>司機班表管理</span>
   </a></li>
   <!-- Nav Item - Tables -->
   <li class="nav-item"><a class="nav-link" href="../driveorder/driveorder.jsp">
     <i class="fas fa-fw fa-table"></i> <span>派車訂單管理</span>
   </a></li>

   <!-- Divider -->
   <hr class="sidebar-divider">

   <!-- Heading -->
   <div class="sidebar-heading">送餐管理</div>

   <!-- Nav Item - Tables -->
   <li class="nav-item"><a class="nav-link" href="../meal/listAllMeal.jsp">
     <i class="fas fa-fw fa-table"></i> <span>餐點資料管理</span>
   </a></li>
   <!-- Nav Item - Tables -->
   <li class="nav-item"><a class="nav-link" href="../meal/mealOrder.jsp">
     <i class="fas fa-fw fa-table"></i> <span>餐點訂單管理</span>
   </a></li>

   <!-- Divider -->
   <hr class="sidebar-divider">

   <!-- Heading -->
   <div class="sidebar-heading">後台功能權限管理</div>

   <!-- Nav Item - Tables -->
   <li class="nav-item"><a class="nav-link" href="../auth/auth.jsp">
     <i class="fas fa-fw fa-table"></i> <span>權限功能群組管理</span>
   </a></li>
   <li class="nav-item"><a class="nav-link" href="../auth/empAuth.jsp">
     <i class="fas fa-fw fa-table"></i> <span>員工權限群組管理</span>
   </a></li>
   <!-- Divider -->
   <hr class="sidebar-divider">

   <!-- Heading -->
   <div class="sidebar-heading">員工管理</div>

   <!-- Nav Item - Tables -->
   <li class="nav-item"><a class="nav-link" href="../emp/emp.jsp">
     <i class="fas fa-fw fa-table"></i> <span>員工資料維護</span>
   </a></li>
   <!-- Heading -->
   <div class="sidebar-heading">前台管理</div>

   <!-- Nav Item - Tables -->
   <li class="nav-item"><a class="nav-link" href="../infomanage/news.jsp">
     <i class="fas fa-fw fa-table"></i> <span>最新消息</span>
   </a></li>
   <!-- Nav Item - Tables -->
   <li class="nav-item"><a class="nav-link" href="../infomanage/intro.jsp">
     <i class="fas fa-fw fa-table"></i> <span>服務簡介</span>
   </a></li>
   <!-- Nav Item - Tables -->
   <li class="nav-item"><a class="nav-link" href="../infomanage/faq.jsp">
     <i class="fas fa-fw fa-table"></i> <span>常見問題</span>
   </a></li>


   <!-- Divider -->
   <hr class="sidebar-divider d-none d-md-block">

   <!-- Sidebar Toggler (Sidebar) -->
   <div class="text-center d-none d-md-inline">
    <button class="rounded-circle border-0" id="sidebarToggle"></button>
   </div>

  </ul>
  <!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<form class="form-inline">
						<button id="sidebarToggleTop"
							class="btn btn-link d-md-none rounded-circle mr-3">
							<i class="fa fa-bars"></i>
						</button>
					</form>

					<!-- Topbar Search -->
					<form
						class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
						<div class="input-group">
							<input type="text" class="form-control bg-light border-0 small"
								placeholder="Search for..." aria-label="Search"
								aria-describedby="basic-addon2">
							<div class="input-group-append">
								<button class="btn btn-primary" type="button">
									<i class="fas fa-search fa-sm"></i>
								</button>
							</div>
						</div>
					</form>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						<li class="nav-item dropdown no-arrow d-sm-none"><a
							class="nav-link dropdown-toggle" href="#" id="searchDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-search fa-fw"></i>
						</a> <!-- Dropdown - Messages -->
							<div
								class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
								aria-labelledby="searchDropdown">
								<form class="form-inline mr-auto w-100 navbar-search">
									<div class="input-group">
										<input type="text"
											class="form-control bg-light border-0 small"
											placeholder="Search for..." aria-label="Search"
											aria-describedby="basic-addon2">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>
								</form>
							</div></li>

						<div class="topbar-divider d-none d-sm-block"></div>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small" id="empName"></span>
								<img class="img-profile rounded-circle"
								src="../img/undraw_profile.svg">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<FORM METHOD="post"
									ACTION="../emp/emp.do"
									style="margin-bottom: 0px;">
									<button class="dropdown-item " type="submit">
										<i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
										我的個人資料
									</button>
									<input type="hidden"name="action" value="get_My_Info">
								</FORM>
								<FORM METHOD="post"
									ACTION="../emp/emp.do"
									style="margin-bottom: 0px;">
									<button class="dropdown-item " type="submit">
										<i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
										修改密碼
									</button>
									<input type="hidden" name="action" value="change_The_Password">
								</FORM>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									登出
								</a>
							</div></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">申請者資料</h1>
					<h5>
						<a href="CarerMemApplyMgt.jsp">返回訂單列表</a>
					</h5>
					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3"
							style="justify-content: space-between; display: flex"></div>
						<div class="card-body">
							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
							<div class="table-responsive"
								style="width: 70%; margin: 0px auto">
								<form method="post" id="form"
									action="<%=request.getContextPath()%>/back-end/carermemapplymgt/carerMemApplyMgt.do">
									<table class="table table-bordered" id="dataTable">
										<table id="table1" style="color:black" width="100%"
									cellspacing="0">
											<tr>
												<td width="150px">申請單號:</td>
												<td width="250px">${carerMemApplyMgtVo.applyID}</td>
											</tr>
											<tr>
												<td>申請者帳號:</td>
												<td>${carerMemApplyMgtVo.memAcct}</td>
											</tr>
											<tr>
												<td>申請者姓名:</td>
												<td>${carerMemApplyMgtVo.memName}</td>
											</tr>
											<tr>
												<td>申請者性別:</td>
												<c:if test="${carerMemApplyMgtVo.memGender == 0}">
													<td>男</td>
												</c:if>
												<c:if test="${carerMemApplyMgtVo.memGender == 1}">
													<td>女</td>
												</c:if>
											</tr>
											<tr>
												<td>申請者年齡:</td>
												<td>${carerMemApplyMgtVo.memAge}</td>
											</tr>
											<tr>
												<td>申請者電話:</td>
												<td>${carerMemApplyMgtVo.memPhone}</td>
											</tr>
											<tr>
												<td>申請者Email:</td>
												<td>${carerMemApplyMgtVo.memEmail}</td>
											</tr>
											<tr>
												<td>申請者聯絡地址:</td>
												<td>${carerMemApplyMgtVo.memAddr}</td>
											</tr>
											<tr>
												<td>照護服務類型:</td>
												<c:if test="${carerMemApplyMgtVo.serviceType == 0}">
													<td>居家照護</td>
												</c:if>
												<c:if test="${carerMemApplyMgtVo.serviceType == 1}">
													<td>醫院看護</td>
												</c:if>
												<c:if test="${carerMemApplyMgtVo.serviceType == 2}">
													<td>居家照護及醫院看護</td>
												</c:if>
											</tr>
											<tr>
												<td>照護服務地區:</td>
												<td>${carerMemApplyMgtVo.serviceDistNo}</td>
											</tr>
											<tr>
												<td>提供的服務:</td>
												<td>
													<div>【基本技能】</div>
													<div>${normalSkill}</div>
													<div>【進階技能】</div>
													<div>${proSkill}</div>
												</td>
											</tr>
											<tr>
												<td>未滿12小時之時薪:</td>
												<td>${carerMemApplyMgtVo.priceHour.intValue()}</td>
											</tr>
											<tr>
												<td>12-24小時之時薪:</td>
												<td>${carerMemApplyMgtVo.priceHalfday.intValue()}</td>
											</tr>
											<tr>
												<td>24小時以上之時薪:</td>
												<td>${carerMemApplyMgtVo.priceDay.intValue()}</td>
											</tr>
											<tr>
												<td>簡介:</td>
												<td>${carerMemApplyMgtVo.intro}</td>
											</tr>
											<tr>
												<td>銀行代碼:</td>
												<td>${carerMemApplyMgtVo.bankCode}</td>
											</tr>
											<tr>
												<td>銀行帳號:</td>
												<td>${carerMemApplyMgtVo.bankAcct}</td>
											</tr>
											<tr>
												<td>申請時間:</td>
												<td>${carerMemApplyMgtVo.createTime.toString().substring(0, 16)}</td>
											</tr>

											<table border="1" style="color:black">
												<tr>證件照片:
												</tr>
												<tr>
													<th>檔案名稱</th>
													<th>照片</th>
												</tr>
												<c:forEach var="fileVo" items="${photoresult}">
													<tr>
														<td>${fileVo.fileTypeName}</td>
														<td><img
															src="data:image/jpg;base64,${fileVo.applyFileContent}"
															width="325" height="225" /></td>
													</tr>
												</c:forEach>
											</table>
											<br>
											<br>
											<input type="hidden" name="applyID"
												value="${carerMemApplyMgtVo.applyID}">
											
<!-- 											若已審核通過則隱藏按鈕 -->
											<c:choose>
											<c:when test="${carerMemApplyMgtVo.status == 1}">
											</c:when>
											<c:otherwise>
											<input type="button" id="success" value="審核成功" class="function4 btn btn-primary" disabled></input>
											<input type="button" id="fail" value="審核失敗" class="function4 btn btn-primary" disabled></input><br>
											
											<input type="hidden" name="memID" value="${carerMemApplyMgtVo.memID}"> 
											
											<input type="hidden" id="apply" name="action"></input>
											</c:otherwise>												
											</c:choose>	
											
											<div>
												<a href="CarerMemApplyMgt.jsp" class="btn btn-primary"><font size="4.5px">返回訂單列表</font></a>
											</div>

										</table>
									</table>
								</form>
							</div>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">準備登出?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">若確定要登出請點選「登出」.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">取消</button>
					<button class="btn btn-primary" id="logout">
						登出</a>
					</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">

	// 權限阻擋功能
	let array;
		<c:forEach var="vo" items="${functionList}">
		 array = document.querySelectorAll('.function${vo.functionNoInt}');
		 for (let i = 0; i < array.length; i++) { 
			 console.log(array[i]);
		 array[i].removeAttribute('disabled'); 
		 }
	 	</c:forEach>
	
	const apply = document.querySelector('#apply');
	const form = document.querySelector('#form');

	document.querySelector('#success').addEventListener('click', () => {
// 	    alert('成功');

	    apply.value = 'success';

	    form.submit();
	});

	document.querySelector('#fail').addEventListener('click', () => {
// 	    alert('失敗');

	    apply.value = 'fail';

	    form.submit();
	});
	
	</script>

	<!-- Bootstrap core JavaScript-->
	<script src="../vendor/jquery/jquery.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->

	<!-- Page level custom scripts -->
	
	<script src="../js/login2.js"></script>

</body>

</html>