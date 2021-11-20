package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultPage<T> {
    @JsonProperty("currentPage")
    public int page;

    @JsonProperty("perPage")
    public int pageSize;

    @JsonProperty("totalItemsCount")
    public int totalItemsCount;

    @JsonProperty("totalItemsOnPage")
    public int itemsInPage;

    @JsonProperty("firstPage")
    public int firstPage;

    @JsonProperty("lastPage")
    public int lastPage;

    @JsonProperty("data")
    public Collection<T> data;

}
