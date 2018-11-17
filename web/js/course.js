function dropCourse(courseId){
    $.post("/dropCourse.op", {
        courseId : courseId
    }, function (data) {
        if (data.success) {
            window.location.href = "/course?type=info&id="+courseId;
        } else {
            window.location.href = "/error";
        }
    });

}

function chooseCourse(courseId) {
    $.post("/chooseCourse.op", {
        courseId : courseId
    }, function (data) {
        if (data.success) {
            window.location.href = "/course?type=info&id="+courseId;
        } else {
            window.location.href = "/error";
        }
    })
}

function checkComment() {
    var title = $("#comment-title").val();
    var context = $("#comment-context").val();

    if (title === "" || context === "") {
        $("#comment-warning").text("title and context cannot be null");
        return false;
    } else {
        return true;
    }
}
