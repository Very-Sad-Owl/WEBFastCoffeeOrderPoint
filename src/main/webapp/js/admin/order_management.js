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
            location.reload();
        },
        error: function(response){
            alert('request failed');
        }
    });
}

function changeSpot(selected) {
    console.log("changespot 1");
    $.ajax({
        type : "GET",
        url : 'Controller?command=gotomanageorders&uid='+selected.value,
        success : function(response) {
            console.log("changespot 2");
            location.reload();
        },
        error: function(response){
            alert('request failed');
        }
    });
}

