$(document).ready(function(){
    $("#writeBtn").on("click", function(){
        let form = $("#form");
        form.attr("action", "<c:url value='/board/write'/>");
        form.attr("method", "post");
        if(formCheck())
            form.submit();
    });
})

let formCheck = function() {
    let form = document.getElementById("form");
    if(form.title.value=="") {
        alert("제목을 입력해 주세요.");
        form.title.focus();
        return false;
    }
    if(form.content.value=="") {
        alert("내용을 입력해 주세요.");
        form.content.focus();
        return false;
    }
    return true;
}