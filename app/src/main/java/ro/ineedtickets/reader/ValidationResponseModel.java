package ro.ineedtickets.reader;

public class ValidationResponseModel {

    public boolean isTicketValid;

    public boolean hasError;

    public String errorMessage;

    public String eventName;

    public String areaName;

    public String locationName;

    public String eventDate;

    public ValidationResponseModel(boolean isTicketValid, boolean hasError, String errorMessage, String eventName, String areaName, String locationName, String eventDate) {
        this.isTicketValid = isTicketValid;
        this.hasError = hasError;
        this.errorMessage = errorMessage;
        this.eventName = eventName;
        this.areaName = areaName;
        this.locationName = locationName;
        this.eventDate = eventDate;
    }
}
