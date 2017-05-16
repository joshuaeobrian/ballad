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
var align = 1;
var lex = new RiLexicon();
$(document).ready(function() {
  checkAlign();
  $('.font-choice').each(function() {
    $(this).css('font-family',$(this).html());
  })
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
     $("#btn2").css("background","#9c0f5f");
     $("#btn2").prop('disabled', false);

   if(e.keyCode == 13) {
     getRhymes();
    //  console.log($(this).prop('selectionStart'));
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
     });
     c++;
   }
   process();
 });
 $('#verse').click(function() {
   c = 0;
   for(var i= 0; i < 4; i++) {
     lines.push({
       scheme: scheme[c%4],
       text: ' ',
       sectioned: true,
       section: 'verse',
     });
     c++;
   }
   process();
 });
 $('#bridge').click(function() {
   c = 0;
   for(var i= 0; i < 4; i++) {
     lines.push({
       scheme: scheme[c%4],
       text: ' ',
       sectioned: true,
       section: 'bridge',
     });
     c++;
   }
   process();
 });
 $('#section').click(function() {
   c = 0;
   for(var i= 0; i < 4; i++) {
     lines.push({
       scheme: scheme[c%4],
       text: ' ',
       sectioned: true,
       section: 'section',
     });
     c++;
   }
   process();
 });
 $('#wordtorhyme').keydown(function(e) {
   if(e.keyCode == 13) {
     var rhymelist = lex.rhymes($('#wordtorhyme').val());
     rhymeboxes = '';
     for(var i = 0; i < rhymelist.length; i++) {
       rhymeboxes += '<div class="rhyme">'+rhymelist[i]+'</div>';
     }
     $('.rhymes').html(rhymeboxes);
    //  if($('#mytext').selectionStart == $('#mytext').val().length) {
    //    console.log('Yup');
    //    lines[lines.length] = lines[lines.length-1];
    //  }
   }
 });
 $('.fa-align-center').click(function() {
   $('#mytext').css('text-align','center');
   align = 2;
   checkAlign();
 });
 $('.fa-align-left').click(function() {
   $('#mytext').css('text-align','left');
   align = 1;
   checkAlign();
 });
 $('.fa-align-right').click(function() {
   $('#mytext').css('text-align','right');
   align = 3;
   checkAlign();
 });
 $('#current-font').click(function() {
   $('#fonts').toggle();
 });
 $('.font-choice').click(function() {
   $('#mytext').css('font-family',$(this).html());
   $('#current-font').html($(this).html()+' <i class="fa fa-angle-down" aria-hidden="true"></i>');
   $('#fonts').toggle();
 });
});
function checkAlign() {
  if(align == 1) {
    $('#l-align').css('background','white').css('color','#2a2a2a');
    $('#c-align').css('background','#2a2a2a').css('color','white');
    $('#r-align').css('background','#2a2a2a').css('color','white');
  } else if(align == 2) {
    $('#c-align').css('background','white').css('color','#2a2a2a');
    $('#l-align').css('background','#2a2a2a').css('color','white');
    $('#r-align').css('background','#2a2a2a').css('color','white');
  } else if(align == 3) {
    $('#r-align').css('background','white').css('color','#2a2a2a');
    $('#c-align').css('background','#2a2a2a').css('color','white');
    $('#l-align').css('background','#2a2a2a').css('color','white');
  }
}
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
     };
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
 // console.log(textpiece.match(/\n/g) || []);
 var linetorhyme = 0;
 for(var i = 0; i < linenum-1; i++) {
   if(lines[i].scheme == currentline) {
     linetorhyme = i;
   }
 }
 var words = lines[linetorhyme].text.split(" ");
 $('#wordtorhyme').val(words[words.length-1]);
 rhymelist = lex.rhymes($('#wordtorhyme').val());
 rhymeboxes = '';
 for(var i = 0; i < Math.min(rhymelist.length,50); i++) {
   rhymeboxes += '<div class="rhyme">'+rhymelist[i]+'</div>';
 }
 $('.rhymes').html(rhymeboxes);
}

const postBallads = (url,ballad,exportNow)=>{
    $.post(url,
        ballad,
        function (response) {
                if(exportNow && response == 'Done'){

                    document.location.href = "/download";

                }
        }
    );
};

$(document).ready(function () {

    $("#btn2").prop('disabled', true);
    $("#btn1").css("background","#9c0f5f");

  $('#editor-page #editor-slideout-button').click(function() {


    $("#editor-slideout-menu").animate({width:'toggle'},500);
    $("#editor-slideout-menu, #editor-slideout-overlay").show();
  });

  $(".fa-arrow-left").click(function() {
    $("#editor-slideout-menu").animate({width:'toggle'},500);
    $("#editor-slideout-overlay").hide();
  });


    $("#user").click(function () {
        const user = $("#user-id").val();
        // console.log(user);
        document.location.href = "/user-profile?userId="+user;
    });


    $('#text-to-render').on('input', function() {
      var text = $('#text-to-render').val();
      text = text.replace(/\n/gi, "<br />");
      $('#rendered-text').html(text);
    });

    $('#tools').click(function() {
      $('#tool-list').slideToggle();
    });


    $("button").click(function (e) {
        let button = e.target;
        let action = button.value;

        if(action == 'Like'||action == 'Save'||action == 'Export') {
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
                        // button.style.background = "#04a777";
                        // button.disabled = true;
                        $("#btn2").css("background","#04a777");
                        $("#btn2").prop('disabled', true);
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
                    postBallads("/saveBallad", ballad,true);


                },
                Like: () => {
                    postBallads("/like-ballad");
                    button.disabled = true;
                    button.style.background= "#F26430";
                    button.textContent ="Liked";
                },
            };
            actions[action]();
        }
    });



});
