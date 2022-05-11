(() => {
    $(document).ready(function(){
        initRegistOption('#county','county');/*載入縣市Select資料*/
		if(getCookie('token')===undefined){
        $("#order").remove();
		$("#logout").remove();
       }else{
		$("#login").remove();
	}
    })
    $("#county").change(function(){/*點擊縣市select載入地區資料*/
        initRegistOption('#district','district',$(this).val())
    })

  $("#submitButton").click(function(){/*點擊註冊按鈕時呼叫傳送註冊資料*/
var dataJSON = {};
	dataJSON["memAcct"] = $("#account").val();
	dataJSON["memPwd"] = $("#password").val(); 
	dataJSON["memName"] = $("#username").val();
	dataJSON["memPhone"] = $("#phonenumber").val();
	dataJSON["distNo"] = $("#district").val();
	dataJSON["memAddr"] = $("#address").val();
	dataJSON["memGender"] = $("input[name='gender']:checked").val();
	dataJSON["memEmail"] = $("#email").val();
	dataJSON["memBirth"] = $("#birthday").val();
	dataJSON["memAge"] = $("#age").val();
	
	$.ajax({
       url: "../front-end/member/member-regist",
       type: "POST",
       data: JSON.stringify(dataJSON),
       success: function(data){
        var jsonObj = JSON.parse(data);
		if(jsonObj.errorCode==='accout-dulpicate'){
			alert(jsonObj.errorCode+','+jsonObj.errorMes);
			location.reload();
		}else if(jsonObj.errorCode==='enroll-sucess'){
            alert(jsonObj.errorMes+'請先進行信箱驗證激活會員帳號，稍後為你跳轉至登入頁面');
			location.href='login.html',3000;
		}else if(jsonObj.errorCode==='data-error'){
			alert(jsonObj.errorMes);
			location.reload();

		}else{
			alert(jsonObj.errorCode+','+jsonObj.errorMes);
			location.reload();
		}
       }
    });
    });

})();