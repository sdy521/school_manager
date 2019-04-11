$(function () {
    //bootstrap fileinput
    $("#input-id").fileinput({
        language: 'zh', //设置语言
        uploadUrl: "/wordConverterPdf/uploadWord", //上传的地址
        allowedFileExtensions: ['docx'],//接收的文件后缀
        maxFilesNum : 100,//上传最大的文件数量
        //uploadExtraData:{"id": 1, "fileName":'123.mp3'},
        uploadAsync: true, //默认异步上传
        showUpload: true, //是否显示上传按钮
        showRemove : true, //显示移除按钮
        showPreview : true, //是否显示预览
        showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        //dropZoneEnabled: true,//是否显示拖拽区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        //maxFileCount: 10, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",

    }).on('filepreupload', function(event, data, previewId, index) {
        var filenames = data.filenames;
        console.log('文件正在上传:'+filenames[index]);
    }).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功
        var filenames = data.filenames;
        console.log('文件上传成功:'+filenames[index]);
        success("word转pdf成功");

    }).on('fileerror', function(event, data, msg) {  //一个文件上传失败
        var filenames = data.filenames;
        console.log('文件上传失败:'+filenames);
        error("word转pdf失败");
    })
});