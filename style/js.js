jQuery(function() {
    "use strict";

    // zoom image script
    if ( document.documentElement.scrollWidth > 700) {
        let allImage = document.getElementsByTagName('img');

        [].forEach.call( allImage, function(elem) {
            elem.onclick = function() {
                [].forEach.call( allImage, function(el) {
                    window.addEventListener('click', function (e) {
                        if ( !elem.contains(e.target) && elem !== el ) elem.style.width = '49%';
                    });
                });
                ( this.style.width === '100%' ) ? this.style.width = '49%' : this.style.width = '100%';
            }
        });
    } // zoom image

    // Button Go Up
    $('body').append('<a href="#" id="go-top" title="Вверх"></a>');

    $(function() {
        $.fn.scrollToTop = function() {
            $(this).hide().removeAttr("href");
            if ($(window).scrollTop() >= "250") $(this).fadeIn("slow")
            const scrollDiv = $(this);
            $(window).scroll(function() {
                if ($(window).scrollTop() <= "250") $(scrollDiv).fadeOut("slow")
                else $(scrollDiv).fadeIn("slow")
            });
            $(this).click(function() {
                $("html, body").animate({scrollTop: 0}, "slow");
            });
        }
    });

    $(function() {
        $("#go-top").scrollToTop();
    }); // end Button Go Up



});

