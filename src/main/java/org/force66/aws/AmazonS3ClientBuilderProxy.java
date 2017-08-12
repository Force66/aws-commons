package org.force66.aws;

import org.apache.commons.lang3.Validate;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * Internal proxy for an AmazonS3ClientBuilder so that it can be mocked.
 * 
 * @author D. Ashmore
 *
 */
class AmazonS3ClientBuilderProxy {

  private AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();

  public AmazonS3ClientBuilderProxy withRegion(Regions regions) {
    Validate.notNull(regions, "Internal error: regions can't be null");
    builder.withRegion(regions);
    return this;
  }

  public AmazonS3ClientBuilderProxy withCredentials(AWSCredentialsProvider creds) {
    Validate.notNull(creds, "Internal error: creds can't be null");
    builder.withCredentials(creds);
    return this;
  }

  public AmazonS3 build() {
    return builder.build();
  }

}
