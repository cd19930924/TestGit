<%@page import="com.utils.DecodeCookieUtil" %>
<%@page import="com.auth.model.AuthVO"%>
<%@page import="com.auth.model.AuthService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
List<AuthVO> functionList = DecodeCookieUtil.getFunctionList(request);
pageContext.setAttribute("functionList", functionList);
%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>最新消息管理</title>
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
<link type="text/css" rel="stylesheet" href="../resources/demos/style.css">


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
					<h1 class="h3 mb-2 text-gray-800">最新消息</h1>
					<p class="mb-4">
						最新消息發布處
					</p>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<span class="m-0 font-weight-bold text-primary">最新消息資料列表</span>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>編號</th>
											<th>標題</th>
											<th>內容</th>
											<th>狀態</th>
											<th>建立時間</th>
											<th>最後更新時間</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>編號</th>
											<th>標題</th>
											<th>內容</th>
											<th>狀態</th>
											<th>建立時間</th>
											<th>最後更新時間</th>
										</tr>
									</tfoot>
									<tbody>
										<jsp:useBean id="news" scope="page"
											class="com.infomanage.model.InfoService" />

										<c:forEach var="newslist" items="${news.allNews}">
											<tr>
												<td>
												<a href="#" id="opener${newslist.no}">${newslist.no}</a>
												<div id="dialog${newslist.no}" title="最新消息#${newslist.no}">
												<FORM onsubmit="return checkUpdate${newslist.no}();" METHOD="post" ACTION="<%=request.getContextPath()%>/infomanage">
												<table class="table table-bordered" width="100%">
															<tr>
																<th>最新消息編號</th>
																<td>${newslist.no}
																<input type="hidden" name="infoNo" value="${newslist.no}">
																</td>
															</tr>
															<tr>
																<th>建立時間</th>
																<td>${newslist.createTime}</td>
															</tr>
															<tr>
																<th>最後更新時間</th>
																<td>${newslist.updateTime}</td>
															</tr>
															<tr>
																<th>最新消息標題</th>
																<td><input type="text" name="infoName" id="infoName${newslist.no}" value="${newslist.name}" style="word-break:break-all;" /></td>
															</tr>
															<tr>
																<th>最新消息內容</th>
																<td><textarea name="infoContent" id="infoContent${newslist.no}" rows="4" cols="50" style="resize:none;word-break:break-all;">${newslist.content}</textarea></td>
															</tr>
															<tr>
																<th>最新消息狀態</th>
																<td><select name="infoStatus">
																	<option value="1">公開發布</option>
																	<option value="0">隱藏</option>
																	</select></td>
															</tr>
												</table>
												<input type="hidden" name="action" value="newsUpdate">
												<input type="submit" value="送出編輯" class="function9" disabled>
												</FORM>
												</div>
												</td>
												<td>${newslist.shortName}...</td>
												<td>${newslist.shortContent}...</td>
												<td>
												<a href="#" id="openerstatus${newslist.no}">${newslist.status}</a>
												<div id="dialogstatus${newslist.no}" title="編輯消息#${newslist.no}狀態">
												<FORM onSubmit="if(!confirm('是否確定更改？')){return false;}" METHOD="post" ACTION="<%=request.getContextPath()%>/infomanage">
												<select name="infoStatus"><option value="1">公開發布</option><option value="0">隱藏</option></select>
												<input type="hidden" name="infoNo"  value="${newslist.no}">
												<input type="hidden" name="action" value="newsStatusUpdate">
												<input type="submit" value="送出編輯" class="function9" disabled>
												</FORM>
												</div>
												</td>
												<td>${newslist.shortCreateTime}</td>
												<td>${newslist.shortUpdateTime}</td>
											</tr>
										</c:forEach>
										<tr>
										<td>
										<a href="#" id="openeradd">
										<i style="font-size:16px;color:#FFD700" class="fa">&#xf067;</i><span class="m-0 font-weight-bold text-primary"> 新增最新消息</span>
										</a>
										<div id="dialogadd" title="發布最新消息">
										<FORM onsubmit="return checkPost();" method="post" action="<%=request.getContextPath()%>/infomanage">
										<table class="table table-bordered" width="100%">
											<tr>
												<th>編號
												</th>
												<td>${news.nextNewsNo}
										<input type="hidden" id="infoNo" name="infoNo" value="${news.nextNewsNo}" />
												</td>
											</tr>
											<tr>
												<th>標題
												</th>
												<td>
										<input type="text" name="infoName" id="infoNamePost" />
												</td>
											</tr>
											<tr>
												<th>內容
												</th>
												<td><textarea name="infoContent" id="infoContentPost" rows="4" cols="50" style="resize:none;word-break:break-all;"></textarea></td>
											</tr>
											<tr>
												<th>發布狀態
												</th>
												<td>	<select id="infoSatus" name="infoSatus">
										<option value="0">隱藏</option>
										<option value="1">公開發布</option>
										</select>
												</td>
											</tr>
										
										</table>
											<input type="hidden" name="action" value="newsPost">
										<input type="submit" id="button" class="button function9" value="送出" disabled>
										</FORM>
										</div>
										</td>
										<td> </td>
										<td> </td>
										<td> </td>
										<td> </td>
										<td> </td>
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
						登出
					</button>
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

	<script type="text/javascript" src="../js/demo/datatables-demo.js"></script>
	<script type="text/javascript"
		src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="../js/login2.js"></script>
	<script type="text/javascript">
		<c:forEach var="newslist" items="${news.allNews}" varStatus="loop">

		$("#dialog${newslist.no}").dialog({
			autoOpen : false,
			width : 700
		});
		$("#opener${newslist.no}").click(function() {
			$("#dialog${newslist.no}").dialog("open");
		});
		
		$("#dialogstatus${newslist.no}").dialog({
			autoOpen : false,
			width : 400
		});
		$("#openerstatus${newslist.no}").click(function() {
			$("#dialogstatus${newslist.no}").dialog("open");
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
		  
		  function checkPost(){
			  var infoName = document.querySelector('#infoNamePost');
			  var infoContent = document.querySelector('#infoContentPost');
			  if (!infoName.value || !infoContent.value){
				  alert('標題或文字內容不得為空');
				  return false;
			  } 
			  if (!confirm('是否確定送出？')){
				  return false;
			  }
			  return true;
			  
		  }
		  
		  <c:forEach var="newslist" items="${news.allNews}" varStatus="loop">
		  function checkUpdate${newslist.no}(){
			  var infoName${newslist.no} = document.querySelector('#infoName${newslist.no}');
			  var infoContent${newslist.no} = document.querySelector('#infoContent${newslist.no}');
			  if (!infoName${newslist.no}.value || !infoContent${newslist.no}.value){
				  alert('標題或文字內容不得為空');
				  return false;
			  } 
			  if (!confirm('是否確定送出？')){
				  return false;
			  }
			  return true;
		  }
		  </c:forEach>
	</script>
</body>
</html>