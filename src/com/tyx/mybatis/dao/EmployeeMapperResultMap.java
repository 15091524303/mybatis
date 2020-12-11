package com.tyx.mybatis.dao;

import java.util.List;

import com.tyx.mybatis.beans.Employee;

public interface EmployeeMapperResultMap {
	
	public Employee getEmployeeById(Integer id);
	
	public Employee getEmpAndDept(Integer id);
	
	public Employee getEmpAndDeptStep(Integer id);
	
	public List<Employee>  getEmpsByDid(Integer did);
}
