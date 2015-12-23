(function(){
    var e = {
        browser: {
            versions: function(){
                var u = navigator.userAgent, 
                    app = navigator.appVersion;
                return {
                    trident: u.indexOf('Trident') > -1, //IE内核
                    presto: u.indexOf('Presto') > -1, //opera内核
                    webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                    gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
                    mobile: !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/), //是否为移动终端
                    ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                    android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
                    iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
                    iPad: u.indexOf('iPad') > -1, //是否iPad
                    webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
                };
            }(),
            language: (navigator.browserLanguage || navigator.language).toLowerCase()
        },
        isWeiXin: function(){
            var ua = window.navigator.userAgent.toLowerCase(); 
            if( ua.match(/MicroMessenger/i) == 'micromessenger'){ 
                return true; 
            }else{ 
                return false; 
            } 
        },
        handleClient: function(){
            if( e.browser.versions.ios || e.browser.versions.iPhone || e.browser.versions.iPad ) {
                $('#iphone-btn').show();
                $('#android-btn').hide();
            }else if( e.browser.versions.android ){
                $('#iphone-btn').hide();
                $('#android-btn').show();
            }
        },
        addEvent: function(){
            $('#android-btn').on('click',function(){
                window.location = "http://a.app.qq.com/o/simple.jsp?pkgname=com.needapp.need";
            });
            $('#iphone-btn').on('click',function(){
                window.location = "http://a.app.qq.com/o/simple.jsp?pkgname=com.needapp.need";
                // if ( e.isWeiXin() ) {
                //     $('#masklayer').show();
                // }else{
                //     window.location = "https://itunes.apple.com/app/id1033089957";
                // }
            });
            $('#masklayer').on('click',function(){
                $(this).hide();
            });
        },
        init: function() {
            window.onload = function(){
                e.addEvent();
                e.handleClient();
            }
        }
    };
    e.init()
})(); 