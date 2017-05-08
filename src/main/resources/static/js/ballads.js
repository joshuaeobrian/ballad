$(document).ready(function() {
  $('#sort-dropdown').click(function() {
    $('#sort-list').slideToggle();
  })

  $('.layout-icon').click(function() {
    $('#ballad-cards-container-grid').toggle();
    $('#ballad-cards-container-list').toggle();
  })
})
