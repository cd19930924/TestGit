// (($) => {
$(document).ready(function(){
    console.log(getCookie('token'));
    if(getCookie('token')===undefined){
        $("#order").remove();
		$("#logout").remove();
       }else{
		$("#login").remove();
	}
    })
//$("#logout").click(logout());
// })(jQuery);
