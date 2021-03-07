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


function checkLogin() {
    return exist;
}

/*async function DeleteReservation(reservation_id){

const echo=await fetch('http://localhost:8080/deletereservation/'+reservation_id,{method:'DELETE',headers:new Headers({'Content-Type':'application/json'})})
        const response=await echo.json();
        console.log(response);

     }*/

async function deleteReservations(){
	const data=await fetch('http://localhost:8080/reservations');
     let reservations=await data.json();
     console.log(reservations);
     document.getElementById('btn').innerHTML="Delete";
	console.log(sessionStorage.getItem('operation'));
	let boutton = document.getElementById('btn');
	

   
     boutton.addEventListener('click', async ()=>{
	 
	 let idReservation = document.getElementById('reservationid').value; 
     let cinClient = document.getElementById('usercin').value;
	  
	 for(let reservation of reservations){
        
        if(idReservation==reservation.reservationId && cinClient==reservation.client.cin){
	      console.log(idReservation);
          const echo=await fetch('http://localhost:8080/deletereservation/'+idReservation,{method:'DELETE',headers:new Headers({'Content-Type':'application/json'})})
          const response=await echo.json();
          console.log(response);
 
        }
      } 

window.location.replace("../index.html");
});
} 



if(sessionStorage.getItem('operation')=="delete"){  
    deleteReservations();
}else if (sessionStorage.getItem('operation')=="edit"){
	console.log(sessionStorage.getItem('operation'));
	login();
} 


