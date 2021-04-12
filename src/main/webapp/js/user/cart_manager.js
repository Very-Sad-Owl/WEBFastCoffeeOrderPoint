function onOrder(f) {
    let formData = new FormData(f);

    $.ajax({
        type : "POST",
        url : "Controller?command=cartmanager&action=order",
        data: $("#order_form").serialize(),
        success : function(response) {
            alert(response);
            showUser();
        },
        error: function(response){
            alert(response);
        }
    });

    return false;
}