<%@page import="com.member.model.vo.MemberVO"%>
<%@page import="com.member.model.dao.impl.MemberDaoImpl"%>
<%@page import="com.member.model.dao.MemberDao"%>
<%@page import="com.carermemapply.model.*"%>
<%@page import="com.skill.model.*"%>
<%@page import="com.bank.model.*"%>
<%@page import="java.util.List"%>
<%@page import="com.common.model.service.JWTokenUtils"%>
<%@page import="com.utils.DecodeCookieUtil"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.*" import="com.driver.model.*"%>

<%
Integer memID = (int) DecodeCookieUtil.getMemId(request);

MemberDao memberDao = new MemberDaoImpl();
MemberVO memberVo = memberDao.getMemberData(memID);
pageContext.setAttribute("MemberVo", memberVo);
%>
<%
BankDAOImpl bankDaoImpl = new BankDAOImpl();
List<BankVO> list1 = bankDaoImpl.getAll();
pageContext.setAttribute("list1", list1);
%>
<%
SkillDAOImpl skillDaoImpl = new SkillDAOImpl();
List<SkillVO> list2 = skillDaoImpl.getAll();
pageContext.setAttribute("list2", list2);
%>
<%
CarerMemApplyVO carerMemApplyVo = (CarerMemApplyVO) request.getAttribute("carerMemApplyVo");
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>申請成為照護員 - DailyWarm</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/healthcare.png" />
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
</head>

