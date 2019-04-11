var File={}
var jstreeOptions = {
    // 引入插件
    plugins: [/*'checkbox',*/'types','themes','contextmenu'],
    types: {
        'default': {
            'icon': true  // 删除默认图标
        },
    },
    core: {
        data: [],
        themes: {
            "variant" : "large"
        },
        strings: {
            "loading": "加载中 ..."
        }
    },
    /*checkbox: {
        'tie_selection': false,
        'keep_selected_style': false,
        'whole_node': false
    },*/
    contextmenu: {    // 右键菜单
        'items': {
            'add': {
                'label': '增加',
                'action': function (data) {
                    var inst = $.jstree.reference(data.reference);
                    var obj = inst.get_node(data.reference);
                    $("#code").val(obj.id);
                    $("#createModal").modal();
                }
            },
            'edit': {
                'label': '编辑',
                'action': function (data) {
                    alert("编辑")
                }
            },
            'delete': {
                'label': '删除',
                'action': function (data) {
                    alert("删除")
                }
            }
        }
    }
};
File.bootstrapInput = function(){
    $("#input-id").fileinput({
        language: 'zh', //设置语言
        uploadUrl: "/file/uploadFileDesk", //上传的地址
        allowedFileExtensions: ['docx','doc','xls','txt'],//接收的文件后缀
        maxFilesNum : 100,//上传最大的文件数量
        uploadAsync: true, //默认异步上传
        showUpload: true, //是否显示上传按钮
        showRemove : true, //显示移除按钮
        showPreview : true, //是否显示预览
        showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        enctype: 'multipart/form-data',
        uploadExtraData:function(){//向后台传递参数
            var data={
                code:$("#code").val(),
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
        $('#jstree_file').jstree('destroy');
        File.initJsTree();
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
$(function () {
    //初始化树
    File.initJsTree();
    //初始化导入
    File.bootstrapInput();
});