$(document).ready(function() {
  $('.color1').animate({
    width: '70%',
  },600);
  $('.color2').delay(200).animate({
    width: '70%',
  },600);
  $('.color3').delay(300).animate({
    width: '70%',
  },600);
  $('.color4').delay(400).animate({
    width: '70%',
  },600);
})

// const postUser = (url,user)=>{
//     $.post(url,
//         user,
//         function (data) {
//             console.log(data);
//             if(url.includes("userlogin")){
//                 handleUserLogin(data);
//             }
//         });
//
// };
//
// function handleUserLogin(data) {
//     if(data){
//         console.log("ok");
//         // getBallads("/myBallads")
//         document.location.href="/";
//     }else{
//         const login = document.getElementById("login-section");
//         const label = document.createElement("label");
//         label.textContent = "Username or Password is incorrect.";
//         label.style.color = "red";
//         login.appendChild(label);
//     }
// }
//
// $(document).ready(function () {
//
//   $('.modal-overlay, .modal-container').hide();
//
//   $('#not-logged').click(function() {
//     console.log("hi");
//
//     $(".modal-overlay, .modal-container").show();
//   });
//
//   $(".fa-times").click(function() {
//     $(".modal-overlay, .modal-container").hide();
//   });
//
//   /**
//    * This section is for user login
//    */
//   $("#login-btn").click(function (e) {
//       e.preventDefault();
//       const user = {
//           username: $("#login-username").val(),
//           password: $("#login-password").val(),
//       };
//       postUser("/userlogin", user);
//
//
//   });
//
//   $("#signup-btn").click(function (e) {
//       e.preventDefault();
//       const user = {
//           id: -1,
//           firstName:$("#first-name").val(),
//           lastName:$("#last-name").val(),
//           email:$("#email-address").val(),
//           username:$("#signup-username").val(),
//           password:$("#signup-password").val(),
//           active: true,
//
//       }
//       postUser("/signUp",user);
//   });
//
//   $("#signup").click(function (e) {
//       e.preventDefault();
//       $("#signup-form").show();
//       $("#login-form").hide();
//   });
//
//   $("#login").click(function (e) {
//       e.preventDefault();
//       $("#signup-form").hide();
//       $("#login-form").show();
//   });
// });
