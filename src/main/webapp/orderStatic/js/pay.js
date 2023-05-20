function pay() {
    var username = document.getElementById("username").textContent;
    console.log(username);
    $.ajax({
        url: "http://localhost:8080/OrderTest/pay",
        type: "POST",
        data: {"username": username},
        dataType: "text",
        success: function (response) {
            alert("支付成功！");
            console.log(response);
            window.location.href = "http://localhost:8080/OrderTest/order";
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // 处理错误
            alert("支付失败！请查看控制台输出信息");
            console.log(textStatus, errorThrown);
        }
    })
}