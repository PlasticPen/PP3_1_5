$(document).on('show.bs.modal', '#deleteModal', function (event) {
    $.getJSON('http://localhost:8080/api/users/' + $(event.relatedTarget).data('id'), function (jd) {
        $("#id").attr('placeholder', jd.id);
        $("#firstname").attr('value', jd.surname);
        $("#lastname").attr('value', jd.name);
        $("#age").attr('value', jd.age);
        $("#email").attr('value', jd.username);
        $("#password").attr('field', jd.password);
        if (jd.roleId == 1) {
            $("#isAdminOption").hide();
        }
    })
})