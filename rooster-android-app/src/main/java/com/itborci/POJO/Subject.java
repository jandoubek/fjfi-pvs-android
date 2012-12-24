/*
 * $HeadURL$
 * $Date$
 * $Revision$
 *
 * All Rights Reserved.
 */
package com.itborci.POJO;


/**
 * Object holding data for Subject.
 *
 * @author <a href="mailto:petr.ujezdsky@gmail.com">Petr Újezdský</a>
 * @version $Id$
 */
public class Subject {
    private Long id;

    private String name;
    private String room;
    private String teacher;
    private int week;
    private int day;
    private int hour;
    private int to;
    private int color;
    private boolean bell;

    private Subject() {
    }

    private Subject(Long id, String name, String room, String teacher, int week, int day, int hour, int to, int color, boolean bell) {
        this();

        this.id = id;
        this.name = name;
        this.room = room;
        this.teacher = teacher;
        this.week = week;
        this.day = day;
        this.hour = hour;
        this.to = to;
        this.color = color;
        this.bell = bell;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
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


    public int getBellAsInt() {
        return isBell() ? 1 : 0;
    }

    public static Subject create() {
        return new Subject();
    }

    public static Subject create(Long id, String name, String room, String teacher, int week, int day, int hour, int to, int color, boolean bell) {
        return new Subject(id, name, room, teacher, week, day, hour, to, color, bell);
    }

    public static Subject create(Long id, String name, String room, String teacher, int week, int day, int hour, int color, int bell) {
        return new Subject(id, name, room, teacher, week, day, hour, 0, color, bell != 0);
    }
}
