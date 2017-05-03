/**
 * Created by josh on 4/27/17.
 */


const input = document.getElementById("user-input");
const rhymeBank = document.getElementById("rhyme-bank");
const balladBank = document.getElementById("ballads");
/**
 * Controls user api
 * @param url
 * @param user
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
function handleUserLogin(data) {
    if(data){
        console.log("ok");
        getBallads("/myBallads")
    }else{
        const login = document.getElementById("logon");
        const label = document.createElement("label");
        label.textContent = "Username or Password is incorrect.";
        label.style.color = "red";
        login.appendChild(label);
    }
}
/**
 * Controls the posts to the webservice then
 * takes the response and write it to the dom
 * @param word
 */
const postForRhymes = (word)=>{
    let words = [];
    $.post("/api/rhymingWord",
        {
            "word": word,
        },function (data) {
            const wordsToTrim = jQuery.parseJSON(data);
            console.log("BREAK:");
            console.log(wordsToTrim);
            const bankedWords = rhymeBank.querySelectorAll("h5");
            for(let i = 0; i < bankedWords.length; i++){
                rhymeBank.removeChild(bankedWords[i]);
            }

            for(let i = 0; i < wordsToTrim.length; i++){
                let element = document.createElement("h5");
                element.textContent = wordsToTrim[i]["word"];
                rhymeBank.appendChild(element);

                }

                return words;
            }

        );
};

const postBallads = (url,ballad)=>{
    $.post(url,
        ballad,
        function () {
            
        }
    );
};

const getBallads = (url) =>{

  $.post(url,
        function (data) {
            console.log(data);
            // ballads = data;
            for(let i = 0; i < data.length;i++){
                const div = document.createElement("div");
                div.style.border = "1px solid";
                div.style.margin = "5px";
                const h4 = document.createElement("h4");
                h4.textContent = data[i]['title'];
                div.appendChild(h4);
                balladBank.appendChild(div);


            }
        }
    );


};


/**
 * gets a highlighted section of the text area and returns it
 * @returns {string}
 */
const selectedText = ()=>{
    let txt = "";
    const element = document.activeElement;
    const tag = element.tagName;
    if(tag == "TEXTAREA"){
        txt = element.value.slice(element.selectionStart,element.selectionEnd);
        return txt;
    }
};


$(document).ready(function () {
    /**
     * This section is for user login
     */
    $("#login").click(function (e) {
        e.preventDefault();
        const user = {
            username: $("#username").val(),
            password: $("#password").val(),
        };
      postUser("/userlogin",user);


    });
    /**
     * handles user creation
     * need to add some validation
     */
    $("#signup-btn").click(function (e) {
        e.preventDefault();
        const user = {
            id: -1,
            firstName:$("#firstName").val(),
            lastName:$("#lastName").val(),
            email:$("#email").val(),
            username:$("#user").val(),
            password:$("#pswd").val(),
            active: true,

        }
        postUser("/signUp",user);
    });


    /**
     * event listener for on click launches function to
     * get a word when double clicked or highlighted
     */
    $("#user-input").on("click", function (e) {
        // let con = $("#user-input").prop("")
        const txt = selectedText();
        if (txt != "" && !txt.includes(" ")){
            console.log(postForRhymes(txt));
        }

    });

    /**
     * event listener on keydown if key is space
     * then is runs the post function to put rhymes
     * in the dom
     */
    $("#user-input").on('keydown', function (e) {

        //This is controlled for when you press space
        if(e.which == 32){
            var cursorPosition = $("#user-input").prop("selectionStart");
            console.log(cursorPosition);
            let index = input.value.substring(0,cursorPosition).split(" ");
            let word = index[index.length-1];
            if(word != ""){
                console.log(word);

                console.log(postForRhymes(word));

            }


        }
    });
    //BALLAD SECTION
    /**
     * Saves New Ballad
     */
    $("#save").click(function (e) {
        const ballad = {
            id:-1,
            title: $("#title").val(),
            ballad: $("#user-input").val(),
            userId:-1,
            creationDate: null,
            lastEdit:null,
            favorite: false,
            publicView: true
        };

        postBallads("/saveNewBallad",ballad)
    });
    //TODO
    $("#edit").click(function () {
        
    });
    //TODO
    $("#delete").click(function () {

    });








});