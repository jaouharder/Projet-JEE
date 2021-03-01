let exist = false;

async function login(){
     
     const data=await fetch('http://localhost:8080/reservations');
     let reservations=await data.json();
     console.log(reservations);
     
     let boutton = document.getElementById('btn');
     

     boutton.addEventListener('click', ()=>{
      let idReservation = document.getElementById('reservationid').value;
      let cinClient = document.getElementById('usercin').value;
      console.log(cinClient);
      console.log(idReservation);
      // let exist = false;
      for(let reservation of reservations){
        
        if(idReservation==reservation.reservationId && cinClient==reservation.client.cin){
          exist=true; 
          sessionStorage.setItem("oldreservation", JSON.stringify(reservation));
          break;
        }
      }
      console.log(exist);
      //alert("w9ef 3and hadk");
      if(exist==false) {Swal.fire({
  icon: 'error',
  title: 'Error',
  text: 'Invalid ID/CIN!',
  footer: '<a href>Forget ID? Check your mail</a>'
})}
         sessionStorage.setItem("exist", exist);
     });

}
login();

function checkLogin() {
    return exist;
}

