package com.taskone.taskone;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.taskone.taskone.adapters.UsersAdapter;
import com.taskone.taskone.model.GitHubUser;
import com.taskone.taskone.webapi.APIService;
import com.taskone.taskone.webapi.ApiUtils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private RecyclerView recyclerView;
    private Call<RealmList<GitHubUser>> usersCallback;
    private RealmList<GitHubUser> usersList;
    private UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersList = new RealmList<>();
        recyclerView = findViewById(R.id.recyclerview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new CustomDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new UsersAdapter(usersList);
        recyclerView.setAdapter(adapter);


        try {
            // opens "taskonerealm.realm"
            realm = Realm.getDefaultInstance();
            //fetching the data
            usersList.addAll(getUserDatafromRealam());
            if (!usersList.isEmpty()) {
                adapter.notifyDataSetChanged();
            } else {
                callGetUsersApi();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // close Realm when done.
        realm.close();
        if (usersCallback != null) {
            usersCallback.cancel();
        }
    }

    void callGetUsersApi() {
        APIService apiService = ApiUtils.getAPIService();
        usersCallback = apiService.getUsers();
        usersCallback.enqueue(new Callback<RealmList<GitHubUser>>() {
            @Override
            public void onResponse(Call<RealmList<GitHubUser>> call, Response<RealmList<GitHubUser>> response) {
                insertUsersInRealam(response.body());
            }
            @Override
            public void onFailure(Call<RealmList<GitHubUser>> call, Throwable t) {
                if(!call.isCanceled() && !isFinishing()){
                    Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void insertUsersInRealam(RealmList<GitHubUser> users) {
        realm.executeTransactionAsync(bgRealm -> bgRealm.insert(users), () -> {
            // Transaction was a success.
            List<GitHubUser> users1 = getUserDatafromRealam();
            setUsersData(users1);
        }, error -> {
            // Transaction failed and was automatically canceled.
            Toast.makeText(MainActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        });
    }


    void setUsersData(List<GitHubUser> users) {
        runOnUiThread(() -> {
            usersList.addAll(users);
            adapter.notifyDataSetChanged();
        });
    }

    List<GitHubUser> getUserDatafromRealam(){
        RealmResults<GitHubUser> results = realm.where(GitHubUser.class).findAllAsync();
        results.load();
        return (results.subList(0, results.size()));
    }


}
