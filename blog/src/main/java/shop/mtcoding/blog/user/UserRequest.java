package shop.mtcoding.blog.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class UserRequest {

    @Data
    public static class JoinDTO {
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;

        @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식으로 작성해주세요")
        @NotEmpty
        private String email;


        //DTO 를 UserObject(=Entity) 로 변환
        public User toEntity() {
            return User.builder().username(username).password(password).email(email).build();
        }
    }

    @Data
    public static class LoginDTO {
        @Size(min = 2, max = 4)
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
    }
}
