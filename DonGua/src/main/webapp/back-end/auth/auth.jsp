<%@page import="com.utils.DecodeCookieUtil"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.*" import="com.auth.model.*"
	import="com.function.model.*"%>

<%
List<AuthVO> authList = new AuthService().getAll();
pageContext.setAttribute("authList", authList);

FunctionService functionService = new FunctionService();
List<FunctionVO> list = functionService.getAllFunction();
pageContext.setAttribute("list", list);

AuthService authService = new AuthService();
List<AuthVO> authTableList = authService.findAuthTable();
pageContext.setAttribute("authTableList", authTableList);
%>
<% 
 int empId = (int) DecodeCookieUtil.getMemId(request);
%>


<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>權限功能群組管理</title>

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
			<li class="nav-item"><a class="nav-link" href="auth.jsp">
					<i class="fas fa-fw fa-table"></i> <span>權限功能群組管理</span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="empAuth.jsp">
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
					<h1 class="h3 mb-2 text-gray-800">權限功能群組管理</h1>
					<p class="mb-4">
						瀏覽群組、新增群組、刪除群組、修改群組<a target="_blank"
							href="https://datatables.net"></a>
					</p>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3"
							style="margin: 5px; padding: 5px; display: flex; justify-content: space-between; align-items: center;">
							<div>
								<h6 class="m-0 font-weight-bold text-primary">權限功能群組管理</h6>
							</div>
							<div>
								<input type="submit" value="新增群組" style="margin-right: 5px"
									class="m-0 font-weight-bold text-primary divleft"
									onclick="location.href='addAuth.jsp';">
							</div>
						</div>

						<div class="card-body">
							<div class="table-responsive">

								<!-- 查詢所有群組 -->
									<c:if test="${not empty errorMsgs}">
										<ul>
											<c:forEach var="message" items="${errorMsgs}">
												<li style="color:red">${message}</li>
											</c:forEach>
										</ul>
									</c:if>
								<div class="card shadow mb-4">
									<c:forEach var="authVO" items="${authList}">
										<FORM class="myForm" METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/auth/auth.do" style="margin-bottom: 0px;" name="form1">
											<input class="myAction" type="hidden" name="action" value=""> <!-- deleteAuth, updateFunctionNo  --> 
											<div class="card-header py-3" style="margin: 5px; padding: 5px; display: flex; justify-content: space-between; align-items: center;">
												<div>
           											<input class="authName" style="margin-left: 10px;border-style:none none solid none; /*  上 右 下  左 */

background-color:transparent;" name="empAuthName" value="${authVO.empAuthName}" disabled="disabled">
       											</div>
												
													<!-- 如果編號為A02就隱藏 -->
<%-- 												<c:if test="${not empty errorMsgs}"> --%>
<%-- 												</c:if>     --%>
												   											
												<!-- 按鈕區塊開始 -->
												<div>
												
													<!-- 編輯 -->
													<div style="display: inline" >
														<input type="hidden" name="empAuthNo" value="${authVO.empAuthNo}"> 
														<input type="hidden" name="empAuthName" value="${authVO.empAuthName}">
														<input type="submit" value="編輯" style="margin-right: 5px" class="edit m-0 font-weight-bold text-primary divleft">
														<input type="submit" value="完成" style="margin-right: 5px; display: none;" class="complete m-0 font-weight-bold text-primary divleft">
													</div>

													<!-- 刪除 -->
													<div style="display: inline">
														<input type="hidden" name="empAuthNo" value="${authVO.empAuthNo}"> 
														<input type="submit" value="刪除" style="margin-right: 5px" class="del m-0 font-weight-bold text-primary divleft">
													</div>

												</div>
												<!-- 按鈕區塊結束 -->
											</div>

											<!-- 印出空白清單並給予ID -->
											
											<div class="card-body">
												<c:forEach var="functionVO" items="${list}"
													varStatus="status">

													<label style="width: 150px"> 
													<input id="${authVO.empAuthNo}${functionVO.functionNo}" type="checkbox" name="functionNo" value="${functionVO.functionNo}">${functionVO.functionName}
													</label>

													<c:if test="${status.count % 4 == 0}">
														<br />
													</c:if>
												</c:forEach>
											</div>
										</FORM>
									</c:forEach>
								</div>
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
	<script src="../js/demo/datatables-demo.js"></script>




	<script type=text/javascript>
	
		// 讓空checkbox依照資料庫中的function打勾
		<c:forEach var="authFunctionVO" items="${authTableList}">
		document.querySelector(
				'#${authFunctionVO.empAuthNo}${authFunctionVO.functionNo}')
				.setAttribute('checked', true);
// 		document.querySelector(
// 				'#${authFunctionVO.empAuthNo}${authFunctionVO.functionNo}')
// 				.setAttribute('disabled', "disabled");
		
		</c:forEach>
		
// 		function edit(item){
// 			empAuthNo = item.parentElement.children[0].value
			
// 			item.parentElement.parentElement.previousElementSibling.firstElementChild.removeAttribute("disabled");
			
// 			item.parentElement.innerHTML =`
// 				<input type="hidden" name="empAuthNo" value=${'${empAuthNo}'}> 
// 				<input type="submit" value="完成" style="margin-right: 5px"
// 					class="m-0 font-weight-bold text-primary divleft">
// 				`
// 		}
		
		
		// checks disable
		let forms = document.getElementsByClassName('myForm');
		for(let i = 0; i < forms.length; i++) {
			let doms = forms[i].querySelectorAll('[name="functionNo"]');
			for(let j = 0; j < doms.length; j++) {
				doms[j].setAttribute("disabled", "disabled");
			}
		}
		
		
		let edit = document.getElementsByClassName('edit');
		for(let i = 0; i < edit.length; i++) {
			edit[i].addEventListener('click', function(e) {
				e.preventDefault();
				this.parentElement.getElementsByClassName('complete')[0].style.display = 'inline';
				
				this.style.display = 'none'; 
				
				let doms = this.closest('.myForm').querySelectorAll('[name="functionNo"]');
				let authName = this.closest('.myForm').querySelector('.authName');
				// 刪除群組名稱disable
				authName.removeAttribute("disabled");
				for(let j = 0; j < doms.length; j++) {
					doms[j].removeAttribute("disabled");
				}
			});
		}
		
		let complete = document.getElementsByClassName('complete');
		for(let i = 0; i < complete.length; i++) {
			complete[i].addEventListener('click', function(e) {
				e.preventDefault();
				this.parentElement.getElementsByClassName('edit')[0].style.display = 'inline';
				this.style.display = 'none'; 
				
				// submit
				this.closest('.myForm').querySelector('.myAction').value = 'updateFunctionNo';
				this.closest('.myForm').submit();
			});
		}
		
		let del = document.getElementsByClassName('del');
		for(let i = 0; i < del.length; i++) {
			del[i].addEventListener('click', function(e) {
				e.preventDefault();
				
				// submit
				this.closest('.myForm').querySelector('.myAction').value = 'deleteAuth';
				this.closest('.myForm').submit();
			});
		}

	</script>
	<script src="../js/login2.js"></script>


</body>

</html>