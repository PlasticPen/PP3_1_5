$(document).on('show.bs.modal', '#editModal', function (event) {
    $.getJSON('http://localhost:8080/api/users/' + $(event.relatedTarget).data('id'), function (jd) {
        $("#sampleId").text(jd.id);
        $("#id").attr('placeholder', jd.id);
        $("#firstname").attr('value', jd.surname);
        $("#lastname").attr('value', jd.name);
        $("#age").attr('value', jd.age);
        $("#email").attr('value', jd.username);
        $("#password").attr('field', jd.name);
    })
})
