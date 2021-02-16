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
 



//add a marker in a specified location with red color

async function GetAgencies(){
  
 

  //const data=await fetch('http://localhost:8080/agencies');
  //const agencies=await data.json();
  //console.log(agencies);


   //create map object with a defined center and a zoom 
var map = new mapboxgl.Map({
  container: 'map',
  style: 'mapbox://styles/mapbox/streets-v11',
  center :[0,0],
  zoom : 8
});

//add controlle buttons zoom in, zoom out
map.addControl(new mapboxgl.NavigationControl());


  var marker = new mapboxgl.Marker({
    color: "#00FE00",
    draggable: true
    }).setLngLat([0,0])
    .setPopup(new mapboxgl.Popup()
    .setHTML(
      "<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width=device-width, initial-scale=1.0'><style>body {margin : 0px 0px 0px 0px; font-family: Verdana, Geneva, Tahoma, sans-serif;font-size:medium;font-weight: bold;border: solid 1px black;"+
      "}div,li {display: flex;justify-content: space-around;border: solid 1px blueviolet;}ul{list-style-type: none;}"+
      "</style><title>PipPup</title></head><body  >"+
      "<ul  ><li><img src='customer-support.png' alt='test'>service 1<span id='1' > 10%</span></li><li><img src='customer-support.png' alt='test'>service 2<span id='2' >Avalaibility : 20%</span></li><li><img src='customer-support.png' alt='test'>service 3<span id='3' >Avalaibility : 50%</span></li><li><input id='reserver' type='button' value='Reserver' /></li></ul>"+
      "</body></html>"
      )
      )
    .addTo(map);
    
 
}
GetAgencies();


