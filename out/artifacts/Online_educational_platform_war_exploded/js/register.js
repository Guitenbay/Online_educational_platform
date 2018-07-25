
function register() {
    var username = $("#username").val();
    var password = $("#password").val();

    alert("ok");
    $.post("/register", {
        username: username,
        password: password
    }
    // , function(data){
    //     if (!data.success) {
    //         $("#message").text(data["back"]);
    //     } else {
    //         alert(data["back"]);
    //         window.location.href = "/login.html";
    //     }
    // }
    );
}