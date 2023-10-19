"use strict";

loadHTML("header", "http://localhost/header.html", () => {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                let result = JSON.parse(xhr.responseText);
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
            }
        }
    };
    xhr.open("GET", "/auth/userInfo", true);
    xhr.send();
});

loadHTML("footer", "http://localhost/footer.html");

function logout(e) {
    e.preventDefault();
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                //console.log(xhr.responseText);
                location.href = "/";
            }
        }
    };
    xhr.open("POST" , `/auth/logout` , true);
    xhr.setRequestHeader(
        "Content-Type",
        "application/x-www-form-urlencoded");
    xhr.send("");
}

