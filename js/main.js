/*------change header color and hide/show back to top button when scroll----*/
$(".back-to-top").hide();
$(window).scroll(function () {
    if ($(this).scrollTop() > 100) {
        $(".header").addClass("header-scrolled");
        $('.back-to-top').fadeIn('slow');
    } else {
        $(".header").removeClass("header-scrolled");
        $('.back-to-top').fadeOut('slow');
    }
});

/*------handle event back to top----*/
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
    var className = className.replace("scroll-to-", "");
    $("html,body").animate({
        scrollTop: $("." + className).offset().top
    }, 1500);
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

$(".btn-close").click(function () {
    $(".mobile-nav-menu-container").css("width", "0");
});

/*-------------------mobile menu click event handle-----------------*/
$(".mobile-nav-menu a").click(function () {
    $(".mobile-nav-menu-container").css("width", "0");
    goToByScroll($(this).attr("class"));
});

$(".mobile-nav-menu a").click(function () {
    $(".mobile-nav-menu .mobile-menu-active").removeClass("mobile-menu-active");
    $(this).addClass("mobile-menu-active");
});


$("body").click(function (e) {
    var currentWidth = parseInt($(".mobile-nav-menu-container").css("width"));
    if (currentWidth > 0) {
        var screenWidth = parseInt($(window).width());
        var currentPosition = parseInt(e.pageX);
        if (screenWidth - currentPosition > currentWidth) {
            $(".mobile-nav-menu-container").css("width", "0");
        }
    }
});