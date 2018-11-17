package com.example.caroline.projet_android.model;

import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


public class LieuxTournageRecord {

    @SerializedName("datasetid")
    private String datasetid;
    @PrimaryKey
    @SerializedName("recordid")
    private String recordid;
    @SerializedName("fields")
    private LieuxTournage fields;

    @SerializedName("geometry")
    private Geometry geometry;

    LieuxTournageRecord() {}

    LieuxTournageRecord(String datasetid, String recordid, LieuxTournage fields, Geometry geometry) {
        this.datasetid = datasetid;
        this.recordid = recordid;
        this.fields = fields;
        this.geometry  = geometry;
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

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
