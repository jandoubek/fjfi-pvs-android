package com.itborci.gui;

import android.R.color;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.itborci.R;

public class SubjectDialog extends BaseDialog {
	private SharedPreferences sharedPreferences;
	private EditText name, classroom, teacher;
	private Button okButton, cancelButton, deleteButton;
	private TextView editedTextView;
	private Spinner colorSpinner;
	private Context context;

	public SubjectDialog(Context context, TextView textView) {
		super(context);
		this.context = context;
        this.editedTextView = textView;

        setContentView(R.layout.subject_dialog);
        setTitle(R.string.subject_details);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

		initWidgets();
	}

	private void initWidgets() {
		name = getEditText(R.id.nameEditText);
		classroom = getEditText(R.id.classroomEditText);
		teacher = getEditText(R.id.teacherEditText);
		colorSpinner = (Spinner) findViewById(R.id.colorSpinner);
		
		okButton = getButton(R.id.okButton);
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				editedTextView.setText(name.getText() + "\n" + classroom.getText() + "\n" + teacher.getText());
				setBackgroundColor((int)colorSpinner.getSelectedItemId());
				hide();				
			}
		});
		
		deleteButton = getButton(R.id.deleteButton);
		deleteButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				editedTextView.setText("");
				editedTextView.setBackgroundColor(Color.WHITE);
				hide();				
			}
		});
		
		cancelButton = getButton(R.id.cancelButton);
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				hide();
				
			}
		});
		
		name.requestFocus();
	}

	// set background of edited TextView
	private void setBackgroundColor(int idColor) {
		idColor = (int)colorSpinner.getSelectedItemId();
		switch (idColor) {
		case 0:
			editedTextView.setBackgroundColor(Color.BLUE);
			break;
		case 1:
			editedTextView.setBackgroundColor(Color.RED);				
			break;
		case 2:
			editedTextView.setBackgroundColor(Color.GREEN);
			break;
		case 3:
			editedTextView.setBackgroundColor(Color.YELLOW);
			break;
		case 4:
			editedTextView.setBackgroundColor(Color.BLACK);
			break;
		default:
			break;
		}
		
		if (idColor != 3) editedTextView.setTextColor(Color.WHITE); // 3 = yellow => white text and yellow background isnù good combination
	}
}
