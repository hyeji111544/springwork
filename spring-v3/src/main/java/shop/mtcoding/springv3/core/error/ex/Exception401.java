package shop.mtcoding.springv3.core.error.ex;

// 인증 관련
public class Exception401 extends RuntimeException {

    public Exception401(String message) {
        super(message);
    }
}
