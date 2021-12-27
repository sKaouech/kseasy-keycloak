# Provider domain

An example of adding new Email Template Provider

This will create the following new Provider

* custom freemarker template

## Keycloak config

Copy the jar in the target folder to the `/opt/jboss/keycloak/standalone/deployments/` folder.
Or when using Docker mount the file `./kseasy-freemarker.jar:/opt/jboss/keycloak/standalone/deployments/kseasy-freemarker.jar`

## enable event listener

in the event menu
![](C:\work\keycloak\event.JPG)