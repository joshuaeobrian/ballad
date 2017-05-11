/**
 * Created by josh on 4/30/17.
 */

var lines = [];
var built = '';
var rhmyelist = [];
var rhymeboxes = '';
var scheme = ['A','B','A','B'];
var schemes = [['A','B','A','B'],['A','A','B','B'],['A','B','B','A'],['A','A','B','A']];
var c = 0;
var cc = 0;
var lex = new RiLexicon();
$(document).ready(function() {

 $('#mytext').on('input', function() {
   process();
 });
 $(document.body).on('click', '.rhymetype', function() {
   // console.log("clicked: "+parseInt($(this).attr('id')[4]));
   // console.log(lines[7]);
   // lines[1].scheme = 'B';
   var current = lines[parseInt($(this).attr('id')[4])].scheme;
   var next= '';

   if(current == 'A') {
     next = 'B';
   } else if(current== 'B') {
     next = 'C';
   } else if(current == 'C') {
     next = 'D';
   } else if(current == 'D') {
     next = 'A';
   }
   lines[parseInt($(this).attr('id')[4])].scheme = next;
   process();
   getRhymes();
 })
 // $('#something').click(function() {
 //   console.log(lines);
 // })
 $('#mytext').keyup(function(e) {
   if(e.keyCode == 13) {
     getRhymes();
     console.log($(this).prop('selectionStart'));
   }
 })
 $('#default-scheme').click(function() {
   cc++;
   $(this).html(schemes[cc%schemes.length]);
   scheme = schemes[cc%schemes.length];
  //  c = 0;
 })
 $('#chorus').click(function() {
   c = 0;
   for(var i= 0; i < 4; i++) {
     lines.push({
       scheme: scheme[c%4],
       text: ' ',
       sectioned: true,
       section: 'chorus',
     })
     c++;
   }
   process();
 })
 $('#verse').click(function() {
   c = 0;
   for(var i= 0; i < 4; i++) {
     lines.push({
       scheme: scheme[c%4],
       text: ' ',
       sectioned: true,
       section: 'verse',
     })
     c++;
   }
   process();
 })
 $('#bridge').click(function() {
   c = 0;
   for(var i= 0; i < 4; i++) {
     lines.push({
       scheme: scheme[c%4],
       text: ' ',
       sectioned: true,
       section: 'bridge',
     })
     c++;
   }
   process();
 })
 $('#section').click(function() {
   c = 0;
   for(var i= 0; i < 4; i++) {
     lines.push({
       scheme: scheme[c%4],
       text: ' ',
       sectioned: true,
       section: 'section',
     })
     c++;
   }
   process();
 })
})
function process() {
 var text = $('#mytext').val();
 text = text.replace(/\n/gi, "<br />");
 var splitlines = text.split('<br />');
 for(var i = 0; i < splitlines.length; i++) {
   if(lines[i] == undefined) {
     lines[i] = {
       scheme: scheme[c%4],
       text: splitlines[i],
       sectioned: false
     }
     c++;
   }
   if(lines[i] != undefined) {
     lines[i].text = splitlines[i];
   }
   if(lines[i].text == '' && i < lines.length-1 && lines[i].sectioned == false) {
    //  lines.splice(i,1);
    //  console.log($('#mytext').selectionStart);
   }
 }
 built = '';
 for(var i = 0; i < lines.length; i++) {
   if( lines[i].sectioned == false) {
     built += '<div class="rhymetype '+lines[i].scheme+'" id="line'+i+'">'+lines[i].scheme+'</div>';
   } else if(lines[i].sectioned == true) {
     built += '<div class="rhymetype '+lines[i].scheme+' sectioned'+lines[i].section+'" id="line'+i+'">'+lines[i].scheme+'</div>';
   }

 }
 $('#rendered-text').html(text);
 $('.scheme').html(built);

}
function getRhymes() {
 // var currentline = lines[lines.length-1].scheme;
 var textpiece = $('#mytext').val().substring(0,$('#mytext').prop('selectionStart'));
 var linenum = (textpiece.match(/\n/g) || []).length;
 var currentline = lines[linenum].scheme;
 console.log(textpiece.match(/\n/g) || []);
 var linetorhyme = 0;
 for(var i = 0; i < linenum-1; i++) {
   if(lines[i].scheme == currentline) {
     linetorhyme = i;
   }
 }
 var words = lines[linetorhyme].text.split(" ");
 rhymelist = lex.rhymes(words[words.length-1]);
 rhymeboxes = '';
 for(var i = 0; i < rhymelist.length; i++) {
   rhymeboxes += '<div class="rhyme">'+rhymelist[i]+'</div>';
 }
 $('.rhymes').html(rhymeboxes);
}




