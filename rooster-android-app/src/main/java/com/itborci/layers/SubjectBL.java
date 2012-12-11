/*
 * $HeadURL$
 * $Date$
 * $Revision$
 *
 * All Rights Reserved.
 */
package com.itborci.layers;

import com.itborci.POJO.Subject;
import com.itborci.enums.DayOfWeek;

import java.util.List;

/**
 * Bussiness layer for object {@link com.itborci.POJO.Subject}
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
public interface SubjectBL {

    /**
     * Saves subject into storage layer
     * @param subject Subject to save
     */
    public void saveSubject(Subject subject);

    /**
     * Creates and saves subject into storage layer
     * @param subject Subject to create
     */
    public void createSubject(Subject subject);

    /**
     * Deletes subject from storage layer
     * @param subject Subject to delete
     */
    public void deleteSubject(Subject subject);

    /**
     * Get subjects that are in given day
     * @param dayOfWeek Day of the week
     * @return List of subjects
     */
    public List<Subject> getSubjectsByDay(DayOfWeek dayOfWeek);

    /**
     * Get the whole week of subjects
     * @return List of List of subjects
     */
    public List<List<Subject>> getSubjectsByWeek();
}
