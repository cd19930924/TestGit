$(document).ready(function () {
  $.ajax({
      url: "driveorder.do",
      type: "POST",
      data: {
        action:"Get_All_Order",
      },
      success: function (data) {
          getData(data);
      }
  });
});

$("#selectStatus").change(function () {
$("#searchDate").val("");
  $.ajax({
      url: "driveorder.do", 
      type: "POST",
      data: {
        action:"Get_Order_Status",
        orderStatus:$("#selectStatus").val()
      },
      success: function (data) {
          getData(data);
      }
  });
});

$("#searchDate").change(function () {
$("#selectStatus").val("7");
  $.ajax({
      url: "driveorder.do",
      type: "POST",
      data: {
        action:"Get_Order_Date",
        sendDriveDate:$("#searchDate").val()
      },
      success: function (data) {
          getData(data);
      }
  });
});

$("#searchBtn").click(function () {
$("#selectStatus").val("7");
$("#searchDate").val("");
  $.ajax({
      url: "driveorder.do", /*GET格式*/
      data: {
        action:"Get_Order_By_Id",
        searchType:$("#searchSlt").val(),
        searchId:$("#searchBar").val()
      },
      success: function (data) {
          getData(data);
      },
    error:function(){
      alert("查無此訂單，請輸入正確的Id");
    }
  });
});

function editTodoItem(item) {
 let drverOrderId = item.id.substring(3);
if(item.value == "✎"){
    let ds = ($("#os"+drverOrderId).text()).trim();
    $("#os"+drverOrderId).attr("contentEditable","true");
    $("#os"+drverOrderId).focus();
    let os = ($("#os"+drverOrderId).text()).trim();
    $("#os"+drverOrderId).html(`<select class="myTest">
              <option value="0" >待執行</option>
              <option value="1" >執行中</option>
              <option value="2" >已完成</option>
              <option value="3" >已結單</option>
              <option value="4" >取消申請中</option>
              <option value="5" >已取消</option>
          </select>`);
    let selectedValue;
    if(ds === '待執行') {
      selectedValue = 0;
    } else if(ds === '執行中') {
      selectedValue = 1;
    } else if(ds === '已完成') {
      selectedValue = 2;
    } else if(ds === '已結單') {
      selectedValue = 3;
    } else if(ds === '取消申請中') {
      selectedValue = 4;
    } else if(ds === '已取消') {
      selectedValue = 5;
    }
    $("#os"+drverOrderId).find('.myTest').val(selectedValue);
    $("#btn"+drverOrderId).val("✔");
}else{
    $.ajax({
          url: "driveorder.do",
          type: "POST",
          data: {
            action:"Set_Order_Status",
            drverOrderId:drverOrderId,
            orderStatus:$("#os"+drverOrderId+">select").val()
          }
      });
    
      switch($("#os"+drverOrderId+">select").val()){
        case "0":
          $("#os"+drverOrderId).html("待執行");
          break;
        case "1":
          $("#os"+drverOrderId).html("執行中");
          break;
        case "2":
          $("#os"+drverOrderId).html("已完成");
          break;
        case "3":
          $("#os"+drverOrderId).html("已結單");
          break;
        case "4":
          $("#os"+drverOrderId).html("取消申請中");
          break;
        case "5":
          $("#os"+drverOrderId).html("已取消");
          break;
        default:
          break;
      }
      if($("#os"+drverOrderId).text()=="已結單"||$("#os"+drverOrderId).text()=="已取消"){
        $("#btn"+drverOrderId).remove();
      }else{
        $("#btn"+drverOrderId).val("✎");
      }
    
}
}

