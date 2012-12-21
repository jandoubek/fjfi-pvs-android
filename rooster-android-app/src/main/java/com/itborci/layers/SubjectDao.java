/*
 * $HeadURL$
 * $Date$
 * $Revision$
 *
 * All Rights Reserved.
 */
package com.itborci.layers;

import android.content.Context;
import com.itborci.POJO.Subject;

/**
 * Data access layer for object {@link com.itborci.POJO.Subject}
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
public interface SubjectDao {

    /**
     * Saves subject into storage media
     * @param subject Subject to save
     */
    public void saveSubject(Subject subject, Context context);

    /**
     * Deletes subject from storage media
     * @param subject Subject to delete
     */
    public void deleteSubject(Subject subject, Context context);

    public Subject getSubjectByWeekDayHour(int week, int day, int hour, Context context);
}
