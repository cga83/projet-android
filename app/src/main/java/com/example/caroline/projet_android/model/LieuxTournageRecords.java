package com.example.caroline.projet_android.model;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.List;

public class LieuxTournageRecords {

    @SerializedName("records")
    private List<LieuxTournageRecord> tournages;

    public List<LieuxTournageRecord> getTournages() {
        return tournages;
    }

    public void setTournages(List<LieuxTournageRecord> tournages) {
        this.tournages = tournages;
    }
}
