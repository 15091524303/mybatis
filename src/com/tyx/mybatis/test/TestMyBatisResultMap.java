package com.tyx.mybatis.test;

import java.io.InputStream;

import com.tyx.mybatis.beans.Department;
import com.tyx.mybatis.beans.Employee;
import com.tyx.mybatis.dao.DepartmentMapperResultMap;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.tyx.mybatis.dao.EmployeeMapperResultMap;

public class TestMyBatisResultMap {

	public SqlSessionFactory getSqlSessionFactory() throws Exception {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	@Test
	public void testResultMap() throws Exception{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session = ssf.openSession();
		try {
			EmployeeMapperResultMap mapper = session.getMapper(EmployeeMapperResultMap.class);
			
			Employee employee = mapper.getEmployeeById(1005);
			System.out.println(employee);
			
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testResultMapCascade() throws Exception{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session = ssf.openSession();
		try {
			EmployeeMapperResultMap mapper = session.getMapper(EmployeeMapperResultMap.class);
			
			Employee employee = mapper.getEmpAndDept(1005);
			System.out.println(employee);
			System.out.println(employee.getDept());
			
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testResultMapAssociation() throws Exception{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session = ssf.openSession();
		try {
			EmployeeMapperResultMap mapper = session.getMapper(EmployeeMapperResultMap.class);
			
			Employee employee = mapper.getEmpAndDeptStep(1005);
			System.out.println(employee.getLastName());
			System.out.println("-------------------------------");
			System.out.println(employee.getDept());
			
		} finally {
			session.close();
		}
	}

	
	@Test
	public void testResultMapCollection() throws Exception{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session = ssf.openSession();
		try {
			DepartmentMapperResultMap mapper = session.getMapper(DepartmentMapperResultMap.class);
			
			Department dept = mapper.getDeptAndEmps(4);
			System.out.println(dept);
			System.out.println(dept.getEmps());
			
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testResultMapCollectionStep() throws Exception{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session = ssf.openSession();
		try {
			DepartmentMapperResultMap mapper = session.getMapper(DepartmentMapperResultMap.class);
			
			Department dept = mapper.getDeptAndEmpsStep(4);
			System.out.println(dept.getDepartmentName());
			System.out.println("---------------------------");
			System.out.println(dept.getEmps());
			
		} finally {
			session.close();
		}
	}

	
	
	
	
	
	
	
	
	
}
