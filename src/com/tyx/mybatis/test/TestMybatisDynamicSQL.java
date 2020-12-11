package com.tyx.mybatis.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.tyx.mybatis.beans.Employee;
import com.tyx.mybatis.dao.EmployeeMapperDynamicSQL;

public class TestMybatisDynamicSQL {
	
	public SqlSessionFactory getSqlSessionFactory() throws Exception {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}

	@Test
	public void testIf()  throws Exception{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
			Employee condition = new Employee();
			condition.setId(1012);
			//condition.setLastName("Tom");
			//condition.setEmail("tom@sina.com");
			//condition.setGender(1);
			List<Employee > emps = mapper.getEmpsByConditionIfWhere(condition);
			System.out.println(emps);
			
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testTrim()  throws Exception{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
			Employee condition = new Employee();
			condition.setId(1012);
			condition.setLastName("Tom");
			condition.setEmail("tom@sina.com");
			//condition.setGender(1);
			List<Employee > emps = mapper.getEmpsByConditionTrim(condition);
			System.out.println(emps);
			
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testSet()  throws Exception{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
			Employee condition = new Employee();
			condition.setId(1012);
			condition.setLastName("TomAA");
			condition.setEmail("tomaa@sina.com");
			//condition.setGender(1);
			mapper.updateEmpByConditionSet(condition);
			
			session.commit();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testChoose()  throws Exception{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
			Employee condition = new Employee();
			//condition.setId(1012);
			//condition.setLastName("TomAA");
			//condition.setEmail("tomaa@sina.com");
			//condition.setGender(1);
			List<Employee> emps  = mapper.getEmpsByConditionChoose(condition);
			System.out.println(emps);
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testForeach()  throws Exception{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
			Employee condition = new Employee();
			
			List<Integer> ids = new ArrayList<Integer>();
			ids.add(1002);
			ids.add(1003);
			ids.add(1004);
			ids.add(1005);
			List<Employee> emps = mapper.getEmpsByIds(ids);
			System.out.println(emps);
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testBacth()  throws Exception{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
			List<Employee> emps = new ArrayList<>();
			emps.add(new Employee(null, "AA", "aa@sina.com", 1));
			emps.add(new Employee(null, "BB", "bb@sina.com", 0));
			emps.add(new Employee(null, "CC", "cc@sina.com", 1));
			
			
			mapper.addEmps(emps);
			
			session.commit();
		} finally {
			session.close();
		}
	}

}
