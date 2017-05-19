package com.zm.mall.service.business.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zm.mall.client.PageVo;
import com.zm.mall.client.result.business.product.SupplierInfoResult;
import com.zm.mall.client.result.system.PageResult;
import com.zm.mall.client.service.business.product.SupplierInfoService;
import com.zm.mall.client.vo.business.product.SupplierInfoVo;
import com.zm.mall.dao.AbstractRedisBaseDao;
import com.zm.mall.dao.business.product.RegionInfoDao;
import com.zm.mall.dao.business.product.SupplierInfoDao;
import com.zm.mall.domain.business.product.BrandInfo;
import com.zm.mall.domain.business.product.SupplierInfo;
import com.zm.mall.domain.system.Page;
import com.zm.mall.service.SpaceBeanCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/25.
 */
@Service("supplierInfoService")
public class SupplierInfoServiceImpl extends AbstractRedisBaseDao<String,SupplierInfo> implements SupplierInfoService{
    @Autowired
    private SupplierInfoDao supplierInfoDao;
    @Autowired
    private RegionInfoDao regionInfoDao;

    /**
     * 新增
     *<br>------------------------------<br>
     *
     * @param supplierInfo
     * @return
     */
    public boolean add(final SupplierInfo supplierInfo) {
        try {
            boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    RedisSerializer<String> serializer = getRedisSerializer();
                    byte[] key  = serializer.serialize(supplierInfo.getSupplierId()+"");
                    byte[] name = serializer.serialize(supplierInfo.getName());
                    return connection.setNX(key, name);
                }
            });
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 删除
     * <br>------------------------------<br>
     * @param key
     */
    public void delete(String key) {
        List<String> list=new ArrayList<String>();
        list.add(key);
        delete(list);
    }
    /**
     * 删除多个
     * <br>------------------------------<br>
     * @param keys
     */
    public void delete(List<String> keys) {
        try {
            redisTemplate.delete(keys);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改
     * <br>------------------------------<br>
     * @param supplierInfor
     * @return
     */
    public boolean update(final SupplierInfo supplierInfor) {
        String key = supplierInfor.getSupplierId()+"";
        if (key == null) {
            throw new NullPointerException("数据行不存在, key = " + key);
        }
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key  = serializer.serialize(supplierInfor.getSupplierId()+"");
                byte[] name = serializer.serialize(supplierInfor.getName());
                connection.set(key, name);
                return true;
            }
        });
        return result;
    }

    /**
     * 通过key获取
     * <br>------------------------------<br>
     * @param keyId
     * @return
     */
    public SupplierInfo get(final String keyId) {
        SupplierInfo result = redisTemplate.execute(new RedisCallback<SupplierInfo>() {
            public SupplierInfo doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(keyId);
                byte[] value = connection.get(key);
                if (value == null) {
                    return null;
                }

                String name = serializer.deserialize(value);
                SupplierInfo supplierInfo=new SupplierInfo();
                supplierInfo.setSupplierId(Long.valueOf(keyId));
                supplierInfo.setName(name);
                return supplierInfo;
            }
        });
        return result;
    }

    @Override
    public PageResult<SupplierInfo> select(PageVo<SupplierInfo> pageVo)throws Exception{
        pageVo.setPageSize(20);
        if(pageVo.getCurrentPage()==0){
            pageVo.setCurrentPage(1);
        }
        Page<SupplierInfo> page=SpaceBeanCopy.PageVoToPage(pageVo);
        PageHelper.startPage(pageVo.getCurrentPage(),pageVo.getPageSize());
        SupplierInfo s=new SupplierInfo();
        if( page.getT()!=null){
            s.setName(page.getT().getName());
        }
        List<SupplierInfo> list=supplierInfoDao.selectPage(s);
        PageInfo<SupplierInfo> p=new PageInfo<SupplierInfo>(list);
        page.setTotalPages(p.getPages());
        page.setTotalCount((int)p.getTotal());
        page.setResult(p.getList());
        return SpaceBeanCopy.PageToPageResult(page);
    }

    @Override
    public SupplierInfoResult selectOne(SupplierInfoVo supplierInfoVo){
        SupplierInfo supplierInfo=SpaceBeanCopy.supplierInfoVoToSupplierInfo(supplierInfoVo);
        return SpaceBeanCopy.supplierInfoToSupplierInfoResult(supplierInfoDao.selectById(supplierInfo));
    }
    @Override
    public void delete(SupplierInfoVo supplierInfoVo){
        SupplierInfo supplierInfo=SpaceBeanCopy.supplierInfoVoToSupplierInfo(supplierInfoVo);
        supplierInfoDao.delete(supplierInfo);
    }

    @Override
    public void insert(SupplierInfoVo supplierInfoVo){
        try {
            SupplierInfo supplierInfo=SpaceBeanCopy.supplierInfoVoToSupplierInfo(supplierInfoVo);
            supplierInfoDao.insertSupplierInfo(supplierInfo);
           //修改用户状态为

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(SupplierInfoVo supplierInfoVo){
       SupplierInfo supplierInfo=SpaceBeanCopy.supplierInfoVoToSupplierInfo(supplierInfoVo);
        supplierInfoDao.updateSupplierInfo(supplierInfo);
    }

    @Override
    public List<BrandInfo> getAllBrands() {
        return supplierInfoDao.getBrandInfos();
    }
}
