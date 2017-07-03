package com;

import lombok.Data;

/**
 * Created by Aaron on 27/06/2017.
 */
@BackOperateType(name="qixinNotify")
@Data
public class QixinNotify {
    private String title;
    private String content;
}
