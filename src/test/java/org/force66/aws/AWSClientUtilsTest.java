package org.force66.aws;

import org.junit.Test;

public class AWSClientUtilsTest {

	@Test(expected = NullPointerException.class)
	public void testValidateNullRegionName() throws Exception {
		AWSClientUtils.validateRegionName(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateInvalidRegionName() throws Exception {
		AWSClientUtils.validateRegionName("does-not-exist");
	}

}
