package org.force66.aws;

import com.amazonaws.client.builder.AwsClientBuilder;

/**
 * Factory that produces AWS S3 client instances
 * 
 * @author D. Ashmore
 *
 */
class AWSBuilderFactory {

  public AmazonClientBuilderProxy createAmazonClientBuilderProxy(Class<? extends AwsClientBuilder<?, ?>> builderType) {
    return new AmazonClientBuilderProxy(builderType);
  }

}
