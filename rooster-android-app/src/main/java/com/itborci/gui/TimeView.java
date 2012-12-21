/*
 * $HeadURL$
 * $Date$
 * $Revision$
 */
package com.itborci.gui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.itborci.R;

/**
 * Description
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
public class TimeView extends RelativeLayout {
    public TimeView(Context context) {
        super(context);
    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.time_view, this);

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.TimeView);
        String from = arr.getString(R.styleable.TimeView_from);
        String to = arr.getString(R.styleable.TimeView_to);

        arr.recycle();

        TextView tvFrom = (TextView) findViewById(R.id.time_view_from);
        TextView tvTo = (TextView) findViewById(R.id.time_view_to);

        tvFrom.setText(from);
        tvTo.setText(to);
    }
}
