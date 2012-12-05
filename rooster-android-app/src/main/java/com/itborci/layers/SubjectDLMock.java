/*
 * $HeadURL$
 * $Date$
 * $Revision$
 *
 * All Rights Reserved.
 */
package com.itborci.layers;

import com.itborci.enums.DayOfWeek;
import com.itborci.layers.POJO.Subject;

import java.util.List;

/**
 * Mock implementation of Subject DL
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
class SubjectDLMock implements SubjectDL {
    public void saveSubject(Subject subject) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    
    public void createSubject(Subject subject) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Subject> getSubjectsByDay(DayOfWeek dayOfWeek) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
