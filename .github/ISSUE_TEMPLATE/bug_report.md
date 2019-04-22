---
name: Bug report
about: Include as much information as possible, and make sure you have searched the
  previous issues for similar problems
title: ''
labels: bug
assignees: ''

---

**Describe the bug**
A clear and concise description of what the bug is. **Check what is the current version of the API. The older version is not maintained (except via patronage/sponsoring).**

**To Reproduce**
Steps to reproduce the behavior:
1. Go to '...'
2. Click on '....'
3. Scroll down to '....'
4. See error

**Expected behavior**
A clear and concise description of what you expected to happen.

**Screenshots**
If applicable, add screenshots to help explain your problem.

**Versions:**
 - TestLink: [e.g. 1.9.19]
 - testlink-java-api: [e.g. 1.9.19-0]
 - JVM [e.g. 1.8]

**Additional context**
Add any other context about the problem here.

**If you have a problem such as "Failed to parse server's response: Expected methodResponse element, got br ", make sure to check the server response, as TestLink may hide exceptions.**

The following command starts a port forwarder that can be used to inspect responses: `socat -v tcp-l:9999,fork,reuseaddr tcp:127.0.0.1:8000`. Then just use the same URL with port `9999`. The output will be recorded in the terminal.
