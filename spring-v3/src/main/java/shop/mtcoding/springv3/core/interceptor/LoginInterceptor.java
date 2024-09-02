package shop.mtcoding.springv3.core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import shop.mtcoding.springv3.core.error.ex.Exception401;
import shop.mtcoding.springv3.user.User;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        /*
        if (sessionUser == null) {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter bw = response.getWriter();
            bw.println("<script> alert('인증되지 않음');\n");
            bw.println("location.href = '/login-form';\n");
            return false;
        }
         */

        if (sessionUser == null) {
            
            throw new Exception401("인증되지 않았어요");
        }

        return true;//false 면 컨트롤러 진입안됨
    }
}
