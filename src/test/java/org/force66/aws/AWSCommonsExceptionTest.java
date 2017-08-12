package org.force66.aws;

import org.junit.Assert;
import org.junit.Test;

public class AWSCommonsExceptionTest {

  private static final String TEST_ERROR_MESSAGE = "crap";

  @Test
  public void testStringConstructor() throws Exception {
    AWSCommonsException e = new AWSCommonsException(TEST_ERROR_MESSAGE);
    Assert.assertTrue(e.getMessage().contains(TEST_ERROR_MESSAGE));
  }

  @Test
  public void testExceptionConstructor() throws Exception {
    RuntimeException testException = new RuntimeException("different message");
    AWSCommonsException e = new AWSCommonsException(testException);
    Assert.assertEquals(testException, e.getCause());
  }

  @Test
  public void testStringExceptionConstructor() throws Exception {
    RuntimeException testException = new RuntimeException("different message");
    AWSCommonsException e = new AWSCommonsException(TEST_ERROR_MESSAGE, testException);
    Assert.assertEquals(testException, e.getCause());
    Assert.assertTrue(e.getMessage().contains(TEST_ERROR_MESSAGE));
  }

  @Test
  public void testAddContextValue() throws Exception {
    AWSCommonsException e1 = new AWSCommonsException(TEST_ERROR_MESSAGE);
    AWSCommonsException e2 = e1.addContextValue("fu", "bar");

    Assert.assertEquals(e1, e2);
    Assert.assertEquals(1, e1.getContextEntries().size());

  }

}
