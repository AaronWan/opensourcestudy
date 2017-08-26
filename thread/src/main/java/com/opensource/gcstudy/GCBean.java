package com.opensource.gcstudy;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class GCBean {
    private String name;
    private String[] s=new String[100];
    private long createTime=System.currentTimeMillis();

    public GCBean(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("%s %s,%d s \n",Thread.currentThread().getName(),name,(System.currentTimeMillis()-createTime)/1000);
    }
}
