package com;

import lombok.Data;

/**
 * Created by Aaron on 27/06/2017.
 */
@BackOperateType(name="sendEmail")
@Data
public class SendEmail {
    private String title;
    private String name;
}
