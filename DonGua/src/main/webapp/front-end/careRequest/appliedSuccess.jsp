<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%-- <%response.sendRedirect("requestSearch.jsp"); %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>應徵成功</title>
</head>
<body>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
// alert('應徵成功！');
// swal("應徵成功！", "請等待審核", "success");
// window.location.href = "requestSearch.jsp";

swal({
	icon: "success",
    title: "應徵成功！",
    text: "請等待審核，將為您跳轉應徵列表",
    type: "success"
}).then(function() {
    window.location = "../careordermgt/CareApplyMgt.jsp";
});


</script>
</body>
</html>