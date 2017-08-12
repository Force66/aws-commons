package org.force66.aws;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

/**
 * Runtime Exception class for AWS Commons.
 * 
 * @author D. Ashmore
 *
 */
public class AWSCommonsException extends ContextedRuntimeException {

  private static final long serialVersionUID = 8780130226006408023L;

  @Override
  public AWSCommonsException addContextValue(String label, Object value) {
    return (AWSCommonsException) super.addContextValue(label, value);
  }

  public AWSCommonsException(String message, Throwable cause) {
    super(message, cause);
  }

  public AWSCommonsException(String message) {
    super(message);
  }

  public AWSCommonsException(Throwable cause) {
    super(cause);
  }

}
