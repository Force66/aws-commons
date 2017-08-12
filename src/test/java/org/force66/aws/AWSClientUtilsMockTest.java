package org.force66.aws;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;

/**
 * This test ensures that AWSClientUtils can satisfy one of its use cases and be mocked. The point
 * is *not* to test Mockito's ability to "mock"; that's their job. The point is to make sure we
 * haven't done anything to AWSClientUtils that *prevents* it from being mocked.
 * 
 * @author D. Ashmore
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AWSClientUtilsMockTest {

  @Mock
  private AWSClientUtils awsClientUtils;

  @Mock
  private AmazonS3 amazonS3Mock;

  @Before
  public void setUp() throws Exception {
    Mockito.when(awsClientUtils.buildAmazonS3()).thenReturn(amazonS3Mock);
    Mockito.when(awsClientUtils.buildAmazonS3("us-east-1")).thenReturn(amazonS3Mock);
    Mockito.when(awsClientUtils.buildAmazonS3(ArgumentMatchers.anyString(),
        ArgumentMatchers.any(AssumeRoleRequest.class))).thenReturn(amazonS3Mock);
  }

  @Test
  public void testBuild() {
    AmazonS3 s3 = awsClientUtils.buildAmazonS3();
    Assert.assertEquals(amazonS3Mock, s3);
  }

  @Test
  public void testBuildWithRegion() {
    AmazonS3 s3 = awsClientUtils.buildAmazonS3("us-east-1");
    Assert.assertEquals(amazonS3Mock, s3);
  }

  @Test
  public void testBuildWithRegionAssumingRole() {
    AmazonS3 s3 = awsClientUtils.buildAmazonS3("us-east-1", new AssumeRoleRequest());
    Assert.assertEquals(amazonS3Mock, s3);
  }

}
