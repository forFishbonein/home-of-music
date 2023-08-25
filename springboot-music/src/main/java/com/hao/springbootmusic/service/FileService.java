package com.hao.springbootmusic.service;

import org.springframework.core.io.Resource;

public interface FileService {
    public Resource loadFileAsResource(String fileName);
}
