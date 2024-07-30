package org.example.eccommerceinspringboot;

import org.example.eccommerceinspringboot.entity.Attachment;
import org.example.eccommerceinspringboot.repo.AttachmentRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EccommerceInSpringBootApplicationTests {

    @Autowired
    private AttachmentRepo attachmentRepo;

    @Test
    void contextLoads() {

    }

}
