/*
 * $HeadURL$
 * $Date$
 * $Revision$
 *
 * All Rights Reserved.
 */
package com.itborci.BL;

/**
 * Factory for bussiness layers
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
public class BLFactory {

    private static SubjectBL subjectBL = null;

    public static SubjectBL getSubjectBL() {
        if (subjectBL == null) {
            subjectBL = new SubjectBLImpl();
        }
        return subjectBL;
    }
}
