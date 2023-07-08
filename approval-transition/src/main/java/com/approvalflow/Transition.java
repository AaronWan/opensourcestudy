package com.approvalflow;

import lombok.Data;

import java.util.Map;

/**
 * @author wansong
 * @date 2023/6/9
 * @apiNote
 **/
@Data
public class Transition {
    String id;
    String fromId;
    String toId;
    String serialNumber;
    Node from;
    Node to;
    Map condition;
}
