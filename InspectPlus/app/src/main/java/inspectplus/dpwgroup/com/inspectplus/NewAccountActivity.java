package inspectplus.dpwgroup.com.inspectplus;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class NewAccountActivity extends Activity {
    private EditText etName, etOrgName, etTitle, etOrgType, etAddress, etMobile, etLandline, etEmail, etCardNum, etCVV;
    private DatePickerDialog expiryDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Spinner spCard_Type;
    private TextView tvExpiryDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        SetUpUI();

        addItemsOnSpinner();
    }


    private void SetUpUI() {
        tvExpiryDate = (TextView) findViewById(R.id.tv_expirydate);
        tvExpiryDate.setOnClickListener(new ButtonListener());

    }

    public void addItemsOnSpinner() {
        spCard_Type = (Spinner) findViewById(R.id.sp_card_type);
        List<String> list = new ArrayList<String>();
        list.add("Enter Card Type");
        list.add("Visa");
        list.add("Master Card");
        list.add("Place Holder");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCard_Type.setAdapter(dataAdapter);
    }

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.tv_expirydate:
                    ShowDatePicker();
                    Toast.makeText(getApplicationContext(), "Expiry Date Selected",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_signupfrm:
                    intent = new Intent(NewAccountActivity.this, NewAccountActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Sign Up Button Selected",
                            Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "No options selected",
                            Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void ShowDatePicker(){
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        System.out.println("the selected " + mDay);
        DatePickerDialog dialog = new DatePickerDialog(NewAccountActivity.this,
                new mDateSetListener(), mYear, mMonth, mDay);
        dialog.show();

    }

    class mDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            // getCalender();
            int mYear = year;
            int mMonth = monthOfYear;
            int mDay = dayOfMonth;
            tvExpiryDate.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("/").append(mDay).append("/")
                    .append(mYear).append(" "));
            System.out.println(tvExpiryDate.getText().toString());


        }
    }



}
