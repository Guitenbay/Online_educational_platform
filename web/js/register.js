
function checkRegister() {

    $("#lr-message").empty();
    var password = $("#password").val();
    var passwordAgain = $("#password-again").val();

    var patt_all_number = /^[0-9]+$/;
    var patt_six = /^.{0,5}$/;
    if (patt_all_number.test(password)) {
        $("#lr-message").append(
            "<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">" +
            "password should not be all number" +
            "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">" +
            "<span aria-hidden=\"true\">&times;</span>" +
            "</button>" +
            "</div>");
    } else if (patt_six.test(password)) {
        $("#lr-message").append(
            "<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">" +
            "password should not be less six" +
            "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">" +
            "<span aria-hidden=\"true\">&times;</span>" +
            "</button>" +
            "</div>");
    } else if (password !== passwordAgain) {

        $("#lr-message").append(
            "<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">" +
            "two password is not equaled" +
            "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">" +
            "<span aria-hidden=\"true\">&times;</span>" +
            "</button>" +
            "</div>");
    } else {
        return true;
    }
    return false;
}