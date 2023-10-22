"use strict";

const RESTAPI_HOST = "http://localhost:8080";

loadHTML("header", "/header.html", () => {
    if (getCookie("XSRF-TOKEN")) {
        loadUserInfo();
    }
});

loadHTML("footer", "/footer.html");

function loadUserInfo() {
    fetch(`${RESTAPI_HOST}/auth/userInfo`, {
        credentials: "include",
    })
    .then(response => response.json())
    .then((result) => {
        console.log(result);
        if (result.status == "success") {
            if (result.data.photo) {
                document.querySelector("#x-user-photo").src = `http://mvsenqskbqzl19010704.cdn.ntruss.com/member/${result.data.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg`;
            }
            document.querySelector("#x-user-name").innerHTML = result.data.name;
            document.querySelector("#x-user").classList.remove('x-hidden');
            document.querySelector("#x-login").classList.add('x-hidden');
            document.querySelector("#x-logout").addEventListener('click', logout);
        }
    });
}

function logout(e) {
    e.preventDefault();

    fetch(`${RESTAPI_HOST}/auth/logout`, {
        credentials: "include",
        method: "POST",
        headers: new Headers({
            "X-XSRF-TOKEN": getCookie("XSRF-TOKEN")
        }),
        body:new FormData(),
    })
    .then(response => response.json())
    .then((result) => {
        console.log(result);
        location.href = "/";
    });
}

