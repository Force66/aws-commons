# Contributing
Contributions of all types are welcome!  

> Please file an [issue](https://github.com/Force66/aws-commons/issues) for all questions, defects, improvement requests.
> Any pull requests should include a reference to an issue

## Developer Conventions
This project follows the Google styleguide for Java. Instructions on how to easily update your IDE to use this style is 
[here](https://github.com/HPI-Information-Systems/Metanome/wiki/Installing-the-google-styleguide-settings-in-intellij-and-eclipse).

### Integration Tests
* Integration tests are JUnit tests that have the suffix `TestIntegration`. For example `AWSClientUtilsTestIntegration`.
* AWS Credentials must be configured per AWS [instructions](http://docs.aws.amazon.com/cli/latest/userguide/cli-config-files.html).
* AWS environment specifics needed for integration tests are found in `src/test/resources/integration-test.properties`.

## Contribution Ideas
* Enhance `AWSClientUtils` to handle increasing number of AWS clients.
* Base Lambda implementation providing better exception handling/reporting