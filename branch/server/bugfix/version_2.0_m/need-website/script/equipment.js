(function(){
    var e = {
        isPc: true,
        handleEquipment: function() {
            var userAgentInfo = navigator.userAgent;  
            var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPod");  
            var flag = true;  
            for (var v = 0; v < Agents.length; v++) {  
                if (userAgentInfo.indexOf(Agents[v]) > 0) { flag = false; break; }  
            }  
            e.isPc = flag;
        },
        handleLocatioin: function(){
            var href = window.location.href;
            var last = href.split('/');
                last = last[last.length-1];
            if( e.isPc ){
                if( href == 'https://www.needapp.cn/' || href == 'http://www.needapp.cn/' || href == 'www.needapp.cn' || href == 'needapp.cn' || last == 'index.html'){
                    return;
                }else{
                    window.location.replace('https://www.needapp.cn');
                }
            }else{
                if( href == 'http://m.needapp.cn/' || href == 'm.needapp.cn' || last == 'm_index.html'){
                    return;
                }else{
                    window.location.replace('http://m.needapp.cn');
                }
            }
        },
        change: function() {
            e.handleEquipment();
            // var width = document.documentElement,t;
            // if( width < 750 && e.isPc){
            //     e.isPc = false;
            // }else{
            //     e.isPc = true;
            // }
            e.handleLocatioin();
        },
        bindEvent: function() {
            var e = this;
            window.onresize = function() {
                clearTimeout(e.timeroutID),
                e.timeroutID = setTimeout(e.change, 100)
            }
        },
        init: function() {
            e.handleEquipment();
            e.handleLocatioin();
            e.bindEvent();
        }
    };
    e.init()
})(); 