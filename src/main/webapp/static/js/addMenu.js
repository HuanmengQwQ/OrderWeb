// function addMenu() {
//     // m_name = $('NameOfDish').val();
//     // m_price = $('DishPrice').val();
//     //
//     // if(m_name === "" && m_price === ""){
//     //     alert("菜名和价格不能为空！");
//     //     return;
//     // }
//
//     $.ajax({
//         data : $('#DishForm').serialize(),
//         dataType:"json",                                    //调用ajax发送数据
//         type: "POST",                             //发送方式,post或者get
//         url: "http://localhost:8080/OrderTest/addMenu",                          //发送给后台的url声明
//         success: function (data) {               //发送成功并且成功获得后台响应的时候，
//             // if(data.code === 200){
//             //     alert("添加成功！"); //执行的操作
//             // }else if(data.code === 400){
//             //     alert("错误！");
//             // }else if(data.code === 500){
//             //     alert("数据库插入错误！");
//             // }
//             var message = data;
//             alert(message);
//         },
//         error: function (e) {                    //发送失败了该执行的操作
//             var message = data;
//             alert(message);
//             // alert("异常错误");
//         }
//     })
// }

// function check(form) {
//
//
//     if (m_name === "" && m_price === ""){
//         alert('菜名和价格不能为空！');
//         return false;
//     }
//     else{
//         return true;
//     }
// }

$(function () {
    //绑定按钮的点击事件
    $("#submitBtn").on("click", function () {
        //获取表单数据
        var formData = $("#DishForm").serialize();
        //发送ajax请求
        $.ajax({
            url: "http://localhost:8080/OrderTest/addMenu", //后台处理的url
            type: "POST", //请求方式
            data: formData, //发送的数据
            dataType: "json", //指定返回的数据类型为json
            success: function (data) {
                //请求成功，获取响应数据
                var response = data;
                //判断是否提交成功
                if (response.status === "success") {
                    //提交成功，显示提示信息
                    alert(response.message);
                } else {
                    //提交失败，显示错误信息
                    alert(response.error);
                }
            },
            error: function () {
                //请求失败，处理错误信息
                alert("请求失败");
            }
        });
    });
});