package application.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.aedificium.controller.ControllerTest;
import com.aedificium.model.domain.AllTestsDomain;

@RunWith(Suite.class)
@SuiteClasses({AllTestsDomain.class,
				ControllerTest.class})

public class AllApplicationTest {

}
