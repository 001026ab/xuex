package com.example.common.crud;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/18 18:00
 */


public class BaseController<T, K> extends AbstractController<T, K> {

    @Autowired
    private IService<T, K> service;

    @PostMapping("/insert")
    @Override
    public Object insert(@RequestBody T t) {
        return service.insert(t);
    }

    @PostMapping("/update")
    @Override
    public Object update(@RequestBody T t) {
        return service.update(t);
    }

    @GetMapping("/delete")
    @Override
    public Object delete(K id) {
        return service.delete(id);
    }

    @GetMapping("/get")
    @Override
    public Object get(K id) {
        return service.get(id);
    }

    @GetMapping("/page-list")
    @Override
    public Object queryPageList(@RequestParam(required = false, defaultValue = "20") int pageSize,
                                 @RequestParam(required = false, defaultValue = "1") int pageIndex,
                                 @RequestParam Map<String,Object> params) {
        return service.queryPageList(pageSize, pageIndex, params);
    }

    @GetMapping("/list")
    @Override
    public Object queryList(@RequestParam Map<String, Object> params) {
        return service.queryList(params);
    }
}