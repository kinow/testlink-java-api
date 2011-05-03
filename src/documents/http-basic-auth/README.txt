On April the 20th Ryo Hang reported in Jenkins' JIRA an issue [1] regarding 
problems using Jenkins TestLink Plug-in and TestLink with HTTP Basic auth 
enabled. 

After some analysis, and debugging, Ryo found out that the issue was actually 
here in TestLink Java API. Ryo joined the team and his first contribution 
was branch REL-1.9.1-httpauthentication [2].

And it's not all, Ryo wrote test cases for this issue (TDD). :)

See ${basedir}/src/documents/http-basic-auth/sample.htaccess.

To create a passwd file, go to Apache installation folder and look for htpasswd 
executable. Then try executing it passing -b -c /tmp/passwd as arguments.

[1] https://issues.jenkins-ci.org/browse/JENKINS-9465

[2] https://testlinkjavaapi.svn.sourceforge.net/svnroot/testlinkjavaapi/branches/REL-1.9.1-httpauthentication

[3] http://httpd.apache.org/docs/1.3/howto/auth.html#basic


