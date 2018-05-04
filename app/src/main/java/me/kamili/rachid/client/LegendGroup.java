package me.kamili.rachid.client;

import android.os.Parcel;
import android.os.Parcelable;


public class LegendGroup implements Parcelable {
    private long id;
    private String name;
    private String slogan;
    private String description;
    private String logo;
    private String image;

    public LegendGroup(String name, String slogan, String description, String logo, String image) {
        this.name = name;
        this.slogan = slogan;
        this.description = description;
        this.logo = logo;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    protected LegendGroup(Parcel in) {
        id = in.readLong();
        name = in.readString();
        slogan = in.readString();
        description = in.readString();
        logo = in.readString();
        image = in.readString();
    }

    public static final Creator<LegendGroup> CREATOR = new Creator<LegendGroup>() {
        @Override
        public LegendGroup createFromParcel(Parcel in) {
            return new LegendGroup(in);
        }

        @Override
        public LegendGroup[] newArray(int size) {
            return new LegendGroup[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(slogan);
        dest.writeString(description);
        dest.writeString(logo);
        dest.writeString(image);
    }

}
