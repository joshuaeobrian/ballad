/**
 * Created by josh on 4/30/17.
 */
console.log("hello");
const input = document.getElementById("ballad-input");
const rhymeBank = document.getElementById("rhyme-bank");
const balladBank = document.getElementById("ballads");


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
        function (response) {
            console.log(response);
        }
    );
};

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
     * event listener for on click launches function to
     * get a word when double clicked or highlighted
     */
    $("#ballad-input").on("click", function (e) {
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
    $("#ballad-input").on('keydown', function (e) {

        //This is controlled for when you press space
        if(e.which == 32){
            var cursorPosition = $("#ballad-input").prop("selectionStart");
            console.log(cursorPosition);
            let index = input.value.substring(0,cursorPosition).split(" ");
            let word = index[index.length-1];
            if(word != ""&& !word.includes("[")){
                console.log(word);

                console.log(postForRhymes(word));

            }


        }
    });
    $("button").click(function (e) {
        let button = e.target;
        let action = button.value;
        if(action.includes("Note")){
            action = "Notes";
        }

        const actions = {
            Intro: ()=>{
                // $("#ballad-input").append("["+action+"]\n");
                console.log(input.value);
                input.value = (input.value+"["+action+"]\n");
            },
            Verse: ()=>{
                input.value = (input.value+"\n\n["+action+"]\n");
                console.log(action);
            },
            Chorus: ()=>{
                input.value = (input.value+"\n\n["+action+"]\n");
                console.log(action);
            },
            Bridge: ()=>{
                input.value = (input.value+"\n\n["+action+"]\n");
                console.log(action);
            },
            Section: ()=>{
                input.value = (input.value+"\n\n["+action+"]\n");
            },
            Outro: ()=>{
                input.value = (input.value+"\n\n["+action+"]\n");
                console.log(action);
            },
            Save: ()=>{
                const ballad = {
                    title: $("#title").val(),
                    content: $("#ballad-input").val(),
                };
                //button.textContent = "Update";
                button.value = "Update";
                postBallads("/saveNewBallad",ballad);

            },
            Update: ()=>{
                const ballad = {
                    title: $("#title").val(),
                    content: $("#ballad-input").val(),
                };
                postBallads("/updateBallad",ballad);
            },
            Delete: ()=>{

            },
            Unknown: ()=>{
                document.location.href = "/login";
            },
            Export: ()=>{
                const ballad = {
                    title: $("#title").val(),
                    content: $("#ballad-input").val(),
                };
                postBallads("/saveNewBallad",ballad);

                document.location.href = "/download";
            },
            Notes:()=>{
                console.log(action);
            }
        };
        actions[action]();
    });


});