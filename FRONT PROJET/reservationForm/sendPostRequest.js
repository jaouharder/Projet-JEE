async function postData() {
    let url = "http://localhost:8080/testpost";

    let cin = document.querySelector('#cin');
    let nom = document.querySelector('#nom');
    let prenom = document.querySelector('#prenom');
    let email = document.querySelector('#email');
    let localisation = document.querySelector('#localisation');

    let data = JSON.stringify({
        "cin": cin.value,
        "nom": nom.value,
        "prenom": prenom.value,
        "email": email.value,
        "localisation": localisation.value
    });

    await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: data
    });
   
}
