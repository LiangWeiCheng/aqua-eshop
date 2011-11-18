package com.aqua.pingtai.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.entity.bean.authority.Menu;
import com.aqua.pingtai.entity.bean.authority.Operator;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.service.MenuService;
import com.aqua.pingtai.service.OperatorService;
import com.aqua.pingtai.service.UserService;
import com.aqua.pingtai.utils.WebUtil;

@SuppressWarnings({"serial"})
public class LoginAction extends BaseAction {
	
	@Resource(name="userServiceImpl")
	private UserService userServiceImpl;
	
	@Resource(name="operatorServiceImpl")
	private OperatorService operatorServiceImpl;
	
	@Resource(name="menuServiceImpl")
	private MenuService menuServiceImpl;
	
	private User user;
	private String yanZhengMa;
	private String menuString;
	
	/**
	 * 登录输入页
	 * @return
	 */
	public String toLoginJsp(){
		returnPageURL = "/WEB-INF/jsp/pingtai/login.jsp";
		return "dispatcher";
	}
	
	/**
	 * 登录平台
	 * @return
	 */
	public String loginPingTai(){
		Object yanZhengMaKeyObject = getSession().get("yanZhengMaKey");
		String yanZhengMaKey = null;
		if(null!=yanZhengMa && null!=yanZhengMaKeyObject){
			yanZhengMaKey = ((String)yanZhengMaKeyObject).toLowerCase();//统一小写
			yanZhengMa = yanZhengMa.toLowerCase();//统一小写
			if(yanZhengMa.equals(yanZhengMaKey)){
				User resultUser = userServiceImpl.userLogin(user);
				if(null!=resultUser){
					getSession().put("currentUser", resultUser);
					getSession().put("clientIp", WebUtil.getIpAddr(request));
					returnPageURL = "/loginPackage/loginPingTaiAction!selectOperator.action";
					return "redirect";
				}
			}
		}
		ActionContext.getContext().getSession().remove("currentUser");
		returnPageURL = "/loginPackage/loginPingTaiAction!toLoginJsp.action";
		return "redirect";
	}
	
	/**
	 * 注销
	 * @return
	 */
	public String logout(){
		ActionContext.getContext().getSession().remove("currentUser");
		return "login";//跳转到后台登陆首页
	}
	
	/**
	 * 登录成功后查询操作权限
	 * @return
	 */
	public String selectOperator(){
		user = Context.getCurrentUser();
		if(null!=user && null!=user.getIds() && 0l!=user.getIds()){
			String selectOperator = "select ids,names,url from pingtai_operator where ids in (select operatorIds from pingtai_roleoperator where roleIds in (select roleIds from pingtai_rolegroup where groupIds in ( select groupIds from pingtai_usergroup where userIds="+user.getIds()+")))";
			List<Operator> operatorList = operatorServiceImpl.selectUserOperator(selectOperator);
			List<String> operatorStringList = new ArrayList<String>();
			for (Operator operator : operatorList) {
				operatorStringList.add(operator.getUrl().trim());
			}
			
			StringBuffer sbSum = new StringBuffer();
			sbSum.append("<table id=\"menuTable\" height=\"100%\" width=\"147\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
			
			List<Menu> menuList = menuServiceImpl.getMenuByHQL(" where parentMenu.menuLevel='-1' and menuType='menuType_houTai' and menuLevel='1' order by orderIds asc");
			
			for (Menu menu : menuList) {
				Set<Menu> menuSet = menu.getMenuSet();
				if(menuSet.size()==0){
					continue;
				}
				
				StringBuffer sb = new StringBuffer();
				sb.append("<tr class=\"oneTrClass\" onclick=\"onclickEvent(this)\">");
				sb.append("<td height=\"23\" background=\""+request.getContextPath()+"/images/pingTai/main_34_1.gif\">");
				sb.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
				sb.append("<tr>");
				sb.append("<td width=\"9%\">&nbsp;</td>");
				sb.append("<td width=\"83%\">");
				sb.append("<div align=\"center\" class=\"STYLE4\">");
				sb.append(menu.getNames());
				sb.append("</div>");
				sb.append("</td>");
				sb.append("<td width=\"8%\">");
				sb.append("&nbsp;");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				
				sb.append("<tr class=\"twoTrClass\" height=\"100%\">");
				sb.append("<td valign=\"top\">");
				sb.append("<div align=\"center\">");
				sb.append("<table width=\"82%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
				for (Iterator<Menu> iterator = menuSet.iterator(); iterator.hasNext();) {
					Menu menuTwo = (Menu) iterator.next();
					boolean bool = operatorStringList.contains(menuTwo.getUrl().trim());
					if(bool){
						sb.append("<tr>");
						sb.append("<td height=\"38\">");
						sb.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
						sb.append("<tr>");
						sb.append("<td width=\"33\" height=\"28\">");
						sb.append("<img src=\""+request.getContextPath()+"/images/pingTai/menu/"+menuTwo.getImages()+"\" width=\"28\" height=\"28\">");
						sb.append("</td>");
						sb.append("<td width=\"99\">");
						sb.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
						sb.append("<tr>");
						sb.append("<td height=\"23\" class=\"STYLE4\" style=\"cursor: hand\" onMouseOver=\"this.style.backgroundImage='url("+request.getContextPath()+"/images/pingTai/tab_bg.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#adb9c2'; \" onmouseout=\"this.style.backgroundImage='url()';this.style.borderStyle='none'\">");
						
						sb.append("<a href=\"").append(request.getContextPath()).append(menuTwo.getUrl()).append("\" target=\"right\">").append(menuTwo.getNames()).append("</a>");
						
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("</table>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("</table>");
						sb.append("</td>");
						sb.append("</tr>");
					}
				}
				sb.append("<tr><td height=\"38\">&nbsp;</td></tr>");
				sb.append("</table>");
				sb.append("</div>");
				sb.append("</td>");
				sb.append("</tr>");
				
				sbSum.append(sb);
			}
			
			sbSum.append("</table>");
			
			menuString = sbSum.toString();
			Context.getSession().setAttribute("menuString", menuString);
			
			returnPageURL = "/WEB-INF/jsp/pingtai/main.jsp";
			return "dispatcher";
		}else{
			ActionContext.getContext().getSession().remove("currentUser");
			returnPageURL = "/loginPackage/loginPingTaiAction!toLoginJsp.action";
			return "redirect";
		}
	}
	
	@Override
	protected String getActionClassFullName() {
		return "com.aqua.pingtai.action.pingtai.LoginAction.action";
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		return null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getYanZhengMa() {
		return yanZhengMa;
	}

	public void setYanZhengMa(String yanZhengMa) {
		this.yanZhengMa = yanZhengMa;
	}

	public UserService getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserService userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public OperatorService getOperatorServiceImpl() {
		return operatorServiceImpl;
	}

	public void setOperatorServiceImpl(OperatorService operatorServiceImpl) {
		this.operatorServiceImpl = operatorServiceImpl;
	}

	public MenuService getMenuServiceImpl() {
		return menuServiceImpl;
	}

	public void setMenuServiceImpl(MenuService menuServiceImpl) {
		this.menuServiceImpl = menuServiceImpl;
	}

	public String getMenuString() {
		return menuString;
	}

	public void setMenuString(String menuString) {
		this.menuString = menuString;
	}

}
