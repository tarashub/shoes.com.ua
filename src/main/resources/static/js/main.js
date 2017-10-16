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

/////////////////////////
// Window of Reristration
/////////////////////////
// Get the modal
var modal1 = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal1) {
        modal1.style.display = "none";
    }
}

/////////////////////////
// Window of Login
/////////////////////////
// Get the modal
var modal2 = document.getElementById('id02');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal2) {
        modal2.style.display = "none";
    }
}

////////////////////////////
// Save JSON object to MySQL
////////////////////////////

$(function () {
    $('#btnSubmit').click(function () {
        var firstName = $('#firstName').val();
        var lastName = $('#lastName').val();
        var userName = $('#userName').val();
        var password = $('#password').val();
        var email = $('#email').val();
        var phoneNumber = $('#phoneNumber').val();
        if (firstName != '' && lastName != '' && userName != '' && password != '' && email != '' && phoneNumber != '') {
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "Default.aspx/InsertData",
                data: '{firstName: ' + firstName +
                ',lastName: ' + lastName +
                ',userName:' + userName +
                ',password:' + password +
                ',email:' + email +
                ',phoneNumber:' + phoneNumber + '}',
                dataType: "json",
                success: function (data) {
                    var obj = data.d;
                    if (obj == 'true') {
                        $('#txtName').val('');
                        $('#txtUsername').val('');
                        $('#txtPassword').val('');
                        $('#lblmsg').html("Details Submitted Successfully");
                        window.location.reload();
                    }
                },
                error: function (result) {
                    alert("Error");
                }
            });
        }
        else {
            alert('Please enter all the fields')
            return false;
        }
    })
});

/////////////////////
// Confirm password
////////////////////
function check(input) {
    if (input.value !== document.getElementById('password').value) {
        input.setCustomValidity('Password Must be Matching.');
    } else {
        // input is valid -- reset the error message
        input.setCustomValidity('');
    }
}

////////////////////
// Search Service
////////////////////

// $('#menuShow').click(function () {
//     if ($('#mobileMenu').is(':visible')) $('#mobileMenu').hide();
//     else
//         $('#mobileMenu').show();
// });
// $(document).scroll(function () {
//     if ($(document).width() > 785) {
//         if ($(document).scrollTop() > $('header').height() + 10) $('nav').addClass('fixed'); else
//             $('nav').removeClass('fixed');
//     }
// });
// function closeSearch() {
//     $('#search_field').hide();
//     $('body').removeClass('overlayForSearch');
// }
// var width = $(window).width();
// $(window).resize(function () {
//     if ($(this).width() != width) {
//         width = $(this).width();
//         $('#mobileMenu').hide();
//         $('nav').removeClass('fixed');
//         if ($('#block_fixed').length) $('#block_fixed').hide();
//     }
// });
// $('#search').click(function () {
//     var search = $('#search_field');
//     if (search.css('display') == 'none') {
//         search.fadeIn();
//         $('body').addClass('overlayForSearch');
//     } else
//         search.fadeOut();
// });
//
// (function () {
//     var cx = '017848058708459995236:8emgwratyma';
//     var gcse = document.createElement('script');
//     gcse.type = 'text/javascript';
//     gcse.async = true;
//     gcse.src = 'https://cse.google.com/cse.js?cx=' + cx;
//     var s = document.getElementsByTagName('script')[0];
//     s.parentNode.insertBefore(gcse, s);
// })();



