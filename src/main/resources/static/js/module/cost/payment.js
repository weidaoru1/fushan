
$(function () {
    //查询事件
    $("#querySearch").click(function () {
        $('#myTable').bootstrapTable('refresh', {
            pageNum: 1
        });
    });
    //添加
    $("#addWindow").click(function () {
        $('#customerName').val("");
        $('#contact').val("");
        $('#payee').val("");
        $('#amounts').val("");
        $('#amount').val("");
        $('#type').val("");
        $('#paymentTime').val("");
        $('#detailsDes').val("");
        $('#remark').val("");
        $('#id').val("");
        $('#createTime').val("");
        $("#paymentModalLabel").text("添加信息");
        $('#paymentModal').modal('show');
    });
    //保存
    $("#paymentSave").click(function () {
        var url = "/payment/save";
        if (form1.id.value != ""){
            url = "/payment/edit";
        }
        if(form1.customerName.value==""){
            form1.customerName.focus();
            return false;
        }
        var contact = form1.contact.value;
        var patrn = /^0?1[358]\d{9}$/;
        if(contact != ""){
            var pa = patrn.test(contact);
            if (!pa){
                bootbox.alert("请输入正确的手机号码格式！");
                form1.contact.focus();
                return false;
            }
        }
        if(form1.payee.value==""){
            form1.payee.focus();
            return false;
        }
        var amount = form1.amount.value;
        var statusList = document.getElementsByName("status");
        var status;
        for(var i=0;i<statusList.length;i++){
            if (statusList[i].checked){
                status = statusList[i].value;
            }
        }
        if (status == '1'){//已付清
            amount = form1.amounts.value;
        }
        if(form1.amounts.value==""){
            form1.amounts.focus();
            return false;
        }
        if(form1.paymentTime.value==""){
            form1.paymentTime.focus();
            return false;
        }
        var paymentTime = new Date(form1.paymentTime.value);
        var createTime = form1.createTime.value;
        if (createTime == ""){
            createTime = new Date();
        }else{
            createTime = new Date(createTime);
        }
        $.ajax({
            url:url,
            type:"POST",
            data:{
                customerName : form1.customerName.value,
                contact : form1.contact.value,
                payee : form1.payee.value,
                status : status,
                amount : amount,
                amounts : form1.amounts.value,
                type : form1.type.value,
                paymentTime : paymentTime,
                detailsDes : form1.detailsDes.value,
                remark : form1.remark.value,
                createTime : createTime,
                id : form1.id.value
            },
            dataType:"json",
            success:function(result){
                bootbox.alert(result.msg, function () {
                    $('#paymentModal').modal('hide');
                    $('#myTable').bootstrapTable('refresh', {pageNum: 1});
                });
            }
        });
    });
    //编辑
    $("#editWindow").click(function () {
        var datas = $("#myTable").bootstrapTable('getSelections');
        if (datas.length > 1 || datas.length == 0){
            bootbox.alert("请选择至少或允许一条记录！");
            return false;
        }
        var data = datas[0];
        var statusList = document.getElementsByName("status");
        for(var j=0;j<statusList.length;j++){
            if (statusList[j].value == data.status){
                statusList[j].checked = true;
                // if (data.status == 2){
                //     document.getElementById('amount').disabled=false;
                // }else{
                //     document.getElementById('amount').disabled=true;
                // }
            }
        }
        $('#paymentTime').val(data.paymentTime.substring(0,10));
        $('#customerName').val(data.customerName);
        $('#contact').val(data.contact);
        $('#payee').val(data.payee);
        $('#amounts').val(data.amounts);
        $('#amount').val(data.amount);
        $('#type').val(data.type);
        $('#detailsDes').val(data.detailsDes);
        $('#remark').val(data.remark);
        $('#id').val(data.id);
        $('#createTime').val(data.createTime);
        $("#paymentModalLabel").text("编辑信息");
        $('#paymentModal').modal('show');
    });
    //删除
    $("#deleteWindow").click(function () {
        if ($("#isSuper").val() != "1"){
            bootbox.alert("您没有删除权限，请联系系统管理员！");
            return;
        }
        var check_val = [];
        var datas = $("#myTable").bootstrapTable('getSelections');
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
                url:"/payment/deleteById",
                type:"POST",
                data:{
                    ids : check_val.join(";")
                },
                dataType:"json",
                success:function(result){
                    bootbox.alert(result.msg, function () {
                        $('#myTable').bootstrapTable('refresh', {pageNum: 1});
                    });
                }
            });
        });
    });
    //返回
    $("#paymentReturn").click(function () {
        window.location.href="/cost/paymentList";
    });
});
function EditViewById(id) {
    //销毁表格数据
    $('#recordTable').bootstrapTable('destroy');
    $("#recordTable").bootstrapTable({
        contentType: "application/x-www-form-urlencoded", //不设置时会默认text/plain，request.getParameter()是取不到值的
        url: "/payment/paymentRecordById",         //请求后台的URL（*）
        method: 'POST',                      //请求方式（*）
        height: '100%',
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
        showRefresh: true,
        showColumns: true,                //是否显示所有的列
        uniqueId: "ID",                   //每一行的唯一标识，一般为主键列
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        queryParams: function (params) {
            var pageSize = params.limit;
            var offset = params.offset;
            var pageNum = offset / pageSize + 1;
            return {
                pageSize : pageSize,
                pageNum : pageNum,
                paymentId : id
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
            field: 'customerName',
            title: '客户姓名'
        },{
            field: 'contact',
            title: '联系方式'
        },{
            field: 'payee',
            title: '收款人'
        },{
            field: 'amounts',
            title: '总金额'
        },{
            field: 'type',
            title: '收款方式',
            formatter:function(value,row,index){
                if (value == 1){
                    return "<span>现金</span>"
                }
                if (value == 2){
                    return "<span>支付宝</span>"
                }
                if (value == 3){
                    return "<span>微信</span>"
                }
                if (value == 4){
                    return "<span>转账</span>"
                }
            }
        },{
            field: 'paymentTime',
            title: '收款日期',
            formatter:function(value,row,index){
                if (value == null){
                    return ""
                }
                return value.substring(0, 10);
            }
        },{
            field: 'createTime',
            title: '创建日期'
        },{
            field: 'detailsDes',
            title: '详情描述',
            visible: false            //设置隐藏
        },{
            field: 'remark',
            title: '备注',
            visible: false
        }]
    });
    $("#paymentRecordModal").text("修改记录");
    $('#recordModal').modal('show');
}
function queryDetails(id) {
    $('#recordTable').bootstrapTable('destroy');
    $('#detailsTable').bootstrapTable('destroy');
    $("#detailsTable").bootstrapTable({
        contentType: "application/x-www-form-urlencoded", //不设置时会默认text/plain，request.getParameter()是取不到值的
        url: "/payment/paymentDetailsById",         //请求后台的URL（*）
        method: 'POST',                      //请求方式（*）
        height: '100%',
        toolbar: '#detailsToolbar',
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
        showRefresh: true,
        showColumns: true,                //是否显示所有的列
        uniqueId: "ID",                   //每一行的唯一标识，一般为主键列
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        queryParams: function (params) {
            var pageSize = params.limit;
            var offset = params.offset;
            var pageNum = offset / pageSize + 1;
            return {
                pageSize : pageSize,
                pageNum : pageNum,
                paymentId : id
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
            field: 'customerName',
            title: '客户姓名'
        },{
            field: 'contact',
            title: '联系方式'
        },{
            field: 'payee',
            title: '收款人'
        },{
            field: 'amount',
            title: '金额'
        },{
            field: 'paymentTime',
            title: '收款日期',
            formatter:function(value,row,index){
                if (value == null){
                    return ""
                }
                return value.substring(0, 10);
            }
        },{
            field: 'createTime',
            title: '创建日期'
        },{
            field: 'remark',
            title: '备注',
            visible: false
        }]
    });
    $("#paymentDetailsModal").text("收入详情");
    $('#detailsModal').modal('show');
}
