package com.j1.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangchuanfu on 20/9/2.
 */
public class Test {
    public static void main(String[] args) {
        int[] a = {20, 2, 10, 8, 12, 17, 4, 25, 11, 6, 33, 13, 24};
       // bubbleSort();
       // binarySearch();
       // choseSort();
     // test2();
       //test3();
        //test4();
       // testList();
      //  test6();
     //   test7();
        test8();
     /* String str=  realSkWord("eeeee");
        System.out.print(str);*/
    }

    private static void test8() {
        String s="1.2.3.4";
       s= s.replace(".","-");
       System.out.print(s);
    }

    private static void test7() {
        if (new Test() {
            boolean returnFalse() {
                System.out.print("A");
                return false;
            }
        }.returnFalse()) {
            System.out.print("A");
        } else {
            System.out.print("B");
        }
    }



    private static void test6() {
        String a = new String("ab"); // a 为一个引用
        String b = new String("ab"); // b为另一个引用,对象的内容一样
        String aa = "ab"; // 放在常量池中
        String bb = "ab"; // 从常量池中查找
        if (aa == bb) // true
            System.out.println("aa==bb");
        if (a == b) // false,非同一对象
            System.out.println("a==b");
        if (a.equals(b)) // true
            System.out.println("aEQb");
        if (42 == 42.0) { // true
            System.out.println("true");
        }

    }

    /**
     * 折半查找
     */
    private static void binarySearch() {
        int[] arr = {20, 2, 10, 8, 12, 17, 4, 25, 11, 6, 33, 13, 24};
        int low = 0;
        int high = arr.length - 1;
        int key = 6;

        while ((low <= high) && (low <= arr.length - 1) && (high <= arr.length - 1)) {
            int middle = (high + low) >> 1;
            if (key == arr[middle]) {
                System.out.print("第" + (middle + 1) + "个");
                break;
            } else if (key < arr[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
    }

    /**
     * 冒泡排序
     */
    private static void bubbleSort() {

        int[] score = {20, 2, 10, 8, 12, 17, 4, 25, 11, 6, 33, 13, 24};


        for (int i = 0; i < score.length - 1; i++) { //最多做n-1趟排序
            for (int j = 0; j < score.length - i - 1; j++) {
                //对当前无序区间score[0......length-i-1]进行排序
                //(j的范围很关键，这个范围是在逐步缩小的)
                if (score[j] > score[j + 1]) { //把小的值交换到后面
                    int temp = score[j];
                    score[j] = score[j + 1];
                    score[j + 1] = temp;
                }
            }
        }
        System.out.print("最终排序结果：");
        for (int a = 0; a < score.length; a++) {
            System.out.print(score[a] + ",");
        }
    }

    /**
     * 选择排序
     */
    public static void choseSort() {
        int[] toBeSorted = {20, 2, 10, 8, 12, 17, 4, 25, 11, 6, 33, 13, 24};

        for (int i = 0; i < toBeSorted.length; i++) {
            for (int j = i + 1; j < toBeSorted.length; j++) {
                if (toBeSorted[i] > toBeSorted[j]) {
                    int temp = toBeSorted[i];
                    toBeSorted[i] = toBeSorted[j];
                    toBeSorted[j] = temp;
                }
            }
        }

        for (int i = 0; i < toBeSorted.length; i++) {
            System.out.print(toBeSorted[i] + ",");
        }

    }


    public static void test2() {
        String a = "money";
        String b = new String("money");
        System.out.println(a==b);  //比较的是地址//false
        System.out.println(a.equals(b));  //true
    }

    public static void test3() {
        String a = "money";
        String b = new String("money").intern();
        System.out.println(a == b);  //比较的是地址
        System.out.println(a.equals(b));  //比较的是值
    }
    public static void test4() {
        String a = new String("money");
        String b = new String("money");
        System.out.println(a==b);  //比较的是地址false
        System.out.println(a.equals(b));  //比较的是值true
    }
    public static void testList(){
        List<String> list = new ArrayList<String>();
        list.add("保护环境");  //向列表中添加数据
        list.add("爱护地球");  //向列表中添加数据
        list.add("从我做起");  //向列表中添加数据
        List<String>list1 = new ArrayList<String>();
        list1.add("保护环境");  //向列表中添加数据
        list1.add("爱护地球");  //向列表中添加数据
        boolean ret = list.removeAll(list1);  //从list中移除与list1相同的元素
        Iterator<String> it = list.iterator();  //创建迭代器
        while(it.hasNext()){  //循环遍历迭代器
            System.out.println(it.next());  //输出集合中元素
        }
    }
    public static String realSkWord(String str){
        return  str.replaceAll(str, "bbbb");
    }

}
