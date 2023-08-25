/*
 * @Time : 2022/8/12 15:17
 * @Author : hao
 * @File : FileException.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.handler;

public class FileException extends RuntimeException {

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}

