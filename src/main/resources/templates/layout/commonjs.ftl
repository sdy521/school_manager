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
        //判断是否在首页
        var path = window.location.href;
        if(path.indexOf("/main")!=-1){
            localStorage.removeItem("noticeCount");
            localStorage.removeItem("pop");
        }
        //判断是否有公告没看
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
            var message = JSON.parse(event.data);
            //提示层
            pop_init("公告信息",message.message,message.color);
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
    function pop_init(title,content,color) {
        //发送公告个数
        if(localStorage.getItem("noticeCount")){
            var count = parseInt(localStorage.getItem("noticeCount"))+1;
            localStorage.setItem("noticeCount",count);
            $("#tip").text(count)
        }else {
            localStorage.setItem("noticeCount",1);
            $("#tip").text(1);
        }
        //取当前浏览器窗口大小
        var windowWidth=$(document).width();
        var windowHeight=$(document).height();
        //弹窗的大小
        var weight=320;
        var height=120;
        var str = "<div id='pop_div'style='background: "+color+";display:none;position:absolute;border:1px solid #e0e0e0;z-index:99;width:"+weight+"px;height:"+height+"px;top:"+(windowHeight-height-10)+"px;left:"+(windowWidth-weight-10)+"px'>"+
                "<div style='line-height:32px;background:"+color+";'>" +
                "<div style='float:right;cursor:pointer;'><span onclick='pop_close()'>关闭</span></div>"+
                "<div style='text-align: center'><h4>"+title+"</h4></div>" +
                "<div style='clear:both'></div>"+
                "</div>" +
                "<div id='content' style='margin-left: 10px;'>" +
                "<strong>"+content+"</strong>"+
                "</div>"+
                "</div>";
        localStorage.setItem("pop",str);
        $("#pop_div").remove();
        $("body").append(str);
        $('#pop_div').fadeIn(400);
    }
    //提示层关闭
    function pop_close(){
        localStorage.removeItem("pop");
        $('#pop_div').fadeOut(400);
    }
</script>