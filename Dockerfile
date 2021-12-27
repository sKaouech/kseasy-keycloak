FROM quay.io/keycloak/keycloak:12.0.1

ARG ACTION_EMAIL_JAR=action-email/target/keasy-action-email.jar
ARG EMAIL_TEMPLATE_JAR=email-template/target/keasy-freemarker.jar
ARG EVENT_LISTENER_JAR=event-listener/target/keasy-event-listener.jar

# copy the jars ...
COPY ${AUTHENTICATOR_JAR} /opt/jboss/keycloak/standalone/deployments/
COPY ${EMAIL_TEMPLATE_JAR} /opt/jboss/keycloak/standalone/deployments/
COPY ${EVENT_LISTENER_JAR} /opt/jboss/keycloak/standalone/deployments/

# theme customisation region
