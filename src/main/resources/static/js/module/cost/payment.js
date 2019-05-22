
$(function () {
    $("#addWindow").click(function () {
        $('#customerName').val("");
        $('#contact').val("");
        $('#payee').val("");
        $('#amount').val("");
        $('#paymentTime').val("");
        $('#detailsDes').val("");
        $('#remark').val("");
        $("#paymentLabel").text("收入记录添加");
        $('#paymentModal').modal('show');
    });

    $("#paymentSave").click(function () {
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
        if(form1.amount.value==""){
            form1.amount.focus();
            return false;
        }
        if(form1.paymentTime.value==""){
            form1.paymentTime.focus();
            return false;
        }
        $.ajax({
            url:"/payment/save",
            type:"POST",
            data:{
                customerName : form1.customerName.value,
                contact : form1.contact.value,
                payee : form1.payee.value,
                amount : form1.amount.value,
                type : form1.type.value,
                paymentTime : form1.paymentTime.value,
                detailsDes : form1.detailsDes.value,
                remark : form1.remark.value,
                id : form1.id.value
            },
            dataType:"json",
            success:function(result){
                bootbox.alert(result.msg, function () {
                    $('#paymentModal').modal('hide');
                    location.assign(getRootPath() + location.pathname);
                });

            }
        });
    });


});


