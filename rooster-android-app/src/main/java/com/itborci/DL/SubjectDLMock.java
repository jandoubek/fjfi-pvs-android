/*
 * $HeadURL$
 * $Date$
 * $Revision$
 *
 * All Rights Reserved.
 */
package com.itborci.DL;

import com.itborci.POJO.Subject;
import com.itborci.enums.DayOfWeek;

import java.util.List;

/**
 * Mock implementation of Subject DL
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
public class SubjectDLMock implements SubjectDL {
    @Override
    public void saveSubject(Subject subject) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createSubject(Subject subject) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Subject> getSubjectsByDay(DayOfWeek dayOfWeek) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
