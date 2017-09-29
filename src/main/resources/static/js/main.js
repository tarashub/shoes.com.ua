console.log("test ok");

(function ($) {

    skel
        .breakpoints({
            desktop: '(min-width: 737px)',
            tablet: '(min-width: 737px) and (max-width: 1200px)',
            mobile: '(max-width: 736px)'
        })
        .viewport({
            breakpoints: {
                tablet: {
                    width: 1080
                }
            }
        });

    $(function () {

        var $window = $(window),
            $body = $('body');

        // Отключить анимацию пока загружается странница
        $body.addClass('is-loading');

        $window.on('load', function () {
            $body.removeClass('is-loading');
        });

        // Fix: Placeholder polyfill.
        $('form').placeholder();

        // Dropdowns.
        $('#nav > ul').dropotron({
            offsetY: -22,
            mode: 'fade',
            noOpenerFade: true,
            speed: 300,
            detach: false
        });

        // Prioritize "important" elements on mobile.

        skel.on('+mobile -mobile', function () {
            $.prioritize(
                '.important\\28 mobile\\29',
                skel.breakpoint('mobile').active
            );
        });

        // Off-Canvas Navigation.

        // Title Bar.
        $(
            '<div id="titleBar">' +
            '<a href="#navPanel" class="toggle"></a>' +
            '<span class="title">' + $('#logo').html() + '</span>' +
            '</div>'
        )
            .appendTo($body);

        // Navigation Panel.
        $(
            '<div id="navPanel">' +
            '<nav>' +
            $('#nav').navList() +
            '</nav>' +
            '</div>'
        )
            .appendTo($body)
            .panel({
                delay: 500,
                hideOnClick: true,
                hideOnSwipe: true,
                resetScroll: true,
                resetForms: true,
                side: 'left',
                target: $body,
                visibleClass: 'navPanel-visible'
            });

        // Fix: Remove navPanel transitions on WP<10 (poor/buggy performance).
        if (skel.vars.os == 'wp' && skel.vars.osVersion < 10)
            $('#titleBar, #navPanel, #page-wrapper')
                .css('transition', 'none');

    });

})(jQuery);


// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}