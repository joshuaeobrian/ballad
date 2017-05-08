
$(document).ready(function() {
  $('#sort-dropdown').click(function() {
    $('#sort-list').slideToggle();
  })

  $('.layout-icon').click(function() {
    $('#ballad-cards-container-grid').toggle();
    $('#ballad-cards-container-list').toggle();
  })
})
$(function () {
    $(".ballad-card").click(function (e) {
        const ballad_id = $(this).children("input:first").val();
        console.log(ballad_id);
        document.location.href = "/editor/"+ballad_id;
    });
});
