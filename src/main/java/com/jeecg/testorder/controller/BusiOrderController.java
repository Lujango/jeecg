package com.jeecg.testorder.controller;
import com.jeecg.testorder.entity.BusiOrderEntity;
import com.jeecg.testorder.service.BusiOrderServiceI;
import com.jeecg.testorder.page.BusiOrderPage;
import com.jeecg.order.entity.BusiOrderSalerEntity;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 业务单
 * @author onlineGenerator
 * @date 2016-08-27 14:22:49
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/busiOrderController")
public class BusiOrderController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BusiOrderController.class);

	@Autowired
	private BusiOrderServiceI busiOrderService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 业务单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/testorder/busiOrderList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(BusiOrderEntity busiOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BusiOrderEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, busiOrder);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.busiOrderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除业务单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BusiOrderEntity busiOrder, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		busiOrder = systemService.getEntity(BusiOrderEntity.class, busiOrder.getId());
		String message = "业务单删除成功";
		try{
			busiOrderService.delMain(busiOrder);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "业务单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除业务单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "业务单删除成功";
		try{
			for(String id:ids.split(",")){
				BusiOrderEntity busiOrder = systemService.getEntity(BusiOrderEntity.class,
				id
				);
				busiOrderService.delMain(busiOrder);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "业务单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加业务单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BusiOrderEntity busiOrder,BusiOrderPage busiOrderPage, HttpServletRequest request) {
		List<BusiOrderSalerEntity> busiOrderSalerList =  busiOrderPage.getBusiOrderSalerList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			busiOrderService.addMain(busiOrder, busiOrderSalerList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "业务单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新业务单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BusiOrderEntity busiOrder,BusiOrderPage busiOrderPage, HttpServletRequest request) {
		List<BusiOrderSalerEntity> busiOrderSalerList =  busiOrderPage.getBusiOrderSalerList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			busiOrderService.updateMain(busiOrder, busiOrderSalerList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新业务单失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 业务单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BusiOrderEntity busiOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(busiOrder.getId())) {
			busiOrder = busiOrderService.getEntity(BusiOrderEntity.class, busiOrder.getId());
			req.setAttribute("busiOrderPage", busiOrder);
		}
		return new ModelAndView("com/jeecg/testorder/busiOrder-add");
	}
	
	/**
	 * 业务单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BusiOrderEntity busiOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(busiOrder.getId())) {
			busiOrder = busiOrderService.getEntity(BusiOrderEntity.class, busiOrder.getId());
			req.setAttribute("busiOrderPage", busiOrder);
		}
		return new ModelAndView("com/jeecg/testorder/busiOrder-update");
	}
	
	
	/**
	 * 加载明细列表[业务单测试]
	 * 
	 * @return
	 */
	@RequestMapping(params = "busiOrderSalerList")
	public ModelAndView busiOrderSalerList(BusiOrderEntity busiOrder, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = busiOrder.getId();
		//===================================================================================
		//查询-业务单测试
	    String hql0 = "from BusiOrderSalerEntity where 1 = 1 AND p_ID = ? ";
	    try{
	    	List<BusiOrderSalerEntity> busiOrderSalerEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("busiOrderSalerList", busiOrderSalerEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/order/busiOrderSalerList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(BusiOrderEntity busiOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(BusiOrderEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, busiOrder);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<BusiOrderEntity> list=this.busiOrderService.getListByCriteriaQuery(cq, false);
    	List<BusiOrderPage> pageList=new ArrayList<BusiOrderPage>();
        if(list!=null&&list.size()>0){
        	for(BusiOrderEntity entity:list){
        		try{
        		BusiOrderPage page=new BusiOrderPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from BusiOrderSalerEntity where 1 = 1 AND p_ID = ? ";
        	        List<BusiOrderSalerEntity> busiOrderSalerEntityList = systemService.findHql(hql0,id0);
            		page.setBusiOrderSalerList(busiOrderSalerEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"业务单");
        map.put(NormalExcelConstants.CLASS,BusiOrderPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("业务单列表", "导出人:Jeecg",
            "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,pageList);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

    /**
	 * 通过excel导入数据
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(2);
			params.setNeedSave(true);
			try {
				List<BusiOrderPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), BusiOrderPage.class, params);
				BusiOrderEntity entity1=null;
				for (BusiOrderPage page : list) {
					entity1=new BusiOrderEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            busiOrderService.addMain(entity1, page.getBusiOrderSalerList());
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			}
			return j;
	}
	/**
	* 导出excel 使模板
	*/
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME,"业务单");
		map.put(NormalExcelConstants.CLASS,BusiOrderPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("业务单列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
		"导出信息"));
		map.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	* 导入功能跳转
	*
	* @return
	*/
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "busiOrderController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<BusiOrderEntity> list() {
		List<BusiOrderEntity> listBusiOrders=busiOrderService.getList(BusiOrderEntity.class);
		return listBusiOrders;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		BusiOrderEntity task = busiOrderService.get(BusiOrderEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody BusiOrderPage busiOrderPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BusiOrderPage>> failures = validator.validate(busiOrderPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<BusiOrderSalerEntity> busiOrderSalerList =  busiOrderPage.getBusiOrderSalerList();
		
		BusiOrderEntity busiOrder = new BusiOrderEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(busiOrder,busiOrderPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		busiOrderService.addMain(busiOrder, busiOrderSalerList);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = busiOrderPage.getId();
		URI uri = uriBuilder.path("/rest/busiOrderController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody BusiOrderPage busiOrderPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BusiOrderPage>> failures = validator.validate(busiOrderPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<BusiOrderSalerEntity> busiOrderSalerList =  busiOrderPage.getBusiOrderSalerList();
		
		BusiOrderEntity busiOrder = new BusiOrderEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(busiOrder,busiOrderPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		busiOrderService.updateMain(busiOrder, busiOrderSalerList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		BusiOrderEntity busiOrder = busiOrderService.get(BusiOrderEntity.class, id);
		busiOrderService.delMain(busiOrder);
	}
}
