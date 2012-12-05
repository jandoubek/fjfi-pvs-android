package com.itborci.gui;

import android.app.Activity;
import com.itborci.layers.SubjectBL;
import com.itborci.layers.SubjectBLFactory;

/**
 * Base class for activities. <br>
 * It contains getter for our SubjectBL.
 *
 * @author <a href="mailto:petr.ujezdsky@cleverlance.com">Petr Újezdský</a>
 * @version $Id$
 */
public class BaseActivity extends Activity {

    /**
     * Getter for our {@link com.itborci.layers.SubjectBL}.
     *
     * @return SubjectBL subject business layer
     */
    protected SubjectBL getSubjectBL() {
        return SubjectBLFactory.getSubjectBL();
    }
}
