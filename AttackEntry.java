// Attack Entries

public class AttackEntry {
    public String countryName;
    public String ipPrefix;
    public String ISP;
    public String timezone;
    public String hint1;
    public String hint2;
    public String hint3;

    public AttackEntry(String countryName, String ipPrefix, String ISP, String timezone, String hint1, String hint2, String hint3) {
        this.countryName = countryName;
        this.ipPrefix = ipPrefix;
        this.ISP = ISP;
        this.timezone = timezone;
        this.hint1 = hint1;
        this.hint2 = hint2;
        this.hint3 = hint3;
    }
}
