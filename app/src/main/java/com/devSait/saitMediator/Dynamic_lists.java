package com.devSait.saitMediator;

public class Dynamic_lists {
    String Name, Description, Author, Time;

    public Dynamic_lists(){

    }

    public Dynamic_lists(String name, String description, String author, String time) {
        Name = name;
        Description = description;
        Author = author;
        Time = time;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getAuthor() {
        return Author;
    }

    public String getTime() {
        return Time;
    }
}