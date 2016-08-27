package com.jeecg.hd.service.impl;
import com.jeecg.hd.service.HdOrderMainServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.hd.entity.HdOrderMainEntity;
import com.jeecg.buy.entity.HdOrderBuyEntity;
import com.jeecg.sell.entity.HdOrderSellEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;


@Service("hdOrderMainService")
@Transactional
public class HdOrderMainServiceImpl extends CommonServiceImpl implements HdOrderMainServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((HdOrderMainEntity)entity);
 	}
	
	public void addMain(HdOrderMainEntity hdOrderMain,
	        List<HdOrderBuyEntity> hdOrderBuyList,List<HdOrderSellEntity> hdOrderSellList){
			//保存主信息
			this.save(hdOrderMain);
		
			/**保存-买方信息*/
			for(HdOrderBuyEntity hdOrderBuy:hdOrderBuyList){
				//外键设置
				hdOrderBuy.setPId(hdOrderMain.getId());
				this.save(hdOrderBuy);
			}
			/**保存-卖方信息*/
			for(HdOrderSellEntity hdOrderSell:hdOrderSellList){
				//外键设置
				hdOrderSell.setPId(hdOrderMain.getId());
				this.save(hdOrderSell);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(hdOrderMain);
	}

	
	public void updateMain(HdOrderMainEntity hdOrderMain,
	        List<HdOrderBuyEntity> hdOrderBuyList,List<HdOrderSellEntity> hdOrderSellList) {
		//保存主表信息
		this.saveOrUpdate(hdOrderMain);
		//===================================================================================
		//获取参数
		Object id0 = hdOrderMain.getId();
		Object id1 = hdOrderMain.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-买方信息
	    String hql0 = "from HdOrderBuyEntity where 1 = 1 AND p_ID = ? ";
	    List<HdOrderBuyEntity> hdOrderBuyOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-买方信息
		if(hdOrderBuyList!=null&&hdOrderBuyList.size()>0){
		for(HdOrderBuyEntity oldE:hdOrderBuyOldList){
			boolean isUpdate = false;
				for(HdOrderBuyEntity sendE:hdOrderBuyList){
					//需要更新的明细数据-买方信息
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-买方信息
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-买方信息
			for(HdOrderBuyEntity hdOrderBuy:hdOrderBuyList){
				if(oConvertUtils.isEmpty(hdOrderBuy.getId())){
					//外键设置
					hdOrderBuy.setPId(hdOrderMain.getId());
					this.save(hdOrderBuy);
				}
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-卖方信息
	    String hql1 = "from HdOrderSellEntity where 1 = 1 AND p_ID = ? ";
	    List<HdOrderSellEntity> hdOrderSellOldList = this.findHql(hql1,id1);
		//2.筛选更新明细数据-卖方信息
		if(hdOrderSellList!=null&&hdOrderSellList.size()>0){
		for(HdOrderSellEntity oldE:hdOrderSellOldList){
			boolean isUpdate = false;
				for(HdOrderSellEntity sendE:hdOrderSellList){
					//需要更新的明细数据-卖方信息
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-卖方信息
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-卖方信息
			for(HdOrderSellEntity hdOrderSell:hdOrderSellList){
				if(oConvertUtils.isEmpty(hdOrderSell.getId())){
					//外键设置
					hdOrderSell.setPId(hdOrderMain.getId());
					this.save(hdOrderSell);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(hdOrderMain);
	}

	
	public void delMain(HdOrderMainEntity hdOrderMain) {
		//删除主表信息
		this.delete(hdOrderMain);
		//===================================================================================
		//获取参数
		Object id0 = hdOrderMain.getId();
		Object id1 = hdOrderMain.getId();
		//===================================================================================
		//删除-买方信息
	    String hql0 = "from HdOrderBuyEntity where 1 = 1 AND p_ID = ? ";
	    List<HdOrderBuyEntity> hdOrderBuyOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(hdOrderBuyOldList);
		//===================================================================================
		//删除-卖方信息
	    String hql1 = "from HdOrderSellEntity where 1 = 1 AND p_ID = ? ";
	    List<HdOrderSellEntity> hdOrderSellOldList = this.findHql(hql1,id1);
		this.deleteAllEntitie(hdOrderSellOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(HdOrderMainEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(HdOrderMainEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(HdOrderMainEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,HdOrderMainEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{order_get_time}",String.valueOf(t.getOrderGetTime()));
 		sql  = sql.replace("#{leader}",String.valueOf(t.getLeader()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}