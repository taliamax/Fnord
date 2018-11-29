package slng.fnord.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import slng.fnord.Database.DBHelper;
import slng.fnord.Database.DBObserver;
import slng.fnord.R;
import slng.fnord.Structures.Service;
import slng.fnord.Structures.Services;

public class MainActivity extends AppCompatActivity {

    private Button register;
    private Button signIn;
    public static Services services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        services = new Services();

        //setting register button on main screen to open to the register screen
        register = (Button) findViewById(R.id.registerChoiceButton);
        register.setOnClickListener(view -> openActivity(RegisterActivity.class));

        //setting sign in button on main screen to open to the sign in screen
        signIn = (Button) findViewById(R.id.signInChoiceButton);
        signIn.setOnClickListener(view -> openActivity(SignInActivity.class));

        Observable<DataSnapshot> observable = DBHelper.makeObservableFromPath("services");
        Observer<DataSnapshot> observer = new DBObserver<DataSnapshot>() {
            @Override
            public void onNext(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    HashMap<String, Service> dbServ = new HashMap<>();
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        String id = child.getKey();
                        Service val = child.getValue(Service.class);
                        dbServ.put(id, val);
                    }

                    services.setServices(dbServ);
                }
            }
        };

        observable.subscribe(observer);

    }

    public void openActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    public static Services getServices() {
        return services;
    }

}
