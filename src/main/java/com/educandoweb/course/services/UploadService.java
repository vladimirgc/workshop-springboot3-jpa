package com.educandoweb.course.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

    private final Path root = Paths.get("uploads");

    public String save(MultipartFile file, String folder) {

        try {
        	
        	Path folderPath = root.resolve(folder);
        	
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            String filename = UUID.randomUUID().toString().substring(0, 8) + "_" + file.getOriginalFilename();
            Path destination = folderPath.resolve(filename);

            Files.copy(file.getInputStream(), destination);

            return "http://localhost:8080/uploads/" + folder + "/" + filename;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar imagem", e);
        }
    }
}

