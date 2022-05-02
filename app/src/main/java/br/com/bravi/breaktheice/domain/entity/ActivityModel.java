package br.com.bravi.breaktheice.domain.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
@Entity(tableName = "activity")
public class ActivityModel {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name = "participants")
    @SerializedName("participants")
    private int _participants;

    @ColumnInfo(name = "activity")
    @SerializedName("activity")
    private String _activity;

    @ColumnInfo(name = "key")
    @SerializedName("key")
    private String _key;

    @ColumnInfo(name = "link")
    @SerializedName("link")
    private String _link;

    @ColumnInfo(name = "type")
    @SerializedName("type")
    private String _type;

    @ColumnInfo(name = "accessibility")
    @SerializedName("accessibility")
    private float _accessibility;

    @ColumnInfo(name = "price")
    @SerializedName("price")
    private float _price;

    public ActivityModel(
            int id,
            int participants,
            String activity,
            String key,
            String link,
            String type,
            float accessibility,
            float price
    ) {
        _id = id;
        _participants = participants;
        _activity = activity;
        _key = key;
        _link = link;
        _type = type;
        _accessibility = accessibility;
        _price = price;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public int getParticipants() {
        return _participants;
    }

    public void setParticipants(int participants) {
        _participants = participants;
    }

    public String getActivity() {
        return _activity;
    }

    public void setActivity(String activity) {
        _activity = activity;
    }

    public String getKey() {
        return _key;
    }

    public void setKey(String key) {
        _key = key;
    }

    public String getLink() {
        return _link;
    }

    public void setLink(String link) {
        _link = link;
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        _type = type;
    }

    public float getAccessibility() {
        return _accessibility;
    }

    public void setAccessibility(float accessibility) {
        _accessibility = accessibility;
    }

    public float getPrice() {
        return _price;
    }

    public void setPrice(float price) {
        _price = price;
    }

    @NonNull
    public String toString() {
        return "[id]: " + _id + ", [participants]: " + _participants + ", [activity]: " + _activity + ", [key]: " + _key + ", [link]: " + _link + ", [type]: " + _type + ", [accessibility]: " + _accessibility + ", [price]: " + _price;
    }
}
