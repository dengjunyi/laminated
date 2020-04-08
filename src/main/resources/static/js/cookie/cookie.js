/*
//获得coolie 的值


a = cookie("the_cookie");
alert("1"+cookie("the_cookie"))
c_start = document.cookie.indexOf("the_cookie=");
if (c_start == -1) {
    alert("1"+a);
    alert(a);
    alert("对不起，你还没有登录!")
    window.location.href = "/login";
} else {
    if (a == null || a == "" || a == "null" || typeof (a) == "undefined" || a == false || a == undefined) {
        alert("2"+a);
        alert("对不起，你还没有登录!")
        window.location.href = "/login";
    }
}*/

/*alert("js" + $.cookie('the_cookie'));

function cookie(name) {
    var cookieArray = document.cookie.split("; "); //得到分割的cookie名值对
    var cookie = new Object();
    for (var i = 0; i < cookieArray.length; i++) {
        var arr = cookieArray[i].split("=");       //将名和值分开
        if (arr[0] == name) return unescape(arr[1]); //如果是指定的cookie，则返回它的值
    }
    return "";
}

$(document).ready(function () {
    var a = cookie("the_cookie");
    if (a == null || a == "" || a == "null" || typeof (a) == "undefined" || a == false || a == undefined) {
        alert("对不起，你还没有登录!")
        window.location.href = "/login";
    }else {
        alert("已登录")
    }
});*/
(function () {
    var ie = !!(window.attachEvent && !window.opera);
    var wk = /webkit\/(\d+)/i.test(navigator.userAgent) && (RegExp.$1 < 525);
    var fn = [];
    var run = function () {
        for (var i = 0; i < fn.length; i++) fn[i]();
    };
    var d = document;
    d.ready = function (f) {
        if (!ie && !wk && d.addEventListener)
            return d.addEventListener('DOMContentLoaded', f, false);
        if (fn.push(f) > 1) return;
        if (ie)
            (function () {
                try {
                    d.documentElement.doScroll('left');
                    run();
                } catch (err) {
                    setTimeout(arguments.callee, 0);
                }
            })();
        else if (wk)
            var t = setInterval(function () {
                if (/^(loaded|complete)$/.test(d.readyState))
                    clearInterval(t), run();
            }, 0);
    };
})();

$(document).ready(function () {
    var $color = sessionStorage.username;
    //alert("666"+$color)
    if ($color == null || $color == "" || $color == "null" || typeof ($color) == "undefined" || $color == undefined) {
        alert("对不起，你还没有登录!")
        window.location.href = "/login";
    } else {
        // alert("已登录")
    }
});

function sounds(name) {
    $.post("/sound", {name: name}, function (data) {
    })
}

//关装浏览器清除cookie
$(window).unload(function () {
    // $.cookie('the_cookie', null);
    sessionStorage.removeItem("mycolor");
    alert("再见了!");
});



//禁止页面刷新
function refresh() {
    document.onkeydown = function(e) {
        e = window.event || e;
        var k = e.keyCode;
        //屏蔽ctrl+R，F5键，ctrl+F5键  F3键！验证
        if ((e.ctrlKey == true && k == 82) || (k == 116)
            || (e.ctrlKey == true && k == 116)||k==114) {
            e.keyCode = 0;
            alert("当前页面不能刷新！");
            e.returnValue = false;
            e.cancelBubble = true;
            return false;

        }
        if (k == 8) {
            alert("不能返回或后退！");
            e.keyCode = 0;
            e.returnValue = false;
            return false;
        }
        //屏蔽 Ctrl+n   验证可以实现效果
        if (e.ctrlKey && k == 78){
            e.keyCode = 0;
            e.returnValue = false;
            e.cancelBubble = true;
            return false;
        }
        //屏蔽F11   验证可以实现效果
        if (k == 122) {
            e.keyCode = 0;
            e.returnValue = false;
            e.cancelBubble = true;
            return false;
        }
        //屏蔽 shift+F10  验证可以实现效果
        if ((e.shiftKey && k == 121)||(e.ctrlKey && k == 121)){
            e.keyCode = 0;
            e.returnValue = false;
            e.cancelBubble = true;
            return false;
        }

        //屏蔽Alt+F4
        if ((e.altKey) && (k== 115)) {
            window.showModelessDialog("about:blank", "",
                "dialogWidth:1px;dialogheight:1px");
            e.keyCode = 0;
            e.returnValue = false;
            e.cancelBubble = true;
            return false;
        }
        //屏蔽 Alt+ 方向键 ← ;屏蔽 Alt+ 方向键 → ！验证
        if ((e.altKey)
            && ((k == 37) || (k == 39))) {
            alert("不准你使用ALT+方向键前进或后退网页！");
            e.keyCode = 0;
            e.returnValue = false;
            e.cancelBubble = true;
            return false;
        }

    };

    //屏蔽右键菜单，！验证
    document.oncontextmenu = function(event) {
        if (window.event) {
            event = window.event;
        }
        try {
            var the = event.srcElement;
            if (!((the.tagName == "INPUT" && the.type.toLowerCase() == "text") || the.tagName == "TEXTAREA")) {
                return false;
            }
            return true;
        } catch (e) {
            return false;
        }
    };
}


