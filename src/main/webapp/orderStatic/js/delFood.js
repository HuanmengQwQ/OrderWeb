//删除购物车菜单
function clearCartFood() {

    var b = window.confirm("你确定要删除吗？");

    //如果用户确定，就跳转到相对应的Servlet上
    if (b) {
        return true;
    } else {
        return false;
    }
}
