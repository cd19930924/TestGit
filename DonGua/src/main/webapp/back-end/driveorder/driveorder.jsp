<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@page import="com.utils.DecodeCookieUtil" %>
<%@page import="com.auth.model.AuthVO"%>
<%@page import="com.auth.model.AuthService"%>

<%
List<AuthVO> functionList = DecodeCookieUtil.getFunctionList(request);
pageContext.setAttribute("functionList", functionList);
%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>派車訂單管理</title>



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
	
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://kit.fontawesome.com/19fb92fb24.js" crossorigin="anonymous"></script>

<style>
	table, .diatable{
		text-align:center
	}
	table>tbody>tr>td{
		white-space:nowrap;
		padding:0px;
		font-size:0.8em;
		
	}
	th{
		white-space:nowrap;
		padding:0px;
		font-size:0.9em;
		color:#9e5b20;
		background:#f5e7ba;
		border:1px solid white;
	}
	td{
		background:#f7efd2;
		border:1px solid white;
	}
	#content{
		background:#ffffff;
	}
	footer{
		background-color:#f2dfa4;
	}
	.ui-widget-header{
		background:#f5e7ba;
	}
	nav, .card-header{
		background-color:#f5e7ba;
	}
	.card-body{
		background-color:#f7efd2
	}
	.mybtn{
		background-color:#fae1a7
	}
	.swal-wide{
		width:80% !important;
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
					class="navbar navbar-expand navbar-light topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<form class="form-inline">
						<button id="sidebarToggleTop"
							class="btn btn-link d-md-none rounded-circle mr-3">
							<i class="fa fa-bars"></i>
						</button>
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
					<h1 class="h3 mb-2 text-gray-800">派車訂單管理</h1>
					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3"
							style="justify-content: space-between; display: flex">
							<div id="test">
								<select style="border-radius: 0.35rem" id="selectStatus">
									<option value="7">所有訂單</option>
									<option value="0">待執行</option>
									<option value="1">執行中</option>
									<option value="2">已完成</option>
									<option value="3">已結單</option>
									<option value="4">取消申請中</option>
									<option value="5">已取消</option>
								</select> <input type=date id="searchDate">
							</div>
							<div>
								<label><input type="search" id="searchBar"
									class="form-control form-control-sm" placeholder="搜尋..."
									aria-controls="dataTable">
								</label> <select id="searchSlt" style="border-radius: 0.35rem">
									<option value="driverOrderId">訂單ID</option>
									<option value="memId">會員ID</option>
									<option value="driverId">司機ID</option>
								</select> <input type="button" class="mybtn" id="searchBtn" value="搜尋"
									style="border: 1px solid; border-radius: 0.35rem">
							</div>
						</div>
						<div class="card-body">
							<div class="table-responsive">

								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th><i class="fa-solid fa-clipboard-list"></i> 訂單ID</th>
											<th><i class="fa-solid fa-person"></i> 會員ID</th>
											<th><i class="fa-solid fa-car"></i> 司機ID</th>
											<th><i class="fa-regular fa-calendar"></i> 訂單日期</th>
											<th><i class="fa-solid fa-circle-info"></i> 訂單狀態</th>
											<th><i class="fa-regular fa-calendar-check"></i> 訂單建立日期</th>
											<th><i class="fa-regular fa-calendar-minus"></i> 訂單修改日期</th>
											<th><i class="fa-solid fa-pen-to-square" style="font-size:1.6em"></i></th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th><i class="fa-solid fa-clipboard-list"></i> 訂單ID</th>
											<th><i class="fa-solid fa-person"></i> 會員ID</th>
											<th><i class="fa-solid fa-car"></i> 司機ID</th>
											<th><i class="fa-regular fa-calendar"></i> 訂單日期</th>
											<th><i class="fa-solid fa-circle-info"></i> 訂單狀態</th>
											<th><i class="fa-regular fa-calendar-check"></i> 訂單建立日期</th>
											<th><i class="fa-regular fa-calendar-minus"></i> 訂單修改日期</th>
											<th><i class="fa-solid fa-pen-to-square" style="font-size:1.6em"></i></th>
										</tr>
									</tfoot>
									<tbody>
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
			<footer class="sticky-footer">
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

	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<!-- Bootstrap core JavaScript-->
	<script src="../vendor/jquery/jquery.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="../js/sb-admin-2.min.js"></script>
	<script src="js/driveorder.js"></script>

	<!-- Page level plugins -->
	<script src="../vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="../vendor/datatables/dataTables.bootstrap4.min.js"></script>
	<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="../js/login2.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@latest"></script>
	<!-- Page level custom scripts -->
	
	<script type="text/javascript">
	
	$("#dataTable").on('init.dt',function() {
  		let array;
  		<c:forEach var ="vo" items="${functionList}">
   			array = document.querySelectorAll('.function${vo.functionNoInt}');
    		for (let i = 0; i < array.length; i++) { 
      			array[i].removeAttribute("disabled"); 
    		}
  		</c:forEach>
    });
	$("#dataTable").on('processing.dt',function() {
  		let array;
  		<c:forEach var ="vo" items="${functionList}">
   			array = document.querySelectorAll('.function${vo.functionNoInt}');
    		for (let i = 0; i < array.length; i++) { 
      			array[i].removeAttribute("disabled"); 
    		}
  		</c:forEach>
    });
	</script>
</body>

</html>