package org.force66.aws;

import org.apache.commons.lang3.Validate;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * Collection of one-line utilities to get configured AWS clients
 * @author D. Ashmore
 *
 */
public class AWSClientUtils {
	
	/**
	 * Builds the default AmazonS3 client based on your current preference and account settings
	 * @return AmazonS3 S3Client
	 */
	public static AmazonS3 buildAmazonS3() {
		return AmazonS3ClientBuilder.defaultClient();
	}
	
	/**
	 * Builds a standard S3 client specifying the region provided.
	 * @param regionName
	 * @return AmazonS3 S3Client
	 */
	public static AmazonS3 buildAmazonS3(String regionName) {
		Regions regions = validateRegionName(regionName);
		
		return AmazonS3ClientBuilder.standard()
				.withRegion(regions)
				.build();
	}

	protected static Regions validateRegionName(String regionName) {
		Validate.notEmpty(regionName, "Null or blank region name not allowed.");
		Regions regions = Regions.fromName(regionName);
		Validate.notNull(regions, "%s is not a valid region name", regionName);
		return regions;
	}

}
