package nyc.c4q.android.ui;

public class RealAuthenticationManager implements AuthenticationManager {
  private static final String EMAIL = "c4q";
  private static final String PASSWORD = "c4q";
  public boolean validateLogin(String email, String password) {
    // TODO - implement authentication logic
    // valid credentials are email: "c4q", password: "c4q"
    return email.equals(EMAIL) && password.equals(PASSWORD);
  }
}
