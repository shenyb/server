var e = {
    getParams: function(){
        console.info(window.location.href);
        var url = window.location.href.split("?")[1];
        var theRequest = new Object();
        var strs = url.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
        return theRequest;
    },
    handleUrl: function(){
        var request = e.getParams();
        if( request.url ){
            window.location.replace( request.url );
        }
    },
    isWeiXin: function(){
        var ua = window.navigator.userAgent.toLowerCase(); 
        if( ua.match(/MicroMessenger/i) == 'micromessenger'){ 
            return true; 
        }else{ 
            return false; 
        } 
    },
    addEvent: function(){
        $('#ios-btn').on('click',function(){
            if( e.isWeiXin() ){
                $('#masklayer').show();
            }else{
                window.location = "https://itunes.apple.com/app/id1033089957";
            }
        });
        $('#android-btn').on('click',function(){
            
        });
        $('#masklayer').on('click',function(){
            $(this).hide();
        });
    },
    init: function(){
        window.onload = function(){
            e.addEvent();
        }
    }
}
e.init()