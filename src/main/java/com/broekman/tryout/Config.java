package com.broekman.tryout;

import org.apache.camel.component.quartz2.QuartzComponent;
import org.quartz.Scheduler;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class Config {

    @Bean
    public SchedulerFactoryBean mySchedulerFactory(DataSource dataSource, QuartzProperties quartzProperties) {

        Properties properties = new Properties();
        properties.putAll(quartzProperties.getProperties());

        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setWaitForJobsToCompleteOnShutdown(true);
        factory.setDataSource(dataSource);
        factory.setQuartzProperties(properties);
        factory.setJobFactory(new SpringBeanJobFactory());
        return factory;
    }

    // Could also use SchedulerFactoryBeanCustomizer

    @Bean
    public Scheduler myScheduler(DataSource dataSource, QuartzProperties quartzProperties) {
        return mySchedulerFactory(dataSource, quartzProperties).getScheduler();
    }

    @Bean
    public QuartzComponent quartz2(ApplicationContext applicationContext, DataSource dataSource, QuartzProperties quartzProperties) {
        QuartzComponent component = new QuartzComponent();
        component.setScheduler(myScheduler(dataSource, quartzProperties));
        return component;
    }
}