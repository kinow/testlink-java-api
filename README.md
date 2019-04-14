TestLink Java API
=================

[![Build Status](https://travis-ci.org/kinow/testlink-java-api.svg?branch=master)](https://travis-ci.org/kinow/testlink-java-api)
[![Coverage Status](https://coveralls.io/repos/kinow/testlink-java-api/badge.svg?branch=master)](https://coveralls.io/r/kinow/testlink-java-api)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/br.eti.kinoshita/testlink-java-api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/br.eti.kinoshita/testlink-java-api/)
[![License](https://img.shields.io/github/license/mashape/apistatus.svg)](https://opensource.org/licenses/MIT)

Project homepage: http://kinow.github.io/testlink-java-api

TestLink Java API is a Java API that interfaces TestLink XML-RPC API. This API lets you call TestLink internal
methods such as createTestProject, uploadAttachment, getTestProjectByName among others.

## Building

The project is built with Maven:

    mvn

You can also use the Docker files included in this project repository to set up an environment
with the PHP TestLink project. Simply run:

    docker-compose up
    
That should create two containers (`web` and `db`). The MySQL database will be available on
`localhost:3306`, and the web application on `http://localhost:8000`.

## Versioning

The API version matches the version of TestLink it was developed for. So for TestLink 1.9.15, you should use
testlink-java-api-1.9.15-x. Where x is an internal sequential number, used to distinguish between project releases.

If you use the API version 1.9.15-0 against TestLink 1.9.16, there is no guarantee it will work.

## Support

This is an Open Source project. Pull requests are welcome! When reporting issues, please include as much
information as possible.

If you have an issue that you need for your organisation and you need it urgentely, please consider contacting the
author about sponsored work, or work with companies that contribute to Open Source software. Remember, this is an
Open Source project, maintained by volunteers on their spare time.
