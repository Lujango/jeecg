package com.jeecg.hd.controller;
import com.jeecg.hd.entity.HdOrderMainEntity;
import com.jeecg.hd.service.HdOrderMainServiceI;
import com.jeecg.hd.page.HdOrderMainPage;
import com.jeecg.buy.entity.HdOrderBuyEntity;
import com.jeecg.sell.entity.HdOrderSellEntity;
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
 * @Description: 业务单主表
 * @author onlineGenerator
 * @date 2016-08-27 18:11:19
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/hdOrderMainController")
public class HdOrderMainController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HdOrderMainController.class);

	@Autowired
	private HdOrderMainServiceI hdOrderMainService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 业务单主表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/hd/hdOrderMainList");
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
	public void datagrid(HdOrderMainEntity hdOrderMain,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HdOrderMainEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hdOrderMain);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.hdOrderMainService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除业务单主表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(HdOrderMainEntity hdOrderMain, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		hdOrderMain = systemService.getEntity(HdOrderMainEntity.class, hdOrderMain.getId());
		String message = "业务单主表删除成功";
		try{
			hdOrderMainService.delMain(hdOrderMain);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "业务单主表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除业务单主表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "业务单主表删除成功";
		try{
			for(String id:ids.split(",")){
				HdOrderMainEntity hdOrderMain = systemService.getEntity(HdOrderMainEntity.class,
				id
				);
				hdOrderMainService.delMain(hdOrderMain);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "业务单主表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加业务单主表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HdOrderMainEntity hdOrderMain,HdOrderMainPage hdOrderMainPage, HttpServletRequest request) {
		List<HdOrderBuyEntity> hdOrderBuyList =  hdOrderMainPage.getHdOrderBuyList();
		List<HdOrderSellEntity> hdOrderSellList =  hdOrderMainPage.getHdOrderSellList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			hdOrderMainService.addMain(hdOrderMain, hdOrderBuyList,hdOrderSellList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "业务单主表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新业务单主表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HdOrderMainEntity hdOrderMain,HdOrderMainPage hdOrderMainPage, HttpServletRequest request) {
		List<HdOrderBuyEntity> hdOrderBuyList =  hdOrderMainPage.getHdOrderBuyList();
		List<HdOrderSellEntity> hdOrderSellList =  hdOrderMainPage.getHdOrderSellList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			hdOrderMainService.updateMain(hdOrderMain, hdOrderBuyList,hdOrderSellList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新业务单主表失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 业务单主表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HdOrderMainEntity hdOrderMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hdOrderMain.getId())) {
			hdOrderMain = hdOrderMainService.getEntity(HdOrderMainEntity.class, hdOrderMain.getId());
			req.setAttribute("hdOrderMainPage", hdOrderMain);
		}
		return new ModelAndView("com/jeecg/hd/hdOrderMain-add");
	}
	
	/**
	 * 业务单主表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HdOrderMainEntity hdOrderMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hdOrderMain.getId())) {
			hdOrderMain = hdOrderMainService.getEntity(HdOrderMainEntity.class, hdOrderMain.getId());
			req.setAttribute("hdOrderMainPage", hdOrderMain);
		}
		return new ModelAndView("com/jeecg/hd/hdOrderMain-update");
	}
	
	
	/**
	 * 加载明细列表[买方信息]
	 * 
	 * @return
	 */
	@RequestMapping(params = "hdOrderBuyList")
	public ModelAndView hdOrderBuyList(HdOrderMainEntity hdOrderMain, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = hdOrderMain.getId();
		//===================================================================================
		//查询-买方信息
	    String hql0 = "from HdOrderBuyEntity where 1 = 1 AND p_ID = ? ";
	    try{
	    	List<HdOrderBuyEntity> hdOrderBuyEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("hdOrderBuyList", hdOrderBuyEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/buy/hdOrderBuyList");
	}
	/**
	 * 加载明细列表[卖方信息]
	 * 
	 * @return
	 */
	@RequestMapping(params = "hdOrderSellList")
	public ModelAndView hdOrderSellList(HdOrderMainEntity hdOrderMain, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id1 = hdOrderMain.getId();
		//===================================================================================
		//查询-卖方信息
	    String hql1 = "from HdOrderSellEntity where 1 = 1 AND p_ID = ? ";
	    try{
	    	List<HdOrderSellEntity> hdOrderSellEntityList = systemService.findHql(hql1,id1);
			req.setAttribute("hdOrderSellList", hdOrderSellEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/sell/hdOrderSellList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(HdOrderMainEntity hdOrderMain,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(HdOrderMainEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hdOrderMain);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<HdOrderMainEntity> list=this.hdOrderMainService.getListByCriteriaQuery(cq, false);
    	List<HdOrderMainPage> pageList=new ArrayList<HdOrderMainPage>();
        if(list!=null&&list.size()>0){
        	for(HdOrderMainEntity entity:list){
        		try{
        		HdOrderMainPage page=new HdOrderMainPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from HdOrderBuyEntity where 1 = 1 AND p_ID = ? ";
        	        List<HdOrderBuyEntity> hdOrderBuyEntityList = systemService.findHql(hql0,id0);
            		page.setHdOrderBuyList(hdOrderBuyEntityList);
            	    Object id1 = entity.getId();
				    String hql1 = "from HdOrderSellEntity where 1 = 1 AND p_ID = ? ";
        	        List<HdOrderSellEntity> hdOrderSellEntityList = systemService.findHql(hql1,id1);
            		page.setHdOrderSellList(hdOrderSellEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"业务单主表");
        map.put(NormalExcelConstants.CLASS,HdOrderMainPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("业务单主表列表", "导出人:Jeecg",
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
				List<HdOrderMainPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), HdOrderMainPage.class, params);
				HdOrderMainEntity entity1=null;
				for (HdOrderMainPage page : list) {
					entity1=new HdOrderMainEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            hdOrderMainService.addMain(entity1, page.getHdOrderBuyList(),page.getHdOrderSellList());
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
		map.put(NormalExcelConstants.FILE_NAME,"业务单主表");
		map.put(NormalExcelConstants.CLASS,HdOrderMainPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("业务单主表列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
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
		req.setAttribute("controller_name", "hdOrderMainController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<HdOrderMainEntity> list() {
		List<HdOrderMainEntity> listHdOrderMains=hdOrderMainService.getList(HdOrderMainEntity.class);
		return listHdOrderMains;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		HdOrderMainEntity task = hdOrderMainService.get(HdOrderMainEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody HdOrderMainPage hdOrderMainPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HdOrderMainPage>> failures = validator.validate(hdOrderMainPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<HdOrderBuyEntity> hdOrderBuyList =  hdOrderMainPage.getHdOrderBuyList();
		List<HdOrderSellEntity> hdOrderSellList =  hdOrderMainPage.getHdOrderSellList();
		
		HdOrderMainEntity hdOrderMain = new HdOrderMainEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(hdOrderMain,hdOrderMainPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		hdOrderMainService.addMain(hdOrderMain, hdOrderBuyList,hdOrderSellList);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = hdOrderMainPage.getId();
		URI uri = uriBuilder.path("/rest/hdOrderMainController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody HdOrderMainPage hdOrderMainPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HdOrderMainPage>> failures = validator.validate(hdOrderMainPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<HdOrderBuyEntity> hdOrderBuyList =  hdOrderMainPage.getHdOrderBuyList();
		List<HdOrderSellEntity> hdOrderSellList =  hdOrderMainPage.getHdOrderSellList();
		
		HdOrderMainEntity hdOrderMain = new HdOrderMainEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(hdOrderMain,hdOrderMainPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		hdOrderMainService.updateMain(hdOrderMain, hdOrderBuyList,hdOrderSellList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		HdOrderMainEntity hdOrderMain = hdOrderMainService.get(HdOrderMainEntity.class, id);
		hdOrderMainService.delMain(hdOrderMain);
	}
}
