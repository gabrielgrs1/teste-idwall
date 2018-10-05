
package gabrielgrs.com.br.provaidwall.service.api.feed;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FeedDto implements Serializable {

    @SerializedName("category")
    private String category;

    @SerializedName("list")
    private List<String> list = new ArrayList<>();


    public FeedDto() {
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

    @Override
    public String toString() {
        return "FeedDto{" +
                "category='" + category + '\'' +
                ", list=" + list +
                '}';
    }


}