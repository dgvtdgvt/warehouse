function editProduct(product_id){
    var url = getProjectPath()+"/findProductById?product_id=" + product_id;
    $.get(url, function (response) {
        //如果是编辑，将获取的信息回显到编辑的窗口中
        $("#ebid").val(response.data.id);
        $("#ebname").val(response.data.name);
        $("#ebean").val(response.data.ean);
        $("#ebprice").val(response.data.price);
        $("#ebspecification").val(response.data.specification);
    })
}

function addProduct(){
    $("#ebname").val("");
    $("#ebean").val("");
    $("#ebprice").val("");
    $("#ebspecification").val("");
}
//点击添加或编辑的窗口的确定按钮时，提交信息
function addOrEdit() {
    //获取表单中id的内容
    if (isNaN($("#ebprice").val())) {
        alert("操作失败!商品价格请填写数字!");
        return null;
    }
    var ebid = $("#ebid").val();
    //如果表单中有id，说明本次为编辑操作
    if (ebid > 0) {
        var url = getProjectPath() + "/editProduct";
        $.post(url, $("#addOrEditProduct").serialize(), function (response) {
            alert(response.message)
            if (response.success == true) {
                window.location.href = getProjectPath() + "/product";
            }
        })
    }
//如果表单中没有id，说明本次为添加操作
    else {
        var url = getProjectPath() + "/addProduct";
        $.post(url, $("#addOrEditProduct").serialize(), function (response) {
            alert(response.message)
            if (response.success == true) {
                window.location.href = getProjectPath() + "/product";
            }
        })
    }
}

//商品入库
function In(product_id){
    $("#proId").val(product_id);
}
function productIn(){
    if (isNaN($("#ebproduct_quantity").val())) {
        alert("商品入库失败!商品数量请填写数字!");
        return null;
    }
    var url = getProjectPath() + "/ProductIn";
    $.post(url, $("#inProduct").serialize(), function (response) {
        alert(response.message)
        if (response.success == true) {
            window.location.href = getProjectPath() + "/product";
        }
    })
}

function Out(warehouse_id){
    // console.log(warehouse_id)
    $("#warehouse_id").val(warehouse_id);
}

function productOut(){
    if (isNaN($("#ebproduct_quantity").val())) {
        alert("商品出库失败!请填写数字!");
        return null;
    }
    var url = getProjectPath() + "/productOut";
    $.post(url, $("#outProduct").serialize(), function (response) {
        alert(response.message)
        if (response.success == true) {
            window.location.href = getProjectPath() + "/warehouse";
        }
    })
}


// function editUser(user_id){
//     var url = getProjectPath()+"/findUserById?user_id=" + user_id;
//     $.get(url, function (response) {
//         $("#user_id").val(response.data.id);
//         $("#ebname").val(response.data.name);
//         // $("#ebpassword").val(response.data.password);
//     })
// }

function editUser(user_id){
    var url = getProjectPath()+"/findUserById?user_id=" + user_id;
    $.get(url, function (response) {
        $("#user_id").val(response.data.id);
        $("#ebname").val(response.data.name);

        // 获取select元素
        var selectRole = document.getElementById("edrole");
        var selectStatus = document.getElementById("ebstatus");

        // 遍历select元素的选项，找到与response.data中role和status属性值相同的选项，并将其设置为默认选项
        for (var i = 0; i < selectRole.options.length; i++) {
            if (selectRole.options[i].value === response.data.role) {
                selectRole.options[i].selected = true;
                break;
            }
        }
        for (var i = 0; i < selectStatus.options.length; i++) {
            if (selectStatus.options[i].value === response.data.status.toString()) {
                selectStatus.options[i].selected = true;
                break;
            }
        }
    })
}


function addUser(){
    $("#user_id").val("");
    $("#ebname").val("");
    $("#ebpassword").val("");
}
function addOrEditUser(){
    console.log($("#addOrEditUser").serialize())
    var user_id = $("#user_id").val();
    //如果表单中有id，说明本次为编辑操作
    if (user_id > 0) {
        var url = getProjectPath() + "/editUser";
        $.post(url, $("#addOrEditUser").serialize(), function (response) {
            alert(response.message)
            if (response.success == true) {
                window.location.href = getProjectPath() + "/user";
            }
        })
    }
//如果表单中没有id，说明本次为添加操作
    else {
        var url = getProjectPath() + "/addUser";
        $.post(url, $("#addOrEditUser").serialize(), function (response) {
            alert(response.message)
            if (response.success == true) {
                window.location.href = getProjectPath() + "/user";
            }
        })
    }
}

function getProjectPath() {
    var pathName = window.document.location.pathname;
    //获取带"/"的项目名，如：/cloudlibrary
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return  projectName;
}