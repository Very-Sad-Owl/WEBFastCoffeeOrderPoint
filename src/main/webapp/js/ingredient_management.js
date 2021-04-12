function openAddForm() {
    document.getElementById("add_ingredient_popup").style.display = "block";
}
function openEditForm(title, itype, stype, quantity, price, img) {
    document.getElementById("edit_ingredient_popup").style.display = "block";

    document.getElementById("original_ingr_title").value = title;
    document.getElementById("ingr_title").value = title;
    document.getElementById("ingr_type").value = itype;
    document.getElementById("ingr_season_type").value = stype;
    document.getElementById("ingr_price").value = price;
    document.getElementById("ingr_quantity").value = quantity;
    console.log(img);
    document.getElementById("img_path").value = img;
}

function closeEditForm() {
    document.getElementById("edit_ingredient_popup").style.display = "none";
}

function closeAddForm() {
    document.getElementById("add_ingredient_popup").style.display = "none";
    document.getElementById("fails_msg").hidden = true;
    document.getElementById("success_msg").hidden = true;
}

function getXmlHttp(){
    var xmlhttp;
    try {
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
        try {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (E) {
            xmlhttp = false;
        }
    }
    if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
        xmlhttp = new XMLHttpRequest();
    }
    return xmlhttp;
}

function deleteIngredient(title) {
    var urlPattern;
    var xmlhttp = getXmlHttp();
    urlPattern = "Controller?command=manageingredients&action=delete&title="+title;

    xmlhttp.open("POST", urlPattern, true);
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState === 4)
        {
            if (xmlhttp.status === 200)
            {
                alert(xmlhttp.responseText);
                var row = document.getElementById(title);
                row.parentNode.removeChild(row);
            }
            else
            {
                document.getElementById("alert_modal").style.display = "block";
                document.getElementById("msg").innerHTML = xmlhttp.responseText;
            }
        }
    };

    xmlhttp.send(null);

}

function onIngredientUpdate(f) {
    let formData = new FormData(f);
    console.log(formData.get("price"));

    $.ajax({
        type : "POST",
        url : 'Controller?command=manageingredients&action=update',
        data: $("#edit_ingredient").serialize(),
        success : function(response) {
            closeEditForm();
            document.getElementById("alert_modal").style.display = "block";
            document.getElementById("msg").innerHTML = response;
        },
        error: function(response){
            document.getElementById("alert_modal").style.display = "block";
            document.getElementById("msg").innerHTML = response;
        }
    });

    return false;
}

function onAddIngredient(f) {
    $.ajax({
        type : "POST",
        url : 'Controller?command=manageingredients&action=add',
        data: $("#add_ingredient").serialize(),
        success : function(response) {
            document.getElementById("success_msg").hidden = false;
            closeAddForm();
            document.getElementById("alert_modal").style.display = "block";
            document.getElementById("msg").innerHTML = response;
        },
        error: function(response){
            document.getElementById("alert_modal").style.display = "block";
            document.getElementById("msg").innerHTML = response;
        }
    });

    return false;
}

function uploadFile(f) {
    let formdata = new FormData(f);
    $.ajax({
        type : "POST",
        url : 'Controller?command=manageingredients&action=upload_img',
        // data: $("#upload_ingredient_img").serialize(),
        data: formdata,
        enctype: 'multipart/form-data',
        processData : false,
        contentType : false,
        cache : false,
        success : function(response) {
            console.log(response);
            document.getElementById("img_path").value = response;
            document.getElementById("uploaded_img_preview").src = "../../" + response;
            document.getElementById("uploaded_img_preview").hidden = false;
            console.log("response ok")
        },
        error: function(response){
            console.log("response not ok")
        }
    });

    return false;
}

function uploadNewFile(f) {
    let formdata = new FormData(f);
    $.ajax({
        type : "POST",
        url : 'Controller?command=manageingredients&action=upload_img',
        // data: $("#upload_ingredient_img").serialize(),
        data: formdata,
        enctype: 'multipart/form-data',
        processData : false,
        contentType : false,
        cache : false,
        success : function(response) {
            console.log(response);
            document.getElementById("new_img_path").value = response;
            document.getElementById("new_uploaded_img_preview").src = "../" + response;
            document.getElementById("new_uploaded_img_preview").hidden = false;
            console.log("response ok")
        },
        error: function(response){
            console.log("response not ok");
            document.getElementById("alert_modal").style.display = "block";
            document.getElementById("msg").innerHTML = response;
        }
    });

    return false;
}
