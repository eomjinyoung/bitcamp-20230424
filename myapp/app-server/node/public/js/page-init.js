"use strict";

const RESTAPI_HOST = "http://localhost:8080";

if ($.cookie("TOKEN")) {
    axios.defaults.headers.common['Authorization'] = `Bearer ${$.cookie("TOKEN")}`;
    // console.log(axios.defaults.headers);
}

axios.get("/header.html", {
    responseType: "text",
})
.then((response) => {
    $("header").html(response.data)
})
.then(() => {
    if ($.cookie("TOKEN")) {
        loadUserInfo();
    }
});

axios.get("/footer.html", {
    responseType: "text",
})
.then((response) => {
    $("footer").html(response.data)
});

function loadUserInfo() {
    axios.get(`${RESTAPI_HOST}/auth/userInfo`)
    .then((response) => {
        let result = response.data;
        // console.log(result.data);
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

    axios.get(`${RESTAPI_HOST}/auth/logout`)
    .then((response) => {
        let result = response.data;
        console.log(result);
        location.href = "/";
    });
}

