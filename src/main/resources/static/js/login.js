const pageLocation = window.location.href;

const FIRST_NAME_VAL = document.getElementById("sign-up-fname");
const LAST_NAME_VAL = document.getElementById("sign-up-lname");
const EMAIL_VAL = document.getElementById("sign-up-email");
const USERNAME_VAL = document.getElementById("sign-up-username");
const PASSWORD_VAL = document.getElementById("sign-up-password");
const NEW_PASS_VAL = document.getElementById("recovery-password-error");
const PASSWORD_STRENGTH ={
    5:{
        color: "#F26430",
        text: "POOR",
        },
    10:{
        color: "#F29130",
        text: "WEAK",
        },
    15:{
        color: "#F2CE30",
        text: "GOOD",
        },
    20:{
        color: "#71F230",
        text: "STRONG",
    },
    25:{
        color: "#10F200",
        text: "VERY STRONG",
    }
};
const SIGN_UP =
    {
        fnameVal: false,
        lnameVal: false,
        emailVal: false,
        usernameVal: false,
        passwordVal: false,
    };
const emptyField = "Field must not be empty.";
const postUser = (url,user)=>{
    var isExist = {};
    $.post(url,
        user,
        function (data) {

            if(url == "/signUp"|| url == "/userlogin") {
                handleUserLogin(data);
            }else if(url=="/update-password"){
                handleRecovery(data);
            }else if("/forgot-email"){
                if(data){
                    alert("An email has been sent with recovery password.");
                    window.location.href="/account-recovery";
                }
            }else{

                if((/[\W@{1}.+]/g).exec(user.username) && (user.username.includes("com")||user.username.includes("net")||user.username.includes("me"))) {
                    EMAIL_VAL.textContent = (data["emailExist"]) ? "Email already used" : "";
                    SIGN_UP.emailVal = !data["emailExist"];
                }else{
                    USERNAME_VAL.textContent = (data["usernameExist"]) ? "Username is taken" : "";
                    SIGN_UP.usernameVal=!data["usernameExist"];
                }
            }


        });
    return isExist;

};
function handleRecovery(data) {
    if(data){
        console.log("ok");
        document.location.href="/";


    }else{

        const passwordValidation = document.getElementById("recovery-password-val");
        passwordValidation.textContent = (data)? "" : "Recovery key Incorrect!";
        passwordValidation.style.color = "red";
        passwordValidation.style.fontSize = "0.90rem";


    }
}
function handleUserLogin(data) {
    if(data[0]&&data[1]){
        $("#current-user").parent().removeAttr("id");
        $("#current-user").parent().attr("id","logged");

        // userMenu.id = (userMenu.id == "not-logged")? "logged":"not-logged";
        $(".modal-overlay, .modal-container").remove();
        $("#current-user").html(data[2]);
        $("#current-user-icon").attr("class","fa fa-angle-down");
        const proof = document.getElementById("logged");
        console.log(proof.tagName);
    }else{

        const login = document.getElementById("login-section");
        const label = document.createElement("label");
        const passwordValidation = document.getElementById("password-validation");
        const userValidation = document.getElementById("user-validation");

        userValidation.textContent = (data[0])? "" : "Username or Email is Incorrect!";
        userValidation.style.color = "red";
        userValidation.style.fontSize = "0.90rem";
        passwordValidation.textContent = (data[1])? "" : "Password is Incorrect!";
        passwordValidation.style.color = "red";
        passwordValidation.style.fontSize = "0.90rem";


    }
}

