function sendPic(){
    var dataJSON = {};
    dataJSON["mealno"]=$("mealno").val();
    if($("addimg1").val()!==null && $("addimg1").val().trim()!==""){
        dataJSON["addimg1"]= $("addimg1").val();
    }
    if($("addimg2").val()!==null && $("addimg2").val().trim()!==""){
        dataJSON["addimg2"]= $("addimg2").val();
    }
    if($("addimg3").val()!==null && $("addimg3").val().trim()!==""){
        dataJSON["addimg3"]= $("addimg3").val();
    }
    $.ajax({
        url: "../back-end/meal/update-pic",
        type: "POST",
        data: JSON.stringify(dataJSON),
        success: function(data){
           
        }
    });
    
}