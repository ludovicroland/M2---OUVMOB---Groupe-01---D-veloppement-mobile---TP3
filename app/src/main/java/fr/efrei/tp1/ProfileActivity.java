package fr.efrei.tp1;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import fr.efrei.tp1.preferences.AppPreferences;

final public class ProfileActivity
    extends AppCompatActivity
    implements OnClickListener
{

  //The tag used into this screen for the logs
  public static final String TAG = ProfileActivity.class.getSimpleName();

  private EditText loginEditText;


  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);

    //We retrieve the EditText References
    loginEditText = findViewById(R.id.login);

    restoreUserLogin();

    //We add the listener on the "save" button
    findViewById(R.id.save).setOnClickListener(this);
  }

  @Override
  public void onClick(View v)
  {
    saveLogin();
  }

  private void restoreUserLogin()
  {
    //We retrieve the name store into the shared preferences
    final String userLogin = AppPreferences.getUserLogin(this);

    //if the name is not null we restore it
    if (TextUtils.isEmpty(userLogin) == false)
    {
      loginEditText.setText(userLogin);
    }
  }

  private void saveLogin()
  {
    //We save only if there is something to save
    if (TextUtils.isEmpty(loginEditText.getText()) == false)
    {
      AppPreferences.saveUserLogin(this, loginEditText.getText().toString());
    }

    //we return to the previous screen
    onBackPressed();
  }

}