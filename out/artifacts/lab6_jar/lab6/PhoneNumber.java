public class PhoneNumber {

    private String areaCode;
    private String exchange;
    private String line;

    private static String areaCodeDesignatorLeft = "(";
    private static String areaCodeDesignatorRight = ")";
    private static String areaCodeDesignatorPost = " ";
    private static String exchangeLineSeparator = "-";

    //non-static variables

    public void setAreaCode(String newAreaCode){
        this.areaCode = newAreaCode;
    }

    public String getAreaCode(){
        return this.areaCode;
    }

    public void setExchange(String newExchange){
        this.exchange = newExchange;
    }

    public String getExchange(){
        return this.exchange;
    }

    public void setLine(String newLine){
        this.line = newLine;
    }

    public String getLine(){
        return this.line;
    }

    //static variables

    public static void setAreaCodeDesignatorLeft(String newAreaCodeDesignatorLeft){
        areaCodeDesignatorLeft = newAreaCodeDesignatorLeft;
    }

    public static String getAreaCodeDesignatorLeft(){
        return areaCodeDesignatorLeft;
    }

    public static void setAreaCodeDesignatorRight(String newAreaCodeDesignatorRight){
        areaCodeDesignatorRight = newAreaCodeDesignatorRight;
    }

    public static String getAreaCodeDesignatorRight(){
        return areaCodeDesignatorRight;
    }

    public static void setAreaCodeDesignatorPost(String newAreaCodeDesignatorPost){
        areaCodeDesignatorPost = newAreaCodeDesignatorPost;
    }

    public static String getAreaCodeDesignatorPost(){
        return areaCodeDesignatorPost;
    }

    public static void setExchangeLineSeparator(String newExchangeLineSeparator){
        exchangeLineSeparator = newExchangeLineSeparator;
    }

    public static String getExchangeLineSeparator(){
        return exchangeLineSeparator;
    }

    public String prettyPrint(){
        return getAreaCodeDesignatorLeft() + getAreaCode() + getAreaCodeDesignatorRight() + getAreaCodeDesignatorPost() + getExchange() + getExchangeLineSeparator() + getLine();
    }

    //constructor

    public PhoneNumber(){
        areaCode = "510";
        exchange = "000";
        line = "9999";
    }

    public PhoneNumber(String areaCode, String exchange, String line){
        setAreaCode(areaCode);
        setExchange(exchange);
        setLine(line);
    }

    public PhoneNumber(String number){
        setAreaCode(number.substring(0,3));
        setExchange(number.substring(3,6));
        setLine(number.substring(6));
    }

}