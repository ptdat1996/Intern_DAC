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
    return false;
});


/*-------- loading skill when scroll to-----*/
$(function () {
    var position = $(".skills").offset().top;

    $(window).scroll(function () {
        var distance = parseInt(position - $(window).scrollTop());

        if (distance < 100) {
            $('.progress-bar').each(function () {
                $(this).css("width", $(this).data("max") + '%');
            });
        } else {
            return false;
        }
    });
});