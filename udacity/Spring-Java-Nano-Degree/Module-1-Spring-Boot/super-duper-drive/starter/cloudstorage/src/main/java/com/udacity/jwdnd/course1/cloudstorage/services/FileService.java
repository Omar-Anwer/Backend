package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;


@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper ) {
        this.fileMapper = fileMapper;
    }

    public Integer createFile(File file){
        return fileMapper.insert(file);
    }

    public void updateFile(File file){
        fileMapper.update(file);
    }

    public void deleteFile(Integer fileId){
        fileMapper.delete(fileId);
    }

    public File getFile(Integer fileId){
        return fileMapper.findById(fileId);
    }
    
    public List<File> getAllUserFiles(Integer userId){
        return fileMapper.findAllUserFiles(userId);
    }

    public File findByIdAndUserId(Integer fileId, Integer userId){
        return fileMapper.findByIdAndUserId(fileId, userId);
    }

    public boolean isFileAvailable(String fileName, Integer userId) {
        File file = fileMapper.findByNameAndByUserId(fileName, userId);
        return file == null;
    }
}
