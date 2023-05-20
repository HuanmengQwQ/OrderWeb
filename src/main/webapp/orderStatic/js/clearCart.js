$(".clear-all").click(function () {
    var username = document.getElementById("username").textContent;


    if (confirm("确认清除购物车？")) {
        $.ajax({
            url: "http://localhost:8080/OrderTest/clearCart",
            type: "POST",
            data: {"username": username},
            dataType: "text",
            success: function (response) {
                // 处理服务器响应
                alert("删除成功！");
                console.log(response);
                $(".cart-item").remove();
                $(".totalPrice").text("￥0.00")
            },
            error: function (jqXHR, textStatus, errorThrown) {
                // 处理错误
                alert("删除失败！请查看控制台输出信息");
                console.log(textStatus, errorThrown);
            }

        })
    }
})