const postUser = (url,user)=>{
    $.post(url,
        user,
        function (data) {
            console.log(data);

                handleUserLogin(data);

        });

};

function handleUserLogin(data) {
    if(data[0]&&data[1]){
        console.log("ok");
        // getBallads("/myBallads")
        document.location.href="/";
    }else{

        const login = document.getElementById("login-section");
        const label = document.createElement("label");
        const passwordValidation = document.getElementById("password-validation");
        const userValidation = document.getElementById("user-validation");
        //valid.style.background = (data[0])? "" : "red";
        userValidation.textContent = (data[0])? "" : "Username or Email is Incorrect!";
        userValidation.style.color = "red";
        userValidation.style.fontSize = "0.90rem";
        passwordValidation.textContent = (data[1])? "" : "Password is Incorrect!";
        passwordValidation.style.color = "red";
        passwordValidation.style.fontSize = "0.90rem";


    }
}

$(document).ready(function () {
    /**
     * This section is for user login
     */
    $("#login-btn").click(function (e) {
        e.preventDefault();
        const user = {
            username: $("#login-username").val(),
            password: $("#login-password").val(),
        };
        postUser("/userlogin", user);


    });

    $("#signup-btn").click(function (e) {
        e.preventDefault();
        const user = {
            id: -1,
            firstName:$("#first-name").val(),
            lastName:$("#last-name").val(),
            email:$("#email-address").val(),
            username:$("#signup-username").val(),
            password:$("#signup-password").val(),
            active: true,

        }
        postUser("/signUp",user);
    });

    $("#signup").click(function (e) {
        e.preventDefault();
        $("#signup-form").show();
        $("#login-form").hide();
    });

    $("#login").click(function (e) {
        e.preventDefault();
        $("#signup-form").hide();
        $("#login-form").show();
    });

});
