/**
 * Created by josh on 4/30/17.
 */
const input = document.getElementById("ballad-input");
const rhymeBank = document.getElementById("rhyme-bank");
const balladBank = document.getElementById("ballads");
let lineRhyme = false;
const rhymeScheme = [['A','B','A','B'],['A','B','B','A'],['A','A','B','B']];


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
                element.style.cursor = "pointer";
                element.style.display = "inline";
                // element.style.border = "1px solid";
                element.style.margin = "5px";

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

const isRhymingLine =() =>{
    lineRhyme = !lineRhyme;
    return lineRhyme;
}

$(document).ready(function () {

  $('.slideout-menu, .overlay').hide();

  $('#editor-page #slideout-button').click(function() {
    console.log("hi");

    $(".slideout-menu").animate({width:'toggle'},500);
    $(".slideout-menu, .overlay").show();
  });

  $(".fa-arrow-left").click(function() {
    $(".slideout-menu").animate({width:'toggle'},500);
    $(".overlay").hide();
  });





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
        if(e.which == 13){
            var cursorPosition = $("#ballad-input").prop("selectionStart");
            let index = input.value.substring(0,cursorPosition).split(" ");
            let word = index[index.length-1];
            console.log(word);
            if(word != ""&& !word.includes("[")&& isRhymingLine()){
                let trash = word.split("\n");
                console.log(trash);
               postForRhymes(trash[trash.length-1]);

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
