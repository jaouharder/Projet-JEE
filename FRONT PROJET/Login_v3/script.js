let exist = false;

async function login(){
     
     const data=await fetch('http://localhost:8080/reservations');
     let reservations=await data.json();
     console.log(reservations);
     
     let boutton = document.getElementById('btn');
     

     boutton.addEventListener('click', ()=>{
      let idReservation = document.getElementById('reservationid').value;
      console.log(idReservation);
      // let exist = false;
      for(let reservation of reservations){
        
        if(idReservation==reservation.reservationId){
          exist=true; 
          sessionStorage.setItem("oldreservation", JSON.stringify(reservation));
          break;
        }
      }
      console.log(exist);
      //alert("w9ef 3and hadk");
      if(exist==false) alert("info non valide");
         sessionStorage.setItem("exist", exist);
     });

}
login();

function checkLogin() {
    return exist;
}