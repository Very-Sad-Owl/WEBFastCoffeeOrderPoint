function showEditable() {
    var x = document.getElementsByClassName("edit");
    var i;
    for (i = 0; i < x.length; i++) {
        x[i].hidden = false;
    }
}

function showInfo() {
    var x = document.getElementsByClassName("show");
    var i;
    for (i = 0; i < x.length; i++) {
        x[i].hidden = false;
    }
}

function hideEditable() {
    var x = document.getElementsByClassName("edit");
    var i;
    for (i = 0; i < x.length; i++) {
        x[i].hidden = true;
    }
}

function hideInfo() {
    var x = document.getElementsByClassName("show");
    var i;
    for (i = 0; i < x.length; i++) {
        x[i].hidden = true;
    }
}

function editUser() {
    hideInfo();
    showEditable();
    document.getElementById("edit_switcher").textContent = "Cancel";
    document.getElementById("apply_changes").hidden = false;
}

function showUser() {
    hideEditable();
    showInfo();
    document.getElementById("edit_switcher").textContent = "Edit Profile";
    document.getElementById("apply_changes").hidden = true;
}

function onUserButton(f){
    if (f.classList.contains("show_state")){
        editUser();
        f.classList.remove("show_state");
        f.classList.add("edit_state");
    } else {
        showUser();
        f.classList.remove("edit_state");
        f.classList.add("show_state");
    }
}

function changeImg(f) {
    console.log("on image change");
    let formData = new FormData(f);
    $.ajax({
        type : "POST",
        url : "Controller?command=editprofile&action=upload_img",
        data: formData,
        enctype: 'multipart/form-data',
        processData : false,
        contentType : false,
        cache : false,
        success : function(response) {
            document.getElementById("user_avatar").src = "../../" + response;
            console.log(response)

        },
        error: function(response){
            alert(response);
            console.log("response not ok")
        }
    });

    return false;
}

function onUpdateUser(f) {
    let formData = new FormData(f);

    $.ajax({
        type : "POST",
        url : "Controller?command=editprofile&action=send_msg",
        data: $("#edit_user").serialize(),
        success : function(response) {
            alert(response);
            showUser();
        },
        error: function(response){
            alert(response);
        }
    });

    return false;
}