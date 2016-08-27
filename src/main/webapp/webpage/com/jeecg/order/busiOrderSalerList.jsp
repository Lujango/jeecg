<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addBusiOrderSalerBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delBusiOrderSalerBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addBusiOrderSalerBtn').bind('click', function(){   
 		 var tr =  $("#add_busiOrderSaler_table_template tr").clone();
	 	 $("#add_busiOrderSaler_table").append(tr);
	 	 resetTrNum('add_busiOrderSaler_table');
	 	 return false;
    });  
	$('#delBusiOrderSalerBtn').bind('click', function(){   
      	$("#add_busiOrderSaler_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_busiOrderSaler_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#busiOrderSaler_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addBusiOrderSalerBtn" href="#">添加</a> <a id="delBusiOrderSalerBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="busiOrderSaler_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						卖方姓名
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						卖方电话
				  </td>
	</tr>
	<tbody id="add_busiOrderSaler_table">
	<c:if test="${fn:length(busiOrderSalerList)  > 0 }">
		<c:forEach items="${busiOrderSalerList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="busiOrderSalerList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="busiOrderSalerList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="busiOrderSalerList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="busiOrderSalerList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="busiOrderSalerList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="busiOrderSalerList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="busiOrderSalerList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="busiOrderSalerList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
					<input name="busiOrderSalerList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
					<input name="busiOrderSalerList[${stuts.index }].bpmStatus" type="hidden" value="${poVal.bpmStatus }"/>
					<input name="busiOrderSalerList[${stuts.index }].pId" type="hidden" value="${poVal.pId }"/>
				   <td align="left">
					  	<input name="busiOrderSalerList[${stuts.index }].selerName" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" datatype="*" value="${poVal.selerName }">
					  <label class="Validform_label" style="display: none;">卖方姓名</label>
				   </td>
				   <td align="left">
					  	<input name="busiOrderSalerList[${stuts.index }].selerTel" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.selerTel }">
					  <label class="Validform_label" style="display: none;">卖方电话</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
