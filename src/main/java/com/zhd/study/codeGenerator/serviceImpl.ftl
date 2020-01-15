package com.zhd.ultimate.sociology.service.impl;

import com.zhd.ultimate.sociology.entity.${firstUpTableName};
import com.zhd.ultimate.sociology.mapping.${firstUpTableName}Mapper;
import com.zhd.ultimate.sociology.service.${firstUpTableName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Service
public class ${firstUpTableName}ServiceImpl implements ${firstUpTableName}Service {

    @Autowired
    private ${firstUpTableName}Mapper ${tableEntityName}Mapper;

    @Override
    public List<${firstUpTableName}> queryAll${firstUpTableName}() {
        return ${tableEntityName}Mapper.queryAll${firstUpTableName}();
    }

    @Override
    public int add(${firstUpTableName} ${tableEntityName}) {
        //TODO
        return ${tableEntityName}Mapper.insert(${tableEntityName});
    }

    @Override
    public int update(${firstUpTableName} ${tableEntityName}) {
        //TODO
        return ${tableEntityName}Mapper.updateByPrimaryKeySelective(${tableEntityName});
    }

    @Override
    public ${firstUpTableName} query${firstUpTableName}(String guid) {
        return ${tableEntityName}Mapper.selectByPrimaryKey(guid);
    }

    @Override
    public int delete(String guid) {
        return ${tableEntityName}Mapper.deleteByPrimaryKey(guid);
    }
}
