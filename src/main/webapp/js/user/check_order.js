function onCheck(f) {
    let formData = new FormData(f);

    $.ajax({
        type : "POST",
        url : "Controller?command=checkorder",
        data: $("#check_order_form").serialize(),
        success : function(response) {
            console.log(response);
            var map = new Map(Object.entries(response));
            console.log(map.get("status"));
            document.getElementById("status").innerHTML = map.get("status");
            document.getElementById("date").innerHTML = map.get("date");
            document.getElementById("delivery_point").innerHTML = map.get("address");
            document.getElementById("price").innerHTML = parseFloat(map.get("coast")).toFixed(2);
        },
        error: function(response){
            //alert(response);
        }
    });

    return false;
}