package com.j1.common.base;

import com.j1.type.ProductFieldEnum;

import java.io.Serializable;
import java.util.*;

/**
 * Created by wangchuanfu on 20/7/16.
 */
//搜索排序
public class Sort implements Iterable {
    public static final Direction DEFAULT_DIRECTION = Direction.ASC;

    private List<Order> orders;

    public Sort()
    {
    }

    public Sort(Order... orders)
    {
        this(Arrays.asList(orders));
    }

    // 预设定的排序Map
    private final static Map<String, Order> orderMap = new HashMap<String, Order>();

    static
    {
        // 默认排序(评分)
        Order order99 = new Order(Direction.DESC, "_score");
        orderMap.put("99", order99);
        // 自定义评分（目前用于库存0排至最后）
        Order order98 = new Order(Direction.DESC, ProductFieldEnum.stock.name());
        orderMap.put("98", order98);

        //97--促销（Y/N）
        Order order97 = new Order(Direction.DESC, ProductFieldEnum.discount.name());
        orderMap.put("97", order97);

        // order字段降序(高到低)
        Order order0 = new Order(Direction.DESC, ProductFieldEnum.productOrder.name());
        orderMap.put("0", order0);

        // 1--销量降序(高到低)
        Order order1 = new Order(Direction.DESC, ProductFieldEnum.saleAmount.name());
        orderMap.put("1", order1);
        // 2--价格降序（高到低）
        Order order2 = new Order(Direction.DESC, ProductFieldEnum.ecPrice.name());
        orderMap.put("2", order2);
        // 3--价格升序（低到高）
        Order order3 = new Order(Direction.ASC, ProductFieldEnum.ecPrice.name());
        orderMap.put("3", order3);
        // 4 --评论降序
        Order order4 = new Order(Direction.DESC, ProductFieldEnum.commentSum.name());
        orderMap.put("4", order4);
        //5   --上架时间(最近)
        Order order5=new Order(Direction.DESC,ProductFieldEnum.saleTime.name());
        orderMap.put("5", order5);
        //6   --点击总量(降序)
        Order order6=new Order(Direction.DESC,ProductFieldEnum.clickAmount.name());
        orderMap.put("6", order6);
        //7   --好评总量(降序)
        Order order7=new Order(Direction.DESC,ProductFieldEnum.goodEvalAmount.name());
        orderMap.put("7", order7);
        //8   --销量升序(低到高)
        Order order8=new Order(Direction.ASC,ProductFieldEnum.saleAmount.name());
        orderMap.put("8", order8);
        //增加两个排序2015-06-26 zhuzhong,品牌列表查询
        //9   --上架时间(最近)
        Order order9=new Order(Direction.ASC,ProductFieldEnum.saleTime.name());
        orderMap.put("9", order9);
        // 10 --评论降序
        Order order10 = new Order(Direction.ASC, ProductFieldEnum.commentSum.name());
        orderMap.put("10", order10);
        // 评论升降同一标准
        Order order101 = new Order(Direction.DESC, ProductFieldEnum.commentSum.name());
        orderMap.put("101", order101);
        Order order102 = new Order(Direction.ASC, ProductFieldEnum.commentSum.name());
        orderMap.put("102", order102);

    }


    public Sort(String... orderTypes)
    {
        List<Order> orders = new ArrayList<Order>();
        for(String orderType : orderTypes)
        {
            if(orderMap.containsKey(orderType))
                orders.add(orderMap.get(orderType));
        }
        this.orders = orders;
    }


    public void add(Order order){
        if(orders==null){
            orders = new ArrayList<Order>();
        }
        orders.add(order);
    }

    public Sort(Order order,String ...orderTypes){

        List<Order> orders = new ArrayList<Order>();
        if(null!=order){
            orders.add(order);
        }

        for(String orderType : orderTypes)
        {
            if(orderMap.containsKey(orderType))
                orders.add(orderMap.get(orderType));
        }

        this.orders=orders;
    }

