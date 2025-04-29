<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	//Add and Edit
	function tbcMenuHelpGridAddAndEdit(title, url, type) {
		if (type == 1) { //edit
			var rows = $('#tbcMenuHelpGrid').treegrid('getSelections');
			if (rows.length != 1) {
				$.messager.alert('消息', '请钩择一行数据!', 'info');
				return;
			}
		}
		$('<div/>').dialog({
			href : '${demoPath}admin/TbcMenuHelp/baseDlg.html',
			modal : true,
			title : title,
			top : '15%',
			left : '30%',
			width : 600,
			resizable:true,
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					tbcMenuHelpGridSubmit(url);
					$(this).closest('.window-body').dialog('destroy');
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$(this).closest('.window-body').dialog('destroy');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				if (type == 0) {
					$('#tbcMenuHelpFm').form('clear');
				} else {
					var rows = $('#tbcMenuHelpGrid').treegrid('getSelections');
					if (rows.length == 1) {
						$('#tbcMenuHelpFm').form('load', rows[0]);
					} else {
						$.messager.alert('消息', '请钩择一行数据!', 'info');
					}
				}
			}
		});
	}
	
	//Del
	function tbcMenuHelpGridDel() {
		var rows = $('#tbcMenuHelpGrid').treegrid('getSelections');
		if (rows.length > 0) {
		    var ids = '';
			for ( var i = 0; i < rows.length; i++) {
						ids += 'ids=' + rows[i].id + '&';
			}
			ids = ids.substring(0, ids.length - 1);
			var url = '${demoPath}admin/TbcMenuHelp/del.html?' + ids;
			$.messager.confirm('Confirm','确定要删除选择的数据吗?', function(r) {
				if (r) {
				    $.get(url, function(result){
						if (result.success){ 
							$('#tbcMenuHelpGrid').treegrid('reload');
							$('#tbcMenuHelpGrid').treegrid('clearSelections');
						} else {  
						    $.messager.show({ title: 'Error', msg : result.msg }); 
						} 
				    }, 'json');
				}
			});
		} else {
			$.messager.alert('消息', '请选择要删除的数据!','info');
		}
	}
	
	//Reload
	function tbcMenuHelpGridReload() {
		$('#tbcMenuHelpGrid').treegrid('options').pageNumber=1;
		var url='${demoPath}admin/TbcMenuHelp/data.html?gridName=treegrid';
		$('#tbcMenuHelpGrid').treegrid('options').url=url;
		$('#tbcMenuHelpGrid').treegrid('reload');
	}
	
	//tbcMenuHelpGridSubmit  submit
	function tbcMenuHelpGridSubmit(url) {
	    $('#tbcMenuHelpFm').form('submit',{  
	        url: url,  
	        onSubmit: function(){  
	            return $(this).form('validate');  
	        },  
	        success: function(result){  
	            var result = eval('('+result+')');  
	            if (result.success){  
	                $('#tbcMenuHelpDlg').dialog('close');      // close the dialog 
	            	$('#tbcMenuHelpGrid').treegrid('reload');
	            } else {  
	            	$.messager.show({ title: 'Error',msg: result.msg }); 
	            }  
	        }  
	    });
	}
	
	//高级搜索 del row
	function tbcMenuHelpSearchRemove(curr) {
		if ($(curr).closest('table').find('tr').size() > 2) {
			$(curr).closest('tr').remove();
		} else {
			alert('该行不允许删除');
		}
	}
	
	//高级查询
	function tbcMenuHelpSearch() {
		$('<div/>').dialog({
			href : '${demoPath}admin/TbcMenuHelp/searchDlg.html',
			modal : true,
			title : '高级查询',
			top : 120,
			width : 480,
			buttons : [ {
				text : '增加一行',
				iconCls : 'icon-add',
				handler : function() {
					var currObj = $(this).closest('.panel').find('table');
					currObj.find('tr:last').clone().appendTo(currObj);
					//currObj.find('tr:last td *[disabled]').removeAttr("disabled");
				}
			}, {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					$('#tbcMenuHelpGrid').treegrid('options').pageNumber=1;
					$('#tbcMenuHelpGrid').treegrid('reload',serializeObjectEx($('#tbcMenuHelpSearchFm')));
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$(this).closest('.window-body').dialog('destroy');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	}
	
	//导出
	function tbcMenuHelpGridExport(){
		window.location="${demoPath}admin/TbcMenuHelp/export.html";
		//var panel=$('#tbcTempGrid').treegrid('getPanel');
		//var options=panel.panel('options');
		//alert(options.method);
		//console.dir(options);
	}
	
	//导入
	function tbcMenuHelpGridImport(){
		$('<div/>').dialog({
			href : '${demoPath}admin/TbcMenuHelp/importDlg.html',
			modal : true,
			title : '导入',
			top : '15%',
			left : '30%',
			width : 600,
			height: 300,
			resizable:true,
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					//$(this).closest('.window-body').dialog('destroy');
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$(this).closest('.window-body').dialog('destroy');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				
			}
		});
	}
	
</script>
    

	<!-- 中  treegrid-->
    <div data-options="region:'center',border : false">
		<!-- treegrid toolbar -->
		<table id="tbcMenuHelpGrid"  class="easyui-treegrid"  data-options="	
			url:'${demoPath}admin/TbcMenuHelp/data.html?gridName=treegrid',
			/*onLoadSuccess : function(row,data){
			     $(this).treegrid('collapseAll');
			},*/
			columns:[ [  

			{field:'id',title:'唯一',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'parentId',title:'父节点',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'name',title:'名称',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'title',title:'标题',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'tags',title:'标签',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'content',title:'内容',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'isMenu',title:'菜单类型',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'type',title:'系统类型',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'sortNumber',title:'排序',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'url',title:'地址',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'button',title:'按钮',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'status',title:'状态',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'createTime',title:'时间',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}}			
			] ],
			toolbar:'#tbcMenuHelpGridToolbar'
		"/>
		
		<!-- treegrid toolbar -->
		<div id="tbcMenuHelpGridToolbar">
			<div style="margin-bottom:5px">
				<c:forEach items="${buttons}" var="button">
			         ${button}
			    </c:forEach>
				<%-- 
				<a href="javascript:void(0)" onclick="javascript:tbcMenuHelpGridAddAndEdit('添加  tbcMenuHelp','${demoPath}admin/TbcMenuHelp/add.html',0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
				<a href="javascript:void(0)" onclick="javascript:tbcMenuHelpGridAddAndEdit('修改  tbcMenuHelp','${demoPath}admin/TbcMenuHelp/save.html',1)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑 </a>  
				<a href="javascript:void(0)" onclick="javascript:tbcMenuHelpGridDel()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">删除</a>
				<a href="javascript:void(0)" onclick="javascript:tbcMenuHelpGridReload()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
				<a href="javascript:void(0)" onclick="javascript:tbcMenuHelpGridExport()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导出</a>
				<a href="javascript:void(0)" onclick="javascript:tbcMenuHelpGridImport()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导入</a>
				
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">后退</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">前进</a>
				--%>
				<!-- tbcMenuHelpGridToolbarSearch -->
				<input class="easyui-searchbox" data-options="
					menu :'#tbcMenuHelpGridToolbarSearch',
					prompt :'模糊查询',
					searcher : function(value,name){
						var str='{searchType:1,'+name+':\''+value+'\'}';
				        var obj = eval('('+str+')');
				        $('#tbcMenuHelpGrid').treegrid('options').pageNumber=1;
				        $('#tbcMenuHelpGrid').treegrid({queryParams: obj});
					}
				"/>
				<div id="tbcMenuHelpGridToolbarSearch">
					<div name="id">唯一</div>
					<div name="parentId">父节点</div>
					<div name="name">名称</div>
					<div name="title">标题</div>
					<div name="tags">标签</div>
					<div name="content">内容</div>
					<div name="isMenu">菜单类型</div>
					<div name="type">系统类型</div>
					<div name="sortNumber">排序</div>
					<div name="url">地址</div>
					<div name="button">按钮</div>
					<div name="status">状态</div>
					<div name="createTime">时间</div>
				</div>
				<a href="javascript:void(0)" onclick="javascript:tbcMenuHelpSearch()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">高级查询</a>
			</div>
		</div>
	</div>
<!--  <div>-->