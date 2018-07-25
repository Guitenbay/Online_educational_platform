
function login() {
    var username = $("#username").val();
    var password = $("#password").val();

    $.post("/login.do", {
        username: username,
        password: password
    }, function(data){
        if (!data.success) {
            $("#message").text(data["back"]);
        } else {
            alert(data["back"]);
            window.location.href = "/";
        }
    });
}