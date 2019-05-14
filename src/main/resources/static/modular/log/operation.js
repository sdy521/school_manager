var Operation={
    tableId:"#grid-table",
    pagerId:"#grid-pager",
    table:null,
}
Operation.initJqGrid = function(){
    var tableInstance = $("#grid-table").jqGrid({
        url:'/log/operationGrid',
        datatype: "json",
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
        colNames:['编号','操作名','操作方法','操作用户','操作时间'],
        colModel:[
            {name:'id',index:'id', width:80,align:'center',sortable:true,search:false,formatter:'integer',key:true},
            {name:'name',index:'name', width:80,align:'center',sortable:false,search:true, stype:'text'},
            {name:'method',index:'method', width:80,align:'center',sortable:false,search:true, stype:'text'},
            {name:'userName',index:'userName', width:80,align:'center',sortable:false,search:true, stype:'text'},
            {name:'createTime',index:'createTime', width:120,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    return dateFtt("yyyy-MM-dd hh:mm:ss",new Date(cellValue));
                }}
        ],
        page: 1,    //初始页码
        rowNum:10,
        rowList:[10,20,30],
        pager: '#grid-pager',
        autowidth: true,
        height:380,
        viewrecords: true,
        sortorder:'desc'
        //loadonce:true//为true时，不请求后台，直接从界面上排序
        // sortorder:"desc",//此属性只能用于loadonce为true时
        // multiselect: false,
        // multiselectWidth: 25,
        /*loadComplete : function() {
            var grid = $("#grid-table");
            var ids = grid.getDataIDs();
            for (var i = 0; i < ids.length; i++) {
                grid.setRowData (ids[i], false, {height: 40}); //设置成你要设定的固定行高
            }
        }*/
    });
    // NameList.jqSearch();
    return tableInstance;
}

$(function () {
    Operation.table = Operation.initJqGrid();
});