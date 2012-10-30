/*
 * $HeadURL$
 * $Date$
 * $Revision$
 *
 * All Rights Reserved.
 */
package com.itborci.DL;

import com.itborci.common.Settings;

/**
 * Factory for data access layers
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
public class DLFactory {

    protected static SubjectDL subjectDL = null;

    public SubjectDL getSubjectDL() {
        if (subjectDL == null) {
            if (Settings.mockSubjectDL()) {
                subjectDL = new SubjectDLMock();
            } else {
                subjectDL = new SubjectDLImpl();
            }
        }
        return subjectDL;
    }
}
