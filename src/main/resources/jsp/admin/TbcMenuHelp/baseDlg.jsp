<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 弹出框 tbcMenuHelpDlg -->
<div id="tbcMenuHelpDlg">
	<form id="tbcMenuHelpFm" method="post">
		<table>
			<tr>
				<td><label> 唯一: </label></td>
    			<td >
					<input name="id" class="easyui-validatebox"  readonly="readonly" 
										/>
				</td>


				<td><label> 父节点: </label></td>
    			<td >
				<input name="parentId" class="easyui-validatebox" type="text"
			   					 />
				</td>

	 		</tr>
	        <tr>

				<td><label> 名称: </label></td>
    			<td >
				<input name="name" class="easyui-validatebox" type="text"
			   					 />
				</td>


				<td><label> 标题: </label></td>
    			<td >
				<input name="title" class="easyui-validatebox" type="text"
			   					 />
				</td>

	 		</tr>
	        <tr>

				<td><label> 标签: </label></td>
    			<td >
				<input name="tags" class="easyui-validatebox" type="text"
			   					 />
				</td>


				<td><label> 内容: </label></td>
    			<td >
				<input name="content" class="easyui-validatebox" type="text"
			   					 />
				</td>

	 		</tr>
	        <tr>

				<td><label> 菜单类型: </label></td>
    			<td >
				<input name="isMenu" class="easyui-validatebox" type="text"
			   					 />
				</td>


				<td><label> 系统类型: </label></td>
    			<td >
				<input name="type" class="easyui-validatebox" type="text"
			   					 />
				</td>

	 		</tr>
	        <tr>

				<td><label> 排序: </label></td>
    			<td >
				<input name="sortNumber" class="easyui-validatebox" type="text"
			   					 />
				</td>


				<td><label> 地址: </label></td>
    			<td >
				<input name="url" class="easyui-validatebox" type="text"
			   					 />
				</td>

	 		</tr>
	        <tr>

				<td><label> 按钮: </label></td>
    			<td >
				<input name="button" class="easyui-validatebox" type="text"
			   					 />
				</td>


				<td><label> 状态: </label></td>
    			<td >
				<input name="status" class="easyui-validatebox" type="text"
			   					 />
				</td>

	 		</tr>
	        <tr>

				<td><label> 时间: </label></td>
    			<td >
				<input name="createTime"  class="easyui-datetimebox"
													 							/>
				</td>


		</table>
	</form>
</div>
