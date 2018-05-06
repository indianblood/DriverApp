package sati.shubham.driverapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity{
    private FirebaseDatabase database;
    static int rideCounter;
    Button moveAmbulance;
    TextView latitudeBox,longitudeBox;
    double latitude[]={28.457518,28.461025,28.470653,28.478540,28.477887,28.478487,28.479559,28.479943,28.483338,28.485947,28.488676};
    double longitude[]={77.074091, 77.074144,77.072724, 77.074291,77.070317, 77.070088, 77.070088, 77.070316, 77.073567, 77.076011, 77.078646};

    private void endRide()
    {
        final DatabaseReference bookingRef = database.getReference("bookings");
        final DatabaseReference driverRef = database.getReference("drivers").child("driver1");
        driverRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Driver value = dataSnapshot.getValue(Driver.class);
                if(value!=null) {

                    String bookingID = value.getCurrentBookingID();
                    if (bookingID!=null) {

                        Log.d("Error!",bookingID);
                        bookingRef.child(bookingID).child("isActive").setValue("false");
                        //Log.d(TAG, "Value is: " + value);
                        driverRef.child("currentBookingID").setValue("NA");
                        driverRef.child("isAvailable").setValue("Yes");

                        database.getReference("drivers" ).
                                child("driver1")
                                .child("latitude").setValue(latitude[10]);
                        database.getReference("drivers" )
                                .child("driver1")
                                .child("longitude").setValue(longitude[10]);
                    }
                    else
                    {
                        Log.d("BITCH LASAGNA","NULL BOOKINGID");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rideCounter=10;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moveAmbulance=(Button)findViewById(R.id.moveAmbulance);
        latitudeBox=findViewById(R.id.latitudeBox);
        longitudeBox=findViewById(R.id.longitudeBox);
        database = FirebaseDatabase.getInstance();
        final DatabaseReference myRefLat = database.getReference("drivers" ).
                child("driver1")
                .child("latitude");
        final DatabaseReference myRefLong = database.getReference("drivers" )
                .child("driver1")
                .child("longitude");
        myRefLat.setValue(latitude[10]);
        myRefLong.setValue(longitude[10]);
        moveAmbulance.setOnClickListener( new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                if(rideCounter>=0){
                    longitudeBox.setText(Double.toString(longitude[rideCounter]));
                    latitudeBox.setText(Double.toString(latitude[rideCounter]));
                    /*RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    String url ="http://13.127.65.50:8080/update?id=1&latitude="+latitude[rideCounter]+"&longitude="+longitude[rideCounter];
                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string.
                                    //mTextView.setText("Response is: "+ response.substring(0,500));
                                    Toast.makeText(getApplicationContext(), "Values Updated",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // mTextView.setText("That didn't work!");
                        }
                    });

                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);*/
                    myRefLat.setValue(latitude[rideCounter]);
                    myRefLong.setValue(longitude[rideCounter]);
                    rideCounter-=1;
                }else{
                    Toast.makeText(getApplicationContext(), "Ride Already Ended",
                            Toast.LENGTH_SHORT).show();
                    endRide();
                }

            }
        });
    }


}
