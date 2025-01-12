package com.demo.controller;


import com.demo.anno.Log;
import com.demo.pojo.Dept;
import com.demo.pojo.Result;
import com.demo.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;


    //查询部门
    @GetMapping
    public Result list(){
        log.info("查询全部部门信息");
        //调用service查询部门信息
        return Result.success(deptService.list());
    }



    //删除部门
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除部门:{}",id);
        deptService.delete(id);
        return Result.success();
    }


    //新增部门
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加部门:{}",dept);
        deptService.add(dept);
        return Result.success();
    }


    //修改部门
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询部门信息:{}",id);
        Dept dept=deptService.getById(id);
        return Result.success(dept);
    }
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门:{}",dept);
        deptService.update(dept);
        return Result.success();
    }


}
