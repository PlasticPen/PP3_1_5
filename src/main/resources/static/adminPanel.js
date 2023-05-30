function allUsersHandler(e) {
    $("#usersTable").empty().load("pages/allUsers")
}

function newHandler() {
    $("#new").load("pages/new")
}

$(document).ready(function(){
    allUsersHandler();
    newHandler();
});

