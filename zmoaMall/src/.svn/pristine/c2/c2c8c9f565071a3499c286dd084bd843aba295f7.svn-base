package com.zm.mall.web.system;

import com.zm.mall.client.result.system.DepartmentResult;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.system.DepartmentService;
import com.zm.mall.client.vo.system.DepartmentVo;
import com.zm.mall.common.utils.SessionGetPlUteFromId;
import com.zm.mall.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/19.
 */
@Controller
@RequestMapping("department")
public class DepartmentController extends BaseController {
	@Resource
	private DepartmentService departmentService;

		@RequestMapping(value = "/show.action")
		public ModelAndView getAllDepartment(HttpServletRequest request) throws Exception {
			try{
				UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
				Map<String,Object> map=new HashMap<String, Object>();

				DepartmentVo departmentVo = new DepartmentVo();
				departmentVo.setPluteformid(user.getPluteformid());
				List<DepartmentResult> departmentResults=departmentService.select(departmentVo);
				map.put("departmentResults",departmentResults);
				return new ModelAndView("system/departments", map);
			}catch (Exception e){
				throw e;
			}

		}

		@RequestMapping(value = "/{id}/getById.action")
		public ModelAndView getDepartment(HttpServletRequest request,@PathVariable("id")Long id) throws Exception {
			try{
				UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
				DepartmentVo departmentVo = new DepartmentVo();
				departmentVo.setId(id);
				departmentVo.setPluteformid(user.getPluteformid());

				Map<String,Object> map=new HashMap<String, Object>();
				DepartmentResult departmentResult=departmentService.selectOne(departmentVo);
				List<DepartmentResult> departmentResults=departmentService.select(departmentVo);
				map.put("departmentResult",departmentResult);
				map.put("departmentResults",departmentResults);
				return new ModelAndView("system/department", map);
			}catch (Exception e){
				throw e;
			}
		}

		@RequestMapping(value = "/update.action")
		public ModelAndView update(HttpServletRequest request,DepartmentVo departmentVo) throws Exception {
			UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
			departmentVo.setPluteformid(user.getPluteformid());
			departmentService.update(departmentVo);
			return getAllDepartment(request);
		}

		@RequestMapping(value = "/toAddPage.action")
		public ModelAndView toAddPage(HttpServletRequest request) throws Exception {
			UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
			DepartmentVo departmentVo = new DepartmentVo();
			departmentVo.setPluteformid(user.getPluteformid());

			Map<String,Object> map=new HashMap<String, Object>();
			List<DepartmentResult> departmentResults=departmentService.select(departmentVo);
			map.put("departmentResults",departmentResults);
			return new ModelAndView("system/addDepartment", map);
		}

		@RequestMapping(value = "/insert.action")
		public ModelAndView add(HttpServletRequest request,DepartmentVo departmentVo) throws Exception {
			UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
			departmentVo.setPluteformid(user.getPluteformid());
			departmentService.insert(departmentVo);
			return getAllDepartment(request);
		}

		@RequestMapping(value = "/{id}/delete.action")
		public ModelAndView delete(HttpServletRequest request,@PathVariable("id")Long id) throws Exception {
			UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
			DepartmentVo departmentVo = new DepartmentVo();
			departmentVo.setId(id);
			departmentVo.setPluteformid(user.getPluteformid());

			Map<String,Object> map=new HashMap<String, Object>();
			departmentService.delete(departmentVo);
			List<DepartmentResult> departmentResults=departmentService.select(departmentVo);
			map.put("departmentResults",departmentResults);
			return new ModelAndView("system/departments", map);
		}
	}


