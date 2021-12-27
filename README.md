# custom-keycloak
**Sample repo extending keycloak**

   - New Required Action: [action-email](action-email/README.md)
   - New email template: [new-freemarker-template](email-template/README.md)
   - New event listener: [new-event-listener](event-listener/README.md)

**build project**

`mvn clean install`

**Copy jar file to deployments**

Copy the jar in the target folder to the `/opt/jboss/keycloak/standalone/deployments/` folder.
Or when using Docker mount the file `./kseasy-action-email.jar:/opt/jboss/keycloak/standalone/deployments/kseasy-action-email.jar`
