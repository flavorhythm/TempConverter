package tempconverter.zenoyuki.com.tempconverter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText cVal, fVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        cVal = (EditText)findViewById(R.id.c_ID);
        fVal = (EditText)findViewById(R.id.f_ID);

        final DecimalFormat valFormat = new DecimalFormat("###.#");

        cVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(cVal.isFocused()) {
                    try {
                        double fValInt = convert(Double.parseDouble(s.toString()), 'f');
                        String output = valFormat.format(fValInt);

                        fVal.setText(output);
                    } catch (NumberFormatException e) {
                        fVal.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        fVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(fVal.isFocused()) {
                    try {
                        double cValInt = convert(Double.parseDouble(s.toString()), 'c');
                        String output = valFormat.format(cValInt);

                        cVal.setText(output);
                    } catch (NumberFormatException e) {
                        cVal.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private double convert(double val, char conv_to) {
        switch (conv_to) {
            case 'f':
                return val * (9.0/5.0) + 32.0;
            case 'c':
                return (val - 32.0) * (5.0/9.0);
            default:
                return 0;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
