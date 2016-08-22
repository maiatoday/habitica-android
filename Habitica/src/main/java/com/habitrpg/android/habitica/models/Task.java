package com.habitrpg.android.habitica.models;

import com.google.gson.annotations.SerializedName;


import com.habitrpg.android.habitica.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class Task implements RealmModel {
    public static final String TYPE_HABIT = "habit";
    public static final String TYPE_TODO = "todo";
    public static final String TYPE_DAILY = "daily";
    public static final String TYPE_REWARD = "reward";
    public static final String FREQUENCY_WEEKLY = "weekly";
    public static final String FREQUENCY_DAILY = "daily";
    public static final String ATTRIBUTE_STRENGTH = "str";
    public static final String ATTRIBUTE_CONSTITUTION = "con";
    public static final String ATTRIBUTE_INTELLIGENCE = "int";
    public static final String ATTRIBUTE_PERCEPTION = "per";


    @PrimaryKey
    @SerializedName("_id")
    String id;

    @SerializedName("userId")
    public String user_id;

    public Float priority;

    public String text, notes, attribute, type;

    public double value;

    public Date dateCreated;

    public int position;

    //Habits
    public Boolean up, down;


    //todos/dailies
    public boolean completed;

    public RealmList<ChecklistItem> checklist;


    //dailies
    public String frequency;

    public Integer everyX, streak;

    public Date startDate;

    public Days repeat;


    //todos
    @SerializedName("date")
    public Date duedate;

    // used for buyable items
    public String specialTag;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }
    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
    /**
     * @return the priority
     */
    public Float getPriority() {
        return priority;
    }
    /**
     * @param i the priority to set
     */
    public void setPriority(Float i) {
        this.priority = i;
    }
    /**
     * @return the text
     */
    public String getText() {
        return text;
    }
    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }
    /**
     * @param value the value to set
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * To be allowed to set int value without problems
     * @param value the value to set
     */
    public void setValue(double value) {
        this.setValue(Double.valueOf(value));
    }


    /**
     * Returns a string of the type of the Task
     * @return the string of the Item type
     */
    public String getType() {return this.type;}

    public void setType(String type) {this.type = type;}

    /**
     * @return whether or not the habit can be "upped"
     */
    public boolean getUp() {
        if (up == null) { return false; }
        return up;
    }
    /**
     * Set the Up value
     * @param up
     */
    public void setUp(Boolean up) {
        this.up = up;
    }
    /**
     * @return whether or not the habit can be "down"
     */
    public boolean getDown() {
        if (down == null) { return false; }
        return down;
    }
    /**
     * Set the Down value
     * @param down
     */
    public void setDown(Boolean down) {
        this.down = down;
    }


    public boolean getCompleted() {
        return completed;
    }
    /**
     *  Set whether or not the daily is completed
     * @param completed
     */
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getFrequency() {
        if (frequency == null) { return FREQUENCY_WEEKLY; }
        return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    public Integer getEveryX() {
        if (everyX == null) { return 1; }
        return everyX; }
    public void setEveryX(Integer everyX) { this.everyX = everyX; }

    public Date getStartDate() {
        if (startDate == null) { return new Date(); }
        return startDate;
    }
    public void setStartDate(Date startDate) {this.startDate = startDate; }

    /**
     * @return the streak
     */
    public int getStreak() {
        if (streak == null) {
            return 0;
        }
        return streak;
    }
    /**
     * @param streak the streak to set
     */
    public void setStreak(Integer streak) {
        this.streak = streak;
    }


    /**
     * @return the due date
     */
    public Date getDueDate() {
        return this.duedate;
    }

    /**
     * Set the due date
     * @param duedate the date to set
     */
    public void setDueDate(Date duedate) {
        this.duedate = duedate;
    }

    /**
     * @return the attribute
     */
    public String getAttribute() {
        return attribute;
    }
    /**
     * @param attribute the attribute to set
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Days getRepeat() {
        return repeat;
    }

    public void setRepeat(Days repeat) {
        repeat.setTaskId(getId());
        this.repeat = repeat;
    }

    public RealmList<ChecklistItem> getChecklist() {
        return checklist;
    }

    public void setChecklist(RealmList<ChecklistItem> checklist) {
        this.checklist = checklist;
    }

    public int getLightTaskColor()
    {
        if (this.value < -20)
            return R.color.worst_100;
        if (this.value < -10)
            return R.color.worse_100;
        if (this.value < -1)
            return R.color.bad_100;
        if (this.value < 1)
            return R.color.neutral_100;
        if (this.value < 5)
            return R.color.good_100;
        if (this.value < 10)
            return R.color.better_100;
        return R.color.best_100;
    }

    /**
     * Get the button color resources depending on a certain score
     *
     * @return the color resource id
     */
    public int getMediumTaskColor()
    {
        if (this.value < -20)
            return R.color.worst_50;
        if (this.value < -10)
            return R.color.worse_50;
        if (this.value < -1)
            return R.color.bad_50;
        if (this.value < 1)
            return R.color.neutral_50;
        if (this.value < 5)
            return R.color.good_50;
        if (this.value < 10)
            return R.color.better_50;

        return R.color.best_50;
    }

    /**
     * Get the button color resources depending on a certain score
     *
     * @return the color resource id
     */
    public int getDarkTaskColor()
    {
        if (this.value < -20)
            return R.color.worst_10;
        if (this.value < -10)
            return R.color.worse_10;
        if (this.value < -1)
            return R.color.bad_10;
        if (this.value < 1)
            return R.color.neutral_10;
        if (this.value < 5)
            return R.color.good_10;
        if (this.value < 10)
            return R.color.better_10;

        return R.color.best_10;
    }

    public Boolean isDue(int offset) {
        if (this.getCompleted()) {
            return true;
        }

        Calendar today = new GregorianCalendar();
        today.add(Calendar.HOUR, -offset);

        Calendar startDate = new GregorianCalendar();
        Calendar startDateAtMidnight;
        if (this.getStartDate() != null) {
            startDate.setTime(this.getStartDate());
            startDateAtMidnight = new GregorianCalendar(startDate.get(Calendar.YEAR),
                    startDate.get(Calendar.MONTH),
                    startDate.get(Calendar.DAY_OF_MONTH));

            if ( startDateAtMidnight.after(today) ) {
                return false;
            }
        } else {
            startDateAtMidnight = new GregorianCalendar(startDate.get(Calendar.YEAR),
                    startDate.get(Calendar.MONTH),
                    startDate.get(Calendar.DAY_OF_MONTH));
        }

        if (this.getFrequency().equals(FREQUENCY_DAILY)) {
            if(getEveryX() == 0){
                return false;
            }

            TimeUnit timeUnit = TimeUnit.DAYS;
            long diffInMillies = startDateAtMidnight.getTimeInMillis() - today.getTimeInMillis();
            long daySinceStart = timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
            return (daySinceStart % this.getEveryX() == 0);
        } else {
            return this.getRepeat().getForDay(today.get(Calendar.DAY_OF_WEEK));
        }
    }

    public Boolean isDisplayedActive(int offset) {
        return this.isDue(offset) && !this.completed;
    }
}
