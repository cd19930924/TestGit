var mylatlng={lat:24.957697805918915,lng:121.22507923675897};
var mapOption={
  center:mylatlng,
  zoom:7,
  mapTypeId:google.maps.MapTypeId.ROADMAP
};
//建立地圖
var map = new google.maps.Map(document.getElementById("googleMap"),mapOption)


//建立路線規劃服務
var directionService = new google.maps.DirectionsService();

//
var directionsDisplay = new google.maps.DirectionsRenderer();

directionsDisplay.setMap(map);

function calcRoute(){
  var request = {
    origin:document.getElementById("from").value,
    destination:document.getElementById("to").value,
    travelMode:google.maps.TravelMode.DRIVING,
    unitSystem:google.maps.UnitSystem.METRIC
  }
  
  directionService.route(request,(result,status)=>{
	  const ds = document.querySelector("#distance");
	  const te = document.querySelector("#maybetime");
	  const at = document.querySelector("#orderAmount");
    if(status == google.maps.DirectionsStatus.OK){
      console.log("here");
      ds.value = result.routes[0].legs[0].distance.text;
      te.value = result.routes[0].legs[0].duration.text;
	  at.value =  (Number)((ds.value).split(" 公里")[0]);
	  at.value = AmountCalc(at.value);
      directionsDisplay.setDirections(result);
    }else{
        directionsDisplay.setDirections( {routes:[]});
        map.setCenter(mylatlng);
        document.getElementById("output").innerHTML =`<font style="color: red"><i class="fa-solid fa-circle-exclamation"></i> 請修正以下錯誤:</font>
		<ul><p style="color: red">此次行程規劃無法達成，請重新選擇乘車地點與目的地</p></ul>`;
		ds.value = "";
      	te.value = "";
	  	at.value = "";
    }
  });
}

$('input').keypress(function(e) {
    code = e.keyCode ? e.keyCode : e.which; // in case of browser compatibility
    if(code == 13) {
        e.preventDefault();
        // do something
        /* also can use return false; instead. */
        }
    });

function AmountCalc(i){
	i*=1000;
	if(i<1500){
		return 85;
	}else{
		i -= 1500;
		let count = parseInt(i/250);
		return 85 + 5*count;
	}
}
var options = {
  types:['geocode','establishment'],
  componentRestrictions: {country: "tw"}
}

var input1 = document.getElementById("from");
var autocomplete1 = new google.maps.places.Autocomplete(input1, options);
var input2 = document.getElementById("to");
var autocomplete2 = new google.maps.places.Autocomplete(input2,options);


