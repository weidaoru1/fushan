
$(function () {
    //查询事件
    $("#dateQuerySearch").click(function () {
        $('#myTable').bootstrapTable('refresh', {
            pageNum: 1
        });
    });
    //添加
    $("#addWindow").click(function () {
        // $('#userName').val("");
        // $('#realName').val("");
        // $('#des').val("");
        // $('#tel').val("");
        // $('#userId').val("");
        // $('#password').val("");
        $("#paymentModalLabel").text("添加信息");
        $('#paymentModal').modal('show');
    });
});
