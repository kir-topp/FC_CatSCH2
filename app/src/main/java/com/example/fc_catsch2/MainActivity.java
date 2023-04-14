package com.example.fc_catsch2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView coordinatesOut;
    private float x;
    private float y;
    private String sDown;
    private String sMove;
    private String sUp;

    private final float xCat = 500;
    private final float yCat = 500;
    private final float deltaCat = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatesOut = findViewById(R.id.coordinatesOut);

        coordinatesOut.setOnTouchListener(listener);
    }

    private View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            x = motionEvent.getX();
            y = motionEvent.getY();

            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    sDown = "Нажатие: координата X = " + x + ", координата y = " + y;
                    sMove = "";
                    sUp = "";
                    break;
                case MotionEvent.ACTION_MOVE:
                    sMove = "Движение: координата X = " + x + ", координата y = " + y;

                    if (x < (xCat + deltaCat) && x > (xCat - deltaCat) && y < (yCat + deltaCat) && y > (yCat - deltaCat)) {

                        LinearLayout toastLayout = new LinearLayout(getApplicationContext());
                        toastLayout.setOrientation(LinearLayout.VERTICAL);
                        toastLayout.setGravity(Gravity.START | Gravity.TOP);

                        ImageView catImageView = new ImageView(getApplicationContext());
                        catImageView.setImageResource(R.drawable.found_cat);

                        toastLayout.addView(catImageView);
                        TextView messageTextView = new TextView(getApplicationContext());
                        messageTextView.setText(R.string.successful_search);
                        toastLayout.addView(messageTextView);

                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(toastLayout);
                        toast.setGravity(Gravity.START | Gravity.TOP, (int) x, (int) y);
                        toast.show();

                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    sMove = "";
                    sUp = "Отпускание: координата X = " + x + ", координата y = " + y;
                    break;
            }

            coordinatesOut.setText(sDown + "\n" + sMove + "\n" + sUp);

            return true;
        }
    };
}
