package com.zhangmin.constant;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;

public class Global {
	
	public static String USER_INFO = "USER_INFO";
	public static String USER_ROLE_LIST = "USER_ROLE_LIST";
	public static String USER_ORGAN = "USER_ORGAN";
	public static String USER_RIGHT_ORGAN = "USER_RIGHT_ORGAN";
	public static String USER_ALL_MENU = "USER_ALL_MENU";
	public static String USER_BUTTON_STR = "USER_BUTTON_STR";
	public static String USER_ROLE = "USER_ROLE";
	public static String LOGIN_TO_URL = "LOGIN_TO_URL";
	public static String USER_EMPLOYEE_INFO = "USER_EMPLOYEE_INFO";
	
	public static int DEFAULT_PAGE_SIZE = 15;
	
	public static final String WPXS = "WPXS";// ��Ʒ����
	public static final String WPXS_ROLLBACK = "WPXSZF";// ��Ʒ����
	public static final String WPXS_REFUND = "WPXSTF";// ��Ʒ����
	
	public static final String QI_TA_SHOU_FEI = "QTSF"; // �����շ�
	public static final String QI_TA_SHOU_FEI_ROLLBACK = "QTSFZF"; // �����շ�
	public static final String QI_TA_SHOU_FEI_REFUND = "QTSFTF"; // �����շ�
	
	public static final String YU_JIAO_FEI = "YJF"; // Ԥ���
	public static final String YU_JIAO_FEI_ROLLBACK = "YJFZF"; // Ԥ�������
	public static final String YU_JIAO_FEI_REFUND = "YJFTF"; // Ԥ����˷�
	
	public static final String STU_EARNEST = "DJSF";
	public static final String STU_EARNEST_REFUND = "DJTF";
	public static final String STU_EARNEST_ROLLBACK = "DJZF";
	public static final String STU_ENROLL = "XSBM";
	public static final String STU_ENROLL_UPDATE = "XSBMXG";
	public static final String STU_ENROLL_ROLLBACK = "XSBMZF";
	public static final String STU_ENROLL_OWN = "XSBMYK";
	public static final String STU_CLASS_LEAVE = "XSTX";
	public static final String STU_CHANGE_CLASS = "XSZB";
	public static final String STU_CHANGE_SCHOOL = "XSZX";
	public static final String STU_STOP_CLASS = "XSXX";
	public static final String STU_STOP_RETURN = "XSXF";
	public static final String STU_STOP_REFUOND = "XSTX";
	
	public final static String GLOBAL_CONFIG_URL = "config/global-config.xml";
	private static CompositeConfiguration config;
	
	/**
	 * ��xml�����ļ���ȡ�����ò����ֵ ʵ�� <name1><name2>value</name2></name1> ��ͨ��getConfigValue(name1.name2)ȡ��value
	 * @param configName ������ƣ���"."����ʶ�ṹ
	 * @return ָ�������ֵ
	 */
	public static String getGlobalConfigValue(String configName) {
		if (config == null) {
			initGlobalConfig();
		}
		return config.getString(configName);
	}
	
	/**
	 * ��ʼ��Global-configȫ������
	 */
	private static void initGlobalConfig() {
		config = new CompositeConfiguration();
		try {
			config.addConfiguration(new org.apache.commons.configuration.XMLConfiguration(GLOBAL_CONFIG_URL));
		}
		catch (ConfigurationException e) {
			config = null;
		}
	}
	
}
