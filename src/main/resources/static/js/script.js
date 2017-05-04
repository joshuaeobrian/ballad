
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
