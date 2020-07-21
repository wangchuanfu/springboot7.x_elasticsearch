package com.j1.common.base;

/**
 * Created by wangchuanfu on 20/7/20.
 */
public class PageRequest implements Pageable{
    private int p = 0;
    private int s = 28;
    private Sort sort;

    public PageRequest()
    {

    }

    public PageRequest(int page, int size)
    {

        this(page, size, null);
    }

    public PageRequest(int page, int size, Sort.Direction direction,
                       String... properties)
    {

        this(page, size, new Sort(direction, properties));
    }

    public PageRequest(int page, int size, Sort sort)
    {

        if (0 > page)
        {
            throw new IllegalArgumentException(
                    "Page index must not be less than zero!");
        }

        if (0 >= size)
        {
            throw new IllegalArgumentException(
                    "Page size must not be less than or equal to zero!");
        }

        this.p = page - 1 < 0 ? 0 : page - 1;
        this.s = size;
        this.sort = sort;
    }

    /**
     * 获得每页显示内容数
     */
    public int getPageSize()
    {
        return s;
    }

    /**
     * 获得当前页数
     */
    public int getPageNumber()
    {

        return p;
    }

    /**
     * 偏移值=每页显示内容数*当前页数
     */
    public int getOffset()
    {

        return p * s;
    }

    public Sort getSort()
    {

        return sort;
    }

    @Override
    public boolean equals(final Object obj)
    {

        if (this == obj)
        {
            return true;
        }

        if (!(obj instanceof PageRequest))
        {
            return false;
        }

        PageRequest that = (PageRequest) obj;

        boolean pEqual = this.p == that.p;
        boolean sEqual = this.s == that.s;

        boolean sortEqual = this.sort == null ? that.sort == null : this.sort
                .equals(that.sort);

        return pEqual && sEqual && sortEqual;
    }

    @Override
    public int hashCode()
    {

        int result = 17;

        result = 31 * result + p;
        result = 31 * result + s;
        result = 31 * result + (null == sort ? 0 : sort.hashCode());

        return result;
    }

    public int getPage()
    {
        return p;
    }

    public void setPage(int p)
    {
        this.p = p < 1 ? 0 : p;
        this.p = p > 99 ? 99 : p;
    }

    public void setP(int p)
    {
        this.p = p < 1 ? 0 : p;
        this.p = p > 99 ? 99 : p;
    }

    public int getSize()
    {
        return s;
    }

    public void setSize(int s)
    {
        this.s = s;
    }

    public void setS(int s)
    {
        this.s = s;
    }

    public void setSort(Sort sort)
    {
        this.sort = sort;
    }
}
