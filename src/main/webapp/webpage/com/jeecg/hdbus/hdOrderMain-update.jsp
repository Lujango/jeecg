<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>业务单主表</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="online/template/ledefault/css/vendor.css">
  <link rel="stylesheet" href="online/template/ledefault/css/bootstrap-theme.css">
  <link rel="stylesheet" href="online/template/ledefault/css/bootstrap.css">
  <link rel="stylesheet" href="online/template/ledefault/css/app.css">
  
  <link rel="stylesheet" href="plug-in/Validform/css/metrole/style.css" type="text/css"/>
  <link rel="stylesheet" href="plug-in/Validform/css/metrole/tablefrom.css" type="text/css"/>
  <script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.js"></script>
  <script type="text/javascript" src="plug-in/tools/dataformat.js"></script>
  <script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.1.3.2.js"></script>
  <script type="text/javascript" src="plug-in/easyui/locale/zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/tools/syUtil.js"></script>
  <script type="text/javascript" src="plug-in/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js"></script>
  <script type="text/javascript" src="plug-in/tools/curdtools_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/tools/easyuiextend.js"></script>
  <script type="text/javascript" src="plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/js/Validform_Datatype_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/js/datatype_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js"></script>
  <link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css"></link>
  <script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
  <link rel="stylesheet" href="plug-in/umeditor/themes/default/css/umeditor.css" type="text/css"></link>
  <script type="text/javascript" src="plug-in/umeditor/umeditor.config.js"></script>
  <script type="text/javascript" src="plug-in/umeditor/umeditor.min.js"></script>
  <script type="text/javascript" src="plug-in/umeditor/lang/zh-cn/zh-cn.js"></script>
</head>


 <script type="text/javascript">
 $(document).ready(function(){
	 init();
	 $("#jform_tab .con-wrapper").hide(); //Hide all tab content  
	 $("#jform_tab li:first").addClass("active").show(); //Activate first tab  
	 $("#jform_tab .con-wrapper:first").show(); //Show first tab content
	 
	 
	 //On Click Event  
    $("#jform_tab li").click(function() {  
        $("#jform_tab li").removeClass("active"); //Remove any "active" class  
        $(this).addClass("active"); //Add "active" class to selected tab  
        $("#jform_tab .con-wrapper").hide(); //Hide all tab content  
        var activeTab = $(this).find("a").attr("href"); //Find the rel attribute value to identify the active tab + content  
        $(activeTab).fadeIn(); //Fade in the active content
        //$(""+activeTab).show();   
        if( $(activeTab).html()!="") {
        	return false;
        }else{
        	$(activeTab).html('正在加载内容，请稍后...');
        	var url = $(this).attr("tab-ajax-url");
        	$.post(url, {}, function(data) {
        		 //$(this).attr("tab-ajax-cached", true);
        		$(activeTab).html(data);
        		
            });
        }  
        return false;  
    });  
  });
  //初始化下标
	function resetTrNum(tableId) {
		$tbody = $("#"+tableId+"");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function(){
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				if(name!=null){
					if (name.indexOf("#index#") >= 0){
						$this.attr("name",name.replace('#index#',i));
					}else{
						var s = name.indexOf("[");
						var e = name.indexOf("]");
						var new_name = name.substring(s+1,e);
						$this.attr("name",name.replace(new_name,i));
					}
				}
			});
			$(this).find('div[name=\'xh\']').html(i+1);
		});
	}
	
	function init(){
    	var tabHead =$("#jform_tab li:first");
    	var tabBox = $("#jform_tab .con-wrapper:first"); 
    	var url = tabHead.attr("tab-ajax-url");
    	tabBox.html('正在加载内容，请稍后...');
    	$.post(url, {}, function(data) {
            tabBox.html(data);
    		//tabHead.attr("tab-ajax-cached", true);
        });
    }
 </script>
 <body>
  <form id="formobj" action="hdOrderMainController.do?doUpdate" name="formobj" method="post"><input type="hidden" id="btn_sub" class="btn_sub"/>
				
			<input type="hidden" id="btn_sub" class="btn_sub"/>
			<input type="hidden" name="id" value='${hdOrderMainPage.id}' >
			
			
			<div class="tab-wrapper">
			    <!-- tab -->
			    <ul class="nav nav-tabs">
			      <li role="presentation" class="active"><a href="javascript:void(0);">业务单主表</a></li>
			    </ul>
			    <!-- tab内容 -->
			    <div class="con-wrapper" style="display: block;">
			      <div class="row form-wrapper">
							<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>接单时间：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="orderGetTime" name="orderGetTime" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${hdOrderMainPage.orderGetTime}' type="date" pattern="yyyy-MM-dd"/>'>
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">接单时间</label>
			          </div>
			          
			        
			          <div class="col-xs-3 text-center">
			          	<b>业务单主管：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="leader" name="leader" type="text" class="form-control"  value='${hdOrderMainPage.leader}'>
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">业务单主管</label>
			          </div>
							</div>
			          
			        

			     </div>
			   </div>
			   
			   <div class="con-wrapper" style="display: block;"></div>
	</div>
		
			
			
<script type="text/javascript">
   $(function(){
    //查看模式情况下,删除和上传附件功能禁止使用
	if(location.href.indexOf("load=detail")!=-1){
		$(".jeecgDetail").hide();
	}
	
	if(location.href.indexOf("mode=read")!=-1){
		//查看模式控件禁用
		$("#formobj").find(":input").attr("disabled","disabled");
	}
	if(location.href.indexOf("mode=onbutton")!=-1){
		//其他模式显示提交按钮
		$("#sub_tr").show();
	}
   });

  var neibuClickFlag = false;
  function neibuClick() {
	  neibuClickFlag = true; 
	  $('#btn_sub').trigger('click');
  }
