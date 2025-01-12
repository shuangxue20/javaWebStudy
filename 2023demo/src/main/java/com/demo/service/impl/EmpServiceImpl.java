package com.demo.service.impl;

import com.demo.mapper.EmpMapper;
import com.demo.pojo.Emp;
import com.demo.pojo.PageBean;
import com.demo.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
public  class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    //条件分页查询员工
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        /*Integer start=(page-1) * pageSize;

        PageBean pageBean=new PageBean(empMapper.count(),empMapper.page(start,pageSize));
         */

        PageHelper.startPage(page, pageSize);//设置分页参数

        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    //删除员工
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    //新增员工
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.insert(emp);
    }

    //修改员工
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
