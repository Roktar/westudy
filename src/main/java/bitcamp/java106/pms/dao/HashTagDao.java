package bitcamp.java106.pms.dao;

import java.util.List;

import bitcamp.java106.pms.domain.HashTag;

public interface HashTagDao {
             
    int insert(HashTag hashTag);
    List<HashTag> selectList(int no);
}