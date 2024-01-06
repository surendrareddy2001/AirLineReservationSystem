package com.infomerica.ars.awsconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.infomerica.ars.constants.Constants;

@Configuration
public class AwsConfigaration {

	@Primary
	@Bean
	public AmazonSNSClient getSnsClient() {

		return (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(
						new AWSStaticCredentialsProvider(new BasicAWSCredentials(Constants.ACCESS_KEY, Constants.SECRET_KEY)))
				.build();

	}
}
