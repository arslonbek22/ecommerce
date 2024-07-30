package org.example.eccommerceinspringboot.repo;

import org.example.eccommerceinspringboot.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentRepo extends JpaRepository<Attachment, UUID> {
}
