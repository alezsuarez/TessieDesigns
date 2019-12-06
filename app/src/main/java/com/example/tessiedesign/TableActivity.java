package com.example.tessiedesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tessiedesign.database.DatabaseTask;
import com.example.tessiedesign.database.Request;
import com.example.tessiedesign.database.TessieDesignDB;

import java.util.ArrayList;
import java.util.List;

public class TableActivity extends AppCompatActivity implements DatabaseTask.ICallbackReceiverDB, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private EditText tfName, tfQuantity1, tfQuantity2, tfQuantity3, tfQuantity4, tfQuantity5, tfQuantity6, tfQuantity7, tfQuantity8
            ,tfImage1, tfImage2, tfImage3, tfImage4, tfImage5, tfImage6, tfImage7, tfImage8, et_data, et_notes;

    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8;
    private Button btnRegister, btnViewData;
    private RadioGroup rg1,rg2,rg3,rg4,rg5,rg6,rg7,rg8;
    private RadioButton rb1_1, rb1_2, rb2_1, rb2_2, rb3_1, rb3_2, rb4_1,
            rb4_2, rb5_1, rb5_2, rb6_1, rb6_2, rb7_1, rb7_2, rb8_1, rb8_2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_activity);

        tfName = findViewById(R.id.et_name);

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        checkBox7 = findViewById(R.id.checkBox7);
        checkBox8 = findViewById(R.id.checkBox8);

        checkBox1.setOnCheckedChangeListener(this);
        checkBox2.setOnCheckedChangeListener(this);
        checkBox3.setOnCheckedChangeListener(this);
        checkBox4.setOnCheckedChangeListener(this);
        checkBox5.setOnCheckedChangeListener(this);
        checkBox6.setOnCheckedChangeListener(this);
        checkBox7.setOnCheckedChangeListener(this);
        checkBox8.setOnCheckedChangeListener(this);

        rg1 = findViewById(R.id.radioGroup1);
        rg2 = findViewById(R.id.radioGroup2);
        rg3 = findViewById(R.id.radioGroup3);
        rg4 = findViewById(R.id.radioGroup4);
        rg5 = findViewById(R.id.radioGroup5);
        rg6 = findViewById(R.id.radioGroup6);
        rg7 = findViewById(R.id.radioGroup7);
        rg8 = findViewById(R.id.radioGroup8);

        rb1_1 = findViewById(R.id.radioButton_1_1);
        rb1_2 = findViewById(R.id.radioButton_1_2);
        rb2_1 = findViewById(R.id.radioButton_2_1);
        rb2_2 = findViewById(R.id.radioButton_2_2);
        rb3_1 = findViewById(R.id.radioButton_3_1);
        rb3_2 = findViewById(R.id.radioButton_3_2);
        rb4_1 = findViewById(R.id.radioButton_4_1);
        rb4_2 = findViewById(R.id.radioButton_4_2);
        rb5_1 = findViewById(R.id.radioButton_5_1);
        rb5_2 = findViewById(R.id.radioButton_5_2);
        rb6_1 = findViewById(R.id.radioButton_6_1);
        rb6_2 = findViewById(R.id.radioButton_6_2);
        rb7_1 = findViewById(R.id.radioButton_7_1);
        rb7_2 = findViewById(R.id.radioButton_7_2);
        rb8_1 = findViewById(R.id.radioButton_8_1);
        rb8_2 = findViewById(R.id.radioButton_8_2);


        tfQuantity1 = findViewById(R.id.et_quantity1);
        tfQuantity2 = findViewById(R.id.et_quantity2);
        tfQuantity3 = findViewById(R.id.et_quantity3);
        tfQuantity4 = findViewById(R.id.et_quantity4);
        tfQuantity5 = findViewById(R.id.et_quantity5);
        tfQuantity6 = findViewById(R.id.et_quantity6);
        tfQuantity7 = findViewById(R.id.et_quantity7);
        tfQuantity8 = findViewById(R.id.et_quantity8);

        tfImage1 = findViewById(R.id.et_image1);
        tfImage2 = findViewById(R.id.et_image2);
        tfImage3 = findViewById(R.id.et_image3);
        tfImage4 = findViewById(R.id.et_image4);
        tfImage5 = findViewById(R.id.et_image5);
        tfImage6 = findViewById(R.id.et_image6);
        tfImage7 = findViewById(R.id.et_image7);
        tfImage8 = findViewById(R.id.et_image8);

        et_data = findViewById(R.id.et_data);
        et_notes = findViewById(R.id.et_notes);

        btnRegister = findViewById(R.id.btnRegister);
        btnViewData = findViewById(R.id.btnViewData);

        btnRegister.setOnClickListener(this);
        btnViewData.setOnClickListener(this);



    }



    @Override
    public void getAllRequests(List<Request> requestList) {

        Log.d("Callback", "Se activo el callack ");
        for(Request item : requestList) {
            Log.d("ID", Integer.toString(item.request_id));
            Log.d("Name",item.name);
            Log.d("Quantity", item.quantity);
        }
    }

    @Override
    public void onClick(View v) {
        TessieDesignDB db = Room.databaseBuilder(this, TessieDesignDB.class,"TessieDesignDB")
                .allowMainThreadQueries().build();
        if(v.getId() == R.id.btnRegister) {
            Request request = new Request();
            request.name = tfName.getText().toString();
            request.size = getChecboxValues();
            request.type = getRadioButtonValues();
            request.quantity = getQuantityFields();
            request.image = getImageFields();
            request.data = et_data.getText().toString();
            request.notes = et_notes.getText().toString();

            Log.d("Name", request.name);
            Log.d("Size", request.size);
            Log.d("Type", request.type);
            Log.d("Quantity", request.quantity);
            Log.d("Data", request.data);
            Log.d("Notes", request.notes);

            db.requestDAO().insertRequest(request);
            Log.d("Done", "Insercion Realizada");
            Toast.makeText(this, "Pedido Registrado", Toast.LENGTH_SHORT).show();

        }

        if(v.getId() == R.id.btnViewData) {
//            Log.d("DEBUG", "ENTRE A btnViewData");
//           DatabaseTask task = new DatabaseTask(getApplicationContext(), this);
//           task.execute();
//            Log.d("DEBUG", "SALIENDO de btnViewData");
            Intent intent = new Intent(this, RequestLogActivity.class);
            startActivity(intent);
        }

    }
    private String getChecboxValues() {
        ArrayList<String> size_buffer = new ArrayList<>();
        String logOfCheckBox = "";
        if(checkBox1.isChecked() ? size_buffer.add(checkBox1.getText().toString()) : size_buffer.add("*"));
        if(checkBox2.isChecked() ? size_buffer.add(checkBox2.getText().toString()) : size_buffer.add("*"));
        if(checkBox3.isChecked() ? size_buffer.add(checkBox3.getText().toString()) : size_buffer.add("*"));
        if(checkBox4.isChecked() ? size_buffer.add(checkBox4.getText().toString()) : size_buffer.add("*"));
        if(checkBox5.isChecked() ? size_buffer.add(checkBox5.getText().toString()) : size_buffer.add("*"));
        if(checkBox6.isChecked() ? size_buffer.add(checkBox6.getText().toString()) : size_buffer.add("*"));
        if(checkBox7.isChecked() ? size_buffer.add(checkBox7.getText().toString()) : size_buffer.add("*"));
        if(checkBox8.isChecked() ? size_buffer.add(checkBox8.getText().toString()) : size_buffer.add("*"));

        for(int i = 0; i < size_buffer.size(); i++) {
            if(i < size_buffer.size() - 1)
                logOfCheckBox += size_buffer.get(i) + "_";
            else
                logOfCheckBox += size_buffer.get(i);
        }
        return logOfCheckBox;
    }

    private String getRadioButtonValues() {
        String logOfRB_values = "";
        String radioValue1, radioValue2, radioValue3, radioValue4, radioValue5, radioValue6, radioValue7, radioValue8;
        if(rg1.getCheckedRadioButtonId() != -1) radioValue1 = ((RadioButton)findViewById(rg1.getCheckedRadioButtonId())).getText().toString(); else radioValue1 = "*";
        if(rg2.getCheckedRadioButtonId() != -1) radioValue2 = ((RadioButton)findViewById(rg2.getCheckedRadioButtonId())).getText().toString(); else radioValue2 = "*";
        if(rg3.getCheckedRadioButtonId() != -1) radioValue3 = ((RadioButton)findViewById(rg3.getCheckedRadioButtonId())).getText().toString(); else radioValue3 = "*";
        if(rg4.getCheckedRadioButtonId() != -1) radioValue4 = ((RadioButton)findViewById(rg4.getCheckedRadioButtonId())).getText().toString(); else radioValue4 = "*";
        if(rg5.getCheckedRadioButtonId() != -1) radioValue5 = ((RadioButton)findViewById(rg5.getCheckedRadioButtonId())).getText().toString(); else radioValue5 = "*";
        if(rg6.getCheckedRadioButtonId() != -1) radioValue6 = ((RadioButton)findViewById(rg6.getCheckedRadioButtonId())).getText().toString(); else radioValue6 = "*";
        if(rg7.getCheckedRadioButtonId() != -1) radioValue7 = ((RadioButton)findViewById(rg7.getCheckedRadioButtonId())).getText().toString(); else radioValue7 = "*";
        if(rg8.getCheckedRadioButtonId() != -1) radioValue8 = ((RadioButton)findViewById(rg8.getCheckedRadioButtonId())).getText().toString(); else radioValue8 = "*";

        logOfRB_values += radioValue1 + "_" + radioValue2 + "_" + radioValue3 + "_" + radioValue4 + "_"
               + radioValue5 + "_" + radioValue6 + "_" + radioValue7 + "_" + radioValue8;
        return logOfRB_values;
    }

    private String getQuantityFields() {
        String qty1, qty2, qty3, qty4, qty5, qty6, qty7, qty8;
        if(!tfQuantity1.getText().toString().equals("")) qty1 = tfQuantity1.getText().toString(); else qty1 = "*";
        if(!tfQuantity2.getText().toString().equals("")) qty2 = tfQuantity2.getText().toString(); else qty2 = "*";
        if(!tfQuantity3.getText().toString().equals("")) qty3 = tfQuantity3.getText().toString(); else qty3 = "*";
        if(!tfQuantity4.getText().toString().equals("")) qty4 = tfQuantity4.getText().toString(); else qty4 = "*";
        if(!tfQuantity5.getText().toString().equals("")) qty5 = tfQuantity5.getText().toString(); else qty5 = "*";
        if(!tfQuantity6.getText().toString().equals("")) qty6 = tfQuantity6.getText().toString(); else qty6 = "*";
        if(!tfQuantity7.getText().toString().equals("")) qty7 = tfQuantity7.getText().toString(); else qty7 = "*";
        if(!tfQuantity8.getText().toString().equals("")) qty8 = tfQuantity8.getText().toString(); else qty8 = "*";

        return  qty1 + "_" + qty2 + "_" + qty3 + "_" + qty4 + "_" + qty5 + "_" + qty6 + "_" + qty7  + "_" + qty8;
    }

    private String getImageFields() {
        String img1, img2, img3, img4, img5, img6, img7, img8;
        if(!tfImage1.getText().toString().equals("")) img1 = tfImage1.getText().toString(); else img1 = "*";
        if(!tfImage2.getText().toString().equals("")) img2 = tfImage2.getText().toString(); else img2 = "*";
        if(!tfImage3.getText().toString().equals("")) img3 = tfImage3.getText().toString(); else img3 = "*";
        if(!tfImage4.getText().toString().equals("")) img4 = tfImage4.getText().toString(); else img4 = "*";
        if(!tfImage5.getText().toString().equals("")) img5 = tfImage5.getText().toString(); else img5 = "*";
        if(!tfImage6.getText().toString().equals("")) img6 = tfImage6.getText().toString(); else img6 = "*";
        if(!tfImage7.getText().toString().equals("")) img7 = tfQuantity7.getText().toString(); else img7 = "*";
        if(!tfQuantity8.getText().toString().equals("")) img8 = tfQuantity8.getText().toString(); else img8 = "*";

        return  img1 + "_" + img2 + "_" + img3 + "_" + img4 + "_" + img5 + "_" + img6 + "_" + img7  + "_" + img8;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.checkBox1) {
            if(checkBox1.isChecked()) {
                checkBox1.setEnabled(true);
                rb1_1.setEnabled(true);
                rb1_2.setEnabled(true);
                tfQuantity1.setEnabled(true);
                tfImage1.setEnabled(true);
            }else {
                rb1_1.setEnabled(false);
                rb1_2.setEnabled(false);
                tfQuantity1.setEnabled(false);
                tfImage1.setEnabled(false);
            }
        }

        if(buttonView.getId() == R.id.checkBox2) {
            if(checkBox2.isChecked()) {
                checkBox2.setEnabled(true);
                rb2_1.setEnabled(true);
                rb2_2.setEnabled(true);
                tfQuantity2.setEnabled(true);
                tfImage2.setEnabled(true);
            }else {
                rb2_1.setEnabled(false);
                rb2_2.setEnabled(false);
                tfQuantity2.setEnabled(false);
                tfImage2.setEnabled(false);
            }
        }

        if(buttonView.getId() == R.id.checkBox3) {
            if(checkBox3.isChecked()) {
                checkBox3.setEnabled(true);
                rb3_1.setEnabled(true);
                rb3_2.setEnabled(true);
                tfQuantity3.setEnabled(true);
                tfImage3.setEnabled(true);
            }else {
                rb3_1.setEnabled(false);
                rb3_2.setEnabled(false);
                tfQuantity3.setEnabled(false);
                tfImage3.setEnabled(false);
            }
        }

        if(buttonView.getId() == R.id.checkBox4) {
            if(checkBox4.isChecked()) {
                checkBox4.setEnabled(true);
                rb4_1.setEnabled(true);
                rb4_2.setEnabled(true);
                tfQuantity4.setEnabled(true);
                tfImage4.setEnabled(true);
            }else {
                rb4_1.setEnabled(false);
                rb4_2.setEnabled(false);
                tfQuantity4.setEnabled(false);
                tfImage4.setEnabled(false);
            }
        }

        if(buttonView.getId() == R.id.checkBox5) {
            if(checkBox5.isChecked()) {
                checkBox5.setEnabled(true);
                rb5_1.setEnabled(true);
                rb5_2.setEnabled(true);
                tfQuantity5.setEnabled(true);
                tfImage5.setEnabled(true);
            }else {
                rb5_1.setEnabled(false);
                rb5_2.setEnabled(false);
                tfQuantity5.setEnabled(false);
                tfImage5.setEnabled(false);
            }
        }

        if(buttonView.getId() == R.id.checkBox6) {
            if(checkBox6.isChecked()) {
                checkBox6.setEnabled(true);
                rb6_1.setEnabled(true);
                rb6_2.setEnabled(true);
                tfQuantity6.setEnabled(true);
                tfImage6.setEnabled(true);
            }else {
                rb6_1.setEnabled(false);
                rb6_2.setEnabled(false);
                tfQuantity6.setEnabled(false);
                tfImage6.setEnabled(false);
            }
        }

        if(buttonView.getId() == R.id.checkBox7) {
            if(checkBox7.isChecked()) {
                checkBox7.setEnabled(true);
                rb7_1.setEnabled(true);
                rb7_2.setEnabled(true);
                tfQuantity7.setEnabled(true);
                tfImage7.setEnabled(true);
            }else {
                rb7_1.setEnabled(false);
                rb7_2.setEnabled(false);
                tfQuantity7.setEnabled(false);
                tfImage7.setEnabled(false);
            }
        }

        if(buttonView.getId() == R.id.checkBox8) {
            if(checkBox8.isChecked()) {
                checkBox8.setEnabled(true);
                rb8_1.setEnabled(true);
                rb8_2.setEnabled(true);
                tfQuantity8.setEnabled(true);
                tfImage8.setEnabled(true);
            }else {
                rb8_1.setEnabled(false);
                rb8_2.setEnabled(false);
                tfQuantity8.setEnabled(false);
                tfImage8.setEnabled(false);
            }
        }
    }
}
