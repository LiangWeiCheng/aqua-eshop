package com.aqua.pingtai.jbpm.action;

import java.util.List;

import javax.annotation.Resource;

import com.aqua.pingtai.action.BaseAction;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.jbpm.entity.Swimlane;
import com.aqua.pingtai.jbpm.service.SwimlaneService;

@SuppressWarnings("serial")
public class SwimlaneAction extends BaseAction {
	
	@Resource(name="swimlaneServiceImpl")
	private SwimlaneService swimlaneServiceImpl;
	
	private String processDefinitionId;
	private List<Swimlane> swimlaneList;
	
	/**
	 * 显示泳道
	 * @return
	 */
	public String swimlaneList(){
		swimlaneList = swimlaneServiceImpl.getSwimlaneByHql(" where processDefinitionId='"+processDefinitionId+"'");
		returnPageURL = "/WEB-INF/jsp/pingtai/jbpm/swimlane/swimlaneListEdit.jsp";
		return "dispatcher";
	}
	
	/**
	 * 保存更新
	 * @return
	 */
	public String swimlaneUpdate(){
		if(null!=swimlaneList && null!=processDefinitionId){
			swimlaneServiceImpl.swimlaneUpdate(swimlaneList, processDefinitionId);
		}
		returnPageURL = "/pingTai/swimlanePingTaiAction!swimlaneList.action?processDefinitionId="+processDefinitionId;
		return "redirect";
	}
	
	@Override
	protected String getActionClassFullName() {
		return "com.aqua.pingtai.action.pingtai.jbpm.SwimlaneAction.java";
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		StringBuffer sb = new StringBuffer();
		
		String processDefinitionId = queryParameter.getParameter("processDefinitionId");
		if(null!=processDefinitionId && !processDefinitionId.equals("")){
			sb.append(" processDefinitionId like '%").append(processDefinitionId.trim()).append("%'").append(" and ");
		}
		
		String swimlaneName = queryParameter.getParameter("swimlaneName");
		if(null!=swimlaneName && !swimlaneName.equals("")){
			sb.append(" swimlaneName like '%").append(swimlaneName.trim()).append("%'");
		}
		
		String sbString = sb.toString().trim();
		if(sbString.endsWith("and")){
			sbString = sbString.substring(0, sbString.length()-3);
		}
		return sbString;
	}

	public SwimlaneService getSwimlaneServiceImpl() {
		return swimlaneServiceImpl;
	}

	public void setSwimlaneServiceImpl(SwimlaneService swimlaneServiceImpl) {
		this.swimlaneServiceImpl = swimlaneServiceImpl;
	}

	public List<Swimlane> getSwimlaneList() {
		return swimlaneList;
	}

	public void setSwimlaneList(List<Swimlane> swimlaneList) {
		this.swimlaneList = swimlaneList;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

}
