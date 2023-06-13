package com.example.coursework_2.impl;

import com.example.coursework_2.QuestionService;
import com.example.coursework_2.exception.IncorrectAmountException;
import com.example.coursework_2.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    Collection<Question> questions = Set.of(
            new Question("A1", "B1"),
            new Question("A2", "B2"),
            new Question("A3", "B3"),
            new Question("A4", "B4"),
            new Question("A5", "B5")
    );
    @Test
    void getQuestionsNegativeTest() {
        when(questionService.getAll()).thenReturn(questions);

        assertThatExceptionOfType(IncorrectAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(questions.size() + 1));

        assertThatExceptionOfType(IncorrectAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(-1));
    }

    @Test
    void getQuestionsTest() {
        when(questionService.getAll()).thenReturn(questions);

        when(questionService.getRandomQuestion()).thenReturn(
                new Question("A2", "B2"),
                new Question("A2", "B2"),
                new Question("A4", "B4"),
                new Question("A4", "B4"),
                new Question("A5", "B5")
        );

        assertThat(examinerService.getQuestions(3))
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("A2", "B2"),
                        new Question("A4", "B4"),
                        new Question("A5", "B5")
                );
    }
}