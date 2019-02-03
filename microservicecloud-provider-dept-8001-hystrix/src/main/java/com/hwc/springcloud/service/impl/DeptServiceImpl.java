package com.hwc.springcloud.service.impl;

import com.hwc.springcloud.dao.DeptDAO;
import com.hwc.springcloud.entities.Dept;
import com.hwc.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDAO deptDAO;

    @Override
    public boolean add(Dept dept) {
        return deptDAO.addDept(dept);
    }

    @Override
    public Dept get(Long id) {
        return deptDAO.findById(id);
    }

    @Override
    public List<Dept> list() {
        return deptDAO.findAll();
    }
}
