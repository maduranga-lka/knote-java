package com.madu.ms.knotejava.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "knote")
public class KnoteProperties {
    @Value("${uploadDir:/tmp/uploads/}")
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }
}
