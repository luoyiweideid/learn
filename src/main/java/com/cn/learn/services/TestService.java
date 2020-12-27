package com.cn.learn.services;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.learn.dto.TestDTO;
import com.cn.learn.entity.Test;
import com.cn.learn.mapper.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TestService {

    @Resource
    private RestHighLevelClient client;
    @Resource
    private TestMapper testMapper;


    /**
     * 添加索引
     * @param testDTO 实体
     */
    public void addTestIndexByName(TestDTO testDTO) {
        //从数据库读取用户信息，然后填充至UserSearch类中，转为json，提交给elastic
        LambdaQueryWrapper<Test> selectList = new QueryWrapper<Test>()
                .lambda().like(Test::getDesc, testDTO.getDesc());
        List<Test> testList = testMapper.selectList(selectList);
        for (Test test : testList) {
            try {
                IndexRequest request = new IndexRequest("test")
                        .id(test.getTestId().toString())
                        .source(beanToMap(test));
                IndexResponse response = client.index(request, RequestOptions.DEFAULT);
                if (response.status().getStatus() != 201){
                    log.info("索引未创建成功");
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        log.info("索引创建成功");
    }

    /**
     * 查询数据
     * @param desc 搜索关键字
     */
    public List<Test> getByDesc(String desc) {
        //创建查询请求
        SearchRequest request = new SearchRequest("test");
        //构建查询参数，比如查询数量，查询耗费时间上限等
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.timeout(new TimeValue(20, TimeUnit.SECONDS));
        //查询字段
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("desc", desc);
        searchSourceBuilder.query(matchQueryBuilder);
        //将查询参数注入查询请求
        request.source(searchSourceBuilder);
        List<Test> testList = new ArrayList<>();
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            if (response.status().getStatus() != 200) {
                log.info("查询失败！");
            } else {
                log.info("查询到数量： " + response.getHits().getTotalHits().value);
                List<Long> idList = new ArrayList<>();
                for (SearchHit searchHit : response.getHits()) {
                    String sourceAsString = searchHit.getSourceAsString();
                    String id = searchHit.getId();
                    idList.add(Long.parseLong(id));
                    log.info(id);
                    log.info(sourceAsString);
                }
                testList = idList.stream().map(this::getById).collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testList;
    }

    /**
     * 查询 - 根据id
     * @param id 主键id
     * @return Test
     */
    public Test getById(Long id) {
        return testMapper.selectById(id);
    }


    /**
     * 对象转map
     * @param bean bean
     * @param <T> 实体
     * @return 实体
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                if (beanMap.get(key) != null) {
                    map.put(key + "", beanMap.get(key));
                }
            }
        }
        return map;
    }
}


