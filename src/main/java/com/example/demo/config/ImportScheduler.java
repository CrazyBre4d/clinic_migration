package com.example.demo.config;

import com.example.demo.services.ImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImportScheduler {
    private final ImportService importService;

    @Scheduled(cron = "0 15 1/2 * * *")
    public void runImport() {
        importService.runImport();
    }
}
