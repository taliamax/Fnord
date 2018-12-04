package slng.fnord.Activities.HomeOwner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import slng.fnord.Activities.Shared.SignInActivity;
import slng.fnord.R;
import slng.fnord.Structures.Ratings;

public class BookingReview extends AppCompatActivity {
    Button addService;
    String commentToAdd;
    String raterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobooking_review);

        final RatingBar ratingBar = (RatingBar) findViewById(R.id.BRRatingBar);

        TextView SPCompanyName = findViewById(R.id.BRCompanyNameTV);
        SPCompanyName.setText(BookingList.currentBooking.getServiceProviderInfo().getCompany());

        TextView ServiceRequested = findViewById(R.id.BRServiceRequestedTV);
        ServiceRequested.setText(BookingList.currentBooking.getService());

        TextView DayOfService = findViewById(R.id.BRDOSTV);
        DayOfService.setText(BookingList.currentBooking.getBookingDate().toString());

        TextView TimeOfDay = findViewById(R.id.BRTODTV);
        TimeOfDay.setText(Integer.toString(BookingList.currentBooking.getStartTime()));

        EditText ratingCommentBox = findViewById(R.id.BRCommentPanel);
        raterName = SignInActivity.currentUser.getEmail(); //this should be the username, not email

        addService = findViewById(R.id.BRUpdateReviewButton);
        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentToAdd = ratingCommentBox.getText().toString();
                Ratings ratingToAdd = new Ratings((int) ratingBar.getRating(), commentToAdd, raterName);
                String SPEmail = BookingList.currentBooking.getServiceProviderInfo().getEmail();

            }
        });

    }

    public void openView(final Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

}
