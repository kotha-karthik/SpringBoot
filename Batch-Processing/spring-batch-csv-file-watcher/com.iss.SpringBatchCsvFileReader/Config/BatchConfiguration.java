package com.iss.springbatchprocessing.Config;

import com.iss.springbatchprocessing.Models.Product;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.infrastructure.item.ItemWriter;

import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.database.JpaItemWriter;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration
{
    // this is the item reader
    @Bean
    @StepScope //It tells Spring not to create this bean until the step actually starts
    public FlatFileItemReader<Product> reader(@Value("#{jobParameters['file.name']}") String filePath)
    {
        return new FlatFileItemReaderBuilder<Product>()
                .name("productItemReader")
//                .resource(new ClassPathResource("Sample-data.csv"))
                .resource(new FileSystemResource(filePath))
                .delimited()
                .names("productid","productname","productprice")
                .targetType(Product.class)
                .build();

    }
    // this will be the item writer
//    @Bean
//    public ItemWriter<Product> writer()
//    {
//        return items -> {
//            for(Product p : items){
//                System.out.println(p);
//            }
//        };
//    }
    @Bean
    public JpaItemWriter<Product> writer(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriter<>(entityManagerFactory);
    }
    // 3. The Step (Reads 5 items at a time)
    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager, ItemWriter<Product> itemWriter,FlatFileItemReader<Product> reader) {
        return new StepBuilder("csv-step", jobRepository)
                .<Product, Product>chunk(5)
                .reader(reader)
                .writer(itemWriter)
                .transactionManager(transactionManager)
                .build();
    }

    // 4. The Job
    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("importProductJob", jobRepository)
                .start(step1)
                .build();
    }
}
