/*
 * Copyright (c) 2021 Grizzly Software, https://grizzlysoftware.pl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Bartosz Pawłowski on 2019-07-01.
 */
public class Table extends CloudEntity {

    @JsonProperty("_tableGroupId")
    public Long tableGroupId;

    @JsonProperty("_sellerId")
    public Long sellerId;

    @JsonProperty("enabled")
    public Boolean isEnabled = true;

    @JsonProperty("display")
    public Boolean shouldBeDisplayed = true;

    @JsonProperty("locationName")
    public String locationName;

    @JsonProperty("positionX")
    public Integer positionX;

    @JsonProperty("positionY")
    public Integer positionY;

    @JsonProperty("rotation")
    public Integer rotationAngle;

    @JsonProperty("seats")
    public Integer seatsCount;

    @JsonProperty("type")
    public Type type;

    @JsonProperty("tags")
    public Collection<String> tags = new HashSet<>();

    public enum Type {
        SQUARE,
        SQUARE6,
        CIRCLE2,
        CIRCLE4,
        DELIVERY,
        CHAIR_SINGLE,
        ROUND,
        DOOR,
        GENERIC,
        CAR1,
        CAR2
    }
}
