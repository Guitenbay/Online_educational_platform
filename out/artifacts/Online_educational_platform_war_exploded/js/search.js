$(document).ready(function () {
    var searchWord = getQueryVariable("search");
    $("#optionsRadios1").attr("checked",false);
    $("#optionsRadios1").parent().removeClass("active");
    $("#optionsRadios2").parent().addClass("active");
    $("#optionsRadios3").attr("checked",true);
    if (searchWord) {
        $("#search-input").val(searchWord);
        search(false, true, searchWord, 1);
    } else {
        search(false, true, "", 1);
    }
});

function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}

function doSearch(m) {
    var sortWay=document.getElementById("optionsRadios1").checked;
    var searchWay=document.getElementById("optionsRadios3").checked;
    var word=document.getElementById("search-input").value;

    search(sortWay, searchWay, word, m);
}

function search(sortWay, searchWay, word, nowPage) {
    console.log(sortWay, searchWay, word, nowPage);
    $.ajax({
        type: "post",
        data: {
            "sortWay": sortWay,
            "searchWay": searchWay,
            "word":word
        },
        url: "/search.op",   //后台url
        success: function (data) {
            $("#searchContent").html("");
            $("#searchPage").html("");
            var before=(nowPage-1<1)?1:nowPage-1;
            $("#searchPage").append("<button type=\"button\" class=\"btn btn-secondary\" onclick='doSearch("+before+")'>&laquo;</button>");
            var next=(nowPage+1>((data.length-1)/9+1))?nowPage:nowPage+1;
            for (var q=0; q < data.length; q++){
                if ((q % 9) === 0)
                {
                    $("#searchPage").append(
                        "<button type=\"button\" class=\"btn btn-secondary\" onclick='doSearch(" +(1+(q/9))+ ")'>"+(1+(q/9))+"</button>"
                    );
                }
            }
            for (var i =(nowPage-1)*9; i < data.length && i < nowPage*9; i++){
                $("#searchContent").append(
                    "<div class=\"col-4\">" +
                    "<div class='card search-center' style='width: 18rem'>"+
                    "<img class=\"card-img-top\" src=\""+ data[i].src +"\" alt=\"Card image cap\">" +
                    "<div class=\"card-body\">" +
                    "<h5 class=\"card-title\">"+data[i].name +"</h5>" +
                    "<p class=\"card-text\">教师名："+ data[i].creatorName +"</p>" +
                    "<p class=\"card-text line-limit-length-for-chrome\">"+ data[i].description +"</p>" +
                    "<a href=\"/course?type=info&id="+ data[i].id +"\" class=\"btn btn-primary\">Go for detail</a>" +
                    "</div></div></div>");
            }
            if (data.length === 0){
                $("#searchContent").append("<p class='display-5'>无搜索结果</p>");
            }
            $("#searchPage").append("<button type=\"button\" class=\"btn btn-secondary\" onclick='doSearch("+next+")'>&raquo;</button>");
        }
    });
}