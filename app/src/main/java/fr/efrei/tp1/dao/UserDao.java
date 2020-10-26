package fr.efrei.tp1.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import fr.efrei.tp1.bo.User;
import fr.efrei.tp1.service.IUserService;

@Dao
public interface UserDao
  extends IUserService
{

  @Override
  @Query("SELECT * FROM User")
  List<User> getUsers();

  @Override
  @Delete
  void deleteUser(User user);

  @Override
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void addUser(User user);

  @Override
  @Query("SELECT * FROM User ORDER BY name DESC")
  List<User> sortUsersByName();

}
