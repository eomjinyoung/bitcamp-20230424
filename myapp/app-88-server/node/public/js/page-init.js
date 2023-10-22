"use strict";

const RESTAPI_HOST = "http://localhost:8080";

$("header").load("/header.html", () => {
    if ($.cookie("XSRF-TOKEN")) {
        loadUserInfo();
    }
});

$("footer").load("/footer.html");

function loadUserInfo() {

    $.ajax(`${RESTAPI_HOST}/auth/userInfo`, {
        xhrFields: {
            withCredentials: true
        },
        dataType: "json",
    })
    .done((result) => {
        console.log(result);
        if (result.status == "success") {
            if (result.data.photo) {
                $("#x-user-photo").attr("src", `http://mvsenqskbqzl19010704.cdn.ntruss.com/member/${result.data.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg`);
            }
            $("#x-user-name").html(result.data.name);
            $("#x-user").removeClass('x-hidden');
            $("#x-login").addClass('x-hidden');
            $("#x-logout").click(logout);
        }
    });
}

function logout(e) {
    e.preventDefault();

    $.ajax(`${RESTAPI_HOST}/auth/logout`, {
        xhrFields: {
            withCredentials: true
        },
        headers: {
            "X-XSRF-TOKEN": $.cookie("XSRF-TOKEN")
        },
        dataType: "json",
        method: "POST",
        data:new FormData(),
        contentType: false,
        processData: false,
    })
    .done((result) => {
        console.log(result);
        location.href = "/";
    });
}

