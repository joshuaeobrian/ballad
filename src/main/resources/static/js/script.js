<<<<<<< HEAD

$(document).ready(function() {
  var logged = false;
  $("#logged").css('display','none');
  $('#not-logged').click(function() {
    $('#not-logged').css('display', 'none');
    $('#logged').css('display', 'block');
  });

  $('#logged').click(function() {
    $('.dropdown').slideToggle();
  })
})
=======
$(document).ready(function() {
//      var logged = false;
//      $("#logged").css('display','none');
//      $('#not-logged').click(function() {
//        $('#not-logged').css('display', 'none');
//        $('#logged').css('display', 'block');
//      });
    $("#logout").click(function () {
        document.location.href="/logout";
    });
    $("#my-ballads").click(function () {
        document.location.href = "/my-ballads";
    });

    $('#logged').click(function() {
        $('.dropdown').slideToggle();
    })
})
>>>>>>> 4ee0f1f17a7791b6302febfdb0182f4996c29745