<body id="page-top">
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
                            <li><a class="dropdown-item" id="applyCarer" href="../carermemapply/CarerMemApplyPage.jsp">申請成為照護員</a></li>
                            <li><a class="dropdown-item" id="memberData" href="../member/setMemData.html">會員資料設定</a></li>
                            <li><a class="dropdown-item" id="logout" onclick="logout1()">登出</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

	<!-- Services 服務內容 -->
	<section class="page-section bg-primary" id="services">

		<div class="container px-md-5">
			<div class="py-5 text-center row justify-content-center">
				<div class="col-md-10">
					<h2><font color="#7a472c"><b>申請成為照護員</b></font></h2>
					<p class="lead"><font color="#7a472c"><b>填寫申請資料...</b></font></p>
				</div>
			</div>
			<div class="row g-3">

				<div class="col-md-7 col-lg-8">

					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<form class="needs-validation" METHOD="post"
						ACTION="carerMemApply.do" enctype="multipart/form-data">
						<div class="row g-3">

							<h4 class="mb-3"><b>一般資料</b></h4>
							<hr class="my-4">

							<input type="hidden" name="memID" class="form-label"
								value="<%=memID%>">

							<div class="col-sm-7">
								<label for="acct" class="form-label"><font size="4.5" color="#7a472c"><b>帳號</b></font></label> <input
									type="text" class="form-control" id="acct"
									value="${MemberVo.memAcct}" readonly>
							</div>

							<div class="col-sm-7">
								<label for="name" class="form-label"><font size="4.5" color="#7a472c"><b>姓名</b></font></label> <input
									type="text" class="form-control" id="name"
									value="${MemberVo.memName}" readonly>
							</div>

							<div class="col-sm-8">
								<label for="memGender" class="form-label"><font size="4.5" color="#7a472c"><b>性別&nbsp;&nbsp;</b></font></label>
								<c:if test="${MemberVo.memGender=='0'}">
									<input type="radio" id="male" name="memGender" value="0"
										disabled="ture" checked>
									<label for="male">男</label>
									<input type="radio" id="female" name="memGender" value="1"
										disabled="ture">
									<label for="female">女</label>
								</c:if>
								<c:if test="${MemberVo.memGender=='1'}">
									<input type="radio" id="male" name="memGender" value="0"
										disabled="ture">
									<label for="male">男</label>
									<input type="radio" id="female" name="memGender" value="1"
										disabled="ture" checked>
									<label for="female">女</label>
								</c:if>
							</div>

							<div class="col-sm-7">
								<label for="age" class="form-label"><font size="4.5" color="#7a472c"><b>年齡</b></font></label> <input
									type="text" class="form-control" id="age"
									value="${MemberVo.memAge}" readonly>
							</div>

							<div class="col-sm-7">
								<label for="phone" class="form-label"><font size="4.5" color="#7a472c"><b>電話</b></font></label> <input
									type="text" class="form-control" id="phone"
									value="${MemberVo.memPhone}" readonly>
							</div>

							<div class="col-7">
								<label for="email" class="form-label"><font size="4.5" color="#7a472c"><b>Email</b></font></label> <input
									type="text" class="form-control" id="email"
									value="${MemberVo.memEmail}" readonly>
							</div>

							<div class="col-8">
								<label for="address" class="form-label"><font size="4.5" color="#7a472c"><b>聯絡地址</b></font></label> <input
									type="text" class="form-control" id="address"
									value="${MemberVo.memAddr}" readonly> <br> <br>
								<br>
							</div>

							<h4 class="mb-3"><b>專業照護資料</b></h4>
							<hr class="my-4">

							<div class="col-sm-8">
								<label for="serviceTpye" class="form-label"><font size="4.5" color="#7a472c"><b>服務類型&nbsp;&nbsp;</b></font></label>
								<input type="radio" name="serviceTpye" id="home" value="0"
									checked> <label for="home">居家照護</label> <input
									type="radio" name="serviceTpye" id="hospital" value="1">
								<label for="hospital">醫院看護</label> <input type="radio"
									name="serviceTpye" id="all" value="2"> <label for="all">全選</label>
							</div>

							<div class="col-md-5">
								<label for="country" class="form-label"><font size="4.5" color="#7a472c"><b>服務縣市</b></font></label> <select
									class="form-select" id="county" required>
									<option value="">請選擇縣市</option>
								</select>
							</div>
							<div class="col-md-5">
								<label for="district" class="form-label"><font size="4.5" color="#7a472c"><b>服務地區</b></font></label> <select
									class="form-select" id="district" name="district" required>
									<option value="">請選擇地區</option>
								</select>
							</div>

							<div class="col-sm-8">
								<label><font size="4.5" color="#7a472c"><b>提供的服務(一般服務)</b></font></label><br>
								<c:forEach var="SkillVO" items="${list2}">
									<c:if test="${SkillVO.skillType == '0'}">
										<label> <input type="checkbox" name="commSkill"
											value="${SkillVO.skillNo}">${SkillVO.skillName}
										</label><br>
									</c:if>
								</c:forEach>
							</div>
							<br>
							<div class="col-sm-8">
								<label><font size="4.5" color="#7a472c"><b>提供的服務(專業服務)</b></font></label><br>
								<c:forEach var="SkillVO" items="${list2}">
									<c:if test="${SkillVO.skillType == '1'}">
										<label> <input type="checkbox" name="ProSkill"
											value="${SkillVO.skillNo}">${SkillVO.skillName}
										</label><br>
									</c:if>
								</c:forEach>
							</div>
							
							<hr class="my-4">

							<div class="col-sm-7">
								<br>
								<h4 class="mb-3"><b>價錢設定</b></h4>

								<label for="priceHour" class="form-label"><font size="4.5" color="#7a472c"><b>未滿12小時之時薪</b></font></label> <input
									type="text" class="form-control" id="priceHour"
									name="priceHour" placeholder="" value="" required> <label
									for="priceHalfDay" class="form-label"><font size="4.5" color="#7a472c"><b>12-24小時之時薪</b></font></label> <input
									type="text" class="form-control" id="priceHalfDay"
									name="priceHalfDay" placeholder="" value="" required> <label
									for="priceDay" class="form-label"><font size="4.5" color="#7a472c"><b>24小時以上之時薪</b></font></label> <input type="text"
									class="form-control" id="priceDay" name="priceDay"
									placeholder="" value="" required>
							</div>

							<div class="col-sm-7">
								<label for="intro" class="form-label"><font size="4.5" color="#7a472c"><b>自我介紹</b></font></label>
								<textarea class="wishContent" id="intro" name="intro" maxlength="500" placeholder="請輸入不超過500個字"
									valign="top" align="left" style="width: 550px; height: 200px; word-break: break-all"></textarea>
							<span class="wordsNum">已輸入字元: 0/500</span>
							</div>

							<hr class="my-4">

							<h4 class="mb-3"><b>檔案上傳</b></h4>
							
							<table border="1"; border-collapse:collapse>
									<tr>
										<th><font size="4.5" color="#7a472c"><b>檔案名稱</b></font></th>
										<th><font size="4.5" color="#7a472c"><b>圖檔</b></font></th>
									</tr>
									<tr>
										<td>
											<label for="P01" class="form-label"><font size="4.5"><b>大頭照</b></font></label><br>
												<input
													type="hidden" name="P01" class="form-label" value="P01">
												<input type="file" accept="image/*" name="photoP01"
													onchange="showImg(this)"> 
										</td>
										<td>
											<img>
										</td>
									</tr>
									<tr>
										<td>
											<label for="P02" class="form-label"><font size="4.5"><b>身分證正面</b></font></label><br>
												<input
													type="hidden" name="P02" class="form-label" value="P02">
												<input type="file" accept="image/*" name="photoP02"
													onchange="showImg(this)"> 
										</td>
										<td>
											<img>
										</td>
									</tr>
									<tr>
										<td>
											<label for="P03" class="form-label"><font size="4.5"><b>身分證反面</b></font></label><br>
												<input
													type="hidden" name="P03" class="form-label" value="P03">
												<input type="file" accept="image/*" name="photoP03"
													onchange="showImg(this)"> 
										</td>
										<td>
											<img>
										</td>
									</tr>
									<tr>
										<td>
											<label for="P04" class="form-label"><font size="4.5"><b>良民證</b></font></label><br>
												<input
													type="hidden" name="P04" class="form-label" value="P04">
												<input type="file" accept="image/*" name="photoP04"
													onchange="showImg(this)"> 
										</td>
										<td>
											<img>
										</td>
									</tr>
									<tr>
										<td>
											<label for="P05" class="form-label"><font size="4.5"><b>存摺封面</b></font></label><br>
												<input
													type="hidden" name="P05" class="form-label" value="P05">
												<input type="file" accept="image/*" name="photoP05"
													onchange="showImg(this)"> 
										</td>
										<td>
											<img>
										</td>
									</tr>
									<tr>
										<td>
											<label label for="C01" class="form-label"><font size="4.5"><b>照服員結業證書</b></font></label><br>
												<input
													type="hidden" name="C01" class="form-label" value="C01">
												<input type="file" accept="image/*" name="photoC01"
													onchange="showImg(this)"> 
										</td>
										<td>
											<img>
										</td>
									</tr>
									<tr>
										<td>
											<label label for="C02" class="form-label"><font size="4.5"><b>護理師證書</b></font></label><br>
												<input
													type="hidden" name="C02" class="form-label" value="C02">
												<input type="file" accept="image/*" name="photoC02"
													onchange="showImg(this)"> 
										</td>
										<td>
											<img>
										</td>
									</tr>
									<tr>
										<td>
											<label label for="C07" class="form-label"><font size="4.5"><b>長照證明卡</b></font></label><br>
												<input
													type="hidden" name="C07" class="form-label" value="C07">
												<input type="file" accept="image/*" name="photoC07"
													onchange="showImg(this)"> 
										</td>
										<td>
											<img>
										</td>
									</tr>
									
								</table>

						</div>

						<hr class="my-4">

						<h4 class="mb-3"><b>收款設定</b></h4>

						<div class="row gy-3">
							<div class="col-md-7">
								<label for="bankCode" class="form-label"><font size="4.5" color="#7a472c"><b>銀行名稱</b></font></label> <select
									class="form-select" id="bankCode" name="bankCode" required>
									<option value="">請選擇...</option>
									<c:forEach var="BankVO" items="${list1}">
										<option value="${BankVO.bankCode}">${BankVO.bankCode}-${BankVO.bankName}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="col-sm-7">
							<label for="bankAcct" class="form-label"><font size="4.5" color="#7a472c"><b>銀行帳號</b></font></label> <input
								type="text" class="form-control" id="bankAcct" name="bankAcct"
								placeholder="" value="" required>
						</div>
						<br> <br> <br> <input type="hidden" name="action"
							value="insert"> <input type="submit" value="送出申請"
							class="w-100 btn btn-primary btn-lg">
					</form>
				</div>
				<hr class="my-4">
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
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="../js/scripts.js"></script>
	<script src="./CarerMemApplyPage.js"></script>
	<script src="../js/utility.js"></script>
	<script src="../js/logout.js"></script>

	<script type="text/javascript">
		function showImg(item) {
			let img = item.parentElement.nextElementSibling.firstElementChild;
			img.src = URL.createObjectURL(item.files[0]);
			img.width = 300;
			img.height = 225;
		}
		
		// 自我介紹 封裝一個限制字數方法
		var checkStrLengths = function (str, maxLength) {
		    var maxLength = maxLength;
		    var result = 0;
		    if (str && str.length > maxLength) {
		        result = maxLength;
		    } else {
		        result = str.length;
		    }
		    return result;
		}

		//監聽輸入
		$(".wishContent").on('input propertychange', function () {

		    //獲取輸入內容
		    var userDesc = $(this).val();

		    //判斷字數
		    var len;
		    if (userDesc) {
		        len = checkStrLengths(userDesc, 500);
		    } else {
		        len = 0
		    }

		    //顯示字數
		    $(".wordsNum").html('已輸入字元: ' + len + '/500');
		});
	</script>

	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script> -->
</body>

</html>