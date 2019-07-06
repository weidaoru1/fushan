
$(function () {
    //添加
    $("#addWindow").click(function () {
        $('#id').val("");
        $("#menuAddForm")[0].reset();
        $("#parentModalLabel").text("父级菜单添加");
        $('#parentModal').modal('show');
    });
    //保存
    $("#menuSave").click(function () {
        var url = "/menu/save";
        if (form3.id.value != ""){
            url = "/menu/edit";
        }
        if(form3.name.value==""){
            form3.name.focus();
            return false;
        }
        if(form3.num.value==""){
            form3.num.focus();
            return false;
        }
        $.ajax({
            url:url,
            type:"POST",
            data:{
                name : form3.name.value,
                num : form3.num.value,
                des : form3.des.value,
                id : form3.id.value
            },
            dataType:"json",
            success:function(result){
                bootbox.alert(result.msg, function () {
                    $('#parentModal').modal('hide');
                    $('#menuTable').bootstrapTable('refresh', {pageNum: 1});
                });
            }
        });
    });
    //编辑
    $("#editWindow").click(function () {
        var datas = $("#menuTable").bootstrapTable('getSelections');
        if (datas.length > 1 || datas.length == 0){
            bootbox.alert("请选择至少或允许一条记录！");
            return false;
        }
        var data = datas[0];
        $('#name').val(data.name);
        $('#num').val(data.num);
        $('#des').val(data.des);
        $('#id').val(data.id);
        $("#parentModalLabel").text("父级菜单编辑");
        $('#parentModal').modal('show');
    });
    //删除
    $("#deleteWindow").click(function () {
        if ($("#isSuper").val() != "1"){
            bootbox.alert("您没有删除权限，请联系系统管理员！");
            return;
        }
        var check_val = [];
        var datas = $("#menuTable").bootstrapTable('getSelections');
        var system = false;
        for (var i = 0; i < datas.length; i++){
            if (datas[i].state == 1){
                system = true;
            }
            check_val.push(datas[i].id);
        }
        if (check_val.length == 0){
            bootbox.alert("请选择删除的数据记录！")
            return false;
        }
        if (system){
            bootbox.alert("系统默认菜单不可删除！")
            return false;
        }
        bootbox.confirm("确定要删除选择的数据！", function(result){
            if(!result){
                return;
            }
            $.ajax({
                url:"/menu/deleteById",
                type:"POST",
                data:{
                    ids : check_val.join(";")
                },
                dataType:"json",
                success:function(result){
                    bootbox.alert(result.msg, function () {
                        $('#menuTable').bootstrapTable('refresh', {pageNum: 1});
                    });
                }
            });
        });
    });
    //子级菜单添加
    $("#childAdd").click(function () {
        $('#childId').val("");
        $("#childMenuAddForm")[0].reset();
        $("#childMenuModalLabel").text("子级菜单添加");
        $('#childModal').modal('show');
    });
    //子级菜单添加
    $("#childMenuSave").click(function () {
        var parentId = $('#menuParentId').val();
        var url = "/menuChild/save";
        if (form4.childId.value != ""){
            url = "/menuChild/edit";
        }
        if(form4.childName.value==""){
            form4.childName.focus();
            return false;
        }
        if(form4.childNum.value==""){
            form4.childNum.focus();
            return false;
        }
        $.ajax({
            url:url,
            type:"POST",
            data:{
                name : form4.childName.value,
                num : form4.childNum.value,
                des : form4.childDes.value,
                parentId :  parentId,
                url : form4.url.value,
                id : form4.childId.value
            },
            dataType:"json",
            success:function(result){
                bootbox.alert(result.msg, function () {
                    $('#childModal').modal('hide');
                    $('#childTable').bootstrapTable('refresh', {pageNum: 1,parentId : parentId});
                });
            }
        });
    });
    //子级菜单编辑
    $("#childEdit").click(function () {
        var datas = $("#childTable").bootstrapTable('getSelections');
        if (datas.length > 1 || datas.length == 0){
            bootbox.alert("请选择至少或允许一条记录！");
            return false;
        }
        var data = datas[0];
        $('#childName').val(data.name);
        $('#childNum').val(data.num);
        $('#url').val(data.url);
        $('#childDes').val(data.des);
        $('#childId').val(data.id);
        $("#childMenuModalLabel").text("子级菜单编辑");
        $('#childModal').modal('show');
    });
    //子级菜单删除
    $("#childDelete").click(function () {
        var parentId = $('#menuParentId').val();
        if ($("#isSuper").val() != "1"){
            bootbox.alert("您没有删除权限，请联系系统管理员！");
            return;
        }
        var check_val = [];
        var datas = $("#childTable").bootstrapTable('getSelections');
        var system = false;
        for (var i = 0; i < datas.length; i++){
            if (datas[i].state == 1){
                system = true;
            }
            check_val.push(datas[i].id);
        }
        if (check_val.length == 0){
            bootbox.alert("请选择删除的数据记录！")
            return false;
        }
        if (system){
            bootbox.alert("系统默认菜单不可删除！")
            return false;
        }
        bootbox.confirm("确定要删除选择的数据！", function(result){
            if(!result){
                return;
            }
            $.ajax({
                url:"/menuChild/deleteById",
                type:"POST",
                data:{
                    ids : check_val.join(";")
                },
                dataType:"json",
                success:function(result){
                    bootbox.alert(result.msg, function () {
                        $('#childModal').modal('hide');
                        $('#childTable').bootstrapTable('refresh', {pageNum: 1,parentId : parentId});
                    });
                }
            });
        });
    });
});
function queryDetails(id) {
    $('#menuParentId').val(id);
    $('#childTable').bootstrapTable('destroy');
    $("#childTable").bootstrapTable({
        contentType: "application/x-www-form-urlencoded", //不设置时会默认text/plain，request.getParameter()是取不到值的
        url: "/menu/childMenuList",         //请求后台的URL（*）
        method: 'POST',                      //请求方式（*）
        height: '100%',
        toolbar: '#childToobar',
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
                parentId : id
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
            field: 'parentId',
            title: '父级ID',
            visible: false
        },{
            field: 'name',
            title: '名称'
        },{
            field: 'url',
            title: '访问路径'
        },{
            field: 'num',
            title: '顺序'
        },{
            field: 'des',
            title: '描述'
        },{
            field: 'state',
            title: '系统默认',
            visible: false
        }]
    });
    $("#childMenuModal").text("子级菜单");
    $('#menuModal').modal('show');
}


