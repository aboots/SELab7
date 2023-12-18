به نام خدا

# آزمایش هفتم آزمایشگاه مهندسی نرم‌افزار (Test Coverage) 
## مقدمات آزمایش
لینک گیت‌هاب پروژه: https://github.com/aboots/SELab7

در این آزمایش از روش Test Coverage کمک می‌گیریم و هدف ما این است که با افزودن بخش‌هایی به کد تست، اعداد پوشش آزمون را مورد تمامی کلاس‌های موجود در برنامه افزایش می‌دهیم.

## شرح آزمایش

### بررسی پوشش آزمون در پروژه‌ی json-simle

بعد از باز کردن پروژه و کانفیگ‌ کردن JDK و موارد مشابه تست‌ها را همراه coverage اجرا می‌کنیم. در زیر درصد پوشش این پروژه را در بخش‌های مختلف می‌بینید.

![1](https://github.com/aboots/SELab7/assets/59336942/925dde14-ad08-42a7-b7f6-ab97bdd4d692)
![2](https://github.com/aboots/SELab7/assets/59336942/01f8dbf9-411f-4b39-afff-6947cb29056d)
![3](https://github.com/aboots/SELab7/assets/59336942/85952570-dde9-450b-bbe1-4093886366ec)


همچنین خروجی HTML از آن می‌گیریم که در فولدر report_json-simple آن را مشاهده می‌کنید. 

![4](https://github.com/aboots/SELab7/assets/59336942/8db76bf1-1b9d-4f07-9400-004cb10509c0)

همانطور که در زیر در اسکرین‌شات می‌بینید این خروجی HTML در هر فایل نشان می‌دهد که کجا‌ها کاور شده‌اند.

![5](https://github.com/aboots/SELab7/assets/59336942/0abdc6c2-7961-4974-b6c7-3973b56c8434)

### بررسی پوشش آزمون در پروژه‌ی CodeCoverageProject و بهبود آن
نخست همانند آزمایش قبل باز می‌کنیم پروژه را و کانفیگ‌های مربوطه را انجام داده و build می‌کنیم. حالا در زیر در اسکرین‌شات‌های بعدی به تفکیک کلاس‌ها و سطح‌های مختلف مشاهده می‌کنید.

![6](https://github.com/aboots/SELab7/assets/59336942/69319622-30d3-48a7-866c-acc78675c8e7)
![7](https://github.com/aboots/SELab7/assets/59336942/a4a33927-e8bb-4aaa-ae9a-0334d81e7d15)
![8](https://github.com/aboots/SELab7/assets/59336942/ec456f37-ef4c-423f-9921-c88db62efd70)

حال به کلاس‌های موجود تست‌های مربوطه را اضافه می‌کنیم تا درصد پوشش را تا حد امکان زیاد کنیم. 
نخست در کلاس CodeCoverageProject/src/test/java/com/unittest/codecoverage/service/PersonServiceTest.java تست‌هایی را اضفه می‌کنیم که تمام توابع و حالت‌های مختلف کلاس PersonServiceImpl تست شود. 5 تست زیر را برای حالت‌های مختلف توابع insert و update و delete با حالت‌های خاص و رخداد‌های exception اضافه می‌کنیم که در زیر می‌بینید.

```java
	@Test
	public void testUpdate_shouldThrowPersonExceptionWhenPersonIsNull() {
		List<String> expectedErrors = Lists.newArrayList("Name is required", "Gender is required");
		String expectedMessage = String.join(";", expectedErrors);
		Person person = null;

		assertThatThrownBy(() -> service.update(person))
			.isInstanceOf(PersonException.class)
			.hasFieldOrPropertyWithValue("errors", expectedErrors)
			.hasMessage(expectedMessage);
	}

	@Test
	public void testGet_shouldReturnPersonWhenNameIsProvided() {
		String name = "Name";
		Person person = new Person();
		person.setName(name);
		person.setAge(21);
		person.setGender(Gender.M);

		when(repository.get(any(String.class))).thenReturn(person);

		Person result = service.get(name);

		assertEquals(person, result);
		verify(repository, times(1)).get(name);
	}

	@Test
	public void testGet_shouldThrowPersonExceptionWhenNameIsNull() {
		String name = null;

		assertThatThrownBy(() -> service.get(name))
			.isInstanceOf(PersonException.class)
			.hasMessage("Name is required");
	}

	@Test
	public void testDelete_shouldDeletePersonWhenNameIsProvided() {
		String name = "Name";

		doNothing().when(repository).delete(any(String.class));

		service.delete(name);
		verify(repository, times(1)).delete(name);
	}

	@Test
	public void testDelete_shouldThrowPersonExceptionWhenNameIsNull() {
		String name = null;

		assertThatThrownBy(() -> service.delete(name))
			.isInstanceOf(PersonException.class)
			.hasMessage("Name is required");
	}
```

حال در کلاس CodeCoverageProject/src/test/java/com/unittest/codecoverage/service/TrafficBehaviorServiceTest.java یک تست اضافه می‌کنیم که حالت بدون exception و حالت عادی را بدون exception پاس کند.
```java
	@Test
	@DisplayName("Should not throw exception when footpassenger crosses the road safely")
	public void testFootpassengerCrossTheStreet_shouldNotThrowBehaviorExceptionWhenFootpassengerCrossesTheRoadSafely() {

		Traffic currentTrafic = new Traffic();
		currentTrafic.setIntenseCarTraffic(false);

		Footpassenger currentFootpassengerBehavior = new Footpassenger();
		currentFootpassengerBehavior.setCrossedTheRoad(true);
		currentFootpassengerBehavior.setCrossedTrafficLigth(TrafficLigth.GREEN);
		currentFootpassengerBehavior.setLookedToTheLeft(true);
		currentFootpassengerBehavior.setLookedToTheRight(true);

		Assertions.assertThatCode(() -> trafficBehaviorService.footpassengerCrossTheStreet(currentTrafic, currentFootpassengerBehavior))
				.doesNotThrowAnyException();
	}
```

حال یک کلاس CodeCoverageProject/src/test/java/com/unittest/codecoverage/repositories/PersonRepositoryTest.java می‌سازیم و انواع توابع کلاس PersonRepository را تست می‌کنیم که درحالت عادی اصلا تست نشده بودند که در زیر این تست‌ها را می‌بینید. (نکته قابل توجه این است که تست‌ها طبق توابع درون کلاس‌ها نوشته شده‌است ولی در مواردی رفتار کلاس اگر اشتباه بود تصحیح شده است.)
```java
package com.unittest.codecoverage.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.unittest.codecoverage.models.Person;

import static org.junit.jupiter.api.Assertions.*;

public class PersonRepositoryTest {

    private PersonRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new PersonRepository();
    }

    @Test
    public void testInsert_shouldInsertPersonSuccessfully() {
        Person person = new Person();
        person.setName("Name");
        person.setAge(21);

        Person result = repository.insert(person);

        assertEquals(person, result);
    }

    @Test
    public void testInsert_shouldThrowNullPointerExceptionWhenPersonIsNull() {
        assertThrows(NullPointerException.class, () -> repository.insert(null));
    }


    @Test
    public void testUpdate_shouldThrowNullPointerExceptionWhenPersonIsNull() {
        assertThrows(NullPointerException.class, () -> repository.update(null));
    }

    @Test
    public void testDelete_shouldThrowNullPointerExceptionWhenNameIsNull() {
        assertThrows(NullPointerException.class, () -> repository.delete(null));
    }

    @Test
    public void testGet_shouldGetPersonSuccessfully() {
        String name = "Name";

        Person result = repository.get(name);

        assertNull(result);
    }

    @Test
    public void testGet_shouldThrowNullPointerExceptionWhenNameIsNull() {
        assertThrows(NullPointerException.class, () -> repository.get(null));
    }

    @Test
    public void testUpdate_shouldUpdatePersonSuccessfullyWhenNameIsNotNull() {
        Person person = new Person();
        person.setName("Name");
        person.setAge(21);

        assertDoesNotThrow(() -> repository.update(person));
    }

    @Test
    public void testDelete_shouldDeletePersonSuccessfullyWhenNameIsNotNull() {
        String name = "Name";

        assertDoesNotThrow(() -> repository.delete(name));
    }

}
```

همچنین حال یک کلاس CodeCoverageProject/src/test/java/com/unittest/codecoverage/models/TrafficTest.java می‌سازیم و توابعی از traffic که تست نشده‌اند (هر چند بیشتر توابع مهم شده بودند) را تست می‌کنیم.
```java
package com.unittest.codecoverage.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrafficTest {

    private Traffic traffic;

    @BeforeEach
    public void setUp() {
        traffic = new Traffic();
    }

    @Test
    public void testCurrentTrafficLight() {
        TrafficLigth expected = TrafficLigth.YELLOW;
        traffic.setCurrentTrafficLight(expected);
        TrafficLigth actual = traffic.getCurrentTrafficLight();
        assertEquals(expected, actual);
    }

    @Test
    public void testIntenseCarTraffic() {
        boolean expected = true;
        traffic.setIntenseCarTraffic(expected);
        boolean actual = traffic.intenseCarTraffic();
        assertEquals(expected, actual);
    }

    @Test
    public void testMaxSpeedAllowed() {
        short expected = 60;
        traffic.setMaxSpeedAllowed(expected);
        short actual = traffic.getMaxSpeedAllowed();
        assertEquals(expected, actual);
    }

    @Test
    public void testMinSpeedAllowed() {
        short expected = 20;
        traffic.setMinSpeedAllowed(expected);
        short actual = traffic.getMinSpeedAllowed();
        assertEquals(expected, actual);
    }

    @Test
    public void testStreetDirectionFlow() {
        StreetDirectionFlow expected = StreetDirectionFlow.ONE_WAY;
        traffic.setStreetDirectionFlow(expected);
        StreetDirectionFlow actual = traffic.getStreetDirectionFlow();
        assertEquals(expected, actual);
        traffic.setStreetDirectionFlow(StreetDirectionFlow.TWO_WAY);
        actual = traffic.getStreetDirectionFlow();
        assertEquals(StreetDirectionFlow.TWO_WAY, actual);
    }
}
```

حال سراغ کلاس CodeCoverageProject/src/test/java/com/unittest/codecoverage/models/PersonTest.java می‌رویم و آن را می‌سازیم و مواردی که هنوز تست نشده‌اند از Person (با این که اکثرا در تست‌های دیگر ظاهر بودند) را اضافه می‌کنیم.

```java
package com.unittest.codecoverage.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonTest {

    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person();
    }

    @Test
    public void testAge() {
        person.setAge(20);
        int age = person.getAge();
        assertEquals(age, 20);
    }
}
```
حال به سراغ کلاس CodeCoverageProject/src/test/java/com/unittest/codecoverage/models/FootpassengerTest.java می‌رویم و آن را می‌سازیم و مواردی که از کلاس Footpassenger تست نشده‌اند را می‌سازیم.  

```java
package com.unittest.codecoverage.models;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FootpassengerTest {

    private Footpassenger footpassenger;

    @BeforeEach
    public void setUp() {
        footpassenger = new Footpassenger();
    }

    @Test
    public void testAge() {
        footpassenger.setCrossedTheCrosswalk(true);
        boolean actual = footpassenger.crossedTheCrosswalk();
        assertTrue(actual);
    }
}
```

یک سری تغییرات ریز دیگر انجام می‌دهیم تا مطمئن شویم که همه چیز تست شده و coverage ما به سمت 100 میل می‌کند. در نهایت می‌توان نتیجه را در زیر دید که اگر با عکس بالا نگاه کنید ما پوشش را در سطح کلاس از 76 به 92 و در سطح خطوط از 59 به 95 بردیم. اون 5% هم یکی تست برنامه‌ی کلی است که تست از قبل نوشته شده بود ولی کامنت شده چون به spring نیاز دارد، بقیه هم مواردی مثل پکیج و این موارد در کلاس‌های enum است. بقیه‌ی موارد همه کامل شده است.

![9](https://github.com/aboots/SELab7/assets/59336942/eac697b4-9268-4014-bd55-27425272fd68)
![10](https://github.com/aboots/SELab7/assets/59336942/af8d96ba-c56a-49c7-9d97-c8d9e9c9f874)

در نهاتی همین‌‌طور که در زیر می‌بینید در ابتدا 21 تست موجود بود (با احتساب تست کامنت شده) ولی در نهایت به 42 تست رسید و دو برابر شد که کاوریج هم بسیار در تمام موارد بالا رفت.

Before:

![tests-before](https://github.com/aboots/SELab7/assets/59336942/7bcf502b-9a3d-48cd-8dcb-830ce4381983)

After:

![tests-after](https://github.com/aboots/SELab7/assets/59336942/5e123513-7b1e-4503-84cd-6a09044d9fa1)

در نهایت در پوشه‌ی report_codecoverageproject می‌توانید خروجی HTML coverage پس از اضافه شدن تست‌های ما را مشاهده کنید.