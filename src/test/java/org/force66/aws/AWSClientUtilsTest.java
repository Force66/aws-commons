package org.force66.aws;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.securitytoken.model.Credentials;

@RunWith(MockitoJUnitRunner.class)
public class AWSClientUtilsTest {
	
	private static final String TEST_REGION = "us-east-1";
	private static final String TEST_SESSIONTOKEN = "testSessiontoken";
	private static final String TEST_SECRET_ACCESS_KEY = "testSecretAccessKey";
	private static final String TEST_ACCESS_KEY_ID = "testAccessKeyId";
	private AWSClientUtils awsClientUtils;
	private AssumeRoleRequest assumeRoleRequest;
	private AssumeRoleResult assumeRoleResult;
	
	@Mock
	private AWSBuilderFactory awsBuilderFactoryMock;
	
	@Mock
	private AmazonS3ClientBuilderProxy amazonS3ClientBuilderProxyMock;
	
	@Mock
	private AWSSecurityTokenServiceClientBuilderProxy awsSecurityTokenServiceClientBuilderProxyMock;
	
	@Mock
	private AmazonS3 amazonS3Mock;
	
	@Mock
	private AWSSecurityTokenService awsSecurityTokenServiceMock;
	
	@Before
	public void setup() throws Exception {		
		assumeRoleRequest = new AssumeRoleRequest();
		assumeRoleResult = new AssumeRoleResult().withCredentials(new Credentials());
		assumeRoleResult.getCredentials().setAccessKeyId(TEST_ACCESS_KEY_ID);
		assumeRoleResult.getCredentials().setSecretAccessKey(TEST_SECRET_ACCESS_KEY);
		assumeRoleResult.getCredentials().setSessionToken(TEST_SESSIONTOKEN);
		
		awsClientUtils = new AWSClientUtils();
		FieldUtils.writeField(awsClientUtils, "factory", awsBuilderFactoryMock, true);
		
		Mockito.when(awsBuilderFactoryMock.createAmazonS3ClientBuilder()).thenReturn(amazonS3ClientBuilderProxyMock);
		Mockito.when(awsBuilderFactoryMock.createAWSSecurityTokenServiceClientBuilder())
			.thenReturn(awsSecurityTokenServiceClientBuilderProxyMock);
		
		Mockito.when(amazonS3ClientBuilderProxyMock.withRegion(ArgumentMatchers.any(Regions.class)))
			.thenReturn(amazonS3ClientBuilderProxyMock);
		Mockito.when(amazonS3ClientBuilderProxyMock.withCredentials(ArgumentMatchers.any(AWSCredentialsProvider.class)))
			.thenReturn(amazonS3ClientBuilderProxyMock);
		Mockito.when(amazonS3ClientBuilderProxyMock.build()).thenReturn(amazonS3Mock);
		
		Mockito.when(awsSecurityTokenServiceClientBuilderProxyMock.build())
			.thenReturn(awsSecurityTokenServiceMock);
		Mockito.when(awsSecurityTokenServiceMock.assumeRole(ArgumentMatchers.any(AssumeRoleRequest.class)))
			.thenReturn(assumeRoleResult);
	}

	@Test(expected = NullPointerException.class)
	public void testValidateNullRegionName() throws Exception {
		awsClientUtils.validateRegionName(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateInvalidRegionName() throws Exception {
		awsClientUtils.validateRegionName("does-not-exist");
	}
	
	@Test
	public void testValidateValidRegionName() throws Exception {
		Regions region = awsClientUtils.validateRegionName(TEST_REGION);
		Assert.assertEquals(region, Regions.US_EAST_1);
	}
	
	@Test(expected = NullPointerException.class)
	public void testValidateNullRole() throws Exception {
		awsClientUtils.createAssumedRoleCredentials(null);
	}
	
	@Test
	public void testCreateAssumedRoleCredentials() throws Exception {
		AWSCredentialsProvider credProvider = awsClientUtils.createAssumedRoleCredentials(assumeRoleRequest);
		Assert.assertEquals(TEST_ACCESS_KEY_ID, credProvider.getCredentials().getAWSAccessKeyId());
		Assert.assertEquals(TEST_SECRET_ACCESS_KEY, credProvider.getCredentials().getAWSSecretKey());
		Assert.assertEquals(TEST_SESSIONTOKEN, ((BasicSessionCredentials)credProvider.getCredentials()).getSessionToken());
	}
	
	@Test
	public void testCreateAssumedRoleCredentials_StsException() throws Exception {
		RuntimeException rExpt = new RuntimeException("crap");
		Mockito.when(awsBuilderFactoryMock.createAWSSecurityTokenServiceClientBuilder())
			.thenThrow(rExpt);
		
		try {
			awsClientUtils.createAssumedRoleCredentials(assumeRoleRequest);
			Assert.fail();
		} catch(AWSCommonsException e) {
			Assert.assertEquals(rExpt, e.getCause());
			Assert.assertEquals(0, e.getContextEntries().size());
		}
	}
	
	@Test
	public void testCreateAssumedRoleCredentials_AssumedRoleException() throws Exception {
		RuntimeException rExpt = new RuntimeException("crap");
		Mockito.when(awsSecurityTokenServiceMock.assumeRole(assumeRoleRequest))
			.thenThrow(rExpt);
		
		try {
			awsClientUtils.createAssumedRoleCredentials(assumeRoleRequest);
			Assert.fail();
		} catch(AWSCommonsException e) {
			Assert.assertEquals(rExpt, e.getCause());
			Assert.assertEquals(1, e.getContextEntries().size());
		}
	}
	
	@Test
	public void testDefaultS3Create() throws Exception {
		AmazonS3 s3 = awsClientUtils.buildAmazonS3();
		Assert.assertEquals(amazonS3Mock, s3);
	}
	
	@Test(expected = AWSCommonsException.class)
	public void testS3CreateWithException() throws Exception {
		Mockito.when(awsBuilderFactoryMock.createAmazonS3ClientBuilder())
			.thenThrow(new RuntimeException("crap"));
		awsClientUtils.buildAmazonS3();
	}
	
	@Test
	public void testS3CreateWithRegion() throws Exception {			
		AmazonS3 s3 = awsClientUtils.buildAmazonS3(TEST_REGION);
		Assert.assertEquals(amazonS3Mock, s3);
		
		validateRegion();
	}

	private void validateRegion() {
		ArgumentCaptor<Regions> regionsArg = ArgumentCaptor.forClass(Regions.class);
		Mockito.verify(amazonS3ClientBuilderProxyMock).withRegion(regionsArg.capture());
		Assert.assertEquals(TEST_REGION, regionsArg.getValue().getName());
	}
	
	@Test
	public void testS3CreateWithRegionAndRole() throws Exception {
		AmazonS3 s3 = awsClientUtils.buildAmazonS3(TEST_REGION, assumeRoleRequest);
		Assert.assertEquals(amazonS3Mock, s3);
		
		validateRegion();
		validateRole();
	}

	private void validateRole() {
		ArgumentCaptor<AWSCredentialsProvider> credsArg = ArgumentCaptor.forClass(AWSCredentialsProvider.class);
		Mockito.verify(amazonS3ClientBuilderProxyMock).withCredentials(credsArg.capture());
		Assert.assertEquals(assumeRoleResult.getCredentials().getSecretAccessKey(), 
				credsArg.getValue().getCredentials().getAWSSecretKey());
		Assert.assertEquals(assumeRoleResult.getCredentials().getAccessKeyId(), 
				credsArg.getValue().getCredentials().getAWSAccessKeyId());
	}

}
