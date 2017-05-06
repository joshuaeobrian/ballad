$(function () {
    $(".ballad-card").click(function (e) {
        const ballad_id = $(this).children("input:first").val();
        console.log(ballad_id);
        document.location.href = "/editor/"+ballad_id;
    });
});