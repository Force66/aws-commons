package org.force66.aws;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;

/**
 * This test is a full integration test with AWS requires configured AWS credentials in order to
 * run.
 * 
 * @author D. Ashmore
 *
 */
@RunWith(ConcurrentTestRunner.class)
public class AWSClientUtilsTestIntegration {

  private static final String TEST_REGION = "us-east-1";
  private static Logger logger = LoggerFactory.getLogger(AWSClientUtilsTestIntegration.class);
  private AWSClientUtils awsClientUtils;

  @Before
  public void setup() throws Exception {
    awsClientUtils = new AWSClientUtils();
  }

  @Test
  public void testbuildAmazonS3Default() {
    logger.info("Executing testbuildAmazonS3Default on thread {}", Thread.currentThread().getName());
    AmazonS3 s3 = awsClientUtils.buildAmazonS3();
    Assert.assertNotNull(s3);
  }

  @Test
  public void testBuildAmazonS3WithRegion() throws Exception {
    logger.info("Executing testBuildAmazonS3WithRegion on thread {}", Thread.currentThread().getName());
    AmazonS3 s3 = awsClientUtils.buildAmazonS3(TEST_REGION);
    Assert.assertEquals(TEST_REGION, s3.getRegionName());
  }

  @Test(expected = ContextedRuntimeException.class)
  public void testBuildAmazonS3WithRole() throws Exception {
    logger.info("Executing testBuildAmazonS3WithRole on thread {}", Thread.currentThread().getName());
    AssumeRoleRequest assumeRoleRequest = new AssumeRoleRequest();
    assumeRoleRequest.setDurationSeconds(900);
    assumeRoleRequest.setRoleArn(IntegrationTestConfiguration.getPropertyAsString(IntegrationTestConfiguration.TEST_ROLE_PROP));
    assumeRoleRequest.setRoleSessionName("demo");

    AmazonS3 s3 = awsClientUtils.buildAmazonS3(TEST_REGION, assumeRoleRequest);
    Assert.assertEquals(TEST_REGION, s3.getRegionName());
  }

}
