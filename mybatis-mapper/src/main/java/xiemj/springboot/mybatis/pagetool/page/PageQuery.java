package xiemj.springboot.mybatis.pagetool.page;

/**
 * @author xiemingjie
 * @version 1.0.0
 * @Type ${TYPE_NAME}
 * @Desc
 * @Date 2018/9/4
 */
public class PageQuery<T> {
    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private Integer startPos = 0;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if (pageNum != null && pageNum >= 0) {
            this.pageNum = pageNum;
        }
        this.startPos = (this.pageNum - 1) * this.pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != null && pageSize > 0) {
            this.pageSize = pageSize;
        }
        this.startPos = (this.pageNum - 1) * this.pageSize;
    }

    public Integer getStartPos() {
        return startPos;
    }
}
