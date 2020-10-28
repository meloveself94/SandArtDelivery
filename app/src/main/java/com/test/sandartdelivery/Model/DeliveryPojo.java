package com.test.sandartdelivery.Model;

import com.google.gson.annotations.SerializedName;

public class DeliveryPojo {

    private int id;

    private String imageUrl;

    private boolean isClose;

    private String closeLabel;

    private String productName;

    private String productDesc;

    @SerializedName("star")
    private double starRating;

    private String distance;

    private String promoDesc;

    @SerializedName("outletAround")
    private int outletAmount;

    public DeliveryPojo (int id, String imageUrl, boolean isClose, String closeLabel, String productName,
                         String productDesc, double starRating, String distance, String promoDesc, int outletAmount) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.isClose = isClose;
        this.closeLabel = closeLabel;
        this.productName = productName;
        this.productDesc = productDesc;
        this.starRating = starRating;
        this.distance = distance;
        this.promoDesc = promoDesc;
        this.outletAmount = outletAmount;

    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isClose() {
        return isClose;
    }

    public String getCloseLabel() {
        return closeLabel;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public double getStarRating() {
        return starRating;
    }

    public String getDistance() {
        return distance;
    }

    public String getPromoDesc() {
        return promoDesc;
    }

    public int getOutletAmount() {
        return outletAmount;
    }
}
