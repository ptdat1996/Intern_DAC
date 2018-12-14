window.onscroll = function(){
    onScrollWindow();
}

function onScrollWindow(){
    if(document.body.scrollTop > 20 || document.documentElement.scrollTop > 20){
        document.getElementById("back-to-top").style.display = "block";
    }
    else{
        document.getElementById("back-to-top").style.display = "none";
    }
}

$("#back-to-top").click(function(event){
    event.preventDefault();   
    $("body").animate({scrollTop : 0} ,'300');
    console.log('click');
});

$(window).scroll(function () {
    if ($(this).scrollTop() > 100) {
        $('#header').addClass('header-scrolled');
    } else {
        $('#header').removeClass('header-scrolled');
    }
});

