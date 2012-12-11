/*
 * $HeadURL$
 * $Date$
 * $Revision$
 *
 * All Rights Reserved.
 */
package com.itborci.POJO;

import com.itborci.enums.DayOfWeek;

/**
 * Object holding data for Subject.
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
public class Subject {
    private long id;

    private String name;
    private String room;
    private String teacher;
    private DayOfWeek day;
    private int from;
    private int to;
    private int color;
    private boolean bell;


    public Subject(long id, String name, String room, String teacher, DayOfWeek day, int from, int to, int color, boolean bell) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.teacher = teacher;
        this.day = day;
        this.from = from;
        this.to = to;
        this.color = color;
        this.bell = bell;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isBell() {
        return bell;
    }

    public void setBell(boolean bell) {
        this.bell = bell;
    }
}
