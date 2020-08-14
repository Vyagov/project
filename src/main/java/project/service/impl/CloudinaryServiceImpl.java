package project.service.impl;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.service.CloudinaryService;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String tempFile = String.valueOf(timestamp.getTime());

        File file = File.createTempFile(tempFile, multipartFile.getContentType());
        multipartFile.transferTo(file);

        return this.cloudinary.uploader().upload(file, new HashMap<>()).get("url").toString();
    }
}
