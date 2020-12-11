package com.tyx.mybatis.test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.tyx.mybatis.beans.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;

import com.tyx.mybatis.dao.EmployeeMapper;

public class TestMybatis {
	
	/**
	 * 
	 * MyBatis HelloWorld 小结:
	 * 	 两个重要的配置文件
	 * 		mybatis-config.xml :全局配置文件 , 数据库连接信息、 引入SQL映射文件等....
	 * 		EmployeeMapper.xml :SQL映射文件 , 配置增删改查的SQL语句的映射
	 * 
	 *  两个重要的对象
	 *  	SqlSessionFactory: SqlSession的工厂对象， 主要是用于获取SqlSession对象
	 *  	SqlSession:  Java程序与数据库的会话对象.可以理解为是对Connection的封装.  	
	 */

	@Test
	public void testSqlSessionFactory()  throws Exception{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		System.out.println(sqlSessionFactory);
		
		SqlSession session = sqlSessionFactory.openSession();
		
		System.out.println(session);
		
		try {
			/**
			 *  statement    Unique identifier matching the statement to use.
			 *  			 SQL语句的唯一标识
				parameter    A parameter object to pass to the statement.	
							  执行SQL需要用到的参数
			 */
			Employee employee = session.selectOne("suibian.selectEmployee", 1001);
			System.out.println(employee);
		} finally {
			session.close();
		}
	}
	
	
	@Test
	public void  testHelloWorldMapper() throws Exception {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			//mapper接口： dao接口
			/*
			 * 两个绑定:
			 * 	 1. Mapper接口与SQL映射文件的绑定.  映射文件的namesapce的值必须指定成接口的全类名.
			 * 	 2. Mapper接口的方法  与 SQL映射文件的具体SQL语句的绑定    SQL语句的id值  必须指定成接口的方法名. 
			 * 
			 * Mapper接口开发的好处:
			 * 	 1. 有更明确的类型
			 * 	 2. 接口本身: 接口本身就是抽象. 抽出了规范. 
			 * 			EmployeeDao:    EmployeeDaoJdbcImpl 、 EmployeeDaoHibernateImpl、MyBatis代理实现类 
			 */
			//获取MyBatis为Mapper接口生成的代理实现类对象
			EmployeeMapper  dao = session.getMapper(EmployeeMapper.class);
			System.out.println(dao.getClass().getName());
			
			Employee employee  = dao.getEmployeeById(1001);
			
			System.out.println(employee);
			
		} finally {
			session.close();
		}
	}
	
	public SqlSessionFactory getSqlSessionFactory() throws Exception {   //mybatis工厂，指定数据源
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	
	@Test
	public void testCRUD() throws Exception {
		SqlSessionFactory ssf = getSqlSessionFactory();
		
		SqlSession session = ssf.openSession();
		//ssf.openSession(true);
		
		try {
			//获取Mapper接口的代理实现类对象
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			//查询
			Employee employee =mapper.getEmployeeById(1005);
			System.out.println(employee);
			
			//添加
//			Employee employee  = 
//						new Employee(null, "苍老师", "cls@sina.com", 1);
//			mapper.addEmployee(employee);
//			System.out.println("返回的主键值: " + employee.getId());
			/*
			JDBC操作获取新插入数据的主键值:
			Connection conn = null ;
			PreparedStatement  ps = conn.prepareStatement("sql", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ps.getGeneratedKeys();
			*/
			//修改
//			Employee employee  = 
//					new Employee(1008, "敏敏", "mm@sina.com", 0);
//			mapper.updateEmployee(employee);
			
			//删除
			
//			Boolean  result= mapper.deleteEmployeeById(1001);
//			System.out.println(result );
			
			
			
			//提交
			session.commit();
		} finally {
			session.close();
		}
	}
	
	
	
	@Test
	public void testParameter() throws Exception {
		SqlSessionFactory ssf = getSqlSessionFactory();
		
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			
//			Employee employee = mapper.getEmployeeByIdAndLastName(1024, "苍老师");
//			System.out.println(employee);
			
			
			Map<String,Object > map = new HashMap<>();
			map.put("id", 1024);
			map.put("ln", "苍老师");
			map.put("tableName", "tbl_employee");
			Employee employee = mapper.getEmployeeByMap(map);
			System.out.println(employee);
			
		} finally {
			session.close();
		}
	}
	
	
	@Test
	public void testSelect()  throws Exception{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			
//			List<Employee> emps = mapper.getEmps();
//			System.out.println(emps);
			
//			Map<String,Object> map = mapper.getEmployeeByIdReturnMap(1005);
//			System.out.println(map);
			
			Map<Integer,Employee> map = mapper.getEmpsReturnMap();
			
			System.out.println(map);
		} finally {
			session.close();
		}
	}
	
	
	
	
	
	
	

}
