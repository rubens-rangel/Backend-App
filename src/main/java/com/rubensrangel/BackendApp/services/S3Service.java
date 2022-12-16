package com.rubensrangel.BackendApp.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);
    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    public URI uploadFile(MultipartFile multipartFile) {
        try {
            String filename = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            return uploadFile(is, filename, contentType);
        } catch (IOException e) {
            throw new RuntimeException("Erro de IO: "+ e.getMessage());
        }
    }

    public URI uploadFile(InputStream is, String filename, String contType) {
        try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contType);
            LOG.info("iniciando upload");
            s3client.putObject(bucketName, filename, is, meta);
            LOG.info("iniciando finalizado");
            return s3client.getUrl(bucketName, filename).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Erro ao converter URL para URI");
        }
    }
}
