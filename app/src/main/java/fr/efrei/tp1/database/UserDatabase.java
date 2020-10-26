package fr.efrei.tp1.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import fr.efrei.tp1.bo.User;
import fr.efrei.tp1.dao.UserDao;

@Database(entities = { User.class }, version = 1)
public abstract class UserDatabase
    extends RoomDatabase
{

  public abstract UserDao userDao();

}
