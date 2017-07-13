
STORYBOARD
==========

Readable testing
Domain driven development framework

Clean up with steps. Ie: given a clean environment -> init vars
For concurrent feature running, instance fields used in steps should be ThreadLocal

Gherkin differences
* Instead examples table, parameters are used (examples are the list of parameters's columns)
* Background has parameters
* No difference between scenarios with or without parameters (no Scenario Outline) in the code.
* No multiline arguments yet

Motivation: No business people writes specs, if you have to do it, do it with code

Design: separation of model and runner to be able to use other testing frameworks. Check API

## BUILD
  The build is handled by [Gradle], to execute it use the `gradle/wrapper` command
  Default target with `gw` executes `build` goal.

## DISTRIBUTION
  Clean files before assembling the bundle. Commands:

    gw clean assemble

## DEPLOY
  Deployment will include sources javadoc and distribution bundle. Before deploying you should
  disable Eclipse's 'Build Automatically' feature. Command:

    gw -P bundle clean deploy

## RELEASE
  Prior to release generation, make sure to disable Eclipse's 'Build Automatically' option in
  order to avoid overwriting release generated files. The commands to generate a release are:

  Check release: gw -P bundle clean install release:clean release:prepare -DdryRun
  In case of error: gw release:rollback
  Perform the release: gw -P bundle release:clean release:prepare release:perform

## CONFIG IDE
    gw eclipse:configure-workspace
    gw eclipse:eclipse
    gw idea:idea
    Code styles in 'src/main/config/eclipse-formatter.xml'
    Clean up actions are in 'src/main/config/eclipse-cleanup.xml'

## TESTING
    gw test

## RUNNING
    Copy win32com.dll into 'lib/ext' or set the following property:
        -Djava.library.path=src/main/config
    For the stamper to work you should also add:
        -Dcom.sun.management.jmxremote=true
        -Dcom.sun.management.jmxremote.port=9999
        -Dcom.sun.management.jmxremote.authenticate=false
        -Dcom.sun.management.jmxremote.ssl=false
        -Djava.rmi.server.hostname=localhost
        -Djavax.net.ssl.trustStore=src/main/config/profiles/std-sas/mpcc-dev6.jks
        -Djavax.net.ssl.trustStorePassword=password
    TODO Maybe these properties can be set on application start (not required int the command line)

## DOCUMENTATION
    gw site
    gw javadoc:javadoc

## DIRECTORY STRUCTURE
    standard Maven layout
    data and configuration not used as resources are in 'src/main/config'

## INSTALL
    Into local repo: gw install
    The instructions to use app are in bundle's 'readme.txt' file

## LOG STRUCTURE
    Rotated daily, date not included in the messages. Different format for
    development and for bundle.

## CONTRIBUTING
    Use formatter (use 'eclipse-formatter.xml')
    Do cleanup before commiting (use 'eclipse-cleanup.xml')

## PROJECT LINKS
    Start here:

    http://

## DEPENDENCY MANAGEMENT
    gw dependency:help

## TODO
    Pending items

See 'pom.xml' and 'src/main/config/maven.xml' for more details
