///////////////////////////////design Part//////////////////////////////////////////

(function ($) {
    'use strict';
    
    try {
        $('.js-datepicker').daterangepicker({
            "singleDatePicker": true,
            "showDropdowns": true,
            "autoUpdateInput": false,
            locale: {
                format: 'DD/MM/YYYY'
            },
        });
    
        var myCalendar = $('.js-datepicker');
        var isClick = 0;
    
        $(window).on('click',function(){
            isClick = 0;
        });
    
        $(myCalendar).on('apply.daterangepicker',function(ev, picker){
            isClick = 0;
            $(this).val(picker.startDate.format('DD/MM/YYYY'));
    
        });
    
        $('.js-btn-calendar').on('click',function(e){
            e.stopPropagation();
    
            if(isClick === 1) isClick = 0;
            else if(isClick === 0) isClick = 1;
    
            if (isClick === 1) {
                myCalendar.focus();
            }
        });
    
        $(myCalendar).on('click',function(e){
            e.stopPropagation();
            isClick = 1;
        });
    
        $('.daterangepicker').on('click',function(e){
            e.stopPropagation();
        });
    
    
    } catch(er) {console.log(er);}
    
    
    try {
        var selectSimple = $('.js-select-simple');
    
        selectSimple.each(function () {
            var that = $(this);
            var selectBox = that.find('select');
            var selectDropdown = that.find('.select-dropdown');
            selectBox.select2({
                dropdownParent: selectDropdown
            });
        });
    
    } catch (err) {
        console.log(err);
    }
    

})(jQuery);

////////////////////////////////   Logic Part     ///////////////////////////////////////////////////////////////////
let Reservation={
    reservationId :3,
    horaire : new Date('December 17, 1995 03:24:00'),
    bureau : {
                service : 'virement Bancaire',
                agence : {
                    nom : 'Agence Kortoba'
                }
    },
    client : {
                  nom : 'MALAk',
                  prenom :'AZERTY',
                  email :'momomo@gmail.com'
    },
      duree :  3
}

const first_name=document.getElementById("first_name").value=Reservation.client.prenom;
const last_name=document.getElementById("last_name").value=Reservation.client.nom;
const email=document.getElementById("email").value=Reservation.client.email;
const date=document.getElementById("date").value=Reservation.horaire;
const agency_name=document.getElementById("agency_name").value=Reservation.bureau.agence.nom;
const service=document.getElementById("service");

document.getElementById("email").setAttribute('disabled',true);
document.getElementById("agency_name").setAttribute('disabled',true);
document.getElementById("first_name").setAttribute('disabled',true);
document.getElementById("last_name").setAttribute('disabled',true);
document.getElementById("date").setAttribute('disabled',true);
const submit=document.getElementById('change');
const deleletbnt=document.getElementById('delete');

function SetNewAttributes(reservation){
    reservation.horaire=date;
    reservation.agency_name=agency_name;
    reservation.service=service.value;
}


async function FetchApi(url = '',data = {}) {
    const response = await fetch(url, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json'
      },
     body:  JSON.stringify(data) 
    });
    return response.json(); 
  }
  
submit.addEventListener('click',()=>{
    alert('clicked...');
    SetNewAttributes(Reservation);
    FetchApi('http://localhost:8080/updatereservation/'+Reservation.reservationId,Reservation)
    .then(data => { console.log(data);});
});

deleletbnt.addEventListener('click',()=>{
    alert('are You sure you want to delete your reservation');
    FetchApi('http://localhost:8080/deletereservation/'+Reservation.reservationId)
    .then(data => { console.log(data);});

});

