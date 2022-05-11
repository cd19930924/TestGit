(() => {
    $(document).ready(function(){
        setMemberDropdownList();
    })
    function setMemberDropdownList(){
        let identity =  getCookie('identity');
         console.log(identity);
        if(identity === 'M'){/*判斷是否為會員*/
            $("#switchAcct").hide();/*隱藏交換按鈕*/
            $("#applyCarer").show();/*開啟申請按鈕*/
        }else if (identity === 'C'){/*判斷是否為照護員*/
            $("#switchAcct").show();/*開啟交換按鈕*/
            $("#applyCarer").hide();/*隱藏申請按鈕*/
        }
    }
})();

$("#memberData").click(()=>{
    location.href=`./../setMemData.html`;
});

$("#logout").click(()=>{
    logoutbef();
});