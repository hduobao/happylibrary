package com.hf.happylibrary;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class HappyLibraryApplicationTests {

    @Test
    void contextLoads() throws InterruptedException, ParseException {
        Date date1 = new Date();
//        Date date1 = new Date();
//        Thread.sleep(1000);
//        Date date2 = new Date();
        System.out.println((date1.getTime()));

    }

}
