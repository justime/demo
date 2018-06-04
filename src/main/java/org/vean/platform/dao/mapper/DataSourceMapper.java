package org.vean.platform.dao.mapper;

import java.util.List;

import org.vean.platform.dao.dataobject.DataSourceDO;

public interface DataSourceMapper {

    List<DataSourceDO> getAllDataSources();

}