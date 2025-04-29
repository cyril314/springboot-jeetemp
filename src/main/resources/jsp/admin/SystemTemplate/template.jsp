<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	$("#temaplate").markItUp(mySettings); //mySettings
	$('#temaplateSubmit').bind('click',function(){
		$.messager.progress();
		$('#temaplateForm').form('submit', {
			url : '${demoPath}admin/template/save.html',
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	
				}
				return isValid;	
			},
			success : function(data) {
				$.messager.progress('close');
				var result=eval('('+data+')');
				if (result.success)
					$.messager.alert('info', result.msg, 'info');
				else
					$.messager.alert('info', result.msg, 'error');
			}
		});
	});
</script>
<style>
.markItUp{
   width: 98%;
}
</style>
<div id="temaplateProgressbar" style="width:400px;"></div> 
<div style="padding: 5px;" id="">
	<form id="temaplateForm" method="post">
		<textarea id="temaplate" style="width: 96%" rows="20" name="text" >
CREATE TABLE `TbsA` (
  `id` char(20) collate utf8_unicode_ci NOT NULL COMMENT '主键',
  `name` varchar(50) collate utf8_unicode_ci default NULL COMMENT '名称',
  `createTime` timestamp NOT NULL default CURRENT_TIMESTAMP  '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT '测试表A';

CREATE TABLE `TbsB` (
  `id` char(20) collate utf8_unicode_ci NOT NULL COMMENT '主键',
  `createTime` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '创建时间',
  `name` varchar(200) collate utf8_unicode_ci default NULL COMMENT '名称',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT '测试表B';

		</textarea>
		<table>
			<tr>
				<td><label>文件名称</label></td>
				<td><input type="text" name="zipName" value="tableTest"/></td>
				<!--  
				<td>
				 <input type="checkbox" name="isAdd" checked="checked"><label> 加上模板表</label>
				</td>
				-->
				<td>
				   <select name="parseType">
				      <option value="0">SQL DDL create语句 解析器</option>
				      <option value="1">JSON 解析器</option>
				   </select>
				</td>
				<td><select name="type">
				 <!--  <option value="6" selected="selected">所有模式生成</option>-->
				 <option value="5">简洁模式生成</option>
				</select></td>
				<td><label>后缀名</label></td>
				<td><input type="text" name="suffix" value=".html" ></td>
				<td>
				<a  href="javascript:void(0)" id="temaplateSubmit" class="easyui-linkbutton">构建</a>
				</td>
				<td>
				 <a href="javascript:window.open('${path}download.html?url=autoCreate/')" id="temaplateSubmit" class="easyui-linkbutton">去下载</a>
				</td>
			</tr>
		</table>
	</form>
</div>


