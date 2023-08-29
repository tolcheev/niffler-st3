package guru.qa.niffler.test;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.jupiter.User;
import guru.qa.niffler.model.UserJson;
import io.qameta.allure.AllureId;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class InvitationSentWebTest extends BaseWebTest {

    private static void run() {
        $(byText("Pending invitation")).shouldBe(visible);
    }

    @BeforeEach
    @Step("User auth before test")
    void doLogin(@User(userType = User.UserType.INVITATION_SENT) UserJson userForTest) {
        Selenide.open("http://127.0.0.1:3000/main");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(userForTest.getUsername());
        $("input[name='password']").setValue(userForTest.getPassword());
        $("button[type='submit']").click();
    }

    @Test
    @AllureId("105")
    void friendInvitationSentShouldBeDisplayed(@User(userType = User.UserType.INVITATION_SENT) UserJson userForTest) {
        step("Click 'All people' button", () -> $("a[href='/people']").click());

        step("Check text 'Pending invitation' is visible", InvitationSentWebTest::run);
    }

    @Test
    @AllureId("106")
    void friendInvitationSentShouldBeDisplayed2(@User(userType = User.UserType.INVITATION_SENT) UserJson userForTest) {
        step("Click 'All people' button", () -> $("a[href='/people']").click());

        step("Check text 'Pending invitation' is visible", () -> {
            $(byText("Pending invitation")).shouldBe(visible);
        });
    }

}
