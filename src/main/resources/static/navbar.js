$(document).ready(function () {
    $.getJSON('http://localhost:8080/api/users/signedUser', function (jd) {
        $('#navbar1').html(jd.name);
        if (jd.roleId == 2) {
            $('#navbar2').html("ADMIN USER");
        } else {
            $('#admin-pills-tab').hide();
            $('#navbar2').html('<p>' + "USER" + '</p>');
        }
    });
});