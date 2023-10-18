"use strict";

const pageContext = {
    params: parseParams(location.href)
};

function parseParams(url) {
    let params = {}
    let values = location.href.split("?");
    if (values.length > 1) {
        values[1].split("&").forEach(value => {
            let kv = value.split("=");
            params[kv[0]] = kv[1];
        });
    }
    return params;
}

function loadHTML(selector, url) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                document.querySelector(selector).innerHTML = xhr.responseText;
            }
        }
    };
    xhr.open("GET", url, true);
    xhr.send();
}