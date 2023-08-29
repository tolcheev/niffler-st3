package guru.qa.niffler.test;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.jupiter.User;
import guru.qa.niffler.model.UserJson;
import io.qameta.allure.AllureId;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class FriendsWebTest extends BaseWebTest {

    @BeforeEach
    @Step("User auth before test")
    void doLogin(@User(userType = User.UserType.WITH_FRIENDS) UserJson userForTest) {
        Selenide.open("http://127.0.0.1:3000/main");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(userForTest.getUsername());
        $("input[name='password']").setValue(userForTest.getPassword());
        $("button[type='submit']").click();
    }

    @Test
    @AllureId("101")
    void friendShouldBeDisplayedInTable() {
        step("Click on 'Friends' button", () -> $("a[href='/friends']").click());

        step("Check text 'You are friends' is visible", () -> {
            $(byText("You are friends")).shouldBe(visible);
        });
    }

    @Test
    @AllureId("102")
    void friendShouldBeDisplayedInTable2(@User(userType = User.UserType.WITH_FRIENDS) UserJson userForTest) {
        step("Click on 'Friends' button", () -> $("a[href='/friends']").click());

        step("Check text 'You are friends' is visible", () -> {
            $(byText("You are friends")).shouldBe(visible);
        });
    }
}
