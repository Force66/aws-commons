package org.force66.aws;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.apigateway.AmazonApiGatewayClientBuilder;
import com.amazonaws.services.applicationautoscaling.AWSApplicationAutoScalingClientBuilder;
import com.amazonaws.services.applicationdiscovery.AWSApplicationDiscoveryClientBuilder;
import com.amazonaws.services.appstream.AmazonAppStreamClientBuilder;
import com.amazonaws.services.athena.AmazonAthenaClientBuilder;
import com.amazonaws.services.autoscaling.AmazonAutoScalingClientBuilder;
import com.amazonaws.services.batch.AWSBatchClientBuilder;
import com.amazonaws.services.budgets.AWSBudgetsClientBuilder;
import com.amazonaws.services.certificatemanager.AWSCertificateManagerClientBuilder;
import com.amazonaws.services.clouddirectory.AmazonCloudDirectoryClientBuilder;
import com.amazonaws.services.cloudformation.AmazonCloudFormationClientBuilder;
import com.amazonaws.services.cloudfront.AmazonCloudFrontClientBuilder;
import com.amazonaws.services.cloudhsm.AWSCloudHSMClientBuilder;
import com.amazonaws.services.cloudsearchdomain.AmazonCloudSearchDomainClientBuilder;
import com.amazonaws.services.cloudsearchv2.AmazonCloudSearchClientBuilder;
import com.amazonaws.services.cloudtrail.AWSCloudTrailClientBuilder;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEventsClientBuilder;
import com.amazonaws.services.codebuild.AWSCodeBuildClientBuilder;
import com.amazonaws.services.codecommit.AWSCodeCommitClientBuilder;
import com.amazonaws.services.codedeploy.AmazonCodeDeployClientBuilder;
import com.amazonaws.services.codepipeline.AWSCodePipelineClientBuilder;
import com.amazonaws.services.codestar.AWSCodeStarClientBuilder;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClientBuilder;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitosync.AmazonCognitoSyncClientBuilder;
import com.amazonaws.services.config.AmazonConfigClientBuilder;
import com.amazonaws.services.costandusagereport.AWSCostAndUsageReportClientBuilder;
import com.amazonaws.services.databasemigrationservice.AWSDatabaseMigrationServiceClientBuilder;
import com.amazonaws.services.datapipeline.DataPipelineClientBuilder;
import com.amazonaws.services.dax.AmazonDaxClientBuilder;
import com.amazonaws.services.devicefarm.AWSDeviceFarmClientBuilder;
import com.amazonaws.services.directconnect.AmazonDirectConnectClientBuilder;
import com.amazonaws.services.directory.AWSDirectoryServiceClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreamsClientBuilder;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ecr.AmazonECRClientBuilder;
import com.amazonaws.services.ecs.AmazonECSClientBuilder;
import com.amazonaws.services.elasticache.AmazonElastiCacheClientBuilder;
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalkClientBuilder;
import com.amazonaws.services.elasticfilesystem.AmazonElasticFileSystemClientBuilder;
import com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancingClientBuilder;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduceClientBuilder;
import com.amazonaws.services.elasticsearch.AWSElasticsearchClientBuilder;
import com.amazonaws.services.elastictranscoder.AmazonElasticTranscoderClientBuilder;
import com.amazonaws.services.gamelift.AmazonGameLiftClientBuilder;
import com.amazonaws.services.glacier.AmazonGlacierClientBuilder;
import com.amazonaws.services.greengrass.AWSGreengrassClientBuilder;
import com.amazonaws.services.health.AWSHealthClientBuilder;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.importexport.AmazonImportExportClientBuilder;
import com.amazonaws.services.inspector.AmazonInspectorClientBuilder;
import com.amazonaws.services.iot.AWSIotClientBuilder;
import com.amazonaws.services.iotdata.AWSIotDataClientBuilder;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesisanalytics.AmazonKinesisAnalyticsClientBuilder;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClientBuilder;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lexmodelbuilding.AmazonLexModelBuildingClientBuilder;
import com.amazonaws.services.lexruntime.AmazonLexRuntimeClientBuilder;
import com.amazonaws.services.lightsail.AmazonLightsailClientBuilder;
import com.amazonaws.services.logs.AWSLogsClientBuilder;
import com.amazonaws.services.machinelearning.AmazonMachineLearningClientBuilder;
import com.amazonaws.services.marketplacecommerceanalytics.AWSMarketplaceCommerceAnalyticsClientBuilder;
import com.amazonaws.services.marketplaceentitlement.AWSMarketplaceEntitlementClientBuilder;
import com.amazonaws.services.marketplacemetering.AWSMarketplaceMeteringClientBuilder;
import com.amazonaws.services.mturk.AmazonMTurkClientBuilder;
import com.amazonaws.services.opsworks.AWSOpsWorksClientBuilder;
import com.amazonaws.services.opsworkscm.AWSOpsWorksCMClientBuilder;
import com.amazonaws.services.organizations.AWSOrganizationsClientBuilder;
import com.amazonaws.services.pinpoint.AmazonPinpointClientBuilder;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.rds.AmazonRDSClientBuilder;
import com.amazonaws.services.redshift.AmazonRedshiftClientBuilder;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.resourcegroupstaggingapi.AWSResourceGroupsTaggingAPIClientBuilder;
import com.amazonaws.services.route53.AmazonRoute53ClientBuilder;
import com.amazonaws.services.route53domains.AmazonRoute53DomainsClientBuilder;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.servermigration.AWSServerMigrationClientBuilder;
import com.amazonaws.services.servicecatalog.AWSServiceCatalogClientBuilder;
import com.amazonaws.services.shield.AWSShieldClientBuilder;
import com.amazonaws.services.simpledb.AmazonSimpleDBClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClientBuilder;
import com.amazonaws.services.snowball.AmazonSnowballClientBuilder;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;
import com.amazonaws.services.storagegateway.AWSStorageGatewayClientBuilder;
import com.amazonaws.services.support.AWSSupportClientBuilder;
import com.amazonaws.services.waf.AWSWAFClientBuilder;
import com.amazonaws.services.waf.AWSWAFRegionalClientBuilder;
import com.amazonaws.services.workdocs.AmazonWorkDocsClientBuilder;
import com.amazonaws.services.workspaces.AmazonWorkspacesClientBuilder;
import com.amazonaws.services.xray.AWSXRayClientBuilder;

