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

function loadHTML(selector, url, listener) {
    fetch(url)
    .then(response => response.text())
    .then((result) => {
        document.querySelector(selector).innerHTML = result;
        if (listener) {
            listener();
        }
    });
}

function getCookie(name) {
    var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return value? value[2] : null;
}