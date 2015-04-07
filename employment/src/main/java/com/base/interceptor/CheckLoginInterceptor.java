package com.base.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhangmin.center.entity.UserInfo;
import com.zhangmin.constant.Const;
import com.zhangmin.constant.Global;
import com.zhangmin.constant.Stat;
import com.zhaosen.util.CommonUtil;

public class CheckLoginInterceptor implements HandlerInterceptor {
	
	
	private static final String FILTERED_REQUEST = "@@session_context_filtered_request";
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CheckLoginInterceptor.class);
	
	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
        //保存请求url到session中
        addSessionRequestUrl(request);
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse arg1,
		Object arg2, ModelAndView mav) throws Exception {
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		
		long beginTime = System.currentTimeMillis();//1、开始时间  
        startTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
        
		//页面传入token与session中的token比较，判断是否是重复表单提交 
		if(null!=request.getParameter("token")){
			String token=request.getParameter("token").toString();
			if(null!=request.getSession().getAttribute("token")){
				if(!token.equals(request.getSession().getAttribute("token").toString())){
					request.getRequestDispatcher("/formRepeat.do").forward(request, response);
					return false;
				}
			}
		}
		String headerStr=request.getHeader("accept");
		if(!headerStr.contains("json")){
			//产生新的token
			request.getSession().setAttribute("token", CommonUtil.getUUID());
		}
		
		if (request != null && request.getAttribute(FILTERED_REQUEST) != null) {
			return true;
		}
		else {
			UserInfo UserInfo = getUserInfo(request);
			if (UserInfo == null) {
				String toUrl = request.getServletPath().toString();
				
				if (!StringUtils.isEmpty(request.getQueryString())) {
					toUrl += "?" + request.getQueryString();
				}
				
				request.getSession().setAttribute(Global.LOGIN_TO_URL, toUrl);
				
				if(headerStr.contains("json")){
					response.setContentType("application/json");
		            response.setCharacterEncoding("utf-8");
		            response.getWriter().println("{\"result\":\"" + Const.LOGOUT + "\",\"errorInfo\":\"用户会话失效，需重新登录\"}");
		            return false;
				}
				else{
//					request.getRequestDispatcher("/").forward(request, response);
					response.sendRedirect("/emp/dologin.do");
//					request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
					return false;
				}
				
			} else {
				request.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
			}
		}
		return true;
	}

	private UserInfo getUserInfo(HttpServletRequest request) {
		return (UserInfo) request.getSession().getAttribute(Global.USER_INFO);
	}
	
	
	/**
	 * 功能：记录token为空的请求url到session中
	 */
	private void addSessionRequestUrl(HttpServletRequest request){
		if(null==request.getParameter("token")){
			String headerStr=request.getHeader("accept");
			if(!headerStr.contains("json")){
				setSessionListUrl(request);
			}
		}
	}
	
	public void setSessionListUrl(HttpServletRequest request){
		//把路径信息写入到session
		String uri = request.getServletPath().toString();
		String queryString = request.getQueryString();
		String queryUrl = (uri + (null != queryString ? ("?" + queryString): ""));
		Stat urlStat=this.getUrlStat(request);
		if(null!=urlStat){
			
			//如果队列中已经包含这个，就不往里增加了。
			if(uri.contains("main/back")){
				//如果是返回操作不做处理
			}
			else if(!uri.contains("tmClassStufee/getRecentStufee")){
				if(urlStat.size()>1){
					if(!urlStat.lastElement().toString().contains(uri)){
						urlStat.push(queryUrl);
					}
					else{
						urlStat.pop();
						urlStat.push(queryUrl);
					}
				}
				else{
					urlStat.push(queryUrl);
				}
			}
		}
		else{
			urlStat=new Stat();
			urlStat.push(queryUrl);
		}
		request.getSession().setAttribute("urlStat", urlStat);
	}
	
	
	/**
	 * 功能：获得session中的保存url的队列。
	 * @param request
	 * @return
	 */
	public Stat getUrlStat(HttpServletRequest request){
		return (Stat)request.getSession().getAttribute("urlStat");
	}

}
