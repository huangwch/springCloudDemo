package com.hwc.springcloud.dao;

import com.hwc.springcloud.entities.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptDAO {
    boolean addDept(Dept dept);

    Dept findById(Long id);

    List<Dept> findAll();
}