function getData(data) {
if($.fn.DataTable.isDataTable("#dataTable")){
   $('#dataTable').dataTable().fnClearTable();
   $('#dataTable').dataTable().fnDestroy();
}
$("#searchBar").val("");
  $("tbody").html('');
  var jsonObj = JSON.parse(data);
  for (let option of jsonObj) {
    let se = (option.sendDriveDate).substring(0,10);
    let st = (option.sendDriveTime).substring(9,11)+" "+(option.sendDriveTime).substring(0,8);
    let ce = (option.createTime).substring(0,10)+" "+(option.createTime).substring(11,19);
    let ue = "尚未修改";
    if(!(option.updateTime == null)){
       ue = (option.updateTime).substring(0,10)+" "+(option.updateTime).substring(11,19);;
    }
    
    $("tbody").append(`<tr>
        <td id="${option.drverOrderId}" onclick="serItem(this)">${option.drverOrderId}
              <td id="md${option.drverOrderId}">${option.memId}</td>
              <td id="dd${option.drverOrderId}">${option.driverId}</td>
              <td id="se${option.drverOrderId}">${se}</td>
              <td id="os${option.drverOrderId}">
              ${option.orderStatus=="0"?"待執行":""}
              ${option.orderStatus=="1"?"執行中":""}
              ${option.orderStatus=="2"?"已完成":""}
              ${option.orderStatus=="3"?"已結單":""}
              ${option.orderStatus=="4"?"取消申請中":""}
              ${option.orderStatus=="5"?"已取消":""}
              </td>
              <td id="ce${option.drverOrderId}">${ce}</td>
              <td id="ue${option.drverOrderId}">${ue}</td>
              <td> <input type="button" class="mybtn function6" id="btn${option.drverOrderId}" value="&#9998" style="border-radius: 0.35rem" onclick="editTodoItem(this)" disabled></td>
              </tr>`)
      if(option.orderStatus == "3" || option.orderStatus == "5"){
        $("#btn"+option.drverOrderId).remove();
      }
  }
  $("#dataTable").DataTable({
      language: {
          url: '//cdn.datatables.net/plug-ins/1.11.4/i18n/zh_Hant.json'
      },
      pagingType: "full_numbers",
      ordering:false,
      searching:false,
      retrieve: true
  });
}
function serItem(item) {
let drverOrderId = item.id;
  $.ajax({
      url: "driveorder.do", /*GET格式*/
      type: "POST",
      data:{
        action:"Get_Order_Info",
        drverOrderId:drverOrderId,
      },
      success: function (data) {
          var jsonObj = JSON.parse(data);
          for (let option of jsonObj) {
            let se = (option.sendDriveDate).substring(0,10);
            let st = (option.sendDriveTime).substring(9,11)+" "+(option.sendDriveTime).substring(0,8);
            Swal.fire({
              title:"訂單詳細資訊",
                html:
                `<table style="width:100%">`+
                `<tr><th>訂單編號</th><td>${option.drverOrderId}</td><th>預計里程數</th><td>${option.distance}</td></tr>`+
                `<tr><th>司機ID</th><td>${option.driverId}</td><th>訂單金額</th><td>${option.orderAmount}</td></tr>`+
                `<tr><th>司機姓名</th><td>${option.driverName}</td><th>派車日期</th><td>${se}</td></tr>`+
                `<tr><th>司機手機</th><td>${option.driverPhone}</td><th>派車時間</th><td>${st}</td></tr>`+
                `<tr><th>司機車牌號碼</th><td>${option.carNumber}</td><th>聯絡人姓名</th><td>${option.contactName}</td></tr>`+
                `<tr><th >出發點</th><td nowrap="nowrap">${option.startPoint}</td><th>聯絡電話</th><td>${option.contactNumber}</td></tr>`+
                `<tr><th>目的地</th><td nowrap="nowrap">${option.endPoint}</td><th>訂單狀態</th><td>`+
                `${option.orderStatus=="0"?"待執行":""}`+
                      `${option.orderStatus=="1"?"執行中":""}`+
                      `${option.orderStatus=="2"?"已完成":""}`+
                      `${option.orderStatus=="3"?"已結單":""}`+
                      `${option.orderStatus=="4"?"取消申請中":""}`+
                      `${option.orderStatus=="5"?"已取消":""}`+
                `<tr><th>備註</th><td style="word-break:break-all" colspan="3">${option.driveFeedback==null?"尚未填寫":(option.driveFeedback)}</td></tr>`+
                `</table>`,
              background:"#f1d683",
              customClass: 'swal-wide',
            });
          }
      }
  });
}