mapboxgl.accessToken = 'pk.eyJ1IjoiYmVuZXR0YWxlYiIsImEiOiJja2w3NWV1dnMyZXp4MnZsYjB1ZW9qcDVjIn0.fSUhZIwlPmxnd95ioh7e-Q';

 

//navigator.geolocation.getCurrentPosition(successLocation,errorLocation,{enableHighAccuracy:true});
/*
function successLocation(position){
   console.log(position);
   Setupmap([position.coords.longitude,position.coords.latitude]);
   
}

mapboxgl.accessToken = 'pk.eyJ1IjoieGVub3Bob2JlIiwiYSI6ImNrbDZwdHhpaTByZ2QycXFwdXk1bTNnY2kifQ.MVEO4m4i88F8hNRaIAXXaA';
mapboxgl.accessToken = 'pk.eyJ1IjoiYmVuZXR0YWxlYiIsImEiOiJja2w3NWV1dnMyZXp4MnZsYjB1ZW9qcDVjIn0.fSUhZIwlPmxnd95ioh7e-Q';


mapboxgl.setRTLTextPlugin('https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-rtl-text/v0.2.0/mapbox-gl-rtl-text.js');
//navigator.geolocation.getCurrentPosition(successLocation,errorLocation,{enableHighAccuracy:true});
/* hhh

function successLocation(position){
   console.log(position);
   Setupmap([position.coords.longitude,position.coords.latitude]);
   
}
function errorLocation(){
  Setupmap([])
}
function Setupmap(center){
   
     //create map object with a defined center and a zoom 
    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center :center,
        zoom : 8
      });
      //add controlle buttons zoom in, zoom out
      map.addControl(new mapboxgl.NavigationControl());
      //add a marker in a specified location with red color
      var marker = new mapboxgl.Marker({
color: "#000000",
draggable: true
}).setLngLat([2.1734,41.3851])
.setPopup(new mapboxgl.Popup().setHTML("<h1>Hello World!</h1>"))
.addTo(map);
}
Setupmap([2.1734,41.3851]);
var new_york = new mapboxgl.LngLat(-74.0060, 40.7128);
var los_angeles = new mapboxgl.LngLat(-118.2437, 34.0522);
alert(new_york.distanceTo(los_angeles)/1000); 
*/
 










let userlocation;
 
 var map;



let agencies;
async function GetAgencies(){
  
 

  const data=await fetch('http://localhost:8080/agencies');
  agencies=await data.json();
  console.log(agencies);  


  

//create map object with a defined center and a zoom 
 map = new mapboxgl.Map({
 container: 'map',
 style: 'mapbox://styles/mapbox/streets-v11',
 center :[-5.554722,33.895000],
 zoom : 8
});

//create the search bar to serach for a location by name
map.addControl(
  new MapboxGeocoder({
  accessToken: mapboxgl.accessToken,
  mapboxgl: mapboxgl
  })
  );


//add controlle buttons zoom in, zoom out
map.addControl(new mapboxgl.NavigationControl());



//Get position of the user
navigator.geolocation.getCurrentPosition(successLocation,errorLocation,{enableHighAccuracy:true});


 
}




