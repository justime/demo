package org.vean.platform.service.dao;

import org.springframework.stereotype.Service;
import org.vean.platform.dao.dataobject.DataSourceDO;
import org.vean.platform.dao.mapper.DataSourceMapper;

import javax.annotation.Resource;
import java.util.List;

@Service("datasourceDao")
public class DataSourceDao {
    @Resource
    DataSourceMapper dataSourceMapper;

    public List<DataSourceDO> query() {
        return dataSourceMapper.getAllDataSources();

    }
    
    public DataSourceDO queryByDatasourceName(String datasourceName) {
        return dataSourceMapper.queryByDatasourceName(datasourceName);

    }
}
