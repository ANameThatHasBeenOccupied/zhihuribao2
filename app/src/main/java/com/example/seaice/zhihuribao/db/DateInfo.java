package com.example.seaice.zhihuribao.db;

import org.litepal.crud.DataSupport;

import java.util.zip.Inflater;

/**
 * Created by seaice on 2016/8/25.
 */
public class DateInfo extends DataSupport {
    private String pos;
    private String date;

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
