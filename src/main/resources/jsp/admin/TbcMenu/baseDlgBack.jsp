<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 弹出框 tbcMenuDlg -->
<div id="tbcMenuDlg">
	<form id="tbcMenuFm" method="post">
		<table>
			<tr>
				<td><label> 唯一: </label></td>
				<td><input name="id" class="easyui-validatebox" /></td>

				<td><label> 父节点: </label></td>
				<td><input name="parentId" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> 名称: </label></td>
				<td><input name="name" class="easyui-validatebox" /></td>

				<td><label> 菜单类型: </label></td>
				<td><input name="isMenu" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> 系统类型: </label></td>
				<td><input name="type" class="easyui-validatebox" /></td>

				<td><label> 排序: </label></td>
				<td><input name="sortNumber" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> 地址: </label></td>
				<td><input name="url" class="easyui-validatebox" /></td>

				<td><label> 按钮: </label></td>
				<td><input name="button" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> 状态: </label></td>
				<td><input name="status" class="easyui-validatebox" /></td>

				<td><label> 时间: </label></td>
				<td><input name="createTime" class="easyui-validatebox" /></td>

			</tr>

		</table>
	</form>
</div>