package com.zhd.ultimate.sociology.service;

import com.zhd.ultimate.sociology.entity.${firstUpTableName};

import java.util.List;


public interface ${firstUpTableName}Service {

    List<${firstUpTableName}> queryAll${firstUpTableName}();

    int add(${firstUpTableName} ${tableEntityName});

    int update(${firstUpTableName} ${tableEntityName});

    ${firstUpTableName} query${firstUpTableName}(String guid);

    int delete(String guid);
}
