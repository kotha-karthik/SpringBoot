package com.iss.springbatchprocessing.Config;

import jakarta.annotation.PostConstruct;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.Duration;
import java.util.Properties;

@Component
public class MyFileWatcher {
    private final FileSystemWatcher watcher;
    private final JobLauncher jobLauncher;
    private final Job importProductJob;

    public MyFileWatcher(JobLauncher jobLauncher, Job importProductJob) {
        this.jobLauncher = jobLauncher;
        this.importProductJob = importProductJob;

        this.watcher = new FileSystemWatcher(
                false,
                Duration.ofSeconds(5),
                Duration.ofSeconds(3)
        );
    }

    @PostConstruct
    public void init() {

        File directoryToWatch = new File("D:\\files");
        watcher.addSourceDirectory(directoryToWatch);

        watcher.addListener(changeSet -> {
            for (ChangedFiles changedFiles : changeSet) {
                for (ChangedFile change : changedFiles.getFiles()) {

                    if (change.getFile().getName().endsWith(".csv")) {
                        try {
                            JobParameters jobParameters = new JobParametersBuilder()
                                    .addLong("run.id", System.currentTimeMillis()) // ensures uniqueness
                                    .addString("file.name", change.getFile().getAbsolutePath())
                                    .toJobParameters();

                            jobLauncher.run(importProductJob, jobParameters);//syn

                            System.out.println("Batch Job triggered for file: "
                                    + change.getFile().getName());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        watcher.start();
        System.out.println("FileSystemWatcher started...");
    }
}
