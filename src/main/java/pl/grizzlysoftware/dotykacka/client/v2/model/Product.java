package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;

public class Product extends CloudEntity {
    @JsonProperty("_categoryId")
    public Long categoryId;

    @JsonProperty("_defaultCourseId")
    public Long defaultCourseId;

    @JsonProperty("_eetSubjectId")
    public Long eetSubjectId;

    @JsonProperty("_supplierId")
    public Long supplierId;

    @JsonProperty("name")
    public String name;

    @JsonProperty("subtitle")
    public String subtitle = "";

    @JsonProperty("vat")
    public Double vat;

    @JsonProperty("points")
    public BigDecimal points;

    @JsonProperty("priceWithVat")
    public BigDecimal priceWithVat;

    @JsonProperty("priceWithoutVat")
    public BigDecimal priceWithoutVat;

    @JsonProperty("purchasePriceWithoutVat")
    public BigDecimal purchasePriceWithoutVat;

    @JsonProperty("packagingPriceWithVat")
    public BigDecimal packagingPriceWithVat;

    @JsonProperty("ean")
    public Collection<String> eans = new HashSet<>();

    @JsonProperty("plu")
    public Collection<String> plus = new HashSet<>();

    @JsonProperty("tags")
    public Collection<String> tags = new HashSet<>();

    @JsonProperty("customizations")
    public Collection<ProductCustomization> customizations = new HashSet<>();

    @JsonProperty("ingredients")
    public Collection<ProductIngredient> ingredients = new HashSet<>();

    @JsonProperty("supplierProductCode")
    public String supplierProductCode = "";

    @JsonProperty("currency")
    public String currency;

    @JsonProperty("deliveryNoteIds")
    public String deliveryNoteIds = "";

    @JsonProperty("description")
    public String description = "";

    @JsonProperty("discountPercent")
    public String discountPercent = "0.0";

    @JsonProperty("discountPermitted")
    public Boolean isDiscountable = true;

    @JsonProperty("display")
    public Boolean shouldBeDisplayed = true;

    @JsonProperty("flags")
    public Integer flags = 0;

    @JsonProperty("hexColor")
    public String hexColor = "#000000";

    @JsonProperty("margin")
    public String margin = "";

    @JsonProperty("marginMin")
    public Double marginMin = 0.0d;

    @JsonProperty("notes")
    public Collection<String> notes = new HashSet<>();

    @JsonProperty("features")
    public Collection<String> features = new HashSet<>();

    @JsonProperty("imageUrl")
    public String imageUrl = "";

    @JsonProperty("onSale")
    public Boolean isOnSale = false;

    @JsonProperty("packageItem")
    public Double packageItem;

    @JsonProperty("packaging")
    public Double packaging;

    @JsonProperty("packagingMeasurement")
    public Double packagingMeasurement;

    @JsonProperty("requiresPriceEntry")
    public Boolean requiresPriceEntry = false;

    @JsonProperty("sortOrder")
    public Long sortOrder;

    @JsonProperty("stockDeduct")
    public Boolean isStockDeductable = true;

    @JsonProperty("stockOverdraft")
    public StockOverdraft stockOverdraft = StockOverdraft.WARN;

    @JsonProperty("unit")
    public Unit unit;

    @JsonProperty("unitMeasurement")
    public String unitMeasurement;

    public enum StockOverdraft {
        ALLOW,
        WARN,
        DISABLE
    }
}
