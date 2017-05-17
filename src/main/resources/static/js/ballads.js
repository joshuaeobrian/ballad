/**
 * Global properties
 */
 var grid = false;

$('.container').click(function() {
  if(grid) {
    tolist();
    grid = !grid;
  } else {
    togrid();
    grid = !grid;
  }

});

togrid = function() {
  $('#a').animate({
    top: '0%',
    height: '30%',
  },200).animate({
    width: '63%',
  },200);
  $('#b').animate({
    top: '33%',
    height: '30%',
  },200).animate({
    left: '0%',
    width: '63%'
  },200);
  $('#c').animate({
    top: '0%',
    height: '30%',
  },200).animate({

  },200);
  $('#d').animate({
    top: '66%',
    height: '30%',
  },200).animate({
    width: '63%',
  },200)
  $('#e').animate({
    top: '66%',
    height: '30%',
  },200).animate({
    left: '66%',
  },200)
  $('#f').animate({
    top: '33%',
    height: '30%',
  },200);
};

tolist = function() {
  $('#a').animate({
    top: '0%',
    height: '30%',
    width: '30%'
  },200).animate({
    height: '55%',
  },200);
  $('#b').animate({
    left: '33%',
    width: '30%',
  },200).animate({
    top: '0%',
    height: '25%'
  },200);
  $('#c').animate({
    top: '0%',
    height: '30%',
  },200).animate({
    top: '0%',
    height: '75%'
  },200);
  $('#d').animate({
    top: '66%',
    height: '30%',
    width: '30%'
  },200).animate({
    top: '60%',
    height: '40%',
    width: '30%',
  },200)
  $('#e').animate({
    top: '66%',
    height: '30%',
    left: '33%'
  },200).animate({
    top: '30%',
    height: '70%',
    left: '33%',
  },200)
  $('#f').animate({
    left: '66%',
  },200).animate({
    top: '80%',
    height: '20%'
  },200);
};
const pageLocation = window.location.href;
const sortIndex = {
    Title:1,
    Recent:4,
    Public:10,
    Private:9,
    Likes:6,

};
const toggleViewVals={
    true: "ballad-cards-container-grid",
    false: "ballad-cards-container-list",
};
let isGrid = true;
const cardView = document.getElementById("ballad-cards-section").firstElementChild;
let isUser = false;

let currentSortID=3;

const createBallad = (ballad)=>{

    var bCard = document.createElement("div");
    bCard.classList.add( "ballad-card");

    var btop = document.createElement("div");
    btop.className = "btop";

    var listDiv = document.createElement("div");
    listDiv.className = "content-container";

    var hiddenId = document.createElement("input");
    hiddenId.type = "hidden";
    hiddenId.value = ballad["id"];
    var title = document.createElement("h1");
    title.textContent = (ballad["title"].length > 18 && !isGrid)? ballad["title"].substring(0,ballad["title"].substring(0,18).lastIndexOf(' '))+'...' : ballad["title"] ;
    var author = document.createElement("h2");
    author.textContent = ballad["owner"]["firstName"]+" "+ballad["owner"]["lastName"];
    var content = document.createElement("p");
    if(isGrid){
        content.textContent = (ballad["ballad"].length < 150)? ballad["ballad"] : ballad["ballad"].substring(0,ballad["ballad"].substring(0, 150).lastIndexOf(' '))+"...";
    }else{
        content.textContent = (ballad["ballad"].length < 100)? ballad["ballad"] : ballad["ballad"].substring(0,ballad["ballad"].substring(0, 100).lastIndexOf(' '))+"...";
    }

    var date = document.createElement("div");
    date.className = "date";
    var span = document.createElement("span");
    span.textContent = ballad["creationDate"]["month"]+" "+ballad["creationDate"]["dayOfMonth"]+", "+ballad["creationDate"]["year"];

    date.appendChild(span);

    var typeDiv = document.createElement("div");
    typeDiv.className = (ballad["publicView"])? "public" : "private";

    var typeText = document.createElement("span");
    typeText.textContent =  (ballad["publicView"])? "Public" : "Private";
    // typeText.className = (ballad["publicView"])? "public" : "private";
    typeDiv.appendChild(typeText);

    var bbottom = document.createElement("div");
    bbottom.className ="bbottom";

    bCard.appendChild(btop);

    listDiv.appendChild(hiddenId);
    if(pageLocation.includes("my-ballads")) {
        var pencil = document.createElement("i");
        pencil.className = "fa fa-pencil editBallad";
        pencil.setAttribute("aria-hidden","true");
        var bdelete = document.createElement("i");
        bdelete.className = "fa fa-minus-circle deleteBallad";
        bdelete.setAttribute("aria-hidden","true");
        listDiv.appendChild(pencil);
        listDiv.appendChild(bdelete);
    }else{
        var popView = document.createElement("i");
        popView.className = "fa fa-eye viewBallad";
        popView.setAttribute("aria-hidden","true");
        listDiv.appendChild(popView);
    }
    listDiv.appendChild(title);
    listDiv.appendChild(author);
    listDiv.appendChild(content);
    listDiv.appendChild(date);
    listDiv.appendChild(typeDiv);

    bCard.appendChild(listDiv);

    bCard.appendChild(bbottom);
    bCard.style.cursor = "pointer";

    return bCard;
};
function clearBallads(){
    while(cardView.hasChildNodes()){
        cardView.removeChild(cardView.lastChild);
    }
}
function getBallads(url,config){
    clearBallads();
    $.post(url,
    config,
    function (ballads) {
        var count  = 0;
        const column1 = document.createElement("div");
        column1.className = "column1";
        const column2 = document.createElement("div");
        column2.className = "column2";
        const column3 = document.createElement("div");
        column3.className = "column3";
        console.log("isGrid: "+isGrid);
        for(var i = 0; i < ballads.length; i++){
            count ++;
            if(isGrid) {
                if (count == 1) {
                    column1.appendChild(createBallad(ballads[i]));
                } else if (count == 2) {
                    column2.appendChild(createBallad(ballads[i]));
                } else {
                    column3.appendChild(createBallad(ballads[i]));
                    count = 0;
                }
            }else{
                cardView.appendChild(createBallad(ballads[i]))
            }
        }
        cardView.appendChild(column1);
        cardView.appendChild(column2);
        cardView.appendChild(column3);
    });
}

