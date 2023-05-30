$(document).ready(function () {
    $.getJSON('http://localhost:8080/api/users/signedUser', function (jd) {
        if (jd.roleId == 2) {
            $('#aboutUserTableRole').html("ADMIN USER");
        } else {
            $('#aboutUserTableRole').html("USER");
        }
        $('#aboutUserTableId').html(jd.id);
        $('#aboutUserTableName').html(jd.name);
        $('#aboutUserTableSurname').html(jd.surname);
        $('#aboutUserTableAge').html(jd.age);
        $('#aboutUserTableUsername').html(jd.username);
    });
});