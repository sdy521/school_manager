var Send = {
    tableId:"#grid-table",
    pagerId:"#grid-pager",
    table:null,
    ue:null
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
            {name:'operation',index:'operation', width:50, sortable:false,align:'center',sortable:false,search:false,formatter:function (cellValue,index,rowObject) {
                    var id = rowObject['id'];
                    var deleted = rowObject['deleted'];
                    var str="";
                    str +="<button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"NameList.updateModal("+id+");\">修改</button>";
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
$(function () {
    Send.ue = UE.getEditor('editor');
    Send.table = Send.initJqGrid();
});