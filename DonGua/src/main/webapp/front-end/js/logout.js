function logout1(){
	deleteCookie();
	setTimeout(() => location.href = '../login.html',500);
}

function deleteCookie(){
	document.cookie = 'identity=0; max-age=0;path=/CFA104G6/front-end';
    document.cookie = 'token=0; max-age=0;path=/CFA104G6/front-end';
}