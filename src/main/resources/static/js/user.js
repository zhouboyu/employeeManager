function clickSearch() {
    $('#userModal').modal({
        show:true,
        keyboard:false,
        backdrop:"static"
    });
    $("#userModalTtile").text("设置查询条件");
    $("#actionBtn").val("查询");
    $("#actionBtn").attr("action","search");
}
function clickAdd() {
    $('#userModal').modal({
        show:true,
        keyboard:false,
        backdrop:"static"
    });
    $("#userModalTtile").text("添加员工");
    $("#actionBtn").val("增加");
    $("#actionBtn").attr("action","add");
}
function doAction(){
    var action = $("#actionBtn").attr("action");
    if(action == "add"){
        addEmployee();
    }else{
        searchEmployee();
    }
}

function addEmployee(){
    if($("#modalname").val()==''){
        $.notify({
            icon: 'glyphicon glyphicon-warning-sign',
            title: '添加错误',
            message: '员工名称不能为空',
        },{
            type: "danger",
            z_index:999999
        });
        return;
    }
    if($("#modalidCard").val()==''){
        $.notify({
            icon: 'glyphicon glyphicon-warning-sign',
            title: '添加错误',
            message: '员工身份证不能为空',
        },{
            type: "danger",
            z_index:999999
        });
        return;
    }

    if($("#modalphone").val()==''){
        $.notify({
            icon: 'glyphicon glyphicon-warning-sign',
            title: '添加错误',
            message: '员工手机不能为空',
        },{
            type: "danger",
            z_index:999999
        });
        return;
    }
    if($("#modalcomselect").val()=='empty'){
        $.notify({
            icon: 'glyphicon glyphicon-warning-sign',
            title: '添加错误',
            message: '请选择公司',
        },{
            type: "danger",
            z_index:999999
        });
        return;
    }
    $.post("/addemployee",{
        "name":$("#modalname").val(),
        "phone":$("#modalphone").val(),
        "idCard":$("#modalidCard").val(),
        "sex":$("#modalsex").val(),
        "comselect":$("#modalcomselect").val()
    },function (data) {
        if(!data.success){
            $.notify({
                icon: 'glyphicon glyphicon-warning-sign',
                title: '添加错误',
                message: data.msg,
            },{
                type: "danger",
                z_index:999999
            });
        }else{
            $.notify({
                icon: 'glyphicon glyphicon-warning-sign',
                title: '添加成功',
                message: "添加成功",
            },{
                type: "success",
                z_index:999999
            });
            searchEmployee();
        }
    })
}

function searchEmployee(){
    $.post("/queryEmployee",{
        "name":$("#modalname").val(),
        "phone":$("#modalphone").val(),
        "idCard":$("#modalidCard").val(),
        "sex":$("#modalsex").val(),
        "comselect":$("#modalcomselect").val()
    },function (data) {
        console.log(data);
        if(!data.success){
            $.Notify({
                caption: '查询错误！',
                content: data.msg,
                type: 'alert'
            });
        }else{
            var htmlstr = "";
            $(data.list).each(function (i,v) {
                console.log(v);
                htmlstr+="<tr>" +
                    "<td>"+v.name+"</td>"+
                    "<td>"+(v.sex?"男":"女")+"</td>"+
                    "<td style=\"white-space:nowrap;overflow: hidden;text-overflow:ellipsis\">"+v.phone+"</td>"+
                    "<td>"+v.company.name+"</td>"+
                    "<td>"+
                    "<button class=\"button dropdown-toggle\" onclick='location.href=\"/toUserDetail?eid="+v.id+"\"'>查看</button>"+
                    "</td>"+
                    "</tr>";
                console.log(htmlstr);
            });
            console.log(htmlstr);
            $("#employListTr").html(htmlstr);
        }
    });
}



function companyClickSearch() {
    $('#companyModal').modal({
        show:true,
        keyboard:false,
        backdrop:"static"
    });
    $("#userModalTtile").text("设置查询条件");
    $("#companyactionBtn").val("查询");
    $("#companyactionBtn").attr("action","search");
    $("#modalcompanyaddressdiv").val("").hide();
    $("#modalcompanydetaildiv").val("").hide();
}
function companyClickAdd() {
    $('#companyModal').modal({
        show:true,
        keyboard:false,
        backdrop:"static"
    });
    $("#userModalTtile").text("添加公司");
    $("#companyactionBtn").val("增加");
    $("#companyactionBtn").attr("action","add");
    $("#modalcompanyaddressdiv").show();
    $("#modalcompanydetaildiv").show();
}


function companydoAction(){
    console.log(1212121)
    var action = $("#companyactionBtn").attr("action");
    if(action == "add"){
        addCompany();
    }else{
        searchCompany();
    }
}

function addCompany(){
    console.log(1212121);
    if($("#modalcompanyname").val()==''){
        $.notify({
            icon: 'glyphicon glyphicon-warning-sign',
            title: '添加错误',
            message: '公司名称不能为空',
        },{
            type: "danger",
            z_index:999999
        });
        return;
    }
    if($("#modalcompanyaddress").val()==''){
        $.notify({
            icon: 'glyphicon glyphicon-warning-sign',
            title: '添加错误',
            message: '公司地址不能为空',
        },{
            type: "danger",
            z_index:999999
        });
        return;
    }

    if($("#modalcompanyphone").val()==''){
        $.notify({
            icon: 'glyphicon glyphicon-warning-sign',
            title: '添加错误',
            message: '联系电话',
        },{
            type: "danger",
            z_index:999999
        });
        return;
    }

    $.post("/addcompany",{
        "name":$("#modalcompanyname").val(),
        "address":$("#modalcompanyaddress").val(),
        "phone":$("#modalcompanyphone").val(),
        "detail":$("#modalcompanydetail").val()
    },function (data) {
        if(data.success){
            $.notify({
                icon: 'glyphicon glyphicon-warning-sign',
                title: '添加成功',
                message: "添加成功",
            },{
                type: "success",
                z_index:999999
            });
            searchCompany();

        }
    })
}

function searchCompany(){
    $.post("/queryCompany",{
        "name":$("#modalcompanyname").val(),
        "address":$("#modalcompanyaddress").val(),
        "phone":$("#modalcompanyphone").val()
    },function (data) {
        console.log(data);
        if(!data.success){
            $.Notify({
                caption: '查询错误！',
                content: data.msg,
                type: 'alert'
            });
        }else{
            var htmlstr = "";
            $(data.list).each(function (i,v) {
                console.log(v);
                htmlstr+="<tr>" +
                    "<td>"+v.name+"</td>"+
                    "<td>"+v.phone+"</td>"+
                    "<td>"+
                    "<button class=\"button dropdown-toggle\" onclick='location.href=\"/toCompanyDetail?cid="+v.id+"\"'>查看</button>"+
                    "</td>"+
                    "</tr>";
                console.log(htmlstr);
            });
            console.log(htmlstr);
            $("#companyDaoListTr").html(htmlstr);
        }
        $('#companyModal').modal('toggle')
    });
}