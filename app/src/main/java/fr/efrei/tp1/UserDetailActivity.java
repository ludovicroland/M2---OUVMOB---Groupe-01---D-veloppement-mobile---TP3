package fr.efrei.tp1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.efrei.tp1.bo.User;

final public class UserDetailActivity
    extends AppCompatActivity
{

  public static final String USER_EXTRA = "userExtra";

  //The tag used into this screen for the logs
  public static final String TAG = UserDetailActivity.class.getSimpleName();

  private TextView name;

  private TextView phoneNumber;

  private TextView address;

  private TextView about;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    //We first set up the layout linked to the activity
    setContentView(R.layout.activity_user_detail);

    //Then we retrieved the widget we will need to manipulate into the
    name = findViewById(R.id.name);
    phoneNumber = findViewById(R.id.phoneNumber);
    address = findViewById(R.id.address);
    about = findViewById(R.id.about);

    //Then we retrieve the extra
    final User user = (User) getIntent().getSerializableExtra(UserDetailActivity.USER_EXTRA);

    //Then we bind the User and the UI
    name.setText(user.name);
    phoneNumber.setText(user.phoneNumber);
    address.setText(user.address);
    about.setText(user.about);

    //Then we update the title into the actionBar
    getSupportActionBar().setTitle(user.name);
  }

}