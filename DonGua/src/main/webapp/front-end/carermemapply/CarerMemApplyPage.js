(() =>{
    $(document).ready(function(){
        initOption('#county','county');/*載入縣市Select資料*/
})})();
 $("#county").change(function(){/*點擊縣市select載入地區資料*/
        initOption('#district','district',$(this).val())
    })