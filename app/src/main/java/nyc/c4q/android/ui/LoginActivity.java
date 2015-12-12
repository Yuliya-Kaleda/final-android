package nyc.c4q.android.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import nyc.c4q.android.R;

public class LoginActivity extends Activity {

  private EditText emailField;
  private EditText passwordField;
  private Button loginButton;
  private final AuthenticationManager manager;

  public LoginActivity() {
    // TODO - fix this
    manager = new RealAuthenticationManager();
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    emailField = (EditText) findViewById(R.id.email);
    passwordField = (EditText) findViewById(R.id.password);
    loginButton = (Button) findViewById(R.id.login);
    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        checkCredentials(email, password);
      }
    });
  }

  private void checkCredentials(String email, String password) {
    if(manager.validateLogin(email, password)) {
      Intent intent = new Intent(this, EmailListActivity.class);
      startActivity(intent);
    }
    else {
      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setMessage(R.string.alert_dialog_title)
          .setPositiveButton(R.string.try_again, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
              dialog.dismiss();
            }
          });
      builder.create().show();
    }
  }
}
