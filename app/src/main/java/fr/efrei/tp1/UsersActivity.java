package fr.efrei.tp1;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import fr.efrei.tp1.adapter.UsersAdapter;
import fr.efrei.tp1.bo.User;
import fr.efrei.tp1.repository.UserRepository;

final public class UsersActivity
    extends AppCompatActivity
    implements OnClickListener
{

  private enum OrderState
  {
    Order, NotOrder
  }

  //The tag used into this screen for the logs
  public static final String TAG = UsersActivity.class.getSimpleName();

  private RecyclerView recyclerView;

  private OrderState currentOrderState = OrderState.NotOrder;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    //We first set up the layout linked to the activity
    setContentView(R.layout.activity_users);

    recyclerView = findViewById(R.id.recyclerView);
    recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    //We configure the click on the fab
    findViewById(R.id.fab).setOnClickListener(this);
  }

  @Override
  protected void onResume()
  {
    super.onResume();

    //We init the list into the onResume method
    //so the list is updated each time the screen goes to foreground
    initList();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    //We add the "menu_order" to the current activity
    getMenuInflater().inflate(R.menu.menu_order, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item)
  {
    //We handle the click on a menu item
    if (item.getItemId() == R.id.order)
    {
      currentOrderState = OrderState.Order;
    }
    else if (item.getItemId() == R.id.noOrder)
    {
      currentOrderState = OrderState.NotOrder;
    }
    else if(item.getItemId() == R.id.profile)
    {
      final Intent intent = new Intent(this, ProfileActivity.class);
      startActivity(intent);
    }

    //We refresh the list with the new order
    initList();

    return super.onOptionsItemSelected(item);
  }

  private void initList()
  {
    //We retrieve the list of users to display
    final List<User> users = currentOrderState == OrderState.NotOrder ? UserRepository.getInstance(this).getUsers() : UserRepository.getInstance(this).sortUsersByName();

    //We create the adapter and we attach it to the RecyclerView
    final UsersAdapter usersAdapter = new UsersAdapter(users);
    recyclerView.setAdapter(usersAdapter);
  }

  @Override
  public void onClick(View v)
  {
    //We open the AddUserActivity screen when the user clicks on the FAB
    final Intent intent = new Intent(this, AddUserActivity.class);
    startActivity(intent);
  }

}