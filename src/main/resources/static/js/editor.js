/**
 * Created by josh on 5/3/17.
 */


$(document).ready(function () {
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
});