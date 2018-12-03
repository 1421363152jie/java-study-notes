package cn.mj.ecps.model;

import java.util.List;

public class Page {

    /**
     * 开始行号
     */
    private int startNum = 0;

    /**
     * 结束行号
     */
    private int endNum = 0;

    /**
     * 每页记录数
     */
    private int pageSize = 5;

    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 总记录数
     */
    private int totalCount = 0;

    /**
     * 总页数
     */
    private int totalPage = 1;

    /**
     * 结果集合
     */

    private List<?> list;

    public int getStartNum() {
        return (pageNum-1)*pageSize;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getEndNum() {
        return pageNum*pageSize+1;
    }

    public void setEndNum(int endNum) {
        this.endNum = endNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        totalPage=totalCount/pageSize;
        if(totalCount==0||totalCount%pageSize!=0){
            totalPage++;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

}
