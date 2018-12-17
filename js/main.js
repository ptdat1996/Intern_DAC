/*------change header color when scroll----*/
$(window).scroll(function () {
    if ($(this).scrollTop() > 100) {
        $(".header").addClass("header-scrolled");
    } else {
        $(".header").removeClass("header-scrolled");
    }
});

/*------handle event back to top----*/
$(".back-to-top").hide();

$(window).scroll(function () {
    if ($(this).scrollTop() > 100) {
        $('.back-to-top').fadeIn('slow');
    } else {
        $('.back-to-top').fadeOut('slow');
    }
});

$(".back-to-top").click(function () {
    $("body,html").animate({
        scrollTop: 0
    }, 800);
    var navMenu = $(".menu-active").parent();
    navMenu.children().removeClass("menu-active");
    navMenu.children().first().addClass("menu-active");
    return false;
});


/*-------- loading skill when scroll to-----*/
$(function () {
    var position = $(".skills").offset().top;

    $(window).scroll(function () {
        var distance = parseInt(position - $(window).scrollTop());

        if (distance < 300) {
            $('.progress-bar').each(function () {
                $(this).css("width", $(this).data("max") + '%');
            });
        } else {
            return false;
        }
    });
});

/*-----------handling event scroll to the part of the page-----------*/
function goToByScroll(className) {
    var className = className.replace("class-", "");
    $("html,body").animate({
        scrollTop: $("." + className).offset().top
    }, 1000);
}

$(".nav-menu a").on("click", function () {
    // Call the scroll function
    goToByScroll($(this).attr("class"));
});

/*---------------change color menu when click on it----------------*/
$(".nav-menu a").click(function () {
    $(".nav-menu .menu-active").removeClass("menu-active");
    $(this).closest("li").addClass("menu-active");
});

/*------------------button mobile navbar toggle---------------------*/
$(".mobile-nav-toggle").click(function () {
    $(".mobile-nav-menu-container").css("width", "250px");
});

$(".btn-close").click(function(){
    $(".mobile-nav-menu-container").css("width", "0");
});
