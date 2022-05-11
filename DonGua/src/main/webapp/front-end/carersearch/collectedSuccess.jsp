<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>收藏成功</title>
</head>
<body>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
// alert('應徵成功！');
// swal("應徵成功！", "請等待審核", "success");
// window.location.href = "requestSearch.jsp";

swal({
	icon: "success",
    title: "收藏成功！",
    text: "請至收藏列表確認",
    type: "success"
}).then(function() {
    window.location = "collection.jsp";
});


</script>
</body>
</html>