package com.test.fileapi.config;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class GcsConfig {

    @Bean
    public Storage storage() throws IOException {
        return StorageOptions.newBuilder()
                .setCredentials(
                        ServiceAccountCredentials.fromStream(
                                new ClassPathResource("gcp-credentials/cyworld-456214-a36c338826d4.json").getInputStream()
                        )
                )
                .build()
                .getService();
    }

}
