package com.dealtobook.event;

import com.google.gson.Gson;
import org.jboss.logging.Logger;
import org.keycloak.authentication.actiontoken.verifyemail.VerifyEmailActionToken;
import org.keycloak.common.util.Time;
import org.keycloak.email.EmailException;
import org.keycloak.email.EmailTemplateProvider;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.admin.OperationType;
import org.keycloak.events.admin.ResourceType;
import org.keycloak.models.*;
import org.keycloak.services.resources.LoginActionsService;
import org.keycloak.sessions.AuthenticationSessionModel;
import org.keycloak.theme.Theme;
import org.keycloak.theme.beans.LinkExpirationFormatterMethod;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EmailRegisterEventListenerProvider implements EventListenerProvider {

    private static final Logger log = Logger.getLogger(EmailRegisterEventListenerProvider.class);

    private final KeycloakSession session;
    private final RealmProvider model;

    public EmailRegisterEventListenerProvider(KeycloakSession session) {
        this.session = session;
        this.model = session.realms();
    }

    @Override
    public void onEvent(Event event) {
        log.info("## NEW EVENT : " + event.getType().name());
        if (event.getType() != EventType.REGISTER) return;

        KeycloakContext context = session.getContext();
        AuthenticationSessionModel authSession = context.getAuthenticationSession();
        RealmModel realm = session.realms().getRealm(event.getRealmId());
        UserModel user = session.users().getUserById(event.getUserId(), realm);

        String locale = user.getAttributes().get("user").get(0);
        String firstName = user.getFirstName();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("locale", locale);
        attributes.put("firstName", firstName);

        try {
            int validityInSecs = realm.getActionTokenGeneratedByUserLifespan(VerifyEmailActionToken.TOKEN_TYPE);
            int absoluteExpirationInSecs = Time.currentTime() + validityInSecs;

            VerifyEmailActionToken token = new VerifyEmailActionToken(user.getId(), absoluteExpirationInSecs, null, user.getEmail(), "dealweb");

            UriBuilder builder = LoginActionsService.actionTokenProcessor(session.getContext().getUri());
            builder.queryParam("key", token.serialize(session, realm, session.getContext().getUri()));

            String link = builder.build(realm.getName()).toString();

            long expirationInMinutes = TimeUnit.SECONDS.toMinutes(validityInSecs);

            this.addLinkInfoIntoAttributes(session, locale, link, expirationInMinutes, attributes);
            session.getProvider(EmailTemplateProvider.class)
                    .setAuthenticationSession(authSession)
                    .setRealm(realm)
                    .setUser(user)
                    .send("activationClientAccountSubject", "activation-client-account.ftl", attributes);

        } catch (EmailException e) {
            log.error("Failed to send welcome mail", e);
        }

    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        log.debug("## NEW ADMIN EVENT");
        log.debug("-----------------------------------------------------------");
        log.debug("Resource path" + ": " + adminEvent.getResourcePath());
        log.debug("Resource type" + ": " + adminEvent.getResourceType());
        log.debug("Operation type" + ": " + adminEvent.getOperationType());

        if (ResourceType.USER.equals(adminEvent.getResourceType()) && OperationType.CREATE.equals(adminEvent.getOperationType())) {
            log.info("A new user has been created : " + adminEvent.getRepresentation());
            KeycloakContext context = session.getContext();
            AuthenticationSessionModel authSession = context.getAuthenticationSession();
            RealmModel realm = session.realms().getRealm(adminEvent.getRealmId());

            UserDto userDto = new Gson().fromJson(adminEvent.getRepresentation(), UserDto.class);
            String tierCategory = userDto.getAttributes().getTierCategory().get(0);
            String locale = userDto.getAttributes().getLocale().get(0);
            String title = userDto.getAttributes().getTitle().get(0);
            String firstName = userDto.getFirstName();
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("locale", locale);
            attributes.put("firstName", firstName);

            UserModel user = session.users().getUserByUsername(userDto.getEmail(), realm);
            log.info("user model first name" + user.getFirstName());

            try {
                int validityInSecs = realm.getActionTokenGeneratedByUserLifespan(VerifyEmailActionToken.TOKEN_TYPE);
                int absoluteExpirationInSecs = Time.currentTime() + validityInSecs;

                VerifyEmailActionToken token = new VerifyEmailActionToken(user.getId(), absoluteExpirationInSecs, null, user.getEmail(), "dealweb");

                UriBuilder builder = LoginActionsService.actionTokenProcessor(session.getContext().getUri());
                builder.queryParam("key", token.serialize(session, realm, session.getContext().getUri()));

                String link = builder.build(realm.getName()).toString();

                long expirationInMinutes = TimeUnit.SECONDS.toMinutes(validityInSecs);

                this.addLinkInfoIntoAttributes(session, locale, link, expirationInMinutes, attributes);
                session.getProvider(EmailTemplateProvider.class)
                        .setAuthenticationSession(authSession)
                        .setRealm(realm)
                        .setUser(user)
                        .send("activationClientAccountSubject", "activation-client-account.ftl", attributes);

            } catch (EmailException e) {
                log.error("Failed to send welcome mail", e);
            }
        }

        log.info("-----------------------------------------------------------");
    }

    @Override
    public void close() {
        // Nothing to close
    }

    protected void addLinkInfoIntoAttributes(KeycloakSession session, String loc, String link, long expirationInMinutes, Map<String, Object> attributes) throws EmailException {
        attributes.put("link", link);
        attributes.put("linkExpiration", expirationInMinutes);
        KeycloakUriInfo uriInfo = session.getContext().getUri();
        URI baseUri = uriInfo.getBaseUri();
        try {
            Locale locale = Locale.forLanguageTag(loc);
            attributes.put("linkExpirationFormatter", new LinkExpirationFormatterMethod(session.theme().getTheme(Theme.Type.EMAIL).getMessages(locale), locale));
//            attributes.put("url", new UrlBean(realm, session.theme().getTheme(Theme.Type.EMAIL), baseUri, null));
        } catch (IOException e) {
            throw new EmailException("Failed to template email", e);
        }
    }

}
