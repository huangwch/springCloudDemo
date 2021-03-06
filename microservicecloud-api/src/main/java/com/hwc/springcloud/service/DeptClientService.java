package com.hwc.springcloud.service;

import com.hwc.springcloud.entities.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//@FeignClient(name="MICROSERVICECLOUD-DEPT") // 配套使用@HystrixCommand注解对某个provider接口做后备处理，对应的服务主启动类上使用@EnableCircuteBreaker标注
@FeignClient(name="MICROSERVICECLOUD-DEPT",fallbackFactory = DeptServiceFallbackFactory.class)
public interface DeptClientService {
    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept);

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list();
}
