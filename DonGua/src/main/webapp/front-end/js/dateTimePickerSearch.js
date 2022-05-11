$.datetimepicker.setLocale('zh');
$(function() {
	$('#start_dateTime').datetimepicker({
		format: 'Y-m-d H:i',
		
		minDate: Date(),
//		minTime: function(date) {
//			if (date > Date()){
//				return 0
//			}
//			return Date()
//		}, 
		beforeShowDay: function(date) {
			let somedate2 = new Date();
			somedate2.setDate(somedate2.getDate()+91);
			
			if ( date.getYear() > somedate2.getYear() || (date.getYear() == somedate2.getYear() && date.getMonth() > somedate2.getMonth()) || (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
			) {
				return [false, ""]
			}
			return [true, ""];},
		onShow: function() {
			this.setOptions({
				maxDate: $('#end_dateTime').val() ? $('#end_dateTime').val() : false
			})
		},
		timepicker: true,
		step: 30
	});

	$('#end_dateTime').datetimepicker({
		format: 'Y-m-d H:i',
		
		minDate: Date(),
//		minTime: Date(),
		beforeShowDay: function(date) {
			let somedate2 = new Date();
			somedate2.setDate(somedate2.getDate()+91);
			
			if ( date.getYear() > somedate2.getYear() || (date.getYear() == somedate2.getYear() && date.getMonth() > somedate2.getMonth()) || (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
			) {
				return [false, ""]
			}
			return [true, ""];},
		
		onShow: function() {
			this.setOptions({
				minDate: $('#start_dateTime').val() ? $('#start_dateTime').val() : false
			})
		},
		timepicker: true,
		step: 30
	});

});
//參考網站: https://xdsoft.net/jqplugins/datetimepicker/

//改key-value用
//	//      1.以下為某一天之前的日期無法選擇
//	var somedate1 = new Date('2022-02-08');
//	$('#f_date1').datetimepicker({
//		beforeShowDay: function(date) {
//			if (date.getYear() < somedate1.getYear() ||
//				(date.getYear() == somedate1.getYear() && date.getMonth() < somedate1.getMonth()) ||
//				(date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
//			) {
//				return [false, ""]
//			}
//			return [true, ""];
//		}
//	});
//	//      2.以下為某一天之後的日期無法選擇
//	var somedate2 = new Date('2017-06-15');
//	$('#f_date1').datetimepicker({
//		beforeShowDay: function(date) {
//			if (date.getYear() > somedate2.getYear() ||
//				(date.getYear() == somedate2.getYear() && date.getMonth() > somedate2.getMonth()) ||
//				(date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
//			) {
//				return [false, ""]
//			}
//			return [true, ""];
//		}
//	});

