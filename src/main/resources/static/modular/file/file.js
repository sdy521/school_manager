var File={}
var jstreeOptions = {
    // 引入插件
    plugins: ['types','themes','contextmenu'],
    types: {
        'package': {},
        'file': {}
    },
    core: {
        data: [],
        themes: {
            "variant" : "large"
        }
    },
    contextmenu: {    // 右键菜单
        'items':contextmenu//每个节点都会调用这个函数
    }
};
function contextmenu(node){
    var items = {
        'addDir': {
            'label': '新增目录',
            'action': function (data) {
                var inst = $.jstree.reference(data.reference);
                var obj = inst.get_node(data.reference);
                $("#codeDir").val(obj.id);
                $("#DirName").val("");
                $("#createModalDir").modal();
            }
        },
        'addFile': {
            'label': '上传文件',
            'action': function (data) {
                var inst = $.jstree.reference(data.reference);
                var obj = inst.get_node(data.reference);
                $("#codeFile").val(obj.id);
                $("#createModal").modal();
            }
        },
        'look': {
            'label': '下载',
            'action': function (data) {
                var inst = $.jstree.reference(data.reference);
                var obj = inst.get_node(data.reference);
                $.ajax({
                    url:"/file/downloadFile?code="+obj.id,
                    type:"GET",
                    success:function (r) {
                        if(r.code===0){
                            window.open(r.msg);
                        }else {
                            error(r.msg);
                        }
                    }
                });
            }
        },
        /*'synchro': {
            'label': '同步',
            'action': function (data) {
                var inst = $.jstree.reference(data.reference);
                var obj = inst.get_node(data.reference);
                $.ajax({
                    url:"/file/synchroFile?name="+obj.text,
                    type:"GET",
                    success:function (r) {
                        if(r.code===0){
                            success("同步成功");
                        }
                    }
                });
            }
        },*/
        'update':{
            'label':'修改',
            'action':function (data) {
                var inst = $.jstree.reference(data.reference);
                var obj = inst.get_node(data.reference);
                $("#updateCode").val(obj.id);
                $("#updateName").val("");
                $("#updateModal").modal();
            }
        },
        'updateFile':{
            'label':'修改',
            'action':function (data) {
                var inst = $.jstree.reference(data.reference);
                var obj = inst.get_node(data.reference);
                $("#updateFileCode").val(obj.id);
                $("#updateFileName").val("");
                $("#updateFileModal").modal();
            }
        },
        'delete': {
            'label': '删除',
            'action': function (data) {
                var inst = $.jstree.reference(data.reference);
                var obj = inst.get_node(data.reference);
                warningCommon("确定删除！","如果是文件夹，删除后文件夹下文件全部删除",function () {
                    $.ajax({
                        url:"/file/deletedFile?code="+obj.id+"&name="+obj.text,
                        type:"GET",
                        dateType:"JSON",
                        success:function (r) {
                            if(r.code===0){
                                File.destroyAndCreate();
                                success("删除成功");
                            }
                        }
                    });
                });
            }
        }
    }
    if(node.type=='file'){
        delete items.addFile;
        delete items.addDir;
        delete items.update;
    }else if(node.type=='package'){
        delete items.updateFile;
        delete items.look;
        delete items.synchro;
    }
    return items;
}
File.bootstrapInput = function(){
    $("#input-id").fileinput({
        language: 'zh', //设置语言
        uploadUrl: "/file/uploadFileDesk", //上传的地址
        allowedFileExtensions: ['docx','doc','xls','xlsx','txt'],//接收的文件后缀
        maxFilesNum : 100,//上传最大的文件数量
        uploadAsync: true, //默认异步上传
        showUpload: true, //是否显示上传按钮
        showRemove : true, //显示移除按钮
        showPreview : false, //是否显示预览
        showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        enctype: 'multipart/form-data',
        uploadExtraData:function(){//向后台传递参数
            var data={
                code:$("#codeFile").val(),
            };
            return data;
        },
        validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
    }).on('filepreupload', function(event, data, previewId, index) {
        var filenames = data.filenames;
        console.log('文件正在上传:'+filenames[index]);
    }).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功
        var filenames = data.filenames;
        console.log('文件上传成功:'+filenames[index]);
        File.destroyAndCreate();
        $("#createModal").modal('hide');
        if(data.response.code===0){
            success("文件上传成功");
        }else {
            error(data.response.msg);
        }

    }).on('fileerror', function(event, data, msg) {  //一个文件上传失败
        var filenames = data.filenames;
        console.log('文件上传失败:'+filenames);
        error("文件上传失败");
    })
}
File.initJsTree = function(){
    $.ajax({
        url:"/file/jsTree",
        type:"GET",
        dataType:"json",
        success:function (r) {
            if(r.code===0){
                jstreeOptions.core.data = r.obj;
                $('#jstree_file').jstree(jstreeOptions);
            }
        }
    });
}
//新增目录
File.addDir = function(){
    var dirname = $("#DirName").val();
    var codeDir = $("#codeDir").val();
    $.ajax({
        url:"/file/addDir?dirname="+dirname+"&codeDir="+codeDir,
        type:"GET",
        dataType:"JSON",
        success:function (r) {
            if(r.code===0){
                $("#createModalDir").modal('hide');
                File.destroyAndCreate();
                success("目录增加成功");
            }
        }
    });
}
//最外层添加目录
File.FirstAddDir = function(){
    $("#DirFirstName").val("");
    $("#createModalFirstDir").modal();
}
//最外层添加目录 确定
File.addFirstDir = function(){
    var dirname = $("#DirFirstName").val();
    $.ajax({
        url:"/file/addFirstDir?dirname="+dirname,
        type:"GET",
        dataType:"JSON",
        success:function (r) {
            if(r.code===0){
                $("#createModalFirstDir").modal('hide');
                File.destroyAndCreate();
                success("目录增加成功");
            }
        }
    });
}
//修改
File.updateName = function(){
    var newName = $("#updateName").val();
    var updateCode = $("#updateCode").val();
    $.ajax({
        url:"/file/updateName?newName="+newName+"&updateCode="+updateCode,
        type:"GET",
        dateType:"JSON",
        success:function (r) {
            if(r.code===0){
                $("#updateModal").modal('hide');
                File.destroyAndCreate();
                success("修改成功");
            }
        }
    });
}
//修改文件
File.updateFileName = function(){
    var newName = $("#updateFileName").val();
    var updateCode = $("#updateFileCode").val();
    warningCommon("确定修改文件名!","",function () {
        $.ajax({
            url:"/file/updateFileName?newName="+newName+"&updateCode="+updateCode,
            type:"GET",
            dateType:"JSON",
            success:function (r) {
                if(r.code===0){
                    $("#updateFileModal").modal('hide');
                    File.destroyAndCreate();
                    success("修改成功");
                }else {
                    error(r.msg);
                }
            }
        });
    });
}
File.destroyAndCreate = function(){
    $('#jstree_file').jstree('destroy');
    File.initJsTree();
}
$(function () {
    //初始化树
    File.initJsTree();
    //初始化导入
    File.bootstrapInput();
});