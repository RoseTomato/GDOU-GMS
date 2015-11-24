package love.drose.gms.utils;

import java.util.List;

/**
 * 分页工具类.
 * Created by lovedrose on 2015/11/23.
 */
public class Page {
    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 开始页码
     */
    private int startPage;

    /**
     * 结束页码
     */
    private int endPage;

    /**
     * 分页大小
     */
    private int pageSize ;

    /**
     * 总记录数.
     */
    private int totalRecord;

    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 开始记录索引
     */
    private int startIndex;

    /**
     * 数据.
     */
    private List list;

    public Page(int pageSize, int pageNum, int totalRecord) {

        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.totalRecord = totalRecord;


        this.totalPage = (this.totalRecord + this.pageSize - 1) / this.pageSize;

        this.startIndex = this.pageSize * (this.pageNum - 1);

        if (this.totalPage <= 10) {
            this.startPage = 1;
            this.endPage = this.totalPage;
        } else {
            this.startPage = this.pageNum - 4;
            this.endPage = this.pageNum + 5;
            if (this.startPage < 1) {
                this.startPage = 1;
                this.endPage = 10;
            }
            if (this.endPage > this.totalPage) {
                this.endPage = this.totalPage;
                this.startPage = this.totalPage - 9;
            }
        }
    }

    /**=============================================setter and getter ===========================================*/

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
