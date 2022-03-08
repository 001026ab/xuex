package com.example.common.crud;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/18 18:02
 */

public class BaseService<T, K> implements IService<T, K> {

    @Autowired
    protected Mapper<T, K> mapper;

    //当前泛型的真实类型Class
    private Class<T> modelClass;

    //通过泛型反射，获取子类中实际要操作的对象class，通过class，service就知道要对哪个对象进行增删改查操操作。
    public BaseService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public Object insert(T t) {
        return mapper.insert(t);
    }

    @Override
    public Object update(T t) {
        return mapper.updateByPrimaryKey(t);
    }

    @Override
    public Object delete(K id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public T get(K id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public Object queryPageList(int pageSize, int pageIndex, Map<String, Object> params) {
        PageHelper.startPage(pageIndex, pageSize);
        Page page = mapper.queryPageList(params);//Page本身是一个ArrayList对象，转换为json时不会保留分页信息
        PageInfo pageInfo = page.toPageInfo();//将page转换成pageInfo会保存分页信息返回
       // return new PageModel(pageInfo);
        return pageInfo;
    }

    @Override
    public Object queryList(Map<String, Object> params) {
        return mapper.queryList(params);
    }

}