$(function(){

    var splashRight=$("#rigthSplash");
    var splashLeft=$("#leftSplash");
    if(splashRight.attr("src").substr(splashRight.attr("src").length-3,3) !="jpg")
    {
        splashRight.hide();
    }
    if(splashLeft.attr("src").substr(splashLeft.attr("src").length-3,3) !="jpg")
    {
        splashLeft.hide();
    }



})
