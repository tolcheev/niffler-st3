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
import static com.codeborne.selenide.Selenide.$;
import static guru.qa.niffler.jupiter.User.UserType.INVITATION_SENT;
import static guru.qa.niffler.jupiter.User.UserType.WITH_FRIENDS;
import static io.qameta.allure.Allure.step;

public class DifferentUsersWebTest extends BaseWebTest {

    @BeforeEach
    @Step("User auth before test")
    void doLogin(@User(userType = WITH_FRIENDS) UserJson userForTest) {
        Selenide.open("http://127.0.0.1:3000/main");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(userForTest.getUsername());
        $("input[name='password']").setValue(userForTest.getPassword());
        $("button[type='submit']").click();
    }

    @Test
    @AllureId("107")
    void differentUsers(@User(userType = WITH_FRIENDS) UserJson userForTest,
                        @User(userType = INVITATION_SENT) UserJson userForTestAnother) {
        System.out.println(userForTest.getUsername());
        System.out.println(userForTestAnother.getUsername());

//        step("Click on 'Friends' button", () -> {
//            $("a[href='/friends']").click();
//        });
//
//        step("Check text 'You are friends' is visible", () -> {
//            $(byText("You are friends")).shouldBe(visible);
//        });
    }

}
