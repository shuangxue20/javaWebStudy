package com.demo.service.impl;

import com.demo.mapper.DeptMapper;
import com.demo.mapper.EmpMapper;
import com.demo.pojo.Dept;
import com.demo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    //查询部门
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }


    //删除部门
    @Transactional(rollbackFor = Exception.class)//添加事务管理,rollbackFor指定哪些异常回滚
    // ,propagation = Propagation.REQUIRES_NEW为创建新事务，可以用来保证事务不相互影响
    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);


        empMapper.deleteByDeptId(id);
    }


    //新增部门
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }

    //修改部门
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

}
