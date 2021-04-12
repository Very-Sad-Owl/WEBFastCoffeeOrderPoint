function onCheck(f) {
    let formData = new FormData(f);

    $.ajax({
        type : "POST",
        url : "Controller?command=checkorder",
        data: $("#check_order_form").serialize(),
        success : function(response) {
            alert(response);
        },
        error: function(response){
            alert(response);
        }
    });

    return false;
}