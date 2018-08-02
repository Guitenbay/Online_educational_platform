function removeRes(courseId, resPath) {
    $.post("/removeRes.op", {
        courseId : courseId,
        resPath : resPath
    }, function (data) {
        if (data.success) {
            window.location.href = "/add?type=resource&id=" + courseId;
        } else {
            window.location.href = "/error";
        }
    });
}

function checkChapter() {
    var chapterName = $("#chapter-name").val();
    if (chapterName === "") {
        $("#add-chapter-warning").text("chapter name cannot be null");
        return false;
    } else {
        return true;
    }
}