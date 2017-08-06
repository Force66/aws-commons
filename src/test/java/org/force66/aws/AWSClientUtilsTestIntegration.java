package org.force66.aws;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.services.s3.AmazonS3;

public class AWSClientUtilsTestIntegration {

	private static final String TEST_REGION = "us-east-1";

	@Test
	public void testbuildAmazonS3() {
		AmazonS3 s3 = AWSClientUtils.buildAmazonS3();
		Assert.assertNotNull(s3);
	}
	
	@Test
	public void testBuildAmazonS3String() throws Exception {
		AmazonS3 s3 = AWSClientUtils.buildAmazonS3(TEST_REGION);
		Assert.assertEquals(TEST_REGION, s3.getRegionName());
	}


}
