 var NameList = {
    tableId:"#grid-table",
    pagerId:"#grid-pager",
    table:null,
}
NameList.initJqGrid = function(){
    var tableInstance = $("#grid-table").jqGrid({
        caption: '老师列表',
        url:'/teacher_nameList/grid',
        datatype: "json",
        colNames:['编号','姓名','密码','创建时间','修改时间', '状态','操作'],
        colModel:[
            {name:'id',index:'id', width:80,align:'center'},
            {name:'name',index:'name', width:180},
            {name:'password',index:'password', width:120},
            {name:'createTime',index:'createTime', width:120},
            {name:'updateTime',index:'updateTime', width:100},
            {name:'deleted',index:'deleted', width:80},
            {name:'operation',index:'operation', width:80, sortable:false,formatter:function (cellValue,index,ObjectRows) {
                    return "11";
                }}
        ],
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
        rowNum:10,
        rowList:[10,20,30],
        pager: '#grid-pager',
        sortname: 'id',
        autowidth: true,
        height:280,
        viewrecords: true,
        multiselect: true,
        multiselectWidth: 25,
        sortable:true,
        sortorder: "asc"
    });
    return tableInstance;
}
$(function () {
    NameList.table = NameList.initJqGrid();
});