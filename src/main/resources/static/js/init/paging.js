var nextPage = 1;
var pageSize = 10;
var firstPage = 1;
var pageNum = 1;
var prePage = 1;
var lastPage = 1;
var params = {};
$(function () {
    $("#resources_meny>li").click(function(){
        var rul = getRootPath() + location.pathname +"?pageSize="+$(this).text()+queryParams();
        location.assign(rul);
    });
    $(".page-last").click(function(){
        loadResources(lastPage,pageSize);
    });
    $(".page-next").click(function(){
        loadResources(nextPage,pageSize);
    });
    $(".page-pre").click(function(){
        loadResources(prePage,pageSize);
    });
    $(".page-first").click(function(){
        loadResources(firstPage,pageSize);
    });
});
function searchResources(){
    loadResources(pageNum,pageSize);
}
function loadResources(pageNum,pageSize){
    var rul = getRootPath() + location.pathname +"?pageNum="+pageNum+"&pageSize="+pageSize+queryParams();
    location.assign(rul);
}
function getRootPath() {
    var curWwwPath = location.href;
    var pathName = location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPaht = curWwwPath.substring(0, pos);
    return localhostPaht;
}
function queryParams() {
    var condition = ""
    for (var par in params){
        condition += "&" + par + "=" + params[par];
    }
    return condition;
}






