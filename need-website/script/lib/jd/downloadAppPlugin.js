(function() {
    var D = navigator.userAgent;
    var e = (D.match(/Chrome\/([\d.]+)/) || D.match(/CriOS\/([\d.]+)/)) ? true: false;
    var G = (D.match(/(Android);?[\s\/]+([\d.]+)?/)) ? true: false;
    var p = (D.match(/(iPad).*OS\s([\d_]+)/)) ? true: false;
    var x = (!p && D.match(/(iPhone\sOS)\s([\d_]+)/)) ? true: false;
    var f = navigator.userAgent.indexOf("MicroMessenger") >= 0;
    var t = false;
    var v = "plugIn_downloadAppPlugIn_loadIframe";
    var k = false;
    var c = 0;
    var b = {};
    var o = {};
    var A = null;
    var u = {};
    var d = window.Zepto || window.jQuery ? true: false;
    var n = [];
    function C() {
        WeixinJSBridge.invoke("getInstallState", {
            packageName: "com.jingdong.app.mall",
            packageUrl: "openApp.jdMobile://"
        },
        function(K) {
            var L = K.err_msg,
            s = 0;
            if (L.indexOf("get_install_state:yes") > -1) {
                t = true
            }
        })
    }
    function J(L, K, s) {
        if (d) {
            A("#" + L).bind(K, s)
        } else {
            A("#" + L).addEventListener(K, s, !1)
        }
    }
    function h(s) {
        var K = (s || "mGen") + (++c);
        return K
    }
    if (f) {
        if (window.WeixinJSBridge && WeixinJSBridge.invoke) {
            C()
        } else {
            document.addEventListener("WeixinJSBridgeReady", C, !1)
        }
    }
    if (window.$LAB) {
        $LAB.setOptions({
            AlwaysPreserveOrder: true
        }).script("http://h5.m.jd.com/active/track/mping.min.js")
    } else {
        var z = document.getElementsByTagName("script");
        var F = false;
        for (i = 0; i < z.length; i++) {
            if (z[i].src && z[i].src.indexOf("/active/track/mping.min.js") != -1) {
                F = true;
                break
            }
        }
        if (!F) {
            var q = document.createElement("script");
            q.type = "text/javascript";
            q.src = "http://h5.m.jd.com/active/track/mping.min.js";
            q.onerror = function() {
                l.removeChild(q)
            };
            var l = document.getElementsByTagName("head")[0];
            l.appendChild(q)
        }
    }
    if (d) {
        A = window.$;
        u = window.$
    } else {
        A = function(s) {
            if (typeof s == "object") {
                return s
            }
            return document.querySelector(s)
        };
        if (!window.$) {
            window.$ = u = A
        } else {
            u = window.$
        }
    }
    window.onblur = function() {
        for (var s = 0; s < n.length; s++) {
            clearTimeout(n[s])
        }
    };
    function E(L) {
        var K = document.cookie.indexOf(L + "=");
        if (K == -1) {
            return null
        }
        K = K + L.length + 1;
        var s = document.cookie.indexOf(";", K);
        if (s == -1) {
            s = document.cookie.length
        }
        return document.cookie.substring(K, s)
    }
    function r(L, N, s, O, M) {
        var P = L + "=" + escape(N);
        if (s != "") {
            var K = new Date();
            K.setTime(K.getTime() + s * 24 * 3600 * 1000);
            P += ";expires=" + K.toGMTString()
        }
        if (O != "") {
            P += ";path=" + O
        }
        if (M != "") {
            P += ";domain=" + M
        }
        document.cookie = P
    }
    function g(s) {
        var L = {
            downAppURl: "http://h5.m.jd.com/active/download/download.html?channel=jd-m",
            downAppIos: "http://union.m.jd.com/download/go.action?to=http%3A%2F%2Fitunes.apple.com%2Fcn%2Fapp%2Fid414245413&client=apple&unionId=12532&subunionId=m-top&key=e4dd45c0f480d8a08c4621b4fff5de74",
            downWeixin: "http://a.app.qq.com/o/simple.jsp?pkgname=com.jingdong.app.mall&g_f=991850",
            downIpad: "https://itunes.apple.com/cn/app/jing-dong-hd/id434374726?mt=8",
            inteneUrl: "openApp.jdMobile://360buy?type=1",
            inteneUrlParams: null,
            openAppBtnId: "",
            closePanelBtnId: "",
            closePanelId: "",
            closeCallblack: null,
            closeCallblackSource: null,
            cookieFlag: null,
            noRecord: false
        };
        if (s) {
            for (var K in s) {
                if (K && s[K]) {
                    L[K] = s[K]
                }
            }
        }
        return L
    }
    function w(M, s) {
        var Q = j(M);
        var N = null;
        if (f) {
            var K = null;
            if (t) {
                K = Q
            } else {
                K = M.downWeixin
            }
            location.href = K;
            return
        }
        if (p) {
            N = M.downIpad
        } else {
            if (x) {
                N = M.downAppIos
            } else {
                N = M.downAppURl
            }
        }
        if (e) {
            if (G) {
                var P = Q;
                Q = y(P)
            }
        }
        document.querySelector("#" + v).src = Q;
        var O = Date.now();
        if (s) {
            var L = setTimeout(function() {
                I(O, N)
            },
            500);
            n.push(L)
        }
    }
    function I(L, K) {
        var s = Date.now();
        if (L && (s - L) < (500 + 200)) {
            window.location.href = K
        }
    }
    function j(K) {
        var R = [];
        var N = K.inteneUrlParams;
        var P = {
            category: "jump",
            des: "productDetail",
            sourceType: "JSHOP_SOURCE_TYPE",
            sourceValue: "JSHOP_SOURCE_VALUE"
        };
        if (N) {
            for (var Q in N) {
                if (Q && N[Q]) {
                    R.push('"' + Q + '":"' + N[Q] + '"')
                }
            }
        } else {
            for (var Q in P) {
                if (Q && P[Q]) {
                    R.push('"' + Q + '":"' + P[Q] + '"')
                }
            }
        }
        try {
            R.push('"m_param":' + MPing.EventSeries.getSeries())
        } catch(O) {
            R.push('"m_param":null')
        }
        var L = "{" + R.join(",") + "}";
        var M = K.inteneUrl.split("?");
        var s = null;
        if (M.length == 2) {
            s = M[0] + "?" + M[1] + "&params=" + L
        } else {
            s = M[0] + "?params=" + L
        }
        return s
    }
    function y(s) {
        return "intent://m.jd.com/#Intent;scheme=" + s + ";package=com.jingdong.app.mall;end"
    }
    function H(s) {
        if (s.openAppBtnId) {
            b[s.openAppBtnId] = s;
            J(s.openAppBtnId, "click",
            function() {
                var N = this.getAttribute("id");
                var K = b[N];
                if (!k) {
                    var L = document.createElement("iframe");
                    L.id = v;
                    document.body.appendChild(L);
                    document.getElementById(v).style.display = "none";
                    document.getElementById(v).style.width = "0px";
                    document.getElementById(v).style.height = "0px";
                    k = true
                }
                var M = K.cookieFlag ? "downloadAppPlugIn_downCloseDate_" + K.cookieFlag: "downloadAppPlugIn_downCloseDate";
                r(M, Date.now() + "_2592000000", 60, "/", "m.jd.com");
                B("MDownLoadFloat_OpenNow");
                w(K, true)
            })
        }
    }
    function a(L) {
        if (L.closePanelBtnId && L.closePanelId) {
            b[L.closePanelBtnId] = L;
            var P = L.cookieFlag ? "downloadAppPlugIn_downCloseDate_" + L.cookieFlag: "downloadAppPlugIn_downCloseDate";
            var N = E(P);
            var O = null;
            if (N) {
                O = N.split("_");
                if (O.length == 2) {
                    O[0] = parseInt(O[0], 10);
                    O[1] = parseInt(O[1], 10)
                } else {
                    O = null
                }
            }
            var K = Date.now();
            if (!L.noRecord && O && O.length == 2 && (K - O[0]) < O[1]) {
                document.querySelector("#" + L.closePanelId).style.display = "none";
                if (L.closeCallblack) {
                    var M = L.closeCallblackSource ? L.closeCallblackSource: null;
                    L.closeCallblack.call(M)
                }
                return
            } else {
                document.querySelector("#" + L.closePanelId).style.display = "block"
            }
            J(L.closePanelBtnId, "click",
            function() {
                B("MDownLoadFloat_Close");
                var T = this.getAttribute("id");
                var Q = b[T];
                var S = Q.cookieFlag ? "downloadAppPlugIn_downCloseDate_" + Q.cookieFlag: "downloadAppPlugIn_downCloseDate";
                if (!Q.noRecord) {
                    r(S, Date.now() + "_259200000", 60, "/", "m.jd.com")
                }
                document.querySelector("#" + Q.closePanelId).style.display = "none";
                if (Q.closeCallblack) {
                    var R = Q.closeCallblackSource ? Q.closeCallblackSource: null;
                    Q.closeCallblack.call(R)
                }
            })
        }
    }
    function B(K) {
        try {
            var L = new MPing.inputs.Click(K);
            L.event_param = "";
            var s = new MPing();
            s.send(L)
        } catch(M) {}
    }
    function m(s) {
        var K = g(s);
        H(K);
        a(K)
    }
    u.downloadAppPlugIn = m;
    u.downloadAppPlugInOpenApp = function(s) {
        var K = g(s);
        w(K)
    }
})();