package com.hongyuan.warehouse.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hongyuan.warehouse.mapper.GoodsMapper;
import com.hongyuan.warehouse.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    public List<Goods> getall(){
        return  goodsMapper.selectList(null);
    }
    public List<Goods> getallbynumber(String num){
        return goodsMapper.selectList(new QueryWrapper<Goods>().like("goodsname",num));
    }
    public boolean insertGoods(Goods goods){
        if (goodsMapper.insert(goods)>0){
            return true;
        }
        return false;
    }
    public boolean update(Goods goods){
        if (goodsMapper.update(goods,null)>0){
            return true;
        }
        return false;
    }
    public Goods getbyid(Integer id){
        return goodsMapper.selectById(id);
    }
    public List<Goods> getgoodsbyisover(Integer integer){
        return goodsMapper.selectList(new QueryWrapper<Goods>().eq("isover",integer));
    }
    public List<Goods> getgoodsbycompanyid(Integer integer){
        return goodsMapper.selectList(new QueryWrapper<Goods>().eq("companyid",integer));

    }
}
