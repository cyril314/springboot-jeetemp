<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 弹出框 tbsBrowserNameDlg -->
<div id="tbsBrowserNameDlg">
	<form id="tbsBrowserNameFm" method="post">
		<table>
			<tr>
				<td><label> 主键: </label></td>
				<td><input name="id" class="easyui-validatebox" /></td>

				<td><label> 搜索: </label></td>
				<td><input name="browser" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> 昵称: </label></td>
				<td><input name="nickname" class="easyui-validatebox" /></td>

				<td><label> 类型: </label></td>
				<td><input name="type" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> 时间: </label></td>
				<td><input name="createTime" class="easyui-validatebox" /></td>

			</tr>

		</table>
	</form>
</div>