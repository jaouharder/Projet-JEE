// script used to check if employee exists
document.getElementById("login").addEventListener('click', () => checkemployee());

async function checkemployee() {
    let employee_url = "http://localhost:8080/employe/" + document.getElementById("brId").value;
    console.log(document.getElementById("password").value);
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "text/plain");
    var raw = document.getElementById("password").value;
    let response = await fetch(employee_url, {
        method: 'POST',
        headers: myHeaders,
        body: raw,//document.getElementById("password").value
        redirect: 'follow'
    });
    let exists = await response.json();
    // console.log(exists);
    if (exists===true) {
        sessionStorage.setItem("id_bureau", document.getElementById("brId").value);
        document.getElementById("login_form").submit();
    }
}


