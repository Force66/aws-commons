package org.force66.aws;

import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;

/**
 * Internal proxy for an AWSSecurityTokenServiceClientBuilder so that it can be mocked.
 * 
 * @author D. Ashmore
 *
 */
class AWSSecurityTokenServiceClientBuilderProxy {

  private AWSSecurityTokenServiceClientBuilder builder =
      AWSSecurityTokenServiceClientBuilder.standard();

  public AWSSecurityTokenService build() {
    return builder.build();
  }

}
