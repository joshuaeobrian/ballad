
$(document).ready(function() {
//      var logged = false;
     $("#logged").css('display','none');
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
