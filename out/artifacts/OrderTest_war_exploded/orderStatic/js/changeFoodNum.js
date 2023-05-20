//改变购物车菜品的数量

$(function (){
    let oldValue = 0;
    $(".quantity").focus(
        function saveoldValue(input){
            oldValue = this.value;
        }
    )


    $(".quantity").change(
        function update(input) {

            //获取得到输入框的数据
            var quantity = this.value;

            let id = this.getAttribute('id');


            //询问用户是否真的修改
            var b = window.confirm("你确定修改吗？");

            //如果确定修改，就发送Ajax请求
            if(b) {
                var data = {
                    mid: id,
                    quantity: quantity
                };

                $.ajax({
                    type:"POST",
                    url:"http://localhost:8080/OrderTest/changeCartFood",
                    data:data,
                    dataType:"text",
                    success:function (){
                        alert("修改成功");
                        window.location.href = "http://localhost:8080/OrderTest/cart";
                    },
                    error:function (){
                        alert("请求失败或您输入的数值错误！");
                        window.location.href = "http://localhost:8080/OrderTest/cart";
                    }
                })
            }else {
                //如果不确定修改，把输入框的数据改成是原来的
                this.value = oldValue;
            }
        }
    )
})