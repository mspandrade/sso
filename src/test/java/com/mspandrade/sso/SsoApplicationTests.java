package com.mspandrade.sso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SsoApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println(new BCryptPasswordEncoder().encode("wqKe3Bb3w3kB2FRn"));
	}

}
