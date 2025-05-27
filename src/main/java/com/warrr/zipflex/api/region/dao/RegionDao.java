package com.warrr.zipflex.api.region.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.warrr.zipflex.api.region.dto.out.DongResponseDto;
import com.warrr.zipflex.api.region.dto.out.GugunResponseDto;
import com.warrr.zipflex.api.region.dto.out.SidoResponseDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RegionDao {

    private final SqlSession sqlSession;
    private static final String NAMESPACE = "com.warrr.zipflex.api.region.dao.RegionDao.";


    public List<SidoResponseDto> findAllSido() {
        return sqlSession.selectList(NAMESPACE + "findAllSido");
    }


    public List<GugunResponseDto> findAllGugun(@Param("sidoCodePrefix") String sidoCodePrefix) {
        Map<String, String> param = new HashMap<>();
        param.put("sidoCodePrefix", sidoCodePrefix);
        return sqlSession.selectList(NAMESPACE + "findAllGugun", sidoCodePrefix);
    }


    public List<DongResponseDto> findAllDong(@Param("gugunCodePrefix") String gugunCodePrefix) {
        Map<String, String> param = new HashMap<>();
        param.put("gugunCode", gugunCodePrefix);
        return sqlSession.selectList(NAMESPACE + "findAllDong", gugunCodePrefix);
    }

}
