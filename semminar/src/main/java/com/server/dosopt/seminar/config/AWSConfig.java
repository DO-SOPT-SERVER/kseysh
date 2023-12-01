package com.server.dosopt.seminar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

// AWS 관련 의존성을 Spring Bean으로 등록하는 코드
// access key와 secret key를 이용해 aws에 접근할 수 있도록 한다.
@Configuration
public class AWSConfig {

    private static final String AWS_ACCESS_KEY_ID = "aws.accessKeyId";
    private static final String AWS_SECRET_ACCESS_KEY = "aws.secretAccessKey";

    private final String accessKey;
    private final String secretKey;
    private final String regionString;

    public AWSConfig(@Value("${aws-property.access-key}") final String accessKey,
                     @Value("${aws-property.secret-key}") final String secretKey,
                     @Value("${aws-property.aws-region}") final String regionString) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.regionString = regionString;
    }


    @Bean
    public SystemPropertyCredentialsProvider systemPropertyCredentialsProvider() {
        System.setProperty(AWS_ACCESS_KEY_ID, accessKey);
        System.setProperty(AWS_SECRET_ACCESS_KEY, secretKey);
        return SystemPropertyCredentialsProvider.create();// credentialsProvider안에 임시 자격 증명을 할 수 있는 class를 넣는 코드
    }

    @Bean
    public Region getRegion() {
        return Region.of(regionString);// region aws에서 어떤 지역을 이용할지
    }

    @Bean
    public S3Client getS3Client() {
        return S3Client.builder()
                .region(getRegion())
                .credentialsProvider(systemPropertyCredentialsProvider())
                .build();
    }
}