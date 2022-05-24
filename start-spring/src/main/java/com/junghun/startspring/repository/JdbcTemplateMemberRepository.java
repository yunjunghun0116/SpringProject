package com.junghun.startspring.repository;

import com.junghun.startspring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements  MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("name",member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> memberList = jdbcTemplate.query("select * from member where id = ?",memberRowMapper(),id);
        return memberList.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> memberList = jdbcTemplate.query("select * from member where name = ?",memberRowMapper(),name);
        return memberList.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //쿼리수행결과를 각 행마다 memberRowMapper()를 수행해서 쿼리결과 -> 멤버List로 반환을 받을 수 있다
        return jdbcTemplate.query("select * from member",memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper(){
        //여기서 ResultSet(rs) -> 결과와 현재행번호를 받아오고, 매 결과마다 해당 작업을 수행해서 멤버로 넘겨준다
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }

}
