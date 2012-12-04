package com.itborci;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubjectDialog extends Dialog {
	private SharedPreferences sharedPreferences;
	private EditText name, classroom, teacher;
	private Button okButton, cancelButton, deleteButton;
	private TextView editedTextView;
	private Context context;

	public SubjectDialog(Context context, TextView textView) {
		super(context);
		this.context = context;
		setContentView(R.layout.subject_dialog);
		setTitle(R.string.subject_details);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		
		editedTextView = textView;
		
		name = (EditText)findViewById(R.id.nameEditText);
		classroom = (EditText)findViewById(R.id.classroomEditText);
		teacher = (EditText)findViewById(R.id.teacherEditText);
		
		okButton = (Button)findViewById(R.id.okButton);
		if (okButton == null) Log.i("AHA", "je no");
		okButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hide();				
			}
		});
		
		deleteButton = (Button)findViewById(R.id.deleteButton);
		deleteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editedTextView.setText("AHOJSFASF\n\nJOP");
				editedTextView.setBackgroundColor(Color.BLUE);
				hide();				
			}
		});
		
		cancelButton = (Button)findViewById(R.id.cancelButton);
		cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hide();
				
			}
		});
	}
}
