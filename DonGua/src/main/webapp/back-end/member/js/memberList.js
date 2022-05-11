(()=>{
	$(document).ready(()=>{
        getAllMemberData();
    })
})();

var jsonObjData ;
function getAllMemberData(){
	let dataJSON = {};
	// dataJSON["token"]=getCookie(token);
	dataJSON["action"]='getAll';
	console.log(JSON.stringify(dataJSON));
	$.ajax({
		url: "../../back-end/member/manage-member", /*POST格式*/
		type: "POST",
		data: JSON.stringify(dataJSON),
		success: function(data){	
			var jsonObj = JSON.parse(data);
			jsonObjData = JSON.parse(data) ;
			let index =1;
			$("#memberdata").html("");
			for(let memberData of jsonObj.body){
				$("#memberdata").append("<tr>"
				+"<td id= memid"+memberData.memId+" value="+memberData.memId+">"+memberData.memId+"</td>"
				+"<td>"+memberData.memAcct+"</td>"
				+"<td>"+memberData.memName+"</td>"
				+"<td>"+memberData.memGender+"</td>"
				+"<td>"
				+"<select id=status"+memberData.memId+" disabled='disabled'>"
				+"<option value='0'>未開通</option>"
				+"<option value='1'>已開通</option>"
				+"<option value='2'>停權</option>"
  				+"<option value='3'>黑名單</option>"
  				+"</select>"
				+"</td>"
				+"<td><button id='button"+memberData.memId+"' onclick=changeStatus("+memberData.memId+")>修改</button></td>"
				+"<td><button id='detail"+memberData.memId+"' onclick=showDetail("+memberData.memId+")>明細</button></td>"
				+"</tr>");
				$("#status"+memberData.memId).val(memberData.memStatus)
			}
			$("#dataTable").DataTable({
                language: {
                    url: '//cdn.datatables.net/plug-ins/1.11.4/i18n/zh_Hant.json'
                },
                pagingType: "full_numbers",
            });
			
		}
	});
}

function changeStatus(id){
	if($("#status"+id).attr('disabled')==='disabled'){
		$("#status"+id).attr('disabled',false);
		$("#button"+id).text("修改儲存");
	}else{
		$("#status"+id).attr("disabled",true);
		$("#button"+id).text("修改");
		let dataJSON = {};
		dataJSON["action"]='update';
		dataJSON["memid"]= $("#memid"+id).text();
		dataJSON["status"]= $("#status"+id).val();
		JSON.stringify(dataJSON);
		$.ajax({
			url: "../../back-end/member/manage-member", /*POST格式*/
			type: "POST",
			data: JSON.stringify(dataJSON),
			success: function(data){	
				var jsonObj = JSON.parse(data);
				if(jsonObj.errorCode==='update-sucess'){
					getAllMemberData();
				}else if(jsonObj.errorCode==='update-failure'){
					alert('更新狀態失敗請重新嘗試')
					getAllMemberData();
				}
			}
		});
	}
}

function showDetail(id){
	for(let memberData of jsonObjData.body){
		if(memberData.memId=== id){
			swal(`會員:${memberData.memName}`, `帳號:${memberData.memAcct}\n 性別:${memberData.memGender}\n 電話:${memberData.memPhone}\n 地址:${memberData.memAddr}\n 地區:${memberData.distName}\n 信箱:${memberData.memEmail}\n 生日:${memberData.memBirth}\n 年齡:${memberData.memAge}`, "info", {button: "確認"});
		}
	}
}	