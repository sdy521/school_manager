<!-- Mainly scripts -->
<script src="/static/js/jquery-3.1.1.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<#--工具js-->
<script src="/static/modular/tool.js"></script>
<!-- Custom and plugin javascript -->
<script src="/static/js/inspinia.js"></script>
<script src="/static/js/plugins/pace/pace.min.js"></script>
<#--jqGrid-->
<script type="text/javascript" src="/static/js/plugins/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="/static/js/plugins/jqGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="/static/js/plugins/jqGrid/jqGrid.js"></script>
<#--sweetalert-->
<script type="text/javascript" src="/static/js/plugins/sweetalert/sweetalert.min.js"></script>
<#--websocket-->
<script src="/static/js/stomp.min.js"></script>
<script src="/static/js/sockjs.min.js"></script>
<#--bootstrapFileUpload-->
<script src="/static/plugins/bootstrapFileUpload/js/fileinput.min.js"></script>
<script type="text/javascript">
    var stompClient = null;
    $(function () {
        //初始化websocket
        var socket = new SockJS('/endpointSang');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected:' + frame);
            stompClient.subscribe('/topic/getResponse', function (response) {
                showResponse(JSON.parse(response.body).responseMessage);
            })
        });
        //bootstrapFileUpload
        $("#uploadfile").fileinput({
            language: 'zh', //设置语言
            uploadUrl: "/upload", //上传的地址
            allowedFileExtensions: ['jpg','png','jpeg'],//接收的文件后缀
            uploadAsync: true, //默认异步上传
            showUpload: true, //是否显示上传按钮
            showRemove : true, //显示移除按钮
            showPreview : true, //是否显示预览
            showCaption: true,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: true,//是否显示拖拽区域
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true
        });
        //异步上传返回结果处理
        $("#uploadfile").on("fileuploaded", function (event, data, previewId, index) {
            var response = data.response;
            success(response.message);
            $("#uploadModal").modal('hide');
        });
        //上传前
       /* $('#uploadfile').on('filepreupload', function(event, data, previewId, index) {
            var form = data.form, files = data.files, extra = data.extra,
                    response = data.response, reader = data.reader;
        });*/
    });
    function sendName() {
        var title = $('#onlyTitle').val();
        var content = Send.ue.getContent();
        var params = {};
        params.title = title;
        params.content = content;
        stompClient.send("/websocket", {}, JSON.stringify(params));
    }
    function showResponse(message) {
        // success("发布成功");
        $("#tip").html(message);
        Send.reload();
        $("#onlyTitle").val("");
        setContent();
    }
    function setContent(isAppendTo) {
        Send.ue.setContent('发布成功...', isAppendTo);
    }
</script>