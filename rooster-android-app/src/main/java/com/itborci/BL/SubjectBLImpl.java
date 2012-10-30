/*
 * $HeadURL$
 * $Date$
 * $Revision$
 *
 * All Rights Reserved.
 */
package com.itborci.BL;

import com.itborci.POJO.Subject;
import com.itborci.enums.DayOfWeek;

import java.util.List;

/**
 * Bussiness layer for object {@link Subject}
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
public class SubjectBLImpl implements SubjectBL {
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

    @Override
    public List<List<Subject>> getSubjectsByWeek() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
