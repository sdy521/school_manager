var WordPdf = {
    tableId:"#grid-table",
    table:null,
}
WordPdf.initJqGrid = function(){
    var tableInstance = $("#grid-table").jqGrid({
        url:'/wordConverterPdf/grid',
        postData:{type:1},
        datatype: "json",
        caption: "已转pdf",
        jsonReader:{
            root: function (data) {
                return data.obj.rows;
            },   // 实际模型数据
            page: function (data) {
                return data.obj.page;
            },   // 当前页码
            total: function (data) {
                return data.obj.total;
            }, // 页码总数
            records: function (data) {
                return data.obj.records;
            }, // 数据总数
            repeatitems:false
        },
        colNames:['编号','文件名','地址','创建时间','修改时间','操作'],
        colModel:[
            {name:'id',index:'id', width:80,sortable:true,search:false,formatter:'integer',key:true},
            {name:'name',index:'name', width:120,sortable:false,search:true, stype:'text'},
            {name:'path',index:'path', width:200,sortable:false,search:true, stype:'text'},
            {name:'createTime',index:'createTime', width:120,sortable:false,search:false,formatter:function (cellValue) {
                    return dateFtt("yyyy-MM-dd hh:mm:ss",new Date(cellValue));
                }},
            {name:'updateTime',index:'updateTime', width:120,sortable:false,search:false,formatter:function (cellValue) {
                    return dateFtt("yyyy-MM-dd hh:mm:ss",new Date(cellValue));
                }},
            {name:'operation',index:'operation', width:100, sortable:false,sortable:false,search:false,formatter:function (cellValue,index,rowObject) {
                    var path = rowObject['path'];
                    var str="";
                    str +="<button type=\"button\" class=\"btn btn-success btn-sm\" style='width: 80px;' onclick=\"WordPdf.downloadPdf(\'"+path+"\');\">下载</button>&nbsp;&nbsp;";
                    return str;
                }}
        ],
        page: 1,    //初始页码
        rowNum:10,
        rowList:[10,20,30],
        pager: '#grid-pager',
        autowidth: true,
        height:380,
        viewrecords: true,
    });
    return tableInstance;
}
//搜索
WordPdf.reload = function () {
    WordPdf.table.setGridParam({
        url:"/wordConverterPdf/grid",
        page:1,
    }).trigger("reloadGrid");
}
//下载pdf
WordPdf.downloadPdf = function(path){
    var nginxurl = $("#nginxurl").val();
    var url = nginxurl+path;
    window.open("/wordConverterPdf/download?url="+url);
}
//全部导出pdf
WordPdf.downloadPdfZip = function(){
    window.open("/wordConverterPdf/downloadZip");
}
$(function () {
    WordPdf.table = WordPdf.initJqGrid();
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
        WordPdf.reload();
        success("word转pdf成功");

    }).on('fileerror', function(event, data, msg) {  //一个文件上传失败
        var filenames = data.filenames;
        console.log('文件上传失败:'+filenames);
        error("word转pdf失败");
    })
});