package cyng.mmview.repository.security;


import cyng.mmview.service.MembersService;


@SpringBootTest
public class passwordEncoderTest {

    @Autowired
    private MembersService membersService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void setPasswordEncoder() {
        String raw = "qwer1234";
        String encoded = passwordEncoder.encode(raw);
        System.out.println(encoded);

        assertAll(
                () -> assertNotEquals(raw, encoded),
                () -> assertTrue(passwordEncoder.matches(raw, encoded))
        );
    }
}
