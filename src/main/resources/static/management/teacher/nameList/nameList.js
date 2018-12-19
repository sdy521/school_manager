 var NameList = {
    tableId:"#grid-table",
    pagerId:"#grid-pager",
    table:null,
}
NameList.initJqGrid = function(){
    var tableInstance = $("#grid-table").jqGrid({
        caption: '手机产品列表',
        url:'#',
        datatype: "json",
        colNames:['编号','名称','主屏尺寸','操作系统','电池容量', '价格(￥)','操作'],
        colModel:[
            {name:'sn',index:'sn', width:80,align:'center'},
            {name:'title',index:'title', width:180},
            {name:'size',index:'size', width:120},
            {name:'os',index:'os', width:120},
            {name:'charge',index:'charge', width:100,align:'center'},
            {name:'price',index:'price', width:80,align:'center'},
            {name:'opt',index:'opt', width:80, sortable:false, align:'center'}
        ],
        rowNum:10,
        rowList:[10,20,30],
        pager: '#pager',
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