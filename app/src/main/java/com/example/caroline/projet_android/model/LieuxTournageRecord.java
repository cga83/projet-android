package com.example.caroline.projet_android.model;

import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LieuxTournageRecord {

    @SerializedName("datasetid")
    private String datasetid;
    @PrimaryKey
    @SerializedName("recordid")
    private String recordid;
    @SerializedName("fields")
    private LieuxTournage fields;

    LieuxTournageRecord() {}

    LieuxTournageRecord(String datasetid, String recordid, LieuxTournage fields) {
        this.datasetid = datasetid;
        this.recordid = recordid;
        this.fields = fields;
    }

    public String getDatasetid() {
        return datasetid;
    }

    public void setDatasetid(String datasetid) {
        this.datasetid = datasetid;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public LieuxTournage getFields() {
        return fields;
    }

    public void setFields(LieuxTournage fields) {
        this.fields = fields;
    }

}