function signUpValidation(user) {

        FIRST_NAME_VAL.textContent = (user.firstName != '') ? "" : emptyField;
        LAST_NAME_VAL.textContent = (user.lastName != '') ? "" : emptyField;
        EMAIL_VAL.textContent = (user.email != '') ? "" : emptyField;
        USERNAME_VAL.textContent = (user.username != '') ? "" : emptyField;
        PASSWORD_VAL.textContent = (user.password != '') ? "" : emptyField;


}
$(document).ready(function () {
    if(FIRST_NAME_VAL != null) {
        FIRST_NAME_VAL.setAttribute("style", "white-space:pre;");
        FIRST_NAME_VAL.style.color = "red";
        LAST_NAME_VAL.setAttribute("style", "white-space:pre;");
        LAST_NAME_VAL.style.color = "red";
        EMAIL_VAL.setAttribute("style", "white-space:pre;");
        EMAIL_VAL.style.color = "red";
        USERNAME_VAL.setAttribute("style", "white-space:pre;");
        USERNAME_VAL.style.color = "red";
        PASSWORD_VAL.setAttribute("style", "white-space:pre;");
        PASSWORD_VAL.style.color = "red";
    }

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
    $("#forgot-email").click(function (e) {
        e.preventDefault();
        const user = {
            username: $("#login-username").val(),
        };

        postUser("/forgot-email", user);
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

        };
        signUpValidation(user);
        if(SIGN_UP["fnameVal"] && SIGN_UP["lnameVal"] && SIGN_UP["emailVal"] && SIGN_UP["usernameVal"] && SIGN_UP["passwordVal"]) {
             postUser("/signUp",user);
        }else{
            EMAIL_VAL.textContent = (SIGN_UP["emailVal"])? "":"Please Try and Recover Account";
            USERNAME_VAL.textContent = (SIGN_UP["usernameVal"]) ? "" : "Please pick different username";
        }
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
    $("#first-name").on("keyup", function () {
        FIRST_NAME_VAL.textContent = ($(this).val().length >= 3) ? "" : "Must be real name.";

        if($(this).val().length >= 3){
            SIGN_UP["fnameVal"]=true;
        }else{
            SIGN_UP["fnameVal"]=false;
        }
    });
    $("#last-name").on("keyup", function () {
        LAST_NAME_VAL.textContent = ($(this).val().length >= 3) ? "" : "Must be real name.";
        if($(this).val().length >= 3){
            SIGN_UP["lnameVal"]=true;
        }else{
            SIGN_UP["lnameVal"]=false;
        }
    });

    $("#email-address").on("keyup", function () {

        let val="";
            EMAIL_VAL.setAttribute("style","white-space:pre;");
            EMAIL_VAL.style.color = "red";
            val += ($(this).val().length > 6 && (/[\W@{1}.+]/g).exec($(this).val()) && ($(this).val().includes("com")||$(this).val().includes("net")||$(this).val().includes("me"))) ? "" : "This is not a valid Email. \n";
            EMAIL_VAL.textContent =val;

            if($(this).val().length > 6 && (/[\W@{1}.+]/g).exec($(this).val()) && ($(this).val().includes("com")||$(this).val().includes("net")||$(this).val().includes("me"))){
                postUser("/check-username",{username:$(this).val()});
                SIGN_UP["emailVal"]=true;
            }else{
                SIGN_UP["emailVal"]=false;
            }

    });

    $("#signup-username").on("keyup", function () {
            let usr = $(this).val();
            let count = 4 - usr.length;
            let val ="";
            USERNAME_VAL.setAttribute("style","white-space:pre;");
            USERNAME_VAL.style.color = "red";
            val += (usr.length >= 4 )? "": "Username needs at least "+count+" more characters! \r\n";
            val += ((/[a-z+]/g).exec(usr)) ? "" : "Needs at least 1 Letter! \r\n";
            val += ((/[0-9+]/g).exec(usr)) ? "" : "Needs at least 1 Number! \r\n";
            val += ((/\s/g).exec(usr))? "Password can not contain spaces \r\n":"";


        if(usr.length >= 4 && new RegExp((/[a-z]/g)).test(usr)&&new RegExp((/[0-9]/g)).test(usr)){
            var response = postUser("/check-username",{username:usr});

            // if(){
            //     val = "Username already exists.";
            // }else{
                SIGN_UP["usernameVal"]=true;
            // }

        }else{
            SIGN_UP["usernameVal"]=false;
        }
        USERNAME_VAL.textContent =val;
    });

    $("#signup-password").on("keyup", function () {
        const password = $(this).val();
        const strength = document.getElementById("password-strength");
        let val="";
        if($(this).val()==''){
            PASSWORD_VAL.textContent = "";
        }else {
            let count = 6 - password.length;
            val += ($(this).val().length >= 6 )? "": "Password needs at least "+count+" more characters!  \r\n";
            val += ((/[a-z+]/g).exec(password)) ? "" : "Needs at least 1 Letter! \r\n";
            val += ((/[0-9+]/g).exec(password)) ? "" : "Needs at least 1 Number! \r\n";
            val += ((/\s/g).exec(password))? "Password can not contain spaces \r\n":"";
            PASSWORD_VAL.style.width = "75%";

            let color = 0;
            color += (password.length >= 6) ? 5 : 2;
            if(password.length >= 6 && new RegExp((/[a-z]/g)).test(password)&&new RegExp((/[0-9]/g)).test(password)) {
                strength.style.display = "block";
                if (new RegExp((/[a-z]/g)).test(password)) {

                    color += ( password.match((/[a-z]/g)).length >= 3 ) ? 5 : 3;
                }
                if (new RegExp((/[A-Z]/g)).test(password)) {

                    color += (password.match((/[A-Z]/g)).length >= 1 ) ? 5 : 0;
                }
                if (new RegExp((/[0-9]/g)).test(password)) {

                    color += (password.match((/[0-9]/g)).length >= 3 ) ? 5 : 3;
                }
                if (new RegExp((/[@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/g)).test(password)) {
                    color += (password.match((/[@#$%^&*()<>\/?]/g)).length >= 1 ) ? 5 : 3;
                }
                if (color <= 5 && color < 10) {
                    strength.style.background = PASSWORD_STRENGTH[5]["color"];
                    strength.textContent = PASSWORD_STRENGTH[5]["text"];
                } else if (color >= 10 && color < 15) {
                    strength.style.background = PASSWORD_STRENGTH[10]["color"];
                    strength.textContent = PASSWORD_STRENGTH[10]["text"];
                } else if (color >= 15 && color < 20) {
                    strength.style.background = PASSWORD_STRENGTH[15]["color"];
                    strength.textContent = PASSWORD_STRENGTH[15]["text"];
                } else if (color >= 20 && color < 25) {
                    strength.style.background = PASSWORD_STRENGTH[20]["color"];
                    strength.textContent = PASSWORD_STRENGTH[20]["text"];
                } else if (color >= 25) {
                    strength.style.background = PASSWORD_STRENGTH[25]["color"];
                    strength.textContent = PASSWORD_STRENGTH[25]["text"];
                }
                SIGN_UP["passwordVal"]=true;
            }else{
                strength.style.display = "none";
            }
            PASSWORD_VAL.textContent =val;
        }
    });
    $("#recover-btn").click(function (e) {
        e.preventDefault();
        const user = {
            username:$("#username-email").val(),
            recoveryKey:$("#recovery-password").val(),
            newPassword:$("#new-password").val(),
        }
        postUser("/update-password",user);
        
    });

    $("#new-password").on("keyup", function () {

        const password = $(this).val();
        const strength = document.getElementById("password-strength");
        let val="";
        if($(this).val()==''){
            NEW_PASS_VAL.textContent = "";
        }else {
            let count = 6 - password.length;
            val += ($(this).val().length >= 6 )? "": "Password needs at least "+count+" more characters!  \r\n";
            val += ((/[a-z+]/g).exec(password)) ? "" : "Needs at least 1 Letter! \r\n";
            val += ((/[0-9+]/g).exec(password)) ? "" : "Needs at least 1 Number! \r\n";
            val += ((/\s/g).exec(password))? "Password can not contain spaces \r\n":"";
            NEW_PASS_VAL.style.width = "75%";
            // console.log(password.match((/[A-Z]/g)).length);
            let color = 0;
            color += (password.length >= 6) ? 5 : 2;
            if(password.length >= 6 && new RegExp((/[a-z]/g)).test(password)&&new RegExp((/[0-9]/g)).test(password)) {
                strength.style.display = "block";
                if (new RegExp((/[a-z]/g)).test(password)) {
                    console.log("Checking Lower");
                    color += ( password.match((/[a-z]/g)).length >= 3 ) ? 5 : 3;
                }
                if (new RegExp((/[A-Z]/g)).test(password)) {
                    console.log("Checking Upper");
                    color += (password.match((/[A-Z]/g)).length >= 1 ) ? 5 : 0;
                }
                if (new RegExp((/[0-9]/g)).test(password)) {
                    console.log("Checking Number");
                    color += (password.match((/[0-9]/g)).length >= 3 ) ? 5 : 3;
                }
                if (new RegExp((/[@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/g)).test(password)) {
                    color += (password.match((/[@#$%^&*()<>\/?]/g)).length >= 1 ) ? 5 : 3;
                }
                if (color <= 5 && color < 10) {
                    strength.style.background = PASSWORD_STRENGTH[5]["color"];
                    strength.textContent = PASSWORD_STRENGTH[5]["text"];
                } else if (color >= 10 && color < 15) {
                    strength.style.background = PASSWORD_STRENGTH[10]["color"];
                    strength.textContent = PASSWORD_STRENGTH[10]["text"];
                } else if (color >= 15 && color < 20) {
                    strength.style.background = PASSWORD_STRENGTH[15]["color"];
                    strength.textContent = PASSWORD_STRENGTH[15]["text"];
                } else if (color >= 20 && color < 25) {
                    strength.style.background = PASSWORD_STRENGTH[20]["color"];
                    strength.textContent = PASSWORD_STRENGTH[20]["text"];
                } else if (color >= 25) {
                    strength.style.background = PASSWORD_STRENGTH[25]["color"];
                    strength.textContent = PASSWORD_STRENGTH[25]["text"];
                }

            }else{
                strength.style.display = "none";
            }
            NEW_PASS_VAL.textContent =val;
        }
    });
});
