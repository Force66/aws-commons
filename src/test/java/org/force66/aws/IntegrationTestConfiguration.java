package org.force66.aws;

import java.io.IOException;
import java.util.Properties;

public class IntegrationTestConfiguration {
  
  public static final String TEST_ROLE_PROP = "test-role";
  
  private static final Properties TEST_PROPS = new Properties();
  static {
    try {
      TEST_PROPS.load(
          IntegrationTestConfiguration.class
          .getClassLoader()
          .getResourceAsStream("integration-test.properties"));
    } catch (IOException e) {
      throw new AWSCommonsException(e);
    }
  }
  
  /**
   * Returns property value cast as a String.
   * @param propertyName
   * @return stringValue
   */
  public static String getPropertyAsString(String propertyName) {
    return (String)TEST_PROPS.getProperty(propertyName);
  }
  
  /**
   * Returns property value - no casting
   * @param propertyName
   * @return value
   */
  public static Object getProperty(String propertyName) {
    return TEST_PROPS.getProperty(propertyName);
  }

}
