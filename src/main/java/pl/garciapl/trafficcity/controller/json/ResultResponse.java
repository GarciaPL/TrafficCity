package pl.garciapl.trafficcity.controller.json;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 19.12.14
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
public enum ResultResponse {
    FAIL("FAIL"), SUCCESS("SUCCESS");

    private String result;

    private ResultResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return this.result;
    }
}
