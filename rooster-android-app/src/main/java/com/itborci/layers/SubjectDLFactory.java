/*
 * $HeadURL$
 * $Date$
 * $Revision$
 *
 * All Rights Reserved.
 */
package com.itborci.layers;

import com.itborci.common.Settings;

/**
 * Factory for Subject data access layer
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
class SubjectDLFactory {

    private static SubjectDL subjectDL = null;

    // don't allow instantiation
    private SubjectDLFactory() {
    }

    public static SubjectDL getSubjectDL() {
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
