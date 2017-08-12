package org.force66.aws;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;

/**
 * This test is a full integration test with AWS requires configured AWS credentials in order to
 * run.
 * 
 * @author D. Ashmore
 *
 */
public class AWSClientUtilsTestIntegration {

  private static final String TEST_REGION = "us-east-1";
  private AWSClientUtils awsClientUtils;

  @Before
  public void setup() throws Exception {
    awsClientUtils = new AWSClientUtils();
  }

  @Test
  public void testbuildAmazonS3Default() {
    AmazonS3 s3 = awsClientUtils.buildAmazonS3();
    Assert.assertNotNull(s3);
  }

  @Test
  public void testBuildAmazonS3WithRegion() throws Exception {
    AmazonS3 s3 = awsClientUtils.buildAmazonS3(TEST_REGION);
    Assert.assertEquals(TEST_REGION, s3.getRegionName());
  }

  @Test(expected = ContextedRuntimeException.class)
  public void testBuildAmazonS3WithRole() throws Exception {
    AssumeRoleRequest assumeRoleRequest = new AssumeRoleRequest();
    assumeRoleRequest.setDurationSeconds(900);
    assumeRoleRequest.setRoleArn("arn:aws:iam::517214143524:role/S3-Admin-Access");
    assumeRoleRequest.setRoleSessionName("demo");

    AmazonS3 s3 = awsClientUtils.buildAmazonS3(TEST_REGION, assumeRoleRequest);
    Assert.assertEquals(TEST_REGION, s3.getRegionName());
  }

}
