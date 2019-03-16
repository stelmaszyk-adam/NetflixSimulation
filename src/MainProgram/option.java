package MainProgram;

public enum option {

    Serial , Film, LiveStreaming, Customer, Seller;

    private option(){}

    public String value()
    {
        return name();
    }

    public static option fromvalue(String v)
    {
        return valueOf(v);
    }

}
