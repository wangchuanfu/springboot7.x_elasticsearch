package com.j1.xiaoxiang.utils;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.j1.dao.GoodsSkuExpandMapper;
import com.j1.dao.TBizSelectionGoodsSpuMapper;
import com.j1.dao.TGmGoodsMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.j1.xiaoxiang.utils.ReadFileUtils.readFile;

@Slf4j
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeFeature {
    private Set<String> brandSet;
    private Set<String> categorySet;
    private Set<String> seriesNameSet;
    private Set<String> stopWordsSet;
   // @Value("${se.feature.wordRelatedBrand}")
    private String wordRelatedBrandDict;
    private Map<String,List<String>> wordRelatedBrandMap;
    private Map<String, List<String>> wordRelatedCategoryMap;
    private Map<String, List<String>> wordRelatedSeriesNameMap;
 //   @Value("${se.feature.wordRelatedCategory}")
    private String wordRelatedCategoryDict;
  //  @Value("${se.feature.wordRelatedSerisName}")
    private String wordRelatedSerisName="https://cdn.xiaoxiangyoupin.com/se/seV3/wordRelatedSerisName.txt";
 //   @Value("${se.feature.myBrands}")
    private String myBrandsUrl;
 //   @Value("${se.feature.filterCategary}")
    private String filterCategary;
 //   @Value("${se.feature.replaceSk}")
    private String replaceSkUrl="https://cdn.xiaoxiangyoupin.com/se/seV3/repalceSk.txt";
//    @Value("${se.feature.skPersonFenCi}")
    private String skPersonFenCiUrl="https://cdn.xiaoxiangyoupin.com/se/seV3/skPersonFenCi.txt";

    @Autowired
    private GoodsSkuExpandMapper gsGoodsSinaExpandHelper;
    @Autowired
    private TBizSelectionGoodsSpuMapper tBizSelectionGoodsSpuMapper;
    @Autowired
    private TGmGoodsMapper tGmGoodsMapper;



    private Cache<String, List<String>> cacherBrands = Caffeine
            .newBuilder()
            .expireAfterWrite(120, TimeUnit.MINUTES)
            .build();

    private Cache<String, ConcurrentHashMap<String,List<String>>> cacherfilterCategary = Caffeine
            .newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .build();

    private StopRecognition filter = null;

    private Map<String, String> lastModifiedRecord = new HashMap<>();
    private Map<String,String> eTagsRecord = new HashMap<>();

    public List<String> getBrands(String keys) {
        return cacherBrands.get(keys,k->{
            switch (k){
                case "brands":
                    return loadAllBrands();//查询搜索所以的brands
                case "categarys":
                    return loadAllCategarys();//查询搜索所以的categary
                default:
                    return new ArrayList<>();
            }
        });
    }

    /**
     * 查询搜索品牌
     * @return
     */
    private List<String> loadAllBrands() {
        List<String> brandsList = gsGoodsSinaExpandHelper.selectAllBrands();
        List<String> tGmGoodsBrand = tGmGoodsMapper.selectBrandName();
        //读取文件
        String wordRelatedContent= readFile(myBrandsUrl);
        if(StringUtils.isNotBlank(wordRelatedContent)){
            String[] str = wordRelatedContent.toString().split("\\n");
            Arrays.stream(str).forEach(e->brandsList.add(e.trim()));
        }

        brandsList.addAll(tGmGoodsBrand);
        Set<String> brandsLists =brandsList.stream().map(e->formatStr(e)).collect(Collectors.toSet());
        return brandsLists.stream().collect(Collectors.toList());
    }

    private String formatStr(String word){
        if(word == null){
            return "";
        }
        word=word.toLowerCase();
        if(word.contains("（")){
            word = word.replace("（","/").replace("）","");
        }
        if(word.contains("(")){
            word = word.replace("(","/").replace(")","");
        }
        if(word.contains("、")){
            word = word.replace("、","/");
        }
        //if(word.endsWith())
        return word;
    }

    private  List<String> loadAllCategarys() {

        List<String> categaryList = gsGoodsSinaExpandHelper.selectAllCategary();
        List<String> tGmGoodsCategary3 = tGmGoodsMapper.selectCategaryName();
        categaryList.addAll(tGmGoodsCategary3);
        Set<String> categorySet = new HashSet<>();
        if (CollectionUtils.isNotEmpty(categaryList)) {
            for (String s : categorySet) {
                s = s.toLowerCase();
                String[] split = s.split("/");
                if (split.length > 0) {
                    categorySet.addAll(Arrays.asList(split));
                }
            }
        }
        Set<String> categorySets = categorySet.stream().map(e -> formatStr(e)).collect(Collectors.toSet());
        return categorySets.stream().collect(Collectors.toList());
    }

    private  ConcurrentHashMap<String,List<String>> loadPersonFenCi() {
        ConcurrentHashMap loadRepalceSk=new ConcurrentHashMap<String,List<String>>();
        String loadRepalceSkContent = readFile(skPersonFenCiUrl);
        if (StringUtils.isNotBlank(loadRepalceSkContent)) {
            String strs[] = loadRepalceSkContent.toString().split("\\n");
            Arrays.stream(strs).forEach(e->{
                String[] split = e.split("->");
                loadRepalceSk.put(split[0], Arrays.stream((split[1]+",").split(",")).filter(x->StringUtils.isNotBlank(x)).collect(Collectors.toList()));
            });
        }
        return loadRepalceSk;
    }
    private  ConcurrentHashMap<String,List<String>> loadRepalceSk() {
        ConcurrentHashMap loadRepalceSk=new ConcurrentHashMap<String,List<String>>();
        String loadRepalceSkContent = readFile(replaceSkUrl);
        if (StringUtils.isNotBlank(loadRepalceSkContent)) {
            String strs[] = loadRepalceSkContent.toString().split("\\n");
            Arrays.stream(strs).forEach(e->{
                String[] split = e.split("->");
                loadRepalceSk.put(split[0], Arrays.stream((split[1]+",").split(",")).filter(x->StringUtils.isNotBlank(x)).collect(Collectors.toList()));
            });
        }
        return loadRepalceSk;
    }
    private  ConcurrentHashMap<String,List<String>> loadFilterCategary() {
        ConcurrentHashMap loadFilterCategary=new ConcurrentHashMap<String,List<String>>();
        String filterCategaryContent = readFile(filterCategary);
        if (StringUtils.isNotBlank(filterCategaryContent)) {
            String strs[] = filterCategaryContent.toString().split("\\n");
            Arrays.stream(strs).forEach(e->{
                String[] split = e.split("->");
                loadFilterCategary.put(split[0], Arrays.stream((split[1]+",").split(",")).filter(x->StringUtils.isNotBlank(x)).collect(Collectors.toList()));
            });
        }
        return loadFilterCategary;
    }
    public ConcurrentHashMap<String,List<String>> getFilterCategary(String key) {
        return cacherfilterCategary.get(key, k -> {
            switch (k) {
                case "filterCategary":
                    return loadFilterCategary();
                case "repalceSk":
                    return loadRepalceSk();
                case "personFenCi":
                    return loadPersonFenCi();
                default:
                    return new ConcurrentHashMap<String,List<String>>();
            }
        });
    }

    public Feature getFeature(String sk) {
        Result parseResult = DicAnalysis.parse(sk);
        Set<String> brandFeature=parseResult.getTerms().stream().filter(term->brandSet.contains(term)
        ).map(Term::getName).collect(Collectors.toSet());
        if(brandSet.contains(sk)){
            brandFeature.add(sk);
        }
        Set<String> relatedBrandFeature = parseResult.getTerms().stream()
                .filter(term -> wordRelatedBrandMap.containsKey(term.getName()))
                .flatMap(term->wordRelatedBrandMap.get(term.getName()).stream())
                .collect(Collectors.toSet());
        brandFeature.addAll(relatedBrandFeature);
        Set<String> categoryFeature = parseResult.getTerms().stream()
                .filter(term -> categorySet.contains(term.getName()))
                .map(Term::getName)
                .collect(Collectors.toSet());
        if(categorySet.contains(sk)){
            categoryFeature.add(sk);
        }
        Set<String> relatedCategoryFeature = parseResult.getTerms().stream()
                .filter(term -> wordRelatedCategoryMap.containsKey(term.getName()))
                .flatMap(term->wordRelatedCategoryMap.get(term.getName()).stream())
                .collect(Collectors.toSet());
        categoryFeature.addAll(relatedCategoryFeature);
        Set<String> seriesNameFeature = parseResult.getTerms().stream()
                .filter(term -> seriesNameSet.contains(term.getName()))
                .map(Term::getName)
                .collect(Collectors.toSet());
        if(seriesNameSet.contains(sk)){
            seriesNameSet.add(sk);
        }
        Set<String> relatedseriesNameFeature = parseResult.getTerms().stream()
                .filter(term -> wordRelatedSeriesNameMap.containsKey(term.getName()))
                .flatMap(term->wordRelatedSeriesNameMap.get(term.getName()).stream())
                .collect(Collectors.toSet());
        seriesNameFeature.addAll(relatedseriesNameFeature);
        return new Feature(categoryFeature, brandFeature,seriesNameFeature,stopWordsSet);


    }

    public static class Feature {
        private Set<String> categorySet;
        private Set<String> brandSet;
        private Set<String> seriesNameSet;
        private Set<String> stopWordsSet;
        private String sk;
        Feature(Set<String> categorySet, Set<String> brandSet) {
            this.categorySet = categorySet;
            this.brandSet = brandSet;
        }
        Feature(Set<String> categorySet, Set<String> brandSet,Set<String> seriesNameSet, Set<String> stopWordsSet) {
            this.categorySet = categorySet;
            this.brandSet = brandSet;
            this.seriesNameSet=seriesNameSet;
            this.stopWordsSet=stopWordsSet;
        }
        Feature(Set<String> categorySet, Set<String> brandSet, String sk) {
            this.categorySet = categorySet;
            this.brandSet = brandSet;
            this.sk = sk;
        }

        public Set<String> getSeriesNameSet() {
            return seriesNameSet;
        }

        public void setSeriesNameSet(Set<String> seriesNameSet) {
            this.seriesNameSet = seriesNameSet;
        }

        public Set<String> getStopWordsSet() {
            return stopWordsSet;
        }

        public void setStopWordsSet(Set<String> stopWordsSet) {
            this.stopWordsSet = stopWordsSet;
        }

        public Set<String> getCategorySet() {
            return categorySet;
        }

        public void setCategorySet(Set<String> categorySet) {
            this.categorySet = categorySet;
        }

        public Set<String> getBrandSet() {
            return brandSet;
        }

        public void setBrandSet(Set<String> brandSet) {
            this.brandSet = brandSet;
        }

        public String getSk() {
            return sk;
        }

        public void setSk(String sk) {
            this.sk = sk;
        }
    }

}
