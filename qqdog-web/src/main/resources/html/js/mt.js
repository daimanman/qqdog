var _$ = jQuery.noConflict(true);

    var parseCityInfo = function(){
      var cityInfo = {};
       cityInfo["ci"] = window._appState["ci"];
        cityInfo["cityName"] = window._appState["cityName"];
        cityInfo["areas"] = window._appState["filters"]["areas"];
        return cityInfo;
    }


    var ip = "http://192.168.1.193:54321/";
   





     0 && _$.ajax({
         url:ip+"mt/data",
         type:"post",
         dataType:"json",
         data:JSON.stringify(window._appState),
         contentType:"application/json;charset=utf-8",
         success:function(data){
            console.info("is return ");
             setTimeout(function(){
                 console.info("3s 之后");
                 // _$(".pagination li:last").click()
             },3000);
         }
     });
    console.info(window._appState)
    console.info(parseCityInfo())

    var rndNum = function(begin,end){
        var num = Math.round(Math.random()*(end-begin)+begin);
        return num;
    }
    var num = rndNum(2,3);
    if(window._appState){
        var cityInfo = parseCityInfo();
        var curHref = window.location.href;
        if(curHref.indexOf("https") >= 0){
             window.location.href = curHref.replace("https","http");
            return;
        }
            _$.ajax({
                url:ip+"mt/parseCityArea",
                type:"post",
                data:JSON.stringify(cityInfo),
                contentType:"application/json;charset=utf-8",
                success:function(data){
                    console.info(data);
                    if(data["nextUrl"]){
                             setTimeout(function(){
                                 console.info(num+" s after ");
                                 // _$(".pagination li:last").click()
                                  window.location.href = data["nextUrl"];
                             },num*1000);
                    }
                }
            });
    }else{
      alert("ou My God ,is not allow Craw")
    }