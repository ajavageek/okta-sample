package ch.frankel.blog.bootokta

import ch.frankel.blog.bootokta.Paths.ROOT
import ch.frankel.blog.bootokta.Paths.SIGN_UP
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect
import org.thymeleaf.spring5.SpringTemplateEngine

@SpringBootApplication
class OktaSampleApplication

fun main(args: Array<String>) {
    runApplication<OktaSampleApplication> {
        addInitializers(beans())
    }
}

private fun beans() = beans {
    bean<SecurityConfig>()
    bean {
        SpringTemplateEngine().apply {
            setTemplateResolver(ref())
            addDialect(ref<SpringSecurityDialect>())
        }
    }
}

private class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers(ROOT, SIGN_UP, "/sign_up", "/webjars/**").permitAll()
            .anyRequest().authenticated()
            .and().oauth2Client()
            .and().oauth2Login()
            .and().logout().logoutSuccessUrl(ROOT)
    }
}