# Provider domain

An example of adding new Email Template Provider

This will create the following new Provider

* custom freemarker template

## Keycloak config

Copy the jar in the target folder to the `/opt/jboss/keycloak/standalone/deployments/` folder.
Or when using Docker mount the file `./kseasy-freemarker.jar:/opt/jboss/keycloak/standalone/deployments/kseasy-freemarker.jar`

## enable new template email

![](C:\work\keycloak\email_template.JPG)

**verify the new template email**
in : `auth/admin/master/console/#/server-info/providers`
![](C:\work\keycloak\server info.JPG)

![](C:\work\keycloak\email freelmar.JPG)