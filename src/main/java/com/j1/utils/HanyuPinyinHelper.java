//package com.j1.utils;
//
//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
//import org.wltea.analyzer.core.IKSegmenter;
//import org.wltea.analyzer.core.Lexeme;
//
//import java.io.StringReader;
//import java.util.Iterator;
//import java.util.LinkedHashSet;
//import java.util.Set;
//
///**
// * <pre>
// * CLASS:
// * Describe class, extends and implements relationships to other classes.
// *
// * RESPONSIBILITIES:
// * High level list of things that the class does
// * -)
// *
// * COLABORATORS:
// * List of descriptions of relationships with other classes, i.e. uses, contains, creates, calls...
// * -) class   relationship
// * -) class   relationship
// *
// * USAGE:
// * Description of typical usage of class.  Include code samples.
// */
//public class HanyuPinyinHelper {
//    /**
//     11      * 将文字转为汉语拼音
//     12      * @param chineselanguage 要转成拼音的中文
//     13      */
//  public static String toHanyuPinyin(String ChineseLanguage){
//      char[] cl_chars = ChineseLanguage.trim().toCharArray();
//            String hanyupinyin = "";
//             HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//             defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
//             defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
//             defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V) ;
//             try {
//                     for (int i=0; i<cl_chars.length; i++){
//                             if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")){// 如果字符是中文,则将中文转为汉语拼音
//                                     hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
//                                 } else {// 如果字符不是中文,则不转换
//                                     hanyupinyin += cl_chars[i];
//                                 }
//                         }
//                 } catch (BadHanyuPinyinOutputFormatCombination e) {
//                     System.out.println("字符不能转成汉语拼音");
//                 }
//             return hanyupinyin;
//         }
//
//          public static String getFirstLettersUp(String ChineseLanguage){
//             return getFirstLetters(ChineseLanguage ,HanyuPinyinCaseType.UPPERCASE);
//         }
//
//          public static String getFirstLettersLo(String ChineseLanguage){
//             return getFirstLetters(ChineseLanguage ,HanyuPinyinCaseType.LOWERCASE);
//         }
//
//          public static String getFirstLetters(String ChineseLanguage,HanyuPinyinCaseType caseType) {
//             char[] cl_chars = ChineseLanguage.trim().toCharArray();
//             String hanyupinyin = "";
//             HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//             defaultFormat.setCaseType(caseType);// 输出拼音全部大写
//             defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
//             try {
//                     for (int i = 0; i < cl_chars.length; i++) {
//                            String str = String.valueOf(cl_chars[i]);
//                           if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
//                                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0].substring(0, 1);
//                                 } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
//                                    hanyupinyin += cl_chars[i];
//                                 } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
//                                     hanyupinyin += cl_chars[i];
//                                 } else {// 否则不转换
//                                    hanyupinyin += cl_chars[i];//如果是标点符号的话，带着
//                                }
//                         }
//                 } catch (BadHanyuPinyinOutputFormatCombination e) {
//                     System.out.println("字符不能转成汉语拼音");
//                }
//            return hanyupinyin;
//         }
//
//        public static String getPinyinString(String ChineseLanguage){
//             char[] cl_chars = ChineseLanguage.trim().toCharArray();
//             String hanyupinyin = "";
//             HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部大写
//             defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
//            try {
//                     for (int i = 0; i < cl_chars.length; i++) {
//                             String str = String.valueOf(cl_chars[i]);
//                             if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
//                                 String[] arr = PinyinHelper.toHanyuPinyinStringArray(
//                                         cl_chars[i], defaultFormat);
//                                 if(arr == null){
//                                     //没有识别出来的汉字
//                                     continue;
//                                 }
//                                 hanyupinyin +=arr[0];
//                                 } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
//                                     hanyupinyin += cl_chars[i];
//                                 } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
//
//                                     hanyupinyin += cl_chars[i];
//                                 } else {// 否则不转换
//                                 }
//                         }
//                 } catch (BadHanyuPinyinOutputFormatCombination e) {
//                     System.out.println("字符不能转成汉语拼音");
//                 }
//             return hanyupinyin;
//         }
//      /**
//        * 取第一个汉字的第一个字符
//       * @Title: getFirstLetter
//       * @Description: TODO
//       * @return String
//       * @throws
//        */
//      public static String getFirstLetter(String ChineseLanguage){
//         char[] cl_chars = ChineseLanguage.trim().toCharArray();
//         String hanyupinyin = "";
//         HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//         defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 输出拼音全部大写
//         defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
//         try {
//                 String str = String.valueOf(cl_chars[0]);
//                 if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
//                         hanyupinyin = PinyinHelper.toHanyuPinyinStringArray(
//                                 cl_chars[0], defaultFormat)[0].substring(0, 1);;
//                     } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
//                         hanyupinyin += cl_chars[0];
//                     } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
//
//                         hanyupinyin += cl_chars[0];
//                     } else {// 否则不转换
//
//                     }
//             } catch (BadHanyuPinyinOutputFormatCombination e) {
//                 System.out.println("字符不能转成汉语拼音");
//             }
//         return hanyupinyin;
//     }
//
//    private static  Set<String> ikAnalysSmartSplit(String str){
//        //独立Lucene实现
//        StringReader re = new StringReader(str);
//        IKSegmenter ik = new IKSegmenter(re,true);
//        Lexeme lex = null;
//        try {
//            Set<String> set = new LinkedHashSet<>();
//            while((lex=ik.next())!=null){
//                set.add(lex.getLexemeText());
//            }
//            return set;
//        }catch (Exception e) {
//            // TODO: handle exception
//        }
//        return null;
//    }
//
//    /**
//     * 将汉语使用iksmart分词并转换成拼音
//     * @return
//     */
//    public static String hanyuIkAnalyzedToPinYin(String title){
//        Set<String> set = ikAnalysSmartSplit(title);
//        Iterator<String> iterator = set.iterator();
//        StringBuffer sb = new StringBuffer();
//        while (iterator.hasNext()){
//            String pinyin = getPinyinString(iterator.next());
//            //TODO 拼音转换工具会把绿色,转换成lu:se,是否要替换成lvse
//            /*if(pinyin.indexOf("u:") >-1){
//                pinyin = pinyin.replace("u:","v");
//            }*/
//            sb.append(pinyin).append(" ");
//        }
//        return sb.toString();
//    }
//
//     public static void main(String[] args) {
//         HanyuPinyinHelper hanyuPinyinHelper = new HanyuPinyinHelper() ;
//
//         //System.out.println(hanyuPinyinHelper.toHanyuPinyin("手机"));
//
//         System.out.println( HanyuPinyinHelper.hanyuIkAnalyzedToPinYin("韩都衣舍2018新款女装春装韩版毛边修身显瘦百搭牛仔长裤JQ6820樰"));
//         HanyuPinyinHelper.hanyuIkAnalyzedToPinYin("Apple iPhone 6s Plus (A1699) 128G 玫瑰金色 移动联通电信4G手机 绿色");
//
//
//     }
//}
