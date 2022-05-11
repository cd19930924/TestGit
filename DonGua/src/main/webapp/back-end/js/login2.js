(() => {

$("#logout").click(()=>{
    location.href = '../login.html';
    document.cookie = 'identity=0; max-age=0';
    document.cookie = 'token=0; max-age=0';
    document.cookie = 'ugid=0; max-age=0';
    document.cookie = 'JSESSIONID=0; max-age=0';
});
})();
