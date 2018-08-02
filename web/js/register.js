var code="";
function checkRegister() {

    $("#lr-message").empty();
    var password = $("#password").val();
    var passwordAgain = $("#password-again").val();
    var identifying=$("#code").val();

    var patt_all_number = /^[0-9]+$/;
    var patt_six = /^.{0,5}$/;
    if (patt_all_number.test(password)) {
        $("#lr-message").append(
            "<div class=\"alert alert-warning alert-dismissible fade show lr-center\" role=\"alert\">" +
            "password should not be all number" +
            "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">" +
            "<span aria-hidden=\"true\">&times;</span>" +
            "</button>" +
            "</div>");
    } else if (patt_six.test(password)) {
        $("#lr-message").append(
            "<div class=\"alert alert-warning alert-dismissible fade show lr-center\" role=\"alert\">" +
            "password should not be less six" +
            "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">" +
            "<span aria-hidden=\"true\">&times;</span>" +
            "</button>" +
            "</div>");
    } else if (password !== passwordAgain) {

        $("#lr-message").append(
            "<div class=\"alert alert-warning alert-dismissible fade show lr-center\" role=\"alert\">" +
            "two password is not equaled" +
            "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">" +
            "<span aria-hidden=\"true\">&times;</span>" +
            "</button>" +
            "</div>");
    } else if (identifying==null||Number(code)!==Number(identifying)){
        $("#lr-message").append(
            "<div class=\"alert alert-warning alert-dismissible fade show lr-center\" role=\"alert\">" +
            "please input the identifying code you have received!" +
            "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">" +
            "<span aria-hidden=\"true\">&times;</span>" +
            "</button>" +
            "</div>");
    }

    else {
        return true;
    }
    return false;
}

function doCode() {
        $("#lr-message").empty();
        var email = $("#email").val();

        var at = email.indexOf("@");
        var dot = email.lastIndexOf(".");
        if (email.length === 0) {
            $("#lr-message").append(
                "<div class=\"alert alert-warning alert-dismissible fade show lr-center\" style='width:100%' role=\"alert\">" +
                "Email should not be empty" +
                "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">" +
                "<span aria-hidden=\"true\">&times;</span>" +
                "</button>" +
                "</div>");
        }
        else if (at < 1 || dot < at + 2 || dot + 2 >= email.length) {
            $("#lr-message").append(
                "<div class=\"alert alert-warning alert-dismissible fade show lr-center\" style='width:100%' role=\"alert\">" +
                "Email should not be useless" +
                "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">" +
                "<span aria-hidden=\"true\">&times;</span>" +
                "</button>" +
                "</div>");
        }
        else {
            document.getElementById("send-button").disabled = "disabled";
            $.ajax({
                type: "post",
                data: {
                    "email": email
                },
                url: "EmailServlet",   //后台url
                success: function (response) {
                    setTimeout(display, 60000);
                    code=response;
                    alert("发送成功，请耐心等待投递！")
                }

            });
        }

}
function display() {
    document.getElementById("send-button").style.display="inline";
}