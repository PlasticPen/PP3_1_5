$(document).on('show.bs.modal', '#editModal', function (event) {
    $.getJSON('http://localhost:8080/api/users/' + $(event.relatedTarget).data('id'), function (jd) {
        $("#EditId").attr('placeholder', jd.id);
        $("#EditFirstname").attr('value', jd.name);
        $("#EditLastname").attr('value', jd.surname);
        $("#EditAge").attr('value', jd.age);
        $("#EditEmail").attr('value', jd.username);
        $("#EditPassword").attr('field', jd.password);
        if (jd.username === "admin@mail.ru") {
            $('#EditRoleId').attr('disabled', true);
            $('#EditEmail').attr('disabled', true);
        }
        $("#editButton").click(function () {
            var arr = {
                "name": $("#EditFirstname").val(),
                "surname": $("#EditLastname").val(),
                "age": $("#EditAge").val(),
                "username": $("#EditEmail").val(),
                "password": $("#EditPassword").val(),
                "roleId": $("#EditRoleId").val(),
            };
            $.ajax('http://localhost:8080/api/users/' + jd.id, {
                'data': JSON.stringify(arr),
                'type': 'PATCH',
                'processData': false,
                'contentType': 'application/merge-patch+json',
                'success': function () {
                    setTimeout(allUsersHandler, 500);
                }
            });
        })
    })
})