$(document).ready(function () {
    $("#editUserInclude").load('pages/editUser');
    $("#deleteUserInclude").load('pages/deleteUser');
    $.getJSON('http://localhost:8080/api/users', function (jd) {

        $('#allUsersTableTbodyId').empty();
        $.each(jd, function (index, value) {
            let role;
            if (value.roleId == 2) {
                role = "ADMIN USER";
            } else {
                role = "USER";
            }
            $('#allUsersTableTbodyId').append("<tr>\
                <td>" + value.id + "</td>\
                <td>" + value.name + "</td>\
                <td>" + value.surname + "</td>\
                <td>" + value.age + "</td>\
                <td>" + value.username + "</td>\
                <td>" + role + "</td>\
                <td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#editModal' data-id='" + value.id + "'>Edit</button></td>\
                <td><button type='button' class='btn btn-danger' data-toggle='modal' data-target='#deleteModal' data-id='" + value.id + "'>Delete</button></td>\
                </tr>");
        });

    });
});