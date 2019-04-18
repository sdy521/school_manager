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
    $(function () {
       //头像上传
        fileInput();
        //websocket客户端
        websocketClient();
    });

    function fileInput() {
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
    }
    
    function websocketClient() {
        var websocketUrl = $("#websocketUrl").val();
        var websocketUserId = $("#websocketUserId").val();
        var websocket = null;

        //判断当前浏览器是否支持WebSocket
        if('WebSocket' in window){
            websocket = new WebSocket("ws://"+websocketUrl+"websocket/"+websocketUserId);
        } else{
            alert('Not support websocket')
        }

        //连接发生错误的回调方法
        websocket.onerror = function(e){
            console.log("websocket连接失败"+e);
        };

        //连接成功建立的回调方法
        websocket.onopen = function(event){
            console.log("websocket连接成功");
        }

        //接收到消息的回调方法
        websocket.onmessage = function(){
        }

        //连接关闭的回调方法
        websocket.onclose = function(){
            console.log("websocket关闭连接");
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function(){
            websocket.close();
        }


        //关闭连接
        function closeWebSocket(){
            websocket.close();
        }

        //发送消息
        function send(){
            websocket.send("message");
        }
    }
</script>