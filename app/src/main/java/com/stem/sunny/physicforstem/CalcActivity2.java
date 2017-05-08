package com.stem.sunny.physicforstem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * @author Sunnara
 * @version 1.0
 * Description - This class creates an activity that displays a conversion of weight options such
 *               as pounds, kilograms, grams, etc.
 */
public class CalcActivity2 extends AppCompatActivity {
    private EditText box1, box2;
    private Spinner spin1, spin2;
    private Button convertB1;

    /**
     *
     * This app creates the content view of the activity page while giving functionality to
     * buttons or equipments involved with it.
     * @param savedInstanceState help recreate without losing prior information
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc2);


        box1 = (EditText) findViewById(R.id.box1);

        box2 = (EditText) findViewById(R.id.box2);
        box2.setEnabled(false);

        spin1 = (Spinner) findViewById(R.id.weight1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.weight_table, android.R.layout.simple_spinner_item);
        spin1.setAdapter(adapter1);

        spin2 = (Spinner) findViewById(R.id.weight2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.weight_table, android.R.layout.simple_spinner_item);
        spin2.setAdapter(adapter2);


        convertB1 = (Button) findViewById(R.id.convbutton);
        convertB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                try {
                    convertDist();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.calc_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dist_page:
                startActivity(new Intent(this, CalcActivity.class));
                return true;
            case R.id.weight_page:
                startActivity(new Intent(this,CalcActivity2.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void convertDist() throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, ClassNotFoundException, InstantiationException {
        String s1 = spin1.getSelectedItem().toString();
        String s2 = spin2.getSelectedItem().toString();
        String method = s1 + "To" + s2;

        if(s1.equals(s2)) {
            if (!box1.toString().equals(null)) {
                box2.setTextColor(getResources().getColor(R.color.white));
                box2.setText(box1.getText());
            }
        }

        //Double parameter
        Class[] paramDouble = new Class[1];
        paramDouble[0] = double.class;

        //No parameter
        Class noParams[] = {};

        //CalcActivity in Runtime
        Class c1 = Class.forName("com.stem.sunny.physicforstem.ConvertWeight");
        Object obj = c1.newInstance();

        if(!s1.equals(s2)) {
            Double b1Value = Double.parseDouble(box1.getText().toString());
            if (s1.equals("pounds")) {
                Method metMeters = c1.getMethod("setPounds", paramDouble);
                metMeters.invoke(obj, b1Value);
            } else if (s1.equals("ounces")) {
                Method metInches = c1.getMethod("setOunces", paramDouble);
                metInches.invoke(obj, b1Value);
            } else if (s1.equals("grams")) {
                Method metFeet = c1.getMethod("setGrams", paramDouble);
                metFeet.invoke(obj, b1Value);
            } else if (s1.equals("kilograms")) {
                Method metCentimeters = c1.getMethod("setKilograms", paramDouble);
                metCentimeters.invoke(obj, b1Value);
            }


            //Capitalize first letter of type and add get
            String dType = "get" + s1.substring(0,1).toUpperCase() + s1.substring(1);
            Method methGet = c1.getMethod(dType,noParams);
            double value = (double) methGet.invoke(obj);

            Method methConv = c1.getMethod(method,paramDouble);
            double f = (double) methConv.invoke(obj,value);
            NumberFormat n = new DecimalFormat("##.###");
            String sF = n.format(f);
            box2.setTextColor(getResources().getColor(R.color.white));
            box2.setText(sF);

        }
    }
}
