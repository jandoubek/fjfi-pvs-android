package com.itborci.gui;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.itborci.layers.SubjectBL;
import com.itborci.layers.SubjectBLFactory;
import junit.framework.Assert;

/**
 * Base class for dialogs. <br>
 * It contains getter for our SubjectBL and some helper methods.
 *
 * @author <a href="mailto:petr.ujezdsky@cleverlance.com">Petr Újezdský</a>
 * @version $Id$
 */
public class BaseDialog extends Dialog {
    protected BaseDialog(Context context) {
        super(context);
    }

    protected BaseDialog(Context context, int theme) {
        super(context, theme);
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     * Getter for our {@link SubjectBL}.
     *
     * @return SubjectBL subject business layer
     */
    protected SubjectBL getSubjectBL() {
        return SubjectBLFactory.getSubjectBL();
    }

    /**
     * Returns EditText if it exists. Throws exception when it does not.
     * @param id ID of EditText
     * @return EditText if it exists. Throws exception when it does not.
     */
    public EditText getEditText(int id) {
        return (EditText)getView(id);
    }

    /**
     * Returns Button if it exists. Throws exception when it does not.
     * @param id ID of Button
     * @return Button if it exists. Throws exception when it does not.
     */
    public Button getButton(int id) {
        return (Button)getView(id);
    }

    /**
     * Returns View if it exists. Throws exception when it does not.
     * @param id ID of View
     * @return View if it exists. Throws exception when it does not.
     */
    private View getView(int id) {
        View result = findViewById(id);
        // TODO Maybe do Log.e(...) if assert don't write to this log
        Assert.assertNotNull("View not found, id = " + id, result);
        return result;
    }
}
