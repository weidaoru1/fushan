
$(function () {

    // $("#paymentSave").click(function () {
    //     var paymentId = $("#paymentId").val();
    //     var url = "/details/save";
    //     if (form1.id.value != ""){
    //         url = "/details/editSave";
    //     }
    //     if(form1.customerName.value==""){
    //         form1.customerName.focus();
    //         return false;
    //     }
    //     var contact = form1.contact.value;
    //     var patrn = /^0?1[358]\d{9}$/;
    //     if(contact != ""){
    //         var pa = patrn.test(contact);
    //         if (!pa){
    //             bootbox.alert("请输入正确的手机号码格式！");
    //             form1.contact.focus();
    //             return false;
    //         }
    //     }
    //     if(form1.payee.value==""){
    //         form1.payee.focus();
    //         return false;
    //     }
    //     if(form1.amount.value==""){
    //         form1.amount.focus();
    //         return false;
    //     }
    //     if(form1.paymentTime.value==""){
    //         form1.paymentTime.focus();
    //         return false;
    //     }
    //     var paymentTime = new Date(form1.paymentTime.value);
    //     var createTime = form1.createTime.value;
    //     if (createTime == ""){
    //         createTime = new Date();
    //     }
    //     $.ajax({
    //         url:url,
    //         type:"POST",
    //         data:{
    //             customerName : form1.customerName.value,
    //             paymentId : paymentId,
    //             contact : form1.contact.value,
    //             payee : form1.payee.value,
    //             amount : form1.amount.value,
    //             paymentTime : paymentTime,
    //             remark : form1.remark.value,
    //             createTime : createTime,
    //             id : form1.id.value
    //         },
    //         dataType:"json",
    //         success:function(result){
    //             bootbox.alert(result.msg, function () {
    //                 window.location.href="/payment/detailsList?paymentId="+paymentId;
    //             });
    //         }
    //     });
    // });
    // $("#paymentClose").click(function () {
    //     window.location.href="/payment/detailsList?paymentId="+$("#paymentId").val();
    // });
    // $("#detailsReturn").click(function () {
    //     window.location.href="/cost/paymentList";
    // });
    // $("#paymentDetailsReturn").click(function () {
    //     window.location.href="/payment/detailsList?paymentId="+$("#paymentId").val();
    // });
    // $("#detailsDeleteWindow").click(function () {
    //     if ($("#isSuper").val() != "1"){
    //         bootbox.alert("您没有删除权限，请联系系统管理员！");
    //         return;
    //     }
    //     var obj = document.getElementsByName("check");
    //     var check_val = [];
    //     for (var i = 0; i < obj.length; i++){
    //         if(obj[i].checked){
    //             check_val.push(obj[i].value);
    //         }
    //     }
    //     if (check_val.length == 0){
    //         bootbox.alert("请选择删除的数据记录！")
    //         return false;
    //     }
    //     bootbox.confirm("确定要删除选择的数据！", function(result){
    //         if(!result){
    //             return;
    //         }
    //         $.ajax({
    //             url:"/details/detailsDelete",
    //             type:"POST",
    //             data:{
    //                 ids : check_val.join(";"),
    //                 paymentId : $("#paymentId").val()
    //             },
    //             dataType:"json",
    //             success:function(result){
    //                 bootbox.alert(result.msg, function () {
    //                     location.assign(getRootPath() + location.pathname);
    //                 });
    //             }
    //         });
    //     });
    // });
});

