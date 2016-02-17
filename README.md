TestLink Java API
=================

Project homepage: http://testlinkjavaapi.sourceforge.net/

TestLink Java API is a Java API that interfaces TestLink XML-RPC API. This API lets you call TestLink internal methods such as createTestProject, uploadAttachment, getTestProjectByName among others.

Our aim with this project is always release one version of this API after a new version of TestLink has been released. So if, for instance, TestLink releases version 2.0, we will release testlink-java-api-2.0.

One fact to keep in mind is that TestLink Java API can't assure you Backward Compabitility among its versions. It occurs because TestLink XML-RPC API is constantly changing. Even some methods are renamed. So trying to keep Backward Compatibility would be really cumbersome.

Another important thing is that behind the scenes we are using xml-rpc to communicate with TestLink. However you won't see any xml or even a Map/HashMap when using TestLink Java API (ok, maybe in only one method :-).

If you call the method getTestPlanPlatforms it will return an array of Platform (an object in TestLink Java API). This way you won't need handle Maps :-) And in case of any error, the message will come to you in a nice TestLinkAPIException. This is the basic concepts to get the gist of the TestLink Java API.

