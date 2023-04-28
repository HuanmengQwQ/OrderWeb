function deleteMenu(id) {

    if (confirm('确认删除吗?')) {
        $.ajax({
            type: 'get',
            url: "http://localhost:8080/OrderTest/delMenu",
            data: {
                'id': 'id' // 编号
            },
            success: function (mag) {
                alert(mag);
                alert(typeof (mag));
                if (mag === 1) { //删除成功
                    alert("删除成功");
                } else {
                    alert("删除失败");
                }
            },
            error: function () {
                alert("Error!");
            }
        });
    }
}

function del() {
    if (confirm("您确定要删除吗？")) {
        return true;
    } else {
        return false;
    }
}