package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor //final 이 붙은 애들의 생성자를 만들어줌
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final HttpSession session;


    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/board";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO) {
        User sessionUser = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/board";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        userRepository.save(joinDTO.toEntity());
        return "user/join-form";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }
}
