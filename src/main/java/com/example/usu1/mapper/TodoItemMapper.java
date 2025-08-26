package com.example.usu1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.example.usu1.dto.TodoItemDto;

// MyBatis의 매퍼 인터페이스임을 나타냅니다.
@Mapper
public interface TodoItemMapper {
    List<TodoItemDto> selectList(TodoItemDto todoItemDto);
    TodoItemDto selectTodoItem(TodoItemDto todoItemDto);

    int insertTodoItem(TodoItemDto todoItemDto);

    int deleteTodoItem(TodoItemDto todoItemDto);

    int updateTodoItem(TodoItemDto todoItemDto);

    
}