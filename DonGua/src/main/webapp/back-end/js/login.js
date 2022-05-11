(() => {
$("#submitButton").click(function(){/*登入時點*/
	var dataJSON = {};
	dataJSON["empAcct"] = $("#account").val();
	dataJSON["empPwd"] = $("#password").val();
	console.log(JSON.stringify(dataJSON));    
	$.ajax({
        url: "login",
        type: "POST",
        data: JSON.stringify(dataJSON),
        success: function(data){
		var jsonObj = JSON.parse(data);
		console.log(jsonObj.errorCode);
		if(jsonObj.errorCode==='login-failure'){
			alert(jsonObj.errorCode+','+jsonObj.errorMes);
			return;
		}else if(jsonObj.errorCode==='login-success'){
            alert(jsonObj.errorMes);
			document.cookie = 'identity=0; max-age=0';
			document.cookie = `token=${jsonObj.body.token}; max-age=60*60*24`;
			document.cookie = `identity=${jsonObj.body.identity}; max-age=60*60*24`;
			location.href='index.html',1000;
		}
    }
	});
});

$("#logout").click(()=>{
    location.href = './login.html';
    document.cookie = 'identity=0; max-age=0';
    document.cookie = 'token=0; max-age=0';
    document.cookie = 'ugid=0; max-age=0';
    document.cookie = 'JSESSIONID=0; max-age=0';
});
})();
function jumpRegistPage(){
	window.location.href="regist.html";
}