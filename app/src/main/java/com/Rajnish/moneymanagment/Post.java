package com.Rajnish.moneymanagment;

public class Post {


    public String getTodayEarning() {
        return TodayEarning;
    }

    public void setTodayEarning(String todayEarning) {
        TodayEarning = todayEarning;
    }

    public String getTodayExpense() {
        return TodayExpense;
    }

    public void setTodayExpense(String expense, String todayExpense) {
        TodayExpense = todayExpense;
    }

    public String getTotalCash() {
        return TotalCash;
    }

    public void setTotalCash(String totalCash) {
        TotalCash = totalCash;
    }

    public String getThisMonthEarning() {
        return ThisMonthEarning;
    }

    public void setThisMonthEarning(String thisMonthEarning) {
        ThisMonthEarning = thisMonthEarning;
    }

    public String getThisMontExpense() {
        return ThisMontExpense;
    }

    public void setThisMontExpense(String thisMonthExpense, String thisMontExpense) {
        ThisMontExpense = thisMontExpense;
    }

    public String getTotalSavingToday() {
        return TotalSavingToday;
    }

    public void setTotalSavingToday(String totalSavingToday) {
        TotalSavingToday = totalSavingToday;
    }

    public String getTotalExpenseThisMonth() {
        return TotalExpenseThisMonth;
    }

    public void setTotalExpenseThisMonth(String totalExpenseThisMonth) {
        TotalExpenseThisMonth = totalExpenseThisMonth;
    }

    public Post(String todayEarning, String todayExpense, String totalCash, String thisMonthEarning, String thisMontExpense, String totalSavingToday, String totalExpenseThisMonth) {
        TodayEarning = todayEarning;
        TodayExpense = todayExpense;
        TotalCash = totalCash;
        ThisMonthEarning = thisMonthEarning;
        ThisMontExpense = thisMontExpense;
        TotalSavingToday = totalSavingToday;
        TotalExpenseThisMonth = totalExpenseThisMonth;
    }

    private String TodayEarning;
    private String TodayExpense;
    private String TotalCash;
    private String ThisMonthEarning;
    private String ThisMontExpense;
    private String TotalSavingToday;
    private String TotalExpenseThisMonth;

    public Post(String monthdate) {
        Monthdate = monthdate;
    }

    public String getMonthdate() {
        return Monthdate;
    }

    public void setMonthdate(String s, String monthdate) {
        Monthdate = monthdate;
    }

    private String Monthdate;

    public Post(String type, String date) {
        Type = type;
        Date = date;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate( String date) {
        Date = date;
    }

    private String Type;
    private String Date;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private  String time;

    public Post() {



    }






}
