package by.scar.itspartnertestcase;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText loginField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        loginField.addTextChangedListener(textWatcher);

        passwordField.addTextChangedListener(textWatcher);
    }

    private void checkFields(){
        if(passwordField.getText().toString().equals("test") && loginField.getText().toString().equals("test"))
            startActivity(new Intent(this, SecondActivity.class));
    }

    private void initViews(){
        loginField = findViewById(R.id.login_field);
        passwordField = findViewById(R.id.password_field);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            checkFields();
        }
    };
}
