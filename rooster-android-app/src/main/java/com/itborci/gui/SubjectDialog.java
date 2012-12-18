package com.itborci.gui;

import android.R.color;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.itborci.R;

public class SubjectDialog extends BaseDialog {
	private static final int inputMaxLength = 9;
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
		
		String text = editedTextView.getText().toString();
		if (text != null) fillEditTexts(text);
	}

	private void initWidgets() {
		name = getEditText(R.id.nameEditText);
		name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					name.setText(checkLengthOfInputs(name));
				}
			}
		});
		
		
		
		classroom = getEditText(R.id.classroomEditText);
		classroom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					classroom.setText(checkLengthOfInputs(classroom));
				}
			}
		});

		teacher = getEditText(R.id.teacherEditText);
		teacher.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					teacher.setText(checkLengthOfInputs(teacher));
				}
			}
		});
		
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
				editedTextView.setText("Test\n\nSubject");
				editedTextView.setBackgroundResource(R.drawable.border);
				editedTextView.setTextColor(Color.GRAY);
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
		
		name.requestFocus(); // cursor sets on first EditText
	}
	
	private void fillEditTexts(String text) {
		String[] inputs = text.split("\n");
		name.setText(inputs[0]);
		classroom.setText(inputs[1]);
		teacher.setText(inputs[2]);
	}
	
	// editing of too long inputs
	private String checkLengthOfInputs(EditText et) {
		String longInput = et.getText().toString();
		if (longInput.length() > inputMaxLength) {
			longInput = longInput.substring(0, inputMaxLength-2) + "..";
		}
		
		return longInput;
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
		
		if (idColor != 3) editedTextView.setTextColor(Color.WHITE); // 3 = yellow => white text and yellow background is not good combination
	}
	
	
}
