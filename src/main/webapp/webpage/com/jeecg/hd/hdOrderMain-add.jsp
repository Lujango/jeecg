<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>业务单主表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
  });
 </script>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="hdOrderMainController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${hdOrderMainPage.id }">
					<input id="createName" name="createName" type="hidden" value="${hdOrderMainPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${hdOrderMainPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${hdOrderMainPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${hdOrderMainPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${hdOrderMainPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${hdOrderMainPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${hdOrderMainPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${hdOrderMainPage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${hdOrderMainPage.bpmStatus }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">接单时间:</label>
			</td>
			<td class="value">
					  <input id="orderGetTime" name="orderGetTime" type="text" style="width: 150px" 
							 class="Wdate" onClick="WdatePicker()" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">接单时间</label>
			</td>
			<td align="right">
				<label class="Validform_label">业务单主管:</label>
			</td>
			<td class="value">
		     	 <input id="leader" name="leader" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务单主管</label>
			</td>
		</tr>
	</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="hdOrderMainController.do?hdOrderBuyList&id=${hdOrderMainPage.id}" icon="icon-search" title="买方信息" id="hdOrderBuy"></t:tab>
				 <t:tab href="hdOrderMainController.do?hdOrderSellList&id=${hdOrderMainPage.id}" icon="icon-search" title="卖方信息" id="hdOrderSell"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
	<table style="display:none">
	<tbody id="add_hdOrderBuy_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  	<input name="hdOrderBuyList[#index#].name" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">姓名</label>
				  </td>
				  <td align="left">
					  	<input name="hdOrderBuyList[#index#].tel" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">电话</label>
				  </td>
				  <td align="left">
					  	<input name="hdOrderBuyList[#index#].idNo" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">身份证号码</label>
				  </td>
				  <td align="left">
					  	<input name="hdOrderBuyList[#index#].addr" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">联系地址</label>
				  </td>
				  <td align="left">
					  	<input name="hdOrderBuyList[#index#].credit" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">征信情况</label>
				  </td>
			</tr>
		 </tbody>
	<tbody id="add_hdOrderSell_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  	<input name="hdOrderSellList[#index#].name" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">姓名</label>
				  </td>
				  <td align="left">
					  	<input name="hdOrderSellList[#index#].tel" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">电话</label>
				  </td>
				  <td align="left">
					  	<input name="hdOrderSellList[#index#].idNo" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">身份证号码</label>
				  </td>
				  <td align="left">
					  	<input name="hdOrderSellList[#index#].addr" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">联系地址</label>
				  </td>
				  <td align="left">
					  	<input name="hdOrderSellList[#index#].credit" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">征信情况</label>
				  </td>
				  <td align="left">
					  	<input name="hdOrderSellList[#index#].risk" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">风险等级</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/jeecg/hd/hdOrderMain.js"></script>
	