//#region change header color and hide/show back to top button when scroll
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
//#endregion 

//#region handle event back to top
function backToTop() {
    $("body,html").animate({
        scrollTop: 0
    }, 800);
}

$(".back-to-top").click(function () {
    backToTop();
    var navMenu = $(".menu-active").parent();
    navMenu.children().removeClass("menu-active");
    navMenu.children().first().addClass("menu-active");
    return false;
});
//#endregion

//#region loading skill when scroll to it
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
//#endregion

//#region handling event scroll to the part of the page
function goToByScroll(className) {
    if (className === "home") {
        backToTop();
        return false;
    }
    var className = className.replace("scroll-to-", "");
    $("html,body").animate({
        scrollTop: $("." + className).offset().top
    }, 1500);
}

$(".nav-menu a").on("click", function (e) {
    if ($(this).attr("class") != undefined) {
        // Call the scroll function
        e.preventDefault();
        goToByScroll($(this).attr("class"));
    }
});

$(".footer-links a").on("click", function (e) {
    e.preventDefault();
    if ($(this).attr("class") != undefined) {
        // Call the scroll function
        goToByScroll($(this).attr("class"));
    }
});
//#endregion

//#region change color menu when click on it
$(".nav-menu a").click(function () {
    $(".nav-menu .menu-active").removeClass("menu-active");
    $(this).closest("li").addClass("menu-active");
});
//#endregion

//#region button mobile navbar toggle
$(".mobile-nav-toggle").click(function () {
    $(".mobile-nav-menu-container").css("width", "250px");
});

$(".btn-close").click(function () {
    $(".mobile-nav-menu-container").css("width", "0");
});
//#endregion

//#region mobile menu click event handle
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
//#endregion

//#region change menu active when scroll to part of website
$(function () {
    var aboutSection = $(".about").offset().top;
    var serviceSection = $(".service").offset().top;
    var teamSection = $(".team").offset().top;
    var contactSection = $(".contact").offset().top;

    var nav_item = $(".nav-menu li");

    $(document).scroll(function () {
        var scrollPosition = $(document).scrollTop();
        if (scrollPosition < aboutSection) {
            nav_item.removeClass("menu-active");
            nav_item.eq(0).addClass("menu-active");
        }
        if (scrollPosition >= aboutSection && scrollPosition < serviceSection) {
            nav_item.removeClass("menu-active");
            nav_item.eq(1).addClass("menu-active");
        }
        else if (scrollPosition >= serviceSection && scrollPosition < teamSection) {
            nav_item.removeClass("menu-active");
            nav_item.eq(2).addClass("menu-active");
        }
        else if (scrollPosition >= teamSection && scrollPosition < contactSection) {
            nav_item.removeClass("menu-active");
            nav_item.eq(3).addClass("menu-active");
        }
        else if (scrollPosition >= contactSection) {
            nav_item.removeClass("menu-active");
            nav_item.eq(4).addClass("menu-active");
        }

    });
});
//#endregion
