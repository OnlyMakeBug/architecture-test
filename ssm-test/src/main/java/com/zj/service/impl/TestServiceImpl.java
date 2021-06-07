package com.zj.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zj.dao.TestMapper;
import com.zj.service.TestService;

/**
 * @time 2019��12��25�� ����5:19:42
 * @author Administrator
 * @corporation luku
 * @description:
 */
@Service("testServiceImpl")
public class TestServiceImpl implements TestService {
	
	@Autowired private TestMapper testMapper;

	public void test1() {
		System.out.println("TestService.test1()");
		ArrayList<Integer> idList = new ArrayList<Integer>(10000);
		System.out.println(idList.size());
		for(int i=0;i<10000;i++) { 
			idList.add(i);
		}
		System.out.println("sql result:" + testMapper.dynamicDataSourceTest(idList));
	}

	public int insertARow(int value) {
		// TODO Auto-generated method stub
		//int i = 1/0;
		
		/**
		 * ע�⣺����������ʱ�쳣����checked�쳣�����ǿ��Բ���ģ������Ժ�����Ͳ���ع���
		 * ��˲�����checked/unchecked�����񣬶���Ҫ��ʾ�׳��������½��������
		 * 1.try{}catch(){throw new RuntimeException()}
		 * 
		 * 2.catch�����
		 * TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		 * ��䣬�ֶ��ع��������ϲ������ȥ�����쳣��������Ŀ��������
		 * */
		
		try {
			//����ʱ�쳣
			@SuppressWarnings("unused")
			int i = 1/0;
		} catch (Exception e) {
			/*ʵ��û���ύ
			Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@15f5b5a5]
			Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@15f5b5a5]
			Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@15f5b5a5]*/
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return testMapper.insertARow(value);
	}
	
	

}



