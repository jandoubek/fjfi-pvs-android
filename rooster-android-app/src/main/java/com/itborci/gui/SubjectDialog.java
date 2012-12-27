package com.itborci.gui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import com.itborci.POJO.Subject;
import com.itborci.R;
import com.itborci.layers.DaoFactory;

public class SubjectDialog extends BaseDialog {
	private static final int inputMaxLength = 9;
	private SharedPreferences sharedPreferences;
	private EditText name, classroom, teacher;
	private Button okButton, cancelButton, deleteButton;
	private Spinner colorSpinner;
    private Context context;
    private CheckBox notification;

    private final SubjectView.Communicator communicator;

	public SubjectDialog(Context context, SubjectView.Communicator communicator) {
		super(context);
		this.context = context;
        this.communicator = communicator;

        setContentView(R.layout.subject_dialog);
        setTitle(R.string.subject_details);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

		initWidgets();
        fillEditTexts();
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
		notification = (CheckBox) findViewById(R.id.notificationCheckBox);
		
		okButton = getButton(R.id.okButton);
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				onSave();
			}
		});
		
		deleteButton = getButton(R.id.deleteButton);
		deleteButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				onDelete();
			}
		});
		
		cancelButton = getButton(R.id.cancelButton);
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				onCancel();
			}
		});
		
		name.requestFocus(); // cursor sets on first EditText
	}
	
	private void fillEditTexts() {
        Subject subject = communicator.getSubject();

        name.setText(subject.getName());
        classroom.setText(subject.getRoom());
        teacher.setText(subject.getTeacher());
        colorSpinner.setSelection(convertColor(subject.getColor()));
        int bell = subject.getBellAsInt();
        if (bell == 1) notification.setChecked(true);
    }

	private int convertColor(int color) {
		switch (color) {
		case Color.BLUE:
			return 0;
		case Color.RED:
			return 1;
		case Color.GREEN:
			return 2;
		case Color.YELLOW:
			return 3;
		case Color.BLACK:
			return 4;

		default:
			return 0;
		}
	}

	private void onSave() {
        Subject subject = communicator.getSubject();

        subject.setName(name.getText().toString());
        subject.setRoom(classroom.getText().toString());
        subject.setTeacher(teacher.getText().toString());
        subject.setColor(getColorFromSpinner(colorSpinner));
        
        if (notification.isChecked()) {
        	subject.setBell(true);
        	AlarmManagerReceiver alarm = new AlarmManagerReceiver();
        	alarm.setAlarm(context, subject);
        } else {
        	subject.setBell(false);
        }
        
        DaoFactory.getSubjectDao().saveSubject(subject, context);

        communicator.updateView(context);
        hide();
    }

    private void onCancel() {
        hide();
    }

    private void onDelete() {
        DaoFactory.getSubjectDao().deleteSubject(communicator.getSubject(), context);
        communicator.getSubject().setId(null);

        communicator.updateView(context);
        hide();
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
	private int getColorFromSpinner(Spinner spinner) {
		int idColor = (int)spinner.getSelectedItemId();

		switch (idColor) {
		case 0:
            return Color.BLUE;
		case 1:
			return Color.RED;
		case 2:
			return 0xFF75FF7A; // greenish
		case 3:
			return 0xFFFFF675; // yellowish
		case 4:
			return Color.BLACK;
		default:
			return Color.MAGENTA;
		}
	}
}
