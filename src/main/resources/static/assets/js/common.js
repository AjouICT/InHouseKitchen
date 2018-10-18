;(function($){
  "use strict";
/*==============================================================
    Header Fixed Scroll
=============================================================*/
$(window).on('scroll', function (event) {
    var scroll = $(window).scrollTop();
    if (scroll >= 80) {
        $("#header-fix").addClass("nav-affix");
        $("#cbp-spmenu-s1").addClass("nav-affix");
    } else {
        $("#header-fix").removeClass("nav-affix");
        $("#cbp-spmenu-s1").removeClass("nav-affix");
    }
});

/*==============================================================
    Countdown
=============================================================*/
function getTimeRemaining(endtime) {
  var t = Date.parse(endtime) - Date.parse(new Date());
  var seconds = Math.floor((t / 1000) % 60);
  var minutes = Math.floor((t / 1000 / 60) % 60);
  var hours = Math.floor((t / (1000 * 60 * 60)) % 24);
  var days = Math.floor(t / (1000 * 60 * 60 * 24));
  return {
    'total': t,
    'days': days,
    'hours': hours,
    'minutes': minutes,
    'seconds': seconds
  };
}

function initializeClock(id, endtime) {
  var clock = document.getElementById(id);
  var daysSpan = clock.querySelector('.days');
  var hoursSpan = clock.querySelector('.hours');
  var minutesSpan = clock.querySelector('.minutes');
  var secondsSpan = clock.querySelector('.seconds');

  function updateClock() {
    var t = getTimeRemaining(endtime);

    daysSpan.innerHTML = t.days;
    hoursSpan.innerHTML = ('0' + t.hours).slice(-2);
    minutesSpan.innerHTML = ('0' + t.minutes).slice(-2);
    secondsSpan.innerHTML = ('0' + t.seconds).slice(-2);

    if (t.total <= 0) {
      clearInterval(timeinterval);
    }
  }

  updateClock();
  var timeinterval = setInterval(updateClock, 1000);
}

var deadline = new Date(Date.parse(new Date()) + 28 * 11 * 28 * 60 * 1000);
 if ( $('#clockdiv').length){
initializeClock('clockdiv', deadline);
 }


/*==============================================================
    Background Image Maker
=============================================================*/
$(document).ready(function() {
  $('.background-image-maker').each(function() {
    var imgURL = $(this).next('.holder-image').find('img').attr('src');
    $(this).css('background-image', 'url(' + imgURL + ')');
  });
});

/*==============================================================
    Animates
=============================================================*/
new WOW().init();

/*==============================================================
    Slick Procuct Slider 1
=============================================================*/
$('.center').slick({
    centerMode: true,
    centerPadding: '150px',
    slidesToShow: 3,
    arrows: true,
    autoplay: false,
    responsive: [
    {
      breakpoint: 1199,
      settings: {
        arrows: true,
        centerMode: true,
        centerPadding: '150px',
        slidesToShow: 1
      }
    },
    {
      breakpoint: 767,
      settings: {
        arrows: true,
        centerMode: true,
        centerPadding: '0px',
        slidesToShow: 1
      }
    }
  ]
});

/*==============================================================
    Slick Procuct Slider 2
=============================================================*/     
$('.center2').slick({
    centerMode: true,
    centerPadding: '150px',
    slidesToShow: 3,
    arrows: false,
    autoplay: true,
    responsive: [
    {
      breakpoint: 1199,
      settings: {
        arrows: false,
        centerMode: true,
        centerPadding: '150px',
        slidesToShow: 1
      }
    },
    {
      breakpoint: 767,
      settings: {
        arrows: false,
        centerMode: true,
        centerPadding: '0px',
        slidesToShow: 1
      }
    }
  ]
});

/*==============================================================
    Slick Procuct Slider 3
=============================================================*/      
$('.center3').slick({
    centerMode: true,
    centerPadding: '0px',
    slidesToShow: 3,
    arrows: false,
    autoplay: true,
    responsive: [
    {
      breakpoint: 1199,
      settings: {
        arrows: false,
        centerMode: true,
        centerPadding: '0px',
        slidesToShow: 2
      }
    },
    {
      breakpoint: 767,
      settings: {
        arrows: false,
        centerMode: true,
        centerPadding: '0px',
        slidesToShow: 1
      }
    }
  ]
});

/*==============================================================
    Masonry
=============================================================*/
$(window).load(function() {
    $('.portfolio-box').masonry({
    // options
    itemSelector: '.post'
    });
});

(function( $ ) {
    var $container = $('.portfolio-box2');
    $container.imagesLoaded( function () {
        $container.masonry({
            columnWidth: '.post',
            itemSelector: '.post'
        });
    });

    //Reinitialize masonry inside each panel after the relative tab link is clicked - 
    $('a[data-toggle=tab]').each(function () {
        var $this = $(this);
        $this.on('shown.bs.tab', function () {
            $container.masonry({
                columnWidth: '.post',
                itemSelector: '.post'
            });
        }); //end shown
    });  //end each
	
})(jQuery);
/*==============================================================
    Testimonial Slider
=============================================================*/
$(document).ready(function(){
    $("#testimonial").owlCarousel({
        items:1,
        itemsDesktop:[1199,1],
        itemsDesktopSmall:[979,1],
        itemsTablet:[768,1],
        pagination: false,
        navigation : false,
        autoPlay:true
    });
});

/*==============================================================
    Back To Top
=============================================================*/
$(document).ready(function () {
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('.scrollup').fadeIn();
        } else {
            $('.scrollup').fadeOut();
        }
    });

    $('.scrollup').click(function () {
        $("html, body").animate({
            scrollTop: 0
        }, 600);
        return false;
    });
});

/*==============================================================
    flexslider Blog Page Item
=============================================================*/
$(window).load(function(){
    $('#carousel').flexslider({
      animation: "slide",
      controlNav: false,
      animationLoop: false,
      slideshow: false,
      itemWidth: 200,
      itemMargin: 20,
      asNavFor: '#slider'
    });

    $('#slider').flexslider({
      animation: "slide",
      controlNav: false,
      animationLoop: false,
      slideshow: false,
      sync: "#carousel",
      start: function(slider){
        $('body').removeClass('loading');
      }
    });
});
/*==============================================================
    Image Lightbox Gallery
=============================================================*/
$(document).on('click', '[data-toggle="lightbox"]', function(event) {
    event.preventDefault();
    $(this).ekkoLightbox();
});
/*==============================================================
    Range Slider 1
=============================================================*/
// Without JQuery
var mySlider = $( "#ex6" ).bootstrapSlider();

 $('#ex6').slider().on('slide',function(value) {
	 $("#ex6SliderVal").html($('#ex6').val());
});
/*==============================================================
    Range Slider 2
=============================================================*/

// Without JQuery
var mySlider = $( "#ex7" ).bootstrapSlider();
 $('#ex7').slider().on('slide',function() {
	 $("#ex7SliderVal").html($('#ex7').val());
});
})(jQuery);

/*==============================================================
    Range Slider 1
=============================================================*/
// Without JQuery
var mySlider = $( "#ex8" ).bootstrapSlider();

 $('#ex8').slider().on('slide',function(value) {
	 $("#ex8SliderVal").html($('#ex8').val());
});


/*==============================================================
    Cache Jquery
=============================================================*/
$(document).ready(function() {
  var foo = $("#bar");
  foo.attr("style", "color: black;");
  foo.attr("class", "foo");
});