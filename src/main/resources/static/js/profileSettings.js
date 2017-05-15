
let image;
// const updateUser = (url,user)=>{
//     $.ajax({
//         type: "POST",
//         url: url,
//         async: true,
//         data: user,
//         contentType: "multipart/form-data",
//         processData: false,
//         success: function(msg) {
//             alert("File has been uploaded successfully");
//         },
//         error:function(msg) {
//             $("#upload-error").html("Couldn't upload file");
//         }
//     });
//
// };
function imageUrl(input) {
    if(input.files && input.files[0]){
        let reader = new FileReader();

        reader.onload = function (e) {
            $("#profile-image").attr("src",e.target.result);
            image = reader.result;

        }
        reader.readAsDataURL(input.files[0]);

    }
}
$(document).ready(function () {
   console.log("hello");
    $("#color-dropdown").css("background", $("#color-dropdown").attr("class"));

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

    // $("#color-list #blue").click(function() {
    //   $("#color-dropdown").css("background", "#26547c");
    //   $("#current-color").html("Blue");
    // });
    //
    // $("#color-list #green").click(function() {
    //   $("#color-dropdown").css("background", "#04a777");
    //   $("#current-color").html("Green");
    // });
    //
    // $("#color-list #pink").click(function() {
    //   $("#color-dropdown").css("background", "#9c0f5f");
    //   $("#current-color").html("Pink");
    // });
    //
    // $("#color-list #yellow").click(function() {
    //   $("#color-dropdown").css("background", "#ffd166");
    //   $("#current-color").html("Yellow");
    // });

    $("#color-list li").click(function (e) {
        const colorPick = e.target;
        $("#color-dropdown").css("background", $(this).attr("value"));
        $("#color-id").attr("value",$(this).attr("id"));
        $("#current-color").html(colorPick.textContent);
        
    });
    $("#color-list li").hover(function (e) {
        const colorPick = e.target;
        $(this).css("background-color",e.type === "mouseenter"? $(this).attr("value") :"#FFF");
        $(this).css("color",e.type === "mouseenter"? "#FFF" : $(this).attr("value"));

    });
});
