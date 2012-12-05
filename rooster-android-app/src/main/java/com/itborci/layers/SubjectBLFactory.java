/*
 * $HeadURL$
 * $Date$
 * $Revision$
 *
 * All Rights Reserved.
 */
package com.itborci.layers;

/**
 * Factory for Subject business layer
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
public class SubjectBLFactory {

    private static SubjectBL subjectBL = null;

    // don't allow instantiation
    private SubjectBLFactory() {
    }

    public static SubjectBL getSubjectBL() {
        if (subjectBL == null) {
            subjectBL = new SubjectBLImpl();
        }
        return subjectBL;
    }
}
