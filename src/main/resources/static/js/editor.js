/**
 * Created by josh on 4/30/17.
 */
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
    $('#text-to-render').on('input', function() {
      var text = $('#text-to-render').val();
      text = text.replace(/\n/gi, "<br />");
      $('#rendered-text').html(text);
    })

    $('#tools').click(function() {
      $('#tool-list').slideToggle();
    })
    /**
     * event listener for on click launches function to
     * get a word when double clicked or highlighted
     */
    $("#ballad-input").on("click", function (e) {
        // let con = $("#user-input").prop("")
        const txt = selectedText();
        if (txt != "" && !txt.includes(" ")){
           postForRhymes(txt);
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
            let index = input.value.substring(0,cursorPosition).split(" ");
            let word = index[index.length-1];
            if(word != ""&& !word.includes("[")){
               postForRhymes(word);

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
            Save: ()=>{
                const ballad = {
                    title: $("#title").val(),
                    content: $("#ballad-input").val(),
                    isPublic: $('#isPublic').prop('checked'),
                };

                if($("#title").val() != "") {
                    postBallads("/saveBallad", ballad);
                }else{
                    $("#title").css('border','1px solid red');
                }
            },
            Delete: ()=>{

            },
            Export: ()=>{
                const ballad = {
                    title: $("#title").val(),
                    content: $("#ballad-input").val(),
                    isPublic: $('#isPublic').prop('checked'),
                };
                postBallads("/saveBallad",ballad);

                document.location.href = "/download";
            },
            Notes:()=>{
                console.log(action);
            }
        };
        actions[action]();
    });



});