
$(function () {
    $("#conditionSearch").click(function () {
        $('#spendTable').bootstrapTable('refresh', {
            pageNum: 1
        });
    });
    $("#addWindow").click(function () {
        $('#id').val("");
        $('#createTime').val("");
        $("#spendForm")[0].reset();
        $("#spendModalLabel").text("添加信息");
        $('#spendModal').modal('show');
    });
    $("#editWindow").click(function () {
        var datas = $("#spendTable").bootstrapTable('getSelections');
        if (datas.length > 1 || datas.length == 0){
            bootbox.alert("请选择至少或允许一条记录！");
            return false;
        }
        var data = datas[0];
        $('#spendTime').val(data.spendTime.substring(0,10));
        $('#spendName').val(data.spendName);
        $('#spendMatters').val(data.spendMatters);
        $('#amount').val(data.amount);
        $('#type').val(data.type);
        $('#remark').val(data.remark);
        $('#id').val(data.id);
        $('#createTime').val(data.createTime);
        $("#spendModalLabel").text("编辑信息");
        $('#spendModal').modal('show');
    });

    $("#spendSave").click(function () {
        var url = "/spend/save";
        if (spendForm.id.value != ""){
            url = "/spend/edit";
        }
        if(spendForm.spendName.value==""){
            spendForm.spendName.focus();
            return false;
        }
        if(spendForm.spendMatters.value==""){
            spendForm.spendMatters.focus();
            return false;
        }
        if(spendForm.amount.value==""){
            spendForm.amount.focus();
            return false;
        }
        if(spendForm.spendTime.value==""){
            spendForm.spendTime.focus();
            return false;
        }
        var spendTime = new Date(spendForm.spendTime.value);
        var createTime = spendForm.createTime.value;
        if (createTime == ""){
            createTime = new Date();
        }else{
            createTime = new Date(createTime);
        }
        $.ajax({
            url:url,
            type:"POST",
            data:{
                spendName : spendForm.spendName.value,
                spendMatters : spendForm.spendMatters.value,
                amount : spendForm.amount.value,
                type : spendForm.type.value,
                spendTime : spendTime,
                remark : spendForm.remark.value,
                createTime : createTime,
                id : spendForm.id.value
            },
            dataType:"json",
            success:function(result){
                bootbox.alert(result.msg, function () {
                    $('#spendModal').modal('hide');
                    $('#spendTable').bootstrapTable('refresh', {pageNum: 1});
                });
            }
        });
    });

    $("#deleteWindow").click(function () {
        if ($("#isSuper").val() != "1"){
            bootbox.alert("您没有删除权限，请联系系统管理员！");
            return;
        }
        var check_val = [];
        var datas = $("#spendTable").bootstrapTable('getSelections');
        for (var i = 0; i < datas.length; i++){
            check_val.push(datas[i].id);
        }
        if (check_val.length == 0){
            bootbox.alert("请选择删除的数据记录！")
            return false;
        }
        bootbox.confirm("确定要删除选择的数据！", function(result){
            if(!result){
                return;
            }
            $.ajax({
                url:"/spend/deleteById",
                type:"POST",
                data:{
                    ids : check_val.join(";")
                },
                dataType:"json",
                success:function(result){
                    bootbox.alert(result.msg, function () {
                        $('#spendTable').bootstrapTable('refresh', {pageNum: 1});
                    });
                }
            });
        });
    });
});
function EditViewById(id) {
    //销毁表格数据
    $('#recordTable').bootstrapTable('destroy');
    $("#recordTable").bootstrapTable({
        contentType: "application/x-www-form-urlencoded", //不设置时会默认text/plain，request.getParameter()是取不到值的
        url: "/spend/spendRecordList",         //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        height: '100%',
        //toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",                    //分页方式：client客户端分页，server服务端分页（*）
        pageNum:1,
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 20],                //可供选择的每页的行数（*）
        striped: true,                    //是否显示行间隔色
        showRefresh: true,               //是否显示刷新按钮
        showToggle:true,                 //是否显示详细视图和列表视图
        showColumns: true,                //是否显示所有的列
        uniqueId: "ID",                   //每一行的唯一标识，一般为主键列
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        paginationFirstText: "首页",
        paginationLastText: "末页",
        queryParams: function (params) {
            var pageSize = params.limit;
            var offset = params.offset;
            var pageNum = offset / pageSize + 1;
            return {
                pageSize : pageSize,
                pageNum : pageNum,
                spendId : id
            }
        },
        responseHandler: responseHandler,
        columns: [{
            checkbox: true
        },{
            field: 'id',
            title: 'ID',
            visible: false
        },{
            field: 'userName',
            title: '修改人'
        },{
            field: 'spendName',
            title: '支出人'
        },{
            field: 'spendMatters',
            title: '支出事项'
        },{
            field: 'amount',
            title: '支出金额'
        },{
            field: 'type',
            title: '支出方式',
            formatter:function(value,row,index){
                if (value == 1){
                    return "<span>现金</span>"
                }else if (value == 2){
                    return "<span>支付宝</span>"
                }else if (value == 3){
                    return "<span>微信</span>"
                }else if (value == 4){
                    return "<span>转账</span>"
                }else{
                    return "<span>其他</span>"
                }
            }
        },{
            field: 'spendTime',
            title: '支出日期',
            formatter:function(value,row,index){
                if (value == null){
                    return ""
                }
                return value.substring(0, 10);
            }
        },{
            field: 'createTime',
            title: '修改日期'
        },{
            field: 'remark',
            title: '备注',
            visible: false
        }]
    });
    $("#spendRecordModal").text("修改记录");
    $('#recordModal').modal('show');
}

