function showNotice(){
    layer.open({
        type: 1,
        title: "系统公告",
        area: '300px',
        shade: 0.8,
        id: 'LAY_layuipro',
        btn: ['确认收到'],
        moveType: 1,
        content: '<div style="padding:15px 20px; text-align:justify; line-height: 22px; text-indent:2em;border-bottom:1px solid #e2e2e2;"><p class="layui-red">暂无最新系统通知</p></pclass></p></div>',
        success: function(layero){
            var btn = layero.find('.layui-layer-btn');
            btn.css('text-align', 'center');
            btn.on("click",function(){
                layer.tips('谢谢配合！', 'body > div.layui-layout.layui-layout-admin > div.layui-header > ul > li.layui-nav-item.layui-this > a', {
                    tips: 3,
                    time : 1000
                });
            });
        },
        cancel: function(index, layero){
            layer.tips('下次请记得点击确认收到哦！', 'body > div.layui-layout.layui-layout-admin > div.layui-header > ul > li.layui-nav-item.layui-this > a', {
                tips: 3,
                time : 1000
            });
        }
    });
}