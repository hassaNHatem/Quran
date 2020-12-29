package com.example.quranproject.quran.API.Model;

import java.io.Serializable;

public class AzkarItem implements Serializable {
    public String title,description;
   public String id;
    public AzkarItem(String id, String title, String description) {
        this.id=id;
        this.title = title;
        this.description = description;
    }
    public AzkarItem(String title, String description) {

        this.title = title;
        this.description = description;
    }
}
