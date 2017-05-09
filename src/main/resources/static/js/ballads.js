const sortIndex = {
    Title:1,
    Recent:4,
    Public:10,
    Private:9,
    Likes:6,

};
const cardView = document.getElementsByClassName("ballad-card-view")[0];
let isUser = false;
const createBallad = (ballad)=>{
    var bCard = document.createElement("div");
    bCard.classList.add( "ballad-card");
    var btop = document.createElement("div");
    btop.className = "btop";
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
    bCard.appendChild(hiddenId);
    bCard.appendChild(title);
    bCard.appendChild(author);
    bCard.appendChild(content);
    bCard.appendChild(date);
    bCard.appendChild(typeDiv);
    bCard.appendChild(bbottom);
    bCard.style.cursor = "pointer";

    return bCard;

};
function clearBallads(){
    const col1 = document.querySelectorAll("div.column1");
    const col2 = document.querySelectorAll("div.column2");
    const col3 = document.querySelectorAll("div.column3");

    for(let i = 0; i < col1.length; i++){
        cardView.removeChild(col1[i]);
    }
    for(let i = 0; i < col2.length; i++){
        cardView.removeChild(col2[i]);
    }
    for(let i = 0; i < col3.length; i++){
        cardView.removeChild(col3[i]);
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
        for(var i = 0; i < ballads.length; i++){
            count ++;
            if(count == 1){
                column1.appendChild(createBallad(ballads[i]));
            }else if(count == 2){
                column2.appendChild(createBallad(ballads[i]));
            }else{
                column3.appendChild(createBallad(ballads[i]));
                count = 0;
            }
        }

        cardView.appendChild(column1);
        cardView.appendChild(column2);
        cardView.appendChild(column3);
    });

}

$(document).ready(function () {

    const pageLocation = window.location.href;

    $('#sort-dropdown').click(function() {
        $('#sort-list').slideToggle();
    });

    // $('.layout-icon').click(function() {
    //     $('#ballad-cards-container-grid').toggle();
    //     $('#ballad-cards-container-list').toggle();
    //
    // });
    
    if(pageLocation.includes("my-ballads")){
        getBallads("/myBallads");
    }
    if(pageLocation.includes("popular")){
        let config = {
            userOnly:false,
            isPublic: true,
            isPrivate: false,
            caseId: 3,
            search: $("#ballads-search").val(),
            
        };
        getBallads("/sortBallads",config);
    }

    $("#sort-list li").click(function (e) {
        $(document).closest("div.column1").remove();
        console.log(e.target.textContent);
        const sort = e.target.textContent;

        if(pageLocation.includes("my-ballads")){
            isUser = true;
        }

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

            }
        };
        action[sort]();
    });

    $("#ballads-search").on('keydown',function (e) {
        console.log($("#ballads-search").val());
        if(pageLocation.includes("my-ballads")){
           // getBallads("/myBallads");
        }
    })

    $("div").on('click',".ballad-card",function (e) {
        console.log($(this).attr("class"));
        console.log("hello");

        const ballad_id = $(this).children("input:first").val();
        console.log(ballad_id);
        window.location = "/editor/"+ballad_id;
    });

});
