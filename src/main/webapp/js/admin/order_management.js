function acceptOrder(uid) {
    $.ajax({
        type : "POST",
        url : 'Controller?command=manageorders&action=preparing&uid='+uid,
        success : function(response) {
            location.reload();
        },
        error: function(response){
            alert('request failed');
        }
    });
}
function acceptDecline(uid) {
    $.ajax({
        type : "POST",
        url : 'Controller?command=manageorders&action=denied&uid='+uid,
        success : function(response) {
            location.reload();
        },
        error: function(response){
            alert('request failed');
        }
    });
}

function setRedy(uid) {
    $.ajax({
        type : "POST",
        url : 'Controller?command=manageorders&action=ready&uid='+uid,
        success : function(response) {
            location.reload();
        },
        error: function(response){
            alert('request failed');
        }
    });
}

function acceptDelivered(uid) {
    $.ajax({
        type : "POST",
        url : 'Controller?command=manageorders&action=completed&uid='+uid,
        success : function(response) {
            location.reload();
        },
        error: function(response){
            alert('request failed');
        }
    });
}

function acceptForgotten(uid) {
    $.ajax({
        type : "POST",
        url : 'Controller?command=manageorders&action=forgotten&uid='+uid,
        success : function(response) {
        },
        error: function(response){
            alert('request failed');
        }
    });
}

function changeSpot(selected) {

    var table = document.getElementById('inprocess_orders_table');
    var targetTDs = table.querySelectorAll('tr');


    for (var i = 0; i < targetTDs.length; i++) {
        var td = targetTDs[i];
        if (!td.classList.contains("header")) {
            td.hidden = false;
            if (!td.classList.contains(selected.value)) {
                td.hidden = true;
            } else {
                td.hidden = false;
            }
        }
        console.log(td.innerHTML);
    }
}

