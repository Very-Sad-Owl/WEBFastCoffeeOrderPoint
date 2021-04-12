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


function deleteUser(login) {
    var urlPattern;
    var xmlhttp = getXmlHttp();
    urlPattern = "Controller?command=manageusers&action=delete&login="+login;

    xmlhttp.open("POST", urlPattern, true);
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState === 4)
        {
            if (xmlhttp.status === 200)
            {
                alert(xmlhttp.responseText);
                var row = document.getElementById(login);
                row.parentNode.removeChild(row);
            }
            else
            {
                alert(xmlhttp.responseText);
            }
        }
    };

    xmlhttp.send(null);
}

function banUser(login) {
    var urlPattern;
    var xmlhttp = getXmlHttp();
    urlPattern = "Controller?command=manageusers&action=ban&login="+login;

    xmlhttp.open("POST", urlPattern, true);
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState === 4)
        {
            if (xmlhttp.status === 200)
            {
                document.getElementById(login + "role").innerHTML = "BANNED";
                alert(xmlhttp.responseText);
            }
            else
            {
                alert(xmlhttp.responseText);
            }
        }
    };

    xmlhttp.send(null);
}

function unbanUser(login) {
    var urlPattern;
    var xmlhttp = getXmlHttp();
    urlPattern = "Controller?command=manageusers&action=unban&login="+login;

    xmlhttp.open("POST", urlPattern, true);
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState === 4)
        {
            if (xmlhttp.status === 200)
            {
                alert(xmlhttp.responseText);
                document.getElementById(login + "role").innerHTML = "USER";
            }
            else
            {
                alert(xmlhttp.responseText);
            }
        }
    };

    xmlhttp.send(null);
}



