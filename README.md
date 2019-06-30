# Spring Boot Okta sample

This sample ha some requirements:

1. An Okta organization with admin permission
2. The following properties :
    
    |  Property                   |  Description                                |
    |-----------------------------|---------------------------------------------|
    | `okta.client.orgUrl`        | URL of the Okta domain to create new users  |
    | `okta.client.token`         | Authentication token for the Okta domain    |
    | `okta.oauth2.issuer`        | URL for OAuth2 authentication               |
    | `okta.oauth2.client-id`     | Client ID for OAuth2 authentication         |
    | `okta.oauth2.client-secret` | Client secret for OAuth2 authentication     |

    Please check the [documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html) on how to pass them