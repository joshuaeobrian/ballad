
$(document).ready(function() {
  $('.color1').animate({
    width: '70%',
  },600);
  $('.color2').delay(200).animate({
    width: '70%',
  },600);
  $('.color3').delay(300).animate({
    width: '70%',
  },600);
  $('.color4').delay(400).animate({
    width: '70%',
  },600);


// SLIDER
    $(".features-carousel-for").slick({
        arrows: false,
        autoplay: true,
        asNavFor: ".features-carousel-nav",
        fade: true,
        slidesToScroll: 1,
        slidesToShow: 1
    });

    $(".features-carousel-nav").slick({
        arrows: false,
        autoplay: true,
        asNavFor: ".features-carousel-for",
        infinite: true,
        dots: true,
        slidesToScroll: 1,
        slidesToShow: 1
    });

});



