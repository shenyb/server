// var log = function (msg) {
//  2     $('body').before('<div class="log">' + msg + '</div>');
//  3 };
//  4 var timeout, t = 1000, hasApp = true;
//  5 setTimeout(function () {
//  6     if (hasApp) {
//  7         log('安装了app');
//  8         $('#dl_app').hide();
//  9 
// 10     } else {
// 11         log('未安装app');
// 12         $('#dl_app').show();
// 13         log('开始强制下载');
// 14         forceDownload();
// 15     }
// 16 }, 2000)
// 17 function testApp() {
// 18     var t1 = Date.now();
// 19     var ifr = $('<iframe id="ifr"></iframe>')
// 20     ifr.attr('src', '您们app的协议');
// 21     $('body').append(ifr);
// 22     timeout = setTimeout(function () {
// 23         try_to_open_app(t1);
// 24     }, t);
// 25 }
// 26 function try_to_open_app(t1) {
// 27     var t2 = Date.now();
// 28     if (!t1 || t2 - t1 < t + 200) {
// 29         hasApp = false;
// 30     }
// 31 }
// 32 testApp();

// var e = {
//     timeout: {},
//     t: 1000,
//     hasApp: true,
//     log: function( _msg ){
//         $('body').before('<div class="log">' + _msg + '</div>');
//     },
//     testApp: function(){
//         var t1 = Date.now();
//         var ifr = $('<iframe id="ifr"></iframe>')
//         ifr.attr('src', 'weplanterneedapp://shop?goodsId=12345678');
//         $('body').append(ifr);
//         timeout = setTimeout(function () {
//             try_to_open_app(t1);
//         }, t);
//     },
//     try_to_open_app: function( _t1 ){
//         var t2 = Date.now();
//         if(!t1 || t2 - t1 < t + 200) {
//             e.hasApp = false;
//         }
//     },
//     init: function(){
//         setTimeout(function () {
//             if (e.hasApp) {
//                 log('安装了app');
//                 // $('#dl_app').hide();
//             }else{
//                 log('未安装app');
//                 // $('#dl_app').show();
//                 log('开始强制下载');
//                 // forceDownload();
//             }
//         }, 2000);
//         e.testApp();
//     }
// }
// e.init()

function testApp( url ) {
    var timeout, t = 1000, hasApp = true;
    setTimeout(function () {
        if (hasApp) {
            alert('安装了app');
        } else {
            alert('未安装app');
        }
        document.body.removeChild(ifr);
    }, 2000)

    var t1 = Date.now();
    var ifr = document.createElement("iframe");
    ifr.setAttribute('src', url);
    ifr.setAttribute('style', 'display:none');
    document.body.appendChild(ifr);
    timeout = setTimeout(function () {
         var t2 = Date.now();
         if (!t1 || t2 - t1 < t + 100) {
             hasApp = false;
         }
    }, t);
}