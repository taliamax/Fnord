package slng.fnord;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteService extends AppCompatActivity {
    private Spinner deleteServicesSpinner;
    private Button deleteService;
    String serviceToDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_service);

        initializeSpinner();//creating our spinner aka the dropdown list
        deleteService = (Button) findViewById(R.id.deleteServiceBtn);
        deleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setting the text of the selected service in the list, then deleting it and the rate associated with it
                if(MainActivity.ser.getServices().size() != 0) {

                    serviceToDelete = deleteServicesSpinner.getSelectedItem().toString();
                    int indexOfService = MainActivity.ser.getServices().indexOf(serviceToDelete); //getting index of the service so we can delete the rate as well
                    MainActivity.ser.getServices().remove(indexOfService);//removing the service from the service arraylist
                    MainActivity.ser.getServiceRates().remove(indexOfService); //removing the service's rate from the servicerate arraylist
                    Toast toastDel = Toast.makeText(getApplicationContext(), "Service Deleted.", Toast.LENGTH_SHORT);
                    toastDel.show();
                }
                else{
                    Toast toastNoDel = Toast.makeText(getApplicationContext(), "No Services Selected.", Toast.LENGTH_SHORT);
                    toastNoDel.show();
                }
                initializeSpinner();
                //TODO make a nice toast saying its been successfully deleted
            }
        });

    }

    //helper function that creates/initializes the spinner aka the dropdown list
    private void initializeSpinner(){
        deleteServicesSpinner = (Spinner) findViewById(R.id.servicesSpinner);
        ArrayList<String> services = new ArrayList<>();
        services = MainActivity.ser.services;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, services);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deleteServicesSpinner.setAdapter(adapter);
    }



}
