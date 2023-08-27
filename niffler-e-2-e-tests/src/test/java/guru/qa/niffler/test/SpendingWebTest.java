package guru.qa.niffler.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.niffler.jupiter.Category;
import guru.qa.niffler.jupiter.Spend;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import io.qameta.allure.AllureId;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class SpendingWebTest {

    static {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1980x1024";
    }

    private static final String user = "vlad";
    private static final String password = "123qwe";

    @BeforeEach
    @Step("User auth before test")
    void doLogin() {
        Selenide.open("http://127.0.0.1:3000/main");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(user);
        $("input[name='password']").setValue(password);
        $("button[type='submit']").click();
    }

    @Category(
            username = user,
            category = "продукты"
    )
    @Spend(
            username = user,
            description = "Кефир",
            category = "продукты",
            amount = 99.99,
            currency = CurrencyValues.RUB
    )
    @Test
    @AllureId("0001")
    void spendingShouldBeDeletedAfterDeleteAction(SpendJson createdSpend) {

        step("Create spend", () -> {
            $(".spendings__content tbody")
                    .$$("tr")
                    .find(text(createdSpend.getDescription()))
                    .$("td")
                    .scrollTo()
                    .click();
        });
        step("Delete selected & check is deleted", () -> {
            $(byText("Delete selected")).click();
            $(".spendings__content tbody")
                    .$$("tr")
                    .shouldHave(size(0));
        });


    }
}
