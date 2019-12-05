package com.example.greenlightcasestudyapp.database.data_source;

import androidx.annotation.NonNull;

import com.example.greenlightcasestudyapp.database.IDataSourceCallback;
import com.example.greenlightcasestudyapp.database.dao.UserDao;
import com.example.greenlightcasestudyapp.models.User;
import com.example.greenlightcasestudyapp.utils.AppExecutors;

import java.util.ArrayList;
import java.util.List;

public class UserLocalDataSource extends UserDataSource {

    private static volatile UserLocalDataSource INSTANCE;

    private AppExecutors mAppExecutors;
    private UserDao userDao;


    private UserLocalDataSource(AppExecutors mAppExecutors, UserDao userDao) {
        this.mAppExecutors = mAppExecutors;
        this.userDao = userDao;
    }

    public static UserLocalDataSource getInstance(@NonNull AppExecutors appExecutors, @NonNull UserDao userDao) {
        if (INSTANCE == null) {
            synchronized (UserDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserLocalDataSource(appExecutors, userDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getUsers(@NonNull final IDataSourceCallback<ArrayList<User>> callback) {
        Runnable completeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    final ArrayList<User> userArrayList = (ArrayList<User>) userDao.getUsers();
                    mAppExecutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            if (userArrayList != null && userArrayList.size() > 0)
                                callback.onDataFound(userArrayList);
                            else
                                callback.onDataNotFound();

                        }
                    });
                } catch (final Exception ex) {
                    mAppExecutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            callback.onError(ex.getLocalizedMessage());
                        }
                    });
                }
            }
        };

        mAppExecutors.diskIO().execute(completeRunnable);
    }

    @Override
    public void insertUserData(@NonNull ArrayList<User> userArrayList, @NonNull IDataSourceCallback<String> callback) {
        Runnable completeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    userDao.insert((List<User>) userArrayList);
                    /*mAppExecutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            if (userArrayList != null && userArrayList.size() > 0)
                                callback.onDataFound(userArrayList);
                            else
                                callback.onDataNotFound();

                        }
                    });*/
                } catch (final Exception ex) {
                    /*mAppExecutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            callback.onError(ex.getLocalizedMessage());
                        }
                    });*/
                }
            }
        };

        mAppExecutors.diskIO().execute(completeRunnable);
    }

    @Override
    public void deleteUserData(@NonNull User user, @NonNull IDataSourceCallback<String> callback) {

        Runnable completeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    userDao.delete( user);
                    mAppExecutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            callback.onDataDelete();
                        }
                    });
                } catch (final Exception ex) {
                    mAppExecutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            callback.onError(ex.getLocalizedMessage());
                        }
                    });
                }
            }
        };

        mAppExecutors.diskIO().execute(completeRunnable);
    }

}
