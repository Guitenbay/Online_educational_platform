$(document).ready(function () {
    accountAjax("choose");
});


function accountAjax (type) {
    $.ajax({
        type: "post",       //使用post方法访问后台
        dataType: "json",   //返回json格式的数据
        url: "/account",    //要访问的后台地址
        data: {
            type: type
        },                  //要发送的数据
        success: function(data){        //data为返回的数据，在这里做数据绑定
            $("#account-ajax-block").empty();

            if (type === "open"){
                $("#account-ajax-block").append(
                    "<div class=\"account-add\">" +
                    "<button type=\"button\" class=\"btn btn-info\" data-toggle=\"modal\" data-target=\"#addModal\">Open</button>" +
                    "<div class=\"dropdown-divider\"></div>" +
                    "</div>"
                )
            } else if (type === "choose") {
                $("#account-ajax-block").append(
                    "<div class=\"account-add\">" +
                    "<button type=\"button\" class=\"btn btn-info\" onclick=\"window.location.href='/search'\">Choose</button>" +
                    "<div class=\"dropdown-divider\"></div>" +
                    "</div>"
                )
            } else if (type === "answer") {
                $("#account-ajax-block").append(
                    "<div class=\"account-add\">" +
                    "<h3>我的作业</h3>" +
                    "<div class=\"dropdown-divider\"></div>" +
                    "</div>"
                )
            }

            $("#account-ajax-block").append("<div class=\"row account-row-center\" id=\"account-ajax-cards\"></div>");

            if (type === "answer") {

                for (var i=0; i < data.length; i++) {

                    if (data[i].done === "Done") {
                        $("#account-ajax-cards").append(
                            "<div class=\"account-float\">\n" +
                            "  <div class=\"card\" style=\"width: 15rem;\">\n" +
                            "    <div class=\"card-body\">\n" +
                            "      <h5 class=\"card-title\">" + data[i].name + "</h5>\n" +
                            "      <p class=\"card-text\">from: \n" + data[i].courseName + "</p>\n" +
                            "      <p class=\"card-text\">deadline: \n" + data[i].deadline + "</p>\n" +
                            "      <p class=\"card-text "+ data[i].done +"\">" + data[i].done + "</p>\n" +
                            "      <p class=\"card-text account-score\">Score: " + data[i].score + "</p>\n" +
                            "      <a href=\"/lookHomework?id="+ data[i].homeworkId +"\" class=\"btn btn-primary\">Go to do</a>\n" +
                            "    </div>\n" +
                            "  </div>\n" +
                            "</div>"
                        );
                    } else {
                        $("#account-ajax-cards").append(
                            "<div class=\"account-float\">\n" +
                            "  <div class=\"card\" style=\"width: 15rem;\">\n" +
                            "    <div class=\"card-body\">\n" +
                            "      <h5 class=\"card-title\">" + data[i].name + "</h5>\n" +
                            "      <p class=\"card-text\">from: \n" + data[i].courseName + "</p>\n" +
                            "      <p class=\"card-text\">deadline: \n" + data[i].deadline + "</p>\n" +
                            "      <p class=\"card-text "+ data[i].done +"\">" + data[i].done + "</p>\n" +
                            "      <p class=\"card-text account-score\">Score: " + data[i].score + "</p>\n" +
                            "      <a href=\"/homework?id="+ data[i].homeworkId +"\" class=\"btn btn-primary\">Go to do</a>\n" +
                            "    </div>\n" +
                            "  </div>\n" +
                            "</div>"
                        );
                    }

                }

            } else {

                for (var i=0; i < data.length; i++) {

                    $("#account-ajax-cards").append(
                        "<div class=\"account-float\">\n" +
                        "  <div class=\"card\" style=\"width: 15rem;\">\n" +
                        "    <img class=\"card-img-top\" src=\""+ data[i].src +"\" alt=\"Card image cap\">\n" +
                        "    <div class=\"card-body\">\n" +
                        "      <h5 class=\"card-title\">" + data[i].name + "</h5>\n" +
                        "      <p class=\"card-text\">教师：" + data[i].creatorName + "</p>\n" +
                        "      <p class=\"card-text line-limit-length-for-chrome\">" + data[i].description + "</p>\n" +
                        "      <a href=\"/course?type=info&id="+ data[i].id +"\" class=\"btn btn-primary\">Go for detail</a>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "</div>"
                    );

                }

            }
        },
        error: function(XMLResponse) {window.location = "/error"}
    });
}