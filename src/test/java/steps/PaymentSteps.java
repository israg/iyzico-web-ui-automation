package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import pages.PaymentPage;

public class PaymentSteps {

    PaymentPage paymentPage;

    @Before
    public void setUp() {
        paymentPage = new PaymentPage();
    }

    @After
    public void tearDown() {
        paymentPage.tearDown();
    }

    @And("Fill card infos")
    public void fillCardInfos() {
        paymentPage.fillCardDetails();
    }

    @And("If OTP is available fill OTP value")
    public void ifOTPIsAvailableFillOTPValue() {
        paymentPage.fillOTP();
    }
}
