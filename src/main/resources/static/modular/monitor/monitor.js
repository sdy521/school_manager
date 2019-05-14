var Monitor = {
    tableId:"#grid-table",
    table:null,
}

Monitor.initJqGrid = function(){
    var tableInstance = $("#grid-table").jqGrid({
        url:'/monitor/grid',
        datatype: "json",
        jsonReader:{
            root: function (data) {
                return data.obj;
            },
            repeatitems:false
        },
        colNames:['编号','地址','端口','mysql状态'],
        colModel:[
            {name:'id',index:'id', width:50,align:'center',sortable:true,search:false,formatter:'integer',key:true},
            {name:'ip',index:'ip', width:70,align:'center',sortable:false,search:true, stype:'text'},
            {name:'port',index:'port', width:70,align:'center',sortable:false,search:true, stype:'text'},
            {name:'mysqlStatus',index:'mysqlStatus', width:80,align:'center',sortable:false,search:true, stype:'text',formatter:function (cellValue) {
                    if(cellValue==1){
                        return "<span><i class='fa fa-circle text-navy'></i>active</span>";
                    }else {
                        return "<span><i class='fa fa-circle text-muted'></i>dead</span>";
                    }
                }}
        ],
        autowidth: true,
        height:100,
        viewrecords: true,
    });
    return tableInstance;
}

$(function () {
    Monitor.table = Monitor.initJqGrid();
});