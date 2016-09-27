package com.pillbot.activity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONException;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pillbot.R;
import com.pillbot.data.IntervalUnit;
import com.pillbot.data.Medication;

@SuppressWarnings("deprecation")
public class AddMedsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_meds);

		// Handle drop-down for Frequency Taken Units
		Spinner frequencyTakenUnitsSpinner = (Spinner) findViewById(R.id.id_freq_taken_units);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter
				.createFromResource(this, R.array.units_array,
						android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		frequencyTakenUnitsSpinner.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_meds, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void save(View view) {
		EditText medNameView = (EditText) findViewById(R.id.id_med_name);
		EditText medDoseView = (EditText) findViewById(R.id.id_med_dose);
		EditText freqTakenCountView = (EditText) findViewById(R.id.id_freq_taken_count);
		Spinner freqTakenUnitsView = (Spinner) findViewById(R.id.id_freq_taken_units);
		CheckBox isAsNeededView = (CheckBox) findViewById(R.id.id_as_needed);

		String medName = medNameView.getText().toString();
		String medDose = medDoseView.getText().toString();
		int freqTakenCount = Integer.parseInt(freqTakenCountView.getText()
				.toString());
		IntervalUnit freqTakenUnit = null;

		try {
			freqTakenUnit = IntervalUnit.getString(freqTakenUnitsView
					.getSelectedItem().toString());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		boolean isAsNeeded = isAsNeededView.isActivated();

		Medication newMed = new Medication(medName, medDose, freqTakenCount,
				freqTakenUnit, isAsNeeded);

		try {
			String filename = medName;
			FileOutputStream outputStream = openFileOutput(filename,
					Context.MODE_PRIVATE);
			outputStream.write(newMed.toJSON().getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO display success or error message
		// TODO wipe options, allow user to add another med or press cancel
		// TODO "add another medication? Y/N" dialogue?
	}

	public void cancel(View view) {
		finish();
	}
}
