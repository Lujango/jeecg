package com.jeecg.testorder.service.impl;
import com.jeecg.testorder.service.BusiOrderServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.testorder.entity.BusiOrderEntity;
import com.jeecg.order.entity.BusiOrderSalerEntity;

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


@Service("busiOrderService")
@Transactional
public class BusiOrderServiceImpl extends CommonServiceImpl implements BusiOrderServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((BusiOrderEntity)entity);
 	}
	
	public void addMain(BusiOrderEntity busiOrder,
	        List<BusiOrderSalerEntity> busiOrderSalerList){
			//保存主信息
			this.save(busiOrder);
		
			/**保存-业务单测试*/
			for(BusiOrderSalerEntity busiOrderSaler:busiOrderSalerList){
				//外键设置
				busiOrderSaler.setPId(busiOrder.getId());
				this.save(busiOrderSaler);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(busiOrder);
	}

	
	public void updateMain(BusiOrderEntity busiOrder,
	        List<BusiOrderSalerEntity> busiOrderSalerList) {
		//保存主表信息
		this.saveOrUpdate(busiOrder);
		//===================================================================================
		//获取参数
		Object id0 = busiOrder.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-业务单测试
	    String hql0 = "from BusiOrderSalerEntity where 1 = 1 AND p_ID = ? ";
	    List<BusiOrderSalerEntity> busiOrderSalerOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-业务单测试
		if(busiOrderSalerList!=null&&busiOrderSalerList.size()>0){
		for(BusiOrderSalerEntity oldE:busiOrderSalerOldList){
			boolean isUpdate = false;
				for(BusiOrderSalerEntity sendE:busiOrderSalerList){
					//需要更新的明细数据-业务单测试
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-业务单测试
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-业务单测试
			for(BusiOrderSalerEntity busiOrderSaler:busiOrderSalerList){
				if(oConvertUtils.isEmpty(busiOrderSaler.getId())){
					//外键设置
					busiOrderSaler.setPId(busiOrder.getId());
					this.save(busiOrderSaler);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(busiOrder);
	}

	
	public void delMain(BusiOrderEntity busiOrder) {
		//删除主表信息
		this.delete(busiOrder);
		//===================================================================================
		//获取参数
		Object id0 = busiOrder.getId();
		//===================================================================================
		//删除-业务单测试
	    String hql0 = "from BusiOrderSalerEntity where 1 = 1 AND p_ID = ? ";
	    List<BusiOrderSalerEntity> busiOrderSalerOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(busiOrderSalerOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(BusiOrderEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(BusiOrderEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(BusiOrderEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,BusiOrderEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{leader}",String.valueOf(t.getLeader()));
 		sql  = sql.replace("#{order_get_date}",String.valueOf(t.getOrderGetDate()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}