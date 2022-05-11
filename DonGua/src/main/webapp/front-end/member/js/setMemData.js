(()=>{
    $(document).ready(()=>{
        initOption('#county','county');
		getMemberData();
    })
    $("#county").change(function(){/*點擊縣市select載入地區資料*/
        initOption('#district','district',$(this).val());
    })
    function getMemberData(){
        let dataJSON = {};
        dataJSON["action"]='select';
		dataJSON["token"]=getCookie("token");
        console.log(JSON.stringify(dataJSON));
        $.ajax({
            url: "../../front-end/member/member-data-setting", /*POST格式*/
            type: "POST",
            data: JSON.stringify(dataJSON),
            success: function(data){
				var jsonObj = JSON.parse(data);
                $("#account").val(jsonObj.body.memAcct);
                $("#password").val(jsonObj.body.memPwd);
                $("#username").val(jsonObj.body.memName);
                $("#phonenumber").val(jsonObj.body.memPhone);
                $("#county").val(jsonObj.body.countyNo);
				$("#address").val(jsonObj.body.memAddr);
				if(jsonObj.body.memGender=='0'){
					$("#men").attr("checked",''); 
				}else if(jsonObj.body.memGender=='1'){
                    $("#women").attr("checked",''); 
                }
				$("input[name='gender']:checked").val();
                $("#email").val(jsonObj.body.memEmail);
                $("#birthday").val(jsonObj.body.memBirth);
                $("#age").val(jsonObj.body.memAge);
                initOption('#district','district',jsonObj.body.countyNo);
                setTimeout(() => $("#district").val(jsonObj.body.distNo),500);
            }
        });
    }
})
();





$("#save").click(()=>{
    if($("#password").val() !== $("#comfirmpassword").val()){
        alert('密碼與確認密碼輸入內容不同!!');
        return 0;
    }
	let dataJSON = {};
	dataJSON["action"]='update';
    dataJSON["memAcct"] = $("#account").val();
    dataJSON["memPwd"] = $("#password").val();
    dataJSON["memName"] = $("#username").val();
    dataJSON["memPhone"] = $("#phonenumber").val();
    dataJSON["distNo"] = $("#district").val();
    dataJSON["memAddr"] = $("#address").val();
    dataJSON["memGender"] = $("input[name='gender']:checked").val();
    dataJSON["memEmail"] = $("#email").val();
    dataJSON["memBirthDate"] = $("#birthday").val();
    dataJSON["memAge"] = $("#age").val();
    $.ajax({
        url: "../../front-end/member/member-data-setting",
        type: "POST",
        data: JSON.stringify(dataJSON),
        success: function (data) {
            var jsonObj = JSON.parse(data);
            if (jsonObj.errorCode === 'update-success') {
                alert(jsonObj.errorCode + ',' + jsonObj.errorMes);
                getMemberData();
            } else if (jsonObj.errorCode === 'update-failure') {
                alert(jsonObj.errorMes );
                return;
            } else {
                alert(jsonObj.errorCode + ',' + jsonObj.errorMes);
            }
        }
    });
})

