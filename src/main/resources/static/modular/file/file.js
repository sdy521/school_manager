var File={}
var jstreeOptions = {
    // 引入插件
    plugins: [/*'checkbox',*/'types','themes','contextmenu'],
    types: {
        'default': {
            'icon': false  // 删除默认图标
        },
    },
    core: {
        data: [],
        themes: {
            "variant" : "large"
        }
    },
    /*checkbox: {
        'tie_selection': false,
        'keep_selected_style': false,
        'whole_node': false
    },*/
    contextmenu: {    // 右键菜单
        'items': {
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
                'label': '新增文件',
                'action': function (data) {
                    var inst = $.jstree.reference(data.reference);
                    var obj = inst.get_node(data.reference);
                    $("#codeFile").val(obj.id);
                    $("#createModal").modal();
                }
            },
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
            'delete': {
                'label': '删除',
                'action': function (data) {
                    info("暂未开发");
                }
            }
        }
    }
};
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
        success("文件上传成功");

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