</script>

<div id="jform_tab" class="tab-wrapper">
	<!-- tab -->
    <ul class="nav nav-tabs">
		    	<li role="presentation" tab-ajax-url="hdOrderMainController.do?hdOrderBuyList&id=${hdOrderMainPage.id}"><a href="#con-wrapper0">买方信息</a></li>
		    	<li role="presentation" tab-ajax-url="hdOrderMainController.do?hdOrderSellList&id=${hdOrderMainPage.id}"><a href="#con-wrapper1">卖方信息</a></li>
    </ul>
    
	     <div class="con-wrapper" id="con-wrapper0" style="display: none;"></div>
	     <div class="con-wrapper" id="con-wrapper1" style="display: none;"></div>
</div>


			
		<div align="center"  id = "sub_tr" style="display: none;" > <input type="button" value="提交" onclick="neibuClick();" class="ui_state_highlight"></div>
		<script type="text/javascript">
		$(function() {
			$("#formobj").Validform({
				tiptype: 1,
				btnSubmit: "#btn_sub",
				btnReset: "#btn_reset",
				ajaxPost: true,
				beforeSubmit: function(curform) {
					var tag = true;
					//提交前处理
					return tag;
				},
				usePlugin: {
					passwordstrength: {
						minLen: 6,
						maxLen: 18,
						trigger: function(obj, error) {
							if (error) {
								obj.parent().next().find(".Validform_checktip").show();
								obj.find(".passwordStrength").hide();
							} else {
								$(".passwordStrength").show();
								obj.parent().next().find(".Validform_checktip").hide();
							}
						}
					}
				},
				callback: function(data) {
					if (data.success == true) {
						 var win = frameElement.api.opener;
						 win.reloadTable();
						 win.tip(data.msg);
						 frameElement.api.close();
					} else {
						if (data.responseText == '' || data.responseText == undefined) {
							$.messager.alert('错误', data.msg);
							$.Hidemsg();
						} else {
							try {
								var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
								$.messager.alert('错误', emsg);
								$.Hidemsg();
							} catch(ex) {
								$.messager.alert('错误', data.responseText + '');
							}
						}
						return false;
					}
				}
			});
		});
		</script>
		
		</form>
		<!-- 添加 产品明细 模版 -->
		<table style="display:none">
			<tbody id="add_hdOrderBuy_table_template">
				<tr>
					 <th scope="row"><div name="xh"></div></th>
					 <td><input style="width:20px;" type="checkbox" name="ck"/></td>
						  <td align="left">
							  	<input name="hdOrderBuyList[#index#].name" maxlength="32" 
							  		type="text" class="form-control"  style="width:120px;" >
							  <label class="Validform_label" style="display: none;">姓名</label>
						  </td>
						  <td align="left">
							  	<input name="hdOrderBuyList[#index#].tel" maxlength="32" 
							  		type="text" class="form-control"  style="width:120px;" >
							  <label class="Validform_label" style="display: none;">电话</label>
						  </td>
						  <td align="left">
							  	<input name="hdOrderBuyList[#index#].idNo" maxlength="32" 
							  		type="text" class="form-control"  style="width:120px;" >
							  <label class="Validform_label" style="display: none;">身份证号码</label>
						  </td>
						  <td align="left">
							  	<input name="hdOrderBuyList[#index#].addr" maxlength="32" 
							  		type="text" class="form-control"  style="width:120px;" >
							  <label class="Validform_label" style="display: none;">联系地址</label>
						  </td>
						  <td align="left">
							  	<input name="hdOrderBuyList[#index#].credit" maxlength="32" 
							  		type="text" class="form-control"  style="width:120px;" >
							  <label class="Validform_label" style="display: none;">征信情况</label>
						  </td>
					</tr>
				 </tbody>
			<tbody id="add_hdOrderSell_table_template">
				<tr>
					 <th scope="row"><div name="xh"></div></th>
					 <td><input style="width:20px;" type="checkbox" name="ck"/></td>
						  <td align="left">
							  	<input name="hdOrderSellList[#index#].name" maxlength="32" 
							  		type="text" class="form-control"  style="width:120px;" >
							  <label class="Validform_label" style="display: none;">姓名</label>
						  </td>
						  <td align="left">
							  	<input name="hdOrderSellList[#index#].tel" maxlength="32" 
							  		type="text" class="form-control"  style="width:120px;" >
							  <label class="Validform_label" style="display: none;">电话</label>
						  </td>
						  <td align="left">
							  	<input name="hdOrderSellList[#index#].idNo" maxlength="32" 
							  		type="text" class="form-control"  style="width:120px;" >
							  <label class="Validform_label" style="display: none;">身份证号码</label>
						  </td>
						  <td align="left">
							  	<input name="hdOrderSellList[#index#].addr" maxlength="32" 
							  		type="text" class="form-control"  style="width:120px;" >
							  <label class="Validform_label" style="display: none;">联系地址</label>
						  </td>
						  <td align="left">
							  	<input name="hdOrderSellList[#index#].credit" maxlength="32" 
							  		type="text" class="form-control"  style="width:120px;" >
							  <label class="Validform_label" style="display: none;">征信情况</label>
						  </td>
						  <td align="left">
							  	<input name="hdOrderSellList[#index#].risk" maxlength="32" 
							  		type="text" class="form-control"  style="width:120px;" >
							  <label class="Validform_label" style="display: none;">风险等级</label>
						  </td>
					</tr>
				 </tbody>
		</table>
	<script src = "webpage/com/jeecg/hdbus/hdOrderMain.js"></script>	
 </body>
 </html>