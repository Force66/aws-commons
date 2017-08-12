package org.force66.aws;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;

/**
 * Collection of one-line utilities to get configured AWS clients
 * 
 * @author D. Ashmore
 *
 */
public class AWSClientUtils {

  private AWSBuilderFactory factory = new AWSBuilderFactory();

  /**
   * Builds the default AmazonS3 client based on your current preference and account settings
   * 
   * @return AmazonS3 S3Client
   */
  public AmazonS3 buildAmazonS3() {
    return buildAmazonS3(null, null);
  }

  /**
   * Builds a standard S3 client specifying the region provided.
   * 
   * @param regionName
   * @return AmazonS3 S3Client
   */
  public AmazonS3 buildAmazonS3(String regionName) {
    return buildAmazonS3(regionName, null);
  }

  /**
   * Builds a standard S3 client specifying the region provided under an assumed role.
   * 
   * @param regionName
   * @param assumedRoleRequest
   * @return AmazonS3 S3Client
   */
  public AmazonS3 buildAmazonS3(String regionName, AssumeRoleRequest assumedRole) {

    AmazonS3ClientBuilderProxy s3Builder = null;
    try {
      s3Builder = factory.createAmazonS3ClientBuilder();
    } catch (Exception e) {
      throw new AWSCommonsException("Error creating AWS S3 client", e);
    }

    if (StringUtils.isNotBlank(regionName)) {
      Regions regions = validateRegionName(regionName);
      s3Builder = s3Builder.withRegion(regions);
    }
    if (assumedRole != null) {
      AWSCredentialsProvider creds = createAssumedRoleCredentials(assumedRole);
      s3Builder = s3Builder.withCredentials(creds);
    }

    return s3Builder.build();
  }

  protected Regions validateRegionName(String regionName) {
    Validate.notEmpty(regionName, "Null or blank region name not allowed.");
    Regions regions = Regions.fromName(regionName);
    Validate.notNull(regions, "%s is not a valid region name", regionName);
    return regions;
  }

  protected AWSCredentialsProvider createAssumedRoleCredentials(AssumeRoleRequest assumedRole) {
    Validate.notNull(assumedRole, "assumedRole can't be null");
    AWSSecurityTokenServiceClientBuilderProxy stsBuilder = null;
    try {
      stsBuilder = factory.createAWSSecurityTokenServiceClientBuilder();
    } catch (Exception e1) {
      throw new AWSCommonsException("Error creating AWS STS client", e1);
    }

    AssumeRoleResult requestResult = null;
    try {
      requestResult = stsBuilder.build().assumeRole(assumedRole);
    } catch (Exception e) {
      throw new AWSCommonsException("Error assuming AWS role", e).addContextValue("assumedRole",
          ReflectionToStringBuilder.toString(assumedRole));
    }

    BasicSessionCredentials tempCreds =
        new BasicSessionCredentials(requestResult.getCredentials().getAccessKeyId(),
            requestResult.getCredentials().getSecretAccessKey(),
            requestResult.getCredentials().getSessionToken());

    return new AWSStaticCredentialsProvider(tempCreds);
  }

}
