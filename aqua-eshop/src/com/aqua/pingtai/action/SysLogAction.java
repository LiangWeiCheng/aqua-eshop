package com.aqua.pingtai.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.entity.bean.authority.SysLog;
import com.aqua.pingtai.service.SysLogService;

//系统日志
@SuppressWarnings({"serial"})
public class SysLogAction extends BaseAction {
	
	@Resource(name="sysLogServiceImpl")
	private SysLogService sysLogServiceImpl;
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	private SysLog sysLog;
	
	//查看日志明细
	public String viewSysLog(){
		if(null!=sysLog && sysLog.getIds()!=null){
			try {
				sysLog = daoHibernateBase.findOneEntity(SysLog.class, sysLog.getIds());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/sysLog/sysLogView.jsp";
		return "dispatcher";
	}
	
	//系统日志分页列表
	public String icCardList(){
		try {
			String hqlFilter = initQueryList(true);//过滤HQL
			hqlFilter += " order by createdDate desc ";
			sysLogServiceImpl.splitPageQuerySysLog(hqlFilter, queryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/sysLog/sysLogList.jsp";
		return "dispatcher";
	}
	
	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		String titles = queryParameter.getParameter("titles");
		String userNames = queryParameter.getParameter("userNames");
		String start = queryParameter.getParameter("start");
		String end = queryParameter.getParameter("end");
		
		StringBuffer sb = new StringBuffer();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(null!=titles && !titles.equals("")){
			sb.append(" titles like '%").append(titles.trim()).append("%'").append(" and ");
		}
		if(null!=userNames && !userNames.equals("")){
			sb.append(" creator.userInfo.names like '%").append(userNames.trim()).append("%'").append(" and ");
		}
		if(null!=start && !start.equals("")){
			try {
				Date date = DateFormat.getDateInstance().parse(start);
				sb.append(" createdDate>='").append(formatter.format(date)).append("'").append(" and ");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(null!=end && !end.equals("")){
			try {
				Date date = DateFormat.getDateInstance().parse(end);
				sb.append(" createdDate<='").append(formatter.format(date)).append("'");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		String sbString = sb.toString().trim();
		if(sbString.endsWith("and")){
			sbString = sbString.substring(0, sbString.length()-3);
		}
		return sbString;
	}

	@Override
	protected String getActionClassFullName() {
		return "com.aqua.pingtai.action.SysLogAction.java";
	}

	public SysLogService getSysLogServiceImpl() {
		return sysLogServiceImpl;
	}

	public void setSysLogServiceImpl(SysLogService sysLogServiceImpl) {
		this.sysLogServiceImpl = sysLogServiceImpl;
	}

	public SysLog getSysLog() {
		return sysLog;
	}

	public void setSysLog(SysLog sysLog) {
		this.sysLog = sysLog;
	}

}
