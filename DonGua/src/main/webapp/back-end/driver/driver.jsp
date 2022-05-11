<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.*" import="com.driver.model.*"%>
<%@page import="com.utils.DecodeCookieUtil" %>
<%@page import="com.auth.model.AuthVO"%>
<%@page import="com.auth.model.AuthService"%>

<%
DriverService driverSvc = new DriverService();
List<DriverVO> list = driverSvc.getAllDriver();
pageContext.setAttribute("list", list);

List<AuthVO> functionList = DecodeCookieUtil.getFunctionList(request);
pageContext.setAttribute("functionList", functionList);
%>

<html lang="en">

<style>
td, th, tr { 
 text-align: center; 
}

#dataTable tr:nth-child(even) {
 background: #ffdea08c;
}
</style>

<head>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>司機基本資料管理</title>

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
<%-- jQuery UI : css --%>
<link type="text/css" rel="stylesheet"
	href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">

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
			<li class="nav-item"><a class="nav-link"
				href="../member/memberList.html"> <i class="fas fa-fw fa-table"></i>
					<span>一般會員管理</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">照護管理</div>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link"
				href="../carermgt/carerMgt.jsp"> <i class="fas fa-fw fa-table"></i>
					<span>照護員管理</span>
			</a></li>
			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link"
				href="../carermemapplymgt/CarerMemApplyMgt.jsp"> <i
					class="fas fa-fw fa-table"></i> <span>照護員申請審核</span>
			</a></li>
			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link"
				href="../careorder/careOrder.jsp"> <i class="fas fa-fw fa-table"></i>
					<span>照護訂單管理</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">派車管理</div>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link" href="driver.jsp">
					<i class="fas fa-fw fa-table"></i> <span>司機基本資料管理</span>
			</a></li>
			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link"
				href="../driverschedule/driverSchedule.jsp"> <i
					class="fas fa-fw fa-table"></i> <span>司機班表管理</span>
			</a></li>
			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link"
				href="../driveorder/driveorder.jsp"> <i
					class="fas fa-fw fa-table"></i> <span>派車訂單管理</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">送餐管理</div>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link"
				href="../meal/listAllMeal.jsp"> <i class="fas fa-fw fa-table"></i>
					<span>餐點資料管理</span>
			</a></li>
			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link"
				href="../meal/mealOrder.jsp"> <i class="fas fa-fw fa-table"></i>
					<span>餐點訂單管理</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">後台功能權限管理</div>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link" href="../auth/auth.jsp">
					<i class="fas fa-fw fa-table"></i> <span>權限功能群組管理</span>
			</a></li>
			<li class="nav-item"><a class="nav-link"
				href="../auth/empAuth.jsp"> <i class="fas fa-fw fa-table"></i> <span>員工權限群組管理</span>
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
			<li class="nav-item"><a class="nav-link"
				href="../infomanage/news.jsp"> <i class="fas fa-fw fa-table"></i>
					<span>最新消息</span>
			</a></li>
			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link"
				href="../infomanage/intro.jsp"> <i class="fas fa-fw fa-table"></i>
					<span>服務簡介</span>
			</a></li>
			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link"
				href="../infomanage/faq.jsp"> <i class="fas fa-fw fa-table"></i>
					<span>常見問題</span>
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

				

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						

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
								<FORM METHOD="post" ACTION="../emp/emp.do"
									style="margin-bottom: 0px;">
									<button class="dropdown-item function7 " type="submit">
										<i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
										我的個人資料
									</button>
									<input type="hidden" name="action" value="get_My_Info">
								</FORM>
								<FORM METHOD="post" ACTION="../emp/emp.do"
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
					<h1 class="h3 mb-2 text-gray-800">司機基本資料管理</h1>
					<p class="mb-4">
						司機姓名、司機電話、車牌號碼、司機信箱、在職狀態 <a target="_blank"
							href="https://datatables.net"></a>
					</p>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3"
							style="margin: 5px; padding: 5px; display: flex; justify-content: space-between; align-items: center;">
							<h6 class="m-0 font-weight-bold text-primary divleft">司機基本資料</h6>
						</div>
						<div class="card-body">
							<div class="table-responsive">
							<c:if test="${not empty errorMsgs}">
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color:red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">

									<thead>
										<tr>
											<th>編號</th>
											<th>姓名</th>
											<th>電話</th>
											<th>車牌</th>
											<th>信箱</th>
											<th>在職狀態</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>編號</th>
											<th>姓名</th>
											<th>電話</th>
											<th>車牌</th>
											<th>信箱</th>
											<th>在職狀態</th>
										</tr>
									</tfoot>

									<tbody>
										<jsp:useBean id="driver" scope="page"
											class="com.driver.model.DriverService" />


										<c:forEach var="driverVO" items="${list}">
											<tr>
												<td><a href="#" id="opener${driverVO.driverIdStr}">${driverVO.driverIdStr}</a>
													<div id="dialog${driverVO.driverIdStr}"
														title="司機編號#${driverVO.driverIdStr}">
														<FORM METHOD="post"
															ACTION="<%=request.getContextPath()%>/back-end/driver/driver.do">
															<table class="table table-bordered" width="100%">
																<tr>
																	<th>司機姓名</th>
																	<td><input type="text" name="driverName"
																		value="${driverVO.driverName}"></td>
																</tr>
																<tr>
																	<th>司機電話</th>
																	<td><input type="text" name="driverPhone"
																		value="${driverVO.driverPhone}"></td>
																</tr>
																<tr>
																	<th>車牌號碼</th>
																	<td><input type="text" name="carNumber"
																		value="${driverVO.carNumber}"></td>
																</tr>
																<tr>
																	<th>司機信箱</th>
																	<td><input type="text" name="driverEmail"
																		value="${driverVO.driverEmail}" /></td>
																</tr>
																<tr>
																	<th>服務狀態</th>
																	<td><select name="serviceStatus">
																			<option value="0">在職中</option>
																			<option value="1">已離職</option>
																	</select></td>
																</tr>
															</table>
															<input type="hidden" name="driverId"
																value="${driverVO.driverIdStr}"> <input
																type="hidden" name="serviceStatus"
																value="${driverVO.serviceStatus}"> <input
																type="hidden" name="action" value="update"> <input class="function7"
																type="submit" disabled value="送出編輯">
														</FORM>
													</div></td>
												<td>${driverVO.driverName}</td>
												<td>${driverVO.driverPhone}</td>
												<td>${driverVO.carNumber}</td>
												<td>${driverVO.driverEmail}</td>
												<td id=a${status.count}><a href="#"
													id="openerstatus${driverVO.driverIdStr}">${driverVO.serviceStatus == '0' ? '在職中' : '已離職'}</a>
													<div id="dialogstatus${driverVO.driverIdStr}"
														title="編輯狀態#${driverVO.driverIdStr}號司機狀態">
														<FORM METHOD="post"
															ACTION="<%=request.getContextPath()%>/back-end/driver/driver.do">
															<select name="serviceStatus"><option value="1">已離職</option>
																<option value="0">在職中</option></select> <input type="hidden"
																name="driverIdStr" value="${driverVO.driverIdStr}">
															<input type="hidden" name="action"
																value="serviceStatusUpdate"> <input class="function7"
																type="submit" disabled value="送出編輯">
														</FORM>
													</div></td>
											</tr>
										</c:forEach>
										<tr>
											<td><a href="#" id="openeradd"> <i
													style="font-size: 16px; color: #6495ED" class="fa">&#xf067;</i><span
													class="m-0 font-weight-bold text-primary">新增司機</span>
											</a>
												<div id="dialogadd" title="新增司機">
													<FORM method="post"
														action="<%=request.getContextPath()%>/back-end/driver/driver.do">
														<table class="table table-bordered" width="100%">
															<tr>
																<th>司機姓名</th>
																<td><input type="text" name="driverName"
																	id="driverName" /></td>
															</tr>
															<tr>
																<th>司機電話</th>
																<td><input type="text" name="driverPhone"
																	id="driverPhone" /></td>
															</tr>
															<tr>
																<th>車牌</th>
																<td><input type="text" name="carNumber"
																	id="carNumber" /></td>
															</tr>
															<tr>
																<th>司機信箱</th>
																<td><input type="text" name="driverEmail"
																	id="driverEmail" /></td>
															</tr>
															<tr>
																<th>服務狀態</th>
																<td><select id="serviceStatus" name="serviceStatus">
																		<option value="0">在職中</option>
																		<option value="1">已離職</option>
																</select></td>
															</tr>

														</table>
														<input type="hidden" name="action" value="insert">
														<input disabled type="submit" class="function7 button" id="button" value="送出">
													</FORM>
												</div></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
									</tbody>
								</table>
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
						<span>Copyright &copy; Daily Warm 2020</span>
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
					<button class="btn btn-primary" id="logout">登出</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="../vendor/jquery/jquery.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="../js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="../vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="../vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="../js/demo/datatables-demo.js"></script>

	<!-- <script src="../js/driver.js"></script> -->

	<script type="text/javascript"
		src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

	<script src="../js/login2.js"></script>


	<script type="text/javascript">
		<c:forEach var="driverVO" items="${list}" varStatus="loop">

		$("#dialog${driverVO.driverIdStr}").dialog({
			autoOpen : false,
			width : 700
		});
		$("#opener${driverVO.driverIdStr}").click(function() {
			$("#dialog${driverVO.driverIdStr}").dialog("open");
		});

		$("#dialogstatus${driverVO.driverIdStr}").dialog({
			autoOpen : false,
			width : 400
		});
		$("#openerstatus${driverVO.driverIdStr}").click(function() {
			$("#dialogstatus${driverVO.driverIdStr}").dialog("open");
		});

		</c:forEach>

		$("#dialogadd").dialog({
			autoOpen : false,
			width : 700
		});
		$("#openeradd").click(function() {
			$("#dialogadd").dialog("open");
		});
		
		let array;
		<c:forEach var="vo" items="${functionList}">
		    array = document.querySelectorAll('.function${vo.functionNoInt}');
		    for (let i = 0; i < array.length; i++) { 
		      array[i].removeAttribute('disabled'); 
		    }
		 </c:forEach>
	</script>
</body>
</html>