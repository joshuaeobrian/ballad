
function imageUrl(input) {
    if(input.files && input.files[0]){
        let reader = new FileReader();

        reader.onload = function (e) {
            $("#profile-image").attr("src",e.target.result);
            image = reader.result;

        };
        reader.readAsDataURL(input.files[0]);

    }
}
$(document).ready(function () {
   console.log("hello");
    $("#color-dropdown").css("color", $("#color-dropdown").attr("class"));

    $(".field").click(function (e) {
        $("input",this).prop("disabled",false);

    });

    $("#update-image").change(function () {
        imageUrl(this);
    });

    $('#color-dropdown').click(function() {
        $('#color-list').slideToggle();
        $('#color-list').css("background-color","#FFF");
    });


    $("#color-list li").click(function (e) {
        const colorPick = e.target;
        $("#current-color, #color-fa-angle-down").css("color", $(this).attr("value"));
        $("#color-id").attr("value",$(this).attr("id"));
        $("#current-color").html(colorPick.textContent);
        $("#color-list").slideToggle();

    });
    $("#color-list li").hover(function (e) {
        const colorPick = e.target;
        $(this).css("background-color",e.type === "mouseenter"? $(this).attr("value") :"#FFF");
        $(this).css("color",e.type === "mouseenter"? "#FFF" : $(this).attr("value"));

    });
});
