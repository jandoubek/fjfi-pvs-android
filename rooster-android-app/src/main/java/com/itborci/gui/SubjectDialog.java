package com.itborci.gui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.itborci.R;

public class SubjectDialog extends BaseDialog {
	private SharedPreferences sharedPreferences;
	private EditText name, classroom, teacher;
	private Button okButton, cancelButton, deleteButton;
	private TextView editedTextView;
	private Context context;

	public SubjectDialog(Context context, TextView textView) {
		super(context);
		this.context = context;
        this.editedTextView = textView;

        setContentView(R.layout.subject_dialog);
        setTitle(R.string.subject_details);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

		name = getEditText(R.id.nameEditText);
		classroom = getEditText(R.id.classroomEditText);
		teacher = getEditText(R.id.teacherEditText);
		
		okButton = getButton(R.id.okButton);
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				hide();				
			}
		});
		
		deleteButton = getButton(R.id.deleteButton);
		deleteButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				editedTextView.setText("AHOJSFASF\n\nJOP");
				editedTextView.setBackgroundColor(Color.BLUE);
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
	}
}
