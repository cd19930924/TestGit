(()=>$(document).ready(function(){
		if(getCookie('token')===undefined){
        $("#order").remove();
		$("#logout").remove();
       }else{
		$("#login").remove();
	}
}))();