#!/usr/bin/groovy

@Grab (group = 'io.ratpack', module = 'ratpack-groovy', version='1.1.1')
@Grab (group = 'org.slf4j', module = 'slf4j-simple', version='1.7.12')
@Grab (group = 'com.fasterxml.jackson.core', module = 'jackson-annotations', version = '2.6.2')

import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {
    get {
      render "Hello World!"
    }
    get(":name") {
      render "Hello $pathTokens.name!"
    }
  }
}
