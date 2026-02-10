package com.educandoweb.course.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

    private final Path root = Paths.get("uploads/products");

    public String save(MultipartFile file) {

        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
            Path destination = root.resolve(filename);

            Files.copy(file.getInputStream(), destination);

            return "http://localhost:8080/uploads/products/" + filename;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar imagem", e);
        }
    }
}

