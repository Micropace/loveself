package com.weibu.loveself.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页处理类 基于已有数据List进行处理
 *
 * @param <T> List元素类型
 */
public class Pagination<T> {

    //当前页
    private int page;
    //每页的数量
    private int pageSize;
    //当前页的实际数量
    private int pageRealsize;
    //是否是起始页
    private boolean isFirstPage;
    //是否是末尾页
    private boolean isLastPage;
    //总记录数
    private int total;
    //总页数
    private int pages;
    //结果集
    private List<T> list;

    @Override
    public String toString() {
        return "Pagination{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", pageRealsize=" + pageRealsize +
                ", isFirstPage=" + isFirstPage +
                ", isLastPage=" + isLastPage +
                ", total=" + total +
                ", pages=" + pages +
                ", list=" + list +
                '}';
    }

    private Pagination() {}

    public Pagination(List<T> list, Integer page, Integer pageSize) {
        if (list != null) {
            this.total = list.size();

            List<T> dataList = new ArrayList<>();

            //当前页在原数据列表的 起始、结束序号
            int start = 0, end = 0;

            //原数据列表为空
            if(this.total == 0) {
                this.page = 1;
                this.pages = 0;
                this.pageSize = this.pageRealsize = this.total;
                this.judgePageBoudary();
            }
            else {
                if(page == null)
                    this.page = 1;
                else
                    this.page = page;

                if(pageSize == null)
                    this.pageSize = this.total;
                else
                    this.pageSize = pageSize;

                //计算总页数
                this.calcPageTotalCount();

                //如果页码为0，则取全部
                if (this.page <= 0) {
                    this.page = this.pages = 1;
                    this.pageSize = this.pageRealsize = this.total;
                    this.isFirstPage = this.isLastPage = true;

                    start = 0;
                    end = this.total;
                }
                //计算对应分页在原数据列表的起始、结束序号
                else {
                    if (this.page < this.pages)
                        end = this.page * this.pageSize;
                    else {
                        this.page = this.pages;
                        end = this.total;
                    }
                    start = (this.page - 1) * this.pageSize;

                    //本页实际记录数
                    this.pageRealsize = end - start;
                    this.judgePageBoudary();
                }
                //本页数据列表
                if (end <= this.total) {
                    for (int i = start; i < end; i++) {
                        dataList.add(list.get(i));
                    }
                }
            }
            this.list = dataList;
        }
    }

    /** 计算总记录数 */
    private void calcPageTotalCount() {
        this.pages = (this.total - this.total % this.pageSize) / this.pageSize;
        if (this.total % this.pageSize > 0) this.pages += 1;
    }

    /** 判定页面边界 */
    private void judgePageBoudary() {
        isFirstPage = page == 1;
        isLastPage = page == pages || pages == 0;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageRealsize() {
        return pageRealsize;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public int getTotal() {
        return total;
    }

    public int getPages() {
        return pages;
    }

    public List<T> getList() {
        return list;
    }
}
