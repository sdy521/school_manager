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
        if(localStorage.pop){
            var str = localStorage.pop;
            $("body").append(str);
            $('#pop_div').fadeIn(400);
        }
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
        websocket.onmessage = function(event){
            //提示层
            pop_init("公告信息",event.data);
            $('#pop_div').fadeIn(400);
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
    //提示层
    function pop_init(title,content) {
        //取当前浏览器窗口大小
        var windowWidth=$(document).width();
        var windowHeight=$(document).height();
        //弹窗的大小
        var weight=320;
        var height=240;
        var str = "<div id='pop_div'style='background: #e9030b;display:none;position:absolute;border:1px solid #e0e0e0;z-index:99;width:"+weight+"px;height:"+height+"px;top:"+(windowHeight-height-10)+"px;left:"+(windowWidth-weight-10)+"px'>"+
                "<div style='line-height:32px;background:#e9030b;border-bottom:1px solid #3b3b3b;font-size:14px;padding:0 0 0 10px;'>" +
                "<div style='float:left;'><b>"+title+"</b></div><div style='float:right;cursor:pointer;'><b onclick='pop_close()'>×</b></div>" +
                "<div style='clear:both'></div>"+
                "</div>" +
                "<div id='content'>" +
                content+
                "</div>"+
                "</div>";
        localStorage.setItem("pop",str);
        $("body").append(str);
    }
    //提示层关闭
    function pop_close(){
        localStorage.removeItem("pop");
        $('#pop_div').fadeOut(400);
    }
</script>