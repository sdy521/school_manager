<!-- Mainly scripts -->
<script src="/static/js/jquery-3.1.1.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<#--工具js-->
<script src="/static/management/tool.js"></script>
<!-- Custom and plugin javascript -->
<script src="/static/js/inspinia.js"></script>
<script src="/static/js/plugins/pace/pace.min.js"></script>
<#--jqGrid-->
<script type="text/javascript" src="/static/js/plugins/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="/static/js/plugins/jqGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="/static/js/plugins/jqGrid/jqGrid.js"></script>
<#--sweetalert-->
<script type="text/javascript" src="/static/js/plugins/sweetalert/sweetalert.min.js"></script>
<#--dropzone-->
<script type="text/javascript" src="/static/js/plugins/dropzone/dropzone.js"></script>
<#--websocket-->
<script src="/static/js/stomp.min.js"></script>
<script src="/static/js/sockjs.min.js"></script>
<script type="text/javascript">
    var stompClient = null;
    $(function () {
        var socket = new SockJS('/endpointSang');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected:' + frame);
            stompClient.subscribe('/topic/getResponse', function (response) {
                showResponse(JSON.parse(response.body).responseMessage);
            })
        });
    });
    function sendName() {
        var title = $('#onlyTitle').val();
        var content = Send.ue.getContent();
        console.log('title:' + title);
        console.log('content:' + content);
        var params = {};
        params.title = title;
        params.content = content;
        stompClient.send("/websocket", {}, JSON.stringify(params));
    }
    function showResponse(message) {
        // $("#response").html(message);
        success("发布成功");
        $("#tip").html(message);
        Send.reload();
        $("#onlyTitle").val("");
        setContent();
    }
    function setContent(isAppendTo) {
        Send.ue.setContent('', isAppendTo);
    }
</script>