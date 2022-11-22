function notify(message, timer=5){

    var span = document.createElement("span");
    span.className = "sb-notify sb-normal";
    var content = document.createTextNode(message);
    span.appendChild(content);
    document.body.appendChild(span);

    setTimeout(function(){
        document.body.removeChild(span);
    },timer*1000);
}