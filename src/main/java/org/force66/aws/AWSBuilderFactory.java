package org.force66.aws;

/**
 * Factory that produces AWS S3 client instances
 * @author D. Ashmore
 *
 */
class AWSBuilderFactory {
	
	public AmazonS3ClientBuilderProxy createAmazonS3ClientBuilder() {
		return new AmazonS3ClientBuilderProxy();
	}
	
	public AWSSecurityTokenServiceClientBuilderProxy createAWSSecurityTokenServiceClientBuilder() {
		return new AWSSecurityTokenServiceClientBuilderProxy();
	}

}
