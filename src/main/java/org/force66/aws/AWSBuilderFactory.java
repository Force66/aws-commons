package org.force66.aws;

import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;

/**
 * Factory that produces AWS S3 client instances
 * @author D. Ashmore
 *
 */
public class AWSBuilderFactory {
	
	public AmazonS3ClientBuilder createAmazonS3ClientBuilder() {
		return AmazonS3ClientBuilder.standard();
	}
	
	public AWSSecurityTokenServiceClientBuilder createAWSSecurityTokenServiceClientBuilder() {
		return AWSSecurityTokenServiceClientBuilder.standard();
	}

}
