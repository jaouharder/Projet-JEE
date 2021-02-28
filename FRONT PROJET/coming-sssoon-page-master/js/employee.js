$(document).ready(function() {
        var recdate=new Date();
        var DayRes = new Array();
        var editedDate=new Date();
        var len;
        var brId= sessionStorage.getItem("id_bureau");
    async function GetReservations(BureauID){
		const data=await fetch('http://localhost:8080/reservation/bureau/'+BureauID);
		const reservations=await data.json();
        (recdate.getMinutes()>30) ? editedDate.setMinutes(30) : editedDate.setMinutes(0) ;
        let hours;
		for(var res of reservations){
			  let resyear= Number(res.horaire.charAt(0)+res.horaire.charAt(1)+res.horaire.charAt(2)+res.horaire.charAt(3));
			  let resmth=Number(res.horaire.charAt(5)+res.horaire.charAt(6));
			  let resday=Number(res.horaire.charAt(8)+res.horaire.charAt(9));
              hours = Number(res.horaire.charAt(11)+res.horaire.charAt(12));
              if(resyear==editedDate.getFullYear() && resmth==(editedDate.getMonth()+1) && resday==editedDate.getDate() && hours >= editedDate.getHours())   { 
                  DayRes.push(res);
                  console.log(res);
              }
        }

        DayRes.sort((a, b) => (a.horaire > b.horaire) ? 1 : -1);
        len=DayRes.length;
        if (len !== 0){
            let old_hrs = Number(DayRes[indexes].horaire.charAt(11)+DayRes[indexes].horaire.charAt(12));
            let old_mnts = Number(DayRes[indexes].horaire.charAt(14)+DayRes[indexes].horaire.charAt(15));
            document.getElementById("cin_current").value=old_hrs+':'+old_mnts+' | '+DayRes[indexes].client.cin;
            if(indexes+1 !== len){
                let hrs=Number(DayRes[indexes+1].horaire.charAt(11)+DayRes[indexes+1].horaire.charAt(12));
                let mnts=Number(DayRes[indexes+1].horaire.charAt(14)+DayRes[indexes+1].horaire.charAt(15));
                document.getElementById("cin_next").value = hrs+':'+mnts+' | '+DayRes[indexes+1].client.cin;
                indexes++;
            }
            else {
                document.getElementById("cin_next").value = 'NO RESERVATION LEFT';
        }
        }
        else {
            document.getElementById("cin_next").value = 'Free as wind';
            document.getElementById("cin_current").value = 'Free as wind';
        }

    
    } 
    var indexes=1;
    document.getElementById("next").addEventListener("click", async function() {
        let old_hrs = Number(DayRes[indexes].horaire.charAt(11)+DayRes[indexes].horaire.charAt(12));
        let old_mnts = Number(DayRes[indexes].horaire.charAt(14)+DayRes[indexes].horaire.charAt(15));
        document.getElementById("cin_current").value=old_hrs+':'+old_mnts+' | '+DayRes[indexes].client.cin;
        if(indexes+1 !== len){
            let hrs=Number(DayRes[indexes+1].horaire.charAt(11)+DayRes[indexes+1].horaire.charAt(12));
            let mnts=Number(DayRes[indexes+1].horaire.charAt(14)+DayRes[indexes+1].horaire.charAt(15));
            document.getElementById("cin_next").value = hrs+':'+mnts+' | '+DayRes[indexes+1].client.cin;
            indexes++;
        }
        else {
            document.getElementById("cin_next").value = 'NO RESERVATION LEFT';
    }
    
        let duree = (new Date()-recdate)/600;
        recdate=new Date();
    });
    
    GetReservations(brId);
});