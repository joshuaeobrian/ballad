// const postUser = (url,user)=>{
//     $.post(url,
//         user,
//         function (data) {
//             console.log(data);
//             if(url.includes("userlogin")){
//                 handleUserLogin(data);
//             }
//         });
//
// };


$(document).ready(function() {
//      var logged = false;
//      $("#logged").css('display','none');
//      $('#not-logged').click(function() {
//        $('#not-logged').css('display', 'none');
//        $('#logged').css('display', 'block');
//      });

  $('#not-logged').click(function() {
        $(".modal-overlay, .modal-container").toggle();
  });

  $(".fa-times").click(function() {
    $(".modal-overlay, .modal-container").toggle();
  });


  $('.global-slideout-button').click(function() {
    console.log("hi");

    $(".global-slideout-menu").animate({width:'toggle'},500);
    $(".global-slideout-menu, .global-slideout-overlay").show();
  });

  $(".fa-arrow-left").click(function() {
    $(".global-slideout-menu").animate({width:'toggle'},500);
    $(".global-slideout-overlay").hide();
  });


  /**
   * This section is for user login
   */
  // $("#login-btn").click(function (e) {
  //     e.preventDefault();
  //     const user = {
  //         username: $("#login-username").val(),
  //         password: $("#login-password").val(),
  //     };
  //     postUser("/userlogin", user);
  //
  //
  // });

  // $("#signup-btn").click(function (e) {
  //     e.preventDefault();
  //     const user = {
  //         id: -1,
  //         firstName:$("#first-name").val(),
  //         lastName:$("#last-name").val(),
  //         email:$("#email-address").val(),
  //         username:$("#signup-username").val(),
  //         password:$("#signup-password").val(),
  //         active: true,
  //
  //     }
  //     postUser("/signUp",user);
  // });

  // $("#signup").click(function (e) {
  //     e.preventDefault();
  //     $("#signup-form").show();
  //     $("#login-form").hide();
  // });

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
    })
});
