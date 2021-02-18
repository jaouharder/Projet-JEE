mapboxgl.accessToken = 'pk.eyJ1IjoieGVub3Bob2JlIiwiYSI6ImNrbDZwdHhpaTByZ2QycXFwdXk1bTNnY2kifQ.MVEO4m4i88F8hNRaIAXXaA';


//navigator.geolocation.getCurrentPosition(successLocation,errorLocation,{enableHighAccuracy:true});
/*

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
 zoom : 7
});

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
    .setPopup(new mapboxgl.Popup().setHTML("<h1>Hello World!</h1>"))
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

"<ul  style='list-style-type: none; font-family: Verdana, Geneva, Tahoma, sans-serif;font-weight: bold;'   ><li style='margin-bottom : 1cm;margin-left: -10%;'> <strong style='margin-left: -10%;' >"+agence.nom+"</strong><span style='margin-right: -20%;margin-left: 20%;' >"+distance+"KM</span></li><li style=' display: flex;margin-bottom : 1cm;'  ><img style='margin-right: 10%;margin-left: -20%;' src='../customer-support.png' alt='test'><span style='margin-right : 10%;' >"+agence.bureauList[0].service+"</span><span id='1' >"+ agence.bureauList[0].bureau_disp+"%</span></li><li style='margin-bottom : 1cm;' ><img  style='margin-right: 10%;margin-left: -20%;' src='../customer-support.png' alt='test'><span style='margin-right : 10%;' >"+agence.bureauList[1].service+"</span><span id='2' >"+agence.bureauList[1].bureau_disp+"%</span></li><li style='margin-bottom : 0.5cm;' ><img  style='margin-right: 10%;margin-left: -20%;' src='../customer-support.png' alt='test'><span style='margin-right : 10%;' >"+agence.bureauList[2].service+"</span><span id='3' >"+agence.bureauList[2].bureau_disp+"%</span></li><li style='margin-left : 20%;'><input id='reserver' agence='181' type='button' value='Reserver' /></li></ul><script src='mapscript.js'></script>"
)
).addTo(map);
    

}

}




function errorLocation(){
  Setupmap([0,0]);
}












GetAgencies();


