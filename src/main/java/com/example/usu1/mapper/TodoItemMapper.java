package com.example.usu1.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.usu1.dto.TodoItemDto;

// MyBatis의 매퍼 인터페이스임을 나타냅니다.
@Mapper
public interface TodoItemMapper {
    // XML 파일의 <insert> 태그 id와 동일한 메서드 이름 사용
    void insertTodoItem(TodoItemDto todoItemDto);
}