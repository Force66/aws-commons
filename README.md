# aws-commons
AWS Common Utilities

This project has two objectives:
* Reduce common AWS plumbing code needed to create AWS clients.
* Make mocking AWS code easier so that it's much easier to unit test.

I commonly see AWS code that isn't injectable. That is, use of the AWS builders (e.g. `AmazonS3ClientBuilder`, 
`AWSSecurityTokenServiceClientBuilderProxy`, etc.) bind code so that it must run with access to AWS to exist.
This prevents unit testing (running without a network) and generally forces that code to be runnable as integration 
tests only.

Please feel free to report defects, questions, improvement suggestions. Details on our [contributing](CONTRIBUTING.md) page.

## AWS Commons Contents
* `AWSClientUtils`   Mockable utility that creates an ever-growing list of AWS clients for different services.