function successLocation(position){
   //console.log(position);
   var markeruser = new mapboxgl.Marker({
    color: "#000000",
    draggable: true
    }).setLngLat([position.coords.longitude,position.coords.latitude])
    .setPopup(new mapboxgl.Popup().setHTML("<h4>User</h4>"))
    .addTo(map);

    
    userlocation = new mapboxgl.LngLat(position.coords.longitude, position.coords.latitude);





    
console.log("ready");

console.log(userlocation);


for(var agence of agencies){


  let agence_location = new mapboxgl.LngLat(agence.longitude, agence.latitude);   
  console.log(agence_location);
  let distance=(Math.round(agence_location.distanceTo(userlocation)/1000*100))/100;
  console.log(distance);


  var marker = new mapboxgl.Marker({
    color: "#EEBA00"
    }).setLngLat([agence.longitude , agence.latitude])
    .setPopup(new mapboxgl.Popup()
.setHTML(

"<ul  style='list-style-type: none; font-family: Verdana, Geneva, Tahoma, sans-serif;font-weight: bold;'   ><li style='margin-bottom : 1cm;margin-left: 0%;'> <strong style='margin-left: -5%;' >"+agence.nom+"</strong><span style='margin-right: -20%;margin-left: 5%;' >"+distance+"KM</span></li><li style=' display: flex;margin-bottom : 1cm;'  ><img style='margin-right: 5%;margin-left: -10%;' src='../customer-support.png' alt='test'><span style='margin-right : 5%;' >"+agence.bureauList[0].service+"</span><span id='1' >"+ agence.bureauList[0].bureau_disp+"%</span></li><li style='margin-bottom : 1cm;' ><img  style='margin-right: 5%;margin-left: -10%;' src='../customer-support.png' alt='test'><span style='margin-right : 5%;' >"+agence.bureauList[1].service+"</span><span id='2' >"+agence.bureauList[1].bureau_disp+"%</span></li><li style='margin-bottom : 0.5cm;' ><img  style='margin-right: 5%;margin-left: -10%;' src='../customer-support.png' alt='test'><span style='margin-right : 2%;' >"+agence.bureauList[2].service+"</span><span id='3' >"+agence.bureauList[2].bureau_disp+"%</span></li></ul><script >console.log('salam')</script>"
)
).addTo(map);
    

}

}


function errorLocation(){
  Setupmap([0,0]);
}














GetAgencies();


 
let found;

function Valid(lat,lng,latclicked,lngclicked){
 if(Math.abs(lat-latclicked)<=0.025 &&Math.abs(lng-lngclicked)<=0.025 )
     return true;
    
    return false; 
}


    

    
    let choice=document.getElementById('choice');
    document.getElementById('form').style.display="none";
   
    choice.addEventListener('click',()=>{
            map.on('click', function(e) {
 // The event object (e) contains information like the
 // coordinates of the point on the map that was clicked.
 
 let point=e.lngLat;
 console.log(point);
 found=false;
 for (const agence of agencies) {
  if(Valid(agence.latitude,agence.longitude,point.lat,point.lng)&&!found){
    
    document.getElementById('map').style.display="none";
    choice.style.display="none";
    document.getElementById('form').style.display="block";
    found=true;
    console.log(agence);
    SettingFormElements(agence);
    break;

 }
   
 }





 
    
 });

      
       
   });
   


   function SettingFormElements(Agency){
  
       const first_name=document.getElementById("first_name");
       const last_name=document.getElementById("last_name");
       const email=document.getElementById("email");
       const agency_name=document.getElementById("agency_name");
       const service=document.getElementById("service");
       const cin=document.getElementById('cin');

       agency_name.value=Agency.nom;
       agency_name.setAttribute('disabled',true);
       

       //this part commented can store bureaux ids into select options
       service.children[1].value=Agency.bureauList[0].bureauId;
       service.children[2].value=Agency.bureauList[1].bureauId;
       service.children[3].value=Agency.bureauList[2].bureauId;

       //confirm and delete button
       var submit=document.getElementById('change');
       const deleletbnt=document.getElementById('delete');
       
         
       

       

      
      
   
       //implements actions that should be executed when buttons are clicked
       deleletbnt.addEventListener('click',()=>{
        document.getElementById('map').style.display="block";
        choice.style.display="block";
        document.getElementById('form').style.display="none";
        found=false;
       });


       


       

       submit.addEventListener('click',()=>{
       

        
        /*
        let Reservation={
         
          horaire : null,
          bureau : {
                      service : service.value,
                      agence : {
                          id : Agency.id
                      }
          },
          client : {
                        nom : last_name.value,
                        prenom :first_name.value,
                        email :email.value
          },
            
      }
      console.log(Reservation);*/
      sessionStorage.setItem("cin", cin.value);
      sessionStorage.setItem("service", service.value);
      sessionStorage.setItem("agence_id", Agency.id);
      sessionStorage.setItem("nom", last_name.value);
      sessionStorage.setItem("prenom", first_name.value);
      sessionStorage.setItem("email", email.value);

      

      
       first_name.value="";

       cin.value="";
       first_name.value="";

       last_name.value="";
       email.value="";
       agency_name.value="";
       service.value="";
       
       

    });

    
  }