    public Sort(List<Order> orders)
    {

        // if (null == orders || orders.isEmpty()) {
        // throw new
        // IllegalArgumentException("You have to provide at least one sort property to sort by!");
        // }

        this.orders = orders;
    }

    public Sort(Direction direction, String... properties)
    {

        this(direction, properties == null ? new ArrayList<String>() : Arrays
                .asList(properties));
    }

    public Sort(Direction direction, List<String> properties)
    {

        if (properties == null || properties.isEmpty())
        {
            throw new IllegalArgumentException(
                    "You have to provide at least one property to sort by!");
        }

        this.orders = new ArrayList<Order>(properties.size());

        for (String property : properties)
        {
            this.orders.add(new Order(direction, property));
        }
    }

    public Sort and(Sort sort)
    {

        if (sort == null)
        {
            return this;
        }

        ArrayList<Order> these = new ArrayList<Order>(this.orders);

        for (Order order : sort.orders)
        {
            these.add(order);
        }

        return new Sort(these);
    }

    public Order getOrderFor(String property)
    {

        for (Order order : this.orders)
        {
            if (order.getProperty().equals(property))
            {
                return order;
            }
        }

        return null;
    }

    public Iterator<Order> iterator()
    {

        return this.orders.iterator();
    }
    @Override
    public boolean equals(Object obj)
    {

        if (this == obj)
        {
            return true;
        }

        if (!(obj instanceof Sort))
        {
            return false;
        }

        Sort that = (Sort) obj;

        return this.orders.equals(that.orders);
    }

    @Override
    public int hashCode()
    {

        int result = 17;
        result = 31 * result + orders.hashCode();
        return result;
    }

    @Override
    public String toString()
    {

        return orders.toString();
    }

    /**
     *
     * @包名 com.huayuan.search.core.common.page
     * @类名 Direction
     * @描述 排序方式枚举
     *
     * @author 槐实(karlspace7@gmail.com)
     * @version 上午11:06:38, 2014年3月19日
     */
    public static enum Direction
    {

        ASC, DESC;

        public static Direction fromString(String value)
        {

            try
            {
                return Direction.valueOf(value.toUpperCase(Locale.US));
            }
            catch (Exception e)
            {
                throw new IllegalArgumentException(
                        String.format(
                                "Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).",
                                value), e);
            }
        }
    }

    public static class Order
    {


        private final Direction direction;
        private final String property;

        public Order()
        {
            this.direction = DEFAULT_DIRECTION;
            this.property = "";
        }

        public Order(Direction direction, String property)
        {

            if (property == null || "".equals(property.trim()))
            {
                throw new IllegalArgumentException(
                        "PropertyPath must not null or empty!");
            }

            this.direction = direction == null ? DEFAULT_DIRECTION : direction;
            this.property = property;
        }

        public Order(String property)
        {
            this(DEFAULT_DIRECTION, property);
        }

        public static List<Order> create(Direction direction,
                                         Iterable<String> properties)
        {

            List<Order> orders = new ArrayList<Sort.Order>();
            for (String property : properties)
            {
                orders.add(new Order(direction, property));
            }
            return orders;
        }

        public Direction getDirection()
        {
            return direction;
        }

        public String getProperty()
        {
            return property;
        }

        public boolean isAscending()
        {
            return this.direction.equals(Direction.ASC);
        }

        public Order with(Direction order)
        {
            return new Order(order, this.property);
        }

        public Sort withProperties(String... properties)
        {
            return new Sort(this.direction, properties);
        }

        @Override
        public int hashCode()
        {

            int result = 17;

            result = 31 * result + direction.hashCode();
            result = 31 * result + property.hashCode();

            return result;
        }

        @Override
        public boolean equals(Object obj)
        {

            if (this == obj)
            {
                return true;
            }

            if (!(obj instanceof Order))
            {
                return false;
            }

            Order that = (Order) obj;

            return this.direction.equals(that.direction)
                    && this.property.equals(that.property);
        }

        @Override
        public String toString()
        {
            return String.format("%s: %s", property, direction);
        }
    }
}
