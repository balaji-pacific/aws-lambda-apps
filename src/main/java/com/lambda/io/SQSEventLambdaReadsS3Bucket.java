package com.lambda.io;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SQSEventLambdaReadsS3Bucket implements RequestHandler<SQSEvent, String> {
	private static final AmazonS3 s3Client = AmazonS3Client.builder()
			.withCredentials(new DefaultAWSCredentialsProviderChain())
			.build();

	@Override
	public String handleRequest(SQSEvent input, Context context) {

		final LambdaLogger logger = context.getLogger();

		for(SQSMessage message: input.getRecords()) {

			logger.log("Message Value : " + message.toString());
			logger.log("Message Body Value : " + message.getBody());


			String bucketName = message.getBody().substring(message.getBody().lastIndexOf("name")+9, message.getBody().indexOf("\"owner")-4);
			String key = message.getBody().substring(message.getBody().lastIndexOf("key")+8, message.getBody().indexOf("\"size")-4);

			logger.log("Value of BucketName : " + bucketName + " and key name : " + key);

			S3Object s3Object = s3Client.getObject(bucketName, key);
			S3ObjectInputStream inputStream = s3Object.getObjectContent();

			try{
				final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
				String line = "";
				while((line = br.readLine()) != null){
					System.out.println(line);
				}
			}catch(IOException ex){
				ex.printStackTrace();
				return "Failure";
			}
		}
		return "Success";
	}
}
