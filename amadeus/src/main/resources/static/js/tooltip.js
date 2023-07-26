function onPageLoaded(){
    $("[data-tooltip]").each(function(i, obj){
        let tp = $(this).attr("data-tooltip");
        $(this).addClass("tooltip");
        $(this).html($(this).html()+'<span class="tooltiptext">'+tp+'</span>');
    });
}

$(document).ready(onPageLoaded);