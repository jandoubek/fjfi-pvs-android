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

import java.util.ArrayList;
import java.util.List;

/**
 * Business layer for object {@link com.itborci.layers.POJO.Subject}
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
public class SubjectBLImpl implements SubjectBL {

    @Override
    public void saveSubject(Subject subject) {
        getSubjectDL().saveSubject(subject);
    }

    @Override
    public void createSubject(Subject subject) {
        getSubjectDL().createSubject(subject);
    }

    @Override
    public List<Subject> getSubjectsByDay(DayOfWeek dayOfWeek) {
        return getSubjectDL().getSubjectsByDay(dayOfWeek);
    }

    @Override
    public List<List<Subject>> getSubjectsByWeek() {
        List<List<Subject>> result = new ArrayList<List<Subject>>();

        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            result.add(getSubjectsByDay(dayOfWeek));
        }

        return result;
    }

    private SubjectDL getSubjectDL() {
        return SubjectDLFactory.getSubjectDL();
    }
}
