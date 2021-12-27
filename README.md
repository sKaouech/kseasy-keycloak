# Extending custom keycloak

**Repo extending keycloak**

- New Required Action: [action-email](action-email/README.md)
- New email template: [new-freemarker-template](email-template/README.md)
- New event listener: [new-event-listener](event-listener/README.md)

**build project**

`mvn clean install`

**Copy jar file to deployments**

Copy the jar in the target folder to the `/opt/jboss/keycloak/standalone/deployments/` folder. Or when using Docker mount the
file `./kseasy-action-email.jar:/opt/jboss/keycloak/standalone/deployments/kseasy-action-email.jar`

## New Required Action

**Keycloak config**

Copy the jar in the target folder to the `/opt/jboss/keycloak/standalone/deployments/` folder. Or when using Docker mount the
file `./kseasy-action-email.jar:/opt/jboss/keycloak/standalone/deployments/kseasy-action-email.jar`

**Declare required Action**

![](C:\work\keycloak\keycloak_required_action.JPG)

**Add required action to user**

![](C:\work\keycloak\user_action.JPG)

## New email template

**Keycloak config**

Copy the jar in the target folder to the `/opt/jboss/keycloak/standalone/deployments/` folder. Or when using Docker mount the
file `./kseasy-freemarker.jar:/opt/jboss/keycloak/standalone/deployments/kseasy-freemarker.jar`

**ignore default template email provider**

![](C:\work\keycloak\email_template.JPG)

**verify the new template email**
in : `auth/admin/master/console/#/server-info/providers`
![](C:\work\keycloak\server info.JPG)

![](C:\work\keycloak\email freelmar.JPG)

## New event listener

**Keycloak config**

Copy the jar in the target folder to the `/opt/jboss/keycloak/standalone/deployments/` folder. Or when using Docker mount the
file `./kseasy-freemarker.jar:/opt/jboss/keycloak/standalone/deployments/kseasy-freemarker.jar`

**enable event listener**

in the event menu
![](C:\work\keycloak\event.JPG)