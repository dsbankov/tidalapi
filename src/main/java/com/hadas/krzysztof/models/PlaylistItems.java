package com.hadas.krzysztof.models;

import java.util.List;

public class PlaylistItems {
    private Integer limit;
    private Integer offset;
    private Integer totalNumberOfItems;
    private List<TrackItem> items;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotalNumberOfItems() {
        return totalNumberOfItems;
    }

    public void setTotalNumberOfItems(Integer totalNumberOfItems) {
        this.totalNumberOfItems = totalNumberOfItems;
    }

    public List<TrackItem> getItems() {
        return items;
    }

    public void setItems(List<TrackItem> items) {
        this.items = items;
    }
}
