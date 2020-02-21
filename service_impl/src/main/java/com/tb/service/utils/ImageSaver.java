package com.tb.service.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.tb.service.impl.exceptions.CustomFileException;
import com.tb.service.impl.exceptions.FileNotFoundException;

@Component
public class ImageSaver {

    private final Logger logger = LoggerFactory.getLogger(ImageSaver.class);

    @Value("${path.folder.photo.user}")
    private String photoFolderPath;

    @PostConstruct
    public void createImageFolder() {
        File imageStore = new File(photoFolderPath);
        logger.info("Trying to create base path for images, {}", imageStore.getAbsolutePath());
        if (!new File(photoFolderPath).mkdirs()) {
            logger.info("Can't create image storage");
        } else {
            logger.info("Base image path was created");
        }
    }

    public String updateImage(MultipartFile file, String name, String oldUrl) {
        String filePath = photoFolderPath + name + LocalDateTime.now();
        if (!file.isEmpty()) {
            if (!oldUrl.isEmpty()) {
                deleteFile(oldUrl);
                logger.info("nonononoo");
            }
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                logger.error("Can't save file: " + e);
                throw new CustomFileException("Can't save file: " + e.getMessage());
            }
        } else {
            throw new FileNotFoundException(file.getOriginalFilename());
        }
        return filePath;
    }

    public void deleteFile(String url) {
        try {
            File file = new File(url);
            file.delete();
        } catch (Exception e) {
            logger.error("Can't remove file: " + e);
            throw new CustomFileException("Can't remove file: " + e.getMessage());
        }
    }

    public byte[] getFileContent(String url) {
        File file = new File(url);
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            return IOUtils.toByteArray(inputStream);
        } catch (Exception e) {
            logger.error("Can't get file: " + e);
            throw new CustomFileException("Can't get file: " + e.getMessage());
        }
    }
}
