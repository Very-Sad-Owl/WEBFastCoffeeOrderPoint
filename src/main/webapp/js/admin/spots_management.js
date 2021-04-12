function openAddForm() {
    document.getElementById("add_spot_popup").style.display = "block";
}
function openEditForm(uid, region, region_ru, city, city_ru, street, street_ru, house) {
    document.getElementById("edit_spot_popup").style.display = "block";

    document.getElementById("uid").value = uid;
    document.getElementById("region").value = region;
    document.getElementById("region_ru").value = region_ru;
    document.getElementById("city").value = city;
    document.getElementById("city_ru").value = city_ru;
    document.getElementById("street").value = street;
    document.getElementById("street_ru").value = street_ru;
    document.getElementById("house").value = house;

}

function closeEditForm() {
    document.getElementById("edit_spot_popup").style.display = "none";
}

function closeAddForm() {
    document.getElementById("add_spot_popup").style.display = "none";
}

function deleteSpot(uid) {
    $.ajax({
        type : "POST",
        url : 'Controller?command=managespot&action=delete&uid='+uid,
        success : function(response) {
            alert(response);
            closeEditForm();
        },
        error: function(response){
            alert('request failed');
        }
    });

    return false;
}

function onSpotUpdate(f) {
    $.ajax({
        type : "POST",
        url : 'Controller?command=managespot&action=update',
        data: $("#edit_spot").serialize(),
        success : function(response) {
            alert(response);
            closeEditForm();
        },
        error: function(response){
            alert('request failed');
        }
    });

    return false;
}

function onAddSpot(f) {
    $.ajax({
        type : "POST",
        url : 'Controller?command=managespot&action=add',
        data: $("#add_spot").serialize(),
        success : function(response) {
            document.getElementById("success_msg").hidden = false;
        },
        error: function(response){
            document.getElementById("fail_msg").hidden = false;
        }
    });

    return false;
}

function openBuyIngredientsForm() {
    document.getElementById("buy_popup").style.display = "block";
}

function closeBuyIngredientsForm() {
    document.getElementById("buy_popup").style.display = "none";
}

function openUpdateIngredientsForm(title, amount) {
    document.getElementById("chosen_title_header").innerText = title;
    document.getElementById("chosen_title").value = title;
    document.getElementById("ingredient_increment").value = amount;
    document.getElementById("update_popup").style.display = "block";
}

function closeUpdateIngredientsForm() {
    document.getElementById("update_popup").style.display = "none";
}

function onAmountChanged() {
    // let amount = parseInt(document.getElementById("ingredient_increment").value);
    // let selected = parseInt(document.getElementById("all_ingredients").value);
    // console.log(amount + " " + selected);

}

function onBuyNew(f) {
    console.log("onbuynew");
    $.ajax({
        type : "POST",
        url : 'Controller?command=buyingredient&action=add',
        data: $("#add_ingr_form").serialize(),
        success : function(response) {
            closeBuyIngredientsForm();
            console.log(response["title"]);
            var tr = document.createElement("tr");
            tr.setAttribute("id", response["title"]);
            var tdTitle = document.createElement("td");
            tdTitle.innerText = response["title"];
            var tdCoast = document.createElement("td");
            tdCoast.innerText = response["coast"];
            var tdAmount = document.createElement("td");
            tdAmount.innerText = response["quantity"];

            var btnTh = document.createElement("th");
            var btn = document.createElement("button");
            btn.setAttribute("class", "delete_ingr");
            btn.setAttribute("onclick", "openUpdateIngredientsForm('" + response["title"] + "')");
            btn.innerHTML = '<fmt:message bundle="${loc}" key="locale.buy.btn"/>';

            btnTh.appendChild(btn);

            tr.appendChild(tdTitle);
            tr.appendChild(tdCoast);
            tr.appendChild(tdAmount);
            tr.appendChild(btnTh);

            var tbody = document.getElementById("table_body");
            tbody.appendChild(tr);

        },
        error: function(response){

        }
    });
    return false;
}

function onBuy(f) {
    $.ajax({
        type : "POST",
        url : 'Controller?command=buyingredient&action=buy',
        data: $("#update_ingr_form").serialize(),
        success : function(response) {
            closeUpdateIngredientsForm();
            var formData = new FormData(f);
            document.getElementById(formData.get('ingredient')+'_quantity').innerText = formData.get("amount")+"";
        },
        error: function(response){
            alert(response);
        }
    });
    return false;
}

