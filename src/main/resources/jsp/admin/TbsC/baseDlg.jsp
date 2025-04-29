<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 弹出框 tbsCDlg -->
<div id="tbsCDlg">
	<form id="tbsCFm" method="post">
		<table>
			<tr>
				<td><label> 主键: </label></td>
    			<td >
				<input name="id" class="easyui-validatebox" type="text"
			   					 />
				</td>


				<td><label> 关联A表: </label></td>
    			<td >
				<input name="aId" class="easyui-validatebox" type="text"
			   					 />
				</td>

	 		</tr>
	        <tr>

				<td><label> 关联B表: </label></td>
    			<td >
				<input name="bId" class="easyui-validatebox" type="text"
			   					 />
				</td>


				<td><label> 名称: </label></td>
    			<td >
				<input name="name" class="easyui-validatebox" type="text"
			   					 />
				</td>

	 		</tr>
	        <tr>

				<td><label> 创建时间: </label></td>
    			<td >
				<input name="createTime"  class="easyui-datetimebox"
													 							/>
				</td>

			</tr>

		</table>
	</form>
</div>
