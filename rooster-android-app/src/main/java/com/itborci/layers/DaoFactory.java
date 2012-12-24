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
 * Factory for Subject data access object
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
public class DaoFactory {

    // don't allow instantiation
    private DaoFactory() {
    }

    public static SubjectDao getSubjectDao() {
        if (Settings.mockSubjectDao()) {
            return new SubjectDaoMock();
        } else {
            return new SubjectDaoSQLite();
        }
    }
}
