
let image;
const updateUser = (url,user)=>{
    $.ajax({
        type: "POST",
        url: url,
        async: true,
        data: user,
        contentType: "multipart/form-data",
        processData: false,
        success: function(msg) {
            alert("File has been uploaded successfully");
        },
        error:function(msg) {
            $("#upload-error").html("Couldn't upload file");
        }
    });

};
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
$(function () {
   console.log("hello");

    $(".field").click(function (e) {
        $("input",this).prop("disabled",false);

    });

    $("#update-image").change(function () {
        imageUrl(this);
    });

    // $('button').click(function (e) {
    //     const button = e.target.textContent;
    //
    //
    //     const actions ={
    //         Save: ()=>{
    //
    //             let user = {
    //                 firstName: $("#firstName").val(),
    //                 lastName: $("#lastName").val(),
    //                 email: $("#email").val(),
    //                 username: $("#username").val(),
    //                 password: $("#password").val(),
    //                 active: true,
    //                 file: $("#update-image"),
    //                 about:$("#about").text(),
    //                 colorCode:'',
    //             }
    //             console.log(user);
    //             // updateUser('/updateUser',user);
    //
    //         },
    //         Delete: () =>{
    //             $.get("/");
    //
    //         }
    //     }
    //     actions[button]();
    //
    // });
});

