# Provider domain

An example of adding new Required Action

This will create the following new action

* activation client account

## Keycloak config

Copy the jar in the target folder to the `/opt/jboss/keycloak/standalone/deployments/` folder.
Or when using Docker mount the file `./kseasy-action-email.jar:/opt/jboss/keycloak/standalone/deployments/kseasy-action-email.jar`

## Declare required Action 

![](C:\work\keycloak\keycloak_required_action.JPG)

## Add required action to user

![](C:\work\keycloak\user_action.JPG)