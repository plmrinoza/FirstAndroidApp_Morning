
package com.mlabs.bbm.firstandroidapp_morningclass;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.app.Activity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText l1;
    private EditText l2;
    private Button b1;
    private TextView stm;
    private TextView Show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l1 = (EditText) findViewById(R.id.l1);
        l2 = (EditText) findViewById(R.id.l2);
        b1 = (Button) findViewById(R.id.b1);
        stm = (TextView) findViewById(R.id.stm);
        Show = (TextView) findViewById(R.id.Show);

        b1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                String username = String.valueOf(l1.getText());
                String password = String.valueOf(l2.getText());

                if (username.equals("jpeg_26@yahoo.com") && password.equals("122692")) {
                    stm.setText("Access Granted!");
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);

                        } else {
                            stm.setText("Access Denied!");
                                           }
                                  }
                });
        Show.setOnTouchListener(new View.OnTouchListener(){
        @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//            int event = motionEvent.getAction();

            switch(motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    Log.d("event", "down");
                    l2.setTransformationMethod(null);
                    return true;
                case MotionEvent.ACTION_UP:
                    Log.d("event", "up");
                    l2.setTransformationMethod(new PasswordTransformationMethod());
                    return true;


            }
            return false;
//            if (event == motionEvent.ACTION_DOWN){
//                Log.d("onTouchListener","ACTION_DOWN was pressed");
//                l2.setTransformationMethod(null);
//                return true;
//            }
//            else{
//                Log.d("onTouchListener","ACTION_DOWN was released");
//                l2.setTransformationMethod(new PasswordTransformationMethod());
//                return true;
//            }
        }

        });
    }

}