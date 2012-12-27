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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Mock implementation of Subject DL
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr ÃšjezdskÃ½</a>
 * @version $Id$
 */
class SubjectDaoMock implements SubjectDao {

    /** List containig all subjects. It is handled as storage. */
    private static final List<Subject> listAll;

    private static final List<Subject> listMonday;
    private static final List<Subject> listTuesday;
    private static final List<Subject> listWednesday;
    private static final List<Subject> listThursday;
    private static final List<Subject> listFriday;

    private static long id = 1;

    static {
        int color1 = 0xFF75FF7A; //Color.GREEN
        int color2 = 0xFFFFF675; //Color.YELLOW

        // create individual lists
        listMonday =
                Collections.unmodifiableList(
                Arrays.asList(
                Subject.create(id++, "VAMB", "T-210", "Beneš", 0, 0, 1, 1, color1, true),
                Subject.create(id++, "ZTGB", "T-207", "Ambroš", 0, 0, 2, 2, color1, false)
        )
        );

        listTuesday = Collections.unmodifiableList(Arrays.asList(
                Subject.create(id++, "FIMA", "T-211", "Hora", 0, 1, 0, 0, color1, false),
                Subject.create(id++, "SQL", "T-014", "Kukal", 0, 1, 1, 1, color2, true),
                Subject.create(id++, "MOPR", "T-014", "Borovièka", 0, 1, 2, 2, color2, false),
                Subject.create(id++, "MOPRcv", "T-014", "Borovièka", 0, 1, 3, 3, color2, true),
                Subject.create(id++, "PVS", "T-115", "Doubek", 0, 1, 4, 4, color2, true)
        ));

        listWednesday = Collections.unmodifiableList(Arrays.asList(
                Subject.create(id++, "FIMA", "T-211", "Hora", 0, 2, 0, 0, color1, false),
                Subject.create(id++, "SQL", "T-014", "Kukal", 0, 2, 1, 1, color2, true),
                Subject.create(id++, "MOPR", "T-014", "Borovièka", 0, 2, 2, 2, color2, false),
                Subject.create(id++, "MOPRcv", "T-014", "Borovièka", 0, 2, 3, 3, color2, true),
                Subject.create(id++, "PVS", "T-115", "Doubek", 0, 2, 4, 4, color2, true)
        ));

        listThursday = Collections.unmodifiableList(Arrays.asList(
                Subject.create(id++, "SROZ", "KFE-121", "Flusser", 0, 3, 2, 2, color2, false)
        ));

        listFriday = Collections.unmodifiableList(Arrays.asList(
                Subject.create(id++, "PMU", "T-204", "Hakl", 0, 4, 0, 0, color1, true),
                Subject.create(id++, "NAH", "T-212", "Veverka", 0, 4, 1, 2, color1, false),
                Subject.create(id++, "TSLO", "T-211", "Majerech", 0, 4, 3, 3, color1, true)
        ));

        // add them to all list
        listAll = new ArrayList<Subject>();
//        listAll.addAll(listMonday);
//        listAll.addAll(listTuesday);
//        listAll.addAll(listWednesday);
//        listAll.addAll(listThursday);
//        listAll.addAll(listFriday);
    }

    @Override
    public void saveSubject(Subject subject, Context context) {
        if (subject.getId() == null) {
            subject.setId(id++);
            listAll.add(subject);
        }
    }

    @Override
    public void deleteSubject(Subject subject, Context context) {
        // remove subject from storage
        listAll.remove(subject);
    }

    @Override
    public Subject getSubjectByWeekDayHour(int week, int day, int hour, Context context) {
        for (Subject subject : listAll) {
            if (week == subject.getWeek() &&
                    day == subject.getDay() &&
                    hour == subject.getHour())
                return subject;
        }
        return null;
    }
}
