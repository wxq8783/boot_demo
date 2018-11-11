package com.wu.im.session;

import lombok.Data;

import java.util.List;

@Data
public class GroupSession {

    private String groupId;

    private List<String> userInfo;

}
