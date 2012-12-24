/*
 * $HeadURL$
 * $Date$
 * $Revision$
 *
 */
package com.itborci.gui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.itborci.R;

/**
 * Description
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
public class DayView extends LinearLayout {
    public DayView(Context context) {
        super(context);
    }

    public DayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.day_view, this);

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.DayView);
        String name = arr.getString(R.styleable.DayView_name);

        arr.recycle();

        TextView tvName = (TextView) findViewById(R.id.day_view_name);

        tvName.setText(name);
    }
}
