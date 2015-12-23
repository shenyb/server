(function(){
    var e = {
        timeroutID: null,
        change: function() {
            var e = document.documentElement,t;
            if( e.clientWidth < 320 ){
                t = 320;
            }else if( e.clientWidth > 750 ){
                t = 750;
            }else{
                t = e.clientWidth;
            }
            e.style.fontSize = t / 22.857142857 + "px"
        },
        bindEvent: function() {
            var e = this;
            window.onresize = function() {
                clearTimeout(e.timeroutID),
                e.timeroutID = setTimeout(e.change, 100)
            }
        },
        init: function() {
            this.bindEvent(),
            this.change()
        }
    };
    e.init()
})();