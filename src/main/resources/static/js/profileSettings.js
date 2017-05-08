/**
 * Created by josh on 5/8/17.
 */
const postUser = (url,user)=>{
    $.post(url,
        user,
        function (data) {
            console.log(data);
            if(url.includes("userlogin")){
                handleUserLogin(data);
            }
        });

};
function imageUrl(input) {
    if(input.files && input.files[0]){
        let reader = new FileReader();

        reader.onload = function (e) {
            $("#profile-image").attr("src",e.target.result);

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

    $('button').click(function (e) {
        const button = e.target.textContent;
        console.log(button);

        const actions ={
            Save: ()=>{
                let user = {
                    firstName: "",
                    lastName: "",
                    email: "",
                    username:'',
                    password: '/',
                    active:'',
                    photo:'',
                    about:'',
                    colorCode:'',
                }
                postUser('/updateUser',user);

            },
            Delete: () =>{
                $.get("/");

            }
        }
        
    });
});

