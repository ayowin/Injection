package com.wz.injection.config;

import com.wz.injection.Configuration;
import com.wz.injection.Inject;

@Configuration
public class TestConfig {

    public static class DataSource{

    }

    private static class Connection{
        private DataSource dataSource;
        public Connection(DataSource dataSource){
            this.dataSource = dataSource;
        }
    }

    @Inject
    public DataSource dataSource(){
        DataSource dataSource = new DataSource();
        System.out.println(dataSource);
        return dataSource;
    }

    @Inject
    public Connection connection(DataSource dataSource){
        Connection connection = new Connection(dataSource);
        System.out.println(connection);
        return connection;
    }

}
