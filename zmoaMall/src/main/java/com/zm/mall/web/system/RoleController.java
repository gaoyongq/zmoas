package com.zm.mall.web.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zm.mall.client.result.system.DepartmentResult;
import com.zm.mall.client.result.system.RoleResult;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.system.DepartmentService;
import com.zm.mall.client.service.system.RoleService;
import com.zm.mall.client.vo.system.DepartmentVo;
import com.zm.mall.client.vo.system.RoleVo;
import com.zm.mall.common.utils.SessionGetPlUteFromId;
import com.zm.mall.domain.system.Department;
import com.zm.mall.domain.system.Privileges;
import com.zm.mall.domain.system.Role;
import com.zm.mall.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/15.
 */
@Controller
@RequestMapping("/role")
public class RoleController  extends BaseController {
	@Resource
	private RoleService roleService;
	@Resource
	private DepartmentService departmentService;
	/**
	*    查询所有的部门和岗位
	 */
	@RequestMapping(value = "/roleShow.action")
	public ModelAndView selectALl(HttpServletRequest request){
		UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
		RoleVo roleVo = new RoleVo();
		roleVo.setPluteformid(user.getPluteformid());
		Map<String,Object> map=new HashMap<String, Object>();
		List<RoleResult> roleResults=roleService.select(roleVo);
		map.put("roleList", roleResults);
		return new ModelAndView("/system/roleShow",map);
	}
	/**
	 *   根据ID查询
	 */

	@RequestMapping(value = "/{id}/getById.action")
	public ModelAndView getRoleById(HttpServletRequest request,@PathVariable("id")Long id)throws Exception{
		try{
			UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
			RoleVo roleVo = new RoleVo();
			roleVo.setPluteformid(user.getPluteformid());
			roleVo.setId(id);
			DepartmentVo departmentVo = new DepartmentVo();
			departmentVo.setPluteformid(user.getPluteformid());

			Map<String,Object> map=new HashMap<String, Object>();
			List<DepartmentResult> departmentResults=departmentService.select(departmentVo);
			map.put("departmentResults",departmentResults);
			RoleResult roleResult=roleService.selectOne(roleVo);
			map.put("roleOne",roleResult);
			return new ModelAndView("/system/roleOne",map);
		}catch (Exception e){
			throw e;
		}
	}
	/**
	 * 修改
	 */

	@RequestMapping(value = "/{id}/update.action")
	public ModelAndView updateRole(HttpServletRequest request,@PathVariable("id")Long id) throws Exception{
		try{
			UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
			RoleVo roleVo = new RoleVo();
			String name = request.getParameter("name");
			if(name!=""&& name!=null){
				roleVo.setName(name);
			}
			String description = request.getParameter("description");
			if(description!=""&& description!=null){
				roleVo.setDescription(description);
			}
			String department = request.getParameter("department.id");
			if(department!=""&& department!=null){
				Department department1 = new Department();
				department1.setId(Long.parseLong(department));
				roleVo.setDepartment(department1);
			}
			roleVo.setPluteformid(user.getPluteformid());
			roleVo.setId(id);
			roleService.updateRole(roleVo);
			return selectALl(request);
		}catch (Exception e){
			throw e;
		}
	}

	/**
	 * 增加岗位之前查询所有部门
	 * @param roleVo
	 * @return
	 */
	@RequestMapping(value = "/showDepartment.action")
	public ModelAndView showDepartment(HttpServletRequest request,RoleVo roleVo){
		UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
		DepartmentVo departmentVo = new DepartmentVo();
		departmentVo.setPluteformid(user.getPluteformid());

		Map<String,Object> map=new HashMap<String, Object>();
		List<DepartmentResult> departmentResults=departmentService.select(departmentVo);
		map.put("departmentResults", departmentResults);
		return new ModelAndView("system/addRole",map);
	}
	/**
	* 增加岗位
 	*/
	@RequestMapping(value = "/addRole.action")
	public ModelAndView insertRole(HttpServletRequest request,RoleVo roleVo){
		UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
		roleVo.setPluteformid(user.getPluteformid());
		roleService.insertRole(roleVo);
		return selectALl(request);
	}

	/**
	 * 删除岗位
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/{id}/delteRole.action")
	public ModelAndView deleteRole(HttpServletRequest request,@PathVariable("id")Long id)throws Exception{
		try{
			UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
			RoleVo roleVo = new RoleVo();
			roleVo.setPluteformid(user.getPluteformid());
			roleVo.setId(id);
			Map<String,Object> map=new HashMap<String, Object>();
			roleService.deleteEntryByKey(roleVo);
			List<RoleResult> roleResults=roleService.select(roleVo);
			map.put("roleList", roleResults);
			return new ModelAndView("/system/roleShow",map);
		}catch (Exception e){
			throw e;
		}
	}
	/**
	 * 修改权限 回显
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePrivilegeRole.action")
	public void updatePrivilegeRole(HttpServletResponse response,HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
		RoleVo roleVo = new RoleVo();
		roleVo.setId(Long.parseLong(id));
		roleVo.setPluteformid(user.getPluteformid());
		JSONArray array = new JSONArray();
		try {
			List<Privileges> list = roleService.updatePrivilegeRole(roleVo);
			for (Privileges sc : list) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", sc.getId());
				jsonObject.put("name", sc.getName());
				jsonObject.put("pId", sc.getPid());
				jsonObject.put("page", sc.getUrl());
				jsonObject.put("checked", sc.getFlag());
				jsonObject.put("open",sc.getOpen());
				array.add(jsonObject);
			}
			response.getWriter().write(array.toString());
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * 修改权限
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/PrivilegeRole.action")
	@ResponseBody
	public Integer PrivilegeRole(HttpServletResponse response,HttpServletRequest request) throws Exception {
		try {
			Long roleId = Long.parseLong(request.getParameter("roleId"));
			String id = request.getParameter("Id");
			UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
			Integer flag = roleService.privilegeRole(roleId, id,user.getPluteformid());
			return flag;
		} catch (Exception e) {
			throw e;
		}
	}
}
