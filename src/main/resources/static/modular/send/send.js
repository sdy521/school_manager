var Send = {
    tableId:"#grid-table",
    pagerId:"#grid-pager",
    table:null,
    ue:null,
    ue2:null
}

Send.initJqGrid = function(){
    var tableInstance = $("#grid-table").jqGrid({
        url:'/notice/grid',
        datatype: "json",
        caption: "公告列表",
        jsonReader:{
            root: function (data) {
                return data.obj.rows;
            },
            page: function (data) {
                return data.obj.page;
            },
            total: function (data) {
                return data.obj.total;
            },
            records: function (data) {
                return data.obj.records;
            },
            repeatitems:false
        },
        rownumbers:true,
        colNames:['编号','标题','内容','创建时间','修改时间','操作'],
        colModel:[
            {name:'id',index:'id', width:30,align:'center',sortable:false,search:false,formatter:'integer',key:true,hidden:true},
            {name:'title',index:'title', width:80,align:'center',sortable:false,search:true, stype:'text'},
            {name:'content',index:'content', width:180,align:'center',sortable:false,search:true, stype:'text'},
            {name:'createTime',index:'createTime', width:100,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    return setDateFormat(new Date(cellValue));
                }},
            {name:'updateTime',index:'updateTime', width:100,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    return setDateFormat(new Date(cellValue));
                }},
            {name:'operation',index:'operation', width:80, sortable:false,align:'center',sortable:false,search:false,formatter:function (cellValue,index,rowObject) {
                    var id = rowObject['id'];
                    var deleted = rowObject['deleted'];
                    var str="";
                    str +="<button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"Send.updateModal("+id+");\">修改</button>&nbsp;&nbsp;";
                    str +="<button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"Send.delete("+id+");\">删除</button>";
                    return str;
                }}
        ],
        gridComplete: function () {
            // $("#grid-table").jqGrid('setLabel','rn', '编号', {'text-align':'center','vertical-align':'middle'},'');
        },
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
//刷新
Send.reload = function(){
    Send.table.setGridParam().trigger('reloadGrid');
}
//删除
Send.delete = function(id){
    if($("#a").find("i").hasClass("fa-chevron-up")){
        $("#a").trigger("click");
    }
    warning("确定要删除吗？","",function() {
        $.ajax({
            url:"/notice/delete?id="+id,
            type:"GET",
            dataType:"JSON",
            success:function (r) {
                if(r.code===0){
                    success("删除成功");
                    Send.reload();
                }
            }
        });
    });
}
//修改弹窗
Send.updateModal = function(id){
    $.ajax({
        url:"/send/updateModal?id="+id,
        type:"GET",
        dataType:"JSON",
        success:function (r) {
            if(r.code===0){
                var data = r.obj;
                var elem = $("#updateModal");
                elem.find("#updateTitle").val(data.title);
                $("#noticeid").val(data.id);
                var content = data.content;
                Ue2setContent(content,false);
                $("#updateModal").modal();
            }
        }
    });
}
//修改
Send.update = function () {
    var elem = $("#updateModal");
    var params = {};
    params.title = elem.find("#updateTitle").val();
    params.content = Send.ue2.getContent();
    params.id = parseInt($("#noticeid").val());
    $.ajax({
        url:"/send/update",
        data:JSON.stringify(params),
        type:"POST",
        dataType:"JSON",
        contentType:"application/json;charset=utf8",
        success:function (r) {
            if(r.code===0){
                $("#updateModal").modal('hide');
                Send.reload();
                success("修改成功");
            }
        }
    });
}
//富文本框赋值
function Ue2setContent(content,isAppendTo){
    Send.ue2.setContent(content,isAppendTo);
}
var clock;
$(function () {
    Send.ue = UE.getEditor('editor');
    Send.ue2 = UE.getEditor('editor2');
    Send.table = Send.initJqGrid();
    clock = setInterval(function () {
        if($('body').hasClass('modal-open')){
            if($("#a").find("i").hasClass("fa-chevron-up")){
                $("#a").trigger('click');
            }
        }
    },100);
});