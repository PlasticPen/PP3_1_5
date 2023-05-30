$(document).on('show.bs.modal', '#deleteModal', function (event) {
    $.getJSON('http://localhost:8080/api/users/' + $(event.relatedTarget).data('id'), function (jd) {
        $("#DeleteId").attr('placeholder', jd.id);
        $("#DeleteFirstname").attr('value', jd.surname);
        $("#DeleteLastname").attr('value', jd.name);
        $("#DeleteAge").attr('value', jd.age);/////////////////////////
        $("#DeleteEmail").attr('value', jd.username);
        if (jd.roleId < 2) {
            $("#isAdminOption").hide();
        }
        $("#deleteButton").click(function () {
            $.ajax('http://localhost:8080/api/users/' + jd.id, {
                'type': 'DELETE',
                'success': function () {
                    setTimeout(allUsersHandler, 500);
                }
            });
        })
    })
})