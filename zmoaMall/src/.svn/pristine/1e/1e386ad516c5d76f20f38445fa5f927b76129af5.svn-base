package com.zm.mall.service.system.impl;



import com.zm.mall.client.result.system.DepartmentResult;
import com.zm.mall.client.service.system.DepartmentService;
import com.zm.mall.client.vo.system.DepartmentVo;
import com.zm.mall.dao.system.DepartmentDao;
import com.zm.mall.domain.system.Department;
import com.zm.mall.service.SpaceBeanCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/11/19.
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;
	@Override
	public List<DepartmentResult> select(DepartmentVo departmentVo) {
		Department department= SpaceBeanCopy.departmentVoToDepartment(departmentVo);
		List<Department> list=selectAll(department);
		if(list!=null&&list.size()>0){
			List<DepartmentResult> departmentResults=new ArrayList<DepartmentResult>();
			for(Department d:list){
				DepartmentResult departmentResult=SpaceBeanCopy.departmentToDepartmentResult(d);
				departmentResults.add(departmentResult);
			}
			return departmentResults;
		}
		return null;
	}
	@Override
	public DepartmentResult selectOne(DepartmentVo departmentVo) {
//		DepartmentVo departmentVo=new DepartmentVo();
//		departmentVo.setId(id);
		Department department=SpaceBeanCopy.departmentVoToDepartment(departmentVo);
		Department d=departmentDao.selectOne(department);
		DepartmentResult departmentResult=SpaceBeanCopy.departmentToDepartmentResult(d);
		return departmentResult;
	}

	@Override
	public int update(DepartmentVo departmentVo) {
		Department department=SpaceBeanCopy.departmentVoToDepartment(departmentVo);
		if (department.getDepartment().getId()==0){
			department.getDepartment().setId(null);
		}
		return departmentDao.update(department);
	}

	@Override
	public int insert(DepartmentVo departmentVo) {
		Department department=SpaceBeanCopy.departmentVoToDepartment(departmentVo);
		if (department.getDepartment().getId()==0){
			department.getDepartment().setId(null);
		}
		return departmentDao.insert(department);
	}

	@Override
	public int delete(DepartmentVo departmentVo) {
//		DepartmentVo departmentVo=new DepartmentVo();
//		departmentVo.setId(id);
		Department department=SpaceBeanCopy.departmentVoToDepartment(departmentVo);
		return departmentDao.delete(department);
	}

	@Override
	public List<Department> getRole(DepartmentVo departmentVo) {
		Department department=SpaceBeanCopy.departmentVoToDepartment(departmentVo);
		return departmentDao.selectRoleByDepartment(department);
	}

	public List<Department> selectAll(Department department) {
		List<Department> departments=departmentDao.selectTop(department);
		for (int i=0;i<departments.size();i++){
			if ("ZM_SH".equals(departments.get(i).getCode())){
				departments.remove(i);
			}
		}
		return getAllDepartmentList(departments);
	}


	public List<Department> getAllDepartmentList(Collection<Department> top) {
		List<Department> list = new ArrayList<Department>();
		createTree(top,"" ,list);
		return list;
	}
	private  void createTree(Collection<Department> top,String string, List<Department> list) {
		for(Department dp : top){
			Department depart = new Department();
			depart.setId(dp.getId());
			depart.setName(string + dp.getName());
			depart.setDescription(dp.getDescription());
			depart.setDepartment(dp.getDepartment());
			depart.setPluteformid(dp.getPluteformid());
			list.add(depart);
			createTree(departmentDao.selectNext(dp),"&nbsp;&nbsp;"+string,list);
		}
	}

}
