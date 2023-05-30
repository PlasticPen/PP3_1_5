$(document).ready(function () {
    $("#newUserButton").click(function () {
        var inputFirstname = $("#firstname").val();
        var inputLastname = $("#lastname").val();
        var inputAge = $("#age").val();
        var inputUsername = $("#email").val();
        var inputPassword = $("#password").val();
        var inputRoleId = $("#roleId").val();
        var arr = {
            "name": inputFirstname,
            "surname": inputLastname,
            "age": inputAge,
            "username": inputUsername,
            "password": inputPassword,
            "roleId": inputRoleId,
        };
        $.ajax('http://localhost:8080/api/users', {
            'data': JSON.stringify(arr),
            'type': 'POST',
            'processData': false,
            'contentType': 'application/json'
        });
        setTimeout(allUsersHandler, 500)
        $('.nav-tabs a[href="#users-table"]').tab('show')
    });
});