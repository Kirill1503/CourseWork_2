package com.example.coursework_2.impl;

import com.example.coursework_2.QuestionService;
import com.example.coursework_2.exception.CollectionAreEmptyException;
import com.example.coursework_2.exception.ElementMissingException;
import com.example.coursework_2.exception.QuestionAlreadyExistsException;
import com.example.coursework_2.model.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    @BeforeEach
    public void beforeEach() {
        questionService.add("A1", "B1");
        questionService.add("A2", "B2");
        questionService.add("A3", "B3");
    }

    @AfterEach
    public void afterEach() {
        new HashSet<>(questionService.getAll()).forEach(questionService::remove);
    }

    @Test
    void addTest() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("A4", "B4");
        Question question1 = new Question("A5", "B5");

        assertThat(questionService.add(question))
                .isEqualTo(question)
                .isIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount + 1);

        assertThat(questionService.add("A5", "B5"))
                .isEqualTo(question1)
                .isIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount + 2);
    }

    @Test
    void testNegativeAdd() {
        Question question = new Question("A1", "B1");

        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add(question));

        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add("A1", "B1"));
    }

    @Test
    void removeTest() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("A3", "B3");

        assertThat(questionService.remove(question))
                .isEqualTo(question)
                .isNotIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount - 1);
    }

    @Test
    void removeNegativeTest() {
        Question question = new Question("A4", "B4");

        assertThatExceptionOfType(ElementMissingException.class)
                .isThrownBy(() -> questionService.remove(question));
    }

    @Test
    void getAllTest() {
        int count = questionService.getAll().size();

        assertThat(questionService.getAll())
                .hasSize(count)
                .containsExactlyInAnyOrder
                        (new Question("A1", "B1"),
                                (new Question("A2", "B2")),
                                (new Question("A3", "B3")));
    }

    @Test
    void getRandomQuestionTest() {
        assertThat(questionService.getRandomQuestion())
                .isIn(questionService.getAll());
    }

    @Test
    void getRandomQuestionNegativeTest() {
        afterEach();
        assertThatExceptionOfType(CollectionAreEmptyException.class)
                .isThrownBy(questionService::getRandomQuestion);
    }
}