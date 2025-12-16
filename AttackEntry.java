public class AttackEntry {
    public String countryName;
    public String ipPrefix;
    public String ISP;
    public String timezone;

    public AttackEntry(String countryName, String ipPrefix, String ISP, String timezone) {
        this.countryName = countryName;
        this.ipPrefix = ipPrefix;
        this.ISP = ISP;
        this.timezone = timezone;
    }
}

