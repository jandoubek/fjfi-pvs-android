/*
 * $HeadURL$
 * $Date$
 * $Revision$
 *
 * All Rights Reserved.
 */
package com.itborci.layers;

import android.graphics.Color;
import com.itborci.POJO.Subject;
import com.itborci.enums.DayOfWeek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Mock implementation of Subject DL
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
class SubjectDLMock implements SubjectDL {

    /** List containig all subjects. It is handled as storage. */
    private final List<Subject> listAll;

    private final List<Subject> listMonday;
    private final List<Subject> listTuesday;
    private final List<Subject> listWednesday;
    private final List<Subject> listThursday;
    private final List<Subject> listFriday;

    SubjectDLMock() {


        // create individual lists
        long id = 1;
        listMonday = Collections.unmodifiableList(Arrays.asList(
                new Subject(id++, "VAMB", "T-210", "Beneš", DayOfWeek.MONDAY, 1, 1, Color.GREEN, true),
                new Subject(id++, "ZTGB", "T-207", "Ambrož", DayOfWeek.MONDAY, 2, 2, Color.GREEN, false)
        ));

        listTuesday = Collections.unmodifiableList(Arrays.asList(
                new Subject(id++, "FIMA", "T-211", "Hora", DayOfWeek.TUESDAY, 0, 0, Color.GREEN, false),
                new Subject(id++, "SQL", "T-014", "Kukal", DayOfWeek.TUESDAY, 1, 1, Color.YELLOW, true),
                new Subject(id++, "MOPR", "T-014", "Borovička", DayOfWeek.TUESDAY, 2, 2, Color.YELLOW, false),
                new Subject(id++, "MOPRcv", "T-014", "Borovička", DayOfWeek.TUESDAY, 3, 3, Color.YELLOW, true),
                new Subject(id++, "PVS", "T-115", "Doubek", DayOfWeek.TUESDAY, 4, 4, Color.YELLOW, true)
        ));

        listWednesday = Collections.unmodifiableList(Arrays.asList(
                new Subject(id++, "FIMA", "T-211", "Hora", DayOfWeek.WEDNESDAY, 0, 0, Color.GREEN, false),
                new Subject(id++, "SQL", "T-014", "Kukal", DayOfWeek.WEDNESDAY, 1, 1, Color.YELLOW, true),
                new Subject(id++, "MOPR", "T-014", "Borovička", DayOfWeek.WEDNESDAY, 2, 2, Color.YELLOW, false),
                new Subject(id++, "MOPRcv", "T-014", "Borovička", DayOfWeek.WEDNESDAY, 3, 3, Color.YELLOW, true),
                new Subject(id++, "PVS", "T-115", "Doubek", DayOfWeek.WEDNESDAY, 4, 4, Color.YELLOW, true)
        ));

        listThursday = Collections.unmodifiableList(Arrays.asList(
                new Subject(id++, "SROZ", "KFE-121", "Flusser", DayOfWeek.THURSDAY, 2, 2, Color.YELLOW, false)
        ));

        listFriday = Collections.unmodifiableList(Arrays.asList(
                new Subject(id++, "PMU", "T-204", "Hakl", DayOfWeek.FRIDAY, 0, 0, Color.GREEN, true),
                new Subject(id++, "NAH", "T-212", "Veverka", DayOfWeek.FRIDAY, 1, 2, Color.GREEN, false),
                new Subject(id++, "TSLO", "T-211", "Majerech", DayOfWeek.FRIDAY, 3, 3, Color.GREEN, true)
        ));

        // add them to all list
        listAll = new ArrayList<Subject>();
        listAll.addAll(listMonday);
        listAll.addAll(listTuesday);
        listAll.addAll(listWednesday);
        listAll.addAll(listThursday);
        listAll.addAll(listFriday);
    }

    @Override
    public void saveSubject(Subject subject) {
        // do nothing, data is stored in memory only
    }

    @Override
    public void createSubject(Subject subject) {
        // add subject to storage
        listAll.add(subject);
    }

    @Override
    public void deleteSubject(Subject subject) {
        // remove subject from storage
        listAll.remove(subject);
    }

    @Override
    public List<Subject> getSubjectsByDay(final DayOfWeek dayOfWeek) {
        List<Subject> result = new ArrayList<Subject>();

        // filter list for given dayOfWeek
        for (Subject subject : listAll) {
            if (dayOfWeek.equals(subject.getDay())) {
                result.add(subject);
            }
        }

        return result;
    }
}
