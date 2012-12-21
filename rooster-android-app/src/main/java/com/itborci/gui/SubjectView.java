/*
 * $HeadURL$
 * $Date$
 * $Revision$
 */
package com.itborci.gui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.itborci.POJO.Subject;
import com.itborci.R;
import com.itborci.layers.DaoFactory;

/**
 * Description
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
public class SubjectView extends LinearLayout {

    private Communicator communicator;

    public SubjectView(Context context) {
        super(context);
    }

    public SubjectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.subject_view, this);

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.SubjectView);
        int hour = arr.getInt(R.styleable.SubjectView_hour, 0);
        int day = arr.getInt(R.styleable.SubjectView_day, 0);
        int week = arr.getInt(R.styleable.SubjectView_week, 0);

        Subject subject = DaoFactory.getSubjectDao().getSubjectByWeekDayHour(week, day, hour, context);
        if (subject == null) {
            subject = Subject.create();
            subject.setHour(hour);
            subject.setDay(day);
            subject.setWeek(week);
        }
        communicator = new Communicator(this, subject);

        arr.recycle();

        refreshFromData(context);

        setClickable(true);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectView.this.onClick();
            }
        });

    }

    private void refreshFromData(Context context) {
        TextView tvName = (TextView) findViewById(R.id.subject_view_name);
        TextView tvRoom = (TextView) findViewById(R.id.subject_view_room);
        TextView tvTeacher = (TextView) findViewById(R.id.subject_view_teacher);
        ImageView ivBell = (ImageView) findViewById(R.id.subject_view_bell);

        Subject subject = communicator.getSubject();
        if (subject.getId() != null) {
            setBackgroundResource(R.drawable.border_rounded);

            tvName.setText(subject.getName());
            tvRoom.setText(subject.getRoom());
            tvTeacher.setText(subject.getTeacher());
            ivBell.setImageResource(subject.isBell() ? R.drawable.bell_on : R.drawable.bell_off);

            GradientDrawable  drawable = (GradientDrawable) context.getResources().getDrawable(R.drawable.border_rounded);
            drawable.setColor(subject.getColor());
            setBackgroundDrawable(drawable);
        } else {
            tvName.setText("");
            tvRoom.setText("");
            tvTeacher.setText("");
            ivBell.setImageDrawable(null);
            setBackgroundDrawable(null);
        }
    }

    public Communicator getCommunicator() {
        return communicator;
    }

    public void onClick() {
        SubjectDialog subjectDialog = new SubjectDialog(getContext(), communicator);
        subjectDialog.show();
    }

    public class Communicator {
        Subject subject;
        final SubjectView subjectView;

        public Communicator(SubjectView subjectView, Subject subject) {
            this.subjectView = subjectView;
            this.subject = subject;
        }

        public Subject getSubject() {
            return subject;
        }

        public void updateView(Context context) {
            subjectView.refreshFromData(context);
            subjectView.invalidate();
        }
    }

}
