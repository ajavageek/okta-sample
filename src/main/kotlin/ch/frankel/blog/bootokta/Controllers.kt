package ch.frankel.blog.bootokta

import ch.frankel.blog.bootokta.Paths.ROOT
import ch.frankel.blog.bootokta.Paths.SIGN_UP
import com.okta.sdk.client.Client
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

internal object Paths {
    const val ROOT = "/"
    const val SIGN_UP = "/signup"
}

private const val USER_ATTR_NAME = "user"

@Controller
class HomeController {

    @GetMapping(ROOT)
    fun home(attrs: RedirectAttributes, model: Model) = "home".apply {
        attrs.getAttribute(USER_ATTR_NAME)?.let {
            model.addAttribute(USER_ATTR_NAME, it)
        }
    }
}

@Controller
class SignupController(private val client: Client) {

    @GetMapping(SIGN_UP)
    fun displayCreateUser() = "signup"

    @PostMapping(SIGN_UP)
    fun createUser(user: NewUser, binding: BindingResult, attrs: RedirectAttributes) = "redirect:$ROOT".apply {
        client.create(user)
        attrs.addFlashAttribute(USER_ATTR_NAME, user.email)
    }
}