// const input = document.getElementById("ballad-input");
// const rhymeBank = document.getElementById("rhyme-bank");
// const balladBank = document.getElementById("ballads");
// let lineRhyme = false;
// const rhymeScheme = [['A','B','A','B'],['A','B','B','A'],['A','A','B','B']];
//
//
// const postForRhymes = (word)=>{
//     let words = [];
//     $.post("/api/rhymingWord",
//         {
//             "word": word,
//         },function (data) {
//             const wordsToTrim = jQuery.parseJSON(data);
//             const bankedWords = rhymeBank.querySelectorAll("h5");
//             for(let i = 0; i < bankedWords.length; i++){
//                 rhymeBank.removeChild(bankedWords[i]);
//             }
//             for(let i = 0; i < wordsToTrim.length; i++){
//                 let element = document.createElement("h5");
//                 element.textContent = wordsToTrim[i]["word"];
//                 element.style.cursor = "pointer";
//                 element.style.display = "inline";
//                 // element.style.border = "1px solid";
//                 element.style.margin = "5px";
//
//                 rhymeBank.appendChild(element);
//
//             }
//             return words;
//         }
//     );
// };
//
const postBallads = (url,ballad)=>{
    $.post(url,
        ballad,
        function (response) {

        }
    );
};
//
// const selectedText = ()=>{
//     let txt = "";
//     const element = document.activeElement;
//     const tag = element.tagName;
//     if(tag == "TEXTAREA"){
//         txt = element.value.slice(element.selectionStart,element.selectionEnd);
//         return txt;
//     }
// };
//
// const isRhymingLine =() =>{
//     lineRhyme = !lineRhyme;
//     return lineRhyme;
// }

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
    // $("#ballad-input").on("click", function (e) {
    //     // let con = $("#user-input").prop("")
    //     const txt = selectedText();
    //     if (txt != "" && !txt.includes(" ")){
    //        postForRhymes(txt);
    //     }
    // });

    // /**
    //  * event listener on keydown if key is space
    //  * then is runs the post function to put rhymes
    //  * in the dom
    //  */
    // $("#ballad-input").on('keydown', function (e) {
    //
    //     //This is controlled for when you press space
    //     if(e.which == 13){
    //         var cursorPosition = $("#ballad-input").prop("selectionStart");
    //         let index = input.value.substring(0,cursorPosition).split(" ");
    //         let word = index[index.length-1];
    //         console.log(word);
    //         if(word != ""&& !word.includes("[")&& isRhymingLine()){
    //             let trash = word.split("\n");
    //             console.log(trash);
    //            postForRhymes(trash[trash.length-1]);
    //
    //         }
    //     }
    // });

    $("button").click(function (e) {
        let button = e.target;
        let action = button.value;
        if(action.includes("Note")){
            action = "Notes";
        }
        if(action == 'Notes'||action == 'Save'||action == 'Export') {
            console.log(action);
            const actions = {
                Save: () => {
                    const ballad = {
                        title: $("#title").val(),
                        content: $("#mytext").val(),
                        isPublic: $('#isPublic').prop('checked'),
                    };



                    if ($("#title").val() != "") {
                        postBallads("/saveBallad", ballad);
                    } else {
                        $("#title").css('border', '1px solid red');
                    }
                },

                Export: () => {
                    const ballad = {
                        title: $("#title").val(),
                        content: $("#mytext").val(),
                        isPublic: $('#isPublic').prop('checked'),
                    };
                    postBallads("/saveBallad", ballad);

                    document.location.href = "/download";
                },
                Notes: () => {
                    console.log(action);
                }
            };
            actions[action]();
        }
    });



});
