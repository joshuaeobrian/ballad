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
let sortID= 4;
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
    title.textContent = ballad["title"];
    var author = document.createElement("h2");
    author.textContent = ballad["owner"]["firstName"]+" "+ballad["owner"]["lastName"];
    var content = document.createElement("p");
    content.textContent = ballad["ballad"];

    var date = document.createElement("div");
    date.className = "date";
    var span = document.createElement("span");
    span.textContent = ballad["creationDate"]["month"]+" "+ballad["creationDate"]["dayOfMonth"]+", "+ballad["creationDate"]["year"];

    date.appendChild(span);

    var typeDiv = document.createElement("div");
    typeDiv.className = "private";//(ballad["publicView"])? "private" : "public";

    var typeText = document.createElement("span");
    typeText.textContent =  (ballad["publicView"])? "Public" : "Private";
    typeDiv.appendChild(typeText);

    var bbottom = document.createElement("div");
    bbottom.className ="bbottom";

    bCard.appendChild(btop);

    listDiv.appendChild(hiddenId);
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
    }else{
        cardView.id=toggleViewVals[isGrid];
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
            caseId: 3,
            search: $("#ballads-search").val(),

        };
        getBallads("/sortBallads",config);
    }
}

$(document).ready(function () {

    mainLoad();

    $('#sort-dropdown').click(function() {
        $('#sort-list').slideToggle();
    });

    $('.layout-icon').click(function() {
        console.log(isGrid);
        isGrid = !isGrid;
        toggleListView()


    });
    


    $("#sort-list li").click(function (e) {
        $(document).closest("div.column1").remove();
        console.log(e.target.textContent);
        const sort = e.target.textContent;

        if(pageLocation.includes("my-ballads")){
            isUser = true;
        }

        sortID = sortIndex[sort];

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

        let config = {
            userOnly:isUser,
            isPublic: true,
            isPrivate: isUser,
            caseId: sortID,
            search: $("#ballads-search").val(),

        };
        getBallads("/sortBallads",config);

    });


    $("div").on('click',".ballad-card",function (e) {
        console.log($(this).attr("class"));
        console.log("hello");

        const ballad_id = $(this).children("input:first").val();
        console.log(ballad_id);
        window.location = "/editor/"+ballad_id;
    });


});
