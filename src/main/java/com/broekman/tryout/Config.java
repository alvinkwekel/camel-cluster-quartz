package com.broekman.tryout;

import org.apache.camel.component.quartz2.QuartzComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class Config {

    @Value("${org.quartz.dataSource.quartz.URL}")
    private String quartzDataSourceQuartzUrl;

    @Value("${org.quartz.dataSource.quartz.user}")
    private String quartzDataSourceQuartzUser;

    @Value("${org.quartz.dataSource.quartz.password}")
    private String quartzDataSourceQuartzPassword;

    @Value("${org.quartz.dataSource.quartz.driver}")
    private String quartzDataSourceQuartzDriver;

    @Value("${org.quartz.jobStore.driverDelegateClass}")
    private String quartzJobStoreDriverDelegateClass;

    @Value("${org.quartz.dataSource.quartz.validationQuery}")
    private String quartzDataSourceQuartzValidationQuery;

    @Bean
    public QuartzComponent quartz2() {
        QuartzComponent component = new QuartzComponent();
        Properties p = new Properties();
        p.put("org.quartz.scheduler.instanceId", "AUTO");
        p.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        p.put("org.quartz.threadPool.threadCount", "25");
        p.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        p.put("org.quartz.jobStore.driverDelegateClass", quartzJobStoreDriverDelegateClass);
        p.put("org.quartz.jobStore.dataSource", "quartz");
        p.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        p.put("org.quartz.jobStore.isClustered", "true");
        p.put("org.quartz.jobStore.txIsolationLevelSerializable", "true");
        p.put("org.quartz.dataSource.quartz.driver", quartzDataSourceQuartzDriver);
        p.put("org.quartz.dataSource.quartz.validationQuery", quartzDataSourceQuartzValidationQuery);
        p.put("org.quartz.dataSource.quartz.URL", quartzDataSourceQuartzUrl);
        p.put("org.quartz.dataSource.quartz.user", quartzDataSourceQuartzUser);
        p.put("org.quartz.dataSource.quartz.password", quartzDataSourceQuartzPassword);
        component.setProperties(p);
        return component;
    }

}
