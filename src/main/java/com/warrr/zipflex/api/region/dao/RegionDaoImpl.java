package com.warrr.zipflex.api.region.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.warrr.zipflex.api.region.dto.DongDto;
import com.warrr.zipflex.api.region.dto.GugunDto;
import com.warrr.zipflex.api.region.dto.SidoDto;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RegionDaoImpl implements RegionDao {

    private final SqlSession sqlSession;
    private static final String NAMESPACE = "com.warrr.zipflex.api.region.dao.RegionDao.";

    @Override
    public List<SidoDto> findAllSido() {
        return sqlSession.selectList(NAMESPACE + "findAllSido");
    }

    @Override
    public List<GugunDto> findAllGugun(@Param("sidoCodePrefix") String sidoCodePrefix) {
        Map<String, String> param = new HashMap<>();
        param.put("sidoCodePrefix", sidoCodePrefix); // ✅ XML #{gugunCode} 와 정확히 일치
        return sqlSession.selectList(NAMESPACE + "findAllGugun", sidoCodePrefix);
    }

    @Override
    public List<DongDto> findAllDong(@Param("gugunCodePrefix") String gugunCodePrefix) {
        Map<String, String> param = new HashMap<>();
        param.put("gugunCode", gugunCodePrefix); // ✅ XML #{gugunCode} 와 정확히 일치
        return sqlSession.selectList(NAMESPACE + "findAllDong", param);
    }

}
