package com.tyx.mybatis.dao;

import com.tyx.mybatis.beans.Department;

public interface DepartmentMapperResultMap {
	
	public Department getDeptById(Integer id);
	
	public Department getDeptAndEmps(Integer id);
	
	
	public Department getDeptAndEmpsStep(Integer id);
}
