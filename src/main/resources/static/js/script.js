$(document).ready(function() {

  $('#not-logged').click(function() {
        $(".modal-overlay, .modal-container").toggle();
  });

  $(".fa-times").click(function() {
    $(".modal-overlay, .modal-container").toggle();
  });


  $('.global-slideout-button').click(function() {
    $(".global-slideout-menu").animate({width:'toggle'},500);
    $(".global-slideout-menu, .global-slideout-overlay").show();
  });

  $(".fa-arrow-left").click(function() {
    $(".global-slideout-menu").animate({width:'toggle'},500);
    $(".global-slideout-overlay").hide();
  });

  $("#login").click(function (e) {
      e.preventDefault();
      $("#signup-form").hide();
      $("#login-form").show();
  });


    $("#logout").click(function () {
        document.location.href="/logout";
    });
    $("#my-ballads").click(function () {
        document.location.href = "/my-ballads";
    });
    $("#profile-settings").click(function () {
        document.location.href = "/Profile";
    });

    $('#logged').click(function() {
        $('.dropdown').slideToggle();
    });




});