public class AmazonClientBuilderProxyTestIntegration {
  
  private static final Class[] BUILDER_ARRAY = {
    AmazonApiGatewayClientBuilder.class, 
    AmazonAppStreamClientBuilder.class, 
    AmazonAthenaClientBuilder.class, 
    AmazonAutoScalingClientBuilder.class, 
    AmazonCloudDirectoryClientBuilder.class, 
    AmazonCloudFormationClientBuilder.class, 
    AmazonCloudFrontClientBuilder.class, 
    AmazonCloudSearchClientBuilder.class, 
    AmazonCloudSearchDomainClientBuilder.class, 
    AmazonCloudWatchClientBuilder.class, 
    AmazonCloudWatchEventsClientBuilder.class, 
    AmazonCodeDeployClientBuilder.class, 
    AmazonCognitoIdentityClientBuilder.class, 
    AmazonCognitoSyncClientBuilder.class, 
    AmazonConfigClientBuilder.class, 
    AmazonDaxClientBuilder.class, 
    AmazonDirectConnectClientBuilder.class, 
    AmazonDynamoDBClientBuilder.class, 
    AmazonDynamoDBStreamsClientBuilder.class, 
    AmazonEC2ClientBuilder.class, 
    AmazonECRClientBuilder.class, 
    AmazonECSClientBuilder.class, 
    AmazonElastiCacheClientBuilder.class, 
    AmazonElasticFileSystemClientBuilder.class, 
    AmazonElasticLoadBalancingClientBuilder.class, 
    AmazonElasticLoadBalancingClientBuilder.class, 
    AmazonElasticMapReduceClientBuilder.class, 
    AmazonElasticTranscoderClientBuilder.class, 
    AmazonGameLiftClientBuilder.class, 
    AmazonGlacierClientBuilder.class, 
    AmazonIdentityManagementClientBuilder.class, 
    AmazonImportExportClientBuilder.class, 
    AmazonInspectorClientBuilder.class, 
    AmazonKinesisAnalyticsClientBuilder.class, 
    AmazonKinesisClientBuilder.class, 
    AmazonKinesisFirehoseClientBuilder.class, 
    AmazonLexModelBuildingClientBuilder.class, 
    AmazonLexRuntimeClientBuilder.class, 
    AmazonLightsailClientBuilder.class, 
    AmazonMachineLearningClientBuilder.class, 
    AmazonMTurkClientBuilder.class, 
    AmazonPinpointClientBuilder.class, 
    AmazonPollyClientBuilder.class, 
    AmazonRDSClientBuilder.class, 
    AmazonRedshiftClientBuilder.class, 
    AmazonRekognitionClientBuilder.class, 
    AmazonRoute53ClientBuilder.class, 
    AmazonRoute53DomainsClientBuilder.class, 
    AmazonSimpleDBClientBuilder.class, 
    AmazonSimpleEmailServiceClientBuilder.class, 
    AmazonSimpleWorkflowClientBuilder.class, 
    AmazonSnowballClientBuilder.class, 
    AmazonSNSClientBuilder.class, 
    AmazonSQSClientBuilder.class, 
    AmazonWorkDocsClientBuilder.class, 
    AmazonWorkspacesClientBuilder.class, 
    AWSApplicationAutoScalingClientBuilder.class, 
    AWSApplicationDiscoveryClientBuilder.class, 
    AWSBatchClientBuilder.class, 
    AWSBudgetsClientBuilder.class, 
    AWSCertificateManagerClientBuilder.class, 
    AWSCloudHSMClientBuilder.class, 
    AWSCloudTrailClientBuilder.class, 
    AWSCodeBuildClientBuilder.class, 
    AWSCodeCommitClientBuilder.class, 
    AWSCodePipelineClientBuilder.class, 
    AWSCodeStarClientBuilder.class, 
    AWSCognitoIdentityProviderClientBuilder.class, 
    AWSCostAndUsageReportClientBuilder.class, 
    AWSDatabaseMigrationServiceClientBuilder.class, 
    AWSDeviceFarmClientBuilder.class, 
    AWSDirectoryServiceClientBuilder.class, 
    AWSElasticBeanstalkClientBuilder.class, 
    AWSElasticsearchClientBuilder.class, 
    AWSGreengrassClientBuilder.class, 
    AWSHealthClientBuilder.class, 
    AWSIotClientBuilder.class, 
    AWSIotDataClientBuilder.class, 
    AWSKMSClientBuilder.class, 
    AWSLambdaClientBuilder.class, 
    AWSLogsClientBuilder.class, 
    AWSMarketplaceCommerceAnalyticsClientBuilder.class, 
    AWSMarketplaceEntitlementClientBuilder.class, 
    AWSMarketplaceMeteringClientBuilder.class, 
    AWSOpsWorksClientBuilder.class, 
    AWSOpsWorksCMClientBuilder.class, 
    AWSOrganizationsClientBuilder.class, 
    AWSResourceGroupsTaggingAPIClientBuilder.class, 
    AWSSecurityTokenServiceClientBuilder.class, 
    AWSServerMigrationClientBuilder.class, 
    AWSServiceCatalogClientBuilder.class, 
    AWSShieldClientBuilder.class, 
    AWSSimpleSystemsManagementClientBuilder.class, 
    AWSStepFunctionsClientBuilder.class, 
    AWSStorageGatewayClientBuilder.class, 
    AWSSupportClientBuilder.class, 
    AWSWAFClientBuilder.class, 
    AWSWAFRegionalClientBuilder.class, 
    AWSXRayClientBuilder.class, 
    DataPipelineClientBuilder.class
  };
  
  private static Regions regions;

  @BeforeClass
  public static void setUp() throws Exception {
    regions = Regions.fromName("us-east-1");   
  }

  @Test
  public void testBuilders() {
    Arrays.stream(BUILDER_ARRAY).forEach(x -> testBuilder(x));;
  }
  
  private void testBuilder(Class<? extends AwsClientBuilder<?, ?>> builderType) {
    AmazonClientBuilderProxy proxy = new AmazonClientBuilderProxy(builderType);
    proxy.withRegion(regions);
  }

}
