package com.wu.conditional;

public class LinuxListService implements ListService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
