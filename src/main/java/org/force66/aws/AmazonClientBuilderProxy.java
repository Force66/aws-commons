package org.force66.aws;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.MethodUtils;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;

/**
 * Generic AwsClientBuilder proxy to allow AWSClientUtils to easily be mocked.
 * @author D. Ashmore
 *
 */
class AmazonClientBuilderProxy {
  private  AwsClientBuilder<?, ?> builder;
  
  public AmazonClientBuilderProxy(Class<? extends AwsClientBuilder<?, ?>> builderType) {
    Validate.notNull(builderType, "Internal error: builderType can't be null");
    try {
      this.builder = (AwsClientBuilder<?, ?>)MethodUtils.invokeExactStaticMethod(builderType, "standard");
    } catch (Exception e) {
      throw new AWSCommonsException("Internal Error - Error building AwsClientBuilder", e)
        .addContextValue("builderType", builderType.toString());
    } 
  }
  
  public AmazonClientBuilderProxy withRegion(Regions regions) {
    Validate.notNull(regions, "Internal error: regions can't be null");
    builder.withRegion(regions);
    return this;
  }

  public AmazonClientBuilderProxy withCredentials(AWSCredentialsProvider creds) {
    Validate.notNull(creds, "Internal error: creds can't be null");
    builder.withCredentials(creds);
    return this;
  }

  public Object build() {
    return builder.build();
  }

}
