package com.example.caroline.projet_android.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

import com.example.caroline.projet_android.R;
import com.example.caroline.projet_android.model.LieuxTournageClusterItem;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

public class LongMetrageClusterRender extends DefaultClusterRenderer<LieuxTournageClusterItem> {

    private final Context mContext;
    private final IconGenerator mClusterIconGenerator;

    public LongMetrageClusterRender(Context context, GoogleMap map, ClusterManager<LieuxTournageClusterItem> clusterManager) {
        super(context, map, clusterManager);
        mContext = context;
        mClusterIconGenerator = new IconGenerator(mContext.getApplicationContext());
    }

    @Override
    protected void onBeforeClusterItemRendered(LieuxTournageClusterItem item, MarkerOptions markerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.long_metrage));
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<LieuxTournageClusterItem> cluster, MarkerOptions markerOptions) {
        mClusterIconGenerator.setBackground(
                ContextCompat.getDrawable(mContext, R.drawable.cluster_long_metrage));

        mClusterIconGenerator.setTextAppearance(R.style.AppTheme_WhiteTextAppearance);

        final Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
    }


    @Override
    protected boolean shouldRenderAsCluster(Cluster<LieuxTournageClusterItem> cluster){
        // Cluster dès qu'il y a plus de deux marqueurs
        return cluster.getSize() > 2;
    }

}