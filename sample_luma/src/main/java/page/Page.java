package page;


public enum Page {

    LUMA_MAIN("https://magento.softwaretestingboard.com"),
    LUMA_NEW(LUMA_MAIN.getValue() + "/what-is-new.html"),
    LUMA_WOMEN(LUMA_MAIN.getValue() + "/women"),
    LUMA_MEN(LUMA_MAIN.getValue() + "/men");

    String value;

    Page(String val) {
        value = val;
    }

    public String getValue() {
        return value;
    }

}