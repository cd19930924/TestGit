function initOption(selectId,category,subcategory){
    $.ajax({
        url: "../../front-end/select-option?category="+category+"&subcategory="+subcategory,/*GET格式*/
        type: "GET",
        success: function(data){
            if(category ==='county'){
                $(selectId).html('<option value="">請選擇縣市</option>');
            }else if(category==='district'){
                $(selectId).html('<option value="">請選擇地區</option>');
            }
            var jsonObj = JSON.parse(data);
            console.log(jsonObj);
            for(let option of jsonObj.body.list){
               $(selectId).append("<option value="+option.value+">"+option.text+"</option>")
            }
        }
    });
}
function initRegistOption(selectId,category,subcategory){
    $.ajax({
        url: "../front-end/select-option?category="+category+"&subcategory="+subcategory,/*GET格式*/
        type: "GET",
        success: function(data){
            if(category ==='county'){
                $(selectId).html('<option value="">請選擇縣市</option>');
            }else if(category==='district'){
                $(selectId).html('<option value="">請選擇地區</option>');
            }
            var jsonObj = JSON.parse(data);
            console.log(jsonObj);
            for(let option of jsonObj.body.list){
               $(selectId).append("<option value="+option.value+">"+option.text+"</option>")
            }
        }
    });
}

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}
function logout(){
	 document.cookie = 'identity=0; max-age=0';
    document.cookie = 'token=0; max-age=0';
    location.href = '../login.html';
}
function logoutbef(){
	document.cookie = 'identity=0; max-age=0';
    document.cookie = 'token=0; max-age=0';
    location.href = './login.html';
}
function getMemberId(){
    var dataJSON = {};
    dataJSON["token"]= getCookie('token');
    $.ajax({
        url: "../front-end/common/get-memberid",
        type: "POST",
        data: JSON.stringify(dataJSON),
        success: function(data){
            return data.memberId;
        }
    });
}