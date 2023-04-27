function changeNote() {
    $.ajax({                                    //调用ajax发送数据
        type: "post",                             //发送方式,post或者get
        url: "http://localhost:8080/OrderTest/adminIndex",                          //发送给后台的url声明
        success: function () {               //发送成功并且成功获得后台响应的时候，
            alert("修改成功！"); //执行的操作
        },
        error: function (e) {                    //发送失败了该执行的操作
            alert("发生未知错误");
        }
    })
}