function timenow() {
    var myDate = new Date();
    var times = myDate.toLocaleString( );
    return times
}

document.getElementById('systime').innerHTML = timenow();
screenFuc();
function screenFuc() {
    var topHeight = $(".chatBox-head").innerHeight();//聊天头部高度
    //屏幕小于768px时候,布局change
    var winWidth = $(window).innerWidth();
    if (winWidth <= 768) {
        var totalHeight = $(window).height(); //页面整体高度
        $(".chatBox-info").css("height", totalHeight - topHeight);
        var infoHeight = $(".chatBox-info").innerHeight();//聊天头部以下高度
        //中间内容高度
        $(".chatBox-content").css("height", infoHeight - 46);
        $(".chatBox-content-demo").css("height", infoHeight - 46);

        $(".chatBox-list").css("height", totalHeight - topHeight);
        $(".chatBox-kuang").css("height", totalHeight - topHeight);
        $(".div-textarea").css("width", winWidth - 106);
    } else {
        $(".chatBox-info").css("height", 495);
        $(".chatBox-content").css("height", 448);
        $(".chatBox-content-demo").css("height", 448);
        $(".chatBox-list").css("height", 495);
        $(".chatBox-kuang").css("height", 495);
        $(".div-textarea").css("width", 260);
    }
}
(window.onresize = function () {
    screenFuc();
})();
//未读信息数量为空时
var totalNum = $(".chat-message-num").html();
if (totalNum == "") {
    $(".chat-message-num").css("padding", 0);
}
$(".message-num").each(function () {
    var wdNum = $(this).html();
    if (wdNum == "") {
        $(this).css("padding", 0);
    }
});


//打开/关闭聊天框
$(".chatBtn").click(function () {
    $(".chatBox").toggle(10);
})
$(".chat-close").click(function () {
    $(".chatBox").toggle(10);
})
//进聊天页面
$(".chat-list-people").each(function () {
    $(this).click(function () {
        var n = $(this).index();
        $(".chatBox-head-one").toggle();
        $(".chatBox-head-two").toggle();
        $(".chatBox-list").fadeToggle();
        $(".chatBox-kuang").fadeToggle();

        //传名字
        $(".ChatInfoName").text($(this).children(".chat-name").children("p").eq(0).html());

        //传头像
        $(".ChatInfoHead>img").attr("src", $(this).children().eq(0).children("img").attr("src"));

        //聊天框默认最底部
        $(document).ready(function () {
            $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
        });
    })
});

//返回列表
$(".chat-return").click(function () {
    $(".chatBox-head-one").toggle(1);
    $(".chatBox-head-two").toggle(1);
    $(".chatBox-list").fadeToggle(1);
    $(".chatBox-kuang").fadeToggle(1);
});

//      发送信息
$("#chat-fasong").click(function () {
    var textContent = $(".div-textarea").html().replace(/[\n\r]/g, '<br>')
    if (textContent != "") {
        $(".chatBox-content-demo").append("<div class=\"clearfloat\">" +
            "<div class=\"author-name\"><small class=\"chat-date\" id=\"timenow\"></small> </div> " +
            "<div class=\"right\"> <div class=\"chat-message\"> " + textContent + " </div> " +
            "<div class=\"chat-avatars\"><img src=\"static/images/icon01.png\" alt=\"头像\" /></div> </div> </div>");
        //发送后清空输入框
        $(".div-textarea").html("");
        //聊天框默认最底部
        $(document).ready(function () {
            $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
        });
        document.getElementById('timenow').innerHTML = timenow();

        if(textContent.indexOf("在线客服联系方式是多少") != "-1"){
            var re = "QQ：1078xxxxxx <br/> Wechat: WAOWAOWAO";
            reply(re);
        }
        else if(textContent.indexOf("跪求豪哥哥联系方式") != "-1"){
            var re = "打钱就给";
            reply(re);
        }
        else if(textContent.indexOf("豪哥哥可爱么") != "-1"){
            var re = "菊花送上";
            reply(re);
        }
        else if(textContent.indexOf("豪哥哥可爱4") != "-1"){
            var re = "还用问！豪哥哥可爱";
            reply(re);
        }
        else{
            var re = "请输入正确的关键字：<br> 1.在线客服联系方式是多少<br> 2.跪求豪哥哥联系方式 <br>3.豪哥哥可爱3<br> 4.豪哥哥可爱4";
            reply(re);
        }

    }
});

//      发送表情
$("#chat-biaoqing").click(function () {
    $(".biaoqing-photo").toggle();
});
$(document).click(function () {
    $(".biaoqing-photo").css("display", "none");
});
$("#chat-biaoqing").click(function (event) {
    event.stopPropagation();//阻止事件
});

$(".emoji-picker-image").each(function () {
    $(this).click(function () {
        var bq = $(this).parent().html();
        console.log(bq)
        $(".chatBox-content-demo").append("<div class=\"clearfloat\">" +
            "<div class=\"author-name\"><small class=\"chat-date\" id=\"timenow\"></small> </div> " +
            "<div class=\"right\"> <div class=\"chat-message\"> " + bq + " </div> " +
            "<div class=\"chat-avatars\"><img src=\"static/images/icon01.png\" alt=\"头像\" /></div> </div> </div>");
        //发送后关闭表情框
        $(".biaoqing-photo").toggle();
        //聊天框默认最底部
        $(document).ready(function () {
            $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
        });
        document.getElementById('timenow').innerHTML = timenow();
    })
});
//自动回复
function reply(textContent) {
    if (textContent != "") {
        $(".chatBox-content-demo").append("<div class=\"clearfloat\">" +
            "<div class=\"author-name\"><small class=\"chat-date\" id=\"timenow\"></small> </div> " +
            "<div class=\"left\"><div class=\"chat-avatars\"><img src=\"static/images/icon01.png\" alt=\"头像\" /></div>  " +
            "<div class=\"chat-message\"> " + textContent + " </div> </div> </div>");
        //发送后清空输入框
        //  $(".div-textarea").html("");
        //聊天框默认最底部
        $(document).ready(function () {
            $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
        });
        document.getElementById('timenow').innerHTML = timenow();
    }
}
//      发送图片
function selectImg(pic) {
    if (!pic.files || !pic.files[0]) {
        return;
    }
    var reader = new FileReader();
    reader.onload = function (evt) {
        var images = evt.target.result;
        $(".chatBox-content-demo").append("<div class=\"clearfloat\">" +
            "<div class=\"author-name\"><small class=\"chat-date\" id=\"timenow\"></small> </div> " +
            "<div class=\"right\"> <div class=\"chat-message\"><img src=" + images + "></div> " +
            "<div class=\"chat-avatars\"><img src=\"static/images/icon01.png\" alt=\"头像\" /></div> </div> </div>");
        //聊天框默认最底部
        $(document).ready(function () {
            $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
        });
        document.getElementById('timenow').innerHTML = timenow();
    };

    reader.readAsDataURL(pic.files[0]);

}