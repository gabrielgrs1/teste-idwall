
package gabrielgrs.com.br.provaidwall.service.api.feed;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FeedDto implements Parcelable {

    @SerializedName("category")
    private String category;

    @SerializedName("list")
    private List<String> list = new ArrayList<>();


    private FeedDto(Parcel in) {
        in.readStringList(list);
        category = in.readString();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<FeedDto> CREATOR = new Parcelable.Creator<FeedDto>() {
        @Override
        public FeedDto createFromParcel(Parcel in) {
            return new FeedDto(in);
        }

        @Override
        public FeedDto[] newArray(int size) {
            return new FeedDto[size];
        }
    };

    @Override
    public String toString() {
        return "FeedDto{" +
                "category='" + category + '\'' +
                ", list=" + list +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(category);
        parcel.writeStringList(list);
    }
}