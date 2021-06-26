function openForm(id) {
    document.getElementById("orderForm").style.display = "block";
    $.ajax({
        type : "POST",
        url : "Controller?command=createorder&action=choose&id="+id,
        success : function(response) {
            let decor = response[0];
            let sizes = response[1];
            let chosen = response[2];
            createDecorForm(decor, sizes, chosen);
        },
        error: function(response){
            alert('request failed');
        }
    });
}


function createDecorForm(decor, sizes, chosen) {
    var decor_div = document.getElementById("pos_properties");
    var size_div = document.getElementById("size_list");

    var chosenMap = new Map(Object.entries(chosen));
    document.getElementById("coast").innerText = chosenMap.get("coast").toFixed(2);

    var chosenBeverage = document.createElement("input");
    chosenBeverage.setAttribute("type", "hidden");
    chosenBeverage.setAttribute("name", "chosen_beverage");
    chosenBeverage.setAttribute("class", "chosen_beverage");
    chosenBeverage.setAttribute("value", chosenMap.get("title"));

    decor.forEach(function (value, i) {
        var map = new Map(Object.entries(value));
        var cost = map.get("coast");

        //element div
        var div_el = document.createElement("div");
        div_el.setAttribute("class", "decoration_element");

        //hidden checkbox
        var dec_el = document.createElement("input");
        dec_el.hidden = true;
        dec_el.setAttribute("type", "checkbox");
        dec_el.setAttribute("name", "decor");
        dec_el.setAttribute("id", "decor"+i);
        dec_el.setAttribute("class", "decor");
        // dec_el.setAttribute("value", map.get("title"));
        dec_el.setAttribute("value", i);
        // dec_el.onclick = function () { updateCoast(cost, "decor"+i); };

        //label
        var label = document.createElement("label");
        label.setAttribute("for", "decor"+i);
        label.innerText = map.get("title");

        //image for label
        var innerImage = document.createElement("img");
        innerImage.setAttribute("src", map.get("imgPath"));
        innerImage.setAttribute("class", "decor_img");

        //counter
        var counter_el = document.createElement("input");
        counter_el.setAttribute("type", "number");
        counter_el.setAttribute("name", "decor_amount");
        counter_el.setAttribute("id", "decor_amount"+i);
        counter_el.setAttribute("class", "decoration_element_counter");
        counter_el.setAttribute("step", "1");
        counter_el.setAttribute("max", "5");
        counter_el.setAttribute("min", "0");
        counter_el.setAttribute("value", "0");

        //setup
        label.appendChild(innerImage);
        div_el.appendChild(label);
        div_el.appendChild(dec_el);
        div_el.appendChild(counter_el);
        decor_div.appendChild(div_el);

        let counter_id = "#"+"decor_amount"+i;
        let cvalue = $(counter_id).val();

        $(counter_id).on('change',function(){
            if($(this).val() > cvalue){
                console.log('Input was incremented ' + $(this).val() + " " + cvalue);
                updateCoastWithDecor(cost)
            }else{
                console.log('Input was decremented ' + $(this).val() + " " + cvalue);
                updateCoastWithDecor(-cost)
            }
            cvalue = $(this).val();

            dec_el.checked = cvalue > 0;
        });
    });


    sizes.forEach(function (value, i) {
        var map = new Map(Object.entries(value));
        var increment = map.get("increment");

        var size_el = document.createElement("input");
        size_el.setAttribute("type", "radio");
        size_el.setAttribute("name", "size");
        size_el.setAttribute("id", "size"+i);
        size_el.setAttribute("class", "size_el");
        // size_el.setAttribute("value", map.get("increment"));
        size_el.setAttribute("value", i);
        size_el.onchange = function () { updateCoastWithSize(increment, size_el.checked === true); };
        if (i === 0){
            size_el.checked = true;
        }

        var label = document.createElement("label");
        label.setAttribute("for", "size"+i);
        label.innerText = map.get("volume");

        size_div.appendChild(label);
        size_div.appendChild(size_el);
    });
}

function closeForm() {
    document.getElementById("orderForm").style.display = "none";
    document.getElementById("pos_properties").innerHTML = "";
    document.getElementById("size_list").innerHTML = "";
}

function onConfirm(f) {
    console.log("onConfirm");
    $.ajax({
        type : "POST",
        url : "Controller?command=createorder&action=add",
        data: $("#form-container").serialize(),
        success : function(response) {
            closeForm();
        },
        error: function(response){
            alert('request failed');
        }
    });
    return false;
}

var prevIncrement = 1;
function updateCoastWithSize(increment) {
    var currentCoast = parseFloat(document.getElementById("coast").innerHTML);

    currentCoast /= (prevIncrement === undefined ? 1 : prevIncrement);
    currentCoast *= parseFloat(increment);

    prevIncrement = increment;
    document.getElementById("coast").innerHTML = currentCoast.toFixed(2) + "";
}

function updateCoastWithDecor(increment, isChecked) {
    var currentCoast = parseFloat(document.getElementById("coast").innerHTML);
    currentCoast += parseFloat(increment);
    document.getElementById("coast").innerHTML = currentCoast.toFixed(2) + "";
}
