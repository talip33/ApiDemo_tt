package utils;

public enum Device {
    REDMI_9("b6c1ca2e0407",
            "10",
            "Redmi 9",
            "Android"),
    PIXEL_2("emulator-5554",
            "11",
            "Pixel_2",
            "Android"),
    PIXEL_33("emulator-5556",
            "11",
            "Pixel_33",
            "Android");



//PIXEL_2("emulator-5554","11","Pixel_2","Android");
    public String udid;
    public String version;
    public String deviceName;
    public String platformName;

    Device(String udid, String version, String deviceName, String platformName) {
        this.udid = udid;
        this.version = version;
        this.deviceName = deviceName;
        this.platformName = platformName;
    }
}
