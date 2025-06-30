package com.example.recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
    private String name;
    private int imageResId;
    private String description;
    private double price;
    private String phone;
    private String address;
    private String website;

    public Food(String name, int imageResId, String description, double price, String phone, String address, String website) {
        this.name = name;
        this.imageResId = imageResId;
        this.description = description;
        this.price = price;
        this.phone = phone;
        this.address = address;
        this.website = website;
    }

    protected Food(Parcel in) {
        name = in.readString();
        imageResId = in.readInt();
        description = in.readString();
        price = in.readDouble();
        phone = in.readString();
        address = in.readString();
        website = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(imageResId);
        parcel.writeString(description);
        parcel.writeDouble(price);
        parcel.writeString(phone);
        parcel.writeString(address);
        parcel.writeString(website);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getWebsite() {
        return website;
    }
}
