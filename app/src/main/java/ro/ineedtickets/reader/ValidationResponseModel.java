package ro.ineedtickets.reader;

import java.util.Date;

public class ValidationResponseModel {

    public boolean isValid;

    public boolean hasError;

    public String errorMessage;

    public String eventName;

    public String areaName;

    public String locationName;

    public String eventDate;

    public ValidationResponseModel(boolean isValid, boolean hasError, String errorMessage, String eventName, String areaName, String locationName, String eventDate) {
        this.isValid = isValid;
        this.hasError = hasError;
        this.errorMessage = errorMessage;
        this.eventName = eventName;
        this.areaName = areaName;
        this.locationName = locationName;
        this.eventDate = eventDate;
    }
}
