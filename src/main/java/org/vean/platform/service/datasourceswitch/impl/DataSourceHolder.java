package org.vean.platform.service.datasourceswitch.impl;

import org.vean.platform.service.datasourceswitch.DataSourceBeanBuilder;

public final class DataSourceHolder {
    private static ThreadLocal<DataSourceBeanBuilder> threadLocal=new ThreadLocal<DataSourceBeanBuilder>(){
        @Override
        protected DataSourceBeanBuilder initialValue() {
            return null;
        }
    };

    static DataSourceBeanBuilder getDataSource(){
        return threadLocal.get();
    }
    
    //使用该方法设置数据源
    public static void setDataSource(DataSourceBeanBuilder dataSourceBeanBuilder){
        threadLocal.set(dataSourceBeanBuilder);
    }

	//使用该方法清除数据源，清除后将使用默认数据源
    public static void clearDataSource(){
        threadLocal.remove();
    }
}