function toggleListView(){
    if(isGrid){
        cardView.id = toggleViewVals[isGrid];
        console.log(cardView.id);
    }else{
        cardView.id=toggleViewVals[isGrid];
        console.log(cardView.id);
    }
    mainLoad();
}
function mainLoad() {
    if(pageLocation.includes("my-ballads")){
        getBallads("/myBallads");
    }
    if(pageLocation.includes("popular")){
        let config = {
            userOnly:false,
            isPublic: true,
            isPrivate: true,
            caseId: 6,
            search: $("#ballads-search").val(),

        };
        getBallads("/sortBallads",config);
    }
    if(pageLocation.includes("user-profile")){
        const x = pageLocation.split("=");
        const config = {
            userId: x[1],
        }
        getBallads("/user-public-ballads",config);
    }
}

$(document).ready(function () {

    mainLoad();


    $('#current-sort').click(function() {
        $('#sort-list').slideDown();
    });

    $(".sort-choice").click(function() {
      // $("#current-sort").html("Recent");
      $('#current-sort').html($(this).html());
      $('#sort-list').slideUp();
    });

    // $("#sort-list #public").click(function() {
    //   $("#current-sort").html("Public");
    //   $("#sort-list").slideUp();
    // });
    //
    // $("#sort-list #private").click(function() {
    //   $("#current-sort").html("Private");
    //   $("#sort-list").slideUp();
    // });
    //
    // $("#sort-list #likes").click(function() {
    //   $("#current-sort").html("Likes");
    //   $("#sort-list").slideUp();
    // });

    $('.layout-icon').click(function() {
        // console.log(isGrid);
        isGrid = !isGrid;
        toggleListView()
    });

    $("#sort-list li").click(function (e) {
        $(document).closest("div.column1").remove();
        // console.log(e.target.textContent);
        const sort = e.target.textContent;

        if(pageLocation.includes("my-ballads")){
            isUser = true;
        }
        console.log(isUser);
        currentSortID =sortIndex[sort];

        const action = {
            Recent: ()=>{
                let config = {
                    userOnly:isUser,
                    isPublic: true,
                    isPrivate: isUser,
                    caseId: sortIndex[sort],
                    search: $("#ballads-search").val(),

                };
                getBallads("/sortBallads",config);

            },
            Public: ()=>{
                let config = {
                    userOnly:isUser,
                    isPublic: true,
                    isPrivate: isUser,
                    caseId: sortIndex[sort],
                    search: $("#ballads-search").val(),

                };
                console.log(config);
                getBallads("/sortBallads",config);

            },
            Private:()=>{
                let config = {
                    userOnly:isUser,
                    isPublic: true,
                    isPrivate: isUser,
                    caseId: sortIndex[sort],
                    search: $("#ballads-search").val(),

                };
                getBallads("/sortBallads",config);
            },
            Likes:()=>{
                let config = {
                    userOnly:isUser,
                    isPublic: true,
                    isPrivate: isUser,
                    caseId: sortIndex[sort],
                    search: $("#ballads-search").val(),

                };
                getBallads("/sortBallads",config);
            }
        };
        action[sort]();
    });

    $("#ballads-search").on('keydown',function (e) {
        const option = document.getElementById("current-sort").textContent;

        let config = {};

        if(pageLocation.includes("user-profile")) {
            const x = pageLocation.split("=");
            config = {
                caseId: sortIndex[option],
                search: $("#ballads-search").val(),
            };
            getBallads("/profileBallads",config);
        }else if(pageLocation.includes("my-ballad")){
            config = {
                userOnly: true,
                isPublic: true,
                isPrivate: true,
                caseId: sortIndex[option],
                search: $("#ballads-search").val(),

            };
            getBallads("/sortBallads",config);
        }else{
           config = {
                userOnly: false,
                isPublic: true,
                isPrivate: true,
                caseId: sortIndex[option],
                search: $("#ballads-search").val(),

            };
            getBallads("/sortBallads",config);
        }



    });

    $("div").on('click',".editBallad",function (e) {

        if(pageLocation.includes("my-ballads")){
            const ballad_id = $(this).parent().find("input[type=hidden]").val();
            // console.log(ballad_id);
            window.location = "/editor/"+ballad_id;
        }
    });
    $("div").on("click",".deleteBallad", function (e) {
        if(pageLocation.includes("my-ballads")){
            const ballad_id = $(this).parent().find("input[type=hidden]").val();
            // console.log(ballad_id);
            // window.location = "/editor/"+ballad_id;
            $.post("/deleteBallad",
                {
                    balladId:ballad_id,
                },function (success) {

                }
            );
            $(this).parent().parent().remove();
        }
    });
    $("div").on('click',".viewBallad",function (e) {
        if(pageLocation.includes("popular")||pageLocation.includes("user-profile")){
            const ballad_id = $(this).parent().find("input[type=hidden]").val();
            // console.log(ballad_id);
            window.location = "/viewBallad?balladId="+ballad_id;
        }
    });



});
