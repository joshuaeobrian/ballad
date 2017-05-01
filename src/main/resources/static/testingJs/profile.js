/**
 * Created by josh on 4/30/17.
 */
$(function () {
    $(".myBallad").click(function (e) {
        const ballad_id = $(this).children("input:first").val();
        console.log(ballad_id);
        document.location.href = "/ballad/"+ballad_id;
    });
});