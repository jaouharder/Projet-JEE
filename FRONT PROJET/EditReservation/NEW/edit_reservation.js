
async function GetOldReservation(){

    // const data = await fetch('http://localhost:8080/reservation/6');
    // let old_reservation = await data.json();

    let old_reservation = JSON.parse(sessionStorage.getItem("oldreservation"));
    // let old_reservation = sessionStorage.getItem("oldreservation");
    // console.log(old_reservation);

    document.getElementById("cin").value = old_reservation.client.cin;
    document.getElementById("first_name").value = old_reservation.client.prenom;
    document.getElementById("last_name").value = old_reservation.client.nom;
    document.getElementById("email").value = old_reservation.client.email;
    document.getElementById("agency_name").value = old_reservation.bureau.agence.nom;
    sessionStorage.setItem("tempoReserv", JSON.stringify(old_reservation));
    let submitbtn = document.getElementById("change");
    submitbtn.addEventListener('click', submit_mod)
}
GetOldReservation();

function submit_mod() {

    let new_reservation = JSON.parse(sessionStorage.getItem("tempoReserv"));


    let service = document.getElementById("service");
   // sessionStorage.setItem("service", service.value);
     let agence = new_reservation.bureau.agence;
    new_reservation.bureau = new_reservation.bureau.agence.bureauList[service.value];
    new_reservation.bureau.agence = agence;
    sessionStorage.setItem("service",new_reservation.bureau.bureauId);
    sessionStorage.setItem("operation", "modifier");
    window.location.href='../Calendar/fullcalendar.html';

    // console.log(new_reservation);

}