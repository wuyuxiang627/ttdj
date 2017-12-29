package com.connxun.ttdj.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Author anna
 * @Date 2017-12-07 16:57
 * @Descprition 名片相关（带分页信息）
 */

public class PCard implements Serializable {

    /*
     * last : false
     * totalElements : 107
     * totalPages : 8
     * size : 15
     * number : 0
     * sort : [{"direction":"DESC","property":"recommendvalue","ignoreCase":false,"nullHandling":"NATIVE","ascending":false}]
     * first : true
     * numberOfElements : 15
     */

    private boolean last;
    private int totalElements;
    private int totalPages;
    private int size;
    private int number;
    private boolean first;
    private int numberOfElements;
    private List<Card> content;



    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<Card> getContent() {
        return content;
    }

    public void setContent(List<Card> content) {
        this.content = content;
    }
}
