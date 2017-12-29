
package com.connxun.ttdj.entity;

/**
 * @Author anna
 * @Date 2017-12-09 17:54
 * @Description 发帖图片实体类
 */
public class PostMessageImageItem {

    //图片ID
    private int id;
    //图片路径
    private String path;


    public PostMessageImageItem(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
